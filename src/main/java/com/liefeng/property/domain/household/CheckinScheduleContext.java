package com.liefeng.property.domain.household;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.po.household.CheckinSchedulePo;
import com.liefeng.property.repository.CheckinScheduleRepository;
import com.liefeng.property.repository.mybatis.CheckinScheduleQueryRepository;
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
	
	@Autowired
	private CheckinScheduleQueryRepository checkinScheduleQueryRepository;
	
	/**
	 * 入住时间安排ID
	 */
	private String checkinScheduleId;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
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
	 * 根据项目ID加载上下文
	 * @param projectId 项目ID
	 * @return 入住时间安排上下文
	 */
	public static CheckinScheduleContext loadByProjectId(String projectId) {
		CheckinScheduleContext checkinScheduleContext = getInstance();
		checkinScheduleContext.setProjectId(projectId);
		
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
	 * 批量保存入住安排时间
	 */
	public void create(List<CheckinScheduleVo> checkinScheduleList) {
		if(ValidateHelper.isNotEmptyCollection(checkinScheduleList)) {
			String oemCode = ContextManager.getInstance().getOemCode();
			for(CheckinScheduleVo checkinSchedule : checkinScheduleList) {
				checkinSchedule.setId(UUIDGenerator.generate());
				checkinSchedule.setOemCode(oemCode);
			}
			
			List<CheckinSchedulePo> checkinSchedulePos = MyBeanUtil.createList(checkinScheduleList, CheckinSchedulePo.class);
			checkinScheduleRepository.save(checkinSchedulePos);
			
			logger.info("批量保存入住安排时间成功！");
		}
	}
	
	/**
	 * 删除入住时间安排信息
	 */
	public void delete() {
		if(ValidateHelper.isNotEmptyString(checkinScheduleId)) {
			checkinScheduleRepository.delete(checkinScheduleId);
			logger.info("根据ID删除入住安排时间成功，ID={}", checkinScheduleId);
		} if(ValidateHelper.isNotEmptyString(projectId)) {
			String oemCode = ContextManager.getInstance().getOemCode();
			checkinScheduleRepository.deleteByProjectIdAndOemCode(projectId, oemCode);
			logger.info("根据项目ID删除入住安排时间成功，项目ID={}", projectId);
		}
	}
	
	/**
	 * 获取某项目所有楼栋的入住安排时间
	 * @param projectId 项目ID
	 * @return 入住安排时间列表
	 */
	public List<CheckinScheduleVo> getCheckinSchedules() {
		List<CheckinScheduleVo> dataList = new ArrayList<CheckinScheduleVo>();
		
		if(ValidateHelper.isNotEmptyString(projectId)) {
			// 参数拷贝
			Map<String, String> extra = new HashMap<String, String>();
			extra.put("projectId", projectId);
			String oemCode = ContextManager.getInstance().getOemCode();
			extra.put("oemCode", oemCode);
			
			PagingParamVo param = new PagingParamVo();
			param.setExtra(extra);
			
			dataList = checkinScheduleQueryRepository.queryByNoPage(param);
			dataList = (ValidateHelper.isEmptyCollection(dataList) ? new ArrayList<CheckinScheduleVo>() : dataList);
		}
		
		return dataList;
	}
	
	/**
	 * 根据项目id、楼栋id，获取入住安排时间
	 * @param projectId 项目id
	 * @param buildingId 楼栋id
	 * @return 
	 * @author xhw
	 * @date 2016年3月8日 下午12:06:29
	 */
	public CheckinScheduleVo getCheckinSchedule(String projectId, String buildingId){
		CheckinScheduleVo checkinScheduleVo = null;
		
		if(ValidateHelper.isNotEmptyString(projectId) && ValidateHelper.isNotEmptyString(buildingId)){
			 CheckinSchedulePo checkinSchedulePo = checkinScheduleRepository.findByProjectIdAndBuildingId(projectId, buildingId);
			 
			 checkinScheduleVo = MyBeanUtil.createBean(checkinSchedulePo, CheckinScheduleVo.class);
		}
		
		return checkinScheduleVo;
	}
	
	protected void setCheckinScheduleId(String checkinScheduleId) {
		this.checkinScheduleId = checkinScheduleId;
	}

	protected void setCheckinSchedule(CheckinScheduleVo checkinSchedule) {
		this.checkinSchedule = checkinSchedule;
	}

	protected void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
}
