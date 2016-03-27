package com.liefeng.property.api.work;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.base.vo.CustomerVo;
import com.liefeng.common.util.TimeUtil;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.property.api.ro.id.StaffIdRo;
import com.liefeng.property.api.ro.work.staff.UpdateStaffRo;
import com.liefeng.property.vo.staff.PropertyStaffDetailInfoVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.property.vo.staff.StaffArchiveVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="物业员工模块")
@RestController
@RequestMapping(value = "/api/work/staff")
public class StaffController {
	
	private static Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private IPropertyStaffService propertyStaffService;
	
	@Autowired
	private IUserService userService;
	
	@ApiOperation(value="获取员工信息", notes="获取员工信息")
	@RequestMapping(value="/getStaff", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<PropertyStaffVo> getStaff(@Valid @ModelAttribute StaffIdRo staffIdRo){
		PropertyStaffVo propertyStaff = propertyStaffService.findPropertyStaffById(staffIdRo.getId());
		return DataValue.success(propertyStaff);
	}
	
	@ApiOperation(value="获取员工档案", notes="获取员工档案")
	@RequestMapping(value="/getStaffArchive", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<StaffArchiveVo> getStaffArchive(@Valid @ModelAttribute StaffIdRo staffIdRo){
		StaffArchiveVo staffArchive = propertyStaffService.findStaffArchByStaffId(staffIdRo.getId());
		return DataValue.success(staffArchive);
	}
	
	@ApiOperation(value="获取员工个人信息", notes="获取员工个人信息")
	@RequestMapping(value="/getCustomer", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<CustomerVo> getCustomer(@Valid @ModelAttribute StaffIdRo staffIdRo){
		StaffArchiveVo staffArchive = propertyStaffService.findStaffArchByStaffId(staffIdRo.getId());
		CustomerVo customer = userService.getCustomerByGlobalId(staffArchive.getCustGlobalId());
		return DataValue.success(customer);
	}
	
	@ApiOperation(value="更新员工信息", notes="更新员工信息")
	@RequestMapping(value="/updateStaff", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue updateStaff(@Valid @ModelAttribute UpdateStaffRo updateStaffRo){
		
		PropertyStaffDetailInfoVo propertyStaffDetailInfo = propertyStaffService.findStaffDetailInfo(updateStaffRo.getStaffId());
		PropertyStaffVo staff = propertyStaffDetailInfo.getPropertyStaffVo();
		StaffArchiveVo staffArchive = propertyStaffDetailInfo.getStaffArchiveVo();
		CustomerVo customer = propertyStaffDetailInfo.getCustomerVo();
		if(staff != null){
			staff.setName(updateStaffRo.getName());
		}
		
		if(staffArchive != null){
			staffArchive.setName(updateStaffRo.getName());
			staffArchive.setPhone(updateStaffRo.getPhone());
		}
	
		if(customer != null){
			customer.setPortraitUrl(updateStaffRo.getPortraitUrl());
			customer.setRealName(updateStaffRo.getName());
			customer.setSex(updateStaffRo.getSex());
			customer.setBirthday(TimeUtil.format(updateStaffRo.getBirthday(), TimeUtil.PATTERN_1));
		}
		
		propertyStaffService.updateStaff(propertyStaffDetailInfo);
		
		return ReturnValue.success();
	}
}
