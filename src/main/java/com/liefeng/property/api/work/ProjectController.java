package com.liefeng.property.api.work;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.core.entity.DataListValue;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IProjectService;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.api.ro.id.StaffIdRo;
import com.liefeng.property.api.ro.work.project.NoticeRo;
import com.liefeng.property.api.ro.work.project.ProjectNoticeRo;
import com.liefeng.property.bo.workbench.NoticeBo;
import com.liefeng.property.constant.WorkbenchConstants;
import com.liefeng.property.vo.project.ProjectNoticeVo;
import com.liefeng.property.vo.project.ProjectVo;
import com.liefeng.property.vo.workbench.NoticeVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="小区模块")
@RestController("workProjectController")
@RequestMapping(value = "/api/work/project")
public class ProjectController {

	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IWorkbenchService workbenchService;
	
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
	
	/**
	 * 查看已发布通知（分页、app）
	 * @param terminal 接收端类型(1：电视；2：电脑；3：移动设备)
	 * @param naticeType 通知类型（1：社区通告；2：温馨提醒；3：通知；4：社区动态）
	 * @param projectId 项目id（员工：所管理的项目id字符串，业主：所在项目id）
	 * @param groupId （员工：部门id，业主：楼栋id）
	 * @param privilegeType 接收人类型（员工：1，业主：2）
	 * @param page 第几页，最小值为1
	 * @param size 每页条数，最小值为0
	 * @return
	 * @author xhw
	 * @2016年3月7日 下午3:58:45
	 */
	@ApiOperation(value="通知列表[社区通告|温馨提醒|通知|社区动态公用接口]")
	@RequestMapping(value="/getNoticeList", method=RequestMethod.GET)
	@ResponseBody
	public DataPageValue<NoticeVo> getNoticeList(@Valid @ModelAttribute NoticeRo noticeRo){
		NoticeBo noticeBo = MyBeanUtil.createBean(noticeRo, NoticeBo.class);
		noticeBo.setPrivilegeType(WorkbenchConstants.NoticePrivilegeType.STAFF);
		DataPageValue<NoticeVo> noticeDataPage = workbenchService.findNoticeOfPublished(noticeBo);
		return noticeDataPage;
	}
}
