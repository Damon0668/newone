package com.liefeng.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.BaseValue;
import com.liefeng.intf.service.tcc.ITccMsgDubboService;
import com.liefeng.mq.type.TccBasicEvent;
import com.liefeng.property.domain.household.ProprietorContext;
import com.liefeng.property.vo.household.ProprietorVo;

/**
 * 
 * @author 蔡少东
 *
 */
@RestController
@RequestMapping(value = "/proprietor")
public class ProprietorController {
	
	@Autowired
	private ITccMsgDubboService tccMsgDubboService;
	
	@RequestMapping("/create")
	@ResponseBody
	public Object create(ProprietorVo proprietor) throws Exception{
		proprietor = ProprietorContext.build(proprietor).create();
		ContextManager.getInstance().setOemCode(proprietor.getOemCode());
		tccMsgDubboService.sendTccMsg(TccBasicEvent.CREATE_CUSTOMER, proprietor);;
		return Boolean.TRUE;
	}
}
