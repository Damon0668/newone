package com.liefeng.property.domain.workbench;

import java.util.Date;

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
import com.liefeng.property.constant.WorkbenchConstants;
import com.liefeng.property.po.workbench.NoticePo;
import com.liefeng.property.repository.workbench.NoticeRepository;
import com.liefeng.property.vo.workbench.NoticeVo;


/**
 * 通知领域上下问对象
 * @author xhw
 * @date 2016年2月26日下午3:33:11
 */
@Service
@Scope("prototype")
public class NoticeContext {
	
	private static Logger logger = LoggerFactory.getLogger(NoticeContext.class);
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	/**
	 * 客户值对象
	 */
	private NoticeVo noticeVo;
	
	/**
	 * 通知Id
	 */
	private String id;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static NoticeContext getInstance() {
		return SpringBeanUtil.getBean(NoticeContext.class);
	}
	
	/**
	 * 根据通知值对象构建上下文
	 * @param noticeVo
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午3:39:58
	 */
	public static NoticeContext build(NoticeVo noticeVo) {
		NoticeContext noticeContext = getInstance();
		noticeContext.setNoticeVo(noticeVo);
		
		return noticeContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 任务上下文
	 */
	public static NoticeContext build() {
		NoticeContext noticeContext = getInstance();
		
		return noticeContext;
	}
	
	/**
	 * 根据通知Id加载任务上下文
	 * @param id
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午3:42:23
	 */
	public static NoticeContext loadById(String id){
		NoticeContext noticeContext = getInstance();
		noticeContext.setId(id);
		
		return noticeContext;
	}
	
	/**
	 * 通过通知id，获取通知
	 * @param id
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午3:58:42
	 */
	public NoticeVo getById(){
		if (noticeVo == null) {
			if (ValidateHelper.isNotEmptyString(id)) {
				NoticePo noticePo = noticeRepository.findOne(id);

				noticeVo = MyBeanUtil.createBean(noticePo, NoticeVo.class);
			}
		}
		return noticeVo;
	}
	
	/**
	 * 创建通知对象
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午4:35:45
	 */
	public NoticeVo create(){
		if (noticeVo != null) {
			noticeVo.setId(UUIDGenerator.generate());
			noticeVo.setOemCode(ContextManager.getInstance().getOemCode());
			noticeVo.setCreateTime(new Date());
			noticeVo.setStatus(WorkbenchConstants.NoticeStatus.CHECKING);

			NoticePo noticePo = MyBeanUtil.createBean(noticeVo, NoticePo.class);
			noticeRepository.save(noticePo);
				
		}

		return noticeVo;
	}

	/**
	 * 更新通知
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午4:43:09
	 */
	public NoticeVo update(){
		if(noticeVo != null && ValidateHelper.isNotEmptyString(noticeVo.getId())){
			NoticePo noticePo = noticeRepository.findOne(noticeVo.getId());
			
			MyBeanUtil.copyBeanNotNull2Bean(noticeVo, noticePo);
			noticeRepository.save(noticePo);
			
			logger.info("Update notice of id: {} success.", noticeVo.getId());
		}
		
		return noticeVo;
	}

	protected void setNoticeVo(NoticeVo noticeVo) {
		this.noticeVo = noticeVo;
	}

	protected void setId(String id) {
		this.id = id;
	}
}
