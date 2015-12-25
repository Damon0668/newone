package com.liefeng.property.domain.fee;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.fee.MeterRecordPo;
import com.liefeng.property.repository.MeterRecordRepository;
import com.liefeng.property.vo.fee.MeterRecordVo;

/**
 * 抄表记录领域模型
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class MeterRecordContext {
	
	private static Logger logger = LoggerFactory.getLogger(MeterRecordContext.class);
	
	@Autowired
	private MeterRecordRepository meterRecordRepository;
	
	/**
	 * 抄表记录ID
	 */
	private String meterRecordId;
	
	/**
	 * 抄表记录值对象
	 */
	private MeterRecordVo meterRecord;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	public static MeterRecordContext getInstance() {
		return SpringBeanUtil.getBean(MeterRecordContext.class);
	}
	
	/**
	 * 根据抄表记录值对象构建上下文
	 * @param meterRecord 抄表记录值对象
	 * @return 抄表记录上下文
	 */
	public static MeterRecordContext build(MeterRecordVo meterRecord) {
		MeterRecordContext meterRecordContext = getInstance();
		meterRecordContext.meterRecord = meterRecord;
		
		return meterRecordContext;
	}
	
	/**
	 * 根据抄表记录ID加载上下文
	 * @param meterRecordId 抄表记录ID
	 * @return 抄表记录上下文
	 */
	public static MeterRecordContext loadById(String meterRecordId) {
		MeterRecordContext meterRecordContext = getInstance();
		meterRecordContext.meterRecordId = meterRecordId;
		
		return meterRecordContext;
	}
	
	/**
	 * 查询抄表记录
	 * @return 抄表记录值对象
	 */
	public MeterRecordVo getMeterRecord() {
		if(meterRecord == null) {
			MeterRecordPo meterRecordPo = null;
			if(ValidateHelper.isEmptyString(meterRecordId)) {
				meterRecordPo = meterRecordRepository.findOne(meterRecordId);
			}
			
			if(meterRecordPo != null) {
				meterRecord = MyBeanUtil.createBean(meterRecordPo, MeterRecordVo.class);
			}
		}
		
		return meterRecord;
	}
	
	/**
	 * 保存抄表记录
	 */
	public void create() {
		if(meterRecord != null) {
			meterRecord.setId(UUIDGenerator.generate());
			meterRecord.setCreateTime(new Date());
			
			MeterRecordPo meterRecordPo = MyBeanUtil.createBean(meterRecord, MeterRecordPo.class);
			meterRecordRepository.save(meterRecordPo);
		}
	}
	
}
