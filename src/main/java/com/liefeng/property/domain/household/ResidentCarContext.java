package com.liefeng.property.domain.household;

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
import com.liefeng.property.po.household.ResidentCarPo;
import com.liefeng.property.repository.household.ResidentCarRepository;
import com.liefeng.property.repository.mybatis.ResidentCarQueryRepository;
import com.liefeng.property.vo.household.ResidentCarVo;

/**
 * 住户车辆信息领域模型
 * 
 * @author ZhenTingJun
 * @date 2015-12-28
 */
@Service
@Scope("prototype")
public class ResidentCarContext {

	private static Logger logger = LoggerFactory.getLogger(ResidentCarContext.class);

	@Autowired
	private ResidentCarRepository residentCarRepository;
	
	@Autowired
	private ResidentCarQueryRepository residentCarQueryRepository;
	
	/**
	 * 住户车辆信息ID
	 */
	private String residentCarId;

	/**
	 * 住户车辆信息值对象
	 */
	private ResidentCarVo residentCar;

	/**
	 * 获取本类实例，每次返回一个新对象
	 * 
	 * @return 本类实例
	 */
	private static ResidentCarContext getInstance() {
		return SpringBeanUtil.getBean(ResidentCarContext.class);
	}

	/**
	 * 根据住户车辆信息值对象构建上下文
	 * 
	 * @param residentCar 住户车辆信息值对象
	 * @return 住户车辆信息上下文
	 */
	public static ResidentCarContext build(ResidentCarVo residentCar) {
		ResidentCarContext residentCarContext = getInstance();
		residentCarContext.setResidentCar(residentCar);

		return residentCarContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * 
	 * @return 住户车辆信息上下文
	 */
	public static ResidentCarContext build() {
		ResidentCarContext residentCarContext = getInstance();

		return residentCarContext;
	}

	/**
	 * 根据住户车辆信息ID加载上下文
	 * 
	 * @param residentCarId 住户车辆信息ID
	 * @return 住户车辆信息上下文
	 */
	public static ResidentCarContext loadById(String residentCarId) {
		ResidentCarContext residentCarContext = getInstance();
		residentCarContext.setResidentCarId(residentCarId);

		return residentCarContext;
	}

	/**
	 * 查询住户车辆信息
	 * 
	 * @return 住户车辆信息值对象
	 */
	public ResidentCarVo getResidentCar() {
		if (residentCar == null) {
			ResidentCarPo residentCarPo = null;
			if (ValidateHelper.isNotEmptyString(residentCarId)) {
				residentCarPo = residentCarRepository.findOne(residentCarId);
			}

			if (residentCarPo != null) {
				residentCar = MyBeanUtil.createBean(residentCarPo, ResidentCarVo.class);
			}
		}

		return residentCar;
	}

	/**
	 * 保存住户车辆信息
	 */
	public void create() {
		if (residentCar != null) {
			residentCar.setId(UUIDGenerator.generate());

			ResidentCarPo residentCarPo = MyBeanUtil.createBean(residentCar, ResidentCarPo.class);
			residentCarRepository.save(residentCarPo);
		}
	}
	
	public void update() {
		residentCarRepository.save(MyBeanUtil.createBean(residentCar, ResidentCarPo.class));
	}
	
	public List<ResidentCarVo> findResidentCarByPakingId(String pakingId) {
		return residentCarQueryRepository.findResidentCarByPakingId(pakingId);
	}
	
	public ResidentCarVo findByPlateNum(String plateNum) {
		logger.info("plateNum is {}",plateNum);
		ResidentCarPo residentCarPo = residentCarRepository.findByPlateNum(plateNum);
		return MyBeanUtil.createBean(residentCarPo, ResidentCarVo.class);
	}
	
	public List<ResidentCarVo> findResidentCarByHouseId(String houseId) {
		List<ResidentCarPo> residentCarPos = residentCarRepository.findByHouseId(houseId);
		return MyBeanUtil.createList(residentCarPos, ResidentCarVo.class);
	}
	
	protected void setResidentCar(ResidentCarVo residentCar) {
		this.residentCar = residentCar;
	}
	
	protected void setResidentCarId(String residentCarId) {
		this.residentCarId = residentCarId;
	}

	
}
