package com.liefeng.property.domain.guard;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.base.vo.message.PushedMsgVo;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.guard.DevicePositionBo;
import com.liefeng.property.po.guard.DevicePositionPo;
import com.liefeng.property.repository.guard.DevicePositionRepository;
import com.liefeng.property.repository.mybatis.DevicePositionQueryRepository;
import com.liefeng.property.vo.guard.DevicePositionVo;

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
	
	public void create(){
		if(devicePosition != null){
			DevicePositionPo devicePositionPo = MyBeanUtil.createBean(devicePosition, DevicePositionPo.class);
			devicePositionPo.setId(UUIDGenerator.generate());
			devicePositionPo.setOemCode(ContextManager.getInstance().getOemCode());
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
	
	@javax.transaction.Transactional
	public void delete(){
		if(ValidateHelper.isNotEmptyString(positionId)){
			devicePositionRepository.delete(positionId);
			//TODO 删除授权信息表数据
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
		return null;
	}
	
	public DataPageValue<DevicePositionVo> findPosition4Page(DevicePositionBo devicePositionBo, Integer page, Integer size){
		
		devicePositionBo.setOemCode(ContextManager.getInstance().getOemCode());
		
		PagingParamVo param = new PagingParamVo();
		
		Map<String, String> extra = MyBeanUtil.bean2Map(devicePositionBo);

		param.setExtra(extra);
		param.setPage(page);
		param.setPageSize(size);
		
		Long total = devicePositionQueryRepository.queryByCount(param);
		
		List<DevicePositionVo> PushedMsgList = devicePositionQueryRepository.queryByPage(param);
		
		return new DataPageValue<DevicePositionVo>(PushedMsgList, total, page, size);
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
	
	
}
