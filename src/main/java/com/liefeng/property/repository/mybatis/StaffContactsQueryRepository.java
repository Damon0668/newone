package com.liefeng.property.repository.mybatis;

import java.util.List;
import java.util.Map;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.workbench.StaffContactsVo;

/**
 * 员工通讯录
 * @author xhw
 * @2016年3月4日 下午3:48:01
 */
public interface StaffContactsQueryRepository extends BaseRepository<StaffContactsVo>{
	
	/**
	 * 查询员工通讯录（分页）
	 * @param paramMap {departmentId：部门id，status：状态，workStatus：在职状态}
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午7:50:14
	 */
	public List<StaffContactsVo> queryByPage(Map<String, String> paramMap);
	
	/**
	 * 查询员工通讯录总数
	 * @param paramMap {departmentId：部门id，status：状态，workStatus：在职状态}
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午7:50:14
	 */
	public Long queryByCount(Map<String, String> paramMap);
	
}
