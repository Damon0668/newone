package com.liefeng.property.domain.project;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.project.HousePo;
import com.liefeng.property.repository.HouseRepository;
import com.liefeng.property.vo.project.HouseVo;

/**
 * 房产信息领域模型
 * @author ZhenTingJun
 * @author levy
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class HouseContext {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(HouseContext.class);
	
	@Autowired
	private HouseRepository houseRepository;
	
	/**
	 * 房产信息ID
	 */
	private String houseId;

	/**
	 * 房产所属项目ID
	 */
	private String projectId;
	
	/**
	 * 房产编码
	 */
	private String houseNum;
	
	/**
	 * 房产信息值对象
	 */
	private HouseVo house;
	
	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	public static HouseContext getInstance() {
		return SpringBeanUtil.getBean(HouseContext.class);
	}
	
	/**
	 * 根据房产信息值对象构建上下文
	 * @param house 房产信息值对象
	 * @return 房产信息上下文
	 */
	public static HouseContext build(HouseVo house) {
		HouseContext houseContext = getInstance();
		houseContext.house = house;
		
		return houseContext;
	}
	
	/**
	 * 根据房产信息ID加载上下文
	 * @param houseId 房产信息ID
	 * @return 房产信息上下文
	 */
	public static HouseContext loadById(String houseId) {
		HouseContext houseContext = getInstance();
		houseContext.houseId = houseId;
		
		return houseContext;
	}
	
	/**
	 * 根据房产编码加载上下文
	 * @param projectId 房产所属项目ID
	 * @param houseNum 房产编码
	 * @return 房产信息上下文
	 */
	public static HouseContext loadByProjectIdAndHouseCode(String projectId, String houseNum) {
		HouseContext houseContext = getInstance();
		houseContext.projectId = projectId;
		houseContext.houseNum = houseNum;
		
		return houseContext;
	}
	
	/**
	 * 获取房产信息
	 * @return 房产信息值对象
	 */
	public HouseVo getHouse() {
		if(house == null) {
			HousePo housePo = null;
			// 根据房产ID查询
			if(ValidateHelper.isNotEmptyString(houseId)) {
				housePo = houseRepository.findOne(houseId);
			} 
			// 根据所属项目ID和房产编码查询
			else if(ValidateHelper.isNotEmptyString(projectId) && ValidateHelper.isNotEmptyString(houseNum)) {
				housePo = houseRepository.findByProjectIdAndHouseNum(projectId, houseNum);
			}
			
			if(housePo != null) {
				house = MyBeanUtil.createBean(housePo, HouseVo.class);
			}
		}
		
		return house;
	}
	
	/**
	 * 保存房产信息
	 */
	public HouseVo create() {
		if(house != null) {
			house.setId(UUIDGenerator.generate());
			house.setOemCode(""); // TODO 待确定后补齐
			house.setRegisterTime(new Date());
			
			HousePo housePo = MyBeanUtil.createBean(house, HousePo.class);
			houseRepository.save(housePo);
		}
		return house;
	}

	public HouseVo update(){
		if(house != null){
			HousePo housePo = MyBeanUtil.createBean(house, HousePo.class);
			houseRepository.save(housePo);
		}
		return house;
	}
}
