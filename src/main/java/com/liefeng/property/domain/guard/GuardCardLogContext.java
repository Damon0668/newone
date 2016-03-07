package com.liefeng.property.domain.guard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.repository.guard.GuardCardLogRepository;
import com.liefeng.property.vo.guard.GuardCardLogVo;

/**
 * 磁卡操作日志领域模型
 * @author Huangama
 * @date 2016-2-25
 */
@Service
@Scope("prototype")
public class GuardCardLogContext {

	@Autowired
	private GuardCardLogRepository guardCardLogRepository;
	
	/**
	 * 磁卡ID
	 */
	private String cardId;
	
	/**
	 * 磁卡操作日志对象
	 */
	private GuardCardLogVo guardCardLog;
	
	private static GuardCardLogContext getInstance() {
		GuardCardLogContext guardCardLogContext =  SpringBeanUtil.getBean(GuardCardLogContext.class);
		return guardCardLogContext;
	}
	
	public static GuardCardLogContext build(GuardCardLogVo guardCardLog) {
		GuardCardLogContext guardCardLogContext = getInstance();
		guardCardLogContext.setGuardCardLog(guardCardLog);
		return guardCardLogContext;
	}
	
	public static GuardCardLogContext loadByCardId(String cardId) {
		GuardCardLogContext guardCardLogContext = getInstance();
		guardCardLogContext.setCardId(cardId);
		return guardCardLogContext;
	}
	
	protected void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	protected void setGuardCardLog(GuardCardLogVo guardCardLog) {
		this.guardCardLog = guardCardLog;
	}
	
}
