package com.liefeng.property.repository.guard;

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

}
