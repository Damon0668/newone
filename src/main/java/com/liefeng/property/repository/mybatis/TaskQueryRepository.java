package com.liefeng.property.repository.mybatis;

import java.util.List;
import java.util.Map;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.workbench.TaskVo;

/**
 * 
 * @author xhw
 * @date 2016年2月23日下午7:46:32
 */
public interface TaskQueryRepository extends BaseRepository<TaskVo>{
	
	/**
	 * 查询我的任务列表（分页）
	 * @param paramMap {creatorId：创建者ID；staffId：接收人ID；status：状态}
	 * @return                      
	 * @author xhw
	 * @date 2016年2月23日 下午7:49:58
	 */
	public List<TaskVo> queryByPage(Map<String, String> paramMap);
	
	/**
	 * 查询总数
	 * @param paramMap {creatorId：创建者ID；staffId：接收人ID；status：状态}
	 * @return                      
	 * @author xhw
	 * @date 2016年2月24日 上午9:26:59
	 */
	public Long queryByCount(Map<String, String> paramMap);
}
