package com.liefeng.property.domain.fee;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.Po2VoConverter;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.error.FeeErrorCode;
import com.liefeng.property.exception.FeeException;
import com.liefeng.property.po.fee.FeeSettingPo;
import com.liefeng.property.repository.FeeSettingRepository;
import com.liefeng.property.vo.fee.FeeSettingVo;

/**
 * <pre>      
 * Title:标准费用设置领域模型
 * Description:
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  wuzhijing
 * @version 1.0      
 * @created 2016年2月18日下午9:12:15
 * </pre>
 */
@Service
@Scope("prototype")
public class FeeSettingContext {

	private static Logger logger = LoggerFactory.getLogger(FeeSettingContext.class);

	@Autowired
	private FeeSettingRepository feeSettingRepository;
	
	private FeeSettingVo feeSetting;
	
	private String feeSettingId;

	private String projectId;

	/**
	 * 获取本类实例，每次返回一个新对象
	 * 
	 * @return 本类实例
	 */
	public static FeeSettingContext getInstance() {
		return SpringBeanUtil.getBean(FeeSettingContext.class);
	}

	/**
	 * 获取本类实例，每次返回一个新对象
	 * @param feeSetting
	 * @return
	 */
	public static FeeSettingContext build(FeeSettingVo feeSetting) {
		FeeSettingContext feeSettingContext = getInstance();
		feeSettingContext.setFeeSetting(feeSetting);
		return feeSettingContext;
	}

	/**
	 * 获取本类实例，每次返回一个新对象
	 * @param id
	 * @return
	 */
	public static FeeSettingContext loadById(String id) {
		FeeSettingContext feeSettingContext = getInstance();
		feeSettingContext.setFeeSettingId(id);
		return feeSettingContext;
	}

	/**
	 * 获取本类实例，每次返回一个新对象
	 * @param projectId
	 * @return
	 */
	public static FeeSettingContext loadByProjectId(String projectId) {
		FeeSettingContext feeSettingContext = getInstance();
		feeSettingContext.setProjectId(projectId);
		return feeSettingContext;
	}

	public void update(){
		FeeSettingPo feeSettingPo=feeSettingRepository.findOne(feeSetting.getId());
		if(feeSetting==null){
			throw new FeeException(FeeErrorCode.FEESETTING_NOT_EXISTS);
		}

		// 判断是否存在相同仪表
		FeeSettingPo feeSettingPoTypeIsExists = feeSettingRepository.findByProjectIdAndFeeTypeAndUseType(
				feeSetting.getProjectId(), feeSetting.getFeeType(),feeSetting.getUseType());
		if (feeSettingPoTypeIsExists!= null
			&&!feeSettingPoTypeIsExists.getId().equals(feeSetting.getId())) {
			throw new FeeException(FeeErrorCode.FEESETTING_EXISTS);
		}

		feeSettingPo.setChargeable(feeSetting.getChargeable());
		feeSettingPo.setFeeType(feeSetting.getFeeType());
		feeSettingPo.setPaymentPeriod(feeSetting.getPaymentPeriod());
		feeSettingPo.setPeriod(feeSetting.getPeriod());
		feeSettingPo.setPrice(feeSetting.getPrice());
		feeSettingPo.setStartDay(feeSetting.getStartDay());
		feeSettingPo.setStartMonth(feeSetting.getStartMonth());
		feeSettingPo.setUnit(feeSetting.getUnit());
		feeSettingPo.setUseType(feeSetting.getUseType());
		
		feeSettingRepository.save(feeSettingPo);
	}

	public void delete() throws FeeException {
		logger.info("执行费用设置删除id:"+feeSettingId);
		
		FeeSettingPo feeSettingPo=feeSettingRepository.findOne(feeSettingId);
		if(feeSettingPo==null){
			throw new FeeException(FeeErrorCode.FEESETTING_NOT_EXISTS);
		}
		feeSettingRepository.delete(feeSettingPo);
	}

	public FeeSettingVo findById(){
		FeeSettingPo feeSettingPo=feeSettingRepository.findOne(feeSettingId);
		return MyBeanUtil.createBean(feeSettingPo,FeeSettingVo.class);
	}

	public void create() throws FeeException {
		if(feeSetting!=null){
			FeeSettingPo feeSettingPo=feeSettingRepository.findByProjectIdAndFeeTypeAndUseType(feeSetting.getProjectId(),feeSetting.getFeeType(),feeSetting.getUseType());
			if(feeSettingPo!=null){
				throw new FeeException(FeeErrorCode.FEESETTING_EXISTS);
			}
			feeSetting.setOemCode(ContextManager.getInstance().getOemCode());
			feeSetting.setId(UUIDGenerator.generate());
			feeSetting.setCreateTime(new Date());
			feeSettingRepository.save(MyBeanUtil.createBean(feeSetting,FeeSettingPo.class));
		}
	}

	public DataPageValue<FeeSettingVo> findByProjectId4Page(Integer currentPage,Integer pageSize){
		Page<FeeSettingVo> voPage = null;

		//分页查询
		Pageable pageable = new PageRequest(currentPage - 1, pageSize);
		Page<FeeSettingPo> feeSettingPage = feeSettingRepository
				.findByProjectIdOrderByFeeTypeDesc(projectId, pageable);

		//转换
		voPage = feeSettingPage
				.map(new Po2VoConverter<FeeSettingPo, FeeSettingVo>(
						FeeSettingVo.class));

		return new DataPageValue<FeeSettingVo>(voPage.getContent(),
				voPage.getTotalElements(), pageSize, currentPage);
	}

	
	
	protected void setFeeSetting(FeeSettingVo feeSetting) {
		this.feeSetting = feeSetting;
	}

	protected void setFeeSettingId(String feeSettingId) {
		this.feeSettingId = feeSettingId;
	}

	protected void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	
}
