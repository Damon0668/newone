package com.liefeng.property.api;

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
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.api.ILoginUserService;
import com.liefeng.property.api.ro.AuthLoginRo;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.vo.api.LoginUserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="权限模块")
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
	
	private static Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILoginUserService loginUserService;

	@ApiOperation(value="登陆", notes="登陆接口")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<LoginUserVo> login(@Valid @ModelAttribute AuthLoginRo authLogin){
		
		ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
		
		LoginUserVo loginUser = null;
		
		try{
			//统一鉴权登陆
			//UserLoginBo userLoginBo = MyBeanUtil.createBean(authLogin, UserLoginBo.class);
			
			//UserVo user = userService.login(userLoginBo);
			
			//获取物业系统用户信息
			loginUser = loginUserService.findLoginUser("C1603161803278af41b8e398146");

		}catch(LiefengException e){
			logger.info("鉴权失败 {}", e);
			throw e;
		}
		
		return DataValue.success(loginUser);
	}
}
