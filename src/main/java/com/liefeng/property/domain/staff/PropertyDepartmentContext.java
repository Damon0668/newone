package com.liefeng.property.domain.staff;

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
import com.liefeng.core.exception.LiefengException;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.error.StaffErrorCode;
import com.liefeng.property.po.staff.PropertyDepartmentPo;
import com.liefeng.property.repository.mybatis.PropertyDepartmentQueryRepository;
import com.liefeng.property.repository.staff.PropertyDepartmentRepository;
import com.liefeng.property.vo.staff.PropertyDepartmentVo;

/**
 * 部门信息领域模型
 * @author ZhenTingJun
 * @author Huangama
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class PropertyDepartmentContext {
	
	private static Logger logger = LoggerFactory.getLogger(PropertyDepartmentContext.class);
	
	@Autowired
	private PropertyDepartmentRepository propertyDepartmentRepository;
	
	@Autowired
	private PropertyDepartmentQueryRepository propertyDepartmentQueryRepository;
	
	/**
	 * 部门信息ID
	 */
	private String propertyDepartmentId;
	
	/**
	 * 部门信息值对象
	 */
	private PropertyDepartmentVo propertyDepartment;
	
	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	private static PropertyDepartmentContext getInstance() {
		return SpringBeanUtil.getBean(PropertyDepartmentContext.class);
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 部门信息上下文
	 */
	public static PropertyDepartmentContext build() {
		PropertyDepartmentContext propertyDepartmentContext = getInstance();
		return propertyDepartmentContext;
	}
	
	/**
	 * 根据部门信息值对象构建上下文
	 * @param propertyDepartment 部门信息值对象
	 * @return 部门信息上下文
	 */
	public static PropertyDepartmentContext build(PropertyDepartmentVo propertyDepartment) {
		PropertyDepartmentContext propertyDepartmentContext = getInstance();
		propertyDepartmentContext.setPropertyDepartment(propertyDepartment);
		return propertyDepartmentContext;
	}
	
	/**
	 * 根据部门信息ID加载上下文
	 * @param propertyDepartmentId 部门信息ID
	 * @return 部门信息上下文
	 */
	public static PropertyDepartmentContext loadById(String propertyDepartmentId) {
		PropertyDepartmentContext propertyDepartmentContext = getInstance();
		propertyDepartmentContext.setPropertyDepartmentId(propertyDepartmentId);
		return propertyDepartmentContext;
	}
	
	/**
	 * 查询部门信息
	 * @return 部门信息值对象
	 */
	public PropertyDepartmentVo get() {
		if(propertyDepartment == null) {
			PropertyDepartmentPo propertyDepartmentPo = null;
			if(ValidateHelper.isNotEmptyString(propertyDepartmentId)) {
				propertyDepartmentPo = propertyDepartmentRepository.findOne(propertyDepartmentId);
			}
			
			if(propertyDepartmentPo != null) {
				propertyDepartment = MyBeanUtil.createBean(propertyDepartmentPo, PropertyDepartmentVo.class);
			}
		}
		
		return propertyDepartment;
	}
	
	/**
	 * 查询部门名称
	 * @return 部门信息值对象
	 */
	public String getName() {
		String deptName = null;
		if(ValidateHelper.isNotEmptyString(propertyDepartmentId)){
			PropertyDepartmentVo dept = get();
			return dept == null ? null : dept.getName();
		}
		return deptName;
	}
	
	/**
	 * 保存部门信息
	 */
	public void create() {
		if(propertyDepartment != null) {
			
			PropertyDepartmentPo parent = propertyDepartmentRepository.findParentDept(propertyDepartment.getDeptType(),
					ContextManager.getInstance().getOemCode());
			//创建父部门，判断 部门是否已存在
			if(ValidateHelper.isEmptyString(propertyDepartment.getParentId()) 
					&& ValidateHelper.isEmptyString(propertyDepartment.getProjectId())){
				if(parent != null){
					throw new LiefengException(StaffErrorCode.PARENT_DEPT_HAS_EXIST);
				}else{
					propertyDepartment.setParentId(SysConstants.DEFAULT_ID);
					propertyDepartment.setProjectId(SysConstants.DEFAULT_ID);
				}
			}
			
			if(ValidateHelper.isNotEmptyString(propertyDepartment.getProjectId()) 
					&& !SysConstants.DEFAULT_ID.equals(propertyDepartment.getProjectId())
					&& parent == null){
				throw new LiefengException(StaffErrorCode.PARENT_DEPT_NOT_EXIST);
			}
			
			if(parent != null){
				propertyDepartment.setParentId(parent.getId());
			}
			
			propertyDepartment.setId(UUIDGenerator.generate());
			
			propertyDepartment.setOemCode(ContextManager.getInstance().getOemCode());
			
			PropertyDepartmentPo propertyDepartmentPo = MyBeanUtil.createBean(propertyDepartment, PropertyDepartmentPo.class);
			propertyDepartmentRepository.save(propertyDepartmentPo);
			
			logger.info("Create department: '{}' successfully!", propertyDepartment);
		}
	}
	
	/**
	 * 更新部门信息
	 */
	public void update() {
		if(propertyDepartment != null) {
			
			PropertyDepartmentPo parent = propertyDepartmentRepository.findParentDept(propertyDepartment.getDeptType(),
					ContextManager.getInstance().getOemCode());
			
			//修改普通部门，判断父部门是否存在
			if(parent == null 
					&& ValidateHelper.isNotEmptyString(propertyDepartment.getProjectId())){
				throw new LiefengException(StaffErrorCode.PARENT_DEPT_NOT_EXIST);
			}
			
			//修改为已存在的父部门
			if(parent != null 
					&& !parent.getId().equals(propertyDepartment.getId()) 
					&& ValidateHelper.isEmptyString(propertyDepartment.getParentId())
					&& ValidateHelper.isEmptyString(propertyDepartment.getProjectId())){
				throw new LiefengException(StaffErrorCode.PARENT_DEPT_HAS_EXIST);
			}
			
			PropertyDepartmentPo propertyDepartmentPo = propertyDepartmentRepository.findOne(propertyDepartment.getId());
			
			if(propertyDepartmentPo != null){
				
				MyBeanUtil.copyBeanNotNull2Bean(propertyDepartment, propertyDepartmentPo);
			
				//修改为不存在的父部门
				if(parent == null 
						&& ValidateHelper.isEmptyString(propertyDepartment.getParentId())
						&& ValidateHelper.isEmptyString(propertyDepartment.getProjectId())){
					propertyDepartmentPo.setParentId(SysConstants.DEFAULT_ID);
					propertyDepartmentPo.setProjectId(SysConstants.DEFAULT_ID);
				}
				
				
				
				//普通部门需要设置父部门
				if(parent != null 
						&& !SysConstants.DEFAULT_ID.equals(propertyDepartment.getProjectId())){
					propertyDepartmentPo.setParentId(parent.getId());
				}
	
				propertyDepartmentRepository.save(propertyDepartmentPo);
				
				logger.info("Update department: '{}' successfully!", propertyDepartment);
				
				/*
				 * 员工是部门负责人
				 * 需要更新员工部门为父级部门
				 */
				if(ValidateHelper.isNotEmptyString(propertyDepartment.getDirectorId())){
					PropertyStaffContext.loadById(propertyDepartment.getDirectorId()).updateStaffDept(parent.getId());
				}
				
				if(ValidateHelper.isNotEmptyString(propertyDepartment.getDirector2Id())){
					PropertyStaffContext.loadById(propertyDepartment.getDirector2Id()).updateStaffDept(parent.getId());
				}
			
			}
			
		}
	}
	
	/**
	 * 删除部门信息
	 */
	public void delete() {
		if(ValidateHelper.isNotEmptyString(propertyDepartmentId)) {
			
			PropertyDepartmentPo propertyDepartmentPo = propertyDepartmentRepository.findOne(propertyDepartment.getId());
			
			if(propertyDepartmentPo != null 
					&& SysConstants.DEFAULT_ID.equals(propertyDepartment.getParentId())){
				List<PropertyDepartmentPo> parentDepts = propertyDepartmentRepository.findDepartmentsByParentId(propertyDepartmentId);
				
				if(ValidateHelper.isNotEmptyCollection(parentDepts)){
					throw new LiefengException(StaffErrorCode.SUB_DEPT_HAS_EXIST);
				}
				
			}
			
			propertyDepartmentRepository.delete(propertyDepartmentId);
			logger.info("Delete department: '{}' successfully!", propertyDepartmentId);
		}
	}
	
	public PropertyDepartmentVo findParentDept(){
		if(ValidateHelper.isNotEmptyString(propertyDepartmentId)){
			return propertyDepartmentQueryRepository.findParentDept(propertyDepartmentId);
		}
		return null;
	} 
	
	/**
	 * 查询多个部门
	 * @param ids
	 * @return
	 */
	public List<PropertyDepartmentVo> findDepartments(List<String> ids){
		if(ValidateHelper.isNotEmptyCollection(ids)){
			List<PropertyDepartmentPo> propertyDepartmentList = propertyDepartmentRepository.findAll(ids);
			logger.info("findDepartments propertyDepartmentList = {}", propertyDepartmentList);
			if(ValidateHelper.isNotEmptyCollection(propertyDepartmentList)){
				return MyBeanUtil.createList(propertyDepartmentList, PropertyDepartmentVo.class);
			}
		}
		return null;
	}
	
	/**
	 * 获取某个OEM下所有部门列表
	 * 分页
	 * @param page 当前页 (默认1)
	 * @param size 每页数据条数(默认10条)
	 * @return
	 */
	public DataPageValue<PropertyDepartmentVo> getDepartments4Page(Integer page, Integer size){
		Map<String, String> extra = new HashMap<String,String>();
		
		page = page == null ? 1 : page;
		size = size == null ? 10 : size;
		
		extra.put("oemCode", ContextManager.getInstance().getOemCode());
		
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(page);
		param.setPageSize(size);
		
		Long total = propertyDepartmentQueryRepository.queryByCount(param);
		
		param.getPager().setRowCount(total);
		
		List<PropertyDepartmentVo> propertyDepartments = propertyDepartmentQueryRepository.queryByPage(param);
		
		return new DataPageValue<PropertyDepartmentVo>(propertyDepartments, total, page, size);
	}
	
	/**
	 * 获取某个OEM下所有部门列表
	 * @return 所有部门列表
	 */
	public List<PropertyDepartmentVo> getDepartments() {
		String oemCode = ContextManager.getInstance().getOemCode();
		List<PropertyDepartmentPo> departmentPoList = 
				propertyDepartmentRepository.findDepartmentsByOemCode(oemCode);
		return MyBeanUtil.createList(departmentPoList, PropertyDepartmentVo.class);
	}

	public List<PropertyDepartmentVo> getDepartments(String projectId) {
		List<PropertyDepartmentPo> departmentPoList = 
				propertyDepartmentRepository.findDepartmentsByProjectId(projectId);
		return MyBeanUtil.createList(departmentPoList, PropertyDepartmentVo.class);
	}

	protected void setPropertyDepartmentId(String propertyDepartmentId) {
		this.propertyDepartmentId = propertyDepartmentId;
	}
	
	protected void setPropertyDepartment(PropertyDepartmentVo propertyDepartment) {
		this.propertyDepartment = propertyDepartment;
	}
}
