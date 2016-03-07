package com.liefeng.property.repository.guard;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.guard.GuardCardPo;

/**
 * 磁卡仓储层
 * @author Huangama
 * @date 2016-2-25
 */
@Transactional
public interface GuardCardRepository extends JpaRepository<GuardCardPo, String> {
	
	/**
	 * 根据SN号查询
	 * @param sn
	 * @return
	 */
	public GuardCardPo findBySnAndOemCode(String sn, String oemCode);
}
