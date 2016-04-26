package com.liefeng.property.domain.guard;

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
import com.liefeng.property.bo.guard.GuardCardBo;
import com.liefeng.property.constant.GuardConstants;
import com.liefeng.property.po.guard.GuardCardPo;
import com.liefeng.property.repository.guard.GuardCardRepository;
import com.liefeng.property.repository.mybatis.GuardCardQueryRepository;
import com.liefeng.property.vo.guard.GuardCardVo;

/**
 * 门禁磁卡领域模型
 * @author Huangama
 * @date 2016-2-25
 */
@Service
@Scope("prototype")
public class GuardCardContext {
	private static Logger logger = LoggerFactory.getLogger(GuardCardContext.class);
	
	@Autowired
	private GuardCardRepository guardCardRepository;
	
	@Autowired
	private GuardCardQueryRepository guardCardQueryRepository;
	
	/**
	 * 磁卡ID
	 */
	private String cardId;
	
	/**
	 * 磁卡sn号
	 */
	private String sn;
	
	/**
	 * 门禁磁卡对象
	 */
	private GuardCardVo guardCard;
	
	private static GuardCardContext getInstance() {
		GuardCardContext guardCardContext =  SpringBeanUtil.getBean(GuardCardContext.class);
		return guardCardContext;
	}
	
	public static GuardCardContext build(GuardCardVo guardCard) {
		GuardCardContext guardCardContext = getInstance();
		guardCardContext.setGuardCard(guardCard);
		return guardCardContext;
	}
	
	public static GuardCardContext build() {
		GuardCardContext guardCardContext = getInstance();
		return guardCardContext;
	}
	
	public static GuardCardContext loadById(String cardId) {
		GuardCardContext guardCardContext = getInstance();
		guardCardContext.setCardId(cardId);
		return guardCardContext;
	}
	
	public static GuardCardContext loadBySn(String sn) {
		GuardCardContext guardCardContext = getInstance();
		guardCardContext.setSn(sn);
		return guardCardContext;
	}
	
	public GuardCardVo get(){
		if(guardCard == null){
			if(ValidateHelper.isNotEmptyString(cardId)){
				GuardCardPo guardCardPo = guardCardRepository.findOne(cardId);
				if(guardCardPo != null){
					guardCard = MyBeanUtil.createBean(guardCardPo, GuardCardVo.class);
					logger.info("get cardId = {}, guardCard = {}", cardId, guardCard);
				}
			}
		}
		return guardCard;
	}
	
	public GuardCardVo create(){
		
		GuardCardPo guardCardPo = MyBeanUtil.createBean(guardCard, GuardCardPo.class);
		guardCardPo.setId(UUIDGenerator.generate());
		
		guardCardPo.setStartDate(TimeUtil.getDate(new Date()));
		if(GuardConstants.GuardCardType.TEMP.equals(guardCard.getType())){
			guardCardPo.setEndDate(TimeUtil.getDayAfter(TimeUtil.getDate(new Date()), guardCard.getDuration()));
		}else{
			guardCardPo.setEndDate(null);
		}
		
		guardCardPo.setStatus(GuardConstants.GuardCardStatus.NORMAL);
		guardCardPo.setCreateTime(new Date());
		guardCardPo.setOemCode(ContextManager.getInstance().getOemCode());
		
		logger.info("create guardCard ={}", guardCardPo);
		guardCardRepository.save(guardCardPo);
		guardCard = MyBeanUtil.createBean(guardCardPo, GuardCardVo.class);
		
		return guardCard;
	}
	
	public void updata(){
		
		if(ValidateHelper.isNotEmptyString(guardCard.getId())){

			GuardCardPo guardCardPo = guardCardRepository.findOne(guardCard.getId());
			
			if(guardCardPo != null){
				
				logger.info("update guardCard ={}", guardCard);
				
				MyBeanUtil.copyBeanNotNull2Bean(guardCard, guardCardPo);
				
				if(GuardConstants.GuardCardType.TEMP.equals(guardCard.getType())){
					if(guardCard.getDuration() > 0){
						guardCardPo.setEndDate(TimeUtil.getDayAfter(TimeUtil.getDate(new Date()), guardCard.getDuration()));
					}
				}else{
					guardCardPo.setEndDate(null);
				}
				
				logger.info("update guardCardPo ={}", guardCardPo);
				guardCardRepository.save(guardCardPo);
				
				guardCard = MyBeanUtil.createBean(guardCardPo, GuardCardVo.class);
			}
			
		}
	}
	
	public void updataStatus(String status){
		if(ValidateHelper.isNotEmptyString(cardId)){
			GuardCardPo guardCardPo = guardCardRepository.findOne(cardId);
			if(guardCardPo != null){
				if(GuardConstants.GuardCardStatus.CANCEL.equals(status) 
						|| GuardConstants.GuardCardStatus.LOSS.equals(status) 
						|| GuardConstants.GuardCardStatus.NORMAL.equals(status) ){
					guardCardPo.setStatus(status);
					
					logger.info("updataStatus guardCard ={}", guardCardPo);
					guardCardRepository.save(guardCardPo);
					
					guardCard = MyBeanUtil.createBean(guardCardPo, GuardCardVo.class);
				}
			}
		}
	}
	
	public void delete(){
		logger.info("delete cardId ={}", cardId);
		if(ValidateHelper.isNotEmptyString(cardId)){
			guardCardRepository.delete(cardId);
		}
	}
	
	public Boolean isExistCardSn(){
		Boolean result = false;
		if(ValidateHelper.isNotEmptyString(sn)){
			String oemCode = ContextManager.getInstance().getOemCode();
			GuardCardPo guardCardPo = guardCardRepository.findBySnAndOemCode(sn, oemCode);
			if(guardCardPo != null){
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * 分页查询磁卡信息
	 * @param guardCardBo 查询参数
	 * @param currentPage 当前页
	 * @param pageSize 分页大小
	 * @return 磁卡分页数据
	 */
	public DataPageValue<GuardCardVo> listGuardCard(GuardCardBo guardCardBo, Integer currentPage, Integer pageSize) {
		// 参数拷贝
		Map<String, String> extra = MyBeanUtil.bean2Map(guardCardBo);
		
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(currentPage);
		param.setPageSize(pageSize);
		
		Long count = guardCardQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		
		List<GuardCardVo> list = guardCardQueryRepository.queryByPage(param);

		DataPageValue<GuardCardVo> returnPage = new DataPageValue<GuardCardVo>(
				list, count, pageSize, currentPage);
		
		return returnPage;
	}
	
	/**
	 * 获取磁卡详细信息
	 * @return 磁卡详细信息
	 */
	public GuardCardVo getCardDetail() {
		if(ValidateHelper.isNotEmptyString(cardId)) {
			guardCard = guardCardQueryRepository.queryById(cardId);
		}
		
		return guardCard;
	}
	
	protected void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	protected void setGuardCard(GuardCardVo guardCard) {
		this.guardCard = guardCard;
	}

	protected void setSn(String sn) {
		this.sn = sn;
	}
}