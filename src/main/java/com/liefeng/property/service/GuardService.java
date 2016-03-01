package com.liefeng.property.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.base.constant.DeviceConstants;
import com.liefeng.base.vo.device.DeviceVo;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.base.ICheckService;
import com.liefeng.intf.property.IGuardSerivce;
import com.liefeng.intf.service.tcc.ITccMsgService;
import com.liefeng.mq.type.TccBasicEvent;
import com.liefeng.property.bo.guard.GuardDeviceBo;
import com.liefeng.property.domain.guard.GuardDeviceContext;
import com.liefeng.property.vo.guard.GuardDeviceVo;

/**
 * 门禁服务
 * @author 蔡少东
 * @date 2016年3月1日
 */
@Service
public class GuardService implements IGuardSerivce{
	
	private Logger logger = LoggerFactory.getLogger(GuardService.class);
	
	@Autowired
	private ICheckService checkService;
	
	@Autowired
	private ITccMsgService tccMsgService;

	@Transactional(rollbackOn=Exception.class)
	@Override
	public void createGuardDevice(GuardDeviceVo guardDevice) {
		logger.info("createGuardDevice check GuardDeviceVo = {}", guardDevice);
		
		DeviceVo deviceVo = checkService.createDeviceCheck(guardDevice);
		guardDevice.setDeviceGlobalId(deviceVo.getGlobalId());
		guardDevice.setRunStatus(DeviceConstants.RunStatus.NORMAL);
		GuardDeviceContext.build(guardDevice).create();
		
		logger.info("createGuardDevice sendTccMsg GuardDeviceVo = {}", guardDevice);
		tccMsgService.sendTccMsg(TccBasicEvent.CREATE_DEVICE, guardDevice.toString());
	}

	@Transactional(rollbackOn=Exception.class)
	@Override
	public void updateGuradDevice(GuardDeviceVo guardDevice) {
		guardDevice.setGlobalId(guardDevice.getDeviceGlobalId());
		
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
				GuardDeviceVo guardDeviceVo = GuardDeviceContext.loadById(guardDeviceId).get();
				guardDeviceVo.setGlobalId(guardDeviceVo.getDeviceGlobalId());
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

}
