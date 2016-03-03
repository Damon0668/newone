package com.liefeng.property.repository.mybatis;

import java.util.List;
import java.util.Map;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.workbench.WebsiteMsgVo;

/**
 * 站内消息
 * @author xhw
 * @date 2016年2月23日下午7:46:32
 */
public interface WebsiteMsgQueryRepository extends BaseRepository<WebsiteMsgVo>{
	
	/**
	 * 查询消息（分页）
	 * @param paramMap {staffId：员工id，type：消息类型，manageProject：管理的项目, deptId：部门id}
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午7:50:14
	 */
	public List<WebsiteMsgVo> queryByPage(Map<String, String> paramMap);
	
	/**
	 * 查询消息总数
	 * @param paramMap {staffId：员工id，type：消息类型，manageProject：管理的项目，deptId：部门id}
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午7:50:14
	 */
	public Long queryByCount(Map<String, String> paramMap);
	
	
}
