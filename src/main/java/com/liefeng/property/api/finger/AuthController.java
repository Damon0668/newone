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
import com.liefeng.common.util.EncryptionUtil;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.api.ILoginUserService;
import com.liefeng.intf.service.cache.IRedisService;
import com.liefeng.property.api.ro.finger.auth.AuthLoginRo;
import com.liefeng.property.api.ro.finger.auth.UpdatePwdLoginRo;
import com.liefeng.property.api.ro.finger.auth.UpdatePwdRo;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.error.SecurityErrorCode;
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
	private ILoginUserService loginUserService;
	
	@Autowired
	private IRedisService redisService;

	@ApiOperation(value="用户登陆", notes="用户登陆接口,当用户没有激活[USER_UNBIND_MOBILE]或者更换手机[USER_LOGIN_MOBILE_CHANGED]登陆时需要申请验证码并填入,短信事件为SD_LOGIN_MSG")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<LoginUserVo> userLogin(@Valid @ModelAttribute AuthLoginRo authLogin){
		LoginUserVo loginUser = null;

		//统一鉴权登陆
		UserLoginBo userLoginBo = MyBeanUtil.createBean(authLogin, UserLoginBo.class);

		userLoginBo.setAppCode(SysConstants.DEFAULT_APP_CODE);
		userLoginBo.setAppType(PushMsgConstants.AppType.MOBILE);
		userLoginBo.setTerminalType(PushMsgConstants.TerminalType.MOBILE_PROPERTY);
		
		UserVo user = userService.login(userLoginBo);
		
		//获取物业系统用户信息
		loginUser = loginUserService.findLoginUser(user.getCustGlobalId(), user.getOemCode());
		
		loginUser.setUserId(user.getId());
		
		//openId加密
		String openId = user.getCustGlobalId() + "|" + loginUser.getOemCode();
		
		openId = EncryptionUtil.encrypt(openId, EncryptionUtil.OPEN_ID_PASSWORD);
		
		loginUser.setOpenId(openId);
		
		//刷新缓存中的oemCode
		String key = "openId_" + openId;
		
		if(!redisService.isKeyExist(key)){
			redisService.setValue(key, loginUser.getOemCode());
		}
		
		return DataValue.success(loginUser);
	}
	
	@ApiOperation(value="忘记密码-修改密码", notes="忘记密码后,修改密码")
	@RequestMapping(value="/updatePwdByForget", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue updatePwdByForget(@Valid @ModelAttribute UpdatePwdRo updatePwdRo){
		
		userService.updatePwdByForget(updatePwdRo.getMobile(), updatePwdRo.getPassword(), updatePwdRo.getCode());
		
		return ReturnValue.success();
	}
	
	@ApiOperation(value="登陆后-修改密码", notes="登陆后,修改密码")
	@RequestMapping(value="/updatePwdAfterLogin", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue updatePwdAfterLogin(@Valid @ModelAttribute UpdatePwdLoginRo updatePwdLoginRo){
		userService.updatePwdAfterLogin(updatePwdLoginRo.getMobile(), updatePwdLoginRo.getOldpassword(), updatePwdLoginRo.getNewpassword());		
		return ReturnValue.success();
	}

}
