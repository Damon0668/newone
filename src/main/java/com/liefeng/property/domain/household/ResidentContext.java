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
import com.liefeng.property.po.household.ResidentPo;
import com.liefeng.property.repository.ResidentRepository;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * 住户信息领域模型
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class ResidentContext {
	
	private static Logger logger = LoggerFactory.getLogger(ResidentContext.class);
	
	@Autowired
	private ResidentRepository residentRepository;
	
	/**
	 * 住户信息ID
	 */
	private String residentId;
	
	/**
	 * 住户信息值对象
	 */
	private ResidentVo resident;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	public static ResidentContext getInstance() {
		return SpringBeanUtil.getBean(ResidentContext.class);
	}
	
	/**
	 * 根据住户信息值对象构建上下文
	 * @param resident 住户信息值对象
	 * @return 住户信息上下文
	 */
	public static ResidentContext build(ResidentVo resident) {
		 ResidentContext residentContext = getInstance();
		 residentContext.resident = resident;
		 
		 return residentContext;
	}
	
	/**
	 * 根据住户信息ID加载上下文
	 * @param residentId 住户信息ID
	 * @return 住户信息上下文
	 */
	public static ResidentContext loadById(String residentId) {
		ResidentContext residentContext = getInstance();
		 residentContext.residentId = residentId;
		 
		 return residentContext;
	}
	
	/**
	 * 查询住户信息
	 * @return 住户信息值对象
	 */
	public ResidentVo getResident() {
		if(resident == null) {
			ResidentPo residentPo = null;
			if(ValidateHelper.isNotEmptyString(residentId)) {
				residentPo = residentRepository.findOne(residentId);
			}
			
			if(residentPo != null) {
				resident = MyBeanUtil.createBean(residentPo, ResidentVo.class);
			}
		}
		
		return resident;
	}
	
	/**
	 * 保存住户信息
	 */
	public void create() {
		if(resident != null) {
			resident.setId(UUIDGenerator.generate());
			resident.setOemCode(""); // TODO 待确定后补齐
			
			ResidentPo residentPo = MyBeanUtil.createBean(resident, ResidentPo.class);
			residentRepository.save(residentPo);
		}
	}

}
