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
import com.liefeng.property.po.workbench.NoticePrivilegePo;
import com.liefeng.property.repository.workbench.NoticePrivilegeRepository;
import com.liefeng.property.vo.workbench.NoticePrivilegeVo;

/**
 * 通知权限领域上下文对象
 * @author xhw
 * @date 2016年2月26日下午6:49:21
 */
@Service
@Scope("prototype")
public class NoticePrivilegeContext {
	
	private static Logger logger = LoggerFactory.getLogger(NoticePrivilegeContext.class);
	
	@Autowired
	private NoticePrivilegeRepository noticePrivilegeRepository;
	
	/**
	 * 客户值对象
	 */
	private NoticePrivilegeVo noticePrivilegeVo;
	
	
	/**
	 * 通知Id
	 */
	private String noticeId;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static NoticePrivilegeContext getInstance() {
		return SpringBeanUtil.getBean(NoticePrivilegeContext.class);
	}
	
	/**
	 * 根据通知权限值对象构建上下文
	 * @param noticePrivilegeVo
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午6:53:15
	 */
	public static NoticePrivilegeContext build(NoticePrivilegeVo noticePrivilegeVo) {
		NoticePrivilegeContext noticePrivilegeContext = getInstance();
		noticePrivilegeContext.setNoticePrivilegeVo(noticePrivilegeVo);
		
		return noticePrivilegeContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 任务上下文
	 */
	public static NoticePrivilegeContext build() {
		NoticePrivilegeContext noticePrivilegeContext = getInstance();
		
		return noticePrivilegeContext;
	}
	
	/**
	 * 根据通知Id加载任务上下文
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午6:57:00
	 */
	public static NoticePrivilegeContext loadById(String noticeId){
		NoticePrivilegeContext noticePrivilegeContext = getInstance();
		noticePrivilegeContext.setNoticeId(noticeId);
		
		return noticePrivilegeContext;
	}

	
	/**
	 * 创建通知权限对象
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午7:10:20
	 */
	public NoticePrivilegeVo create(){
		if (noticePrivilegeVo != null) {
			noticePrivilegeVo.setId(UUIDGenerator.generate());
			noticePrivilegeVo.setOemCode(ContextManager.getInstance().getOemCode());

			NoticePrivilegePo noticePrivilegePo = MyBeanUtil.createBean(noticePrivilegeVo, NoticePrivilegePo.class);
			noticePrivilegeRepository.save(noticePrivilegePo);
			
			logger.info("Create noticePrivilege : {} success.", noticePrivilegeVo);
		}

		return noticePrivilegeVo;
	}
	
	/**
	 * 根据通知id，删除相应的通知权限
	 *                       
	 * @author xhw
	 * @date 2016年2月26日 下午7:14:38
	 */
	public void deleteByNoticeId(){
		if(ValidateHelper.isNotEmptyString(noticeId)){
			noticePrivilegeRepository.deleteByNoticeId(noticeId);
			
			logger.info("Delete noticePrivilege of noticeId : {} success.", noticeId);
		}
	}
	
	/**
	 * 根据通知id，获取相关的通知权限
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午7:21:55
	 */
	public List<NoticePrivilegeVo> findByNoticeId(){
		List<NoticePrivilegeVo> noticePrivilegeVos = null;
		if(ValidateHelper.isNotEmptyString(noticeId)){
			List<NoticePrivilegePo> noticePrivilegePos = noticePrivilegeRepository.findByNoticeId(noticeId);
			
			noticePrivilegeVos = MyBeanUtil.createList(noticePrivilegePos, NoticePrivilegeVo.class);
		}
		
		return noticePrivilegeVos;
	}
	
	
	protected void setNoticePrivilegeVo(NoticePrivilegeVo noticePrivilegeVo) {
		this.noticePrivilegeVo = noticePrivilegeVo;
	}


	protected void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	
}
