package com.liefeng.property.domain.guard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.repository.guard.GuardDeviceRepository;
import com.liefeng.property.vo.guard.GuardDeviceVo;

/**
 * 门禁设备领域模型
 * @author Huangama
 * @date 2016-2-25
 */
@Service
@Scope("prototype")
public class GuardDeviceContext {

	@Autowired
	private GuardDeviceRepository guardDeviceRepository;
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 设备全局ID
	 */
	private String deviceGlobalId;
	
	/**
	 * 门禁设备对象
	 */
	private GuardDeviceVo guardDevice;
	
	private static GuardDeviceContext getInstance() {
		GuardDeviceContext guardDeviceContext =  SpringBeanUtil.getBean(GuardDeviceContext.class);
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
	
	protected void setId(String id) {
		this.id = id;
	}
	
	protected void setDeviceGlobalId(String deviceGlobalId) {
		this.deviceGlobalId = deviceGlobalId;
	}
	
	protected void setGuardDevice(GuardDeviceVo guardDevice) {
		this.guardDevice = guardDevice;
	}
}
