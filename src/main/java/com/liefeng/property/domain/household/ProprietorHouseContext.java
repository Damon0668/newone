package com.liefeng.property.domain.household;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.base.po.DevicePo;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.household.ProprietorHousePo;
import com.liefeng.property.repository.ProprietorHouseRepository;
import com.liefeng.property.vo.household.ProprietorHouseVo;

/**
 * 业主房产信息领域模型
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class ProprietorHouseContext {
	
	private static Logger logger = LoggerFactory.getLogger(ProprietorHouseContext.class);
	
	@Autowired
	private ProprietorHouseRepository proprietorHouseRepository;
	
	/**
	 * 业主房产信息ID
	 */
	private String proprietorHouseId;
	
	/**
	 * 业主房产信息值对象
	 */
	private ProprietorHouseVo proprietorHouse;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	public static ProprietorHouseContext getInstance() {
		return SpringBeanUtil.getBean(ProprietorHouseContext.class);
	}

	/**
	 * 根据业主房产信息值对象构建上下文
	 * @param proprietorHouse 业主房产信息值对象
	 * @return 业主房产信息上下文
	 */
	public static ProprietorHouseContext build(ProprietorHouseVo proprietorHouse) {
		ProprietorHouseContext proprietorHouseContext = getInstance();
		proprietorHouseContext.proprietorHouse = proprietorHouse;
		
		return proprietorHouseContext;
	}
	
	/**
	 * 根据业主房产信息ID加载上下文
	 * @param proprietorHouseId 业主房产信息ID
	 * @return 业主房产信息上下文
	 */
	public static ProprietorHouseContext loadById(String proprietorHouseId) {
		ProprietorHouseContext proprietorHouseContext = getInstance();
		proprietorHouseContext.proprietorHouseId = proprietorHouseId;
		
		return proprietorHouseContext;
	}
	
	/**
	 * 查询业主房产信息
	 * @return 业主房产信息值对象
	 */
	public ProprietorHouseVo getProprietorHouse() {
		if(proprietorHouse == null) {
			ProprietorHousePo proprietorHousePo = null;
			if(ValidateHelper.isNotEmptyString(proprietorHouseId)) {
				proprietorHousePo = proprietorHouseRepository.findOne(proprietorHouseId);
			}
			
			if(proprietorHousePo != null) {
				proprietorHouse = MyBeanUtil.createBean(proprietorHousePo, ProprietorHouseVo.class);
			}
		}
		
		return proprietorHouse;
	}
	
	/**
	 * 保存业主房产信息
	 */
	public ProprietorHouseVo create() {
		if(proprietorHouse != null) {
			proprietorHouse.setId(UUIDGenerator.generate());
			proprietorHouse.setOemCode(""); // TODO 待确定后补齐
			proprietorHouse.setRegisterTime(new Date());
			
			ProprietorHousePo proprietorHousePo = MyBeanUtil.createBean(proprietorHouse, ProprietorHousePo.class);
			proprietorHouseRepository.save(proprietorHousePo);
		}
		
		return proprietorHouse;
	}
	
	/**
	 * 更新业主房产信息
	 */
	public void update() {
		if(proprietorHouse != null  && ValidateHelper.isNotEmptyString(proprietorHouse.getId())) {
			
			ProprietorHousePo proprietorHousePo = proprietorHouseRepository.findOne(proprietorHouse.getId());
			if(proprietorHousePo != null) {
				MyBeanUtil.copyBeanNotNull2Bean(proprietorHouse, proprietorHousePo);
				proprietorHouseRepository.save(proprietorHousePo);
			}
		}
	}
	
}
