package com.liefeng.property.repository.mybatis;

import java.util.List;
import java.util.Map;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.workbench.ProprietorContactsVo;

/**
 * 业主通讯录
 * @author xhw
 * @2016年3月4日 下午3:48:01
 */
public interface ProprietorContactsQueryRepository extends BaseRepository<ProprietorContactsVo>{
	
	/**
	 * 查询业主通讯录（分页）
	 * @param paramMap {projectId：项目id，buildingId 楼栋id，status：状态}
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午7:50:14
	 */
	public List<ProprietorContactsVo> queryByPage(Map<String, String> paramMap);
	
	/**
	 * 查询业主通讯录总数
	 * @param paramMap {projectId：项目id，buildingId 楼栋id，status：状态}
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午7:50:14
	 */
	public Long queryByCount(Map<String, String> paramMap);
	
}
