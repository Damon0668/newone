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
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.error.StaffErrorCode;
import com.liefeng.property.exception.PropertyException;
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
	 * 保存部门信息
	 */
	public void create() {
		if(propertyDepartment != null) {
			
			String departName = propertyDepartment.getName().trim();
			
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
	
	protected void setPropertyDepartmentId(String propertyDepartmentId) {
		this.propertyDepartmentId = propertyDepartmentId;
	}
	
	protected void setPropertyDepartment(PropertyDepartmentVo propertyDepartment) {
		this.propertyDepartment = propertyDepartment;
	}
}
