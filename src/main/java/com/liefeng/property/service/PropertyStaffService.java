package com.liefeng.property.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.base.vo.CustomerVo;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.base.ICheckService;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.IProjectService;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.intf.property.ISysSecurityService;
import com.liefeng.intf.service.tcc.ITccMsgService;
import com.liefeng.mq.type.TccBasicEvent;
import com.liefeng.property.bo.property.PropertyStaffBo;
import com.liefeng.property.domain.staff.ManageProjectContext;
import com.liefeng.property.domain.staff.PropertyDepartmentContext;
import com.liefeng.property.domain.staff.PropertyStaffContext;
import com.liefeng.property.domain.staff.StaffArchiveContext;
import com.liefeng.property.domain.sys.SysRoleContext;
import com.liefeng.property.error.StaffErrorCode;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.vo.staff.PropertyDepartmentVo;
import com.liefeng.property.vo.staff.PropertyStaffDetailInfoVo;
import com.liefeng.property.vo.staff.PropertyStaffListVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.property.vo.staff.StaffArchiveVo;

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
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IProjectService projectService;
		
	@Override
	public DataPageValue<PropertyStaffListVo> listPropertyStaff4Page(PropertyStaffBo propertyStaffBo, int page, int size) {
		return PropertyStaffContext.build().listPropertyStaff4Page(propertyStaffBo, page, size);
	}

	@Transactional(rollbackOn=Exception.class)
	@Override
	public ReturnValue createStaff(PropertyStaffDetailInfoVo propertyStaffDetailInfo) throws Exception {
		
		logger.info("createStaff PropertyStaffDetailInfoVo = {}", propertyStaffDetailInfo);
		
		//客户创建校验
		CustomerVo customerVo = checkService.createCustomerCheck(propertyStaffDetailInfo.getCustomerVo());
		
		logger.info("createStaff createCustomerCheck customerVo = {}", customerVo);
		
		//创建员工
		PropertyStaffVo propertyStaffVo = PropertyStaffContext.build(propertyStaffDetailInfo.getPropertyStaffVo()).create();
		
		propertyStaffDetailInfo.getStaffArchiveVo().setStaffId(propertyStaffVo.getId());
		
		propertyStaffDetailInfo.getStaffArchiveVo().setCustGlobalId(customerVo.getGlobalId());
		
		//创建员工档案
		StaffArchiveContext.build(propertyStaffDetailInfo.getStaffArchiveVo()).create();
		
		logger.info("createStaff StaffArchive create success");

		//员工授权
		sysSecurityService.gruntRoleUser(propertyStaffVo.getId(), propertyStaffDetailInfo.getRoleIds());
		
		//员工管理相关项目
		ManageProjectContext.build(propertyStaffVo.getId()).create(propertyStaffDetailInfo.getManageProjects());
		
		logger.info("createStaff sendTccMsg event = {} , content = {}", TccBasicEvent.CREATE_CUSTOMER, customerVo);
		
		//发送tcc消息创建客户
		tccMsgService.sendTccMsg(TccBasicEvent.CREATE_CUSTOMER, customerVo.toString());
		
		logger.info("createStaff sendTccMsg success");
		
		return ReturnValue.success();
	}

	@Transactional(rollbackOn=Exception.class)
	@Override
	public ReturnValue updateStaff(PropertyStaffDetailInfoVo propertyStaffDetailInfo) throws Exception {
		
		logger.info("updateStaff PropertyStaffDetailInfoVo = {}", propertyStaffDetailInfo);
		
		CustomerVo customerVo = checkService.updateCustomerCheck(propertyStaffDetailInfo.getCustomerVo());
		
		logger.info("updateStaff createCustomerCheck customerVo = {}", customerVo);
		
		PropertyStaffVo propertyStaffVo = propertyStaffDetailInfo.getPropertyStaffVo();
		
		//更新员工信息
		PropertyStaffContext.build(propertyStaffVo).update();
		
		logger.info("updateStaff propertyStaff update success");
				
		propertyStaffDetailInfo.getStaffArchiveVo().setStaffId(propertyStaffVo.getId());
				
		propertyStaffDetailInfo.getStaffArchiveVo().setCustGlobalId(customerVo.getGlobalId());
				
		//更新员工档案
		StaffArchiveContext.build(propertyStaffDetailInfo.getStaffArchiveVo()).update();
				
		//员工授权
		sysSecurityService.gruntRoleUser(propertyStaffVo.getId(), propertyStaffDetailInfo.getRoleIds());
				
		//员工管理相关项目
		ManageProjectContext.build(propertyStaffVo.getId()).create(propertyStaffDetailInfo.getManageProjects());
				
		logger.info("updateStaff sendTccMsg event = {} , content = {}", TccBasicEvent.UPDATE_CUSTOMER, customerVo);
		
		//发送tcc消息
		tccMsgService.sendTccMsg(TccBasicEvent.UPDATE_CUSTOMER, customerVo.toString());
		
		logger.info("updateStaff sendTccMsg success");
				
		return ReturnValue.success();
	}
	
	@Override
	public List<PropertyStaffVo> findPropertyStaff(String departmentId, String projectId) throws LiefengException {
		return PropertyStaffContext.build().listPropertyStaffByDeptIdAndProjectId(departmentId, projectId);
	}

	@Override
	public PropertyStaffVo findPropertyStaffById(String staffId) {
		PropertyStaffVo propertyStaffVo = PropertyStaffContext.loadById(staffId).getPropertyStaff();
		return propertyStaffVo;
	}

	@Override
	public PropertyStaffDetailInfoVo findStaffDetailInfo(String staffId) {
		PropertyStaffDetailInfoVo propertyStaffDetailInfo = new PropertyStaffDetailInfoVo();
		
		//查找员工信息
		PropertyStaffVo propertyStaffVo = PropertyStaffContext.loadById(staffId).getPropertyStaff();
		
		//查找员工档案信息
		StaffArchiveVo staffArchiveVo = StaffArchiveContext.loadByStaffId(propertyStaffVo.getId()).getStaffArchive();
		
		//查找员工客户信息
		CustomerVo customerVo = userService.getCustomerByGlobalId(staffArchiveVo.getCustGlobalId());
		
		//员工项目列表
		List<String> projectIdList = projectService.findProjectIdByStaffId(propertyStaffVo.getId());
		
		//员工角色列表
		List<Long> roleIdList = SysRoleContext.build().findRoleIdsByUserId(propertyStaffVo.getId());
		
		propertyStaffDetailInfo.setPropertyStaffVo(propertyStaffVo);
		
		propertyStaffDetailInfo.setStaffArchiveVo(staffArchiveVo);
		
		propertyStaffDetailInfo.setCustomerVo(customerVo);
		
		propertyStaffDetailInfo.setManageProjects(projectIdList.toArray(new String[projectIdList.size()]));
		
		propertyStaffDetailInfo.setRoleIds(roleIdList.toArray(new Long[roleIdList.size()]));
		
		return propertyStaffDetailInfo;
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

	@Override
	public PropertyDepartmentVo getDepartment(String departmentId) {
		return PropertyDepartmentContext.loadById(departmentId).get();
	}

}
