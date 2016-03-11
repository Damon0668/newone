package com.liefeng.property.repository.guard;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.guard.GuardCardApplyPo;

/**
 * 磁卡业务申请仓储层
 * @author Huangama
 * @date 2016-2-25
 */
@Transactional
public interface GuardCardApplyRepository extends JpaRepository<GuardCardApplyPo, String> {

}
