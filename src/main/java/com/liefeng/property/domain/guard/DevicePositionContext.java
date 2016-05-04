package com.liefeng.property.domain.guard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.StringUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.guard.DevicePositionBo;
import com.liefeng.property.error.GuardErrorCode;
import com.liefeng.property.po.guard.DevicePositionPo;
import com.liefeng.property.repository.guard.DevicePositionRepository;
import com.liefeng.property.repository.mybatis.DevicePositionQueryRepository;
import com.liefeng.property.util.DictionaryUtil;
import com.liefeng.property.vo.guard.DevicePositionVo;
import com.liefeng.property.vo.guard.GuardDeviceVo;

/**
 * 设备位置领域
 * @author 蔡少东
 * @date 2016年4月11日
 */
@Service
@Scope("prototype")
public class DevicePositionContext {

	private static Logger logger = LoggerFactory.getLogger(DevicePositionContext.class);
	
	@Autowired
	private DevicePositionRepository devicePositionRepository;
	
	@Autowired
	private DevicePositionQueryRepository devicePositionQueryRepository;
	
	private DevicePositionVo devicePosition;
	
	private String positionId;
	
	private String projectId;
	
	private String cardId;
	
	private static DevicePositionContext getInstance() {
		DevicePositionContext devicePositionContext =  SpringBeanUtil.getBean(DevicePositionContext.class);
		return devicePositionContext;
	}
	
	public static DevicePositionContext build() {
		DevicePositionContext devicePositionContext = getInstance();
		return devicePositionContext;
	}
	
	public static DevicePositionContext build(DevicePositionVo devicePosition) {
		DevicePositionContext devicePositionContext = getInstance();
		devicePositionContext.setDevicePosition(devicePosition);
		return devicePositionContext;
	}
	
	public static DevicePositionContext loadByProjectId(String projectId) {
		DevicePositionContext devicePositionContext = getInstance();
		devicePositionContext.setProjectId(projectId);
		return devicePositionContext;
	}
	
	public static DevicePositionContext loadById(String positionId) {
		DevicePositionContext devicePositionContext = getInstance();
		devicePositionContext.setPositionId(positionId);
		return devicePositionContext;
	}
	
	public static DevicePositionContext loadByCardId(String cardId) {
		DevicePositionContext devicePositionContext = getInstance();
		devicePositionContext.setCardId(cardId);
		return devicePositionContext;
	}
	
	public void create(){
		
		if(devicePosition != null){
			String oemCode = ContextManager.getInstance().getOemCode();

			DevicePositionPo devicePositionPo = MyBeanUtil.createBean(devicePosition, DevicePositionPo.class);
			devicePositionPo.setId(UUIDGenerator.generate());
			devicePositionPo.setOemCode(oemCode);
			devicePositionRepository.save(devicePositionPo);
		}
	}
	
	public void update(){
		if(devicePosition != null && ValidateHelper.isNotEmptyString(devicePosition.getId())){

			DevicePositionPo devicePositionPo = devicePositionRepository.findOne(devicePosition.getId());
			if(devicePositionPo != null){
				MyBeanUtil.copyBeanNotNull2Bean(devicePosition, devicePositionPo);
				devicePositionRepository.save(devicePositionPo);
			}
		}
	}
	
	public DevicePositionVo get(){
		
		if(ValidateHelper.isNotEmptyString(positionId)){
			DevicePositionPo devicePositionPo = devicePositionRepository.getOne(positionId);
			if(devicePositionPo != null){
				devicePosition = MyBeanUtil.createBean(devicePositionPo, DevicePositionVo.class);
			}
		}
		
		return devicePosition;
	}
	
	@Transactional
	public void delete(){
		if(ValidateHelper.isNotEmptyString(positionId)){
			
			List<GuardDeviceVo> guardDeviceList = GuardDeviceContext.loadByPositionId(positionId).findGuardDevice();
			
			if(ValidateHelper.isNotEmptyCollection(guardDeviceList)){
				throw new LiefengException(GuardErrorCode.POSITION_HAS_DEVICE);
			}
			
			devicePositionRepository.delete(positionId);
		}
	}
	
	public List<DevicePositionVo> findDevicePosition(){
		if(ValidateHelper.isNotEmptyString(projectId)){
			logger.info("projectId = {}", projectId);
			List<DevicePositionPo> list = devicePositionRepository.findByProjectId(projectId);
			logger.info("positionList  = {}", list);
			if(ValidateHelper.isNotEmptyCollection(list)){
				return MyBeanUtil.createList(list, DevicePositionVo.class);
			}
		}
		
		if(ValidateHelper.isNotEmptyString(cardId)){
			logger.info("cardId = {}", cardId);
			List<DevicePositionVo> list = devicePositionQueryRepository.findByCardId(cardId);
			logger.info("positionList  = {}", list);
			if(ValidateHelper.isNotEmptyCollection(list)){
				return list;
			}
		}
		
		return new ArrayList<DevicePositionVo>();
	}
	
	public List<DevicePositionVo> findDevicePosition(DevicePositionBo devicePositionBo){
		if(devicePositionBo != null){
			devicePositionBo.setOemCode(ContextManager.getInstance().getOemCode());
			
			PagingParamVo param = new PagingParamVo();
			
			Map<String, String> extra = MyBeanUtil.bean2Map(devicePositionBo);
			
			param.setExtra(extra);
			param.setPage(1);
			param.setPageSize(Integer.MAX_VALUE);
			Long total = devicePositionQueryRepository.queryByCount(param);
			param.getPager().setRowCount(total);
			
			List<DevicePositionVo> devicePositionList = devicePositionQueryRepository.queryByPage(param);
			return devicePositionList;
		}
		return null;
	}
	
	public DataPageValue<DevicePositionVo> findPosition4Page(DevicePositionBo devicePositionBo, Integer page, Integer size){
		
		devicePositionBo.setOemCode(ContextManager.getInstance().getOemCode());
		
		PagingParamVo param = new PagingParamVo();
		Map<String, String> extra = MyBeanUtil.bean2Map(devicePositionBo);
		if(ValidateHelper.isNotEmptyCollection(devicePositionBo.getProjectIds())){
			extra.put("projectIds", StringUtil.fmtToSqlInCondition(devicePositionBo.getProjectIds()));
		}
		param.setExtra(extra);
		param.setPage(page);
		param.setPageSize(size);
		Long total = devicePositionQueryRepository.queryByCount(param);
		param.getPager().setRowCount(total);
		
		List<DevicePositionVo> devicePositionList = devicePositionQueryRepository.queryByPage(param);
		
		devicePositionList = DictionaryUtil.transformDicValueToDicName(devicePositionList);
		
		return new DataPageValue<DevicePositionVo>(devicePositionList, total, page, size);
	}
	
	
	public List<DevicePositionVo> findPosition4Type(String projectId, String deviceType){
		
		List<DevicePositionPo> positionList = devicePositionRepository.findByProjectIdAndDeviceType(projectId, deviceType);
		if(ValidateHelper.isNotEmptyCollection(positionList)){
			return MyBeanUtil.createList(positionList, DevicePositionVo.class);
		}
		
		return new ArrayList<DevicePositionVo>();
	}
	
	public List<DevicePositionVo> findPosition4Type(String deviceType){
		
		String oemCode = ContextManager.getInstance().getOemCode();
		
		List<DevicePositionPo> positionList = devicePositionRepository.findByDeviceTypeAndOemCode(deviceType, oemCode);
		
		if(ValidateHelper.isNotEmptyCollection(positionList)){
			return MyBeanUtil.createList(positionList, DevicePositionVo.class);
		}
		
		return new ArrayList<DevicePositionVo>();
	}

	protected void setDevicePosition(DevicePositionVo devicePosition) {
		this.devicePosition = devicePosition;
	}

	protected void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	protected void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	protected String getCardId() {
		return cardId;
	}

	protected void setCardId(String cardId) {
		this.cardId = cardId;
	}
}
