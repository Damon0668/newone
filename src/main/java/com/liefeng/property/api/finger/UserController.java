package com.liefeng.property.api.finger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.base.vo.CustomerVo;
import com.liefeng.base.vo.UserVo;
import com.liefeng.common.util.TimeUtil;
import com.liefeng.core.entity.DataListValue;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.base.ICheckService;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.intf.service.tcc.ITccMsgService;
import com.liefeng.mq.type.TccBasicEvent;
import com.liefeng.property.api.ro.finger.user.AppFriendIdAndStatusRo;
import com.liefeng.property.api.ro.finger.user.AppMsgSettingRo;
import com.liefeng.property.api.ro.finger.user.ResidentFeedbackRo;
import com.liefeng.property.api.ro.finger.user.UserIdConditionRo;
import com.liefeng.property.api.ro.finger.user.UserIdFriendIdRo;
import com.liefeng.property.api.ro.finger.user.UserRo;
import com.liefeng.property.api.ro.id.CustGlobalIdRo;
import com.liefeng.property.api.ro.id.UserIdRo;
import com.liefeng.property.constant.HouseholdConstants;
import com.liefeng.property.vo.household.AppFriendVo;
import com.liefeng.property.vo.household.AppMsgSettingVo;
import com.liefeng.property.vo.household.ResidentFeedbackVo;

/**
 * 用户公共服务类
 * @author xhw
 * @date 2016年3月11日 下午2:58:28
 */
@Api(value="用户模块")
@RestController
@RequestMapping(value = "/api/finger/user")
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
	@ApiOperation(value="根据custGlobalId，获取用户信息", notes="个人资料页面")
	@RequestMapping(value="/getUserByCustGlobalId", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<UserVo> getUserByCustGlobalId(@Valid @ModelAttribute CustGlobalIdRo custGlobalIdRo) {
		
		UserVo user = userService.getUserByCustGlobalId(custGlobalIdRo.getCustGlobalId());
		return DataValue.success(user);
	}
	
	/**
	 * 更新用户信息
	 * @param userVo 用户信息
	 * @return 
	 * @author xhw
	 * @date 2016年3月11日 下午2:01:53
	 */
	@ApiOperation(value="修改用户信息", notes="修改个人资料")
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue updateUser(@Valid @ModelAttribute UserRo userRo) {
		
		UserVo user = userService.getUserByCustGlobalId(userRo.getCustGlobalId());
		CustomerVo customerVo = user.getCustomer();
		
		user.setNickName(userRo.getNickName());
		user.setAvatarUrl(userRo.getAvatarUrl());
		user.setEmail(userRo.getEmail());
		
		customerVo.setSex(userRo.getSex());
		customerVo.setMaritalStatus(userRo.getMaritalStatus());
		customerVo.setHeight(userRo.getHeight());
		customerVo.setStep(userRo.getStep());
		customerVo.setWeight(userRo.getWeight());
		customerVo.setBirthday(TimeUtil.format(userRo.getBirthday(), "yyyy-MM-dd"));
		
		user.setCustomer(customerVo);
		// 校验用户信息
		user = checkService.updateUserCheck(user);
		// 发送TCC消息，更新用户信息
		tccMsgService.sendTccMsg(TccBasicEvent.UPDATE_USER, user.toString());
		
		return ReturnValue.success();
	}
	
	/**
	 * 创建用户反馈
	 * @param houseId 房屋id
	 * @param residentId 住户/业主id
	 * @param isProprietor 是否为业主。0：否；1：是
	 * @param content 内容
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 上午11:02:05
	 */
	@ApiOperation(value="创建用户反馈", notes="创建用户反馈")
	@RequestMapping(value="/createResidentFeedback", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue createResidentFeedback(@Valid @ModelAttribute ResidentFeedbackRo residentFeedbackRo) {
		
		ResidentFeedbackVo residentFeedbackVo = new ResidentFeedbackVo();
		residentFeedbackVo.setContent(residentFeedbackRo.getContent());
		residentFeedbackVo.setHouseId(residentFeedbackRo.getHouseId());
		residentFeedbackVo.setResidentId(residentFeedbackRo.getResidentId());
		residentFeedbackVo.setIsProprietor(residentFeedbackRo.getIsProprietor());
		householdService.createResidentFeedback(residentFeedbackVo);
		
		return ReturnValue.success();
	}
	
	/**
	 * 保存用户手机端消息设置
	 * @param userId
	 * @param sound
	 * @param popFlag
	 * @param floatFlag
	 * @param lockFlag
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 下午3:06:51
	 */
	@ApiOperation(value="保存用户手机端消息设置", notes="保存用户手机端消息设置")
	@RequestMapping(value="/saveAppMsgSetting", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue saveAppMsgSetting(@Valid @ModelAttribute AppMsgSettingRo appMsgSettingRo) {
		
		AppMsgSettingVo appMsgSettingVo = householdService.getAppMsgSetting(appMsgSettingRo.getUserId());
		if(appMsgSettingVo == null){  //创建
			AppMsgSettingVo appMsgSetting = new AppMsgSettingVo();
			appMsgSetting.setFloatFlag(appMsgSettingRo.getFloatFlag());
			appMsgSetting.setLockFlag(appMsgSettingRo.getLockFlag());
			appMsgSetting.setPopFlag(appMsgSettingRo.getPopFlag());			
			appMsgSetting.setSound(appMsgSettingRo.getSound());
			
			appMsgSetting.setUserId(appMsgSettingRo.getUserId());
			
			householdService.createAppMsgSetting(appMsgSetting);
		}else{ //更新
			appMsgSettingVo.setFloatFlag(appMsgSettingRo.getFloatFlag());
			appMsgSettingVo.setLockFlag(appMsgSettingRo.getLockFlag());
			appMsgSettingVo.setPopFlag(appMsgSettingRo.getPopFlag());			
			appMsgSettingVo.setSound(appMsgSettingRo.getSound());
			appMsgSettingVo.setUserId(appMsgSettingRo.getUserId());
			appMsgSettingVo.setUpdateTime(new Date());
			
			householdService.updateAppMsgSetting(appMsgSettingVo);
		}
		
		return ReturnValue.success();
	}
	
	/**
	 * 根据用户id，获取用户手机端消息设置
	 * @param userId 用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 下午3:17:45
	 */
	@ApiOperation(value="获取用户手机端消息设置", notes="获取用户手机端消息设置")
	@RequestMapping(value="/getAppMsgSetting", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<AppMsgSettingVo> getAppMsgSetting(@Valid @ModelAttribute UserIdRo userIdRo) {
		
		AppMsgSettingVo appMsgSettingVo = householdService.getAppMsgSetting(userIdRo.getUserId());
		return DataValue.success(appMsgSettingVo);
	}
	
	/**
	 * 添加好友
	 * @param userId 用户id
	 * @param friendId 好友id
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午4:31:30
	 */
	@ApiOperation(value="添加通讯录好友", notes="添加通讯录好友")
	@RequestMapping(value="/createAppFriend", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue createAppFriend(@Valid @ModelAttribute UserIdFriendIdRo userIdFriendIdRo) {
		
		AppFriendVo friendVo = new AppFriendVo();
		friendVo.setFriendId(userIdFriendIdRo.getFriendId());
		friendVo.setUserId(userIdFriendIdRo.getUserId());
		friendVo.setStatus(HouseholdConstants.AppFriendStatus.WAITING);
		friendVo.setCreateTime(new Date());
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
	@ApiOperation(value="对加为好友的请求做出回应", notes="对加为好友的请求做出回应")
	@RequestMapping(value="/respondAppFriendApply", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue respondAppFriendApply(@Valid @ModelAttribute AppFriendIdAndStatusRo appFriendIdAndStatusRo) {
		
		householdService.updateAppFriend(appFriendIdAndStatusRo.getId(), appFriendIdAndStatusRo.getStatus());
		
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
	@ApiOperation(value="删除通讯录好友", notes="删除通讯录好友")
	@RequestMapping(value="/deleteAppFriend", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue deleteAppFriend(@Valid @ModelAttribute UserIdFriendIdRo userIdFriendIdRo) {
		
		householdService.deleteAppFriend(userIdFriendIdRo.getUserId(), userIdFriendIdRo.getFriendId());
		
		return ReturnValue.success();
	}
	
	/**
	 * 查询用户（通讯录）
	 * @param userId 用户id
	 * @param condition 过滤条件
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午8:31:54
	 */
	@ApiOperation(value="查询用户列表", notes="查询用户列表")
	@RequestMapping(value="/getUserList", method=RequestMethod.POST)
	@ResponseBody
	public DataListValue<AppFriendVo> getUserList(@Valid @ModelAttribute UserIdConditionRo userIdConditionRo) {
		
		List<AppFriendVo> appFriendVoList = householdService.getUserList(userIdConditionRo.getUserId(), userIdConditionRo.getConditon());
		
		return DataListValue.success(appFriendVoList);
	}
	
	/**
	 * 获取好友列表
	 * @param userId 用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月17日 上午9:53:24
	 */
	@ApiOperation(value="获取通讯录好友列表", notes="获取通讯录好友列表")
	@RequestMapping(value="/getAppFriendList", method=RequestMethod.POST)
	@ResponseBody
	public DataListValue<AppFriendVo> getAppFriendList(@Valid @ModelAttribute UserIdRo userIdRo) {
		
		List<AppFriendVo> appFriendVoList = householdService.getAppFriendList(userIdRo.getUserId());
		
		return DataListValue.success(appFriendVoList);
	}
	
	/**
	 * 根据用户id，获取好友操作历史
	 * @param userId 用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月17日 上午10:57:37
	 */
	@ApiOperation(value="获取通讯录好友操作历史", notes="获取通讯录好友操作历史")
	@RequestMapping(value="/getAppFriendHistoryList", method=RequestMethod.POST)
	@ResponseBody
	public DataListValue<AppFriendVo> getAppFriendHistoryList(@Valid @ModelAttribute UserIdRo userIdRo) {
		
		List<AppFriendVo> appFriendVoList = householdService.getAppFriendHistoryList(userIdRo.getUserId());
		
		return DataListValue.success(appFriendVoList);
	}
	
	/**
	 * 根据用户id，获取用户信息
	 * @param userId
	 * @return 
	 * @author xhw
	 * @date 2016年3月17日 上午11:06:39
	 */
	@ApiOperation(value="根据用户id，获取用户信息",notes="通讯录聊天页面，对方的信息")
	@RequestMapping(value="/getUserById", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<UserVo> getUserById(@Valid @ModelAttribute UserIdRo userIdRo) {
		
		UserVo user = userService.getUserById(userIdRo.getUserId());
		return DataValue.success(user);
	}
}
