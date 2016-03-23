package com.liefeng.property.api.finger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.api.ro.EventReportRo;
import com.liefeng.property.api.ro.ProjectIdHouseNumPhoneRo;
import com.liefeng.property.bo.workbench.EventReportBo;
import com.liefeng.property.vo.workbench.EventReportVo;

/**
 * 工作台服务类（app）
 * @author xhw
 * @2016年3月7日 下午3:54:17
 */
@Api(value="工作台模块")
@RestController
@RequestMapping(value = "/api/finger/event")
public class EventReportController {

	@Autowired
	private IWorkbenchService workbenchService;

	/**
	 * 创建报事
	 * @param bo
	 * @return 
	 * @author xhw
	 * @date 2016年3月15日 下午6:03:49
	 */
	@ApiOperation(value="创建报事")
	@RequestMapping(value="/createEventReport", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue createEventReport(@Valid @ModelAttribute EventReportRo eventReportRo) {
		EventReportBo bo = MyBeanUtil.createBean(eventReportRo, EventReportBo.class);
		workbenchService.createAppEventReport(bo);
		
		return ReturnValue.success();
	}
	
	/**
	 * 获取用户的历史宝石
	 * @param projectId 项目id
	 * @param houseNum 房间号
	 * @param phone 手机号码
	 * @return 
	 * @author xhw
	 * @date 2016年3月20日 上午9:58:29
	 */
	@ApiOperation(value="获取用户的历史报事")
	@RequestMapping(value="/getEventReportList", method=RequestMethod.POST)
	@ResponseBody
	public DataListValue<EventReportVo> getEventReportList(@Valid @ModelAttribute ProjectIdHouseNumPhoneRo projectIdHouseNumPhoneRo) {
		List<EventReportVo> eventReportVoList = workbenchService.getEventReportList(projectIdHouseNumPhoneRo.getProjectId(), projectIdHouseNumPhoneRo.getHouseNum(), projectIdHouseNumPhoneRo.getPhone());
		return DataListValue.success(eventReportVoList);
	}
}
