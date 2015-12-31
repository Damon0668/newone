package com.liefeng.property.domain.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.property.domain.project.ProjectBuildingContext;
import com.liefeng.property.vo.project.ProjectBuildingVo;

/**
 * 楼栋楼层领域测试
 * @author levy
 * @date 2015年12月31日
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
	
	/**
	 * 查询楼栋
	 */
	@Test
	public void findBuildingsByProjectId(){
		String projectId = "projectId";
		
		ProjectBuildingContext context = ProjectBuildingContext.getInstance();
		Page page = context.findBuildingsByProjectId(projectId, new PageRequest(0, 5));
		System.out.println(page.getNumberOfElements() + " building");
	}
	
	/**
	 * 查询楼层
	 */
	@Test
	public void findFloorsByBuildingId(){
		String buildingId = "parentId";
		
		ProjectBuildingContext context = ProjectBuildingContext.getInstance();
		
		Page page = context.findFloorsByBuildingId(buildingId, new PageRequest(0, 5));
		
	}
	
}
