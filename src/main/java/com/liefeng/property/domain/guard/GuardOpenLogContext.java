package com.liefeng.property.domain.guard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.repository.guard.GuardOpenLogRepository;
import com.liefeng.property.vo.guard.GuardOpenLogVo;

/**
 * 开门日志领域模型
 * @author Huangama
 * @date 2016-2-29
 */
@Service
@Scope("prototype")
public class GuardOpenLogContext {

	@Autowired
	private GuardOpenLogRepository guardOpenLogRepository;
	
	/**
	 * 磁卡ID
	 */
	private String cardId;
	
	/**
	 * 开门日志对象
	 */
	private GuardOpenLogVo guardOpenLog;
	
	private static GuardOpenLogContext getInstance() {
		GuardOpenLogContext guardOpenLogContext =  SpringBeanUtil.getBean(GuardOpenLogContext.class);
		return guardOpenLogContext;
	}
	
	public static GuardOpenLogContext build(GuardOpenLogVo guardOpenLog) {
		GuardOpenLogContext guardOpenLogContext = getInstance();
		guardOpenLogContext.setGuardOpenLog(guardOpenLog);
		return guardOpenLogContext;
	}
	
	public static GuardOpenLogContext loadByCardId(String cardId) {
		GuardOpenLogContext guardOpenLogContext = getInstance();
		guardOpenLogContext.setCardId(cardId);
		return guardOpenLogContext;
	}
	
	protected void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	protected void setGuardOpenLog(GuardOpenLogVo guardOpenLog) {
		this.guardOpenLog = guardOpenLog;
	}
	
}
