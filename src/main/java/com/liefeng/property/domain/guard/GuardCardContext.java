package com.liefeng.property.domain.guard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
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
	
	protected void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	protected void setGuardCard(GuardCardVo guardCard) {
		this.guardCard = guardCard;
	}
}
