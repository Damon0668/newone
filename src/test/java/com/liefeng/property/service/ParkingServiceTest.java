package com.liefeng.property.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.intf.property.IParkingService;
import com.liefeng.property.bo.parking.ParkingBo;
import com.liefeng.property.vo.parking.ParkingSingleRentalVo;
import com.liefeng.property.vo.parking.ParkingVo;

/**
 * 车位管理测试类
 * @author wuzhijng
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ParkingServiceTest {
	
	@Autowired
	private IParkingService parkingService;
	
	/**
	 * 创建车位
	 */
	@Test
	public void createParking(){
		ContextManager.getInstance().setOemCode("1");
		ParkingVo parkingVo = new ParkingVo();
		parkingVo.setProjectId("1");
		parkingVo.setArea(12.00);
		parkingVo.setBuildingId("2");
		parkingVo.setCode("1");
		parkingVo.setFloor("1");
		parkingVo.setManageFee(212.00);
		parkingVo.setNum("1233");
		parkingVo.setPreRentPrice(221.00);
		parkingVo.setPreSalePrice(231.00);
		parkingVo.setStatus("0");
		parkingVo.setSuitableCar("1");
		parkingService.createParking(parkingVo);
	}
	
	/**
	 * 修改车位信息
	 */
	@Test
	public void updateParking(){
		ParkingSingleRentalVo parkingSingleRentalVo = new ParkingSingleRentalVo();
		parkingSingleRentalVo.setId("4028895e5335f3c5015335f3c7770003");
		parkingSingleRentalVo.setProjectId("1");
		parkingSingleRentalVo.setArea(12.00);
		parkingSingleRentalVo.setBuildingId("2");
		parkingSingleRentalVo.setCode("1");
		parkingSingleRentalVo.setFloor("1");
		parkingSingleRentalVo.setManageFee(212.00);
		parkingSingleRentalVo.setNum("1233");
		parkingSingleRentalVo.setPreRentPrice(221.00);
		parkingSingleRentalVo.setPreSalePrice(231.00);
		parkingSingleRentalVo.setStatus("0");
		parkingSingleRentalVo.setSuitableCar("1");
		
		parkingService.updateParking(parkingSingleRentalVo);
	}
	
	/**
	 *	删除车位
	 */
	@Test
	public void deleteParking(){
		ContextManager.getInstance().setOemCode("1");
		parkingService.deleteParking("4028895e5335f8eb015335f8eb130000");
	}
	
	/**
	 * 获取车位详情
	 */
	@Test
	public void getParking(){
		ContextManager.getInstance().setOemCode("1");
		System.out.println(parkingService.getParking("4028895e5335f7b4015335f85bd40128"));
	}
	
	/**
	 * 获取车位信息列表
	 * @param parkingBo
	 * @return
	 */
	@Test
	public void getParkingList(){
		ParkingBo parkingBo = new ParkingBo();
		parkingBo.setProjectId("1");
		System.out.println(parkingService.getParkingList(parkingBo, 1, 30));
	}

	/**
	 * 批量创建车位
	 * @param parkingVo
	 * @param startNum
	 * @param endNum
	 */
	@Test
	public void createManyParking(){
		
		ContextManager.getInstance().setOemCode("1");
		ParkingVo parkingVo = new ParkingVo();
		parkingVo.setProjectId("1");
		parkingVo.setArea(12.00);
		parkingVo.setBuildingId("2");
		parkingVo.setCode("1");
		parkingVo.setFloor("1");
		parkingVo.setManageFee(212.00);
		parkingVo.setNum("1233");
		parkingVo.setPreRentPrice(221.00);
		parkingVo.setPreSalePrice(231.00);
		parkingVo.setStatus("0");
		parkingVo.setSuitableCar("1");
		parkingService.createManyParking(parkingVo,1,10);
	}

}
