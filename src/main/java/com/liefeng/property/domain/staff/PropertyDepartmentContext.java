package com.liefeng.property.domain.staff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.staff.PropertyDepartmentPo;
import com.liefeng.property.repository.PropertyDepartmentRepository;
import com.liefeng.property.vo.staff.PropertyDepartmentVo;

/**
 * 部门信息领域模型
 * 
 * @author ZhenTingJun
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
	 * 根据部门信息值对象构建上下文
	 * @param propertyDepartment 部门信息值对象
	 * @return 部门信息上下文
	 */
	public static PropertyDepartmentContext build(PropertyDepartmentVo propertyDepartment) {
		PropertyDepartmentContext propertyDepartmentContext = getInstance();
		propertyDepartmentContext.propertyDepartment = propertyDepartment;
		
		return propertyDepartmentContext;
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
	 * 根据部门信息ID加载上下文
	 * @param propertyDepartmentId 部门信息ID
	 * @return 部门信息上下文
	 */
	public static PropertyDepartmentContext loadById(String propertyDepartmentId) {
		PropertyDepartmentContext propertyDepartmentContext = getInstance();
		propertyDepartmentContext.propertyDepartmentId = propertyDepartmentId;
		
		return propertyDepartmentContext;
	}
	
	/**
	 * 查询部门信息
	 * @return 部门信息值对象
	 */
	public PropertyDepartmentVo getPropertyDepartment() {
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
			propertyDepartment.setId(UUIDGenerator.generate());
			propertyDepartment.setOemCode(""); // TODO 待确定后补齐
			
			PropertyDepartmentPo propertyDepartmentPo = MyBeanUtil.createBean(propertyDepartment, PropertyDepartmentPo.class);
			propertyDepartmentRepository.save(propertyDepartmentPo);
		}
	}
	
}
