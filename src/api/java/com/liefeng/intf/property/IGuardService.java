package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.bo.guard.GuardDeviceBo;
import com.liefeng.property.vo.guard.GuardDeviceVo;

/**
 * 门禁服务
 * 对外暴露接口
 * @author 蔡少东
 * @date 2016年3月1日
 */
public interface IGuardService {
	
	/**
	 * 添加门禁设备
	 * @param guardDevice 门禁设备信息
	 */
	public void createGuardDevice(GuardDeviceVo guardDevice);
	
	/**
	 * 更新门禁设备
	 * @param guardDevice
	 */
	public void updateGuradDevice(GuardDeviceVo guardDevice);
	
	/**
	 * 删除门禁设备
	 * @param guardDeviceIds 门禁设备id列表
	 */
	public void deleteGuradDevice(List<String> guardDeviceIds);
	
	/**
	 * 分页查询门禁设备
	 * @param guardDeviceBo 查询条件
	 * @param page 第几页
	 * @param size 每页数据条数
	 * @return
	 */
	public DataPageValue<GuardDeviceVo> listGuardDevice(GuardDeviceBo guardDeviceBo, int page, int size);
	
	/**
	 * 查找门禁设备
	 * @param guardDeviceId 门禁设备ID
	 * @return
	 */
	public GuardDeviceVo findGuardDevice(String guardDeviceId);
	
	/**
	 * 检测门禁设备号是否存储
	 * @param guardNum 门禁设备号
	 * @return true 存在 false 不存在
	 */
	public Boolean isExistGuardNum(String guardNum);
}
