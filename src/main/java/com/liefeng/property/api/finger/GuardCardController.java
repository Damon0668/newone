package com.liefeng.property.api.finger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IGuardService;
import com.liefeng.property.api.ro.finger.guard.GuardCardStatusRo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="门禁磁卡模块")
@RestController
@RequestMapping(value = "/api/finger/guard/car")
public class GuardCardController {
	
	@Autowired
	private IGuardService guardService;
	
	@ApiOperation(value="磁卡状态修改")
	@RequestMapping(value="/updateCardStatus", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue updateCardStatus(@Valid @ModelAttribute GuardCardStatusRo guardCardStatus){
		guardService.updateGuardCardStatus(guardCardStatus.getCardId(), guardCardStatus.getStatus());
		return ReturnValue.success();
	} 
}
