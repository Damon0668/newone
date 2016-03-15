package com.liefeng.property.domain.parking;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.parking.ParkingRentalPo;
import com.liefeng.property.repository.parking.ParkingRentalRepository;
import com.liefeng.property.vo.parking.ParkingRentalVo;

/**
 * 车位租售信息领域模型
 * 
 * @author wuzhijing
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
	private static ParkingRentalContext getInstance(){
		return SpringBeanUtil.getBean(ParkingRentalContext.class);
	}
	
	/**
	 * 根据车位租售信息值对象构建上下文
	 * @param parkingRental 车位租售信息值对象
	 * @return 车位租售信息上下文
	 */
	public static ParkingRentalContext build(ParkingRentalVo parkingRental) {
		ParkingRentalContext parkingRentalContext = getInstance();
		parkingRentalContext.setParkingRental(parkingRental);
		
		return parkingRentalContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 车位租售信息上下文
	 */
	public static ParkingRentalContext build() {
		ParkingRentalContext parkingRentalContext = getInstance();
		
		return parkingRentalContext;
	}
	
	/**
	 * 根据车位租售信息ID加载上下文
	 * @param parkingRentalId 车位租售信息ID
	 * @return 车位租售信息上下文
	 */
	public static ParkingRentalContext loadById(String parkingRentalId) {
		ParkingRentalContext parkingRentalContext = getInstance();
		parkingRentalContext.setParkingRentalId(parkingRentalId); 
		
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
	
	public void update() {
		if(parkingRental!=null && ValidateHelper.isNotEmptyString(parkingRental.getId())){
			parkingRentalRepository.save(MyBeanUtil.createBean(parkingRental,ParkingRentalPo.class));
		}
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
	
	public List<ParkingRentalVo> list(String parkId){
		List<ParkingRentalPo> parkingRentalPos = parkingRentalRepository.findByParkingId(parkId);
		return MyBeanUtil.createList(parkingRentalPos, ParkingRentalVo.class);
	}
	
	protected void setParkingRental(ParkingRentalVo parkingRental) {
		this.parkingRental = parkingRental;
	}
	
	protected void setParkingRentalId(String parkingRentalId) {
		this.parkingRentalId = parkingRentalId;
	}

	
}
