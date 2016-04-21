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
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.base.ICheckService;
import com.liefeng.intf.base.device.IDeviceService;
import com.liefeng.intf.property.IGuardService;
import com.liefeng.intf.service.tcc.ITccMsgService;
import com.liefeng.mq.type.TccBasicEvent;
import com.liefeng.property.bo.guard.DevicePositionBo;
import com.liefeng.property.bo.guard.GuardCardBo;
import com.liefeng.property.bo.guard.GuardDeviceBo;
import com.liefeng.property.bo.guard.GuardResidentBo;
import com.liefeng.property.constant.GuardConstants;
import com.liefeng.property.domain.guard.AttendantContext;
import com.liefeng.property.domain.guard.CameraContext;
import com.liefeng.property.domain.guard.DevicePositionContext;
import com.liefeng.property.domain.guard.GuardCardContext;
import com.liefeng.property.domain.guard.GuardCardLogContext;
import com.liefeng.property.domain.guard.GuardCardPrivilegeContext;
import com.liefeng.property.domain.guard.GuardCardUserContext;
import com.liefeng.property.domain.guard.GuardDeviceContext;
import com.liefeng.property.domain.household.ResidentContext;
import com.liefeng.property.domain.household.VisitorContext;
import com.liefeng.property.vo.guard.AttendantVo;
import com.liefeng.property.vo.guard.CameraVo;
import com.liefeng.property.vo.guard.DevicePositionVo;
import com.liefeng.property.vo.guard.GuardCardLogVo;
import com.liefeng.property.vo.guard.GuardCardPrivilegeVo;
import com.liefeng.property.vo.guard.GuardCardUserVo;
import com.liefeng.property.vo.guard.GuardCardVo;
import com.liefeng.property.vo.guard.GuardDeviceVo;
import com.liefeng.property.vo.guard.GuardResidentVo;
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

		logger.info("createGuardDevice check GuardDeviceVo = {}", guardDevice);
		
		guardDevice.setType(guardDevice.getGuardType());
		guardDevice.setSn(guardDevice.getGuardNum());
		guardDevice.setName(guardDevice.getGuardName());
		guardDevice.setInstallPosition(
				DevicePositionContext
				.loadById(guardDevice.getPositionId())
				.get()
				.getName());
		
		DeviceVo deviceVo = checkService.createDeviceCheck(guardDevice);
		
		guardDevice.setDeviceGlobalId(deviceVo.getGlobalId());
		GuardDeviceContext.build(guardDevice).create();
		
		if(DeviceConstants.Type.CAMERA.equals(guardDevice.getType())){
			//添加摄像头
			CameraVo camera = new CameraVo();
			camera.setDeviceGlobalId(deviceVo.getGlobalId());
			camera.setProjectId(guardDevice.getProjectId());
			camera.setCameraSn(guardDevice.getGuardNum());
			camera.setPositionId(guardDevice.getPositionId());
			camera.setType(GuardConstants.GuardCameraType.ATTACHMENT);
			CameraContext.build(camera).create();
		}
		
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
				
				GuardCardPrivilegeContext.loadByDeviceId(guardDeviceId).deleteByGuardDeviceId();
				
				tccMsgService.sendTccMsg(TccBasicEvent.DELETE_DEVICE, guardDeviceVo.toString());
			}
		}
	}

	@Override
	public DataPageValue<GuardDeviceVo> listGuardDevice(GuardDeviceBo guardDeviceBo, int page, int size) {
		return GuardDeviceContext.build().listGuardDevice4Page(guardDeviceBo, page, size);
	}

	@Override
	public GuardDeviceVo findGuardDevice(String guardDeviceId) {
		GuardDeviceVo guardDeviceVo = GuardDeviceContext.loadById(guardDeviceId).get();
		
		DeviceVo deviceVo = deviceService.getDeviceByGlobalId(guardDeviceVo.getDeviceGlobalId());
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

	@Override
	public DataPageValue<GuardResidentVo> listGuardRedisent(GuardResidentBo guardResidentBo, Integer pageSize, Integer currentPage) {
			
		DataPageValue<GuardResidentVo> dataPageValue = ResidentContext.build().listGuardResident4Page(guardResidentBo, pageSize, currentPage);

		return dataPageValue;
	}

	@Transactional(rollbackOn=Exception.class)
	@Override
	public void createGuardCard(GuardCardUserVo guardCardUser, GuardCardVo guardCard, List<String> guardDeviceIds) {
		
		guardCard = GuardCardContext.build(guardCard).create();
		
		guardCardUser.setCardId(guardCard.getId());
		GuardCardUserContext.build(guardCardUser).create();
		
		GuardCardPrivilegeContext.loadByCardId(guardCard.getId()).grantGuardCard(guardDeviceIds);

	}

	@Override
	public void updateGuardCardStatus(String cardId, String status) {
		GuardCardContext.loadById(cardId).updataStatus(status);
	}

	@Override
	public Boolean isExistCardSn(String sn) {
		return GuardCardContext.loadBySn(sn).isExistCardSn();
	}

	@Override
	public List<GuardDeviceVo> findGuardDevice(String projectId, String type) {
		logger.info("findGuardDevice projectId = {}, type = {}", projectId, type);
		return GuardDeviceContext.loadByProjectId(projectId).findGuardDevice(type);
	}

	@Override
	public GuardCardVo findGuardCard(String cardId) {
		logger.info("findGuardCard cardId = {}", cardId);
		return GuardCardContext.loadById(cardId).get();
	}

	@Override
	public GuardCardUserVo findGuardCardUser(String cardId) {
		logger.info("findGuardCardUser cardId = {}", cardId);
		return GuardCardUserContext.loadByCardId(cardId).get();
	}

	@Override
	public List<GuardCardPrivilegeVo> findGuardCarPrivilege(String cardId) {
		logger.info("findGuardCarPrivilege cardId = {}", cardId);
		return GuardCardPrivilegeContext.loadByCardId(cardId).findAllPrivilege();
	}

	@Transactional(rollbackOn=Exception.class)
	@Override
	public void updateGuardCard(GuardCardVo guardCard, List<String> guardDeviceIds) {
		GuardCardContext.build(guardCard).updata();
		GuardCardPrivilegeContext.loadByCardId(guardCard.getId()).grantGuardCard(guardDeviceIds);
	}

	@Override
	public void createDevicePosition(DevicePositionVo devicePosition) throws LiefengException{
		DevicePositionContext.build(devicePosition).create();
	}

	@Override
	public void updateDevicePosition(DevicePositionVo devicePosition) throws LiefengException{
		DevicePositionContext.build(devicePosition).update();
	}

	@Override
	public void deleteDevicePosition(String id) {
		DevicePositionContext.loadById(id).delete();
	}

	@Override
	public List<DevicePositionVo> findDevicePosition(DevicePositionBo devicePositionBo) {
		return DevicePositionContext.build().findDevicePosition(devicePositionBo);
	}

	@Override
	public DataPageValue<DevicePositionVo> findDevicePosition(DevicePositionBo devicePositionBo, Integer page, Integer size) {
		return DevicePositionContext.build().findPosition4Page(devicePositionBo, page, size);
	}

	@Override
	public DataPageValue<GuardCardVo> listGuardCard(GuardCardBo guardCardBo, Integer pageSize, Integer currentPage) {
		GuardCardContext guardCardContext = GuardCardContext.build();
		return guardCardContext.listGuardCard(guardCardBo, currentPage, pageSize);
	}

	@Override
	public GuardCardVo getGuardCardDetail(String cardId) {
		GuardCardContext guardCardContext = GuardCardContext.loadById(cardId);
		return guardCardContext.getCardDetail();
	}

	@Override
	public List<GuardDeviceVo> findGuardDeviceByProjectId(String projectId) {
		return findGuardDevice(projectId,null);
	}	
	
	@Override
	public DataPageValue<GuardCardLogVo> listGuardCardLog(String cardId, Integer currentPage, Integer pageSize) {
		GuardCardLogContext guardCardLogContext = GuardCardLogContext.build();
		return guardCardLogContext.listGuardCardLog(cardId, currentPage, pageSize);
	}

	@Override
	public AttendantVo createAttendant(AttendantVo attendantVo) {
		return AttendantContext.build(attendantVo).create();
	}

	@Override
	public AttendantVo getAttendant(String id) {
		return AttendantContext.loadById(id).getAttendantVo();
	}

	@Override
	public DataPageValue<AttendantVo> listAttendants(String projectId, String manageProjectIds, String name, String phone,
			String serviceType, Integer page, Integer size) {
		return AttendantContext.build().findByPage(projectId, manageProjectIds, name, phone, serviceType, page, size);
	}

	@Override
	public AttendantVo updateAttendant(AttendantVo attendantVo) {
		return AttendantContext.build(attendantVo).update();
	}

	@Override
	public List<GuardDeviceVo> listPrivilegeDevice(String cardId) {
		return GuardDeviceContext.build().listPrivilegeDevice(cardId);
	}

}