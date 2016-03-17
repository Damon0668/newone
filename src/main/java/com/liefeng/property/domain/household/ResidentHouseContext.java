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
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.property.po.household.ResidentHousePo;
import com.liefeng.property.repository.household.ResidentHouseRepository;
import com.liefeng.property.vo.household.ResidentHouseVo;

/**
 * 住户房屋信息领域模型
 * 
 * @author ZhenTingJun
 * @date 2016年3月15日
 */
@Service
@Scope("prototype")
public class ResidentHouseContext {
	
	private static Logger logger = LoggerFactory.getLogger(ResidentHouseContext.class);
	
	@Autowired
	private ResidentHouseRepository residentHouseRepository;
	
	
	/**
	 * 住户房屋信息ID
	 */
	private String residentHouseId;
	
	/**
	 * 住户房屋信息值对象
	 */
	private ResidentHouseVo residentHouse;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static ResidentHouseContext getInstance() {
		return SpringBeanUtil.getBean(ResidentHouseContext.class);
	}
	
	/**
	 * 根据住户房屋信息值对象构建上下文
	 * @param residentHouse 住户房屋信息值对象
	 * @return 住户房屋信息上下文
	 */
	public static ResidentHouseContext build(ResidentHouseVo residentHouse) {
		 ResidentHouseContext residentHouseContext = getInstance();
		 residentHouseContext.setResidentHouse(residentHouse);
		 
		 return residentHouseContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 住户房屋信息上下文
	 */
	public static ResidentHouseContext build() {
		 ResidentHouseContext residentHouseContext = getInstance();
		 
		 return residentHouseContext;
	}
	
	/**
	 * 根据住户房屋信息ID加载上下文
	 * @param residentHouseId 住户房屋信息ID
	 * @return 住户房屋信息上下文
	 */
	public static ResidentHouseContext loadById(String residentHouseId) {
		ResidentHouseContext residentHouseContext = getInstance();
		residentHouseContext.setResidentHouseId(residentHouseId);
		 
		 return residentHouseContext;
	}
	
	/**
	 * 查询住户房屋信息
	 * @return 住户房屋信息值对象
	 */
	public ResidentHouseVo get() {
		if(residentHouse == null) {
			ResidentHousePo residentHousePo = null;
			if(ValidateHelper.isNotEmptyString(residentHouseId)) {
				residentHousePo = residentHouseRepository.findOne(residentHouseId);
			}
			
			if(residentHousePo != null) {
				residentHouse = MyBeanUtil.createBean(residentHousePo, ResidentHouseVo.class);
			}
		}
		
		return residentHouse;
	}
	
	
	/**
	 * 保存住户房屋信息
	 */
	public ResidentHouseVo create() {
		if(residentHouse != null) {
			residentHouse.setId(UUIDGenerator.generate());
			residentHouse.setOemCode(ContextManager.getInstance().getOemCode()); 
			
			ResidentHousePo residentPo = MyBeanUtil.createBean(residentHouse, ResidentHousePo.class);
			residentHouseRepository.save(residentPo);
			logger.info("保存住户房屋信息成功，住户信息：{}", residentHouse);
		}
		
		return residentHouse;
	}
	
	/**
	 * 更新住户房屋信息
	 */
	public ResidentHouseVo update() {
		if(residentHouse != null && ValidateHelper.isNotEmptyString(residentHouse.getId())) {
			logger.info("更新住户房屋信息，住户ID{}", residentHouse.getId());
			ResidentHousePo residentHousePo = residentHouseRepository.findOne(residentHouse.getId());
			
			if(residentHousePo != null) {
				MyBeanUtil.copyBeanNotNull2Bean(residentHouse, residentHousePo);
				residentHouseRepository.save(residentHousePo);
				logger.info("更新住户房屋信息成功，住户信息：{}", residentHouse);
				
				residentHouse = MyBeanUtil.createBean(residentHousePo, ResidentHouseVo.class);
			}
		}
		
		return residentHouse;
	}

	protected void setResidentHouseId(String residentHouseId) {
		this.residentHouseId = residentHouseId;
	}

	protected void setResidentHouse(ResidentHouseVo residentHouse) {
		this.residentHouse = residentHouse;
	}
	
	
	
	
}