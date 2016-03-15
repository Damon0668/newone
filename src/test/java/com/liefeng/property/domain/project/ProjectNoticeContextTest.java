package com.liefeng.property.domain.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.vo.project.ProjectNoticeVo;

/**
 * 小区通告领域模型测试类
 * 
 * @author ZhenTingJun
 * @date 2016年3月11日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("dev")
public class ProjectNoticeContextTest {
	
	@Test
	public void create() {
		ProjectNoticeVo projectNotice = new ProjectNoticeVo();
		projectNotice.setTitle("下水道消毒通知");
		projectNotice.setContent("今天小区将对下水道进行消毒");
		projectNotice.setProjectId("222");
		projectNotice.setStaffId("1");
		projectNotice.setImageUrl("www.baidui.com");
		
		ProjectNoticeContext projectNoticeContext = ProjectNoticeContext.build(projectNotice);
		projectNotice = projectNoticeContext.create();
		
		System.out.println(projectNotice);
	}
	
	@Test
	public void get() {
		ProjectNoticeContext projectNoticeContext = ProjectNoticeContext.loadById("1");
		ProjectNoticeVo projectNotice = projectNoticeContext.get();
		System.out.println(projectNotice);
	}
	
	@Test
	public void update() {
		ProjectNoticeVo projectNotice = new ProjectNoticeVo();
		projectNotice.setId("4028899a53653a330153653a33c60000");
		projectNotice.setTitle("下水道消毒通知");
		projectNotice.setContent("今天小区将对下水道进行消毒");
		projectNotice.setProjectId("222");
		projectNotice.setStaffId("1");
		projectNotice.setImageUrl("https://github.com");
		
		ProjectNoticeContext projectNoticeContext = ProjectNoticeContext.build(projectNotice);
		projectNotice = projectNoticeContext.update();
		
		System.out.println(projectNotice);
	}
	
	@Test
	public void findProjectNotices() {
		
		ProjectNoticeContext projectNoticeContext = ProjectNoticeContext.build();
		DataPageValue<ProjectNoticeVo> dataPage = projectNoticeContext.findProjectNotices("222", 1, 10);
		
		System.out.println(dataPage);
	}
}
