package com.liefeng.property.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.constant.WorkbenchConstants;
import com.liefeng.property.domain.workbench.MessageContext;
import com.liefeng.property.domain.workbench.NoticeContext;
import com.liefeng.property.domain.workbench.NoticePrivilegeContext;
import com.liefeng.property.domain.workbench.ScheduleContext;
import com.liefeng.property.domain.workbench.TaskAttachmentContext;
import com.liefeng.property.domain.workbench.TaskContext;
import com.liefeng.property.domain.workbench.TaskPrivilegeContext;
import com.liefeng.property.vo.workbench.MessageVo;
import com.liefeng.property.vo.workbench.NoticePrivilegeVo;
import com.liefeng.property.vo.workbench.NoticeVo;
import com.liefeng.property.vo.workbench.ScheduleVo;
import com.liefeng.property.vo.workbench.TaskAttachmentVo;
import com.liefeng.property.vo.workbench.TaskPrivilegeVo;
import com.liefeng.property.vo.workbench.TaskVo;


/**
 * 任务服务实现类
 * @author XHW
 * @date 2016年2月19日下午7:36:30
 */
@Service
public class WorkbenchService implements IWorkbenchService {
	private static Logger logger = LoggerFactory.getLogger(WorkbenchService.class);
	@Override
	public TaskVo findTaskById(String taskId) {
		TaskContext taskContext = TaskContext.loadById(taskId);
		TaskVo taskVo = taskContext.getById();
		
		return taskVo;
	}

	@Override
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
				if (status.equals(WorkbenchConstants.NoticeStatus.PUBLISHING)) { // “待发布”状态
					if (!nowTime.before(noticeVo.getStartTime())) { // 到了发布时间，将“待发布”状态的通知发布
						noticeVo.setPublisherId("0"); // 0代表系统自动发布
						noticeVo.setPublishTime(new Date());
						noticeVo.setStatus(WorkbenchConstants.NoticeStatus.ARCHIVING); // 改为“待归档”状态
						updateNotice(noticeVo);

						logger.info("***将通知：title{} 发布******", noticeVo.getTitle());
					}
				} else if (status.equals(WorkbenchConstants.NoticeStatus.ARCHIVING)) { // “待归档”状态
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
	public MessageVo createMessageVo(MessageVo messageVo) {
		MessageContext messageContext = MessageContext.build(messageVo);
		return messageContext.create();
	}

	@Override
	public MessageVo findMessageById(String id) {
		MessageContext messageContext = MessageContext.loadById(id);
		return messageContext.getById();
	}

	@Override
	public void deleteMessageById(String id) {
		MessageContext messageContext = MessageContext.loadById(id);
		messageContext.deleteById();
	}
}
