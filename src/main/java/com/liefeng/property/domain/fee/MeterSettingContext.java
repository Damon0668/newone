package com.liefeng.property.domain.fee;

import java.util.Date;
import java.util.List;

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
import com.liefeng.common.util.TimeUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.constant.FeeConstants;
import com.liefeng.property.error.FeeErrorCode;
import com.liefeng.property.exception.FeeException;
import com.liefeng.property.po.fee.MeterSettingPo;
import com.liefeng.property.repository.MeterSettingRepository;
import com.liefeng.property.vo.fee.MeterSettingVo;

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

	private static Logger logger = LoggerFactory
			.getLogger(MeterSettingContext.class);

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
	 * 
	 * @return 本类实例
	 */
	private static MeterSettingContext getInstance() {
		return SpringBeanUtil.getBean(MeterSettingContext.class);
	}

	/**
	 * 构建上下文
	 * 
	 * @return 仪表设置上下文
	 */
	public static MeterSettingContext build(MeterSettingVo meterSetting) {
		MeterSettingContext meterSettingContext = getInstance();
		meterSettingContext.setMeterSetting(meterSetting);
		return meterSettingContext;
	}

	/**
	 * 构建上下文(无参数)
	 * 
	 * @return 仪表设置上下文
	 */
	public static MeterSettingContext build() {
		MeterSettingContext meterSettingContext = getInstance();
		return meterSettingContext;
	}

	/**
	 * 构建上下文 加载项目id
	 * 
	 * @param projectId
	 *            项目id
	 * @return
	 */
	public static MeterSettingContext loadByProjectId(String projectId) {
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
	public void create() {

		if (meterSetting != null) {

			// 判断仪表是否存在
			if (meterSettingRepository.findByProjectIdAndType(
					meterSetting.getProjectId(), meterSetting.getType()) != null) {
				throw new FeeException(FeeErrorCode.METERSETTING_EXISTS);
			}
			meterSetting.setId(UUIDGenerator.generate());
			meterSetting.setOemCode(ContextManager.getInstance().getOemCode());
			meterSetting.setCreateTime(new Date());
			MeterSettingPo meterSettingPo = MyBeanUtil.createBean(meterSetting,
					MeterSettingPo.class);
			meterSettingRepository.save(meterSettingPo);
		}
	}

	/**
	 * 根据项目获取所以仪表
	 */
	public DataPageValue<MeterSettingVo> findByProjectId(Integer pageSize,
			Integer currentPage) {
		Page<MeterSettingVo> voPage = null;

		// spring-data 的page从0开始
		Pageable pageable = new PageRequest(currentPage - 1, pageSize);
		Page<MeterSettingPo> meterSettingPage = meterSettingRepository
				.findByProjectId(projectId, pageable);
		voPage = meterSettingPage
				.map(new Po2VoConverter<MeterSettingPo, MeterSettingVo>(
						MeterSettingVo.class));

		return new DataPageValue<MeterSettingVo>(voPage.getContent(),
				voPage.getTotalElements(), pageSize, currentPage);
	}

	/**
	 * 获取项目下要抄仪表
	 */
	public List<MeterSettingVo> findByProjectIdAndChargeableYes(){
		List<MeterSettingPo> meterSettingPos = meterSettingRepository.findByProjectIdAndChargeable(projectId,FeeConstants.MeterSetting.CHARGEABLE_YES);
		return MyBeanUtil.createList(meterSettingPos, MeterSettingVo.class);
	}
	
	/**
	 * 获取单仪表设置详情
	 * 
	 * @param id
	 * @return
	 */
	public MeterSettingVo findById() {
		return MyBeanUtil.createBean(meterSettingRepository.findOne(id),
				MeterSettingVo.class);
	}

	/**
	 * 删除仪表设置
	 */
	public void delete() {
		logger.info("Delete meter setting of id: '{}'", id);
		// 判断仪表是否存在
		if (meterSettingRepository.findOne(id) == null) {
			throw new FeeException(FeeErrorCode.METERSETTING_NOT_EXISTS);
		}

		meterSettingRepository.delete(id);
	}

	/**
	 * 更新费用设置
	 */
	public void update() throws FeeException {
		// 判断仪表是否存在
		MeterSettingPo meterSettingPo=meterSettingRepository.findById(meterSetting.getId());
		if (meterSettingPo == null) {
			throw new FeeException(FeeErrorCode.METERSETTING_NOT_EXISTS);
		}

		// 判断是否存在相同仪表
		MeterSettingPo meterSettingPoTypeIsExists = meterSettingRepository.findByProjectIdAndType(
				meterSetting.getProjectId(), meterSetting.getType());
		if (meterSettingPoTypeIsExists!= null
			&&!meterSettingPoTypeIsExists.getId().equals(meterSetting.getId())) {
			throw new FeeException(FeeErrorCode.METERSETTING_EXISTS);
		}

		meterSettingPo.setChargeable(meterSetting.getChargeable());
		meterSettingPo.setLastingDay(meterSetting.getLastingDay());
		meterSettingPo.setModNum(meterSetting.getModNum());
		meterSettingPo.setProjectId(meterSetting.getProjectId());
		meterSettingPo.setStartDay(meterSetting.getStartDay());
		meterSettingPo.setType(meterSetting.getType());
		
		meterSettingRepository.save(meterSettingPo);
	}

	public List<MeterSettingVo> getMeterAuth(String meterOwner){
		
		List<MeterSettingPo> meterSettingPos = meterSettingRepository.findByProjectIdAndChargeable(projectId, FeeConstants.MeterSetting.CHARGEABLE_YES);
		List<MeterSettingVo> meterSettingVos = MyBeanUtil.createList(meterSettingPos, MeterSettingVo.class);
	
		for (MeterSettingVo meterSettingVo : meterSettingVos) {
			if(meterSettingVo.getStartDay()>TimeUtil.getCurrentDay()
					||meterSettingVo.getStartDay()+meterSettingVo.getLastingDay()-1<TimeUtil.getCurrentDay()){
				meterSettingVo.setIsRead(0);
			}else{
				meterSettingVo.setIsRead(1);
			}
		}
		
		
		return meterSettingVos;
	}
	
	protected void setMeterSetting(MeterSettingVo meterSetting) {
		this.meterSetting = meterSetting;
	}

	protected void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	protected void setId(String id) {
		this.id = id;
	}
}
