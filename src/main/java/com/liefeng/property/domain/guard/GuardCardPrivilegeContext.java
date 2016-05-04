package com.liefeng.property.domain.guard;

import java.util.List;

import javax.transaction.Transactional;

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
import com.liefeng.property.po.guard.GuardCardPrivilegePo;
import com.liefeng.property.repository.guard.GuardCardPrivilegeRepository;
import com.liefeng.property.repository.mybatis.GuardCardPrivilegeQueryRepository;
import com.liefeng.property.vo.guard.GuardCardPrivilegeVo;
import com.liefeng.property.vo.guard.GuardDeviceVo;

/**
 * 磁卡权限领域模型
 * @author Huangama
 * @date 2016-2-25
 */
@Service
@Scope("prototype")
public class GuardCardPrivilegeContext {

	private Logger logger = LoggerFactory.getLogger(GuardCardPrivilegeContext.class);
	
	@Autowired
	private GuardCardPrivilegeRepository guardCardPrivilegeRepository;
	
	@Autowired
	private GuardCardPrivilegeQueryRepository guardCardPrivilegeQueryRepository;
	
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
	
	public static GuardCardPrivilegeContext build() {
		GuardCardPrivilegeContext guardCardPrivilegeContext = getInstance();
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
	
	/**
	 * 磁卡授权（新增）
	 * @param cardIds 磁卡ID列表
	 * @param deviceId 设备ID
	 */
	@Transactional(rollbackOn=Exception.class)
	public void grantGuardCard(List<String> cardIds, String deviceId){
		
		if(ValidateHelper.isNotEmptyCollection(cardIds)){
			if(ValidateHelper.isNotEmptyString(deviceId)){
				for (String cardId : cardIds) {
					GuardCardPrivilegePo guardCardPrivilegePo = new GuardCardPrivilegePo();
					guardCardPrivilegePo.setId(UUIDGenerator.generate());
					guardCardPrivilegePo.setCardId(cardId);
					guardCardPrivilegePo.setGuardDeviceId(deviceId);
					guardCardPrivilegePo.setOemCode(ContextManager.getInstance().getOemCode());
					logger.info("grantGuardCard guardCardPrivilegePo = {}", guardCardPrivilegePo);
					guardCardPrivilegeRepository.save(guardCardPrivilegePo);
				}
			}
		}
	}
	
	
	/**
	 * 磁卡授权
	 * @param guardDeviceId 门口机ID列表
	 */
	@Transactional(rollbackOn=Exception.class)
	public void grantGuardCard(List<String> positionIds){
		if(ValidateHelper.isNotEmptyString(cardId)){
			if(ValidateHelper.isNotEmptyCollection(positionIds)){
				
				deleteByCardId();
				
				List<GuardDeviceVo> deviceList =  GuardDeviceContext.build().findGuardDeviceByPositions(positionIds);
				
				for (GuardDeviceVo guardDevice : deviceList) {
					GuardCardPrivilegePo guardCardPrivilegePo = new GuardCardPrivilegePo();
					guardCardPrivilegePo.setId(UUIDGenerator.generate());
					guardCardPrivilegePo.setCardId(cardId);
					guardCardPrivilegePo.setGuardDeviceId(guardDevice.getId());
					guardCardPrivilegePo.setOemCode(ContextManager.getInstance().getOemCode());
					
					logger.info("grantGuardCard guardCardPrivilegePo = {}", guardCardPrivilegePo);
					guardCardPrivilegeRepository.save(guardCardPrivilegePo);
				}
			}
		}
	}

	public List<GuardCardPrivilegeVo> findAllPrivilege(){
		List<GuardCardPrivilegeVo> dataList = null;
		if(ValidateHelper.isNotEmptyString(cardId)){
			logger.info("findAllPrivilege cardId = {}", cardId);
			List<GuardCardPrivilegePo> guardCardPrivilegeList = guardCardPrivilegeRepository.findByCardId(cardId);
			if(ValidateHelper.isNotEmptyCollection(guardCardPrivilegeList)){
				dataList = MyBeanUtil.createList(guardCardPrivilegeList, GuardCardPrivilegeVo.class);
				logger.info("findAllPrivilege dataList = {}", dataList);
			}
		}
		return dataList;
	}
	
	public void deleteByCardId(){
		if(ValidateHelper.isNotEmptyString(cardId)){
			logger.info("deleteByCardId cardId = {}", cardId);
			guardCardPrivilegeRepository.deleteByCardId(cardId);
		}
	}
	
	public void deleteByGuardDeviceId(){
		if(ValidateHelper.isNotEmptyString(guardDeviceId)){
			logger.info("deleteByGuardDeviceId guardDeviceId = {}", cardId);
			guardCardPrivilegeRepository.deleteByGuardDeviceId(guardDeviceId);
		}
	}
	
	public List<String> queryCardIdByPositionId(String positionId){
		return guardCardPrivilegeQueryRepository.queryCardIdByPositionId(positionId);
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
