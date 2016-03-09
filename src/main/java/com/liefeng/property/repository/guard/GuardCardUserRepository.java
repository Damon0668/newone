package com.liefeng.property.repository.guard;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.guard.GuardCardUserPo;

/**
 * 磁卡用户仓储层
 * @author Huangama
 * @date 2016-2-29
 */
@Transactional
public interface GuardCardUserRepository extends JpaRepository<GuardCardUserPo, String> {
	
	/**
	 * 查询
	 * @param cardId 磁卡ID
	 * @return
	 */
	public GuardCardUserPo findByCardId(String cardId);
}
