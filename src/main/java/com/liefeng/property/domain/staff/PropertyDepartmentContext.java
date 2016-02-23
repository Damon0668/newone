package com.liefeng.property.domain.staff;

import java.util.List;

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
import com.liefeng.property.error.StaffErrorCode;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.po.staff.PropertyDepartmentPo;
import com.liefeng.property.repository.PropertyDepartmentRepository;
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
	 * 保存部门信息
	 */
	public void create() {
		if(propertyDepartment != null) {
			String departName = propertyDepartment.getName();
			String oemCode = ContextManager.getInstance().getOemCode();
			if (ValidateHelper.isEmptyString(departName)) {
				throw new PropertyException(StaffErrorCode.DEPARTMENT_NAME_NULL);
			}
			
			PropertyDepartmentPo departmentWithSameName = 
					propertyDepartmentRepository.findDepartmentByNameAndOemCode(departName, oemCode);
			if (departmentWithSameName != null) {
				throw new PropertyException(StaffErrorCode.DEPARTMENT_ALREADY_EXIST, departName);
			}
			
			propertyDepartment.setId(UUIDGenerator.generate());
			propertyDepartment.setOemCode(oemCode);
			
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
			PropertyDepartmentPo propertyDepartmentPo = 
					propertyDepartmentRepository.findOne(propertyDepartment.getId());
			
			if (ValidateHelper.isNotEmptyString(propertyDepartment.getDirectorId())) {
				propertyDepartmentPo.setDirectorId(propertyDepartment.getDirectorId());
			}
			if (ValidateHelper.isNotEmptyString(propertyDepartment.getName())) {
				propertyDepartmentPo.setName(propertyDepartment.getName());
			}
			if (ValidateHelper.isNotEmptyString(propertyDepartment.getTel())) {
				propertyDepartmentPo.setTel(propertyDepartment.getTel());
			}
			propertyDepartmentRepository.save(propertyDepartmentPo);
			
			logger.info("Update department: '{}' successfully!", propertyDepartment);
		}
	}
	
	/**
	 * 删除部门信息
	 */
	public void delete() {
		if(ValidateHelper.isNotEmptyString(propertyDepartmentId)) {
			propertyDepartmentRepository.delete(propertyDepartmentId);
			logger.info("Delete department: '{}' successfully!", propertyDepartmentId);
		}
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
	
	protected void setPropertyDepartmentId(String propertyDepartmentId) {
		this.propertyDepartmentId = propertyDepartmentId;
	}
	
	protected void setPropertyDepartment(PropertyDepartmentVo propertyDepartment) {
		this.propertyDepartment = propertyDepartment;
	}
}
