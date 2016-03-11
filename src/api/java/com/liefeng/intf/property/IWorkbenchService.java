package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.property.bo.workbench.EventReportBo;
import com.liefeng.property.vo.workbench.EventReportVo;
import com.liefeng.property.vo.workbench.ProprietorContactsVo;
import com.liefeng.property.vo.workbench.StaffContactsVo;
import com.liefeng.property.vo.workbench.WebsiteMsgPrivilegeVo;
import com.liefeng.property.vo.workbench.WebsiteMsgVo;
import com.liefeng.property.vo.workbench.NoticePrivilegeVo;
import com.liefeng.property.vo.workbench.NoticeVo;
import com.liefeng.property.vo.workbench.ScheduleVo;
import com.liefeng.property.vo.workbench.TaskAttachmentVo;
import com.liefeng.property.vo.workbench.TaskPrivilegeVo;
import com.liefeng.property.vo.workbench.TaskVo;


/**
 * 工作台接口
 * @author XHW
 * @date 2016年2月19日下午7:16:34
 */
public interface IWorkbenchService {

	/**
	 * 通过id获取任务
	 * @param taskId 任务id
	 * @return 具体任务对象              
	 * @author xhw
	 * @date 2016年2月19日
	 */
	public TaskVo findTaskById(String taskId);


	/**
	 * 创建任务
	 * @param taskVo  任务对象
	 * @throws LiefengException                      
	 * @author xhw
	 * @date 2016年2月19日
	 */
	public void createTask(TaskVo taskVo);

	/**
	 * 更新任务
	 * @param taskVo  任务对象                 
	 * @author xhw
	 * @date 2016年2月19日
	 */
	public void updateTask(TaskVo taskVo);
	
	/**
	 * 创建任务权限
	 * @param taskPrivilegeVo
	 * @return                      
	 * @author xhw
	 * @date 2016年2月22日 下午4:32:50
	 */
	public TaskPrivilegeVo createTaskPrivilege(TaskPrivilegeVo taskPrivilegeVo);

	/**
	 * 根据taskid，获取任务的相关任务权限
	 * @param taskId
	 * @return                      
	 * @author xhw
	 * @date 2016年2月22日 下午4:34:20
	 */
	public List<TaskPrivilegeVo> findTaskPrivilegeByTaskId(String taskId);
	
	/**
	 * 根据taskid，删除任务相关的任务权限
	 * @param taskId                      
	 * @author xhw
	 * @date 2016年2月22日 下午4:35:43
	 */
	public void deleteTaskPrivilegeByTaskId(String taskId);
	
	/**
	 * 根据状态，员工Id获取任务的数量
	 * @param status 状态
	 * @param staffId 员工Id
	 * @return                      
	 * @author xhw
	 * @date 2016年2月24日 上午10:45:00
	 */
	public Long findCountByStatusAndStaffId(String status, String staffId);
	
	/**
	 * 分页查询任务信息
	 * @param status 任务状态
	 * @param staffId 员工Id
	 * @param page 当前页
	 * @param size 每页数量
	 * @return                      
	 * @author xhw
	 * @date 2016年2月24日 上午10:47:17
	 */
	public DataPageValue<TaskVo> findTaskByPage(String status, String staffId,Integer page, Integer size);
	
	/**
	 * 获取与员工有关的最近几条待处理、待审核的任务
	 * @param staffId  员工id
	 * @return                      
	 * @author xhw
	 * @date 2016年2月25日 上午11:23:46
	 */
	public List<TaskVo> findTaskByStaffIdAndSize(String staffId, Integer size);
	
	/**
	 * 创建任务附件
	 * @param attachmentVo
	 * @return                      
	 * @author xhw
	 * @date 2016年2月25日 下午7:21:43
	 */
	public TaskAttachmentVo createTaskAttachment(TaskAttachmentVo attachmentVo);
	
	/**
	 * 根据任务id，获取任务的附件
	 * @param taskId
	 * @return                      
	 * @author xhw
	 * @date 2016年2月25日 下午7:23:10
	 */
	public List<TaskAttachmentVo> findAttachmentByTaskId(String taskId);
	
	/**
	 * 根据任务id，删除任务的附件
	 * @param taskId                      
	 * @author xhw
	 * @date 2016年2月25日 下午7:24:08
	 */
	public void deleteAttachmentByTaskId(String taskId);
	
	/**
	 * 创建通知
	 * @param noticeVo
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午4:47:01
	 */
	public NoticeVo createNotice(NoticeVo noticeVo);
	
	/**
	 * 更新通知
	 * @param noticeVo
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午4:47:57
	 */
	public NoticeVo updateNotice(NoticeVo noticeVo);
	
	/**
	 * 根据通知id，获取id
	 * @param id
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午4:48:58
	 */
	public NoticeVo getNoticeById(String id);
	
	/**
	 * 创建通知权限对象
	 * @param noticePrivilegeVo
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午7:16:26
	 */
	public NoticePrivilegeVo createNoticePrivilege(NoticePrivilegeVo noticePrivilegeVo);
	
	/**
	 * 根据通知id，获取相关的通知权限
	 * @param noticeId
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午7:22:49
	 */
	public List<NoticePrivilegeVo> getNoticePrivilegeByNoticeId(String noticeId);
	
	/**
	 * 根据通知id，删除相关的通知权限
	 * @param noticeId                      
	 * @author xhw
	 * @date 2016年2月26日 下午7:25:14
	 */
	public void deleteNoticePrivilegeByNoticeId(String noticeId);
	
	/**
	 * 根据状态、员工id，管理的项目，获取通知的总数
	 * @param status
	 * @param staffId
	 * @param manageProject
	 * @return                      
	 * @author xhw
	 * @date 2016年2月29日 上午11:26:12
	 */
	public Long findNoticeCount(String status, String staffId, String manageProject);
	
	/**
	 * 分页查询通知（分页、非“已发布”）
	 * @param status
	 * @param staffId
	 * @param manageProject
	 * @param orderBy
	 * @param page
	 * @param size
	 * @return                      
	 * @author xhw
	 * @date 2016年2月29日 上午11:28:53
	 */
	public DataPageValue<NoticeVo> findNoticeByPage(String status, String staffId, String manageProject, String orderBy, Integer page, Integer size);
	
	/**
	 * 根据状态、员工id，管理的项目，获取已发布通知的总数
	 * @param staffId
	 * @param manageProject
	 * @param deptId
	 * @return                      
	 * @author xhw
	 * @date 2016年2月29日 上午11:26:12
	 */
	public Long findNoticeCountOfPublished(String staffId, String manageProject, String deptId);
	
	/**
	 * 分页查询通知（分页、“已发布”）
	 * @param staffId
	 * @param manageProject
	 * @param orderBy
	 * @param page
	 * @param size
	 * @return                      
	 * @author xhw
	 * @date 2016年2月29日 上午11:28:53
	 */
	public DataPageValue<NoticeVo> findNoticeByPageOfPublished(String staffId, String manageProject, String deptId, Integer page, Integer size);
	
	/**
	 * 通过状态、查找相应的通知；
	 * @param status
	 * @return                      
	 * @author xhw
	 * @date 2016年3月1日 下午2:35:56
	 */
	public List<NoticeVo> findNoticeVoByStatus(String status);
	
	/**
	 * 根据状态，自动检测通知，如：到发布时间，还没有审核通过的通知，自动归档。
	 * @param status                      
	 * @author xhw
	 * @date 2016年3月1日 下午5:10:03
	 */
	public void autoCheckNotice(String status);
	
	/**
	 * 根据日程id，查找日程
	 * @param id 日程id
	 * @return
	 * @author xhw
	 * @2016年3月2日 上午9:18:17
	 */
	public ScheduleVo findScheduleById(String id);
	
	/**
	 * 创建日程
	 * @param scheduleVo
	 * @return
	 * @author xhw
	 * @2016年3月2日 上午9:19:09
	 */
	public ScheduleVo createSchedule(ScheduleVo scheduleVo);
	
	
	/**
	 * 跟新日程
	 * @param scheduleVo
	 * @return
	 * @author xhw
	 * @2016年3月2日 上午9:19:46
	 */
	public ScheduleVo updateSchedule(ScheduleVo scheduleVo);
	
	/**
	 * 根据日程id，删除日程
	 * @param id 日程id
	 * @author xhw
	 * @2016年3月2日 上午9:20:34
	 */
	public void deleteScheduleById(String id);
	
	/**
	 * 根据创建人id，删除该创建人的所有日程
	 * @param creatorId 创建人id
	 * @author xhw
	 * @2016年3月2日 上午9:21:34
	 */
	public void deleteScheduleByCreatorId(String creatorId);
	
	/**
	 * 通过创建人id、日期，查找该创建人在这一天的日程
	 * @param creatorId
	 * @param queryDate
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午2:09:44
	 */
	public List<ScheduleVo> findScheduleByCreatorIdAndQueryDate(String creatorId, String queryDate);
	
	/**
	 * 创建站内消息
	 * @param websiteMsgVo
	 * @return
	 * @author xhw
	 * @2016年3月3日 上午10:36:35
	 */
	public WebsiteMsgVo createWebsiteMsgVo(WebsiteMsgVo websiteMsgVo);
	
	/**
	 * 根据站内消息id，获取站内消息
	 * @param id 消息id
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午4:15:37
	 */
	public WebsiteMsgVo findWebsiteMsgById(String id);
	
	/**
	 * 根据站内消息id，删除站内消息
	 * @param id 站内消息id
	 * @author xhw
	 * @2016年3月2日 下午4:16:32
	 */
	public void deleteWebsiteMsgById(String id);
	
	/**
	 * 创建站内消息权限
	 * @param websiteMsgPrivilegeVo
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午5:17:50
	 */
	public WebsiteMsgPrivilegeVo createWebsiteMsgPrivilege(WebsiteMsgPrivilegeVo websiteMsgPrivilegeVo);
	
	/**
	 * 根据站内消息id，获取站内消息的权限
	 * @param messageId 消息id
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午5:19:41
	 */
	public List<WebsiteMsgPrivilegeVo> findWebsiteMsgPrivilegeByMessageId(String messageId);
	
	/**
	 * 根据站内消息id，删除站内消息的权限
	 * @param messageId 消息id
	 * @author xhw
	 * @2016年3月2日 下午5:20:41
	 */
	public void deleteWebsiteMsgPrivilegeByMessageId(String messageId);
	
	/**
	 * 获取站内消息总数
	 * @param type 消息类型：1：系统，2：个人
	 * @param staffId 员工id
	 * @param deptId  部门id
	 * @param manageProject 管理的项目
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午8:33:35
	 */
	public Long findWebsiteMsgCount(String type, String staffId, String deptId, String manageProject);
	
	/**
	 * 获取站内消息（分页）
	 * @param type 消息类型：1：系统，2：个人
	 * @param staffId 员工id
	 * @param deptId 部门id
	 * @param manageProject 管理的项目
	 * @param page
	 * @param size
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午8:35:57
	 */
	public DataPageValue<WebsiteMsgVo> findWebsiteMsgByPage(String type, String staffId, String deptId, String manageProject, Integer page, Integer size);
	
	/**
	 * 根据parentId，获取站内消息的回复信息
	 * @param parentId
	 * @return
	 * @author xhw
	 * @2016年3月3日 下午2:21:43
	 */
	public List<WebsiteMsgVo> getReplyMsgList(String parentId);
	
	/**
	 * 根据创建人id，获取创建人创建的消息（父消息）（分页）
	 * @param creatorId
	 * @param page 第几页，最小为1
	 * @param size 页面大小
	 * @return
	 * @author xhw
	 * @2016年3月3日 下午3:02:43
	 */
	public DataPageValue<WebsiteMsgVo> findWebsiteMsgByCreatorId(String creatorId, int page, int size);
	
	/**
	 * 查询员工通讯录
	 * @param departmentId 部门id
	 * @param status 状态：1：激活；2：注销
	 * @param workStatus 在职状态 1：在职；2：离职
	 * @param page
	 * @param size
	 * @return
	 * @author xhw
	 * @2016年3月4日 下午4:39:52
	 */
	public DataPageValue<StaffContactsVo> findStaffContacts(String departmentId, int page, int size);
	
	/**
	 * 获取员工通讯录中的人数
	 * @param departmentId 部门id
	 * @param status 状态：1：激活；2：注销
	 * @param workStatus 在职状态 1：在职；2：离职
	 * @return
	 * @author xhw
	 * @2016年3月4日 下午4:42:07
	 */
	public Long findCountOfStaffContacts(String departmentId);
	
	/**
	 * 查询业主通讯录（分页）
	 * @param projectId 项目id
	 * @param buildingId 楼栋id
	 * @param status 状态
	 * @param page
	 * @param size
	 * @return
	 * @author xhw
	 * @2016年3月4日 下午5:15:37
	 */
	public DataPageValue<ProprietorContactsVo> findProprietorContacts(String projectId, String buildingId, Integer page, Integer size);

	/**
	 * 业主通讯录总数
	 * @param projectId 项目id
	 * @param buildingId 楼栋id
	 * @param status 状态
	 * @return
	 * @author xhw
	 * @2016年3月4日 下午5:16:34
	 */
	public Long findCountOfProprietorContacts(String projectId, String buildingId);

	/**
	 * 报事列表查询
	 */
	public DataPageValue<EventReportVo> listEventReport(EventReportBo eventReportBo,
			Integer page, Integer size);

	/**
	 * 创建报事
	 */
	public void createEventReport(EventReportVo eventReportVo);

	/**
	 * 修改报事
	 */
	public void updateEventReport(EventReportVo eventReportVo);


	public EventReportVo getEventReport(String id);
	
	/**
	 * 推送消息
	 * @param receiveUserType 接收人类型（员工：1，业主：2）
	 * @param sendUserId 推送人id
	 * @param receiveUserId 接收人id字符串（多个接收人id，用逗号隔开）
	 * @param content 发送内容
	 * @author xhw
	 * @2016年3月7日 上午9:48:39
	 */
	public void pushMessage(String receiveUserType, String sendUserId, String receiveUserId, String content);
	
	/**
	 * 查看已发布通知（分页、app）
	 * @param terminal 接收端类型
	 * @param naticeType 通知类型
	 * @param projectId 项目id（员工：所管理的项目id字符串，业主：所在项目id）
	 * @param groupId （员工：部门id，业主：楼栋id）
	 * @param privilegeType 接收人类型（员工：1，业主：2）
	 * @param page
	 * @param size
	 * @return
	 * @author xhw
	 * @2016年3月7日 下午3:24:55
	 */
	public DataPageValue<NoticeVo> findNoticeOfPublished(String terminal, String noticeType, String projectId, String groupId, String privilegeType, Integer page, Integer size);
	
}
