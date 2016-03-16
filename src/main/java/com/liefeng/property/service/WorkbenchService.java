package com.liefeng.property.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.base.vo.UserVo;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.intf.service.msg.IPushMsgService;
import com.liefeng.intf.service.workflow.IWorkflowService;
import com.liefeng.mq.type.MessageEvent;
import com.liefeng.property.bo.workbench.EventReportBo;
import com.liefeng.property.constant.HouseholdConstants;
import com.liefeng.property.constant.StaffConstants;
import com.liefeng.property.constant.WorkbenchConstants;
import com.liefeng.property.domain.workbench.EventProcAttachContext;
import com.liefeng.property.domain.workbench.EventProcessContext;
import com.liefeng.property.domain.workbench.EventReportContext;
import com.liefeng.property.domain.workbench.NoticeContext;
import com.liefeng.property.domain.workbench.NoticePrivilegeContext;
import com.liefeng.property.domain.workbench.ProprietorContactsContext;
import com.liefeng.property.domain.workbench.ScheduleContext;
import com.liefeng.property.domain.workbench.StaffContactsContext;
import com.liefeng.property.domain.workbench.TaskAttachmentContext;
import com.liefeng.property.domain.workbench.TaskContext;
import com.liefeng.property.domain.workbench.TaskPrivilegeContext;
import com.liefeng.property.domain.workbench.WebsiteMsgContext;
import com.liefeng.property.domain.workbench.WebsiteMsgPrivilegeContext;
import com.liefeng.property.error.WorkbenchErrorCode;
import com.liefeng.property.exception.WorkbenchException;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.property.vo.workbench.EventProcAttachVo;
import com.liefeng.property.vo.workbench.EventProcessVo;
import com.liefeng.property.vo.workbench.EventReportVo;
import com.liefeng.property.vo.workbench.NoticePrivilegeVo;
import com.liefeng.property.vo.workbench.NoticeVo;
import com.liefeng.property.vo.workbench.ProprietorContactsVo;
import com.liefeng.property.vo.workbench.ScheduleVo;
import com.liefeng.property.vo.workbench.StaffContactsVo;
import com.liefeng.property.vo.workbench.TaskAttachmentVo;
import com.liefeng.property.vo.workbench.TaskPrivilegeVo;
import com.liefeng.property.vo.workbench.TaskVo;
import com.liefeng.property.vo.workbench.WebsiteMsgPrivilegeVo;
import com.liefeng.property.vo.workbench.WebsiteMsgVo;
import com.liefeng.service.constant.PushMsgConstants;
import com.liefeng.service.vo.msg.ListUserMsg;
import com.liefeng.service.vo.msg.SingleUserMsg;


/**
 * 任务服务实现类
 * @author XHW
 * @date 2016年2月19日下午7:36:30
 */
@Service
public class WorkbenchService implements IWorkbenchService {
	private static Logger logger = LoggerFactory.getLogger(WorkbenchService.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IPushMsgService pushMsgService;
	
	@Autowired
	private IWorkflowService workflowService;
	
	@Autowired
	private IPropertyStaffService propertyStaffService;
	
	
	@Override
	public TaskVo findTaskById(String taskId) {
		TaskContext taskContext = TaskContext.loadById(taskId);
		TaskVo taskVo = taskContext.getById();
		
		return taskVo;
	}

	@Override
	@Transactional
	public void createTask(TaskVo task){
		TaskContext taskContext = TaskContext.build(task);
		TaskVo taskVo = taskContext.create();

		if (taskVo != null) { // 创建任务的权限 、附件
			if (ValidateHelper.isNotEmptyString(taskVo.getPrivilegeStr())) { // 权限
				String[] privilegeArray = taskVo.getPrivilegeStr().split(","); //多个员工id字符串，以逗号隔开
				for (int i = 0; i < privilegeArray.length; i++) {
					TaskPrivilegeVo privilegeVo = new TaskPrivilegeVo();
					privilegeVo.setTaskId(taskVo.getId());  //任务id
					privilegeVo.setStaffId(privilegeArray[i]); //员工id
					createTaskPrivilege(privilegeVo);
				}

			}

			if (ValidateHelper.isNotEmptyString(taskVo.getAttachmentStr())) { // 附件
				//attachmentStr字符串，是附件的信息，每个附件信息使用|隔开，附件不同的信息用逗号隔开
				String[] attachmentStrArray = taskVo.getAttachmentStr().substring(0, taskVo.getAttachmentStr().length() - 1).split("\\|");
				
				for (int k = 0; k < attachmentStrArray.length; k++) {
					String[] attachmentArray = attachmentStrArray[k].split(",");
					TaskAttachmentVo taskAttachmentVo = new TaskAttachmentVo();
					taskAttachmentVo.setCreatorId(taskVo.getCreatorId()); //上传人id
					taskAttachmentVo.setTaskId(taskVo.getId());
					taskAttachmentVo.setFileUrl(attachmentArray[0]); //附件url
					taskAttachmentVo.setFileName(attachmentArray[1]); //附件原名称
					taskAttachmentVo.setFileSize(Double.valueOf(attachmentArray[2]));//附件大小

					createTaskAttachment(taskAttachmentVo); 

				}
			}
		}
	}

	@Override
	@Transactional
	public void updateTask(TaskVo taskVo) {
		TaskContext taskContext = TaskContext.build(taskVo);
		taskContext.update();

		if (ValidateHelper.isNotEmptyString(taskVo.getAttachmentStr())) { // 附件
			//attachmentStr字符串，是附件的信息，每个附件信息使用|隔开，附件不同的信息用逗号隔开
			String[] attachmentStrArray = taskVo.getAttachmentStr().substring(0, taskVo.getAttachmentStr().length() - 1).split("\\|");
			
			for (int k = 0; k < attachmentStrArray.length; k++) {
				String[] attachmentArray = attachmentStrArray[k].split(",");
				
				TaskAttachmentVo taskAttachmentVo = new TaskAttachmentVo();
				taskAttachmentVo.setCreatorId(taskVo.getUploadId());//上传人id
				taskAttachmentVo.setTaskId(taskVo.getId());
				taskAttachmentVo.setFileUrl(attachmentArray[0]); //附件url
				taskAttachmentVo.setFileName(attachmentArray[1]); //附件原名称
				taskAttachmentVo.setFileSize(Double.valueOf(attachmentArray[2])); //附件大小

				createTaskAttachment(taskAttachmentVo);

			}
		}
	}

	@Override
	public TaskPrivilegeVo createTaskPrivilege(TaskPrivilegeVo taskPrivilegeVo) {
		TaskPrivilegeContext taskPrivilegeContext = TaskPrivilegeContext.build(taskPrivilegeVo);
		return taskPrivilegeContext.create();
	}

	@Override
	public List<TaskPrivilegeVo> findTaskPrivilegeByTaskId(String taskId) {
		TaskPrivilegeContext taskPrivilegeContext = TaskPrivilegeContext.loadById(taskId);
		return taskPrivilegeContext.getByTaskid();
	}

	@Override
	public void deleteTaskPrivilegeByTaskId(String taskId) {
		TaskPrivilegeContext taskPrivilegeContext = TaskPrivilegeContext.loadById(taskId);
		taskPrivilegeContext.deleteByTaskId();
	}

	@Override
	public Long findCountByStatusAndStaffId(String status, String staffId) {
		TaskContext taskContext = TaskContext.build();
		
		return taskContext.findCountByStatusAndStaffId(status, staffId);
	}

	@Override
	public DataPageValue<TaskVo> findTaskByPage(String status, String staffId, Integer page, Integer size) {
		TaskContext taskContext = TaskContext.build();
		return taskContext.findByPage(status, staffId, page, size);
	}

	@Override
	public List<TaskVo> findTaskByStaffIdAndSize(String staffId, Integer size) {
		TaskContext taskContext = TaskContext.build();
		return taskContext.findByStaffIdAndSize(staffId, size);
	}

	@Override
	public TaskAttachmentVo createTaskAttachment(TaskAttachmentVo attachmentVo) {
		TaskAttachmentContext attachmentContext = TaskAttachmentContext.build(attachmentVo);
		
		return attachmentContext.create();
	}

	@Override
	public List<TaskAttachmentVo> findAttachmentByTaskId(String taskId) {
		TaskAttachmentContext attachmentContext = TaskAttachmentContext.loadById(taskId);
		return attachmentContext.findByTaskId();
	}

	@Override
	public void deleteAttachmentByTaskId(String taskId) {
		TaskAttachmentContext attachmentContext = TaskAttachmentContext.loadById(taskId);
		attachmentContext.deleteByTaskId();
	}

	@Override
	@Transactional
	public NoticeVo createNotice(NoticeVo notice) {
		NoticeContext noticeContext = NoticeContext.build(notice);
		NoticeVo noticeVo =  noticeContext.create();
		
		if(noticeVo.getStaffMessage().trim().length()>0){   //通知的员工权限
			//每个权限使用逗号隔开，权限的具体信息使用|隔开
			String[] staffArray = noticeVo.getStaffMessage().split(",");
			
			for(int i=0; i<staffArray.length; i++){
				String[] staff = staffArray[i].split("\\|");
				NoticePrivilegeVo noticePrivilegeVo = new NoticePrivilegeVo();
				
				noticePrivilegeVo.setType(WorkbenchConstants.NoticePrivilegeType.STAFF); // 1 代表员工权限
				noticePrivilegeVo.setNoticeId(noticeVo.getId());
				
				if("0".equals(staff[1])){    // 代表权限是某个项目下的所有人（包括员工、业主、住户）
					noticePrivilegeVo.setProjectId(staff[0]);
					noticePrivilegeVo.setGroupId("-1");
					createNoticePrivilege(noticePrivilegeVo);
				}else{  //代表权限是有某个项目管理权限的，并且是某个部门的所有员工
					noticePrivilegeVo.setProjectId(staff[0]);
					noticePrivilegeVo.setGroupId(staff[1]);
					createNoticePrivilege(noticePrivilegeVo);
				}
				
			}
		}
		
		if(noticeVo.getProprietorMessage().trim().length()>0){  //业主、住户
			//每个权限使用逗号隔开，权限的具体信息使用|隔开
			String[] proprietorArray = noticeVo.getProprietorMessage().split(",");
			for(int i=0; i<proprietorArray.length; i++){
				String[] proprietor = proprietorArray[i].split("\\|");
				NoticePrivilegeVo noticePrivilegeVo = new NoticePrivilegeVo();
				noticePrivilegeVo.setType(WorkbenchConstants.NoticePrivilegeType.RESIDENT); //2 代表业主、住户
				noticePrivilegeVo.setNoticeId(noticeVo.getId());
				
				if("0".equals(proprietor[1])){  //某个项目下的所有业主、住户
					noticePrivilegeVo.setProjectId(proprietor[0]);
					noticePrivilegeVo.setGroupId("-1");
					createNoticePrivilege(noticePrivilegeVo);
				}else{ //某个项目、某个楼栋的所有业主、住户
					noticePrivilegeVo.setProjectId(proprietor[0]);
					noticePrivilegeVo.setGroupId(proprietor[1]);
					createNoticePrivilege(noticePrivilegeVo);
				}
				
			}
		}
		return noticeVo;
	}

	@Override
	@Transactional
	public NoticeVo updateNotice(NoticeVo notice) {
		NoticeContext noticeContext = NoticeContext.build(notice);
		NoticeVo noticeVo = noticeContext.update();
		
		if(ValidateHelper.isNotEmptyString(noticeVo.getStaffMessage())){   //员工
			//每个权限使用逗号隔开，权限的具体信息使用|隔开
			String[] staffArray = noticeVo.getStaffMessage().split(",");
			for(int i=0; i<staffArray.length; i++){
				String[] staff = staffArray[i].split("\\|");
				NoticePrivilegeVo noticePrivilegeVo = new NoticePrivilegeVo();
				noticePrivilegeVo.setType(WorkbenchConstants.NoticePrivilegeType.STAFF); // 1 代表员工权限
				noticePrivilegeVo.setNoticeId(noticeVo.getId());
				
				if("0".equals(staff[1])){ // 代表权限是某个项目下的所有人（包括员工、业主、住户）
					noticePrivilegeVo.setProjectId(staff[0]);
					noticePrivilegeVo.setGroupId("-1");
					createNoticePrivilege(noticePrivilegeVo);
				}else{//代表权限是有某个项目管理权限的，并且是某个部门的所有员工
					noticePrivilegeVo.setProjectId(staff[0]);
					noticePrivilegeVo.setGroupId(staff[1]);
					createNoticePrivilege(noticePrivilegeVo);
				}
				
			}
		}
		if(ValidateHelper.isNotEmptyString(noticeVo.getProprietorMessage())){  //业主、住户
			//每个权限使用逗号隔开，权限的具体信息使用|隔开
			String[] proprietorArray = noticeVo.getProprietorMessage().split(",");
			for(int i=0; i<proprietorArray.length; i++){
				String[] proprietor = proprietorArray[i].split("\\|");
				NoticePrivilegeVo noticePrivilegeVo = new NoticePrivilegeVo();
				noticePrivilegeVo.setType(WorkbenchConstants.NoticePrivilegeType.RESIDENT);//2 代表业主、住户
				noticePrivilegeVo.setNoticeId(noticeVo.getId());
				
				if("0".equals(proprietor[1])){ //某个项目下的所有业主、住户
					noticePrivilegeVo.setProjectId(proprietor[0]);
					noticePrivilegeVo.setGroupId("-1");
					createNoticePrivilege(noticePrivilegeVo);
				}else{ //某个项目、某个楼栋的所有业主、住户
					noticePrivilegeVo.setProjectId(proprietor[0]);
					noticePrivilegeVo.setGroupId(proprietor[1]);
					createNoticePrivilege(noticePrivilegeVo);
				}
				
			}
		}
			
		return noticeVo;
	}

	@Override
	public NoticeVo getNoticeById(String id) {
		NoticeContext noticeContext = NoticeContext.loadById(id);
		return noticeContext.getById();
	}

	@Override
	public NoticePrivilegeVo createNoticePrivilege(NoticePrivilegeVo noticePrivilegeVo) {
		NoticePrivilegeContext noticePrivilegeContext = NoticePrivilegeContext.build(noticePrivilegeVo);
		
		return noticePrivilegeContext.create();
	}

	@Override
	public List<NoticePrivilegeVo> getNoticePrivilegeByNoticeId(String noticeId) {
		NoticePrivilegeContext noticePrivilegeContext = NoticePrivilegeContext.loadById(noticeId);
		return noticePrivilegeContext.findByNoticeId();
	}

	@Override
	public void deleteNoticePrivilegeByNoticeId(String noticeId) {
		NoticePrivilegeContext noticePrivilegeContext = NoticePrivilegeContext.loadById(noticeId);
		
		 noticePrivilegeContext.deleteByNoticeId();
	}

	@Override
	public Long findNoticeCount(String status, String staffId,
			String manageProject) {
		NoticeContext noticeContext = NoticeContext.build();
		
		return noticeContext.queryByCount(status, staffId, manageProject);
	}

	@Override
	public DataPageValue<NoticeVo> findNoticeByPage(String status, String staffId, String manageProject, String orderBy, Integer page, Integer size) {
		NoticeContext noticeContext = NoticeContext.build();
		
		return noticeContext.findByPage(status, staffId, manageProject, orderBy, page, size);
	}

	@Override
	public Long findNoticeCountOfPublished(String staffId,
			String manageProject, String deptId) {
		NoticeContext noticeContext = NoticeContext.build();
		
		return noticeContext.queryByCountOfPublished(staffId, manageProject, deptId);
	}

	@Override
	public DataPageValue<NoticeVo> findNoticeByPageOfPublished(String staffId, String manageProject, String deptId, Integer page,
			Integer size) {
		NoticeContext noticeContext = NoticeContext.build();
		
		return noticeContext.findByPageOfPublished(staffId, manageProject, deptId, page, size);
	}

	@Override
	public List<NoticeVo> findNoticeVoByStatus(String status) {
		NoticeContext noticeContext = NoticeContext.build();
		return noticeContext.findByStatus(status);
	}

	@Override
	public void autoCheckNotice(String status){
		try {
			logger.info("***开始检测状态：{} 通知******", status);
			Date nowTime = new Date();

			// 获取该状态的所有通知
			List<NoticeVo> noticeVos = findNoticeVoByStatus(status);

			for (NoticeVo noticeVo : noticeVos) {
				if (WorkbenchConstants.NoticeStatus.PUBLISHING.equals(status)) { // “待发布”状态
					if (!nowTime.before(noticeVo.getStartTime())) { // 到了发布时间，将“待发布”状态的通知发布
						noticeVo.setPublisherId("0"); // 0代表系统自动发布
						noticeVo.setPublishTime(new Date());
						noticeVo.setStatus(WorkbenchConstants.NoticeStatus.ARCHIVING); // 改为“待归档”状态
						updateNotice(noticeVo);

						logger.info("***将通知：title{} 发布******", noticeVo.getTitle());
					}
				} else if (WorkbenchConstants.NoticeStatus.ARCHIVING.equals(status)) { // “待归档”状态
					if (!nowTime.before(noticeVo.getEndTime())) { // 将过了“公布时效”的通知进行归档
						noticeVo.setArchiverId("0"); // 0代表有系统自动归档
						noticeVo.setArchiveTime(new Date());
						noticeVo.setStatus(WorkbenchConstants.NoticeStatus.ARCHIVED); // 改为“已归档”状态
						updateNotice(noticeVo);

						logger.info("***将通知：title{} 归档******", noticeVo.getTitle());
					}
				} else { // "待审核”、“审核不通过”状态
					if (!nowTime.before(noticeVo.getStartTime())) { // 到了发布时间、将“待审核”、“审核不通过”的通知归档
						noticeVo.setArchiverId("0"); // 0代表系统自动归档
						noticeVo.setArchiveTime(new Date());
						noticeVo.setStatus(WorkbenchConstants.NoticeStatus.ARCHIVED); // 改为“已归档”状态
						updateNotice(noticeVo);
						logger.info("***将通知：title{} 归档******", noticeVo.getTitle());
					}
				}
			}

			logger.info("***结束检测状态：{} 通知******", status);

		} catch (Exception e) {
			logger.error("***状态：{} 通知自动检测失败******", status, e);
		}
	}

	@Override
	public ScheduleVo findScheduleById(String id) {
		ScheduleContext scheduleContext = ScheduleContext.loadById(id);
		return scheduleContext.getById();
	}

	@Override
	public ScheduleVo createSchedule(ScheduleVo scheduleVo) {
		ScheduleContext scheduleContext = ScheduleContext.build(scheduleVo);
		return scheduleContext.create();
	}

	@Override
	public ScheduleVo updateSchedule(ScheduleVo scheduleVo) {
		ScheduleContext scheduleContext = ScheduleContext.build(scheduleVo);
		return scheduleContext.update();
	}

	@Override
	public void deleteScheduleById(String id) {
		ScheduleContext scheduleContext = ScheduleContext.loadById(id);
		scheduleContext.deleteById();
	}

	@Override
	public void deleteScheduleByCreatorId(String creatorId) {
		ScheduleContext scheduleContext = ScheduleContext.build();
		scheduleContext.deleteByCreatorId(creatorId);
	}

	@Override
	public List<ScheduleVo> findScheduleByCreatorIdAndQueryDate(
			String creatorId, String queryDate) {
		ScheduleContext scheduleContext = ScheduleContext.build();
		return scheduleContext.findByCreatorIdAndQueryDate(creatorId, queryDate);
	}

	@Override
	@Transactional
	public WebsiteMsgVo createWebsiteMsgVo(WebsiteMsgVo websiteMsg) {
		WebsiteMsgContext websiteMsgContext = WebsiteMsgContext.build(websiteMsg);
		WebsiteMsgVo websiteMsgVo = websiteMsgContext.create();
		if (websiteMsgVo != null) { // 创建消息的权限 
			if (ValidateHelper.isNotEmptyString(websiteMsgVo.getPrivilegeStr())) { // 权限
				//每个权限使用逗号隔开，权限的具体信息使用|隔开
				String[] privilegeArray = websiteMsgVo.getPrivilegeStr().split(",");
				for(int i=0; i<privilegeArray.length; i++){
					String[] privilege = privilegeArray[i].split("\\|");
					WebsiteMsgPrivilegeVo websiteMsgPrivilegeVo= new WebsiteMsgPrivilegeVo();
					websiteMsgPrivilegeVo.setMessageId(websiteMsgVo.getId());
					
					if("0".equals(privilege[1])){ // 代表权限是某个项目下的所有人
						websiteMsgPrivilegeVo.setProjectId(privilege[0]);
						websiteMsgPrivilegeVo.setDepartmentId("-1");
					}else{
						if("0".equals(privilege[2])){//代表权限是有某个项目管理权限的，并且是某个部门的所有员工
							websiteMsgPrivilegeVo.setProjectId(privilege[0]);
							websiteMsgPrivilegeVo.setDepartmentId(privilege[1]);
							websiteMsgPrivilegeVo.setStaffId("-1");
						}else{
							websiteMsgPrivilegeVo.setProjectId(privilege[0]);
							websiteMsgPrivilegeVo.setDepartmentId(privilege[1]);
							websiteMsgPrivilegeVo.setStaffId(privilege[2]);
						}
					}
				
					createWebsiteMsgPrivilege(websiteMsgPrivilegeVo);
				}

			}

		}
		
		return websiteMsgVo;
	}

	@Override
	public WebsiteMsgVo findWebsiteMsgById(String id) {
		WebsiteMsgContext websiteMsgContext = WebsiteMsgContext.loadById(id);
		return websiteMsgContext.getById();
	}

	
	@Override
	@Transactional
	public void deleteWebsiteMsgById(String id) {
		//删除消息及其回复消息
		WebsiteMsgContext websiteMsgContext = WebsiteMsgContext.loadById(id);
		websiteMsgContext.delete();
		
		//删除消息的所有权限
		WebsiteMsgPrivilegeContext websiteMsgPrivilegeContext = WebsiteMsgPrivilegeContext.loadByMessageId(id);
		websiteMsgPrivilegeContext.deleteByMessageId();
	}

	@Override
	public WebsiteMsgPrivilegeVo createWebsiteMsgPrivilege(
			WebsiteMsgPrivilegeVo websiteMsgPrivilegeVo) {
		WebsiteMsgPrivilegeContext websiteMsgPrivilegeContext = WebsiteMsgPrivilegeContext.build(websiteMsgPrivilegeVo);
		return websiteMsgPrivilegeContext.create();
	}

	@Override
	public List<WebsiteMsgPrivilegeVo> findWebsiteMsgPrivilegeByMessageId(
			String messageId) {
		WebsiteMsgPrivilegeContext websiteMsgPrivilegeContext = WebsiteMsgPrivilegeContext.loadByMessageId(messageId);
		return websiteMsgPrivilegeContext.findByMessageId();
	}

	@Override
	public void deleteWebsiteMsgPrivilegeByMessageId(String messageId) {
		WebsiteMsgPrivilegeContext websiteMsgPrivilegeContext = WebsiteMsgPrivilegeContext.loadByMessageId(messageId);
		websiteMsgPrivilegeContext.deleteByMessageId();
	}

	@Override
	public Long findWebsiteMsgCount(String type, String staffId, String deptId,
			String manageProject) {
		WebsiteMsgContext websiteMsgContext = WebsiteMsgContext.build();
		return websiteMsgContext.findCount(type, staffId, deptId, manageProject);
	}

	@Override
	public DataPageValue<WebsiteMsgVo> findWebsiteMsgByPage(String type,
			String staffId, String deptId, String manageProject, Integer page,
			Integer size) {
		WebsiteMsgContext websiteMsgContext = WebsiteMsgContext.build();

		return websiteMsgContext.findByPage(type, staffId, deptId, manageProject, page, size);
	}

	@Override
	public List<WebsiteMsgVo> getReplyMsgList(String parentId) {
		WebsiteMsgContext websiteMsgContext = WebsiteMsgContext.build();
		return websiteMsgContext.getReplyMsgList(parentId);
	}

	@Override
	public DataPageValue<WebsiteMsgVo> findWebsiteMsgByCreatorId(
			String creatorId, int page, int size) {
		WebsiteMsgContext websiteMsgContext = WebsiteMsgContext.build();
		return websiteMsgContext.findByCreatorId(creatorId, page, size);
	}

	@Override
	public DataPageValue<StaffContactsVo> findStaffContacts(
			String departmentId, int page, int size) {
		StaffContactsContext staffContactsContext = StaffContactsContext.build();
		return staffContactsContext.findByPage(departmentId, StaffConstants.StaffStatus.ACTIVE, StaffConstants.WorkStatus.IN_OFFICE, page, size);
	}

	@Override
	public Long findCountOfStaffContacts(String departmentId) {
		StaffContactsContext staffContactsContext = StaffContactsContext.build();

		return staffContactsContext.findCount(departmentId, StaffConstants.StaffStatus.ACTIVE, StaffConstants.WorkStatus.IN_OFFICE);
	}

	@Override
	public DataPageValue<ProprietorContactsVo> findProprietorContacts(
			String projectId, String buildingId, Integer page, Integer size) {
		ProprietorContactsContext proprietorContactsContext = ProprietorContactsContext.build();
		return proprietorContactsContext.findByPage(projectId, buildingId, HouseholdConstants.ProprietorStatus.ACTIVE, page, size);
	}

	@Override
	public Long findCountOfProprietorContacts(String projectId, String buildingId) {
		ProprietorContactsContext proprietorContactsContext = ProprietorContactsContext.build();

		return proprietorContactsContext.findCount(projectId, buildingId, HouseholdConstants.ProprietorStatus.ACTIVE);
	}
	
	/***********报事************/
	/**
	 * 报事列表查询
	 */
	@Override
	public DataPageValue<EventReportVo> listEventReport(EventReportBo eventReportBo,Integer page, Integer size){
		EventReportContext eventReportContext = EventReportContext.build();
		return eventReportContext.list(eventReportBo, page, size);
	}
	
	/**
	 * 创建报事
	 */
	@Override
	public void createEventReport(EventReportVo eventReportVo){
		EventReportContext eventReportContext = EventReportContext.build(eventReportVo);
		eventReportContext.create();
	}
	
	/**
	 * 修改报事
	 */
	@Override
	public void updateEventReport(EventReportVo eventReportVo){
		EventReportContext eventReportContext = EventReportContext.build(eventReportVo);
		eventReportContext.update();
	}
	
	@Override
	public EventProcessVo createEventProcess(EventProcessVo eventProcessVo){
		return EventProcessContext.build(eventProcessVo).create();
	}
	
	@Override
	public void updateEventProcess(EventProcessVo eventProcessVo){
		EventProcessContext.build(eventProcessVo).update();
	}
	
	/***********************附件*************************/
	
	@Override
	public void createEventProcAttach(EventProcAttachVo eventProcAttachVo){
		EventProcAttachContext.build(eventProcAttachVo).create();
	}
	
	@Override
	public void deleteEventProcAttach(String id){
		EventProcAttachContext.loadById(id).delete();
	}
	
	@Override
	public List<EventProcAttachVo> findEventProcAttachByEventProcessId(String eventProcessId){
		return EventProcAttachContext.build().findByEventProcessId(eventProcessId);
	}
	
	@Override
	public EventProcessVo findEventProcessByWfTaskId(String wfTaskId){
		EventProcessVo eventProcessVo = EventProcessContext.build().findByWfTaskId(wfTaskId);
		//设置当前办理人的显示名称
				if(ValidateHelper.isNotEmptyString(eventProcessVo.getCurrAccepterId())){
				PropertyStaffVo propertyStaffVo	= propertyStaffService.findPropertyStaffById4DP(eventProcessVo.getCurrAccepterId());
				eventProcessVo.setCurrAccepterName(propertyStaffVo.getDepartmentName()+"--"+propertyStaffVo.getPositionName()+"--"+propertyStaffVo.getName());
				}
				//设置下一步办理人显示名称
				eventProcessVo.setNextAccepterName(" ");
				if(ValidateHelper.isNotEmptyString(eventProcessVo.getNextAccepterId()))
				for (String accid : eventProcessVo.getNextAccepterId().split(",")) {
					PropertyStaffVo propertyStaffVo1	= propertyStaffService.findPropertyStaffById4DP(accid);
					eventProcessVo.setNextAccepterName(eventProcessVo.getNextAccepterName()+propertyStaffVo1.getDepartmentName()+"--"+propertyStaffVo1.getPositionName()+"--"+propertyStaffVo1.getName()+",");
				}
				eventProcessVo.setNextAccepterName(eventProcessVo.getNextAccepterName().substring(0,eventProcessVo.getNextAccepterName().length()-1));
				
				//设置协办人的显示名称
				eventProcessVo.setAssistAccepterName(" ");
				if(ValidateHelper.isNotEmptyString(eventProcessVo.getAssistAccepterIds()))
				for (String accid : eventProcessVo.getAssistAccepterIds().split(",")) {
					PropertyStaffVo propertyStaffVo1	= propertyStaffService.findPropertyStaffById4DP(accid);
					eventProcessVo.setAssistAccepterName(eventProcessVo.getAssistAccepterName()+propertyStaffVo1.getDepartmentName()+"--"+propertyStaffVo1.getPositionName()+"--"+propertyStaffVo1.getName()+",");
				}
				eventProcessVo.setAssistAccepterName(eventProcessVo.getAssistAccepterName().substring(0,eventProcessVo.getAssistAccepterName().length()-1));
				
				return eventProcessVo;
	}
	
	@Override
	public EventReportVo findEventReportByWfTaskId(String wfOrderId){
		return EventReportContext.build().findByWfOrderId(wfOrderId);
	}
	
	@Override
	public EventReportVo getEventReport(String id){
		EventReportContext eventReportContext = EventReportContext.loadById(id);
		return eventReportContext.get();
	}

	@Override
	public void pushMessage(String receiveUserType, String sendUserId, String receiveUserId, String content) {
		if(receiveUserType.equals(WorkbenchConstants.ReceiveUserType.TYPE_STAFF)){
			
		}else{
			if(ValidateHelper.isNotEmptyString(receiveUserId)){ //业主id字符串不为空
				String[] userIdArray = receiveUserId.split(",");
				if(userIdArray.length > 1){  //推送消息给多个用户
					List<String> receiveUserIdList = new ArrayList<String>();
					
					//将业主id转为app段的userid
					for(int i = 0; i < userIdArray.length; i++){
						UserVo userVo = userService.getUserByCustGlobalId(userIdArray[i]);
						if(userVo != null){
							receiveUserIdList.add(userVo.getId());
						}
					}
					ListUserMsg message = new ListUserMsg();
					message.setContent(content);
					message.setSendUserId(sendUserId);
					message.setReceiveUserIdList(receiveUserIdList);
					
					pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_PROPRIETOR, PushMsgConstants.TerminalType.MOBILE_PROPERTY, message);
				}else{
					UserVo userVo = userService.getUserByCustGlobalId(userIdArray[0]);
					if(userVo != null){
						SingleUserMsg message = new SingleUserMsg();
						message.setContent(content);
						message.setSendUserId(sendUserId);
						message.setReceiveUserId(userVo.getId());
						pushMsgService.push2Single(MessageEvent.PUSH_TO_PROPERTY_PROPRIETOR, PushMsgConstants.TerminalType.MOBILE_PROPERTY, message);
					}
				}
			}
		}
		
	}

	@Override
	public DataPageValue<NoticeVo> findNoticeOfPublished(String terminal,
			String noticeType, String projectId, String groupId,
			String privilegeType, Integer page, Integer size) {
		NoticeContext noticeContext = NoticeContext.build();
		return noticeContext.findOfPublished(terminal, noticeType, projectId, groupId, privilegeType, page, size);
	}
	
	/**************报事工单处理******************/
	//待办理
	@Override
	public DataPageValue<EventReportVo> getWaitingForEventReportList(EventReportBo eventReportBo,
			Integer page, Integer size){
		return EventReportContext.build().getWaitingForList(eventReportBo, page, size);
	}
	
	//待签收
	@Override
	public DataPageValue<EventReportVo> getSignForEventReporList(EventReportBo eventReportBo,
			Integer page, Integer size){
		return EventReportContext.build().getSignForList(eventReportBo, page, size);
	}
	
	//抢单列表
	@Override
	public DataPageValue<EventReportVo> getGrabEventReporList(EventReportBo eventReportBo,
			Integer page, Integer size){
		return EventReportContext.build().getGrabList(eventReportBo, page, size);
	}
	
	//流转中列表
	@Override
	public DataPageValue<EventReportVo> getFlowingEventReporList(EventReportBo eventReportBo,
			Integer page, Integer size){
		return EventReportContext.build().getFlowingList(eventReportBo, page, size);
	}
	
	@Override
	public DataPageValue<EventReportVo> getCompleteEventReporList(
			EventReportBo eventReportBo, Integer page, Integer size) {
		return EventReportContext.build().getCompleteList(eventReportBo, page, size);

	}
	
	//各种状态数量
	@Override
	public Map<String, Long> eventReporNoRead(EventReportBo eventReportBo){
		return EventReportContext.build().noRead(eventReportBo);
	}
	
	//派单
	@Override
	public void eventReporDistribute(EventReportVo eventReportVo,EventProcessVo eventProcessVo,String staffid,String nextAccepterId){
		Map<String, Object> arg = new HashMap<String, Object>();
		arg.put("distributeLeaflets", staffid);
		arg.put("dispatching", eventProcessVo.getNextAccepterId());
		Order order = workflowService.startInstanceByName(WorkbenchConstants.EventReport.EVENT_REPORT_FLOW_NAME, 0, staffid,arg);
		List<Task> tasks = workflowService.getActiveTasks(new QueryFilter().setOrderId(order.getId()));
		workflowService.execute(tasks.get(0).getId(), staffid, arg);
		eventReportVo.setWfOrderId(order.getId());
		EventReportContext.build(eventReportVo).update();
		
		eventProcessVo.setWfTaskId(tasks.get(0).getId());
		EventProcessContext.build(eventProcessVo).create();
	}
	
	
	
	@Override
	public List<EventProcessVo> getHisEventProcess(String orderId){
		List<EventProcessVo>  eventProcessVos =  EventProcessContext.build().getHis(orderId);
		for (EventProcessVo eventProcessVo : eventProcessVos) {
			
			//设置附件
			eventProcessVo.setAttachs(EventProcAttachContext.build().findByEventProcessId(eventProcessVo.getId()));
			
			//设置当前办理人的显示名称
			if(ValidateHelper.isNotEmptyString(eventProcessVo.getCurrAccepterId())){
				PropertyStaffVo propertyStaffVo	= propertyStaffService.findPropertyStaffById4DP(eventProcessVo.getCurrAccepterId());
				eventProcessVo.setCurrAccepterName(propertyStaffVo.getDepartmentName()+"--"+propertyStaffVo.getPositionName()+"--"+propertyStaffVo.getName());
			}
			
			//设置下一步办理人显示名称
			eventProcessVo.setNextAccepterName(" ");
			if(ValidateHelper.isNotEmptyString(eventProcessVo.getNextAccepterId()))
			for (String accid : eventProcessVo.getNextAccepterId().split(",")) {
				PropertyStaffVo propertyStaffVo1	= propertyStaffService.findPropertyStaffById4DP(accid);
				eventProcessVo.setNextAccepterName(eventProcessVo.getNextAccepterName()+propertyStaffVo1.getDepartmentName()+"--"+propertyStaffVo1.getPositionName()+"--"+propertyStaffVo1.getName()+",");
			}
			eventProcessVo.setNextAccepterName(eventProcessVo.getNextAccepterName().substring(0,eventProcessVo.getNextAccepterName().length()-1));
			
			//设置协办人的显示名称
			eventProcessVo.setAssistAccepterName(" ");
			if(ValidateHelper.isNotEmptyString(eventProcessVo.getAssistAccepterIds()))
			for (String accid : eventProcessVo.getAssistAccepterIds().split(",")) {
				PropertyStaffVo propertyStaffVo1	= propertyStaffService.findPropertyStaffById4DP(accid);
				eventProcessVo.setAssistAccepterName(eventProcessVo.getAssistAccepterName()+propertyStaffVo1.getDepartmentName()+"--"+propertyStaffVo1.getPositionName()+"--"+propertyStaffVo1.getName()+",");
			}
			eventProcessVo.setAssistAccepterName(eventProcessVo.getAssistAccepterName().substring(0,eventProcessVo.getAssistAccepterName().length()-1));

		}
		return eventProcessVos;
	}
	
	//当前活动的任务
	@Override
	public EventProcessVo getActiveEventProcess(String orderId,String staffid){
		EventProcessVo eventProcessVo = EventProcessContext.build().getActive(orderId,staffid);
		
		//设置附件
		eventProcessVo.setAttachs(EventProcAttachContext.build().findByEventProcessId(eventProcessVo.getId()));
		

		//设置当前办理人的显示名称
		if(ValidateHelper.isNotEmptyString(eventProcessVo.getCurrAccepterId())){
		PropertyStaffVo propertyStaffVo	= propertyStaffService.findPropertyStaffById4DP(eventProcessVo.getCurrAccepterId());
		eventProcessVo.setCurrAccepterName(propertyStaffVo.getDepartmentName()+"--"+propertyStaffVo.getPositionName()+"--"+propertyStaffVo.getName());
		}
		//设置下一步办理人显示名称
		eventProcessVo.setNextAccepterName(" ");
		if(ValidateHelper.isNotEmptyString(eventProcessVo.getNextAccepterId()))
		for (String accid : eventProcessVo.getNextAccepterId().split(",")) {
			PropertyStaffVo propertyStaffVo1	= propertyStaffService.findPropertyStaffById4DP(accid);
			eventProcessVo.setNextAccepterName(eventProcessVo.getNextAccepterName()+propertyStaffVo1.getDepartmentName()+"--"+propertyStaffVo1.getPositionName()+"--"+propertyStaffVo1.getName()+",");
		}
		eventProcessVo.setNextAccepterName(eventProcessVo.getNextAccepterName().substring(0,eventProcessVo.getNextAccepterName().length()-1));
		
		//设置协办人的显示名称
		eventProcessVo.setAssistAccepterName(" ");
		if(ValidateHelper.isNotEmptyString(eventProcessVo.getAssistAccepterIds()))
		for (String accid : eventProcessVo.getAssistAccepterIds().split(",")) {
			PropertyStaffVo propertyStaffVo1	= propertyStaffService.findPropertyStaffById4DP(accid);
			eventProcessVo.setAssistAccepterName(eventProcessVo.getAssistAccepterName()+propertyStaffVo1.getDepartmentName()+"--"+propertyStaffVo1.getPositionName()+"--"+propertyStaffVo1.getName()+",");
		}
		eventProcessVo.setAssistAccepterName(eventProcessVo.getAssistAccepterName().substring(0,eventProcessVo.getAssistAccepterName().length()-1));
		
		return eventProcessVo;
	}
	
	@Override
	public void executeEventReporFlow(EventReportVo eventReportVo,EventProcessVo eventProcessVo,String staffid,String nextAccepterId){
		
		if(ValidateHelper.isEmptyString(eventProcessVo.getTaskName())){
			logger.error("executeEventReporFlow taskName is Null or Empty");
			throw  new WorkbenchException(WorkbenchErrorCode.PARAM_IS_NULL);
		}
		if(ValidateHelper.isEmptyString(eventProcessVo.getWfTaskId())){
			logger.error("executeEventReporFlow taskId is Null or Empty");
			throw  new WorkbenchException(WorkbenchErrorCode.PARAM_IS_NULL);
		}
		
		//设置默认审核状态为通过
		if(eventProcessVo.getAuditStatus()==null){
			eventProcessVo.setAuditStatus("1");
		}
		
		//保存 附件
		if(ValidateHelper.isNotEmptyCollection(eventProcessVo.getAttachs()))
		for (EventProcAttachVo eventProcAttachVo : eventProcessVo.getAttachs()) {
			EventProcAttachContext.build(eventProcAttachVo).create();
		}
		
		//创建或者修改
		EventProcessContext eventProcessContext = EventProcessContext.build(eventProcessVo);
		if(ValidateHelper.isEmptyString(eventProcessVo.getId())){
			eventProcessContext.create();
		}else{
			eventProcessContext.update();
		}
		org.snaker.engine.entity.Process process = workflowService.getProcessByName(WorkbenchConstants.EventReport.EVENT_REPORT_FLOW_NAME);
		//获取下一个步骤的角色名称
		String role = workflowService.getNextTaskRole(process.getId(), eventProcessVo.getTaskName());
	
		Map<String, Object> arg = new HashMap<String, Object>();
		arg.put(role, nextAccepterId);
		//流程决策使用 该步骤主要使用在部门审批
		arg.put("auditStatus", eventProcessVo.getAuditStatus());
		if(eventProcessVo.getAuditStatus().equals("1")){
			arg.put("returnVisit", nextAccepterId);
		}
		//执行
		List<Task> tasks = workflowService.execute(eventProcessVo.getWfTaskId(), staffid, arg);
		
		if(eventProcessVo.getGrab()!=null && eventProcessVo.getGrab().equals("1")){ //是否抢单
			if(tasks!=null && tasks.size()>0){
				EventProcessVo eventProcess = new EventProcessVo();
				eventProcess.setGrab("1");
				eventProcess.setWfTaskId(tasks.get(0).getId());
				EventProcessContext.build(eventProcess).create();;
			}
		}
	}
	
	/*
	 * 签收
	 */
	@Override
	public void eventReporSignfor(String wfTaskId,String staffid){
		EventProcessContext eventProcessContext = EventProcessContext.build();
		EventProcessVo eventProcessVo = eventProcessContext.findByWfTaskId(wfTaskId);
		
		Task task = workflowService.findTaskById(wfTaskId);
		if(task == null ){
			throw new WorkbenchException(WorkbenchErrorCode.TASK_NOT_EXIST);
		}
		
		//判断是否存在处理，没有则创建
		if(eventProcessVo == null){
			eventProcessVo = new EventProcessVo();
			eventProcessVo.setWfTaskId(wfTaskId);
			eventProcessVo.setNextAccepterId(arrayToString(task.getActorIds()));
			eventProcessVo.setCurrAccepterId(staffid);
			EventProcessContext.build(eventProcessVo).create();
		}
		
		//判断是否已经签收
		if(eventProcessVo.getStatus() == null || eventProcessVo.getStatus().equals(WorkbenchConstants.EventReport.SIGNFOR_NO)){
			eventProcessVo.setStatus(WorkbenchConstants.EventReport.SIGNFOR_YES);
			
			List<String> removeStaffid = compare(task.getActorIds(),new String[]{staffid});
			
			//判断是否需要移除其他人
			if(removeStaffid.size()>0)
			workflowService.removeTaskActor(wfTaskId, (String[]) removeStaffid.toArray());
			
			EventProcessContext.build(eventProcessVo).update();
		}else{
			throw new WorkbenchException(WorkbenchErrorCode.ALREADY_SIGNFOR);
		}
	}
	
	//退回
	@Override
	public void eventReporSendBack(String wfTaskId){
		EventProcessContext eventProcessContext = EventProcessContext.build();
		EventProcessVo eventProcessVo = eventProcessContext.findByWfTaskId(wfTaskId);
		
		Task task = workflowService.findTaskById(wfTaskId);
		if(task == null ){
			throw new WorkbenchException(WorkbenchErrorCode.TASK_NOT_EXIST);
		}
		if(eventProcessVo.getStatus() != null && eventProcessVo.getStatus().equals(WorkbenchConstants.EventReport.SIGNFOR_YES)){
			eventProcessVo.setStatus(WorkbenchConstants.EventReport.SIGNFOR_NO); //将以签收改为待签收
			EventProcessContext.build(eventProcessVo).update();
			List<String> removeStaffid = compare(task.getActorIds(),eventProcessVo.getNextAccepterId().split(","));
			//判断是否需要添加其他人
			if(removeStaffid.size()>0)
			workflowService.addTaskActor(wfTaskId, (String[]) removeStaffid.toArray());
		}else{
			throw new WorkbenchException(WorkbenchErrorCode.ALREADY_SENDBACK);
		}
		
	}
	
	//撤回
	@Override
	public void eventReporWithdraw(String wfTaskId,String staffid){
		EventProcessVo eventProcessVo = EventProcessContext.build().findByWfTaskId(wfTaskId);
		Task task = workflowService.findTaskById(wfTaskId);
		if(task == null ){
			throw new WorkbenchException(WorkbenchErrorCode.TASK_NOT_EXIST);
		}
		
		if(eventProcessVo == null){
			eventProcessVo = new EventProcessVo();
			eventProcessVo.setWfTaskId(task.getId());
			eventProcessVo.setStatus(WorkbenchConstants.EventReport.SIGNFOR_YES);
			EventProcessContext.build(eventProcessVo).create();
		}
		
		//判断是否已经签收,签收了则无法撤回
		if(eventProcessVo.getStatus() == null || eventProcessVo.getStatus().equals(WorkbenchConstants.EventReport.SIGNFOR_NO)){
			eventProcessVo.setStatus(WorkbenchConstants.EventReport.SIGNFOR_YES);
			workflowService.withdrawTask(task.getParentTaskId(), staffid);
			EventProcessContext.build(eventProcessVo).update();
		 }else{
			throw new WorkbenchException(WorkbenchErrorCode.ALREADY_SIGNFOR);
		}
	}
	
	
	
	//TODO 字符串 移至core
	public static <T> List<T> compare(T[] t1, T[] t2) {
	    List<T> list1 = Arrays.asList(t1);
	    List<T> list2 = new ArrayList<T>();
	    for (T t : t2) {
	      if (!list1.contains(t)) {
	        list2.add(t);
	      }
	    }
	    return list2;
	}
	
	public static String arrayToString(String[] array){
		String string ="";
		for (int i = 0; i < array.length; i++) {
			string += array[i];
			if(array.length-1 < i){
				string += ",";
			}
		}
		return string;
	}

}
