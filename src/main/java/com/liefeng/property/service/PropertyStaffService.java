package com.liefeng.property.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.base.vo.CustomerVo;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.base.ICheckService;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.IProjectService;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.intf.property.ISysSecurityService;
import com.liefeng.intf.property.ISysService;
import com.liefeng.intf.service.tcc.ITccMsgService;
import com.liefeng.mq.type.TccBasicEvent;
import com.liefeng.property.bo.property.PropertyStaffBo;
import com.liefeng.property.domain.staff.ManageProjectContext;
import com.liefeng.property.domain.staff.PropertyDepartmentContext;
import com.liefeng.property.domain.staff.PropertyStaffContext;
import com.liefeng.property.domain.staff.StaffArchiveContext;
import com.liefeng.property.domain.staff.StaffAttachContext;
import com.liefeng.property.domain.staff.StaffContactPrivilegeContext;
import com.liefeng.property.domain.staff.StaffMsgClientContext;
import com.liefeng.property.domain.sys.SysRoleContext;
import com.liefeng.property.error.StaffErrorCode;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.vo.household.UserClientIdVo;
import com.liefeng.property.vo.project.ProjectVo;
import com.liefeng.property.vo.staff.PropertyDepartmentVo;
import com.liefeng.property.vo.staff.PropertyStaffDetailInfoVo;
import com.liefeng.property.vo.staff.PropertyStaffListVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.property.vo.staff.StaffArchiveVo;
import com.liefeng.property.vo.staff.StaffAttachVo;
import com.liefeng.property.vo.staff.StaffContactPrivilegeVo;
import com.liefeng.property.vo.staff.StaffMsgClientVo;
import com.liefeng.property.vo.staff.StaffWorkFlowUseVo;
import com.liefeng.service.constant.PushActionConstants;

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
	
	@Autowired
	private ISysService sysService;

	@Autowired
	private PropertyPushMsgService propertyPushMsgService;

	@Override
	public DataPageValue<PropertyStaffListVo> listPropertyStaff4Page(PropertyStaffBo propertyStaffBo, int page, int size) {
		return PropertyStaffContext.build().listPropertyStaff4Page(propertyStaffBo, page, size);
	}

	@Transactional(rollbackOn=Exception.class)
	@Override
	public void createStaff(PropertyStaffDetailInfoVo propertyStaffDetailInfo) throws LiefengException{

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
		sysSecurityService.grantRoleUser(propertyStaffVo.getId(), propertyStaffDetailInfo.getRoleIds());
		
		//员工管理相关项目
		ManageProjectContext.build(propertyStaffVo.getId()).grantManageProject(propertyStaffDetailInfo.getManageProjects());
		
		//员工通讯录授权
		StaffContactPrivilegeContext.loadByStaffId(propertyStaffVo.getId()).grantPrivilege(propertyStaffDetailInfo.getContactProjects());
		
		//员工附件
		StaffAttachContext.loadByStaffId(propertyStaffVo.getId()).createAttachs(propertyStaffDetailInfo.getStaffAttachs());
		
		logger.info("createStaff sendTccMsg event = {} , content = {}", TccBasicEvent.CREATE_CUSTOMER, customerVo);
		
		//发送tcc消息创建客户
		tccMsgService.sendTccMsg(TccBasicEvent.CREATE_CUSTOMER, customerVo.toString());
		
		logger.info("createStaff sendTccMsg success");

	}

	@Transactional(rollbackOn=Exception.class)
	@Override
	public void updateStaff(PropertyStaffDetailInfoVo propertyStaffDetailInfo) throws LiefengException{
		
		logger.info("updateStaff PropertyStaffDetailInfoVo = {}", propertyStaffDetailInfo);
		
		CustomerVo customerVo = propertyStaffDetailInfo.getCustomerVo();
		
		if(ValidateHelper.isEmptyString(customerVo.getId())){
			customerVo = checkService.createCustomerCheck(propertyStaffDetailInfo.getCustomerVo());
		}else{
			customerVo = checkService.updateCustomerCheck(propertyStaffDetailInfo.getCustomerVo());
		}
		
		logger.info("updateStaff createCustomerCheck customerVo = {}", customerVo);
		
		PropertyStaffVo propertyStaffVo = propertyStaffDetailInfo.getPropertyStaffVo();
		
		//更新员工信息
		PropertyStaffContext.build(propertyStaffVo).update();
		
		logger.info("updateStaff propertyStaff update success");
		
		StaffArchiveVo staffArchiveVo = propertyStaffDetailInfo.getStaffArchiveVo();
				
		staffArchiveVo.setStaffId(propertyStaffVo.getId());
				
		staffArchiveVo.setCustGlobalId(customerVo.getGlobalId());
		
		if(ValidateHelper.isEmptyString(staffArchiveVo.getId())){
			//新建员工档案
			StaffArchiveContext.build(propertyStaffDetailInfo.getStaffArchiveVo()).create();
		}else{
			//更新员工档案
			StaffArchiveContext.build(propertyStaffDetailInfo.getStaffArchiveVo()).update();
		}
		
		//员工授权
		sysSecurityService.grantRoleUser(propertyStaffVo.getId(), propertyStaffDetailInfo.getRoleIds());
				
		//员工管理相关项目
		ManageProjectContext.build(propertyStaffVo.getId()).grantManageProject(propertyStaffDetailInfo.getManageProjects());
		
		//员工通讯录授权
		StaffContactPrivilegeContext.loadByStaffId(propertyStaffVo.getId()).grantPrivilege(propertyStaffDetailInfo.getContactProjects());
		
		//员工附件
		StaffAttachContext.loadByStaffId(propertyStaffVo.getId()).createAttachs(propertyStaffDetailInfo.getStaffAttachs());
		
		
		TccBasicEvent event = TccBasicEvent.UPDATE_CUSTOMER;
		
		if(ValidateHelper.isEmptyString(customerVo.getId())){
			event = TccBasicEvent.CREATE_CUSTOMER;
		}
		
		logger.info("updateStaff sendTccMsg event = {} , content = {}", event, customerVo);
		
		//发送tcc消息
		tccMsgService.sendTccMsg(event, customerVo.toString());
		
		logger.info("updateStaff sendTccMsg success");
	}
	
	@Override
	public List<PropertyStaffVo> findPropertyStaff(String departmentId, String projectId){
		return PropertyStaffContext.build().listPropertyStaffByDeptIdAndProjectId(departmentId, projectId);
	}

	@Override
	public PropertyStaffVo findPropertyStaffById(String staffId) {
		PropertyStaffVo propertyStaffVo = PropertyStaffContext.loadById(staffId).get();
		if(propertyStaffVo != null){
			
			propertyStaffVo.setDepartmentName(PropertyDepartmentContext.loadById(propertyStaffVo.getDepartmentId()).getName());
			
			propertyStaffVo.setPositionName(sysService.getDictNameByValue("POSITION", propertyStaffVo.getPosition()));
			
		}
		return propertyStaffVo;
	}

	@Override
	public PropertyStaffDetailInfoVo findStaffDetailInfo(String staffId) {
		PropertyStaffDetailInfoVo propertyStaffDetailInfo = new PropertyStaffDetailInfoVo();
		
		//查找员工信息
		PropertyStaffVo propertyStaffVo = PropertyStaffContext.loadById(staffId).get();
		
		//查找员工档案信息
		StaffArchiveVo staffArchiveVo = StaffArchiveContext.loadByStaffId(staffId).getStaffArchive();
		
		CustomerVo customerVo = null;
		if(staffArchiveVo != null){
			//查找员工客户信息
			customerVo = userService.getCustomerByGlobalId(staffArchiveVo.getCustGlobalId());
		}
		
		//员工项目列表
		List<String> projectIdList = projectService.findProjectIdByStaffId(staffId);
		
		//员工角色列表
		List<Long> roleIdList = SysRoleContext.build().findRoleIdsByUserId(staffId);
		
		//通讯录权限
		List<String> contactPrivilegeDeptIds = StaffContactPrivilegeContext.loadByStaffId(staffId).findContactPrivilegeToDeptIds();
		
		//员工附件
		List<StaffAttachVo> staffAttachList = StaffAttachContext.loadByStaffId(staffId).getStaffAttachs();
		
		propertyStaffDetailInfo.setPropertyStaffVo(propertyStaffVo);
		
		propertyStaffDetailInfo.setStaffArchiveVo(staffArchiveVo);
		
		propertyStaffDetailInfo.setCustomerVo(customerVo);
		
		propertyStaffDetailInfo.setManageProjects(projectIdList.toArray(new String[projectIdList.size()]));
		
		propertyStaffDetailInfo.setRoleIds(roleIdList.toArray(new Long[roleIdList.size()]));
		
		propertyStaffDetailInfo.setContactProjects(contactPrivilegeDeptIds.toArray(new String[contactPrivilegeDeptIds.size()]));
		
		propertyStaffDetailInfo.setStaffAttachs(staffAttachList);
		
		return propertyStaffDetailInfo;
	}
	
	@Override
	public PropertyStaffVo findPropertyStaffByAccount(String account) {
		return PropertyStaffContext.loadByAccount(account).get();
	}
	
	/*********************** 部门相关接口 **********************/
	
	@Override
	public void createDepartment(PropertyDepartmentVo department) throws LiefengException{
		PropertyDepartmentContext departContext = PropertyDepartmentContext.build(department);
		departContext.create();
	}

	@Override
	public void updateDepartment(PropertyDepartmentVo department) throws LiefengException{
		PropertyDepartmentContext departContext = PropertyDepartmentContext.build(department);
		departContext.update();
	}

	@Override
	public void deleteDepartment(String departmentId) throws LiefengException {
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
		return staffContext.get();
	}

	@Override
	public PropertyDepartmentVo getDepartment(String departmentId) {
		return PropertyDepartmentContext.loadById(departmentId).get();
	}

	@Override
	public void updateStaffStatus(List<String> staffIdList, String status) throws LiefengException {
		if(ValidateHelper.isNotEmptyCollection(staffIdList)){
			for (String staffId : staffIdList) {
				PropertyStaffContext.loadById(staffId).updateStaffStatus(status);
			}
		}
	}

	@Override
	public List<PropertyDepartmentVo> findStaffContactPrivilege(String staffId) {
		List<PropertyDepartmentVo> propertyDepartmentList = null;
		if(ValidateHelper.isNotEmptyString(staffId)){
			List<StaffContactPrivilegeVo> staffContactPrivilegeList = StaffContactPrivilegeContext.loadByStaffId(staffId).findContactPrivilege();
			if(ValidateHelper.isNotEmptyCollection(staffContactPrivilegeList)){
				propertyDepartmentList = new ArrayList<PropertyDepartmentVo>();
				for (StaffContactPrivilegeVo staffContactPrivilegeVo : staffContactPrivilegeList) {
					PropertyDepartmentVo propertyDepartmentVo = PropertyDepartmentContext.loadById(staffContactPrivilegeVo.getDepartmentId()).get();
					propertyDepartmentList.add(propertyDepartmentVo);
				}
			}
		}
		return propertyDepartmentList;
	}

	@Override
	public List<PropertyStaffVo> findPropertyStaffByRoleId(Long roleId) {
		return PropertyStaffContext.build().findByRoleId(roleId);
	}

	@Override
	public List<PropertyDepartmentVo> getDepartments(List<String> departmentIds) {
		logger.info("getDepartments departmentIds = {}", departmentIds);
		return PropertyDepartmentContext.build().findDepartments(departmentIds);
	}

	@Override
	public DataPageValue<PropertyDepartmentVo> listDepartment4Page(Integer page, Integer size) {
		return PropertyDepartmentContext.build().getDepartments4Page(page, size);
	}

	@Override
	public List<PropertyStaffVo> findPropertyStaff(String departmentId) {
		return PropertyStaffContext.build().findByDepartmentId(departmentId);
	}

	@Override
	public PropertyStaffVo findPropertyStaffById4DP(String staffId) {
		logger.info("select PropertyStaff by id is {}",staffId);
		return PropertyStaffContext.loadById(staffId).findPropertyStaffById4DP();
	}
	
	@Override
	public List<PropertyStaffVo> findPropertyStaffById4DPList(String staffIds) {
		logger.info("select PropertyStaff by id is {}",staffIds);
		return PropertyStaffContext.build().findPropertyStaffById4DPList(staffIds);
	}
	
	@Override
	public void updateStaffPassword(String staffId, String oldPassword, String newPassword) {
		PropertyStaffContext.loadById(staffId).updataPassword(oldPassword, newPassword);
	}

	@Override
	public StaffArchiveVo findStaffArchByStaffId(String staffId) {
		return StaffArchiveContext.loadByStaffId(staffId).getStaffArchive();
	}

	@Override
	public String findStaffMsgClientId(String staffId) {
		StaffMsgClientVo staffMsgClient = StaffMsgClientContext.loadByStaffId(staffId).get();
		if(staffMsgClient != null){
			return staffMsgClient.getClientId();
		}
		return null;
	}

	@Override
	public void settIngStaffMsgClientId(String staffId, String clientId, String oemCode) {
		//通过clientID查询此手机是否登录过
		StaffMsgClientVo staffMsgClientByCli = StaffMsgClientContext.loadByClientId(clientId).get();
		
		logger.info("settIngStaffMsgClientId staffMsgClientByCli = {}", staffMsgClientByCli);
		
		if(staffMsgClientByCli == null){
			//没有登陆过
			//查询此用户是否在别的手机登陆过
			StaffMsgClientVo staffMsgClientByUsr = StaffMsgClientContext.loadByStaffId(staffId).get();
			
			logger.info("settIngStaffMsgClientId staffMsgClientByUsr = {}", staffMsgClientByUsr);
			
			//没有在别的手机登陆
			if(staffMsgClientByUsr == null){
				//插入新记录
				staffMsgClientByUsr = new StaffMsgClientVo(staffId, clientId, oemCode);
				StaffMsgClientContext.build(staffMsgClientByUsr).create();
				
				logger.info("settIngStaffMsgClientId staffMsgClientByUsr create = {}", staffMsgClientByUsr);
			}else{
				
				//在别的手机登陆过，更新旧记录clientID
				StaffMsgClientContext.build(staffMsgClientByUsr).updateClientId(clientId);
				
				logger.info("settIngStaffMsgClientId staffMsgClientByUsr update = {}", staffMsgClientByUsr);
			}
			
		}else{
			//登录用户staffId
			String newStaffId = staffId;
			//旧推送记录staffId
			String oldStaffId = staffMsgClientByCli.getStaffId();
			
			logger.info("settIngStaffMsgClientId newStaffId = {}, oldStaffId = {}", newStaffId, oldStaffId);
			
			if(ValidateHelper.isNotEmptyString(oldStaffId) && !oldStaffId.equals(newStaffId)){
				//清除自己在别的手机的登陆记录
				StaffMsgClientContext.loadByStaffId(newStaffId).delete();
				//更新登陆手机的staffId
				StaffMsgClientContext.build(staffMsgClientByCli).updateStaffId(newStaffId);
				logger.info("settIngStaffMsgClientId staffMsgClientByCli update staffId = {}", newStaffId);
			}
		}

	}
	
	@Override
	public List<PropertyStaffVo> getDepartmentDirectorList(String projectId,String departmentId){
		return PropertyStaffContext.build().getDepartmentDirectorList(projectId,departmentId);
	}

	@Override
	public List<String> findStaffMsgClientIds(List<String> staffIds) {
		return StaffMsgClientContext.build().findStaffMsgClients(staffIds);
	}

	@Override
	public List<PropertyStaffVo> findPropertyStaffByPosition(String positionId) {
		return PropertyStaffContext.build().findPropertyStaffByPosition(positionId);
	}

	@Override
	public List<PropertyStaffVo> findStaffByMenuCode(String menuCode) {
		return PropertyStaffContext.build().findStaffByMenuCode(menuCode);
	}

	@Override
	public List<UserClientIdVo> findStaffClientIdList(String departmentId,
			String projectId) {
		return PropertyStaffContext.build().listClientIdByDeptIdAndProjectId(departmentId, projectId);
	}

	@Override
	public StaffArchiveVo findStaffArchiveByPhone(String phone) {
		return StaffArchiveContext.build().getStaffArchiveByPhone(phone);
	}
	
	@Override
	public void pushMsgToStaffByPhone(String phone) {
		
		StaffArchiveVo staffArchiveVo = findStaffArchiveByPhone(phone);
		
		if(staffArchiveVo != null){
			//修改密码时单推消息
			propertyPushMsgService.pushMsgToStaff(PushActionConstants.CHANGE_PWD_SUCCESS, staffArchiveVo.getStaffId());
		}
			
	}

	@Override
	public void pushMsgToStaffByStaffId(String staffId) {
		//修改密码时单推消息
		propertyPushMsgService.pushMsgToStaff(PushActionConstants.CHANGE_PWD_SUCCESS, staffId);
	}

	@Override
	public List<PropertyStaffVo> listPropertyStaffByDepartmentId(
			String departmentId) {
		return PropertyStaffContext.build().listPropertyStaffByDepartmentId(departmentId);
	}
	
	@Override
	public StaffWorkFlowUseVo getStaffWorkFlowUseVo(String staffId){
		StaffWorkFlowUseVo staffWorkFlowUseVo = PropertyStaffContext.build().getStaffWorkFlowUseVo(staffId);
		List<ProjectVo> projectVos = projectService.findProjectByStaffId(staffId);
		String projectName = "";
		for (ProjectVo projectVo : projectVos) {
			projectName+=projectVo.getFullName()+" ";
		}
		staffWorkFlowUseVo.setProjectName(projectName);
		if(staffWorkFlowUseVo.getSex() != null)
		staffWorkFlowUseVo.setSex(staffWorkFlowUseVo.getSex().equals("1")?"男":"女");
		else
		staffWorkFlowUseVo.setSex("未知");
		return staffWorkFlowUseVo;
	}
	
	@Override
	public List<PropertyDepartmentVo> getDepartments(String project) {
		PropertyDepartmentContext departContext = PropertyDepartmentContext.build();
		return departContext.getDepartments(project);
	}

	@Override
	public List<PropertyDepartmentVo> getAllDepartmentsByProjectId(
			String projectId) {
		return PropertyDepartmentContext.build().findAllDepartmentByProjectId(projectId);
	}

	@Override
	public List<PropertyStaffVo> getStaffByDepartmentId(String departmentId) {
		return PropertyStaffContext.build().getStaffByDepartmentId(departmentId);
	}

	@Override
	public PropertyDepartmentVo findDepartmentByDeptType(String projectId,
			String deptType) {
		return PropertyDepartmentContext.build().findByDeptType(projectId, deptType);
	}
	
	@Override
	public List<PropertyDepartmentVo> getDeptAndChildDept(String departmentId,String projectId) {
		List<PropertyDepartmentVo> departmentVos = new ArrayList<PropertyDepartmentVo>();
		
		PropertyDepartmentVo propertyDepartmentVo = PropertyDepartmentContext.loadById(departmentId).get();
		if(propertyDepartmentVo != null) {
			departmentVos.add(propertyDepartmentVo);
			//是否是父部门  父id为 0
			if(propertyDepartmentVo.getParentId().equals("0")){ 
				List<PropertyDepartmentVo> childDept = PropertyDepartmentContext.build().findByParentIdAndProjectId(propertyDepartmentVo.getId(),projectId);
				if(childDept != null && childDept.size() > 0)
					departmentVos.addAll(childDept);
			}else{ 
				PropertyDepartmentVo departmentVo = PropertyDepartmentContext.loadById(propertyDepartmentVo.getParentId()).get();
				if(departmentVo != null)
					departmentVos.add(departmentVo);
			}
		}
		return departmentVos;
	}

	@Override
	public UserClientIdVo findClientIdByAccount(String account) {
		return PropertyStaffContext.build().findClientIdByAccount(account);
	}
}
