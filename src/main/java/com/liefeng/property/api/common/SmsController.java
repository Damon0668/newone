package com.liefeng.property.api.common;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataListValue;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.service.msg.ISmsService;
import com.liefeng.property.api.ro.common.SmsSendRo;
import com.liefeng.property.api.ro.common.VerifyCodeRo;
import com.liefeng.property.api.ro.common.VerifyRo;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.service.vo.SMSMsgVo;
import com.liefeng.service.vo.SmsTemplateVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="短信模块")
@RestController
@RequestMapping(value = "/api/common/sms")
public class SmsController {
		
	@Autowired
	private ISmsService smsService;
	
	@ApiOperation(value="获取验证码", notes="获取验证码")
	@RequestMapping(value="/getVerifyCode", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<String> getVerifyCode(@Valid @ModelAttribute VerifyCodeRo verifyCodeRo) throws InterruptedException{
		
		ContextManager.getInstance().isExist(SysConstants.DEFAULT_OEM_CODE);
		
		String verifyCode = smsService.getVerifyCode(verifyCodeRo.getPhoneNum(), verifyCodeRo.getAction());
		return DataValue.success(verifyCode);
	}
	
	@ApiOperation(value="校验验证码", notes="校验验证码")
	@RequestMapping(value="/verifySmsCode", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue verifySmsCode(@Valid @ModelAttribute VerifyRo verifyRo) {

		ContextManager.getInstance().isExist(SysConstants.DEFAULT_OEM_CODE);

		smsService.verifySMSCode(verifyRo.getPhoneNum(), verifyRo.getAction(), verifyRo.getCode());
		return ReturnValue.success();
	}
	
	@ApiOperation(value="发送短信", notes="发送短信")
	@RequestMapping(value="/send", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue send(@Valid @ModelAttribute SmsSendRo smsSend) throws Exception{
		
		ContextManager.getInstance().isExist(SysConstants.DEFAULT_OEM_CODE);
		
		SMSMsgVo smsMsg = MyBeanUtil.createBean(smsSend, SMSMsgVo.class);
		Map<String,String> data = JSON.parseObject(smsSend.getParamString(),new TypeReference<Map<String,String>>(){});
		smsMsg.setData(data);
		smsService.sendSMSMsg(smsMsg);
		return ReturnValue.success();
	}
	
	@ApiOperation(value="获取模板列表", notes="获取模板列表")
	@RequestMapping(value="/getTemplates", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<SmsTemplateVo> getTemplates(){
		List<SmsTemplateVo> list = smsService.getAllTemplates();
		return DataListValue.success(list);
	}
}
