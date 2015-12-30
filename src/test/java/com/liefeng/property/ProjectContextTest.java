package com.liefeng.property;

import com.liefeng.property.domain.project.ProjectContext;
import com.liefeng.property.vo.project.ProjectVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;

/**
 * 项目领域测试类
 * @author Huangama
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
}
