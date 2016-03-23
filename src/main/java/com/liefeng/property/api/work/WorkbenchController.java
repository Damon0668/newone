package com.liefeng.property.api.work;

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
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.api.ro.EventReportRo;
import com.liefeng.property.api.ro.NoticeRo;
import com.liefeng.property.api.ro.ProjectIdHouseNumPhoneRo;
import com.liefeng.property.api.ro.id.NoticeIdRo;
import com.liefeng.property.bo.workbench.EventReportBo;
import com.liefeng.property.bo.workbench.NoticeBo;
import com.liefeng.property.vo.workbench.EventReportVo;
import com.liefeng.property.vo.workbench.NoticeVo;

/**
 * 工作台服务类（app）
 * @author xhw
 * @2016年3月7日 下午3:54:17
 */
@Api(value="工作台模块")
@RestController
@RequestMapping(value = "/api/work/workbench")
public class WorkbenchController {

	@Autowired
	private IWorkbenchService workbenchService;

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
	@ApiOperation(value="通知列表【社区通告、温馨提醒、通知、社区动态公用接口】")
	@RequestMapping(value="/getNoticeList", method=RequestMethod.GET)
	@ResponseBody
	public DataPageValue<NoticeVo> getNoticeList(@Valid @ModelAttribute NoticeRo noticeRo){
		NoticeBo noticeBo = MyBeanUtil.createBean(noticeRo, NoticeBo.class);
		DataPageValue<NoticeVo> noticeDataPage = workbenchService.findNoticeOfPublished(noticeBo);
		return noticeDataPage;
	}
	
	/**
	 * 根据id获取通知
	 * @param id 通知id
	 * @return
	 * @author xhw
	 * @2016年3月7日 下午7:08:08
	 */
	@ApiOperation(value="通知信息")
	@RequestMapping(value="/getNotice", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<NoticeVo> getNotice(@Valid @ModelAttribute NoticeIdRo noticeIdRo){
		NoticeVo noticeVo = workbenchService.getNoticeById(noticeIdRo.getId());
		return DataValue.success(noticeVo);
	}
	
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
