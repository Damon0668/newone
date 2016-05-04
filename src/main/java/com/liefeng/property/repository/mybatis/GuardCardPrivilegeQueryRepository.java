package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;

/**
 * 出入管理
 * 授权管理
 * 查询接口
 */
public interface GuardCardPrivilegeQueryRepository extends BaseRepository {
	
	/**
	 * 通过位置ID,查询已授权的cardID
	 * @param positionId 位置ID
	 * @return
	 */
	public List<String> queryCardIdByPositionId(String positionId);
}
