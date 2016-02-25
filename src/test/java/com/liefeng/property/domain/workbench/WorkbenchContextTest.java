package com.liefeng.property.domain.workbench;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IWorkbenchService;
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
	public void findTask4Page(){
		DataPageValue page  = workbenchService.findTask4Page("", "40288901530c1a1d01530c1a1db40000", 1, 10);
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
		List<TaskVo> taskVoList =  workbenchService.findTasks4ByStaffId("402889015311068e015311068e3a0000");
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
		List<TaskAttachmentVo> attachmentVoList = workbenchService.findAttachmentVoListByTaskId("402889ba530dd9dd01530ddaa5e50002");
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
}
