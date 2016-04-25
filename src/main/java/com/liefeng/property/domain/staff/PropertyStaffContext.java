package com.liefeng.property.domain.staff;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.property.PropertyStaffBo;
import com.liefeng.property.constant.StaffConstants;
import com.liefeng.property.error.PropertyStaffErrorCode;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.po.staff.PropertyStaffPo;
import com.liefeng.property.repository.mybatis.PropertyStaffQueryRepository;
import com.liefeng.property.repository.staff.PropertyStaffRepository;
import com.liefeng.property.vo.household.UserClientIdVo;
import com.liefeng.property.vo.staff.PropertyDepartmentVo;
import com.liefeng.property.vo.staff.PropertyStaffListVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.property.vo.staff.StaffWorkFlowUseVo;

/**
 * 物业员工领域模型
 * 
 * @author ZhenTingJun
 * @author 蔡少东
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class PropertyStaffContext {
	
	private static Logger logger = LoggerFactory.getLogger(PropertyStaffContext.class);
	
	@Autowired
	private PropertyStaffRepository propertyStaffRepository;
	
	@Autowired
	private PropertyStaffQueryRepository propertyStaffQueryRepository;
	
	/**
	 * 物业员工ID
	 */
	private String propertyStaffId;
	
	/**
	 * 员工账号
	 */
	private String account;
	
	/**
	 * 物业员工值对象
	 */
	private PropertyStaffVo propertyStaff;
	
	protected void setPropertyStaffId(String propertyStaffId) {
		this.propertyStaffId = propertyStaffId;
	}

	protected void setPropertyStaff(PropertyStaffVo propertyStaff) {
		this.propertyStaff = propertyStaff;
	}
	
	
	protected void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	private static PropertyStaffContext getInstance() {
		return SpringBeanUtil.getBean(PropertyStaffContext.class);
	}
	
	/**
	 * 根据物业员工值对象构建上下文
	 * @return 物业员工上下文
	 */
	public static PropertyStaffContext build() {
		PropertyStaffContext propertyStaffContext = getInstance();
		return propertyStaffContext;
	}
	
	/**
	 * 根据物业员工值对象构建上下文
	 * @param propertyStaff 物业员工值对象
	 * @return 物业员工上下文
	 */
	public static PropertyStaffContext build(PropertyStaffVo propertyStaff) {
		PropertyStaffContext propertyStaffContext = getInstance();
		propertyStaffContext.setPropertyStaff(propertyStaff);
		
		return propertyStaffContext;
	}
	
	/**
	 * 根据物业员工ID加载上下文
	 * @param propertyStaffId 物业员工ID
	 * @return 物业员工上下文
	 */
	public static PropertyStaffContext loadById(String propertyStaffId) {
		PropertyStaffContext propertyStaffContext = getInstance();
		propertyStaffContext.setPropertyStaffId(propertyStaffId);
		return propertyStaffContext;
	}
	
	/**
	 * 根据物业员工登陆账号加载上下文
	 * @param propertyStaffId 物业员工ID
	 * @return 物业员工上下文
	 */
	public static PropertyStaffContext loadByAccount(String account) {
		PropertyStaffContext propertyStaffContext = getInstance();
		propertyStaffContext.setAccount(account);
		return propertyStaffContext;
	}
	
	/**
	 * 查询物业员工信息
	 * @return 物业员工值对象
	 */
	public PropertyStaffVo get() {
		if(propertyStaff == null) {
			PropertyStaffPo propertyStaffPo = null;
			
			if(ValidateHelper.isNotEmptyString(propertyStaffId)) {
				logger.info("【PropertyStaffContext.getPropertyStaff】Query property staff by id:{}", propertyStaffId);
				propertyStaffPo = propertyStaffRepository.findOne(propertyStaffId);
			}
			
			if(ValidateHelper.isEmptyString(propertyStaffId) && ValidateHelper.isNotEmptyString(account)){
				logger.info("【PropertyStaffContext.getPropertyStaff】Query property staff by account:{}", account);
				propertyStaffPo = propertyStaffRepository.findByAccount(account);
			}
			
			if(propertyStaffPo != null) {
				propertyStaff = MyBeanUtil.createBean(propertyStaffPo, PropertyStaffVo.class);
			}
		}
		
		logger.info("【PropertyStaffContext.getPropertyStaff】Query property staff, details:{}", propertyStaff);
		
		return propertyStaff;
	}
	
	/**
	 * 保存物业员工信息
	 */
	public PropertyStaffVo create() {
		if(propertyStaff != null) {
			
			//已存在的物业员工
			PropertyStaffPo existPropertyStaff = propertyStaffRepository.findByAccount(propertyStaff.getAccount());
			
			if(existPropertyStaff != null){
				throw new PropertyException(PropertyStaffErrorCode.STAFF_ALREADY_EXIST, PropertyStaffErrorCode.STAFF_ALREADY_EXIST.getDesc());
			}
			
			propertyStaff.setId(UUIDGenerator.generate());
			propertyStaff.setStatus(StaffConstants.StaffStatus.ACTIVE);
			propertyStaff.setOemCode(ContextManager.getInstance().getOemCode());
			propertyStaff.setCreateTime(new Date());
			
			PropertyStaffPo propertyStaffPo = MyBeanUtil.createBean(propertyStaff, PropertyStaffPo.class);
			propertyStaffRepository.save(propertyStaffPo);
		}
		return propertyStaff;
	}
	
	/**
	 * 更新物业员工信息
	 */
	public void update() {
		if(propertyStaff != null && ValidateHelper.isNotEmptyString(propertyStaff.getId())){
			PropertyStaffPo propertyStaffPo = propertyStaffRepository.findOne(propertyStaff.getId());
			if(propertyStaffPo != null){
				MyBeanUtil.copyBeanNotNull2Bean(propertyStaff, propertyStaffPo);
				propertyStaffRepository.save(propertyStaffPo);
			}
		}
	}
	
	/**
	 * 更新员工状态
	 */
	public void updateStaffStatus(String status) {
		
		logger.info("updateStaffStatus status = {}", status);
		
		PropertyStaffPo propertyStaffPo = propertyStaffRepository.findOne(propertyStaffId);
		
		if(StaffConstants.WorkStatus.IN_OFFICE.equals(status)){
			propertyStaffPo.setStatus(status);
		}
		if(StaffConstants.WorkStatus.LEAVE_OFFICE.equals(status)){
			propertyStaffPo.setStatus(status);
		}
		
		propertyStaffPo.setStatus(status);
		propertyStaffRepository.save(propertyStaffPo);
	}
	
	/**
	 * 更新员工部门
	 */
	public void updateStaffDept(String deptId) {
		if(ValidateHelper.isNotEmptyString(propertyStaffId)){
			PropertyStaffPo propertyStaffPo = propertyStaffRepository.findOne(propertyStaffId);
			
			if(propertyStaffPo != null){
				propertyStaffPo.setDepartmentId(deptId);
				
				propertyStaffRepository.save(propertyStaffPo);
			}
		}
	}
	
	/**
	 * 分页查询物业员工信息
	 * @param page 第几页
	 * @param size
	 * @return
	 */
	public DataPageValue<PropertyStaffListVo> listPropertyStaff4Page(PropertyStaffBo propertyStaffBo, Integer page, Integer size) {
		// 参数拷贝
		propertyStaffBo.setOemCode(ContextManager.getInstance().getOemCode());
		
		Map<String, String> extra = MyBeanUtil.bean2Map(propertyStaffBo);
		
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(page);
		param.setPageSize(size);
		
		
		Long count = propertyStaffQueryRepository.queryByCount(param);
		
		count = (count == null ? 0 : count);
		
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);

		List<PropertyStaffListVo> list = propertyStaffQueryRepository.queryByPage(param);

		DataPageValue<PropertyStaffListVo> returnPage = new DataPageValue<PropertyStaffListVo>(list, count, size, page);
		
		return returnPage;
	}

	/**
	 * 根据部门ID和项目ID
	 * 查询员工
	 * @param departmentId 部门ID
	 * @param projectId 项目ID
	 * @return
	 */
	public List<PropertyStaffVo> listPropertyStaffByDeptIdAndProjectId(String departmentId, String projectId){
		Map<String, String> extra = new HashMap<String, String>();
		extra.put("departmentId", departmentId);
		extra.put("projectId", projectId);
		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(extra);
		return propertyStaffQueryRepository.queryByDeptIdAndProjectId(pagingParamVo);
	}
	
	/**
	 * 根据角色查询员工
	 * @param roleId 角色ID
	 * @return
	 */
	public List<PropertyStaffVo> findByRoleId(Long roleId){
		Map<String, String> extra = new HashMap<String, String>();
		extra.put("roleId", String.valueOf(roleId));
		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(extra);
		return propertyStaffQueryRepository.queryByRoleId(pagingParamVo);
	}
	
	/**
	 * 根据部门ID查询员工 
	 * 员工必须是在职并且激活的
	 * 包含父部门员工
	 * @return
	 */
	public List<PropertyStaffVo> findByDepartmentId(String departmentId){
		String oemCode = ContextManager.getInstance().getOemCode();
		
		List<PropertyStaffPo> propertyStaffList = propertyStaffRepository.findByDepartmentIdAndOemCode(departmentId, oemCode);
		
		//父部门
		PropertyDepartmentVo praentDept = PropertyDepartmentContext.loadById(departmentId).findParentDept();
		
		if(praentDept != null){
			List<PropertyStaffPo> praentDeptStaffList = propertyStaffRepository.findByDepartmentIdAndOemCode(praentDept.getId(), oemCode);
			
			if(ValidateHelper.isNotEmptyCollection(praentDeptStaffList)){
				propertyStaffList.addAll(praentDeptStaffList);
			}
		}
		
		if(ValidateHelper.isNotEmptyCollection(propertyStaffList)){
			return MyBeanUtil.createList(propertyStaffList, PropertyStaffVo.class);
		}
		return new ArrayList<PropertyStaffVo>();
	}

	/**
	 * 更新密码
	 * @param oldPassword
	 * @param newPassword
	 */
	public void updataPassword(String oldPassword, String newPassword){
		if(ValidateHelper.isNotEmptyString(propertyStaffId)){
			PropertyStaffPo propertyStaffPo = propertyStaffRepository.findOne(propertyStaffId);
			
			if(propertyStaffPo == null){
				throw new PropertyException(PropertyStaffErrorCode.STAFF_ID_NOT_EXIST);
			}

			if(!propertyStaffPo.getPassword().equals(oldPassword)){
				throw new PropertyException(PropertyStaffErrorCode.OLD_PASSWORD_ERROR);
			}
			
			propertyStaffPo.setPassword(newPassword);
			propertyStaffRepository.save(propertyStaffPo);
		}
	}

	/**
	 * 员工信息，包含 部门名称，岗位名称
	 * @return
	 */
	public PropertyStaffVo findPropertyStaffById4DP() {
		return propertyStaffQueryRepository.findPropertyStaffById4DP(propertyStaffId);
	}
	
	/**
	 * 获取部门负责人信息列表
	 * @param projectId
	 */
	public List<PropertyStaffVo> getDepartmentDirectorList(String projectId,String departmentId) {
		Map<String, String> param = new  HashMap<String, String>();
		param.put("projectId", projectId);
		param.put("departmentId", departmentId);
		return propertyStaffQueryRepository.getDepartmentDirectorList(param);
	}

	/**
	 * 员工信息列表，包含 部门名称，岗位名称
	 * @return
	 */
	public List<PropertyStaffVo> findPropertyStaffById4DPList(String staffIds) {
		if(ValidateHelper.isNotEmptyString(staffIds))
		return propertyStaffQueryRepository.findPropertyStaffById4DPList(staffIds.split(","));
		
		return null;
	}

	/**
	 * 根据岗位id获取员工信息 包含 部门名称，岗位名称
	 * @return
	 */
	public List<PropertyStaffVo> findPropertyStaffByPosition(String positionId) {
		return propertyStaffQueryRepository.findPropertyStaffByPosition(positionId);
	}
	
	/**
	 * 查询物业员工
	 * 根据menu的code查询具有此权限的员工
	 * @param menuCode
	 * @return
	 */
	public List<PropertyStaffVo> findStaffByMenuCode(String menuCode){
		String oemCode = ContextManager.getInstance().getOemCode();
		return propertyStaffQueryRepository.findStaffByMenuCode(menuCode, oemCode);
	}
	
	/**
	 * 根据部门ID和项目ID
	 * 获取clientId
	 * @param departmentId 部门ID
	 * @param projectId 项目ID
	 * @return
	 */
	public List<UserClientIdVo> listClientIdByDeptIdAndProjectId(String departmentId, String projectId){
		Map<String, String> extra = new HashMap<String, String>();
		extra.put("departmentId", departmentId);
		extra.put("projectId", projectId);
		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(extra);
		return propertyStaffQueryRepository.queryByProjectIdAndDeptId(pagingParamVo);
	}
	
	/**
	 * 通过部门id，获取员工资料（id、name、number、phone、custGloalId、clientId）
	 * @param departmentId
	 * @return 
	 * @author xhw
	 * @date 2016年4月13日 下午1:57:28
	 */
	public List<PropertyStaffVo> listPropertyStaffByDepartmentId(String departmentId){
		String oemCode = ContextManager.getInstance().getOemCode();
		Map<String, String> extra = new HashMap<String, String>();
		extra.put("departmentId", departmentId);
		extra.put("oemCode", oemCode);
		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(extra);
		return propertyStaffQueryRepository.findPropertyStaffByDepartmentId(pagingParamVo);
	}
	
	/**
	 * 审批流程员工信息
	 * @param staffId 员工id
	 * @return
	 */
	public StaffWorkFlowUseVo getStaffWorkFlowUseVo(String staffId) {
		return propertyStaffQueryRepository.getStaffWorkFlowUseVo(staffId);
	}
	
}
