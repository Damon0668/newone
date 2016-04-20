package com.liefeng.property.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.FreeMarkerUtil;
import com.liefeng.intf.service.msg.IPushMsgService;
import com.liefeng.mq.type.MessageEvent;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.util.UserClientIdUtil;
import com.liefeng.property.vo.household.UserClientIdVo;
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

	/**
	 * 发送个推给多个员工
	 * @param action
	 * @param receiveUserIdList 
	 * @author xhw
	 * @date 2016年4月20日 上午11:16:32
	 */
	public void pushMsgToStaffList(String action, List<String> receiveUserIdList) {
		try {
			//获取推送消息模板
			PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(action);
			
			if(pushMsgTemplateVo != null){
				if(receiveUserIdList.size() > 1){
					
					ListUserMsg message = new ListUserMsg();
					message.setAction(action);
					message.setPageUrl(pushMsgTemplateVo.getPageUrl());
					message.setPageType(pushMsgTemplateVo.getPageType());
					message.setTitle(pushMsgTemplateVo.getTitle());
					message.setContent(pushMsgTemplateVo.getContent());
					message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
					message.setReceiveUserIdList(receiveUserIdList);
					
					pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_STAFF, PushMsgConstants.TerminalType.MOBILE_PROPERTY_WORKBENCH, message);
					logger.info("群推消息给员工{}", message);

				}else{
					SingleUserMsg message = new SingleUserMsg();
					message.setAction(action);
					message.setPageUrl(pushMsgTemplateVo.getPageUrl());
					message.setPageType(pushMsgTemplateVo.getPageType());
					message.setTitle(pushMsgTemplateVo.getTitle());
					message.setContent(pushMsgTemplateVo.getContent());
					message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
					message.setReceiveUserId(receiveUserIdList.get(0));
					pushMsgService.push2Single(MessageEvent.PUSH_TO_PROPERTY_STAFF, PushMsgConstants.TerminalType.MOBILE_PROPERTY_WORKBENCH, message);
					logger.info("单推消息员工{}", message);
				}
			}
		} catch (Exception e) {
			logger.error("[pushMsgToStaffList] Send message to rocketmq failed: {}", e);
		}
	}
	
	/**
	 * 根据userid、clientid推送消息
	 * @param action
	 * @param receiveUserIdList 
	 * @author xhw
	 * @date 2016年4月20日 上午11:16:32
	 */
	public void pushMsgOfUserIdClientId(String action, List<UserClientIdVo> staffList, List<UserClientIdVo> proprietorList) {
		try {
			//获取推送消息模板
			PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(action);
			
			if(pushMsgTemplateVo != null){
				if(staffList != null && staffList.size() > 0){
					
					List<String> clientIdList = UserClientIdUtil.getClientIdList(staffList);
					List<String> userIdList = UserClientIdUtil.getUserIdList(staffList);
					
					ListUserMsg message = new ListUserMsg();
					message.setAction(action);
					message.setPageUrl(pushMsgTemplateVo.getPageUrl());
					message.setPageType(pushMsgTemplateVo.getPageType());
					message.setTitle(pushMsgTemplateVo.getTitle());
					message.setContent(pushMsgTemplateVo.getContent());
					message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
					message.setReceiveClientIdList(clientIdList);
					message.setReceiveUserIdList(userIdList);
					
					pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_STAFF, PushMsgConstants.TerminalType.MOBILE_PROPERTY_WORKBENCH, message);
					logger.info(" 根据userid、clientid推送消息给员工{}", message);
				}
				
				if(proprietorList != null && proprietorList.size() > 0){
					
					List<String> clientIdList = UserClientIdUtil.getClientIdList(staffList);
					List<String> userIdList = UserClientIdUtil.getUserIdList(staffList);
					
					//获取推送消息模板
					ListUserMsg message = new ListUserMsg();
					message.setAction(action);
					message.setPageUrl(pushMsgTemplateVo.getPageUrl());
					message.setPageType(pushMsgTemplateVo.getPageType());
					message.setTitle(pushMsgTemplateVo.getTitle());
					message.setContent(pushMsgTemplateVo.getContent());
					message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
					message.setReceiveClientIdList(clientIdList);
					message.setReceiveUserIdList(userIdList);
					
					pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_PROPRIETOR, PushMsgConstants.TerminalType.MOBILE_PROPERTY, message);
					logger.info(" 根据userid、clientid推送消息给业主、住户{}", message);
				}
			}
		} catch (Exception e) {
			logger.error("[pushMsgOfUserIdClientId] Send message to rocketmq failed: {}", e);
		}
	}
	
	/**
	 * 根据userid、clientid推送消息
	 * @param action
	 * @param receiveUserIdList 
	 * @author xhw
	 * @date 2016年4月20日 上午11:16:32
	 */
	public void pushMsgOfUserIdClientIdAndMap(String action, Map<String, String> data, List<UserClientIdVo> staffList, List<UserClientIdVo> proprietorList) {
		try {
			//获取推送消息模板
			PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(action, data);
			
			if(pushMsgTemplateVo != null){
				if(staffList != null && staffList.size() > 0){
					
					List<String> clientIdList = UserClientIdUtil.getClientIdList(staffList);
					List<String> userIdList = UserClientIdUtil.getUserIdList(staffList);
					
					ListUserMsg message = new ListUserMsg();
					message.setAction(action);
					message.setPageUrl(pushMsgTemplateVo.getPageUrl());
					message.setPageType(pushMsgTemplateVo.getPageType());
					message.setTitle(pushMsgTemplateVo.getTitle());
					message.setContent(pushMsgTemplateVo.getContent());
					message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
					message.setReceiveClientIdList(clientIdList);
					message.setReceiveUserIdList(userIdList);
					
					pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_STAFF, PushMsgConstants.TerminalType.MOBILE_PROPERTY_WORKBENCH, message);
					logger.info(" 根据userid、clientid推送消息给员工{}", message);
				}
				
				if(proprietorList != null && proprietorList.size() > 0){
					
					List<String> clientIdList = UserClientIdUtil.getClientIdList(staffList);
					List<String> userIdList = UserClientIdUtil.getUserIdList(staffList);
					
					//获取推送消息模板
					ListUserMsg message = new ListUserMsg();
					message.setAction(action);
					message.setPageUrl(pushMsgTemplateVo.getPageUrl());
					message.setPageType(pushMsgTemplateVo.getPageType());
					message.setTitle(pushMsgTemplateVo.getTitle());
					message.setContent(pushMsgTemplateVo.getContent());
					message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
					message.setReceiveClientIdList(clientIdList);
					message.setReceiveUserIdList(userIdList);
					
					pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_PROPRIETOR, PushMsgConstants.TerminalType.MOBILE_PROPERTY, message);
					logger.info(" 根据userid、clientid推送消息给业主、住户{}", message);
				}
			}
		} catch (Exception e) {
			logger.error("[pushMsgOfUserIdClientId] Send message to rocketmq failed: {}", e);
		}
	}
	
	/**
	 * 消息中心发消息时发送个推
	 * @param action
	 * @param receiveUserIdList 
	 * @author xhw
	 * @date 2016年4月20日 上午11:16:32
	 */
	public void createWebsiteMsgPushMsg(String action, List<UserClientIdVo> userClientIdList, List<String> staffList) {
		try {
			//获取推送消息模板
			PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(action);
			
			if(pushMsgTemplateVo != null){
				if(staffList != null && staffList.size() > 0){
					
					ListUserMsg message = new ListUserMsg();
					message.setAction(action);
					message.setPageUrl(pushMsgTemplateVo.getPageUrl());
					message.setPageType(pushMsgTemplateVo.getPageType());
					message.setTitle(pushMsgTemplateVo.getTitle());
					message.setContent(pushMsgTemplateVo.getContent());
					message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
					message.setReceiveUserIdList(staffList);
					
					pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_STAFF, PushMsgConstants.TerminalType.MOBILE_PROPERTY_WORKBENCH, message);
					logger.info("消息中心发布时群推消息{}", message);
				}
				
				if(userClientIdList != null && userClientIdList.size() > 0){
					
					List<String> clientIdList = UserClientIdUtil.getClientIdList(userClientIdList);
					List<String> userIdList = UserClientIdUtil.getUserIdList(userClientIdList);
					
					//获取推送消息模板
					ListUserMsg message = new ListUserMsg();
					message.setAction(action);
					message.setPageUrl(pushMsgTemplateVo.getPageUrl());
					message.setPageType(pushMsgTemplateVo.getPageType());
					message.setTitle(pushMsgTemplateVo.getTitle());
					message.setContent(pushMsgTemplateVo.getContent());
					message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
					message.setReceiveClientIdList(clientIdList);
					message.setReceiveUserIdList(userIdList);
					
					pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_STAFF, PushMsgConstants.TerminalType.MOBILE_PROPERTY_WORKBENCH, message);
					logger.info("消息中心发布时群推消息", message);
				}
			}
		} catch (Exception e) {
			logger.error("[plushNoticePushMsg] Send message to rocketmq failed: {}", e);
		}
	}
	
	/**
	 * 发送个推给用户（业主、住户）
	 * @param action
	 * @param receiveUserId 
	 * @author xhw
	 * @date 2016年4月20日 上午11:16:59
	 */
	public void pushMsgToUser(String action, String receiveUserId) {
		try {
			//获取推送消息模板
			PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(action);
			
			if(pushMsgTemplateVo != null){
				SingleUserMsg message = new SingleUserMsg();
				message.setAction(action);
				message.setPageUrl(pushMsgTemplateVo.getPageUrl());
				message.setPageType(pushMsgTemplateVo.getPageType());
				message.setTitle(pushMsgTemplateVo.getTitle());
				message.setContent(pushMsgTemplateVo.getContent());
				message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
				message.setReceiveUserId(receiveUserId);
				pushMsgService.push2Single(MessageEvent.PUSH_TO_PROPERTY_PROPRIETOR, PushMsgConstants.TerminalType.MOBILE_PROPERTY, message);
				logger.info("推消息给单个用户（业主、住户）{}", message);
			}
		} catch (Exception e) {
			logger.error("[pushMsgToUser] Send message to rocketmq failed: {}", e);
		}
	}
	
	/**
	 * 发送个推给单个员工
	 * @param action
	 * @param receiveUserId 
	 * @author xhw
	 * @date 2016年4月20日 上午11:16:59
	 */
	public void pushMsgToStaff(String action, String receiveUserId) {
		try {
			//获取推送消息模板
			PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(action);
			
			if(pushMsgTemplateVo != null){
				SingleUserMsg message = new SingleUserMsg();
				message.setAction(action);
				message.setPageUrl(pushMsgTemplateVo.getPageUrl());
				message.setPageType(pushMsgTemplateVo.getPageType());
				message.setTitle(pushMsgTemplateVo.getTitle());
				message.setContent(pushMsgTemplateVo.getContent());
				message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
				message.setReceiveUserId(receiveUserId);
				pushMsgService.push2Single(MessageEvent.PUSH_TO_PROPERTY_STAFF, PushMsgConstants.TerminalType.MOBILE_PROPERTY_WORKBENCH, message);
				logger.info("推消息给单个员工{}", message);
			}
		} catch (Exception e) {
			logger.error("[pushMsgToStaff] Send message to rocketmq failed: {}", e);
		}
	}
	
	/**
	 * 推送给多个员工
	 * @param action
	 * @param receiveUserIdList
	 * @param data 
	 * @author xhw
	 * @date 2016年4月20日 下午2:21:15
	 */
	public void pushMsgToStaffListOfMap(String action, List<String> receiveUserIdList, Map<String, String> data) {
		try {
			//获取推送消息模板
			PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(action);
			
			if(pushMsgTemplateVo != null){
				String pageUrl = FreeMarkerUtil.parseStringTemplate(pushMsgTemplateVo.getPageUrl(), data);
				
				ListUserMsg message = new ListUserMsg();
				message.setAction(action);
				message.setPageUrl(pageUrl);
				message.setPageType(pushMsgTemplateVo.getPageType());
				message.setTitle(pushMsgTemplateVo.getTitle());
				message.setContent(pushMsgTemplateVo.getContent());
				message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
				message.setReceiveUserIdList(receiveUserIdList);
				pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_STAFF, PushMsgConstants.TerminalType.MOBILE_PROPERTY_WORKBENCH, message);
				logger.info("推消息给多个员工{}", message);
			}
		} catch (Exception e) {
			logger.error("[pushMsgToStaffListOfMap] Send message to rocketmq failed: {}", e);
		}
	}
	
	/**
	 * 推送给单个员工
	 * @param action
	 * @param receiveUserIdList
	 * @param data 
	 * @author xhw
	 * @date 2016年4月20日 下午2:21:15
	 */
	public void pushMsgToStaffOfMap(String action, String receiveUserId, Map<String, String> data) {
		try {
			//获取推送消息模板
			PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(action);
			
			if(pushMsgTemplateVo != null){
				String pageUrl = FreeMarkerUtil.parseStringTemplate(pushMsgTemplateVo.getPageUrl(), data);
				
				SingleUserMsg message = new SingleUserMsg();
				message.setAction(action);
				message.setPageUrl(pageUrl);
				message.setPageType(pushMsgTemplateVo.getPageType());
				message.setTitle(pushMsgTemplateVo.getTitle());
				message.setContent(pushMsgTemplateVo.getContent());
				message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
				message.setReceiveUserId(receiveUserId);
				pushMsgService.push2Single(MessageEvent.PUSH_TO_PROPERTY_STAFF, PushMsgConstants.TerminalType.MOBILE_PROPERTY_WORKBENCH, message);
				logger.info("推消息给单个员工{}", message);
			}
		} catch (Exception e) {
			logger.error("[pushMsgToStaffOfMap] Send message to rocketmq failed: {}", e);
		}
	}
}
