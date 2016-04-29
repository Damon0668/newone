package com.liefeng.property.api.ro.temp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.intf.property.IProjectService;
import com.liefeng.intf.property.ISysService;
import com.liefeng.property.api.ro.finger.household.ResidentRo;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.vo.household.ProprietorHouseVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ResidentHouseVo;
import com.liefeng.property.vo.household.ResidentVo;
import com.liefeng.property.vo.project.HouseVo;
import com.liefeng.property.vo.project.ProjectBuildingVo;

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
	
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private ISysService sysService;
	
	@ApiOperation(value="保存业主数据")
	@RequestMapping(value="/saveProprietor", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue saveProprietor(@Valid @ModelAttribute ProprietorSingleHouseRo proprietorSingleHouseRo){

		try {
			if(ValidateHelper.isNotEmptyString(proprietorSingleHouseRo.getName())){
				proprietorSingleHouseRo.setName(URLDecoder.decode(proprietorSingleHouseRo.getName(),"utf-8"));
			}
			if(ValidateHelper.isNotEmptyString(proprietorSingleHouseRo.getAddress())){
				proprietorSingleHouseRo.setAddress(URLDecoder.decode(proprietorSingleHouseRo.getAddress(),"utf-8"));
			}
			proprietorSingleHouseRo.setUseType("1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ProprietorSingleHouseVo proprietorSingleHouseVo = MyBeanUtil.createBean(proprietorSingleHouseRo, ProprietorSingleHouseVo.class);
		householdService.saveProprietor(proprietorSingleHouseVo);
		return ReturnValue.success();
	}
	
	@ApiOperation(value="保存住户数据")
	@RequestMapping(value="/saveResident", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue saveResident(@Valid @ModelAttribute ResidentRo resident){
		ProprietorHouseVo proprietorHouseVo =  householdService.getProprietorHouse(resident.getProjectId(), null /*resident.getHouseNum()*/);
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
	
	@ApiOperation(value="保存房产资料")
	@RequestMapping(value="/saveHouse", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue saveHouse(@Valid @ModelAttribute HouseRo houseRo){
		ProjectBuildingVo projectBuildingVo = null;
		ProjectBuildingVo floorVo = null;
		try {
		HouseVo houseVo = MyBeanUtil.createBean(houseRo, HouseVo.class);
		
		String houseType = sysService.getDictValueByName("HOUSE_TYPE", URLDecoder.decode(houseVo.getHouseType(),"utf-8"));
		if(ValidateHelper.isEmptyString(houseType)){
			throw new PropertyException(houseVo.getHouseType());
		}
		houseVo.setHouseType(houseType);
		if(ValidateHelper.isNotEmptyString(houseRo.getBuildingName())){
			projectBuildingVo = projectService.findBuilding(houseRo.getProjectId(),null,URLDecoder.decode(houseRo.getBuildingName(),"utf-8"));
			if(projectBuildingVo == null){
				projectBuildingVo = new ProjectBuildingVo();
				projectBuildingVo.setProjectId(houseRo.getProjectId());
				projectBuildingVo.setName(URLDecoder.decode(houseRo.getBuildingName(),"utf-8"));
				projectBuildingVo.setCode(houseRo.getBuildingCode());
				projectService.createProjectBuilding(projectBuildingVo);
				projectBuildingVo = projectService.findBuilding(houseRo.getProjectId(),null,URLDecoder.decode(houseRo.getBuildingName(),"utf-8"));
			}
			houseVo.setBuildingId(projectBuildingVo.getId());
		}
		
		if(ValidateHelper.isNotEmptyString(houseRo.getFloorName())){
			floorVo = projectService.findBuilding(houseRo.getProjectId(),houseVo.getBuildingId(),URLDecoder.decode(houseRo.getFloorName(),"utf-8"));
			if(floorVo == null){
				floorVo = new ProjectBuildingVo();
				floorVo.setParentId(houseVo.getBuildingId());
				floorVo.setProjectId(houseRo.getProjectId());
				floorVo.setName(URLDecoder.decode(houseRo.getFloorName(),"utf-8"));
				floorVo.setCode(houseRo.getFloorCode());
				projectService.createProjectBuilding(floorVo);
				floorVo = projectService.findBuilding(houseRo.getProjectId(),houseVo.getBuildingId(),URLDecoder.decode(houseRo.getFloorName(),"utf-8"));
			}
			houseVo.setFloorId(floorVo.getId());
		}
		projectService.createHouse(houseVo);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ReturnValue.success();
	}
}
