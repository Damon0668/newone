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
	
	/**
	 * 查询楼栋
	 */
	@Test
	public void findBuildingsByOemCodeAndProjectId(){
		ProjectBuildingVo vo  = new ProjectBuildingVo();
		vo.setOemCode("levy");
		vo.setProjectId("projectId");
		//parentId should be null
		vo.setParentId(null);
		
		ProjectBuildingContext context = ProjectBuildingContext.build(vo);
		Page page = context.findBuildingsByOemCodeAndProjectId(new PageRequest(0, 5));
		
	}
	
	/**
	 * 查询楼层
	 */
	@Test
	public void findFloorsByOemCodeAndProjectIdAndParentId(){
		ProjectBuildingVo vo  = new ProjectBuildingVo();
		vo.setOemCode("levy");
		vo.setProjectId("projectId");
		vo.setParentId("parentId"); // query floors
//		vo.setParentId(null);  query buildings
		
				ProjectBuildingContext context = ProjectBuildingContext.build(vo);
		Page page = context.findFloorsByOemCodeAndProjectIdAndParentId(new PageRequest(0, 5));
		
	}
	
}
