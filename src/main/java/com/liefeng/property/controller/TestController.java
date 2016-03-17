package com.liefeng.property.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.core.entity.DataValue;
import com.liefeng.property.controller.ro.TestRo;
import com.liefeng.property.vo.project.ProjectVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="测试模块")
@RestController
@RequestMapping(value = "/api/test")
public class TestController {
	
	@ApiOperation(value="测试接口", notes="测试接口详细描述")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<ProjectVo> get(@PathVariable String id){
		return DataValue.success(new ProjectVo());
	}

	@ApiOperation(value="测试接口", notes="测试接口详细描述")
	@RequestMapping(value="/load", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<ProjectVo> load(@RequestParam(required=false) String id, @RequestParam String name){
		return DataValue.success(new ProjectVo());
	}
	
	@ApiOperation(value="测试接口", notes="测试接口详细描述")
	@RequestMapping(value="/find", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<ProjectVo> find(@ModelAttribute TestRo testRo){
		return DataValue.success(null);
	}
	
	@ApiOperation(value="测试接口", notes="测试接口详细描述")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<ProjectVo> save(@ModelAttribute TestRo testRo){
		return DataValue.success(null);
	}
}
