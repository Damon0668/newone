package com.liefeng.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author 蔡少东
 *
 */
@RestController
@RequestMapping(value = "/proprietor")
public class ProprietorController {
	
	
	@RequestMapping("/create")
	@ResponseBody
	public Object create(){
		return null;
	}
}
