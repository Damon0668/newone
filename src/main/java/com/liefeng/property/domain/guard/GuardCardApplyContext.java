package com.liefeng.property.domain.guard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.repository.guard.GuardCardApplyRepository;
import com.liefeng.property.vo.guard.GuardCardApplyVo;

/**
 * 磁卡业务申请领域模型
 * @author Huangama
 * @date 2016-2-25
 */
@Service
@Scope("prototype")
public class GuardCardApplyContext {

	@Autowired
	private GuardCardApplyRepository guardCardApplyRepository;
	
	/**
	 * 磁卡ID
	 */
	private String cardId;
	
	/**
	 * 磁卡业务申请对象
	 */
	private GuardCardApplyVo guardCardApply;
	
	private static GuardCardApplyContext getInstance() {
		GuardCardApplyContext guardCardApplyContext =  SpringBeanUtil.getBean(GuardCardApplyContext.class);
		return guardCardApplyContext;
	}
	
	public static GuardCardApplyContext build(GuardCardApplyVo guardCardApply) {
		GuardCardApplyContext guardCardApplyContext = getInstance();
		guardCardApplyContext.setGuardCardApply(guardCardApply);
		return guardCardApplyContext;
	}
	
	public static GuardCardApplyContext loadByCardId(String cardId) {
		GuardCardApplyContext guardCardApplyContext = getInstance();
		guardCardApplyContext.setCardId(cardId);
		return guardCardApplyContext;
	}
	
	protected void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	protected void setGuardCardApply(GuardCardApplyVo guardCardApply) {
		this.guardCardApply = guardCardApply;
	}
	
}
