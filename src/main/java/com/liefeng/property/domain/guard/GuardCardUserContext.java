package com.liefeng.property.domain.guard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.repository.guard.GuardCardUserRepository;
import com.liefeng.property.vo.guard.GuardCardUserVo;

/**
 * 磁卡用户领域模型
 * @author Huangama
 * @date 2016-2-29
 */
@Service
@Scope("prototype")
public class GuardCardUserContext {

	@Autowired
	private GuardCardUserRepository guardCardUserRepository;
	
	/**
	 * 磁卡ID
	 */
	private String cardId;
	
	/**
	 * 磁卡用户对象
	 */
	private GuardCardUserVo guardCardUser;
	
	private static GuardCardUserContext getInstance() {
		GuardCardUserContext guardCardUserContext =  SpringBeanUtil.getBean(GuardCardUserContext.class);
		return guardCardUserContext;
	}
	
	public static GuardCardUserContext build(GuardCardUserVo guardCardUser) {
		GuardCardUserContext guardCardUserContext = getInstance();
		guardCardUserContext.setGuardCardUser(guardCardUser);
		return guardCardUserContext;
	}
	
	public static GuardCardUserContext loadByCardId(String cardId) {
		GuardCardUserContext guardCardUserContext = getInstance();
		guardCardUserContext.setCardId(cardId);
		return guardCardUserContext;
	}
	
	protected void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	protected void setGuardCardUser(GuardCardUserVo guardCardUser) {
		this.guardCardUser = guardCardUser;
	}
	
}
