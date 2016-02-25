package com.liefeng.property.domain.guard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.repository.guard.GuardCardPrivilegeRepository;
import com.liefeng.property.vo.guard.GuardCardPrivilegeVo;
import com.liefeng.property.vo.guard.GuardCardVo;

/**
 * 磁卡权限领域模型
 * @author Huangama
 * @date 2016-2-25
 */
@Service
@Scope("prototype")
public class GuardCardPrivilegeContext {

	@Autowired
	private GuardCardPrivilegeRepository guardCardPrivilegeRepository;
	
	/**
	 * 磁卡ID
	 */
	private String cardId;
	
	/**
	 * 门禁设备ID
	 */
	private String guardDeviceId;
	
	/**
	 * 磁卡权限对象
	 */
	private GuardCardPrivilegeVo guardCardPrivilege;
	
	private static GuardCardPrivilegeContext getInstance() {
		GuardCardPrivilegeContext guardCardPrivilegeContext =  SpringBeanUtil.getBean(GuardCardPrivilegeContext.class);
		return guardCardPrivilegeContext;
	}
	
	public static GuardCardPrivilegeContext build(GuardCardPrivilegeVo guardCardPrivilege) {
		GuardCardPrivilegeContext guardCardPrivilegeContext = getInstance();
		guardCardPrivilegeContext.setGuardCardPrivilege(guardCardPrivilege);
		return guardCardPrivilegeContext;
	}
	
	public static GuardCardPrivilegeContext loadByCardId(String cardId) {
		GuardCardPrivilegeContext guardCardPrivilegeContext = getInstance();
		guardCardPrivilegeContext.setCardId(cardId);
		return guardCardPrivilegeContext;
	}
	
	public static GuardCardPrivilegeContext loadByDeviceId(String guardDeviceId) {
		GuardCardPrivilegeContext guardCardPrivilegeContext = getInstance();
		guardCardPrivilegeContext.setGuardDeviceId(guardDeviceId);
		return guardCardPrivilegeContext;
	}
	
	protected void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	protected void setGuardCardPrivilege(GuardCardPrivilegeVo guardCardPrivilege) {
		this.guardCardPrivilege = guardCardPrivilege;
	}
	
	protected void setGuardDeviceId(String guardDeviceId) {
		this.guardDeviceId = guardDeviceId;
	}
}
