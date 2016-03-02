package com.liefeng.property.domain.workbench;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.property.po.workbench.MessagePrivilegePo;
import com.liefeng.property.repository.workbench.MessagePrivilegeRepository;
import com.liefeng.property.vo.workbench.MessagePrivilegeVo;


/**
 * 消息权限值对象上下文
 * @author xhw
 * @2016年3月2日 下午4:58:59
 */
@Service
@Scope("prototype")
public class MessagePrivilegeContext {
	
	private static Logger logger = LoggerFactory.getLogger(MessagePrivilegeContext.class);
	
	@Autowired
	private MessagePrivilegeRepository messagePrivilegeRepository;
	
	
	/**
	 * 客户值对象
	 */
	private MessagePrivilegeVo messagePrivilegeVo;
	
	/**
	 * 消息Id
	 */
	private String messageId;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static MessagePrivilegeContext getInstance() {
		return SpringBeanUtil.getBean(MessagePrivilegeContext.class);
	}
	
	/**
	 * 根据消息权限值对象构建上下文对象
	 * @param messagePrivilegeVo
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午5:03:08
	 */
	public static MessagePrivilegeContext build(MessagePrivilegeVo messagePrivilegeVo) {
		MessagePrivilegeContext messagePrivilegeContext = getInstance();
		messagePrivilegeContext.setMessagePrivilegeVo(messagePrivilegeVo);
		return messagePrivilegeContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 消息上下文
	 */
	public static MessagePrivilegeContext build() {
		MessagePrivilegeContext messagePrivilegeContext = getInstance();
		
		return messagePrivilegeContext;
	}
	
	/**
	 * 根据消息id，加载消息权限上下文对象
	 * @param messageId 消息id
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午5:05:18
	 */
	public static MessagePrivilegeContext loadByMessageId(String messageId){
		MessagePrivilegeContext messagePrivilegeContext = getInstance();
		messagePrivilegeContext.setMessageId(messageId);
		
		return messagePrivilegeContext;
	}
	
	
	/**
	 * 创建消息权限
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午5:08:04
	 */
	public MessagePrivilegeVo create(){
		if (messagePrivilegeVo != null) {
			messagePrivilegeVo.setId(UUIDGenerator.generate());
			messagePrivilegeVo.setOemCode(ContextManager.getInstance().getOemCode());
			
			MessagePrivilegePo messagePrivilegePo = MyBeanUtil.createBean(messagePrivilegeVo, MessagePrivilegePo.class);
			messagePrivilegeRepository.save(messagePrivilegePo);
			
			logger.info("create messageprivilege : {} success.", messagePrivilegeVo);
		}

		return messagePrivilegeVo;
	}
	
	
	/**
	 * 根据消息id，获取消息的权限
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午5:13:07
	 */
	public List<MessagePrivilegeVo> findByMessageId(){
		List<MessagePrivilegeVo> messagePrivilegeVos = null;
		
		if(ValidateHelper.isNotEmptyString(messageId)){
			List<MessagePrivilegePo> messagePrivilegePos = messagePrivilegeRepository.findByMessageId(messageId);
			
			messagePrivilegeVos = MyBeanUtil.createList(messagePrivilegePos, MessagePrivilegeVo.class);
		}
		
		return messagePrivilegeVos;
	}

	/**
	 * 根据消息id，删除消息的权限
	 * 
	 * @author xhw
	 * @2016年3月2日 下午5:15:15
	 */
	public void deleteByMessageId(){
		if(ValidateHelper.isNotEmptyString(messageId)){
			messagePrivilegeRepository.deleteByMessageId(messageId);
			
			logger.info("delete messageprivilege of messageId : {} success.", messageId);
		}
	}
	
	
	protected void setMessagePrivilegeVo(MessagePrivilegeVo messagePrivilegeVo) {
		this.messagePrivilegeVo = messagePrivilegeVo;
	}

	protected void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	

}
