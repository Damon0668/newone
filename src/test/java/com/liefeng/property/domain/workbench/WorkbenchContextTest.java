package com.liefeng.property.domain.workbench;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.intf.property.IWorkbenchService;
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
		tv.setStart_time(new Date());
		tv.setEnd_time(new Date());
		tv.setCreator_id("1");
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
		tv.setId("402889ba52f9b5ca0152f9b5caf90000");
		tv.setTitle("测试2");
		tv.setContent("测试内容2");
		tv.setPriority("00");
		tv.setStart_time(new Date());
		tv.setEnd_time(new Date());
		
		workbenchService.updateTask(tv);
	}
}
