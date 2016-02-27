package com.liefeng.property.domain.fee;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.TimeUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.fee.FeeItemBo;
import com.liefeng.property.constant.FeeConstants;
import com.liefeng.property.po.fee.FeeItemPo;
import com.liefeng.property.repository.FeeItemRepository;
import com.liefeng.property.repository.mybatis.FeeItemQueryRepository;
import com.liefeng.property.vo.fee.FeeItemVo;

/**
 * 费用项领域模型
 * 
 * @author Wuzhijing
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class FeeItemContext {
	
	private static Logger logger = LoggerFactory.getLogger(FeeItemContext.class);
	
	@Autowired
	private FeeItemRepository feeItemRepository;
	
	@Autowired
	private FeeItemQueryRepository feeItemQueryRepository;
	
	/**
	 * 费用项ID
	 */
	private String feeItemId;
	
	/**
	 * 项目id
	 */
	private String projectId;
	
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
		feeItemContext.setFeeItem(feeItem);
		
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
		feeItemContext.setFeeItemId(feeItemId);
		return feeItemContext;
	}
	
	/**
	 * 根据费用项ID加载上下文
	 * @param feeItemId 费用项ID
	 * @return 费用项上下文
	 */
	public static FeeItemContext loadByProjectId(String projectId) {
		FeeItemContext feeItemContext = getInstance();
		feeItemContext.setProjectId(projectId); 
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
			feeItem.setOemCode(ContextManager.getInstance().getOemCode()); 
			FeeItemPo feeItemPo = MyBeanUtil.createBean(feeItem, FeeItemPo.class);
			feeItemRepository.save(feeItemPo);
		}
	}

	
	
	public DataPageValue<FeeItemVo> list (FeeItemBo feeItemBo,Integer currentPage,Integer pageSize){
		Map<String, String> extra = MyBeanUtil.bean2Map(feeItemBo);
		
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(currentPage);
		param.setPageSize(pageSize);
		
		Long total = feeItemQueryRepository.queryByCount(param);
		total = (total == null ? 0 : total);
		logger.info("FeeItem List total：total={}", total);
	
		param.getPager().setRowCount(total);
		List<FeeItemVo> meterRecordVos = feeItemQueryRepository.queryByPage(param);
		return new DataPageValue<FeeItemVo>(meterRecordVos, total, pageSize, currentPage);
	}
	
	public FeeItemVo collect(){
		FeeItemPo feeItemPo = feeItemRepository.findOne(feeItemId);
		feeItemPo.setStatus(FeeConstants.FeeItem.STATUS_YES);
		feeItemPo.setUpdateTime(new Date());
		return MyBeanUtil.createBean(feeItemRepository.save(feeItemPo),FeeItemVo.class);
	}
	
	/**
	 * 获取上一期费用记录
	 * @param houseNum 房号
	 * @param feeType 费用类型
	 * @return
	 */
	public FeeItemVo getPreFeeItem(String houseNum, String feeType) {
		//上个月日期
		Date preDate = TimeUtil.getDayBeforeByMonth(new Date(), 1);
		FeeItemPo feeItemPo = feeItemRepository.getPreFeeItem(projectId,houseNum, feeType,preDate);
		return MyBeanUtil.createBean(feeItemPo, FeeItemVo.class);
	}
	
	protected void setFeeItem(FeeItemVo feeItem) {
		this.feeItem = feeItem;
	}
	 
	protected void setFeeItemId(String feeItemId) {
		this.feeItemId = feeItemId;
	}
	
	protected void setProjectId(String projectId) {
		this.projectId = projectId;
	}


	
}
