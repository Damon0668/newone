package com.liefeng.property.domain.household;

import java.util.ArrayList;
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
import com.liefeng.property.po.household.CheckinMaterialPo;
import com.liefeng.property.repository.household.CheckinMaterialRepository;
import com.liefeng.property.repository.mybatis.CheckinMaterialQueryRepository;
import com.liefeng.property.vo.household.CheckinMaterialVo;

/**
 * 入住资料信息上下文
 * 
 * @author ZhenTingJun
 * @date 2016年2月25日
 */
@Service
@Scope("prototype")
public class CheckinMaterialContext {

	private static Logger logger = LoggerFactory.getLogger(CheckinMaterialContext.class);
	
	@Autowired
	private CheckinMaterialRepository checkinMaterialRepository;
	
	@Autowired
	private CheckinMaterialQueryRepository checkinMaterialQueryRepository;
	
	/**
	 * 入住资料信息ID
	 */
	private String checkinMaterialId;;
	
	/**
	 * 业主房产ID
	 */
	private String proprietorHouseId;
	
	/**
	 * 入住资料值对象
	 */
	private CheckinMaterialVo checkinMaterial;
	
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static CheckinMaterialContext getInstance(){
		return SpringBeanUtil.getBean(CheckinMaterialContext.class);
	}
	
	/**
	 * 根据入住信息值对象构建上下文
	 * @param checkinMaterial 入住信息值对象
	 * @return 入住资料信息上下文
	 */
	public static CheckinMaterialContext build(CheckinMaterialVo checkinMaterial) {
		CheckinMaterialContext checkinMaterialContext = getInstance();
		checkinMaterialContext.setCheckinMaterial(checkinMaterial);
		
		return checkinMaterialContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 入住资料信息上下文
	 */
	public static CheckinMaterialContext build() {
		CheckinMaterialContext checkinMaterialContext = getInstance();
		
		return checkinMaterialContext;
	}
	
	/**
	 * 根据入住资料信息ID加载上下文
	 * @param checkinMaterialId 入住信息ID
	 * @return 入住资料信息上下文
	 */
	public static CheckinMaterialContext loadById(String checkinMaterialId) {
		CheckinMaterialContext checkinMaterialContext = getInstance();
		checkinMaterialContext.setCheckinMaterialId(checkinMaterialId);
		
		return checkinMaterialContext;
	}
	
	/**
	 * 根据业主房产ID加载上下文
	 * @param proprietorHouseId 业主房产ID
	 * @return 入住资料信息上下文
	 */
	public static CheckinMaterialContext loadByProprietorHouseId(String proprietorHouseId) {
		CheckinMaterialContext checkinMaterialContext = getInstance();
		checkinMaterialContext.setProprietorHouseId(proprietorHouseId);
		
		return checkinMaterialContext;
	}
	
	/**
	 * 查询入住资料信息
	 */
	public CheckinMaterialVo get() {
		if(checkinMaterial == null) {
			CheckinMaterialPo checkinMaterialPo = null;
			if(ValidateHelper.isNotEmptyString(checkinMaterialId)) {
				checkinMaterialPo = checkinMaterialRepository.findOne(checkinMaterialId);
			}
			
			if(checkinMaterialPo != null) {
				checkinMaterial = MyBeanUtil.createBean(checkinMaterialPo, CheckinMaterialVo.class);
			}
		}
		
		return checkinMaterial;
	}
	
	/**
	 * 保存入住资料信息
	 */
	public void create() {
		if(checkinMaterial != null) {
			checkinMaterial.setId(UUIDGenerator.generate());
			checkinMaterial.setOemCode(ContextManager.getInstance().getOemCode());
			
			CheckinMaterialPo checkinMaterialPo = MyBeanUtil.createBean(checkinMaterial, CheckinMaterialPo.class);
			checkinMaterialRepository.save(checkinMaterialPo);
			logger.info("保存入住资料信息成功，checkinMaterial={}",checkinMaterial);
		}
	}
	
	/**
	 * 保存入住资料信息
	 */
	public void create(List<CheckinMaterialVo> checkinMaterialList) throws Exception {
		if (ValidateHelper.isNotEmptyCollection(checkinMaterialList)) {
			for(CheckinMaterialVo checkinMaterial : checkinMaterialList) {
				checkinMaterial.setId(UUIDGenerator.generate());
				checkinMaterial.setOemCode(ContextManager.getInstance().getOemCode());
			}
			
			List<CheckinMaterialPo> checkinMaterialPoList = MyBeanUtil.createList(checkinMaterialList, CheckinMaterialPo.class);
			checkinMaterialRepository.save(checkinMaterialPoList);
		}
	}
	
	/**
	 * 删除入住资料信息
	 */
	public void delete() {
		if(ValidateHelper.isNotEmptyString(proprietorHouseId)) {
			checkinMaterialRepository.deleteByProprietorHouseId(proprietorHouseId);
		} 
	}
	
	/**
	 * 获取入住资料信息列表
	 */
	public List<CheckinMaterialVo> getList() {
		List<CheckinMaterialVo> returnList = new ArrayList<CheckinMaterialVo>();
		
		if(ValidateHelper.isNotEmptyString(proprietorHouseId)) {
			returnList = checkinMaterialQueryRepository.queryByProprietorHouseId(proprietorHouseId);
		}
		
		return returnList;
	}
	
	protected void setCheckinMaterialId(String checkinMaterialId) {
		this.checkinMaterialId = checkinMaterialId;
	}

	protected void setProprietorHouseId(String proprietorHouseId) {
		this.proprietorHouseId = proprietorHouseId;
	}

	protected void setCheckinMaterial(CheckinMaterialVo checkinMaterial) {
		this.checkinMaterial = checkinMaterial;
	}
}
