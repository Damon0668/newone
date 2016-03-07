package com.liefeng.property.domain.household;

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
import com.liefeng.property.po.household.CheckinSchedulePo;
import com.liefeng.property.repository.CheckinScheduleRepository;
import com.liefeng.property.vo.household.CheckinScheduleVo;

/**
 * 入住时间安排上下文
 * 
 * @author ZhenTingJun
 * @date 2016年3月6日
 */
@Service
@Scope("prototype")
public class CheckinScheduleContext {

	private static Logger logger = LoggerFactory.getLogger(CheckinScheduleContext.class);
	
	@Autowired
	private CheckinScheduleRepository checkinScheduleRepository;
	
	/**
	 * 入住时间安排ID
	 */
	private String checkinScheduleId;;
	
	/**
	 * 入住时间安排值对象
	 */
	private CheckinScheduleVo checkinSchedule;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static CheckinScheduleContext getInstance(){
		return SpringBeanUtil.getBean(CheckinScheduleContext.class);
	}
	
	/**
	 * 根据入住时间安排值对象构建上下文
	 * @param checkinSchedule 入住时间安排值对象
	 * @return 入住时间安排上下文
	 */
	public static CheckinScheduleContext build(CheckinScheduleVo checkinSchedule) {
		CheckinScheduleContext checkinScheduleContext = getInstance();
		checkinScheduleContext.setCheckinSchedule(checkinSchedule);
		
		return checkinScheduleContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 入住时间安排上下文
	 */
	public static CheckinScheduleContext build() {
		CheckinScheduleContext checkinScheduleContext = getInstance();
		
		return checkinScheduleContext;
	}
	
	/**
	 * 根据入住时间安排ID加载上下文
	 * @param checkinScheduleId 入住时间安排ID
	 * @return 入住时间安排上下文
	 */
	public static CheckinScheduleContext loadById(String checkinScheduleId) {
		CheckinScheduleContext checkinScheduleContext = getInstance();
		checkinScheduleContext.setCheckinScheduleId(checkinScheduleId);
		
		return checkinScheduleContext;
	}
	
	/**
	 * 查询入住时间安排信息
	 */
	public CheckinScheduleVo get() {
		if(checkinSchedule == null) {
			CheckinSchedulePo checkinSchedulePo = null;
			if(ValidateHelper.isNotEmptyString(checkinScheduleId)) {
				checkinSchedulePo = checkinScheduleRepository.findOne(checkinScheduleId);
			}
			
			if(checkinSchedulePo != null) {
				checkinSchedule = MyBeanUtil.createBean(checkinSchedulePo, CheckinScheduleVo.class);
			}
		}
		
		return checkinSchedule;
	}
	
	/**
	 * 保存入住时间安排信息
	 */
	public void create() {
		if(checkinSchedule != null) {
			checkinSchedule.setId(UUIDGenerator.generate());
			checkinSchedule.setOemCode(ContextManager.getInstance().getOemCode());
			
			CheckinSchedulePo checkinSchedulePo = MyBeanUtil.createBean(checkinSchedule, CheckinSchedulePo.class);
			checkinScheduleRepository.save(checkinSchedulePo);
			logger.info("保存入住时间安排信息成功，checkinSchedule={}",checkinSchedule);
		}
	}
	
	/**
	 * 删除入住时间安排信息
	 */
	public void delete() {
		if(ValidateHelper.isNotEmptyString(checkinScheduleId)) {
			checkinScheduleRepository.delete(checkinScheduleId);
		} 
	}
	
	protected void setCheckinScheduleId(String checkinScheduleId) {
		this.checkinScheduleId = checkinScheduleId;
	}

	protected void setCheckinSchedule(CheckinScheduleVo checkinSchedule) {
		this.checkinSchedule = checkinSchedule;
	}
	
}
