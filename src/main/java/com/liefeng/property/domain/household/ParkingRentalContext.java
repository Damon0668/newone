package com.liefeng.property.domain.household;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.household.ParkingRentalPo;
import com.liefeng.property.repository.ParkingRentalRepository;
import com.liefeng.property.vo.household.ParkingRentalVo;

/**
 * 车位租售信息领域模型
 * 
 * @author ZhenTingJun
 * @date 2015-12-25
 */
@Service
@Scope("prototype")
public class ParkingRentalContext {

	private static Logger logger = LoggerFactory.getLogger(ParkingRentalContext.class);
	
	@Autowired
	private ParkingRentalRepository parkingRentalRepository;
	
	/**
	 * 车位租售信息ID
	 */
	private String parkingRentalId;
	
	/**
	 * 车位租售信息值对象
	 */
	private ParkingRentalVo parkingRental;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	public static ParkingRentalContext getInstance(){
		return SpringBeanUtil.getBean(ParkingRentalContext.class);
	}
	
	/**
	 * 根据车位租售信息值对象构建上下文
	 * @param parkingRental 车位租售信息值对象
	 * @return 车位租售信息上下文
	 */
	public static ParkingRentalContext build(ParkingRentalVo parkingRental) {
		ParkingRentalContext parkingRentalContext = getInstance();
		parkingRentalContext.parkingRental = parkingRental;
		
		return parkingRentalContext;
	}
	
	/**
	 * 根据车位租售信息ID加载上下文
	 * @param parkingRentalId 车位租售信息ID
	 * @return 车位租售信息上下文
	 */
	public static ParkingRentalContext loadById(String parkingRentalId) {
		ParkingRentalContext parkingRentalContext = getInstance();
		parkingRentalContext.parkingRentalId = parkingRentalId;
		
		return parkingRentalContext;
	}
	
	/**
	 * 查询车位租售信息
	 * @return 车位租售信息值对象
	 */
	public ParkingRentalVo getParkingRental() {
		if(parkingRental == null) {
			ParkingRentalPo parkingRentalPo = null;
			if(ValidateHelper.isNotEmptyString(parkingRentalId)) {
				parkingRentalPo = parkingRentalRepository.findOne(parkingRentalId);
			}
			
			if(parkingRentalPo != null) {
				parkingRental = MyBeanUtil.createBean(parkingRentalPo, ParkingRentalVo.class);
			}
		}
		
		return parkingRental;
	}
	
	/**
	 * 保存车位租售信息
	 */
	public void create() {
		if(parkingRental != null) {
			parkingRental.setId(UUIDGenerator.generate());
			
			ParkingRentalPo parkingRentalPo = MyBeanUtil.createBean(parkingRental, ParkingRentalPo.class);
			parkingRentalRepository.save(parkingRentalPo);
		}
	}
}
