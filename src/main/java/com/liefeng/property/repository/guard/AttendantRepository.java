package com.liefeng.property.repository.guard;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.guard.AttendantPo;

/**
 * 服务人员仓储层
 * @author xhw
 * @date 2016年4月18日 下午5:46:02
 */
@Transactional
public interface AttendantRepository extends JpaRepository<AttendantPo, String> {

}
