package com.liefeng.property.domain.workbench;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.repository.workbench.EventReportRepository;
import com.liefeng.property.vo.workbench.EventReportVo;

/**
 * 报事领域模型
 * @author Huangama
 * @date 2016-3-3
 */
@Service
@Scope("prototype")
public class EventReportContext {

	@Autowired
	private EventReportRepository eventReportRepository;
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 报事对象
	 */
	private EventReportVo eventReport;
	
	private static EventReportContext getInstance() {
		EventReportContext eventReportContext =  SpringBeanUtil.getBean(EventReportContext.class);
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
	
	protected void setId(String id) {
		this.id = id;
	}
	
	protected void setEventReport(EventReportVo eventReport) {
		this.eventReport = eventReport;
	}
}
