package com.liefeng.property.repository.staff;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.staff.StaffMsgClientPo;

@Transactional
public interface StaffMsgClientRepository extends JpaRepository<StaffMsgClientPo, String>{
	
	public StaffMsgClientPo findByStaffId(String staffId);
	
	public List<StaffMsgClientPo> findByStaffIdIn(List<String> staffIds);
	
	public List<StaffMsgClientPo> findByClientId(String clientId);
}
