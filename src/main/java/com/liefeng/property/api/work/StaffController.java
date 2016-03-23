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

import com.liefeng.core.entity.DataValue;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.property.api.ro.id.StaffIdRo;
import com.liefeng.property.vo.staff.PropertyStaffVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="物业员工模块")
@RestController
@RequestMapping(value = "/api/work/staff")
public class StaffController {
	
	private static Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private IPropertyStaffService propertyStaffService;
	
	@ApiOperation(value="获取员工信息", notes="获取员工信息")
	@RequestMapping(value="/getStaff", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<PropertyStaffVo> getStaff(@Valid @ModelAttribute StaffIdRo staffIdRo){
		PropertyStaffVo propertyStaff = propertyStaffService.findPropertyStaffById(staffIdRo.getId());
		return DataValue.success(propertyStaff);
	}
	
}
