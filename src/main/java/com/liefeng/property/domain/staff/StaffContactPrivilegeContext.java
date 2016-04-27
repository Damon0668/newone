package com.liefeng.property.domain.staff;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.error.PropertyStaffErrorCode;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.po.staff.StaffContactPrivilegePo;
import com.liefeng.property.repository.staff.StaffContactPrivilegeRepository;
import com.liefeng.property.vo.staff.StaffContactPrivilegeVo;

/**
 * 员工通讯录权限
 * 领域模型
 * @author 蔡少东
 * @date 2016年2月27日
 */
@Service
@Scope("prototype")
public class StaffContactPrivilegeContext{
	
	@Autowired
	private StaffContactPrivilegeRepository staffContactPrivilegeRepository;
	/**
	 * 员工ID
	 */
	private String staffId;
	
	private StaffContactPrivilegeVo staffContactPrivilege;
	
	protected void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	protected void setStaffContactPrivilege(StaffContactPrivilegeVo staffContactPrivilege) {
		this.staffContactPrivilege = staffContactPrivilege;
	}

	private static StaffContactPrivilegeContext getInstance() {
		return SpringBeanUtil.getBean(StaffContactPrivilegeContext.class);
	}
	
	/**
	 * 构建上下文
	 * @return 上下文对象
	 */
	public static StaffContactPrivilegeContext build() {
		StaffContactPrivilegeContext staffContactPrivilegeContext = getInstance();
		return staffContactPrivilegeContext;
	}
	
	public static StaffContactPrivilegeContext build(StaffContactPrivilegeVo staffContactPrivilege) {
		StaffContactPrivilegeContext staffContactPrivilegeContext = getInstance();
		staffContactPrivilegeContext.setStaffContactPrivilege(staffContactPrivilege);
		return staffContactPrivilegeContext;
	}
	
	public static StaffContactPrivilegeContext loadByStaffId(String staffId) {
		StaffContactPrivilegeContext staffContactPrivilegeContext = getInstance();
		staffContactPrivilegeContext.setStaffId(staffId);
		return staffContactPrivilegeContext;
	}
	
	public void create(){
		StaffContactPrivilegePo staffContactPrivilegePo = MyBeanUtil.createBean(staffContactPrivilege, StaffContactPrivilegePo.class);
		staffContactPrivilegePo.setId(UUIDGenerator.generate());
		staffContactPrivilegeRepository.save(staffContactPrivilegePo);
	}
	
	/**
	 * 授权通讯录权限
	 * @param departmentId 部门ID
	 */
	public void grantPrivilege(String[] departmentIds){
		if(ValidateHelper.isEmptyString(staffId)){
			throw new PropertyException(PropertyStaffErrorCode.STAFF_ID_NOT_EXIST);
		}
		if(departmentIds != null){
			deleteAll();
			for (String departmentId : departmentIds) {
				StaffContactPrivilegePo staffContactPrivilegePo = new StaffContactPrivilegePo();
				staffContactPrivilegePo.setId(UUIDGenerator.generate());
				staffContactPrivilegePo.setStaffId(staffId);
				staffContactPrivilegePo.setDepartmentId(departmentId);
				staffContactPrivilegeRepository.save(staffContactPrivilegePo);
			}
		}
	}
	
	/**
	 * 删除所有权限
	 */
	public void deleteAll(){
		staffContactPrivilegeRepository.deleteByStaffId(staffId);
	}
	
	/**
	 * 根据部门ID 删除
	 * @param deptID
	 */
	public void deleteByDeptID(String deptID){
		staffContactPrivilegeRepository.deleteByDepartmentId(deptID);
	}
	/**
	 * 查找通讯录权限
	 * @return
	 */
	public List<StaffContactPrivilegeVo> findContactPrivilege(){
		if(ValidateHelper.isEmptyString(staffId)){
			throw new PropertyException(PropertyStaffErrorCode.STAFF_ID_NOT_EXIST);
		}
		List<StaffContactPrivilegePo> dataList = staffContactPrivilegeRepository.findByStaffId(staffId);
		return MyBeanUtil.createList(dataList, StaffContactPrivilegeVo.class);
	}
	
	/**
	 * 查找通讯录权限
	 * @return 具有权限的部门ID列表
	 */
	public List<String> findContactPrivilegeToDeptIds(){
		List<StaffContactPrivilegeVo> dataList = StaffContactPrivilegeContext.loadByStaffId(staffId).findContactPrivilege();
		List<String> deptIds = new ArrayList<String>();
		for (StaffContactPrivilegeVo staffContactPrivilegeVo : dataList) {
			deptIds.add(staffContactPrivilegeVo.getDepartmentId());
		}
		return deptIds;
	}
}
