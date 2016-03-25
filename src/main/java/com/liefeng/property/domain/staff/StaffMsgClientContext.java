package com.liefeng.property.domain.staff;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.staff.StaffMsgClientPo;
import com.liefeng.property.repository.staff.StaffMsgClientRepository;
import com.liefeng.property.vo.staff.StaffMsgClientVo;

/**
 * 员工消息推送客户端信息领域
 * @author 蔡少东
 * @date 2016年3月25日
 */
@Service
@Scope("prototype")
public class StaffMsgClientContext {
	
	@Autowired
	private StaffMsgClientRepository staffMsgClientRepository;
	
	/**
	 * 员工ID
	 */
	private String staffId;
	
	/**
	 * 个推ID
	 */
	private String clientId;
	
	/**
	 * 推送
	 */
	private StaffMsgClientVo staffMsgClient;

	protected void setStaffMsgClientRepository(StaffMsgClientRepository staffMsgClientRepository) {
		this.staffMsgClientRepository = staffMsgClientRepository;
	}

	protected void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	protected void setStaffMsgClientVo(StaffMsgClientVo staffMsgClient) {
		this.staffMsgClient = staffMsgClient;
	}
	
	private static StaffMsgClientContext getInstance() {
		return SpringBeanUtil.getBean(StaffMsgClientContext.class);
	}
	
	public static StaffMsgClientContext build() {
		StaffMsgClientContext staffMsgClientContext = getInstance();
		return staffMsgClientContext;
	}
	
	public static StaffMsgClientContext build(StaffMsgClientVo staffMsgClient) {
		StaffMsgClientContext staffMsgClientContext = getInstance();
		staffMsgClientContext.setStaffMsgClientVo(staffMsgClient);
		return staffMsgClientContext;
	}
	
	public static StaffMsgClientContext loadByStaffId(String staffId) {
		StaffMsgClientContext staffMsgClientContext = getInstance();
		staffMsgClientContext.setStaffId(staffId);
		return staffMsgClientContext;
	}
	
	public void create(){
		StaffMsgClientPo staffMsgClientPo = MyBeanUtil.createBean(staffMsgClient, StaffMsgClientPo.class);
		staffMsgClientPo.setId(UUIDGenerator.generate());
		staffMsgClientPo.setUpdateTime(new Date());
		staffMsgClientRepository.save(staffMsgClientPo);
	}
	
	public StaffMsgClientVo get(){
		if(ValidateHelper.isNotEmptyString(staffId)){
			StaffMsgClientPo staffMsgClientPo = staffMsgClientRepository.findByStaffId(staffId);
			staffMsgClient = MyBeanUtil.createBean(staffMsgClientPo, StaffMsgClientVo.class);
		}
		return staffMsgClient;
	}
}
