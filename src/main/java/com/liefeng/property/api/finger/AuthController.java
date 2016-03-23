package com.liefeng.property.api.finger;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.base.bo.UserLoginBo;
import com.liefeng.base.vo.UserVo;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.core.error.IErrorCode;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.api.ILoginUserService;
import com.liefeng.intf.service.msg.ISmsService;
import com.liefeng.mq.type.SMSMsgEvent;
import com.liefeng.property.api.ro.finger.auth.AuthLoginRo;
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
	
	private static Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ISmsService smsService;
	
	@Autowired
	private ILoginUserService loginUserService;

	@ApiOperation(value="用户登陆", notes="用户登陆接口")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<LoginUserVo> userLogin(@Valid @ModelAttribute AuthLoginRo authLogin){

		LoginUserVo loginUser = null;
		
		try{
			//统一鉴权登陆
			UserLoginBo userLoginBo = MyBeanUtil.createBean(authLogin, UserLoginBo.class);
			
			userLoginBo.setAppCode(SysConstants.DEFAULT_APP_CODE);
			userLoginBo.setAppType(PushMsgConstants.AppType.MOBILE);
			userLoginBo.setTerminalType(PushMsgConstants.TerminalType.MOBILE_PROPERTY);
			
			UserVo user = userService.login(userLoginBo);
			
			//获取物业系统用户信息
			loginUser = loginUserService.findLoginUser(user.getCustGlobalId());

		}catch(LiefengException e){
			logger.error("鉴权失败 {}", e);
			throw e;
		}
		
		return DataValue.success(loginUser);
	}
	
	@ApiOperation(value="忘记密码-修改密码", notes="忘记密码后,修改密码")
	@RequestMapping(value="/updatePwdByForget", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue updatePwdByForget(@Valid @ModelAttribute UpdatePwdRo updatePwdRo){
		
		smsService.verifySMSCode(updatePwdRo.getMobile(), SMSMsgEvent.SD_UPDATAPWD_MSG.getEventCode(), updatePwdRo.getCode());

		userService.updatePassword(updatePwdRo.getMobile(), updatePwdRo.getPassword());
		
		return ReturnValue.success();
	}
}
