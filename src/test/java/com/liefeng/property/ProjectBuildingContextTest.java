package com.liefeng.property;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.property.domain.project.ProjectBuildingContext;
import com.liefeng.property.domain.project.ProjectContext;
import com.liefeng.property.po.project.ProjectBuildingPo;
import com.liefeng.property.vo.project.ProjectBuildingVo;
import com.liefeng.property.vo.project.ProjectVo;

/**
 * 楼栋楼层领域测试
 * @author levy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProjectBuildingContextTest {
	@Test
	public void create() {
		ProjectBuildingVo vo  = new ProjectBuildingVo();
		vo.setId("id");
		vo.setCode("code");
		vo.setName("name");
		vo.setOemCode("oemCode");
		vo.setParentId("parentId");
		vo.setProjectId("projectId");
		vo.setRemark("remark");
		
		ProjectBuildingContext context = ProjectBuildingContext.build(vo);
		context.create();
	}
	
	@Test
	public void update(){
		ProjectBuildingVo vo  = new ProjectBuildingVo();
		vo.setId("id");
		vo.setCode("code");
		vo.setName("name");
		vo.setOemCode("oemCode");
//		vo.setParentId("parentId");
//		vo.setProjectId("projectId");
		vo.setRemark("remark");
		
		vo.setProjectId("levy");
		vo.setParentId(null);
		
		ProjectBuildingContext context = ProjectBuildingContext.build(vo);
		context.update();
		
	}
	
	@Test
	public void delete(){
		ProjectBuildingContext context = ProjectBuildingContext.loadById("id");
		context.delete();
	}
}
