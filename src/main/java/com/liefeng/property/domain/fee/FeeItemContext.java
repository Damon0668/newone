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
import com.liefeng.property.po.fee.FeeItemPo;
import com.liefeng.property.repository.FeeItemRepository;
import com.liefeng.property.vo.fee.FeeItemVo;

/**
 * 费用项领域模型
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class FeeItemContext {
	
	private static Logger logger = LoggerFactory.getLogger(FeeItemContext.class);
	
	@Autowired
	private FeeItemRepository feeItemRepository;
	
	/**
	 * 费用项ID
	 */
	private String feeItemId;
	
	/**
	 * 费用项值对象
	 */
	private FeeItemVo feeItem;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static FeeItemContext getInstance() {
		return SpringBeanUtil.getBean(FeeItemContext.class);
	}
	
	/**
	 * 根据费用项值对象构建上下文
	 * @param feeItem 费用项值对象
	 * @return 费用项上下文
	 */
	public static FeeItemContext build(FeeItemVo feeItem) {
		FeeItemContext feeItemContext = getInstance();
		feeItemContext.feeItem = feeItem;
		
		return feeItemContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 费用项上下文
	 */
	public static FeeItemContext build() {
		FeeItemContext feeItemContext = getInstance();
		
		return feeItemContext;
	}
	
	/**
	 * 根据费用项ID加载上下文
	 * @param feeItemId 费用项ID
	 * @return 费用项上下文
	 */
	public static FeeItemContext loadById(String feeItemId) {
		FeeItemContext feeItemContext = getInstance();
		feeItemContext.feeItemId = feeItemId;
		
		return feeItemContext;
	}
	
	/**
	 * 查询费用项
	 * @return 费用项值对象
	 */
	public FeeItemVo getFeeItem(){
		if(feeItem == null) {
			FeeItemPo feeItemPo = null;
			if(ValidateHelper.isNotEmptyString(feeItemId)) {
				feeItemPo = feeItemRepository.findOne(feeItemId);
			}
			
			if(feeItemPo != null) {
				feeItem = MyBeanUtil.createBean(feeItemPo, FeeItemVo.class);
			}
		}
		
		return feeItem;
	}
	
	/**
	 * 保存费用项
	 */
	public void create() {
		if(feeItem != null) {
			feeItem.setId(UUIDGenerator.generate());
			feeItem.setCreateTime(new Date());
			feeItem.setOemCode(""); // TODO 待确定后补齐
			
			FeeItemPo feeItemPo = MyBeanUtil.createBean(feeItem, FeeItemPo.class);
			feeItemRepository.save(feeItemPo);
		}
	}

}
