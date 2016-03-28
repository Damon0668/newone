package com.liefeng.property.domain.workbench;

import java.util.Date;
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
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.workbench.EventReportBo;
import com.liefeng.property.po.workbench.EventReportPo;
import com.liefeng.property.repository.mybatis.EventReportQueryRepository;
import com.liefeng.property.repository.workbench.EventReportRepository;
import com.liefeng.property.vo.workbench.EventReportVo;
import com.liefeng.property.vo.workbench.HeadCountVo;

/**
 * 报事领域模型
 * 
 * @author Huangama
 * @author xhw
 * @date 2016-3-3
 */
@Service
@Scope("prototype")
public class EventReportContext {

	private Logger logger = LoggerFactory.getLogger(EventReportContext.class);
	
	@Autowired
	private EventReportRepository eventReportRepository;
	
	@Autowired
	private EventReportQueryRepository eventReportQueryRepository;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 报事对象
	 */
	private EventReportVo eventReport;

	private static EventReportContext getInstance() {
		EventReportContext eventReportContext = SpringBeanUtil
				.getBean(EventReportContext.class);
		return eventReportContext;
	}

	public static EventReportContext build() {
		EventReportContext eventReportContext = getInstance();
		return eventReportContext;
	}

	public static EventReportContext build(EventReportVo eventReport) {
		EventReportContext eventReportContext = getInstance();
		eventReportContext.setEventReport(eventReport);
		return eventReportContext;
	}

	public static EventReportContext loadById(String id) {
		EventReportContext eventReportContext = getInstance();
		eventReportContext.setId(id);
		return eventReportContext;
	}

	public void create() {
		if (eventReport != null) {
			eventReport.setId(UUIDGenerator.generate());
			eventReport.setCreateTime(new Date());
			eventReport.setOemCode(ContextManager.getInstance().getOemCode());
			eventReportRepository.save(MyBeanUtil.createBean(eventReport,
					EventReportPo.class));
		}
	}
	
	public void update(){
		eventReport.setOemCode(ContextManager.getInstance().getOemCode());
		eventReport.setCreateTime(new Date());
		eventReportRepository.save(MyBeanUtil.createBean(eventReport,
				EventReportPo.class));
	}

	public EventReportVo get() {
		EventReportPo eventReportPo = eventReportRepository.findOne(id);
		return MyBeanUtil.createBean(eventReportPo, EventReportVo.class);
	}
	
	public void delete(){
		logger.info("delete eventReport id is {}",id);
		eventReportRepository.delete(id);
	}
	
	public EventReportVo findByWfOrderId(String wfOrderId){
		EventReportVo eventReportVo = eventReportQueryRepository.findByWfOrderId(wfOrderId);
		return eventReportVo;
	}
	
	public DataPageValue<EventReportVo> list(EventReportBo eventReportBo,Integer currentPage,Integer pageSize) {
		eventReportBo.setOemCode(ContextManager.getInstance().getOemCode());
		Map<String, String> extra = MyBeanUtil.bean2Map(eventReportBo);
		
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(currentPage);
		param.setPageSize(pageSize);
		
		Long total = eventReportQueryRepository.queryByCount(param);
		total = (total == null ? 0 : total);
		logger.info("EventReport List total：total={}", total);
		
		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(total);
		
		List<EventReportVo> eventReportVos = eventReportQueryRepository.queryByPage(param);
		
		return new DataPageValue<EventReportVo>(eventReportVos, total, pageSize, currentPage);
	}
	
	/**
	 * 待办理列表
	 * @param eventReportBo
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public DataPageValue<EventReportVo> getWaitingForList(EventReportBo eventReportBo,Integer currentPage,Integer pageSize){
		eventReportBo.setOemCode(ContextManager.getInstance().getOemCode());
		Map<String, String> extra = MyBeanUtil.bean2Map(eventReportBo);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(currentPage);
		param.setPageSize(pageSize);
		
		Long total = eventReportQueryRepository.waitingForQueryByCount(param);
		total = (total == null ? 0 : total);
		logger.info("EventReport List total：total={}", total);
		
		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(total);
		
		List<EventReportVo> eventReportVos = eventReportQueryRepository.waitingForQueryByPage(param);
		
		return new DataPageValue<EventReportVo>(eventReportVos, total, pageSize, currentPage);
	}
	
	/**
	 * 带签收列表
	 * @param eventReportBo
	 * @param page
	 * @param size
	 * @return
	 */
	public DataPageValue<EventReportVo> getSignForList(
			EventReportBo eventReportBo, Integer page, Integer size) {
		eventReportBo.setOemCode(ContextManager.getInstance().getOemCode());
		Map<String, String> extra = MyBeanUtil.bean2Map(eventReportBo);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(page);
		param.setPageSize(size);
		
		Long total = eventReportQueryRepository.signForQueryByCount(param);
		total = (total == null ? 0 : total);
		logger.info("EventReport List total：total={}", total);
		
		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(total);
		
		List<EventReportVo> eventReportVos = eventReportQueryRepository.signForQueryByPage(param);
		
		return new DataPageValue<EventReportVo>(eventReportVos, total, page, size);

	}
	
	/**
	 * 抢单列表
	 * @param eventReportBo
	 * @param page
	 * @param size
	 * @return
	 */
	public DataPageValue<EventReportVo> getGrabList(
			EventReportBo eventReportBo, Integer page, Integer size) {
		eventReportBo.setOemCode(ContextManager.getInstance().getOemCode());
		Map<String, String> extra = MyBeanUtil.bean2Map(eventReportBo);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(page);
		param.setPageSize(size);
		
		Long total = eventReportQueryRepository.grabQueryByCount(param);
		total = (total == null ? 0 : total);
		logger.info("EventReport List total：total={}", total);
		
		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(total);
		
		List<EventReportVo> eventReportVos = eventReportQueryRepository.grabQueryByPage(param);
		
		return new DataPageValue<EventReportVo>(eventReportVos, total, page, size);
	}
	
	/**
	 * 流转中分页
	 * @param eventReportBo
	 * @param page
	 * @param size
	 * @return
	 */
	public DataPageValue<EventReportVo> getFlowingList(
			EventReportBo eventReportBo, Integer page, Integer size) {
		eventReportBo.setOemCode(ContextManager.getInstance().getOemCode());
		Map<String, String> extra = MyBeanUtil.bean2Map(eventReportBo);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(page);
		param.setPageSize(size);
		
		Long total = eventReportQueryRepository.flowingQueryByCount(param);
		total = (total == null ? 0 : total);
		logger.info("EventReport List total：total={}", total);
		
		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(total);
		
		List<EventReportVo> eventReportVos = eventReportQueryRepository.flowingQueryByPage(param);
		
		return new DataPageValue<EventReportVo>(eventReportVos, total, page, size);
	}
	
	/**
	 * 各种状态数量
	 * @param eventReportBo
	 * @return
	 */
	public Map<String, Long> noRead(EventReportBo eventReportBo) {
		eventReportBo.setOemCode(ContextManager.getInstance().getOemCode());
		Map<String, String> extra = MyBeanUtil.bean2Map(eventReportBo);
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		
		Map<String, Long> map =new HashMap<String, Long>();
		map.put("waitingFor", eventReportQueryRepository.waitingForQueryByCount(param));
		map.put("flowing", eventReportQueryRepository.flowingQueryByCount(param));
		map.put("grab", eventReportQueryRepository.grabQueryByCount(param));
		map.put("signFor", eventReportQueryRepository.signForQueryByCount(param));
		
		return map;
	}
	
	/**
	 * 已完成列表
	 * @param eventReportBo
	 * @param page
	 * @param size
	 * @return
	 */
	public DataPageValue<EventReportVo> getCompleteList(
			EventReportBo eventReportBo, Integer page, Integer size) {
		eventReportBo.setOemCode(ContextManager.getInstance().getOemCode());
		Map<String, String> extra = MyBeanUtil.bean2Map(eventReportBo);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(page);
		param.setPageSize(size);
		
		Long total = eventReportQueryRepository.completeQueryByCount(param);
		total = (total == null ? 0 : total);
		logger.info("EventReport List total：total={}", total);
		
		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(total);
		
		List<EventReportVo> eventReportVos = eventReportQueryRepository.completeQueryByPage(param);
		
		return new DataPageValue<EventReportVo>(eventReportVos, total, page, size);

	}
	
	/**
	 * 获取用户的所有报事
	 * @param projectId
	 * @param houseNum
	 * @param phone
	 * @return 
	 * @author xhw
	 * @date 2016年3月18日 下午4:45:52
	 */
	public List<EventReportVo> getHistoryEventReport(String projectId, String houseNum, String phone){
		List<EventReportVo> eventReportVoList = null;
		if(ValidateHelper.isNotEmptyString(projectId) && ValidateHelper.isNotEmptyString(houseNum) && ValidateHelper.isNotEmptyString(phone)){
			List<EventReportPo> eventReportPoList = eventReportRepository.findByProjectIdAndHouseNumAndPhone(projectId, houseNum, phone);
			
			eventReportVoList = MyBeanUtil.createList(eventReportPoList, EventReportVo.class);
		}
		return eventReportVoList;
	}
	
	/**
	 * 获取各列表数量
	 * @param eventReportBo 查询参数
	 * @return
	 */
	public HeadCountVo getCountsToHead(EventReportBo eventReportBo) {
		eventReportBo.setOemCode(ContextManager.getInstance().getOemCode());
		Map<String, String> extra = MyBeanUtil.bean2Map(eventReportBo);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		
		// 待签收数量
		Long waitSignCount = eventReportQueryRepository.signForQueryByCount(param);
		// 抢单数量
		Long grabCount = eventReportQueryRepository.grabQueryByCount(param);
		// 待办理数量
		Long waitDealCount = eventReportQueryRepository.waitingForQueryByCount(param);
		// 流转中数量
		Long flowingCount = eventReportQueryRepository.flowingQueryByCount(param);
		// 已完成数量
		Long completeCount = eventReportQueryRepository.completeQueryByCount(param);
		//本月已处理
		Long monthCompleteCount = eventReportQueryRepository.monthCompleteQueryByCount(param);
	
		HeadCountVo headCount = new HeadCountVo();
		headCount.setWaitSignCount(waitSignCount);
		headCount.setGrabCount(grabCount);
		headCount.setWaitDealCount(waitDealCount);
		headCount.setFlowingCount(flowingCount);
		headCount.setCompleteCount(completeCount);
		headCount.setMonthCompleteCount(monthCompleteCount);
		
		return headCount;
	}
	
	/**
	 * 通过手机号码，获取报事
	 * @param phone
	 * @return 
	 * @author xhw
	 * @date 2016年3月25日 下午7:37:10
	 */
	public List<EventReportVo> getHistoryEventReportOfPhone(String phone){
		List<EventReportVo> eventReportVoList = null;
		if(ValidateHelper.isNotEmptyString(phone)){
			List<EventReportPo> eventReportPoList = eventReportRepository.findByPhone(phone);
			
			eventReportVoList = MyBeanUtil.createList(eventReportPoList, EventReportVo.class);
		}
		return eventReportVoList;
	}
	
	protected void setId(String id) {
		this.id = id;
	}

	protected void setEventReport(EventReportVo eventReport) {
		this.eventReport = eventReport;
	}

}
