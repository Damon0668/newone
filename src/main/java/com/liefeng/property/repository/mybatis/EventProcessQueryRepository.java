package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.workbench.EventReportBo;
import com.liefeng.property.vo.workbench.EventProcessVo;
import com.liefeng.property.vo.workbench.EventReportVo;

/**
 * 工单处理查询
 * @author wuzhijing
 */
public interface EventProcessQueryRepository extends BaseRepository<EventProcessVo>{
	
	public List<EventProcessVo> getHisEventProcess(String orderId);

	public EventProcessVo getActiveEventProcess(String orderId,String staffid);
}
