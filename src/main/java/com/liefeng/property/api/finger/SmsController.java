package com.liefeng.property.api.finger;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.core.error.IErrorCode;
import com.liefeng.intf.service.msg.ISmsService;
import com.liefeng.property.api.ro.finger.sms.SmsSendRo;
import com.liefeng.service.vo.SMSMsgVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="短信模块")
@RestController
@RequestMapping(value = "/api/finger/sms")
public class SmsController {
	
	private static Logger logger = LoggerFactory.getLogger(SmsController.class);
	
	@Autowired
	private ISmsService smsService;
	
	@ApiOperation(value="发送短信", notes="发送短信")
	@RequestMapping(value="/send", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue send(@Valid @ModelAttribute SmsSendRo smsSend) throws Exception{
		SMSMsgVo smsMsg = MyBeanUtil.createBean(smsSend, SMSMsgVo.class);
		Map<String,String> data = JSON.parseObject(smsSend.getParamString(),new TypeReference<Map<String,String>>(){});
		smsMsg.setData(data);
		smsService.sendSMSMsg(smsMsg);
		return ReturnValue.success();
	}
}
