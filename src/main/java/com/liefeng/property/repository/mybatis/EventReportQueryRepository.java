package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.workbench.EventReportBo;
import com.liefeng.property.vo.workbench.EventReportVo;

/**
 * 报事查询
 * @author wuzhijing
 */
public interface EventReportQueryRepository extends BaseRepository<EventReportVo>{
	
	/**
	 * 待办理查询
	 * @param eventReportBo
	 * @return
	 */
	public List<EventReportVo> waitingForQueryByPage (PagingParamVo param);
	
	/**
	 * 待办理总数
	 * @param eventReportBo
	 * @return
	 */
	public Long waitingForQueryByCount (PagingParamVo param);
}
