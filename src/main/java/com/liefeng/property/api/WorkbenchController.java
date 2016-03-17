package com.liefeng.property.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.bo.workbench.EventReportBo;
import com.liefeng.property.vo.workbench.EventReportVo;
import com.liefeng.property.vo.workbench.NoticeVo;

/**
 * 工作台服务类（app）
 * @author xhw
 * @2016年3月7日 下午3:54:17
 */
@RestController
@RequestMapping(value = "/api/workbench")
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
	@RequestMapping("getNoticeList")
	@ResponseBody
	public DataPageValue<NoticeVo> getNoticeList(String terminal, String noticeType, String projectId, String groupId, String privilegeType, Integer page, Integer size){
		DataPageValue<NoticeVo> noticeDataPage = workbenchService.findNoticeOfPublished(terminal, noticeType, projectId, groupId, privilegeType, page, size);
		return noticeDataPage;
	}
	
	/**
	 * 根据id获取通知
	 * @param id 通知id
	 * @return
	 * @author xhw
	 * @2016年3月7日 下午7:08:08
	 */
	@RequestMapping("getNotice")
	@ResponseBody
	public DataValue<NoticeVo> getNotice(String id){
		NoticeVo noticeVo = workbenchService.getNoticeById(id);
		return DataValue.success(noticeVo);
	}
	
	/**
	 * 创建报事
	 * @param bo
	 * @return 
	 * @author xhw
	 * @date 2016年3月15日 下午6:03:49
	 */
	@RequestMapping("createEventReport")
	@ResponseBody
	public ReturnValue createEventReport(EventReportBo bo) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		workbenchService.createAppEventReport(bo);
		
		return ReturnValue.success();
	}
}
