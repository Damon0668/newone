package com.liefeng.property.domain.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.bo.project.HouseBo;
import com.liefeng.property.vo.project.HouseVo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class HouseContextTest {
	@Test
	public void create(){
		HouseVo vo = new HouseVo();
		vo.setId("levy");
		vo.setProjectId("projectId");
		vo.setBuildingId("buildingId");
		vo.setFloorId("floorId");
		vo.setSaleStatus("1");
		
		HouseContext context = HouseContext.build(vo);
		context.create();
	}
	
	@Test
	public void update(){
		HouseVo vo = new HouseVo();
		vo.setId("levy");
		vo.setProjectId("projectId");
		vo.setBuildingId("buildingId");
		vo.setFloorId("floorId");
		vo.setSaleStatus("1");
		
		vo.setOemCode("levy oemCode");
		HouseContext context = HouseContext.build(vo);
		context.update();
	}
	
	@Test
	public void query(){
		HouseContext context = HouseContext.getInstance();
		HouseBo houseBo = new HouseBo();
		houseBo.setProjectId("projectId");
		houseBo.setOemCode("oemCode");
		
		DataPageValue page = context.listHouse4Page(houseBo, 1, 100);
		System.out.println(page.getDataList());
	}
}
