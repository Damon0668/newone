package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.household.ResidentCarVo;

/**
 * 通讯录好友查询
 * @author wuzhijing
 * @date 2016年3月22日16:56:17
 */
public interface ResidentCarQueryRepository extends BaseRepository<ResidentCarVo> {
	
	/**
	 * 
	 * @param pakingId
	 * @return
	 */
	public List<ResidentCarVo> findResidentCarByPakingId(String pakingId);
	
	
}
