package com.liefeng.property.repository.guard;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.guard.GuardCardPrivilegePo;

/**
 * 磁卡权限仓储层
 * @author Huangama
 * @date 2016-2-25
 */
@Transactional
public interface GuardCardPrivilegeRepository extends JpaRepository<GuardCardPrivilegePo, String> {
	
	/**
	 * 查询权限
	 * @param cardId 磁卡ID
	 * @return
	 */
	public List<GuardCardPrivilegePo> findByCardId(String cardId);
	
	/**
	 * 删除
	 * @param cardId 磁卡ID
	 */
	public void deleteByCardId(String cardId);
}
