package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.service.vo.TaskAttachVo;

/**
 * 工作流附件MyBatis仓储层
 * 
 * @author ZhenTingJun
 * @date 2016年4月9日
 */
public interface TaskAttachQueryRepository extends BaseRepository<TaskAttachVo> {
	
	/**
	 * 根据流程实例ID查询附件
	 * @param orderId 流程实例ID
	 * @return 附件列表
	 */
	List<TaskAttachVo> queryTaskAttachList(String orderId);
}
