package com.liefeng.property.repository.guard;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.guard.GuardOpenLogPo;

/**
 * 开门日志仓储层
 * @author Huangama
 * @date 2016-2-29
 */
@Transactional
public interface GuardOpenLogRepository extends JpaRepository<GuardOpenLogPo, String> {

}
