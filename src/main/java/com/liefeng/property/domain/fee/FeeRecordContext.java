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
import com.liefeng.property.po.fee.FeeRecordPo;
import com.liefeng.property.repository.FeeRecordRepository;
import com.liefeng.property.vo.fee.FeeRecordVo;

/**
 * 缴费记录领域模型
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class FeeRecordContext {
	
	private static Logger logger = LoggerFactory.getLogger(FeeRecordContext.class);
	
	@Autowired
	private FeeRecordRepository feeRecordRepository;
	
	/**
	 * 缴费记录ID
	 */
	private String feeRecordId;
	
	/**
	 * 缴费记录值对象
	 */
	private FeeRecordVo feeRecord;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static FeeRecordContext getInstance() {
		return SpringBeanUtil.getBean(FeeRecordContext.class);
	}
	
	/**
	 * 根据缴费记录值对象构建上下文
	 * @param feeRecord 缴费记录值对象
	 * @return 缴费记录上下文
	 */
	public static FeeRecordContext build(FeeRecordVo feeRecord) {
		FeeRecordContext feeRecordContext = getInstance();
		feeRecordContext.feeRecord = feeRecord;
		
		return feeRecordContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 缴费记录上下文
	 */
	public static FeeRecordContext build() {
		FeeRecordContext feeRecordContext = getInstance();
		
		return feeRecordContext;
	}
	
	/**
	 * 根据缴费记录ID加载上下文
	 * @param feeRecordId 缴费记录ID
	 * @return 缴费记录上下文
	 */
	public static FeeRecordContext loadById(String feeRecordId) {
		FeeRecordContext feeRecordContext = getInstance();
		feeRecordContext.feeRecordId = feeRecordId;
		
		return feeRecordContext;
	}
	
	/**
	 * 查询缴费记录
	 * @return 缴费记录值对象
	 */
	public FeeRecordVo getFeeRecord() {
		if(feeRecord == null) {
			FeeRecordPo feeRecordPo = null;
			if(ValidateHelper.isNotEmptyString(feeRecordId)) {
				feeRecordPo = feeRecordRepository.findOne(feeRecordId);
			}
			
			if(feeRecordPo != null) {
				feeRecord = MyBeanUtil.createBean(feeRecordPo, FeeRecordVo.class);
			}
		}
		
		return feeRecord;
	}
	
	/**
	 * 保存缴费记录
	 */
    public void create() {
    	if(feeRecord != null) {
    		feeRecord.setId(UUIDGenerator.generate());
    		feeRecord.setCreateTime(new Date());
    		
    		FeeRecordPo feeRecordPo = MyBeanUtil.createBean(feeRecord, FeeRecordPo.class);
    		feeRecordRepository.save(feeRecordPo);
    	}
    }

}
