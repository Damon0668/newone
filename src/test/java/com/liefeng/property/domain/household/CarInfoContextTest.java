package com.liefeng.property.domain.household;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.liefeng.Application;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.property.bo.household.CarInfoBo;
import com.liefeng.property.vo.household.CarInfoVo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("dev")
@WebAppConfiguration
public class CarInfoContextTest {
	
	@Before
	public void before(){
		ContextManager.getInstance().setOemCode("property");
	}
	
	@Test
	public void get() {
		CarInfoContext carInfoContext = CarInfoContext.loadById("1");
		System.out.println(carInfoContext.get());
	}
	
	@Test
	public void create() {
		CarInfoVo carInfoVo = new CarInfoVo();
		carInfoVo.setProjectId("0000000052a7943f0152a7943fc00000");
		carInfoVo.setOwnerId("402889bf5320ceae015320ceaee60000");
		carInfoVo.setOwnerName("甄庭俊");
		carInfoVo.setOwnerType("1");
		carInfoVo.setHouseNum("A0808");
		carInfoVo.setPlateNum("粤A 0808");
		carInfoVo.setBrand("奥迪");
		carInfoVo.setColor("黑色");
		carInfoVo.setVehicleType("02");
		
		CarInfoContext carInfoContext = CarInfoContext.build(carInfoVo);
		carInfoContext.create();
	}
	
	@Test
	public void update() {
		CarInfoVo carInfoVo = new CarInfoVo();
		carInfoVo.setId("402889af544ce9bf01544ce9bf3a0000");
		carInfoVo.setColor("白色");
		
		CarInfoContext carInfoContext = CarInfoContext.build(carInfoVo);
		carInfoContext.update();
	}
	
	@Test
	public void listCarInfo() {
		CarInfoBo params = new CarInfoBo();
		params.setOemCode("property");
		params.setProjectId("0000000052a7943f0152a7943fc00000");
		
		CarInfoContext carInfoContext = CarInfoContext.build();
		System.out.println(carInfoContext.listCarInfo(params, 1, 10));
		
	}
}
