package com.liefeng.property.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.property.domain.staff.PropertyStaffContext;
import com.liefeng.property.domain.staff.StaffArchiveContext;
import com.liefeng.property.vo.staff.PropertyStaffDetailInfoVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;

/**
 * 物业员工服务
 * @author 蔡少东
 * @date 2016年2月22日
 */
@Service
public class PropertyStaffService implements IPropertyStaffService {
	
	@Autowired
	private IUserService userService;
	
	@Override
	public DataPageValue<PropertyStaffVo> findPropertyStaff(int page, int size) {
		return null;
	}

	@Override
	public ReturnValue createStaff(PropertyStaffVo propertyStaff) throws LiefengException {
		PropertyStaffContext.build(propertyStaff).create();
		return ReturnValue.success();
	}
	@Transactional(rollbackOn=Exception.class)
	@Override
	public ReturnValue createStaff(PropertyStaffDetailInfoVo propertyStaffDetailInfo) throws Exception {
		
		PropertyStaffVo propertyStaff = PropertyStaffContext.build(propertyStaffDetailInfo.getPropertyStaffVo()).create();
		
		propertyStaffDetailInfo.getStaffArchiveVo().setStaffId(propertyStaff.getId());
		
		userService.createCustomerCheck(propertyStaffDetailInfo.getCustomerVo());
		
		propertyStaffDetailInfo.getStaffArchiveVo().setCustGlobalId(propertyStaffDetailInfo.getCustomerVo().getGlobalId());
		
		StaffArchiveContext.build(propertyStaffDetailInfo.getStaffArchiveVo()).create();
				
		return ReturnValue.success();
	}

	@Override
	public ReturnValue updateStaff(PropertyStaffVo propertyStaff) throws LiefengException {
		PropertyStaffContext.build(propertyStaff).update();
		return ReturnValue.success();
	}

	

}
