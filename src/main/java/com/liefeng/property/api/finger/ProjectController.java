package com.liefeng.property.api.finger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.core.entity.DataValue;
import com.liefeng.intf.property.IProjectService;
import com.liefeng.property.api.ro.id.ProjectIdRo;
import com.liefeng.property.vo.project.ProjectVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="小区模块")
@RestController
@RequestMapping(value = "/api/finger/project")
public class ProjectController {

	@Autowired
	private IProjectService projectService;
	
	@ApiOperation(value="获取项目信息")
	@RequestMapping(value="/getProject", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<ProjectVo> getProject(@Valid @ModelAttribute ProjectIdRo id){
		ProjectVo project = projectService.findProjectById(id.getId());
		return DataValue.success(project);
	}
}
