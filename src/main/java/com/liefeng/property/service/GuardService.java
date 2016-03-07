package com.liefeng.property.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.base.constant.DeviceConstants;
import com.liefeng.base.vo.device.DeviceVo;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.base.ICheckService;
import com.liefeng.intf.base.device.IDeviceService;
import com.liefeng.intf.property.IGuardService;
import com.liefeng.intf.service.tcc.ITccMsgService;
import com.liefeng.mq.type.TccBasicEvent;
import com.liefeng.property.bo.guard.GuardDeviceBo;
import com.liefeng.property.domain.guard.GuardCardPrivilegeContext;
import com.liefeng.property.domain.guard.GuardDeviceContext;
import com.liefeng.property.domain.household.VisitorContext;
import com.liefeng.property.vo.guard.GuardCardVo;
import com.liefeng.property.vo.guard.GuardDeviceVo;
import com.liefeng.property.vo.household.VisitorVo;

/**
 * 门禁服务
 * @author 蔡少东
 * @date 2016年3月1日
 */
@Service
public class GuardService implements IGuardService{
	
	private Logger logger = LoggerFactory.getLogger(GuardService.class);
	
	@Autowired
	private ICheckService checkService;
	
	@Autowired
	private ITccMsgService tccMsgService;
	
	@Autowired
	private IDeviceService deviceService;


	@Transactional(rollbackOn=Exception.class)
	@Override
	public void createGuardDevice(GuardDeviceVo guardDevice) {
		guardDevice.setType(DeviceConstants.Type.GUARD);
		
		logger.info("createGuardDevice check GuardDeviceVo = {}", guardDevice);
		
		DeviceVo deviceVo = checkService.createDeviceCheck(guardDevice);
		guardDevice.setDeviceGlobalId(deviceVo.getGlobalId());
		
		GuardDeviceContext.build(guardDevice).create();
		
		logger.info("createGuardDevice sendTccMsg GuardDeviceVo = {}", guardDevice);
		
		tccMsgService.sendTccMsg(TccBasicEvent.CREATE_DEVICE, guardDevice.toString());
	}

	@Transactional(rollbackOn=Exception.class)
	@Override
	public void updateGuradDevice(GuardDeviceVo guardDevice) {

		logger.info("updateGuradDevice check GuardDeviceVo = {}", guardDevice);
		
		checkService.updateDeviceCheck(guardDevice);
		GuardDeviceContext.build(guardDevice).update();
		
		logger.info("updateGuradDevice sendTccMsg GuardDeviceVo = {}", guardDevice);
		
		tccMsgService.sendTccMsg(TccBasicEvent.UPDATE_DEVICE, guardDevice.toString());
		
	}

	@Transactional(rollbackOn=Exception.class)
	@Override
	public void deleteGuradDevice(List<String> guardDeviceIds) {
		if(ValidateHelper.isNotEmptyCollection(guardDeviceIds)){
			for (String guardDeviceId : guardDeviceIds) {
				
				logger.info("deleteGuradDevice guardDeviceId = {}", guardDeviceId);
				
				GuardDeviceVo guardDeviceVo = GuardDeviceContext.loadById(guardDeviceId).get();
				
				checkService.deleteDeviceCheck(guardDeviceVo);
				GuardDeviceContext.loadById(guardDeviceId).delete();
				tccMsgService.sendTccMsg(TccBasicEvent.DELETE_DEVICE, guardDeviceVo.toString());
			}
		}
	}

	@Override
	public DataPageValue<GuardDeviceVo> listGuardDevice(GuardDeviceBo guardDeviceBo, int page, int size) {
		return GuardDeviceContext.build().listGuardDevice(guardDeviceBo, page, size);
	}

	@Override
	public GuardDeviceVo findGuardDevice(String guardDeviceId) {
		GuardDeviceVo guardDeviceVo = GuardDeviceContext.loadById(guardDeviceId).get();
		
		DeviceVo deviceVo = deviceService.getDeviceByGlobalId(guardDeviceVo.getGlobalId());
		//避免拷贝ID
		deviceVo.setId(null);
		MyBeanUtil.copyBeanNotNull2Bean(deviceVo, guardDeviceVo);
		
		return guardDeviceVo;
	}

	@Override
	public Boolean isExistGuardNum(String guardNum) {
		return GuardDeviceContext.build().isExistGuardNum(guardNum);
	}

	@Override
	public void grantGuardCard(String guardCardId, List<String> guardDeviceId) {
		logger.info("grantGuardCard guardCardId = {}, guardDeviceId = {}", guardCardId, guardDeviceId);
		GuardCardPrivilegeContext.loadByCardId(guardCardId).grantGuardCard(guardDeviceId);
	}

	@Override
	public void createVisitorInfo(VisitorVo visitor) {
		VisitorContext.build(visitor).create();
	}

}
