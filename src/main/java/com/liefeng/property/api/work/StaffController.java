package com.liefeng.property.api.work;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.base.vo.CustomerVo;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.TimeUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.DataListValue;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.property.api.ro.id.StaffIdRo;
import com.liefeng.property.api.ro.work.staff.UpdateStaffRo;
import com.liefeng.property.vo.api.StaffInfoVo;
import com.liefeng.property.vo.staff.PropertyDepartmentVo;
import com.liefeng.property.vo.staff.PropertyStaffDetailInfoVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.property.vo.staff.StaffArchiveVo;
import com.liefeng.property.vo.staff.StaffContactVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="物业员工模块")
@RestController
@RequestMapping(value = "/api/work/staff")
public class StaffController {
	
	@Autowired
	private IPropertyStaffService propertyStaffService;
	
	@Autowired
	private IUserService userService;

	@ApiOperation(value="获取员工信息", notes="获取员工信息")
	@RequestMapping(value="/getStaff", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<StaffInfoVo> getStaff(@Valid @ModelAttribute StaffIdRo staffIdRo){
		StaffInfoVo staffInfoVo = new StaffInfoVo();
		PropertyStaffVo propertyStaff = propertyStaffService.findPropertyStaffById(staffIdRo.getStaffId());
		if(propertyStaff != null){
			MyBeanUtil.copyBeanNotNull2Bean(propertyStaff, staffInfoVo);
			StaffArchiveVo staffArchive = propertyStaffService.findStaffArchByStaffId(staffIdRo.getStaffId());
			if(staffArchive != null){
				MyBeanUtil.copyBeanNotNull2Bean(staffArchive, staffInfoVo);
				CustomerVo customer = userService.getCustomerByGlobalId(staffArchive.getCustGlobalId());
				if(customer != null){
					MyBeanUtil.copyBeanNotNull2Bean(customer, staffInfoVo);
				}
			}
		}
		return DataValue.success(staffInfoVo);
	}
	
	/*@ApiOperation(value="获取员工档案", notes="获取员工档案")
	@RequestMapping(value="/getStaffArchive", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<StaffArchiveVo> getStaffArchive(@Valid @ModelAttribute StaffIdRo staffIdRo){
		StaffArchiveVo staffArchive = propertyStaffService.findStaffArchByStaffId(staffIdRo.getStaffId());
		return DataValue.success(staffArchive);
	}
	
	@ApiOperation(value="获取员工个人信息", notes="获取员工个人信息")
	@RequestMapping(value="/getCustomer", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<CustomerVo> getCustomer(@Valid @ModelAttribute StaffIdRo staffIdRo){
		StaffArchiveVo staffArchive = propertyStaffService.findStaffArchByStaffId(staffIdRo.getStaffId());
		CustomerVo customer = userService.getCustomerByGlobalId(staffArchive.getCustGlobalId());
		return DataValue.success(customer);
	}*/
	
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
			
			customer.setSex(updateStaffRo.getSex());
			
			if(ValidateHelper.isNotEmptyString(updateStaffRo.getName())){
				customer.setRealName(updateStaffRo.getName());
			}
			
			if(ValidateHelper.isNotEmptyString(updateStaffRo.getBirthday())){
				customer.setBirthday(TimeUtil.format(updateStaffRo.getBirthday(), TimeUtil.PATTERN_1));
			}
			
		}
		
		propertyStaffService.updateStaff(propertyStaffDetailInfo);
		
		return ReturnValue.success();
	}
	
	/**
	 * 获取员工通讯录
	 * @param staffIdRo
	 * @return 
	 * @author xhw
	 * @date 2016年3月28日 上午10:58:44
	 */
	@ApiOperation(value="获取员工通讯录", notes="员工通讯录")
	@RequestMapping(value="/getStaffContact", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<StaffContactVo> getStaffContact(@Valid @ModelAttribute StaffIdRo staffIdRo){
		List<StaffContactVo> staffContactList = new ArrayList<StaffContactVo>();
		//获取部门权限
		List<PropertyDepartmentVo>  departmentVos = propertyStaffService.findStaffContactPrivilege(staffIdRo.getStaffId());
		
		for(PropertyDepartmentVo departmentVo : departmentVos){
			StaffContactVo staffContactVo = new StaffContactVo();
			staffContactVo.setDepartmentId(departmentVo.getId());
			staffContactVo.setDepartmentName(departmentVo.getName());
			
			//获取部门的员工
			List<PropertyStaffVo> staffVoList = propertyStaffService.findPropertyStaff(departmentVo.getId());
			
			staffContactVo.setStaffList(staffVoList);
			staffContactList.add(staffContactVo);
		}
		return DataListValue.success(staffContactList);
	}
}
