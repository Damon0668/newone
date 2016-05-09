package com.liefeng.property.api.finger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.base.bo.UserLoginBo;
import com.liefeng.base.vo.UserVo;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.property.api.ro.finger.auth.AuthLoginRo;
import com.liefeng.property.api.ro.finger.auth.UpdatePwdLoginRo;
import com.liefeng.property.api.ro.finger.auth.UpdatePwdRo;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.vo.api.LoginUserVo;
import com.liefeng.service.constant.PushMsgConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="权限模块")
@RestController
@RequestMapping(value = "/api/finger/auth")
public class AuthController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IHouseholdService householdService;

	@ApiOperation(value="用户登陆", notes="调用此接口前，需要重新初始化，清空所有和用户相关的缓存信息，"
			+ "用户登陆接口,当用户没有激活[USER_UNBIND_MOBILE]或者更换手机[USER_LOGIN_MOBILE_CHANGED]登陆时需要申请验证码并填入,"
			+ "短信事件为SD_LOGIN_MSG"
			+ "登陆成功后只返回OPENID和全局ID"
			+ "需要调用房产列表和getLoginUser获取登陆用户信息")
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<LoginUserVo> userLogin(@Valid @ModelAttribute AuthLoginRo authLogin){
		
		ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
		
		LoginUserVo loginUser = null;

		//统一鉴权登陆
		UserLoginBo userLoginBo = MyBeanUtil.createBean(authLogin, UserLoginBo.class);

		userLoginBo.setAppCode(SysConstants.FINGER_APP_CODE);
		userLoginBo.setAppType(PushMsgConstants.AppType.MOBILE);
		userLoginBo.setTerminalType(PushMsgConstants.TerminalType.MOBILE_PROPERTY);
		
		UserVo user = userService.login(userLoginBo);

		loginUser = new LoginUserVo();
		loginUser.setGlobalId(user.getCustGlobalId());
		loginUser.setOpenId("default");
		
		return DataValue.success(loginUser);
	}
	
	@ApiOperation(value="忘记密码-修改密码", notes="忘记密码后,修改密码")
	@RequestMapping(value="/updatePwdByForget", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue updatePwdByForget(@Valid @ModelAttribute UpdatePwdRo updatePwdRo){
		
		ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
		
		userService.updatePwdByForget(updatePwdRo.getMobile(), updatePwdRo.getPassword(), updatePwdRo.getCode(), SysConstants.FINGER_APP_CODE);
		//个推提醒
		householdService.pushMsgToUserByPhone(updatePwdRo.getMobile());
		
		return ReturnValue.success();
	}
	
	@ApiOperation(value="登陆后-修改密码", notes="登陆后,修改密码")
	@RequestMapping(value="/updatePwdAfterLogin", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue updatePwdAfterLogin(@Valid @ModelAttribute UpdatePwdLoginRo updatePwdLoginRo){
		userService.updatePwdAfterLogin(updatePwdLoginRo.getUserId(), updatePwdLoginRo.getOldpassword(), updatePwdLoginRo.getNewpassword());		
		//个推提醒
		householdService.pushMsgToUserByPhone(updatePwdLoginRo.getUserId());
		
		return ReturnValue.success();
	}

}
