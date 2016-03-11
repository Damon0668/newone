package com.liefeng.property.domain.workbench;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.workbench.EventReportBo;
import com.liefeng.property.constant.WorkbenchConstants;
import com.liefeng.property.exception.WorkbenchException;
import com.liefeng.property.po.workbench.EventReportPo;
import com.liefeng.property.repository.mybatis.EventReportQueryRepository;
import com.liefeng.property.repository.workbench.EventReportRepository;
import com.liefeng.property.vo.fee.FeeItemVo;
import com.liefeng.property.vo.workbench.EventReportVo;

/**
 * 报事领域模型
 * 
 * @author Huangama
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
			eventReportRepository.save(MyBeanUtil.createBean(eventReport,
					EventReportPo.class));
		}
	}
	
	public void update(){
		EventReportPo eventReportPo = eventReportRepository.findOne(eventReport.getId());
		
		/*if(eventReportPo.getStatus().equals(WorkbenchConstants.EventReport.STATUS_ALREADYWORKERS)){
			throw new WorkbenchException(en)
		}*/
		
		eventReportRepository.save(MyBeanUtil.createBean(eventReport,
				EventReportPo.class));
	}

	public EventReportVo get() {
		EventReportPo eventReportPo = eventReportRepository.findOne(id);
		return MyBeanUtil.createBean(eventReportPo, EventReportVo.class);
	}
	
	public EventReportVo findByWfOrderId(String wfOrderId){
		EventReportPo eventReportPo = eventReportRepository.findByWfOrderId(wfOrderId);
		return MyBeanUtil.createBean(eventReportPo, EventReportVo.class);
	}
	
	public DataPageValue<EventReportVo> list(EventReportBo eventReportBo,Integer currentPage,Integer pageSize) {
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
	
	public DataPageValue<EventReportVo> getWaitingForList(EventReportBo eventReportBo,Integer currentPage,Integer pageSize){
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

	protected void setId(String id) {
		this.id = id;
	}

	protected void setEventReport(EventReportVo eventReport) {
		this.eventReport = eventReport;
	}
}
