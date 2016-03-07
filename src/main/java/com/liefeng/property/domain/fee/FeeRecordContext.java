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
import com.liefeng.property.error.FeeErrorCode;
import com.liefeng.property.exception.FeeException;
import com.liefeng.property.po.fee.FeeRecordPo;
import com.liefeng.property.repository.FeeRecordRepository;
import com.liefeng.property.repository.mybatis.FeeRecordQueryRepository;
import com.liefeng.property.vo.fee.FeeItemVo;
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
	
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(FeeRecordContext.class);
	
	@Autowired
	private FeeRecordRepository feeRecordRepository;
	
	@Autowired
	private FeeRecordQueryRepository feeRecordQueryRepository;
	/**
	 * 缴费记录ID
	 */
	private String feeRecordId;
	
	/**
	 * 费用id
	 */
	private String feeItemId;
	
	/**
	 * 缴费记录值对象
	 */
	private FeeRecordVo feeRecord;
	
	
	public static FeeRecordContext loadByFeeItemId(String feeItemId) {
		FeeRecordContext feeRecordContext = getInstance();
		feeRecordContext.setFeeItemId(feeItemId);
		return feeRecordContext;
	}
	
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
		feeRecordContext.setFeeRecord(feeRecord);
		
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
		feeRecordContext.setFeeRecordId(feeRecordId);
		
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
	 * @param feeItemVo 
	 */
    public void create(FeeItemVo feeItemVo) {
    		FeeRecordPo feeRecordPo	= feeRecordRepository.findByFeeItemId(feeItemVo.getId());
    		if(feeRecordPo != null){
    			throw new FeeException(FeeErrorCode.FEEITEM_ALREADYCOLLECT);
    		}
     		
				Double discountAmount = feeItemVo.getTotalFee()*feeItemVo.getDiscount();
    			Double paidAmount = discountAmount+(discountAmount*feeItemVo.getLateFeeRate());
    			FeeRecordVo feeRecordVo = new FeeRecordVo();
    			feeRecordVo.setFeeItemId(feeItemVo.getId());
    			feeRecordVo.setReceivableAmount(feeItemVo.getTotalFee());
    			feeRecordVo.setLateFee(feeItemVo.getLateFeeRate());
    			feeRecordVo.setDiscountAmount(discountAmount);
    			feeRecordVo.setPaidAmount(paidAmount);
    			feeRecordVo.setLateFee(feeItemVo.getLateFeeRate());
    			feeRecordVo.setCreateTime(new Date());
    			feeRecordVo.setId(UUIDGenerator.generate());
    			feeRecordRepository.save(MyBeanUtil.createBean(feeRecordVo, FeeRecordPo.class));
    }
    
    
    protected void setFeeItemId(String feeItemId) {
		this.feeItemId = feeItemId;
	}
    
    protected void setFeeRecordId(String feeRecordId) {
		this.feeRecordId = feeRecordId;
	}

    protected void setFeeRecord(FeeRecordVo feeRecord) {
		this.feeRecord = feeRecord;
	}
}
