package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.workbench.EventProcessVo;

/**
 * 工单处理查询
 * @author wuzhijing
 */
public interface EventProcessQueryRepository extends BaseRepository<EventProcessVo>{
	
	public List<EventProcessVo> getHisEventProcess(String orderId);

	public EventProcessVo getActiveEventProcess(PagingParamVo param);
	
	/**
	 * 获取某个已经完成的任务流程
	 * @param eventId
	 * @param taskName
	 * @return 
	 * @author xhw
	 * @date 2016年3月18日 下午5:57:44
	 */
	public EventProcessVo findEventProcess(PagingParamVo param);
}
