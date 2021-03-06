package com.liefeng.property.domain.guard;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.guard.GuardDeviceBo;
import com.liefeng.property.po.guard.GuardDevicePo;
import com.liefeng.property.repository.guard.GuardDeviceRepository;
import com.liefeng.property.repository.mybatis.GuardDeviceQueryRepository;
import com.liefeng.property.vo.guard.GuardDeviceVo;

/**
 * 门禁设备领域模型
 * @author Huangama
 * @date 2016-2-25
 */
@Service
@Scope("prototype")
public class GuardDeviceContext {
	private static Logger logger = LoggerFactory.getLogger(GuardDeviceContext.class);
	
	@Autowired
	private GuardDeviceRepository guardDeviceRepository;
	
	@Autowired
	private GuardDeviceQueryRepository guardDeviceQueryRepository;
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 设备全局ID
	 */
	private String deviceGlobalId;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 位置ID
	 */
	private String positionId;
	
	/**
	 * 门禁设备对象
	 */
	private GuardDeviceVo guardDevice;
	
	private static GuardDeviceContext getInstance() {
		GuardDeviceContext guardDeviceContext =  SpringBeanUtil.getBean(GuardDeviceContext.class);
		return guardDeviceContext;
	}
	
	public static GuardDeviceContext build() {
		GuardDeviceContext guardDeviceContext = getInstance();
		return guardDeviceContext;
	}
	
	public static GuardDeviceContext build(GuardDeviceVo guardDevice) {
		GuardDeviceContext guardDeviceContext = getInstance();
		guardDeviceContext.setGuardDevice(guardDevice);
		return guardDeviceContext;
	}
	
	public static GuardDeviceContext loadById(String id) {
		GuardDeviceContext guardDeviceContext = getInstance();
		guardDeviceContext.setId(id);
		return guardDeviceContext;
	}
	
	public static GuardDeviceContext loadByDeviceGlobalId(String deviceGlobalId) {
		GuardDeviceContext guardDeviceContext = getInstance();
		guardDeviceContext.setDeviceGlobalId(deviceGlobalId);
		return guardDeviceContext;
	}
	
	public static GuardDeviceContext loadByProjectId(String projectId) {
		GuardDeviceContext guardDeviceContext = getInstance();
		guardDeviceContext.setProjectId(projectId);
		return guardDeviceContext;
	}
	
	public static GuardDeviceContext loadByPositionId(String positionId) {
		GuardDeviceContext guardDeviceContext = getInstance();
		guardDeviceContext.setPositionId(positionId);
		return guardDeviceContext;
	}
	
	@Transactional
	public void create(){
		if(guardDevice != null ){
			GuardDevicePo guardDevicePo = MyBeanUtil.createBean(guardDevice, GuardDevicePo.class);
			guardDevicePo.setId(UUIDGenerator.generate());
			guardDevicePo.setCreateTime(new Date());
			guardDevicePo.setOemCode(ContextManager.getInstance().getOemCode());
			
			logger.info("create guardDevice ={}", guardDevice);
			
			guardDeviceRepository.save(guardDevicePo);
			guardDevice = MyBeanUtil.createBean(guardDevicePo, GuardDeviceVo.class);
			
			//此设备关联的位置，已授权的磁卡需要添加新增设备的授权
			List<String> cardIdList = GuardCardPrivilegeContext.build().queryCardIdByPositionId(guardDevice.getPositionId());
			
			GuardCardPrivilegeContext.build().grantGuardCard(cardIdList, guardDevice.getId());
		}
	}
	
	public void update(){
		
		if(ValidateHelper.isNotEmptyString(guardDevice.getId())){
			GuardDevicePo guardDevicePo = guardDeviceRepository.findOne(guardDevice.getId());
			
			if(guardDevicePo != null){
				MyBeanUtil.copyBeanNotNull2Bean(guardDevice, guardDevicePo);
				
				logger.info("update guardDevice ={}", guardDevice);
				guardDeviceRepository.save(guardDevicePo);
				guardDevice = MyBeanUtil.createBean(guardDevicePo, GuardDeviceVo.class);
			}
			
		}
	}
	
	@Transactional
	public void delete(){
		logger.info("delete id ={}", id);
		if(ValidateHelper.isNotEmptyString(id)){
			GuardCardPrivilegeContext.loadByDeviceId(id).deleteByGuardDeviceId();
			guardDeviceRepository.delete(id);
		}
	}
	
	public GuardDeviceVo get(){
		if(guardDevice == null){
			if(ValidateHelper.isNotEmptyString(id)){
				guardDevice = guardDeviceQueryRepository.findOne(id);
			}
		}
		return guardDevice;
	}
	
	public DataPageValue<GuardDeviceVo> listGuardDevice4Page(GuardDeviceBo guardDeviceBo, Integer currentPage,Integer pageSize){
		
		guardDeviceBo.setOemCode(ContextManager.getInstance().getOemCode());
		
		currentPage = currentPage == null ? 1 : currentPage;
		pageSize = pageSize == null ? 10 : pageSize;
		
		Map<String, String> extra = MyBeanUtil.bean2Map(guardDeviceBo);
		if(ValidateHelper.isNotEmptyCollection(guardDeviceBo.getProjectIds())){
			extra.put("projectIds", StringUtil.fmtToSqlInCondition(guardDeviceBo.getProjectIds()));
		}
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(currentPage);
		param.setPageSize(pageSize);
		
		Long total = guardDeviceQueryRepository.queryByCount(param);
		param.getPager().setRowCount(total);
		
		List<GuardDeviceVo> guardDeviceList = guardDeviceQueryRepository.queryByPage(param);
		
		return new DataPageValue<GuardDeviceVo>(guardDeviceList, total, pageSize, currentPage);
	}
	
	public List<GuardDeviceVo> findGuardDevice(String type){
		List<GuardDeviceVo> guardDeviceList = null;
		if(ValidateHelper.isNotEmptyString(projectId) && ValidateHelper.isNotEmptyString(type)){
			Map<String, String> extra = new HashMap<String, String>();
			extra.put("projectId", projectId);
			extra.put("guardType", type);
			
			PagingParamVo param = new PagingParamVo();
			param.setExtra(extra);
			param.setPage(1);
			param.setPageSize(Integer.MAX_VALUE);
			
			Long total = guardDeviceQueryRepository.queryByCount(param);
			// 设置数据总行数，用于计算偏移量
			param.getPager().setRowCount(total);
			
			guardDeviceList = guardDeviceQueryRepository.queryByPage(param);
			
			logger.info("findGuardDevice guardDeviceList = {}", guardDeviceList);
		}
		return guardDeviceList;
	}
	
	public List<GuardDeviceVo> findGuardDeviceByPositions(List<String> positionIds){
		if(ValidateHelper.isNotEmptyCollection(positionIds)){
			List<GuardDevicePo> deviceList = guardDeviceRepository.findByPositionIdIn(positionIds);
			if(ValidateHelper.isNotEmptyCollection(deviceList)){
				return MyBeanUtil.createList(deviceList, GuardDeviceVo.class);
			}
		}
		return new ArrayList<GuardDeviceVo>();
	}
	
	/**
	 * 查找门禁设备
	 * @return
	 */
	public List<GuardDeviceVo> findGuardDevice(){
		if(ValidateHelper.isNotEmptyString(positionId)){
			List<GuardDevicePo> deviceList = guardDeviceRepository.findByPositionId(positionId);
			if(ValidateHelper.isNotEmptyCollection(deviceList)){
				return MyBeanUtil.createList(deviceList, GuardDeviceVo.class);
			}
		}
		return new ArrayList<GuardDeviceVo>();
	}
	
	
	public Boolean isExistGuardNum(String guardNum){
		Boolean result = false;
		
		String oemCode = ContextManager.getInstance().getOemCode();
		List<GuardDevicePo>  guardDeviceList = guardDeviceRepository.findByGuardNumAndOemCode(guardNum, oemCode);
	
		if(ValidateHelper.isNotEmptyCollection(guardDeviceList)){
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 根据磁卡ID获取授权设备
	 * @param cardId 磁卡ID
	 * @return 授权设备列表
	 */
	public List<GuardDeviceVo> listPrivilegeDevice(String cardId) {
		List<GuardDeviceVo> dataList = guardDeviceQueryRepository.queryPrivilegeDevice(cardId);
		dataList = ValidateHelper.isNotEmptyCollection(dataList) ? dataList : new ArrayList<GuardDeviceVo>();
		return dataList;
	}

	protected void setId(String id) {
		this.id = id;
	}
	
	protected void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	protected void setDeviceGlobalId(String deviceGlobalId) {
		this.deviceGlobalId = deviceGlobalId;
	}
	
	protected void setGuardDevice(GuardDeviceVo guardDevice) {
		this.guardDevice = guardDevice;
	}

	protected String getPositionId() {
		return positionId;
	}

	protected void setPositionId(String positionId) {
		this.positionId = positionId;
	}
}
