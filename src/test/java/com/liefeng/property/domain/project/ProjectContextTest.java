package com.liefeng.property.domain.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.vo.project.ProjectVo;

/**
 * 项目领域测试类
 * @author Huangama
 * @author levy
 * @date 2015-12-22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProjectContextTest {
	//纯context与vo
	@Test
	public void create() {
		ProjectVo projectVo = new ProjectVo();
		projectVo.setCity("guangzhou");
		projectVo.setShortName("levy");
		projectVo.setFullName("levy full");
		ProjectContext context = ProjectContext.build(projectVo);
		context.create();
	}
	
	@Test
	public void update(){
		ProjectVo projectVo = new ProjectVo();
		projectVo.setCity("guangzhou");
		projectVo.setShortName("levy shot name");
		projectVo.setFullName("levy full name");
		projectVo.setId("4028810351f1a3120151f1a312520000");
		projectVo.setOemCode("oemCode");
		
		ProjectContext context = ProjectContext.build(projectVo);
		context.update();
		
	}
	
	@Test
	public void delete(){
		ProjectContext context = ProjectContext.loadById("4028810351f1a3120151f1a312520000");
		context.delete();
	}
	
	@Test
	public void findProjects(){
		
		ProjectContext context = ProjectContext.getInstance();
		ContextManager.getInstance().setOemCode("levy");
		//从0开始
		DataPageValue page = context.findProjects(0,5);
//		if(ValidateHelper.isNotEmptyCollection(list)){
//			System.out.println("true");
//		}
	}
}
