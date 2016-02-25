package com.liefeng.property.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.base.ICheckService;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.intf.property.ISysSecurityService;
import com.liefeng.intf.service.tcc.ITccMsgService;
import com.liefeng.property.bo.property.PropertyStaffBo;
import com.liefeng.property.domain.staff.ManageProjectContext;
import com.liefeng.property.domain.staff.PropertyDepartmentContext;
import com.liefeng.property.domain.staff.PropertyStaffContext;
import com.liefeng.property.domain.staff.StaffArchiveContext;
import com.liefeng.property.error.StaffErrorCode;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.vo.staff.PropertyDepartmentVo;
import com.liefeng.property.vo.staff.PropertyStaffDetailInfoVo;
import com.liefeng.property.vo.staff.PropertyStaffListVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;

/**
 * 物业员工服务
 * @author 蔡少东
 * @date 2016年2月22日
 */
@Service
public class PropertyStaffService implements IPropertyStaffService {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertyStaffService.class);
	
	@Autowired
	private ISysSecurityService sysSecurityService;
	
	@Autowired
	private ICheckService checkService;
	
	@Autowired
	private ITccMsgService tccMsgService;
	
	@Override
	public DataPageValue<PropertyStaffListVo> listPropertyStaff4Page(PropertyStaffBo propertyStaffBo, int page, int size) {
		return PropertyStaffContext.build().listPropertyStaff4Page(propertyStaffBo, page, size);
	}

	@Override
	public ReturnValue createStaff(PropertyStaffVo propertyStaff) throws LiefengException {
		PropertyStaffContext.build(propertyStaff).create();
		return ReturnValue.success();
	}
	@Transactional(rollbackOn=Exception.class)
	@Override
	public ReturnValue createStaff(PropertyStaffDetailInfoVo propertyStaffDetailInfo) throws Exception {
		
		logger.info("createStaff PropertyStaffDetailInfoVo = {}", propertyStaffDetailInfo);
		//CustomerVo customerVo = checkService.createCustomerCheck(propertyStaffDetailInfo.getCustomerVo());
		
		//创建物业员
		PropertyStaffVo propertyStaffVo = PropertyStaffContext.build(propertyStaffDetailInfo.getPropertyStaffVo()).create();
		
		propertyStaffDetailInfo.getStaffArchiveVo().setStaffId(propertyStaffVo.getId());
		
		propertyStaffDetailInfo.getStaffArchiveVo().setCustGlobalId(UUIDGenerator.generate());
		
		//propertyStaffDetailInfo.getStaffArchiveVo().setCustGlobalId(customerVo.getGlobalId());
		
		//创建员工档案
		StaffArchiveContext.build(propertyStaffDetailInfo.getStaffArchiveVo()).create();
		
		//员工授权
		sysSecurityService.gruntRoleUser(propertyStaffVo.getId(), propertyStaffDetailInfo.getRoleIds());
		
		//员工管理相关项目
		ManageProjectContext.build(propertyStaffVo.getId()).create(propertyStaffDetailInfo.getManageProjects());
		
		//tccMsgService.sendTccMsg(TccBasicEvent.CREATE_CUSTOMER, customerVo.toString());
		
		return ReturnValue.success();
	}

	@Override
	public ReturnValue updateStaff(PropertyStaffVo propertyStaff) throws LiefengException {
		PropertyStaffContext.build(propertyStaff).update();
		return ReturnValue.success();
	}
	
	@Override
	public List<PropertyStaffVo> findPropertyStaff(String departmentId, String projectId) throws LiefengException {
		return PropertyStaffContext.build().listPropertyStaffByDeptIdAndProjectId(departmentId, projectId);
	}


	@Override
	public PropertyStaffVo findPropertyStaffById(String staffId) {
		PropertyStaffVo propertyStaffVo = PropertyStaffContext.loadById(staffId).getPropertyStaff();
		propertyStaffVo.setPropertyDepartment(PropertyDepartmentContext.loadById(propertyStaffVo.getDepartmentId()).get());
		return propertyStaffVo;
	}

	@Override
	public PropertyStaffVo findPropertyStaffByAccount(String account) {
		return PropertyStaffContext.loadByAccount(account).getPropertyStaff();
	}
	
	/*********************** 部门相关接口 **********************/
	
	@Override
	public void createDepartment(PropertyDepartmentVo department) {
		PropertyDepartmentContext departContext = PropertyDepartmentContext.build(department);
		departContext.create();
	}

	@Override
	public void updateDepartment(PropertyDepartmentVo department) {
		PropertyDepartmentContext departContext = PropertyDepartmentContext.build(department);
		departContext.update();
	}

	@Override
	public void deleteDepartment(String departmentId) {
		PropertyDepartmentContext departContext = PropertyDepartmentContext.loadById(departmentId);
		departContext.delete();
	}
	
	@Override
	public List<PropertyDepartmentVo> getDepartments() {
		PropertyDepartmentContext departContext = PropertyDepartmentContext.build();
		return departContext.getDepartments();
	}

	@Override
	public PropertyStaffVo getDepartmentDirector(String departmentId) {
		PropertyDepartmentContext departContext = PropertyDepartmentContext.loadById(departmentId);
		PropertyDepartmentVo departmentVo = departContext.get();
		if (departmentVo == null) {
			logger.error("Not found department of id '{}'.", departmentId);
			throw new PropertyException(StaffErrorCode.DEPARTMENT_NOT_EXIST);
		}
		
		String directorId = departmentVo.getDirectorId();
		PropertyStaffContext staffContext = PropertyStaffContext.loadById(directorId);
		return staffContext.getPropertyStaff();
	}
}
