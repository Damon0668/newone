package com.liefeng.property.domain.workbench;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.constant.WorkbenchConstants;
import com.liefeng.property.vo.workbench.MessagePrivilegeVo;
import com.liefeng.property.vo.workbench.MessageVo;
import com.liefeng.property.vo.workbench.NoticePrivilegeVo;
import com.liefeng.property.vo.workbench.NoticeVo;
import com.liefeng.property.vo.workbench.ScheduleVo;
import com.liefeng.property.vo.workbench.TaskAttachmentVo;
import com.liefeng.property.vo.workbench.TaskPrivilegeVo;
import com.liefeng.property.vo.workbench.TaskVo;


/**
 * 任务领域测试类
 * @author XHW
 * @date 2016年2月19日下午8:14:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class WorkbenchContextTest {
	
	@Autowired
	private IWorkbenchService workbenchService;
	
	@Before
	public void before(){
		ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
	}
	/**
	 * 根据taskId获取任务
	 * @author xhw
	 * @date 2016年2月19日
	 */
	@Test
	public void getTask(){
		
		TaskVo taskVo = workbenchService.findTaskById("1");
		System.out.println(taskVo);
	}
	
	/**
	 * 创建任务                       
	 * @author xhw
	 * @date 2016年2月20日
	 */
	@Test
	public void createTask(){
		TaskVo tv = new TaskVo();
		tv.setTitle("测试");
		tv.setContent("测试内容");
		tv.setStatus("1");
		tv.setPriority("11");
		tv.setStartTime(new Date());
		tv.setEndTime(new Date());
		tv.setCreatorId("1");
		tv.setPrivilegeStr("96|2|testall,3|1|0,2|0|0"); //接收人范围
		workbenchService.createTask(tv);
	}
	
	/**
	 * 更新任务
	 *                       
	 * @author xhw
	 * @date 2016年2月20日
	 */
	@Test
	public void updateTask(){
		TaskVo tv = new TaskVo();
		tv.setId("402889ba530be06f01530be06fb70000");
		tv.setTitle("测试2");
		tv.setContent("测试内容2");
		tv.setPriority("00");
		tv.setStartTime(new Date());
		tv.setEndTime(new Date());
		
		workbenchService.updateTask(tv);
	}
	
	/**
	 * 创建任务权限           
	 * @author xhw
	 * @date 2016年2月22日 下午4:43:42
	 */
	@Test
	public void createTaskPrivilege(){
		TaskPrivilegeVo tv = new TaskPrivilegeVo();
		tv.setTaskId("402889ba53082b620153082b62060000");
		workbenchService.createTaskPrivilege(tv);
	}
	
	/**
	 * 根据taskid，获取相应的任务权限                      
	 * @author xhw
	 * @date 2016年2月22日 下午4:52:33
	 */
	@Test
	public void getTaskPrivilegeBytaskId(){
		
		List<TaskPrivilegeVo> tvlist = workbenchService.findTaskPrivilegeByTaskId("402889ba530be06f01530be06fb70000");
		for(TaskPrivilegeVo tv:tvlist){
			System.out.println(tv);
		}
		
	}
	
	/**
	 * 根据taskid，删除相应的任务权限                      
	 * @author xhw
	 * @date 2016年2月22日 下午4:52:33
	 */
	@Test
	public void deleteTaskPrivilegeBytaskId(){
		workbenchService.deleteTaskPrivilegeByTaskId("402889ba53082b620153082b62060000");
	}
	
	/**
	 * 我的任务、分页查询
	 *                       
	 * @author xhw
	 * @date 2016年2月24日 上午9:28:29
	 */
	@Test
	public void findTaskByPage(){
		DataPageValue page  = workbenchService.findTaskByPage("", "40288901530c1a1d01530c1a1db40000", 1, 10);
		System.out.println(page);
	}
	
	/**
	 * 根据状态，员工id查询任务数量
	 *                       
	 * @author xhw
	 * @date 2016年2月24日 上午11:21:52
	 */
	@Test
	public void findCountByStautsAndStaffId(){
		Long count = workbenchService.findCountByStatusAndStaffId("", "40288901530c1a1d01530c1a1db40000");
		System.out.println(count);
	}
	
	/**
	 * 获取与员工有关的最近4条待处理、待审核的任务                      
	 * @author xhw
	 * @date 2016年2月25日 下午12:01:30
	 */
	@Test
	public void findTasks4ByStaffId(){
		List<TaskVo> taskVoList =  workbenchService.findTaskByStaffIdAndSize("402889015311068e015311068e3a0000", 4);
		System.out.println(taskVoList);
	}
	
	/**
	 * 创建任务附件
	 *                       
	 * @author xhw
	 * @date 2016年2月25日 下午7:28:12
	 */
	@Test
	public void createAttachment(){
		TaskAttachmentVo attachmentVo = new TaskAttachmentVo();
		attachmentVo.setCreatorId("402889015311068e015311068e3a0000");
		attachmentVo.setFileName("xhw.txt");
		attachmentVo.setFileSize(175.5);
		attachmentVo.setFileUrl("www.baidu.com");
		attachmentVo.setTaskId("402889ba530dd9dd01530ddaa5e50002");
		
		workbenchService.createTaskAttachment(attachmentVo);
	}
	
	/**
	 * 根据任务id，获取任务的附件
	 *                       
	 * @author xhw
	 * @date 2016年2月25日 下午7:36:20
	 */
	@Test
	public void findAttachmentByTaskId(){
		List<TaskAttachmentVo> attachmentVoList = workbenchService.findAttachmentByTaskId("402889ba530dd9dd01530ddaa5e50002");
		System.out.println(attachmentVoList);
	}
	
	/**
	 * 根据任务id，删除任务的附件
	 *                       
	 * @author xhw
	 * @date 2016年2月25日 下午7:39:15
	 */
	@Test
	public void deleteAttachmentByTaskId(){
		workbenchService.deleteAttachmentByTaskId("402889ba530dd9dd01530ddaa5e50002");
	}
	
	/**
	 * 创建通知
	 *                       
	 * @author xhw
	 * @date 2016年2月26日 下午5:18:23
	 */
	@Test
	public void createNotice(){
		NoticeVo noticeVo = new NoticeVo();
		noticeVo.setTitle("系统切割");
		noticeVo.setContent("通知模块切割");
		noticeVo.setImportant('1');
		noticeVo.setEmergency('1');
		noticeVo.setTerminal("0");
		noticeVo.setType("1");
		noticeVo.setStartTime(new Date());
		noticeVo.setEndTime(new Date());
		noticeVo.setCreateTime(new Date());
		noticeVo.setCreatorId("40282081531ca96a01531ca96a5a0000");
		workbenchService.createNotice(noticeVo);
	}
	
	/**
	 * 获取通知
	 * @author xhw
	 * @date 2016年2月26日 下午5:18:23
	 */
	@Test
	public void getNotice(){
		NoticeVo noticeVo = workbenchService.getNoticeById("402889d2531ceaec01531ceaec420000");
		System.out.println(noticeVo);
	}
	
	/**
	 * 跟新通知
	 *                       
	 * @author xhw
	 * @date 2016年2月26日 下午5:36:32
	 */
	@Test
	public void updateNotice(){
		NoticeVo noticeVo = new NoticeVo();
		noticeVo.setId("402889d2531ceaec01531ceaec420000");
		noticeVo.setTitle("系统切割2");
		noticeVo.setContent("通知模块切割2");
		noticeVo.setImportant('0');
		noticeVo.setEmergency('0');
		noticeVo.setTerminal("2");
		noticeVo.setType("3");
		noticeVo.setStartTime(new Date());
		noticeVo.setEndTime(new Date());
		noticeVo.setCreateTime(new Date());
		noticeVo.setCreatorId("40282081531ca96a01531ca96a5a0000");
		workbenchService.updateNotice(noticeVo);
	}
	
	/**
	 * 创建通知权限
	 *                       
	 * @author xhw
	 * @date 2016年2月26日 下午7:43:12
	 */
	@Test
	public void createNoticePrivilege(){
		NoticePrivilegeVo noticePrivilegeVo = new NoticePrivilegeVo();
		noticePrivilegeVo.setNoticeId("402889d2531ceaec01531ceaec420000");
		noticePrivilegeVo.setType("1");
		noticePrivilegeVo.setProjectId("1");
		noticePrivilegeVo.setGroupId("-1");
		workbenchService.createNoticePrivilege(noticePrivilegeVo);
	}
	
	/**
	 * 根据通知id，获取通知权限
	 *                       
	 * @author xhw
	 * @date 2016年2月26日 下午7:45:53
	 */
	@Test
	public void getNoticePrivilegeByNoticeId(){
		List<NoticePrivilegeVo> noticePrivilegeVos = workbenchService.getNoticePrivilegeByNoticeId("402889d2531ceaec01531ceaec420000");
		System.out.println(noticePrivilegeVos);
	}
	
	/**
	 * 根据通知id，删除相应的通知权限
	 *                       
	 * @author xhw
	 * @date 2016年2月26日 下午7:48:41
	 */
	@Test
	public void deleteNoticePrivilegeByNoticeId(){
		workbenchService.deleteNoticePrivilegeByNoticeId("402889d2531ceaec01531ceaec420000");
	}
	/**
	 * 获取通知的总数
	 *                       
	 * @author xhw
	 * @date 2016年2月29日 上午11:39:20
	 */
	@Test
	public void queryNoticeCount(){
		Long count = workbenchService.findNoticeCount("1", "", "0000000052a7943f0152a7943fc00000");
		System.out.println(count);
	}
	
	/**
	 * 查找通知（分页）
	 *                       
	 * @author xhw
	 * @date 2016年2月29日 上午11:48:12
	 */
	@Test
	public void queryNoticeBypage(){
		DataPageValue page  = workbenchService.findNoticeByPage("1", "", "0000000052a7943f0152a7943fc00000", WorkbenchConstants.NoticeOrderBy.CREATETIME, 1, 10);
		System.out.println(page);
	}
	
	/**
	 * 查找已发布通知的总数
	 *                       
	 * @author xhw
	 * @date 2016年2月29日 下午2:43:58
	 */
	@Test
	public void queryNoticeCountPulished(){
		Long count = workbenchService.findNoticeCountOfPublished("40282081531cf49b01531d3f4e1c0006", "0000000052a7943f0152a7943fc00000", "402881fb530cd2a501530cd2a5580000");
		System.out.println(count);
	}
	
	/**
	 * 查找已发布通知(分页)
	 *                       
	 * @author xhw
	 * @date 2016年2月29日 下午2:53:35
	 */
	@Test
	public void queryNoticeBypageOfPublished(){
		DataPageValue page  = workbenchService.findNoticeByPageOfPublished("40282081531cf49b01531d3f4e1c0006", "0000000052a7943f0152a7943fc00000", "402881fb530cd2a501530cd2a5580000", 1, 30);
		System.out.println(page);
	}
	
	/**
	 * 获取某个状态下，所有通知
	 *                       
	 * @author xhw
	 * @date 2016年3月1日 下午3:19:53
	 */
	@Test
	public void findNoticeByStatus(){
		List<NoticeVo> noticeVos = workbenchService.findNoticeVoByStatus("1");
		System.out.println(noticeVos);
	}
	
	/**
	 * 创建日程
	 * 
	 * @author xhw
	 * @2016年3月2日 上午9:29:34
	 */
	@Test
	public void createSchedule(){
		ScheduleVo scheduleVo = new ScheduleVo();
		scheduleVo.setContent("晚上一起吃饭");
		scheduleVo.setCreatorId("40282081531cf49b01531d3f4e1c0006");
		scheduleVo.setRepeats("1");
		scheduleVo.setScheduleDate(new Date());
		scheduleVo.setTimes(1);
		scheduleVo.setTitle("吃饭");
		
		workbenchService.createSchedule(scheduleVo);
	}
	
	/**
	 * 根据日程id，获取日程
	 * 
	 * @author xhw
	 * @2016年3月2日 上午9:59:33
	 */
	@Test
	public void findScheduleById(){
		ScheduleVo scheduleVo = workbenchService.findScheduleById("402889d253350ba80153350ba8100000");
		System.out.println(scheduleVo);
	}
	
	/**
	 * 更新日程
	 * 
	 * @author xhw
	 * @2016年3月2日 上午10:03:04
	 */
	@Test
	public void updateSchedule(){
		ScheduleVo scheduleVo = new ScheduleVo();
		scheduleVo.setId("402889d253350ba80153350ba8100000");
		scheduleVo.setContent("晚上一起吃饭2");
		scheduleVo.setCreatorId("40282081531cf49b01531d3f4e1c0006");
		scheduleVo.setRepeats("2");
		scheduleVo.setScheduleDate(new Date());
		scheduleVo.setTimes(3);
		scheduleVo.setTitle("吃饭3");
		workbenchService.updateSchedule(scheduleVo);
	}
	
	/**
	 * 根据日程id，删除日程
	 * 
	 * @author xhw
	 * @2016年3月2日 上午10:05:02
	 */
	@Test
	public void deleteScheduleById(){
		workbenchService.deleteScheduleById("402889d253350ba80153350ba8100000");
	}
	
	/**
	 * 根据创建人id，删除创建人所有日程
	 * 
	 * @author xhw
	 * @2016年3月2日 上午10:06:57
	 */
	@Test
	public void deleteScheduleByCreatorId(){
		workbenchService.deleteScheduleByCreatorId("40282081531cf49b01531d3f4e1c0006");
	}
	
	/**
	 * 通过创建人id、日期，查找该创建人在这一天的日程
	 * 
	 * @author xhw
	 * @2016年3月2日 下午2:11:25
	 */
	@Test
	public void findScheduleByCreatorIdAndQueryDate(){
		List<ScheduleVo> scheduleVos = workbenchService.findScheduleByCreatorIdAndQueryDate("40282081531cf49b01531d3f4e1c0006", "2016-03-02");
		System.out.println(scheduleVos);
	}
	
	/**
	 * 创建消息
	 * 
	 * @author xhw
	 * @2016年3月2日 下午4:21:00
	 */
	@Test
	public void createMessage(){
		MessageVo messageVo = new MessageVo();
		messageVo.setContent("吃饭啦");
		messageVo.setCreatorId("40282081531cf49b01531d3f4e1c0006");
		messageVo.setType("2");
		workbenchService.createMessageVo(messageVo);
	}
	
	/**
	 * 根据消息id，获取消息
	 * 
	 * @author xhw
	 * @2016年3月2日 下午4:45:07
	 */
	@Test
	public void findMessageById(){
		MessageVo messageVo = workbenchService.findMessageById("402889d253367eb70153367eb7710000");
		System.out.println(messageVo);
	}
	
	/**
	 * 根据消息id，删除消息
	 * 
	 * @author xhw
	 * @2016年3月2日 下午4:46:41
	 */
	@Test
	public void deleteMessageById(){
		workbenchService.deleteMessageById("402889d253367eb70153367eb7710000");
	}
	
	/**
	 * 创建消息权限
	 * 
	 * @author xhw
	 * @2016年3月2日 下午5:33:52
	 */
	@Test
	public void createMessagePrivilege(){
		MessagePrivilegeVo messagePrivilegeVo = new MessagePrivilegeVo();
		messagePrivilegeVo.setMessageId("402889d25336a818015336a818a70000");
		messagePrivilegeVo.setProjectId("0000000052a7943f0152a7943fc00000");
		messagePrivilegeVo.setGroupId("-1");
		messagePrivilegeVo.setType("1");
		workbenchService.createMessagePrivilege(messagePrivilegeVo);
	}
	
	/**
	 * 通过消息id，获取消息的权限
	 * 
	 * @author xhw
	 * @2016年3月2日 下午5:43:41
	 */
	@Test
	public void findMessagePrivilegeByMessageID(){
		List<MessagePrivilegeVo> messagePrivilegeVos = workbenchService.findMessagePrivilegeByMessageId("402889d25336a818015336a818a70000");
		System.out.println(messagePrivilegeVos);
	}
	
	/**
	 * 通过消息id，删除消息的权限
	 * 
	 * @author xhw
	 * @2016年3月2日 下午5:45:46
	 */
	@Test
	public void deleteMessagePrivilegeByMessageId(){
		workbenchService.deleteMessagePrivilegeByMessageId("402889d25336a818015336a818a70000");
	}
	
	/**
	 * 查询消息总数
	 * 
	 * @author xhw
	 * @2016年3月2日 下午8:44:42
	 */
	@Test
	public void findMessageCount(){
		Long countLong = workbenchService.findMessageCount("2", "40282081531cf49b01531d3f4e1c0006", "402881fb530cd2a501530cd2a5580000", "0000000052a7943f0152a7943fc00000");
		System.out.println(countLong);
	}
	
	/**
	 * 查消息（分页）
	 * 
	 * @author xhw
	 * @2016年3月2日 下午8:54:24
	 */
	@Test
	public void findMessageByPage(){
		DataPageValue<MessageVo> messageDataPageValue = workbenchService.findMessageByPage("2", "40282081531cf49b01531d3f4e1c0006", "402881fb530cd2a501530cd2a5580000", "0000000052a7943f0152a7943fc00000", 1, 30);
		System.out.print(messageDataPageValue);
	}
}
