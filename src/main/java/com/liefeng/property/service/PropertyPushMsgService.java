package com.liefeng.property.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.intf.service.msg.IPushMsgService;
import com.liefeng.mq.type.MessageEvent;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.service.constant.PushMsgConstants;
import com.liefeng.service.vo.PushMsgTemplateVo;
import com.liefeng.service.vo.msg.ListUserMsg;
import com.liefeng.service.vo.msg.SingleUserMsg;

/**
 * 物业个推公共类
 * @author xhw
 * @date 2016年4月20日 上午9:54:34
 */
@Service
public class PropertyPushMsgService {

	private static Logger logger = LoggerFactory.getLogger(PropertyPushMsgService.class);
	
	@Autowired
	private IPushMsgService pushMsgService;

	public void createTaskPushMsg(String action, List<String> receiveUserIdList) {
		try {
			//获取推送消息模板
			PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(action);
			
			if(pushMsgTemplateVo != null){
				if(receiveUserIdList.size() > 1){
					
					ListUserMsg message = new ListUserMsg();
					message.setAction(action);
					message.setPageUrl(pushMsgTemplateVo.getPageUrl());
					message.setTitle(pushMsgTemplateVo.getTitle());
					message.setContent(pushMsgTemplateVo.getContent());
					message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
					message.setReceiveUserIdList(receiveUserIdList);
					
					pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_STAFF, PushMsgConstants.TerminalType.MOBILE_PROPERTY_WORKBENCH, message);
					logger.info("创建任务时群推消息{}", message);

				}else{
					SingleUserMsg message = new SingleUserMsg();
					message.setAction(action);
					message.setPageUrl(pushMsgTemplateVo.getPageUrl());
					message.setTitle(pushMsgTemplateVo.getTitle());
					message.setContent(pushMsgTemplateVo.getContent());
					message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
					message.setReceiveUserId(receiveUserIdList.get(0));
					pushMsgService.push2Single(MessageEvent.PUSH_TO_PROPERTY_STAFF, PushMsgConstants.TerminalType.MOBILE_PROPERTY_WORKBENCH, message);
					logger.info("创建任务时单推消息{}", message);
				}
			}
		} catch (Exception e) {
			logger.error("[createTaskPushMsg] Send message to rocketmq failed: {}", e);
		}
	}
		
}
