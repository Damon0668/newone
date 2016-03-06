package com.liefeng.property.domain.guard;

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
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.property.po.guard.GuardCardPo;
import com.liefeng.property.repository.guard.GuardCardRepository;
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
	
	/**
	 * 磁卡ID
	 */
	private String cardId;
	
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
	
	public static GuardCardContext loadById(String cardId) {
		GuardCardContext guardCardContext = getInstance();
		guardCardContext.setCardId(cardId);
		return guardCardContext;
	}
	
	public GuardCardVo get(){
		if(guardCard == null){
			if(ValidateHelper.isNotEmptyString(cardId)){
				GuardCardPo guardCardPo = guardCardRepository.findOne(cardId);
				if(guardCardPo != null){
					guardCard = MyBeanUtil.createBean(guardCardPo, GuardCardVo.class);
				}
			}
		}
		return guardCard;
	}
	
	public void create(){
		
		GuardCardPo guardCardPo = MyBeanUtil.createBean(guardCard, GuardCardPo.class);
		guardCardPo.setId(UUIDGenerator.generate());
		guardCardPo.setStatus("");
		guardCardPo.setCreateTime(new Date());
		guardCardPo.setOemCode(ContextManager.getInstance().getOemCode());
		
		logger.info("create guardCard ={}", guardCard);
		guardCardRepository.save(guardCardPo);
		guardCard = MyBeanUtil.createBean(guardCardPo, GuardCardVo.class);
	}
	
	public void updata(){
		
		if(ValidateHelper.isNotEmptyString(guardCard.getId())){
			GuardCardPo guardCardPo = guardCardRepository.findOne(guardCard.getId());
			
			if(guardCardPo != null){
				MyBeanUtil.copyBeanNotNull2Bean(guardCard, guardCardPo);
				
				logger.info("update guardCard ={}", guardCard);
				guardCardRepository.save(guardCardPo);
				guardCard = MyBeanUtil.createBean(guardCardPo, GuardCardVo.class);
			}
			
		}
	}
	
	public void delete(){
		logger.info("delete cardId ={}", cardId);
		if(ValidateHelper.isNotEmptyString(cardId)){
			guardCardRepository.delete(cardId);
		}
	}
	
	protected void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	protected void setGuardCard(GuardCardVo guardCard) {
		this.guardCard = guardCard;
	}
}
