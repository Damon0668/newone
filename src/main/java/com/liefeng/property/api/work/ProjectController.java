package com.liefeng.property.api.work;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.core.entity.DataListValue;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IProjectService;
import com.liefeng.property.api.ro.id.StaffIdRo;
import com.liefeng.property.api.ro.work.project.ProjectNoticeRo;
import com.liefeng.property.vo.project.ProjectNoticeVo;
import com.liefeng.property.vo.project.ProjectVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="小区模块")
@RestController("workProjectController")
@RequestMapping(value = "/api/work/project")
public class ProjectController {

	@Autowired
	private IProjectService projectService;
	
	@ApiOperation(value="获取员工管理的小区列表")
	@RequestMapping(value="/getProjectByStaff", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<ProjectVo> getProjectByStaff(@Valid @ModelAttribute StaffIdRo staffId){
		List<ProjectVo> projects = projectService.findProjectByStaffId(staffId.getStaffId());
		return DataListValue.success(projects);
	}
	
	/**
	 * 物业须知列表
	 * @param noticeRo
	 * @return 
	 * @author xhw
	 * @date 2016年4月6日 下午2:18:27
	 */
	@ApiOperation(value="物业须知列表", notes="物业须知列表")
	@RequestMapping(value="/getProjectNoticeList", method=RequestMethod.GET)
	@ResponseBody
	public DataPageValue<ProjectNoticeVo> getProjectNoticeList(@Valid @ModelAttribute ProjectNoticeRo projectNoticeRo){
		DataPageValue<ProjectNoticeVo> projectNoticeData = projectService.findProjectNotices(projectNoticeRo.getProjectId(), projectNoticeRo.getPage(), projectNoticeRo.getSize());
		return projectNoticeData;
	}
}
