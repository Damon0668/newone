package com.liefeng.property.domain.staff;

import java.util.Date;

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
import com.liefeng.property.po.staff.PropertyStaffPo;
import com.liefeng.property.repository.PropertyStaffRepository;
import com.liefeng.property.vo.staff.PropertyStaffVo;

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
	
	/**
	 * 物业员工ID
	 */
	private String propertyStaffId;
	
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
	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	private static PropertyStaffContext getInstance() {
		return SpringBeanUtil.getBean(PropertyStaffContext.class);
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
	 * 查询物业员工信息
	 * @return 物业员工值对象
	 */
	public PropertyStaffVo getPropertyStaff() {
		if(propertyStaff == null) {
			PropertyStaffPo propertyStaffPo = null;
			if(ValidateHelper.isNotEmptyString(propertyStaffId)) {
				propertyStaffPo = propertyStaffRepository.findOne(propertyStaffId);
			}
			
			if(propertyStaffPo != null) {
				propertyStaff = MyBeanUtil.createBean(propertyStaffPo, PropertyStaffVo.class);
			}
		}
		
		return propertyStaff;
	}
	
	/**
	 * 保存物业员工信息
	 */
	public PropertyStaffVo create() {
		if(propertyStaff != null) {
			propertyStaff.setId(UUIDGenerator.generate());
			propertyStaff.setOemCode(ContextManager.getInstance().getOemCode()); // TODO 待确定后补齐
			propertyStaff.setCreateTime(new Date());
			
			PropertyStaffPo propertyStaffPo = MyBeanUtil.createBean(propertyStaff, PropertyStaffPo.class);
			propertyStaffRepository.save(propertyStaffPo);
		}
		return propertyStaff;
	}
	
	public void update() {
		if(propertyStaff != null && ValidateHelper.isNotEmptyString(propertyStaff.getId())){
			PropertyStaffPo propertyStaffPo = propertyStaffRepository.findOne(propertyStaff.getId());
			if(propertyStaffPo != null){
				MyBeanUtil.copyBeanNotNull2Bean(propertyStaff, propertyStaffPo);
				propertyStaffRepository.save(propertyStaffPo);
			}
		}
	}

}
