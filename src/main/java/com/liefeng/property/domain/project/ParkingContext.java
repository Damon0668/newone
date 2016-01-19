package com.liefeng.property.domain.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.project.ParkingPo;
import com.liefeng.property.repository.ParkingRepository;
import com.liefeng.property.vo.project.ParkingVo;

/**
 * 车位信息领域模型
 * 
 * @author ZhenTingJun
 * @date 2015-12-25
 */
@Service
@Scope("prototype")
public class ParkingContext {

	private static Logger logger = LoggerFactory.getLogger(ParkingContext.class);
	
	@Autowired
	private ParkingRepository parkingRepository;
	
	/**
	 * 车位信息ID
	 */
	private String parkingId;
	
	/**
	 * 车位信息值对象
	 */
	private ParkingVo parking;
	
	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	private static ParkingContext getInstance() {
		return SpringBeanUtil.getBean(ParkingContext.class);
	}
	
	/**
	 * 根据车位信息值对象构建上下文
	 * @param parking 车位信息值对象
	 * @return 车位信息上下文
	 */
	public static ParkingContext build(ParkingVo parking) {
		ParkingContext parkingContext = getInstance();
		parkingContext.parking = parking;
		
		return parkingContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 车位信息上下文
	 */
	public static ParkingContext build() {
		ParkingContext parkingContext = getInstance();
		
		return parkingContext;
	}
	
	/**
	 * 根据车位信息ID加载上下文
	 * @param parkingId 车位信息ID
	 * @return 车位信息上下文
	 */
	public static ParkingContext loadById(String parkingId) {
		ParkingContext parkingContext = getInstance();
		parkingContext.parkingId = parkingId;
		
		return parkingContext;
	}
	
	/**
	 * 获取车位信息
	 * @return 车位信息值对象
	 */
	public ParkingVo getParking() {
		if(parking == null) {
			ParkingPo parkingPo = null;
			if(ValidateHelper.isNotEmptyString(parkingId)) {
				parkingPo = parkingRepository.findOne(parkingId);
			}
			
			if(parkingPo != null) {
				parking = MyBeanUtil.createBean(parkingPo, ParkingVo.class);
			}
		}
		
		return parking;
	}
	
	/**
	 * 保存车位信息
	 */
	public void create() {
		if(parking != null) {
			parking.setId(UUIDGenerator.generate());
			parking.setOemCode(""); // TODO 待确定后补齐
			
			ParkingPo parkingPo = MyBeanUtil.createBean(parking, ParkingPo.class);
			parkingRepository.save(parkingPo);
		}
	}
}
