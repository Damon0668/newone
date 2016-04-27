package com.liefeng.property.api.work;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.property.api.ro.finger.household.ResidentRo;
import com.liefeng.property.api.ro.temp.ProprietorSingleHouseRo;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.vo.household.ProprietorHouseVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ResidentHouseVo;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * 
 * @author wuzhijing
 */
@Api(value="业主数据迁移")
@RestController("ownerController")
@RequestMapping(value = "/api/work/owner")
public class OwnerController {

	private static Logger logger = LoggerFactory.getLogger(OwnerController.class);
	
	@Autowired
	private IHouseholdService householdService;
	
	
	
	@ApiOperation(value="保存业主数据")
	@RequestMapping(value="/saveProprietor", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue saveProprietor(@Valid @ModelAttribute ProprietorSingleHouseRo proprietorSingleHouseRo){
		ProprietorSingleHouseVo proprietorSingleHouseVo = MyBeanUtil.createBean(proprietorSingleHouseRo, ProprietorSingleHouseVo.class);
		householdService.saveProprietor(proprietorSingleHouseVo);
		return ReturnValue.success();
	}
	
	@ApiOperation(value="保存住户数据")
	@RequestMapping(value="/saveResident", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue saveResident(@Valid @ModelAttribute ResidentRo resident){
		ProprietorHouseVo proprietorHouseVo =  householdService.getProprietorHouse(resident.getProjectId(), resident.getHouseNum());
		if(proprietorHouseVo == null){
			 throw  new PropertyException("不存在业主");
		}
		resident.setProprietorId(proprietorHouseVo.getProprietorId());
		
		ResidentVo residentVo = MyBeanUtil.createBean(resident, ResidentVo.class);
		
		CustomerVo customerVo = new CustomerVo();
		customerVo.setRealName(residentVo.getName());
		customerVo.setMobile(residentVo.getMobile());
		customerVo.setIdNum(resident.getIdNum());
		residentVo.setCustomer(customerVo);
		
		ResidentHouseVo residentHouseVo = new ResidentHouseVo();
		residentHouseVo.setHouseId(proprietorHouseVo.getId());
		residentHouseVo.setProjectId(proprietorHouseVo.getProjectId());
		residentHouseVo.setProprietorId(proprietorHouseVo.getProprietorId());
		residentVo.setResidentHouse(residentHouseVo);
		
		householdService.saveResident(residentVo);
		return ReturnValue.success();
	}
}
