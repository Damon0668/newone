package com.liefeng.property.domain.household;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.EncryptionUtil;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.po.household.ResidentHousePo;
import com.liefeng.property.repository.household.ResidentHouseRepository;
import com.liefeng.property.repository.mybatis.ResidentHouseQueryRepository;
import com.liefeng.property.vo.household.ResidentHouseVo;

/**
 * 住户房屋信息领域模型
 * 
 * @author ZhenTingJun
 * @author xhw
 * @date 2016年3月15日
 */
@Service
@Scope("prototype")
public class ResidentHouseContext {
	
	private static Logger logger = LoggerFactory.getLogger(ResidentHouseContext.class);
	
	@Autowired
	private ResidentHouseRepository residentHouseRepository;
	
	@Autowired
	private ResidentHouseQueryRepository ResidentHouseQueryRepository;
	
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
			if(residentHouse.getCheckinDate() == null){
				residentHouse.setCheckinDate(new Date());
			}
			
			ResidentHousePo residentPo = MyBeanUtil.createBean(residentHouse, ResidentHousePo.class);
			residentHouseRepository.save(residentPo);
			logger.info("保存住户房屋信息成功，住户房屋ID = {}", residentHouse.getId());
		}
		
		return residentHouse;
	}
	
	/**
	 * 更新住户房屋信息
	 */
	public ResidentHouseVo update() {
		if(residentHouse != null && ValidateHelper.isNotEmptyString(residentHouse.getId())) {
			logger.info("更新住户房屋信息，住户房屋ID = {}", residentHouse.getId());
			ResidentHousePo residentHousePo = residentHouseRepository.findOne(residentHouse.getId());
			
			if(residentHousePo != null) {
				MyBeanUtil.copyBeanNotNull2Bean(residentHouse, residentHousePo);
				residentHouseRepository.save(residentHousePo);
				logger.info("更新住户房屋信息成功，住户房屋ID = {}", residentHouse.getId());
				
				residentHouse = MyBeanUtil.createBean(residentHousePo, ResidentHouseVo.class);
			}
		}
		
		return residentHouse;
	}
	
	/**
	 * 根据住户id，房间id获取residentHouse
	 * @param residentId 住户id
	 * @param houseId 房间id
	 * @return 
	 * @author xhw
	 * @date 2016年3月17日 下午5:34:52
	 */
	public ResidentHouseVo getResidentHouse(String residentId, String houseId){
		ResidentHouseVo residentHouseVo = null;
		if(ValidateHelper.isNotEmptyString(residentId) && ValidateHelper.isNotEmptyString(houseId)){
			ResidentHousePo residentHousePo = residentHouseRepository.findByResidentIdAndHouseId(residentId, houseId);
			
			residentHouseVo = MyBeanUtil.createBean(residentHousePo, ResidentHouseVo.class);
		}
		return residentHouseVo;
	}
	
	/**
	 * 根据住户id，获取residentHouse
	 * @param residentId
	 * @return
	 */
	public ResidentHouseVo findResidentId(String residentId) {
		ResidentHousePo residentHousePo = residentHouseRepository.findByResidentId(residentId);
		return MyBeanUtil.createBean(residentHousePo, ResidentHouseVo.class);
	}
	
	/**
	 * 获取某个住户某个项目下的住户房产列表
	 * @param residentId 住户id
	 * @param projectId 项目id
	 * @return 
	 */
	public List<ResidentHouseVo> findResidentIdAndProjectId(String residentId,String projectId) {
		List<ResidentHousePo> residentHousePos = residentHouseRepository.findByResidentIdAndProjectId(residentId,projectId);
		return MyBeanUtil.createList(residentHousePos, ResidentHouseVo.class);
	}
	
	/**
	 * 根据身份证号码获取住户房屋关系
	 * @param idNum
	 * @param projectId
	 * @param houseId
	 * @return 
	 * @author xhw
	 * @date 2016年4月26日 上午11:09:53
	 */
	public ResidentHouseVo findByIdNum(String idNum, String projectId, String houseId){
		ResidentHouseVo residentHouseVo = null;
		
		if(ValidateHelper.isNotEmptyString(idNum) && ValidateHelper.isNotEmptyString(projectId) && ValidateHelper.isNotEmptyString(houseId)){
			String oemCode = ContextManager.getInstance().getOemCode();
			// 身份证号加密
			String encryptedIdNum = EncryptionUtil.encryptCustIdNum(idNum);
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("projectId", projectId);
			paramMap.put("houseId", houseId);
			paramMap.put("oemCode", oemCode);
			paramMap.put("idNum", encryptedIdNum);
			
			PagingParamVo param = new PagingParamVo();
			param.setExtra(paramMap);
			
			residentHouseVo = ResidentHouseQueryRepository.queryByIdNum(param);
		}
		return residentHouseVo;
	}
	
	/**
	 * 删除住户房产信息
	 */
	public void delete() {
		residentHouseRepository.delete(residentHouseId);
	}
	
	protected void setResidentHouseId(String residentHouseId) {
		this.residentHouseId = residentHouseId;
	}

	protected void setResidentHouse(ResidentHouseVo residentHouse) {
		this.residentHouse = residentHouse;
	}

	

}