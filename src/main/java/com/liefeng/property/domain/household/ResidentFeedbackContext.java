package com.liefeng.property.domain.household;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.po.household.ResidentFeedbackPo;
import com.liefeng.property.repository.household.ResidentFeedbackRepository;
import com.liefeng.property.vo.household.ResidentFeedbackVo;


/**
 * 用户反馈领域上下文对象
 * @author xhw
 * @date 2016年3月14日 上午10:03:17
 */
@Service
@Scope("prototype")
public class ResidentFeedbackContext {
	
	private static Logger logger = LoggerFactory.getLogger(ResidentFeedbackContext.class);
	
	@Autowired
	private ResidentFeedbackRepository residentFeedbackRepository;
	
	
	/**
	 * 客户值对象
	 */
	private ResidentFeedbackVo residentFeedbackVo;
	
	/**
	 * 反馈Id
	 */
	private String id;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static ResidentFeedbackContext getInstance() {
		return SpringBeanUtil.getBean(ResidentFeedbackContext.class);
	}
	
	/**
	 * 根据反馈对象，构建上下文对象
	 * @param residentFeedbackVo
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 上午10:12:09
	 */
	public static ResidentFeedbackContext build(ResidentFeedbackVo residentFeedbackVo) {
		ResidentFeedbackContext residentFeedbackContext= getInstance();
		residentFeedbackContext.setResidentFeedbackVo(residentFeedbackVo);
		
		return residentFeedbackContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 反馈上下文
	 */
	public static ResidentFeedbackContext build() {
		ResidentFeedbackContext residentFeedbackContext = getInstance();
		
		return residentFeedbackContext;
	}
	
	/**
	 * 根据任务Id加载任务上下文
	 * @param taskId 任务Id
	 * @return 任务上下文                     
	 * @author xhw
	 * @date 2016年2月19日
	 */
	public static ResidentFeedbackContext loadById(String id){
		ResidentFeedbackContext residentFeedbackContext = getInstance();
		residentFeedbackContext.setId(id);
		
		return residentFeedbackContext;
	}
	
	
	/**
	 * 创建任务
	 * @throws PropertyException                      
	 * @author xhw
	 * @date 2016年2月19日
	 */
	public ResidentFeedbackVo create() {
		if (residentFeedbackVo != null) {
			residentFeedbackVo.setId(UUIDGenerator.generate());
			residentFeedbackVo.setOemCode(ContextManager.getInstance().getOemCode());
			residentFeedbackVo.setCreateTime(new Date());

			ResidentFeedbackPo residentFeedbackPo = MyBeanUtil.createBean(residentFeedbackVo, ResidentFeedbackPo.class);
			residentFeedbackRepository.save(residentFeedbackPo);

			logger.info("Create residentFeedback : {} success.", residentFeedbackPo);
		}

		return residentFeedbackVo;
	}
	
	protected void setResidentFeedbackVo(ResidentFeedbackVo residentFeedbackVo) {
		this.residentFeedbackVo = residentFeedbackVo;
	}

	protected void setId(String id) {
		this.id = id;
	}
	
	
}
