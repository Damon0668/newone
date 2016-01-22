package com.liefeng.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.property.service.ProprietorService;
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
	private ProprietorService proprietorService;
	
	@RequestMapping("/create")
	@ResponseBody
	public Object create(ProprietorVo proprietor) throws Exception{
		for(int i=1;i<10000;i++){
			proprietorService.createProprietor(proprietor);
		}
		return Boolean.TRUE;
	}
}
