package com.liefeng.property.domain.household;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.property.po.household.ResidentHistoryPo;
import com.liefeng.property.repository.household.ResidentHistoryRepository;
import com.liefeng.property.vo.household.ResidentHistoryVo;

/**
 * 住户历史信息领域模型
 * 
 * @author wuzhijing
 * @date 2016-04-25
 */
@Service
@Scope("prototype")
public class ResidentHistoryContext {
	
	private static Logger logger = LoggerFactory.getLogger(ResidentHistoryContext.class);
	
	@Autowired
	private ResidentHistoryRepository residentHistoryRepository;
	
	
	/**
	 * 住户历史信息ID
	 */
	private String id;
	
	/**
	 * 住户历史信息值对象
	 */
	private ResidentHistoryVo resideHistory;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static ResidentHistoryContext getInstance() {
		return SpringBeanUtil.getBean(ResidentHistoryContext.class);
	}
	
	/**
	 * 根据住户信息值对象构建上下文
	 * @param resident 住户信息值对象
	 * @return 住户信息上下文
	 */
	public static ResidentHistoryContext build(ResidentHistoryVo resideHistory) {
		 ResidentHistoryContext residentHistoryContext = getInstance();
		 residentHistoryContext.setResideHistory(resideHistory);
		 
		 return residentHistoryContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 住户信息上下文
	 */
	public static ResidentHistoryContext build() {
		 ResidentHistoryContext residentContext = getInstance();
		 
		 return residentContext;
	}
	
	/**
	 * 根据住户信息ID加载上下文
	 * @param residentId 住户信息ID
	 * @return 住户信息上下文
	 */
	public static ResidentHistoryContext loadById(String residentId) {
		ResidentHistoryContext residentContext = getInstance();
		 residentContext.setId(residentId);
		 
		 return residentContext;
	}
	
	public void update() {
		residentHistoryRepository.save(MyBeanUtil.createBean(resideHistory,ResidentHistoryPo.class));
	}
	
	public void create() {
		resideHistory.setId(UUIDGenerator.generate());
		resideHistory.setCreateTime(new Date());
		residentHistoryRepository.save(MyBeanUtil.createBean(resideHistory,ResidentHistoryPo.class));
	}
	
	public ResidentHistoryVo get() {
		ResidentHistoryPo residentHistoryPo = residentHistoryRepository.findOne(id);
		return MyBeanUtil.createBean(residentHistoryPo, ResidentHistoryVo.class);
	}
	
	protected void setId(String id) {
		this.id = id;
	}

	protected void setResideHistory(ResidentHistoryVo resideHistory) {
		this.resideHistory = resideHistory;
	}
	
	
}