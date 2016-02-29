package com.liefeng.property.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.domain.workbench.NoticeContext;
import com.liefeng.property.domain.workbench.NoticePrivilegeContext;
import com.liefeng.property.domain.workbench.TaskAttachmentContext;
import com.liefeng.property.domain.workbench.TaskContext;
import com.liefeng.property.domain.workbench.TaskPrivilegeContext;
import com.liefeng.property.vo.workbench.NoticePrivilegeVo;
import com.liefeng.property.vo.workbench.NoticeVo;
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
				String[] privilegeArray = taskVo.getPrivilegeStr().split(",");
				for (int i = 0; i < privilegeArray.length; i++) {
					TaskPrivilegeVo privilegeVo = new TaskPrivilegeVo();
					privilegeVo.setTaskId(taskVo.getId());
					privilegeVo.setStaffId(privilegeArray[i]);
					createTaskPrivilege(privilegeVo);
				}

			}

			if (ValidateHelper.isNotEmptyString(taskVo.getAttachmentStr())) { // 附件
				String[] attachmentStrArray = taskVo.getAttachmentStr().substring(0, taskVo.getAttachmentStr().length() - 1).split("\\|");
				for (int k = 0; k < attachmentStrArray.length; k++) {
					String[] attachmentArray = attachmentStrArray[k].split(",");
					TaskAttachmentVo taskAttachmentVo = new TaskAttachmentVo();
					taskAttachmentVo.setCreatorId(taskVo.getCreatorId());
					taskAttachmentVo.setTaskId(taskVo.getId());
					taskAttachmentVo.setFileUrl(attachmentArray[0]);
					taskAttachmentVo.setFileName(attachmentArray[1]);
					taskAttachmentVo.setFileSize(Double.valueOf(attachmentArray[2]));

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
			String[] attachmentStrArray = taskVo.getAttachmentStr().substring(0, taskVo.getAttachmentStr().length() - 1).split("\\|");
			for (int k = 0; k < attachmentStrArray.length; k++) {
				String[] attachmentArray = attachmentStrArray[k].split(",");
				TaskAttachmentVo taskAttachmentVo = new TaskAttachmentVo();
				taskAttachmentVo.setCreatorId(taskVo.getUploadId());
				taskAttachmentVo.setTaskId(taskVo.getId());
				taskAttachmentVo.setFileUrl(attachmentArray[0]);
				taskAttachmentVo.setFileName(attachmentArray[1]);
				taskAttachmentVo.setFileSize(Double.valueOf(attachmentArray[2]));

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
		
		if(noticeVo.getStaffMessage().trim().length()>0){   //员工
			String[] staffArray = noticeVo.getStaffMessage().split(",");
			for(int i=0; i<staffArray.length; i++){
				String[] staff = staffArray[i].split("\\|");
				NoticePrivilegeVo noticePrivilegeVo = new NoticePrivilegeVo();
				noticePrivilegeVo.setType("1");
				noticePrivilegeVo.setNoticeId(noticeVo.getId());
				
				if("0".equals(staff[1])){
					noticePrivilegeVo.setProjectId(staff[0]);
					noticePrivilegeVo.setGroupId("-1");
					createNoticePrivilege(noticePrivilegeVo);
				}else{
					if("0".equals(staff[2])){
						noticePrivilegeVo.setProjectId(staff[0]);
						noticePrivilegeVo.setGroupId(staff[1]);
						noticePrivilegeVo.setNotifiederId("-1");
						createNoticePrivilege(noticePrivilegeVo);
					}else{
						noticePrivilegeVo.setProjectId(staff[0]);
						noticePrivilegeVo.setGroupId(staff[1]);
						noticePrivilegeVo.setNotifiederId(staff[2]);
						createNoticePrivilege(noticePrivilegeVo);
					}
				}
				
			}
			}
			if(noticeVo.getProprietorMessage().trim().length()>0){  //业主
			String[] proprietorArray = noticeVo.getProprietorMessage().split(",");
			for(int i=0; i<proprietorArray.length; i++){
				String[] proprietor = proprietorArray[i].split("\\|");
				NoticePrivilegeVo noticePrivilegeVo = new NoticePrivilegeVo();
				noticePrivilegeVo.setType("2");
				noticePrivilegeVo.setNoticeId(noticeVo.getId());
				
				if("0".equals(proprietor[1])){
					noticePrivilegeVo.setProjectId(proprietor[0]);
					noticePrivilegeVo.setGroupId("-1");
					createNoticePrivilege(noticePrivilegeVo);
				}else{
					if("0".equals(proprietor[2])){
						noticePrivilegeVo.setProjectId(proprietor[0]);
						noticePrivilegeVo.setGroupId(proprietor[1]);
						noticePrivilegeVo.setNotifiederId("-1");
						createNoticePrivilege(noticePrivilegeVo);
					}else{
						noticePrivilegeVo.setProjectId(proprietor[0]);
						noticePrivilegeVo.setGroupId(proprietor[1]);
						noticePrivilegeVo.setNotifiederId(proprietor[2]);
						createNoticePrivilege(noticePrivilegeVo);
					}
				}
				
			}
			}
		return noticeVo;
	}

	@Override
	public NoticeVo updateNotice(NoticeVo noticeVo) {
		NoticeContext noticeContext = NoticeContext.build(noticeVo);
		return noticeContext.update();
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


}
