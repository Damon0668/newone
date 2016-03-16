package com.liefeng.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.base.vo.CustomerVo;
import com.liefeng.base.vo.UserVo;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.base.ICheckService;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.intf.service.tcc.ITccMsgService;
import com.liefeng.mq.type.TccBasicEvent;
import com.liefeng.property.constant.HouseholdConstants;
import com.liefeng.property.vo.household.AppFriendVo;

/**
 * 用户公共服务类
 * @author xhw
 * @date 2016年3月11日 下午2:58:28
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICheckService checkService;
	
	@Autowired
	private ITccMsgService tccMsgService;
	
	@Autowired
	private IHouseholdService householdService;
	
	/**
	 * 获取用户信息
	 * @param custGlobalId
	 * @return 
	 * @author xhw
	 * @date 2016年3月11日 下午2:16:48
	 */
	@RequestMapping("getUser")
	@ResponseBody
	public DataValue<UserVo> getUser(String custGlobalId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		UserVo user = userService.getUserByCustGlobalId(custGlobalId);
		return DataValue.success(user);
	}
	
	/**
	 * 更新用户信息
	 * @param userVo 用户信息
	 * @return 
	 * @author xhw
	 * @date 2016年3月11日 下午2:01:53
	 */
	@RequestMapping("updateUser")
	@ResponseBody
	public ReturnValue updateUser(UserVo userVo) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		UserVo user = userService.getUserByCustGlobalId(userVo.getCustGlobalId());
		CustomerVo customerVo = user.getCustomer();
		if(customerVo != null){
			MyBeanUtil.copyBeanNotNull2Bean(userVo.getCustomer(), customerVo);
			
		}
		MyBeanUtil.copyBeanNotNull2Bean(userVo, user);
		user.setCustomer(customerVo);
		// 校验用户信息
		user = checkService.updateUserCheck(user);
		// 发送TCC消息，更新用户信息
		tccMsgService.sendTccMsg(TccBasicEvent.UPDATE_USER, user.toString());
		
		return ReturnValue.success();
	}
	
	/**
	 * 添加好友
	 * @param userId 用户id
	 * @param friendId 好友id
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午4:31:30
	 */
	@RequestMapping("createAppFriend")
	@ResponseBody
	public ReturnValue createAppFriend(String userId, String friendId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		AppFriendVo friendVo = new AppFriendVo();
		friendVo.setFriendId(friendId);
		friendVo.setUserId(userId);
		friendVo.setStatus(HouseholdConstants.AppFriendStatus.WAITING);
		householdService.createAppFriend(friendVo);
		
		return ReturnValue.success();
	}
	
	/**
	 * 对好友申请做出回应
	 * @param id 好友申请id
	 * @param status 状态
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午5:26:07
	 */
	@RequestMapping("respondAppFriendApply")
	@ResponseBody
	public ReturnValue respondAppFriendApply(String id, String status) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		householdService.updateAppFriend(id, status);
		
		return ReturnValue.success();
	}
	
	/**
	 * 删除好友
	 * @param userId
	 * @param friendId
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午5:40:18
	 */
	@RequestMapping("deleteAppFriend")
	@ResponseBody
	public ReturnValue deleteAppFriend(String userId, String friendId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		householdService.deleteAppFriend(userId, friendId);
		
		return ReturnValue.success();
	}
}
