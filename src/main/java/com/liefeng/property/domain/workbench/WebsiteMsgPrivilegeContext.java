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
import com.liefeng.property.po.workbench.WebsiteMsgPrivilegePo;
import com.liefeng.property.repository.workbench.WebsiteMsgPrivilegeRepository;
import com.liefeng.property.vo.workbench.WebsiteMsgPrivilegeVo;


/**
 * 站内消息权限值对象上下文
 * @author xhw
 * @2016年3月2日 下午4:58:59
 */
@Service
@Scope("prototype")
public class WebsiteMsgPrivilegeContext {
	
	private static Logger logger = LoggerFactory.getLogger(WebsiteMsgPrivilegeContext.class);
	
	@Autowired
	private WebsiteMsgPrivilegeRepository websiteMsgPrivilegeRepository;
	
	
	/**
	 * 客户值对象
	 */
	private WebsiteMsgPrivilegeVo websiteMsgPrivilegeVo;
	
	/**
	 * 站内消息Id
	 */
	private String messageId;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static WebsiteMsgPrivilegeContext getInstance() {
		return SpringBeanUtil.getBean(WebsiteMsgPrivilegeContext.class);
	}
	
	/**
	 * 根据站内消息权限值对象构建上下文对象
	 * @param websiteMsgPrivilegeVo
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午5:03:08
	 */
	public static WebsiteMsgPrivilegeContext build(WebsiteMsgPrivilegeVo websiteMsgPrivilegeVo) {
		WebsiteMsgPrivilegeContext websiteMsgPrivilegeContext = getInstance();
		websiteMsgPrivilegeContext.setWebsiteMsgPrivilegeVo(websiteMsgPrivilegeVo);;
		return websiteMsgPrivilegeContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 站内消息上下文
	 */
	public static WebsiteMsgPrivilegeContext build() {
		WebsiteMsgPrivilegeContext websiteMsgPrivilegeContext = getInstance();
		
		return websiteMsgPrivilegeContext;
	}
	
	/**
	 * 根据站内消息id，加载站内消息权限上下文对象
	 * @param messageId 站内消息id
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午5:05:18
	 */
	public static WebsiteMsgPrivilegeContext loadByMessageId(String messageId){
		WebsiteMsgPrivilegeContext websiteMsgPrivilegeContext = getInstance();
		websiteMsgPrivilegeContext.setMessageId(messageId);
		
		return websiteMsgPrivilegeContext;
	}
	
	
	/**
	 * 创建站内消息权限
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午5:08:04
	 */
	public WebsiteMsgPrivilegeVo create(){
		if (websiteMsgPrivilegeVo != null) {
			websiteMsgPrivilegeVo.setId(UUIDGenerator.generate());
			websiteMsgPrivilegeVo.setOemCode(ContextManager.getInstance().getOemCode());
			
			WebsiteMsgPrivilegePo websiteMsgPrivilegePo = MyBeanUtil.createBean(websiteMsgPrivilegeVo, WebsiteMsgPrivilegePo.class);
			websiteMsgPrivilegeRepository.save(websiteMsgPrivilegePo);
			
			logger.info("create websiteMsgPrivilege : {} success.", websiteMsgPrivilegeVo);
		}

		return websiteMsgPrivilegeVo;
	}
	
	
	/**
	 * 根据站内消息id，获取站内消息的权限
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午5:13:07
	 */
	public List<WebsiteMsgPrivilegeVo> findByMessageId(){
		List<WebsiteMsgPrivilegeVo> websiteMsgPrivilegeVos = null;
		
		if(ValidateHelper.isNotEmptyString(messageId)){
			List<WebsiteMsgPrivilegePo> websitemsgPrivilegePos = websiteMsgPrivilegeRepository.findByMessageId(messageId);
			
			websiteMsgPrivilegeVos = MyBeanUtil.createList(websitemsgPrivilegePos, WebsiteMsgPrivilegeVo.class);
		}
		
		return websiteMsgPrivilegeVos;
	}

	/**
	 * 根据站内消息id，删除站内消息的权限
	 * 
	 * @author xhw
	 * @2016年3月2日 下午5:15:15
	 */
	public void deleteByMessageId(){
		if(ValidateHelper.isNotEmptyString(messageId)){
			websiteMsgPrivilegeRepository.deleteByMessageId(messageId);
			
			logger.info("delete messageprivilege of messageId : {} success.", messageId);
		}
	}
	
	
	protected void setWebsiteMsgPrivilegeVo(WebsiteMsgPrivilegeVo websiteMsgPrivilegeVo) {
		this.websiteMsgPrivilegeVo = websiteMsgPrivilegeVo;
	}

	protected void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	

}
