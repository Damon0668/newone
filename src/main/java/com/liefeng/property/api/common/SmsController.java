package com.liefeng.property.api.common;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.core.entity.DataValue;
import com.liefeng.intf.service.msg.ISmsService;
import com.liefeng.property.api.ro.common.VerifyCodeRo;
import com.liefeng.property.api.ro.common.VerifyRo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="短信模块")
@RestController("csmsController")
@RequestMapping(value = "/api/common/sms")
public class SmsController {
	
	private static Logger logger = LoggerFactory.getLogger(SmsController.class);
	
	@Autowired
	private ISmsService smsService;
	
	@ApiOperation(value="获取验证码", notes="获取验证码")
	@RequestMapping(value="/getVerifyCode", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<String> getVerifyCode(@Valid @ModelAttribute VerifyCodeRo verifyCodeRo){
		String verifyCode = smsService.getVerifyCode(verifyCodeRo.getPhoneNum(), verifyCodeRo.getAction());
		return DataValue.success(verifyCode);
	}
	
	@ApiOperation(value="校验验证码", notes="校验验证码")
	@RequestMapping(value="/verifySmsCode", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<Boolean> verifySmsCode(@Valid @ModelAttribute VerifyRo verifyRo){
		Boolean result = smsService.verifySMSCode(verifyRo.getPhoneNum(), verifyRo.getAction(), verifyRo.getCode());
		return DataValue.success(result);
	}
}
