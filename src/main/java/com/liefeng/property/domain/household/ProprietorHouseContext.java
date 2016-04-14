package com.liefeng.property.domain.household;

import java.util.Date;
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
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.property.po.household.ProprietorHousePo;
import com.liefeng.property.repository.household.ProprietorHouseRepository;
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
	 * 所属项目ID
	 */
	private String projectId;
	
	/**
	 * 房产房号
	 */
	private String houseNum;
	
	/**
	 * 业主房产信息值对象
	 */
	private ProprietorHouseVo proprietorHouse;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static ProprietorHouseContext getInstance() {
		return SpringBeanUtil.getBean(ProprietorHouseContext.class);
	}

	/**
	 * 根据业主房产信息值对象构建上下文
	 * @param proprietorHouse 业主房产信息值对象
	 * @return 业主房产信息上下文
	 */
	public static ProprietorHouseContext build(ProprietorHouseVo proprietorHouse) {
		ProprietorHouseContext proprietorHouseContext = getInstance();
		proprietorHouseContext.setProprietorHouse(proprietorHouse);
		
		return proprietorHouseContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 业主房产信息上下文
	 */
	public static ProprietorHouseContext build() {
		ProprietorHouseContext proprietorHouseContext = getInstance();
		
		return proprietorHouseContext;
	}
	
	/**
	 * 根据业主房产信息ID加载上下文
	 * @param proprietorHouseId 业主房产信息ID
	 * @return 业主房产信息上下文
	 */
	public static ProprietorHouseContext loadById(String proprietorHouseId) {
		ProprietorHouseContext proprietorHouseContext = getInstance();
		proprietorHouseContext.setProprietorHouseId(proprietorHouseId);
		
		return proprietorHouseContext;
	}
	
	/**
	 * 根据业主房产信息ID加载上下文
	 * @param projectId 项目ID
	 * @param houseNum 房号
	 * @return 业主房产信息上下文
	 */
	public static ProprietorHouseContext loadByProjectIdAndHouseNum(String projectId, String houseNum) {
		ProprietorHouseContext proprietorHouseContext = getInstance();
		proprietorHouseContext.setProjectId(projectId);
		proprietorHouseContext.setHouseNum(houseNum);
		
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
			} else if(ValidateHelper.isNotEmptyString(projectId) && ValidateHelper.isNotEmptyString(houseNum)) {
				proprietorHousePo = proprietorHouseRepository.findByProjectIdAndHouseNum(projectId, houseNum);
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
			proprietorHouse.setOemCode(ContextManager.getInstance().getOemCode()); 
			proprietorHouse.setRegisterTime(new Date());
			
			ProprietorHousePo proprietorHousePo = MyBeanUtil.createBean(proprietorHouse, ProprietorHousePo.class);
			proprietorHouseRepository.save(proprietorHousePo);
			logger.info("保存业主房产信息成功，房产信息ID = {}", proprietorHousePo.getId());
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
				logger.info("更新业主房产信息成功，业主房产ID = {}", proprietorHouse.getId());
			}
		}
	}

	public List<ProprietorHouseVo> findByProjectId(String projectId) {
		List<ProprietorHousePo> proprietorHousePos = proprietorHouseRepository.findByProjectId(projectId);
		return MyBeanUtil.createList(proprietorHousePos, ProprietorHouseVo.class);
	}
	
	public List<ProprietorHouseVo> findByProjectIdAndBuildingId(
			String projectId, String buildingId) {
		List<ProprietorHousePo> proprietorHousePos = proprietorHouseRepository.findByProjectIdAndBuildingId(projectId,buildingId);
		return MyBeanUtil.createList(proprietorHousePos, ProprietorHouseVo.class);
	}

	protected void setProprietorHouseId(String proprietorHouseId) {
		this.proprietorHouseId = proprietorHouseId;
	}


	protected void setProjectId(String projectId) {
		this.projectId = projectId;
	}


	protected void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	protected void setProprietorHouse(ProprietorHouseVo proprietorHouse) {
		this.proprietorHouse = proprietorHouse;
	}

	

	
	
	
}
