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
import com.liefeng.property.po.guard.GuardCardUserPo;
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
	private static Logger logger = LoggerFactory.getLogger(GuardCardUserContext.class);

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
	
	public GuardCardUserVo get(){
		if(guardCardUser == null){
			if(ValidateHelper.isNotEmptyString(cardId)){
				logger.info("get cardId = {}", cardId);
				GuardCardUserPo guardCardUserPo = guardCardUserRepository.findByCardId(cardId);
				if(guardCardUserPo != null){
					guardCardUser = MyBeanUtil.createBean(guardCardUserPo, GuardCardUserVo.class);
					logger.info("get guardCardUser = {}", guardCardUser);
				}
			}
		}
		return guardCardUser;
	}
	
	public void create(){
		if(guardCardUser != null){
			
			GuardCardUserPo guardCardUserPo = MyBeanUtil.createBean(guardCardUser, GuardCardUserPo.class);
			guardCardUserPo.setId(UUIDGenerator.generate());
			guardCardUserPo.setOemCode(ContextManager.getInstance().getOemCode());
			guardCardUserPo.setCreateTime(new Date());
			
			logger.info("create guardCardUserPo = {}", guardCardUserPo);
			guardCardUserRepository.save(guardCardUserPo);
			
		}
	}
	
	
	protected void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	protected void setGuardCardUser(GuardCardUserVo guardCardUser) {
		this.guardCardUser = guardCardUser;
	}
	
}
