package com.liefeng.property.domain.fee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.po.fee.FeeRecordPo;
import com.liefeng.property.po.fee.MeterSettingPo;
import com.liefeng.property.repository.MeterSettingRepository;
import com.liefeng.property.vo.fee.MeterRecordVo;
import com.liefeng.property.vo.fee.MeterSettingVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.project.ProjectBuildingVo;

/**
 * 
 * <pre>      
 * Title 仪表设置领域模型:
 * Description:
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  wuzhijing     
 * @version 1.0      
 * @created 2016年2月18日下午4:05:56
 * </pre>
 */
@Service
@Scope("prototype")
public class MeterSettingContext {

	private static Logger logger = LoggerFactory.getLogger(MeterSettingContext.class);

	@Autowired
	private MeterSettingRepository meterSettingRepository;
	
	/**
	 * 仪表设置对象
	 */
	private MeterSettingVo meterSetting;
	
	/**
	 * 项目id
	 */
	private String projectId;
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static MeterSettingContext getInstance() {
		return SpringBeanUtil.getBean(MeterSettingContext.class);
	}
	
	/**
	 * 构建上下文
	 * @return 仪表设置上下文
	 */
	public static MeterSettingContext build(MeterSettingVo meterSetting) {
		MeterSettingContext meterSettingContext = getInstance();
		meterSettingContext.setMeterSetting(meterSetting);
		return meterSettingContext;
	}
	/**
	 * 构建上下文(无参数)
	 * @return 仪表设置上下文
	 */
	public static MeterSettingContext build() {
		MeterSettingContext meterSettingContext = getInstance();
		return meterSettingContext;
	}
	
	/**
	 * 构建上下文 加载项目id
	 * @param projectId 项目id
	 * @return
	 */
	public static MeterSettingContext loadByProjectId(String projectId){
		MeterSettingContext meterSettingContext = getInstance();
		meterSettingContext.setProjectId(projectId);
		return meterSettingContext;
	}

	public static MeterSettingContext loadById(String id) {
		MeterSettingContext meterSettingContext = getInstance();
		meterSettingContext.setId(id);
		return meterSettingContext;
	}

	

	/**
	 * 创建仪表设置
	 */
	public void save() {
		if(meterSetting != null) {
			meterSetting.setId(UUIDGenerator.generate());
			meterSetting.setOemCode(ContextManager.getInstance().getOemCode());

			MeterSettingPo meterSettingPo = MyBeanUtil.createBean(meterSetting, MeterSettingPo.class);
			meterSettingRepository.save(meterSettingPo);
    	}
	}
	/**
	 * 根据项目获取所以仪表
	 */
	public  DataPageValue<MeterSettingPo> findByProjectId(Integer pageSize, Integer currentPage){
		
		Pageable pageable=new PageRequest(currentPage - 1, pageSize);
		Page<MeterSettingPo> meterSettingPage = meterSettingRepository.findByProjectId(projectId,pageable);
		
		return new DataPageValue<MeterSettingPo>(meterSettingPage.getContent(),meterSettingPage.getTotalElements(), pageSize, currentPage);
	}

	/**
	 * 获取单仪表设置详情
	 * @param id
	 * @return
	 */
	public MeterRecordVo findById() {
		return MyBeanUtil.createBean(meterSettingRepository.findOne(id), MeterRecordVo.class);
	}
	
	/**
	 * 更新费用设置
	 */
	public void update(){
		meterSetting.setOemCode(ContextManager.getInstance().getOemCode());
		MeterSettingPo meterSettingPo = MyBeanUtil.createBean(meterSetting, MeterSettingPo.class);
		 meterSettingRepository.save(meterSettingPo);
	}

	protected void setMeterSetting(MeterSettingVo meterSetting) {
		this.meterSetting = meterSetting;
	}

	protected void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	protected void setId(String id) {
		this.id=id;
	}
}
