package com.liefeng.property.domain.guard;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public void create(){
		GuardDevicePo guardDevicePo = MyBeanUtil.createBean(guardDevice, GuardDevicePo.class);
		guardDevicePo.setId(UUIDGenerator.generate());
		guardDevicePo.setCreateTime(new Date());
		guardDevicePo.setOemCode(ContextManager.getInstance().getOemCode());
		
		logger.info("create guardDevice ={}", guardDevice);
		guardDeviceRepository.save(guardDevicePo);
		guardDevice = MyBeanUtil.createBean(guardDevicePo, GuardDeviceVo.class);
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
	
	public void delete(){
		logger.info("delete id ={}", id);
		if(ValidateHelper.isNotEmptyString(id)){
			guardDeviceRepository.delete(id);
		}
	}
	
	public GuardDeviceVo get(){
		if(guardDevice == null){
			if(ValidateHelper.isNotEmptyString(id)){
				GuardDevicePo guardDevicePo = guardDeviceRepository.findOne(id);
				if(guardDevicePo != null){
					guardDevice = MyBeanUtil.createBean(guardDevicePo, GuardDeviceVo.class);
				}
			}
		}
		return guardDevice;
	}
	
	public DataPageValue<GuardDeviceVo> listGuardDevice4Page(GuardDeviceBo guardDeviceBo, Integer currentPage,Integer pageSize){
		
		guardDeviceBo.setOemCode(ContextManager.getInstance().getOemCode());
		currentPage = currentPage == null ? 1 : currentPage;
		pageSize = pageSize == null ? 10 : pageSize;
		
		Map<String, String> extra = MyBeanUtil.bean2Map(guardDeviceBo);
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(currentPage);
		param.setPageSize(pageSize);
		
		Long total = guardDeviceQueryRepository.queryByCount(param);
		
		List<GuardDeviceVo> guardDeviceList = guardDeviceQueryRepository.queryByPage(param);
		
		return new DataPageValue<GuardDeviceVo>(guardDeviceList, total, pageSize, currentPage);
	}
	
	public List<GuardDeviceVo> findGuardDevice(){
		List<GuardDeviceVo> guardDeviceList = null;
		if(ValidateHelper.isNotEmptyString(projectId)){
			Map<String, String> extra = new HashMap<String, String>();
			extra.put("projectId", projectId);
			
			PagingParamVo param = new PagingParamVo();
			param.setExtra(extra);
			param.setPage(1);
			param.setPageSize(Integer.MAX_VALUE);
			
			guardDeviceList = guardDeviceQueryRepository.queryByPage(param);
			
			logger.info("findGuardDevice guardDeviceList = {}", guardDeviceList);
		}
		return guardDeviceList;
	}
	
	public Boolean isExistGuardNum(String guardNum){
		Boolean result = false;
		String oemCode = ContextManager.getInstance().getOemCode();
		GuardDevicePo  guardDevicePo = guardDeviceRepository.findByGuardNumAndOemCode(guardNum, oemCode);
		
		if(guardDevicePo != null){
			result = true;
		}
		
		return result;
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
	
	
}
