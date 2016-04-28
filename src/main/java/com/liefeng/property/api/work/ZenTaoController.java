package com.liefeng.property.api.work;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
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

import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.intf.service.msg.IPushMsgService;
import com.liefeng.mq.type.MessageEvent;
import com.liefeng.property.api.ro.work.zentao.ZenTaoRo;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.service.PropertyPushMsgService;
import com.liefeng.property.vo.household.UserClientIdVo;
import com.liefeng.service.constant.PushMsgConstants;
import com.liefeng.service.vo.PushMsgTemplateVo;
import com.liefeng.service.vo.msg.SingleUserMsg;

@Api(value="禅道模块")
@RestController
@RequestMapping(value = "/api/work/zentao")
public class ZenTaoController {
	private static Logger logger = LoggerFactory.getLogger(PropertyPushMsgService.class);
	
	@Autowired
	private IPropertyStaffService propertyStaffService;
	
	@Autowired
	private IPushMsgService pushMsgService;


	@ApiOperation(value="个推提醒员工", notes="禅道个推给员工")
	@RequestMapping(value="/pushMsgToStaff", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue pushMsgToStaff(@Valid @ModelAttribute ZenTaoRo zenTaoRo){
		
		ContextManager.getInstance().setOemCode("lf_property");
		
		UserClientIdVo userClientIdVo = propertyStaffService.findClientIdByAccount(zenTaoRo.getAccount());
		if(userClientIdVo == null){
			logger.info("通过登陆账号：{}找不到个推需要的clientId", zenTaoRo.getAccount());
		}else{
			Map<String,String> data = new HashMap<String,String>();
			data.put("id", zenTaoRo.getId());
			//获取推送消息模板
			PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(zenTaoRo.getAction(), data);
			if(pushMsgTemplateVo == null){
				logger.info("通过action：{}找不到个推需要的模板", zenTaoRo.getAction());
			}else{
				SingleUserMsg message = new SingleUserMsg();
				message.setAction(zenTaoRo.getAction());
				message.setPageUrl(pushMsgTemplateVo.getPageUrl());
				message.setPageType(pushMsgTemplateVo.getPageType());
				message.setTitle(pushMsgTemplateVo.getTitle());
				message.setContent(pushMsgTemplateVo.getContent());
				message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
				message.setReceiveUserId(userClientIdVo.getUserId());
				message.setReceiveClientId(userClientIdVo.getClientId());
				pushMsgService.push2Single(MessageEvent.PUSH_TO_PROPERTY_STAFF, PushMsgConstants.TerminalType.MOBILE_PROPERTY_WORKBENCH, message);
				
				logger.info("禅道单推消息给员工{}", message);
			}
		}
		
		return ReturnValue.success();
	}
	
}
