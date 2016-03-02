package com.liefeng.property.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IParkingService;
import com.liefeng.property.bo.parking.ParkingBo;
import com.liefeng.property.domain.parking.ParkingContext;
import com.liefeng.property.domain.parking.ParkingRentalContext;
import com.liefeng.property.vo.parking.ParkingRentalVo;
import com.liefeng.property.vo.parking.ParkingSingleRentalVo;
import com.liefeng.property.vo.parking.ParkingVo;

/**
 * 
 * <pre>
 * Title:车位相关表接口类
 * Description:
 * Company:广州列丰科技有限公司
 * @author  wuzhijing
 * @version 1.0      
 * </pre>
 */
@Service
public class ParkingService implements IParkingService {

	private static Logger logger = LoggerFactory.getLogger(ParkingServiceTest.class);

	@Override
	public void createParking(ParkingVo parkingVo) {
		ParkingContext parkingContext = ParkingContext.build(parkingVo);
		parkingContext.create();
	}
	
	@Override
	public void createManyParking(ParkingVo parkingVo,Integer startNum,Integer endNum) {
		ParkingContext parkingContext = ParkingContext.build(parkingVo);
		parkingContext.createMany(startNum,endNum);
	}

	@Override
	public void updateParking(ParkingSingleRentalVo parkingSingleRentalVo) {
		ParkingVo parkingVo = MyBeanUtil.createBean(parkingSingleRentalVo, ParkingVo.class);
		ParkingRentalVo parkingRentalVo = MyBeanUtil.createBean(parkingSingleRentalVo, ParkingRentalVo.class);
		parkingRentalVo.setId(parkingSingleRentalVo.getParkingRentalId());
		
		ParkingContext parkingContext = ParkingContext.build(parkingVo);
		parkingContext.update();
		
		ParkingRentalContext parkingRentalContext = ParkingRentalContext.build(parkingRentalVo);
		parkingRentalContext.update();
	}

	@Override
	public void deleteParking(String parkingId) {
		ParkingContext parkingContext = ParkingContext.loadById(parkingId);
		parkingContext.delete();
	}

	@Override
	public ParkingSingleRentalVo getParking(String parkingId) {
		ParkingContext parkingContext = ParkingContext.loadById(parkingId);
		return parkingContext.get();
	}

	@Override
	public DataPageValue<ParkingSingleRentalVo> getParkingList(
			ParkingBo parkingBo, Integer page, Integer size) {
		ParkingContext parkingContext = ParkingContext.build();
		
		return parkingContext.list(parkingBo, page, size);
	}

}
