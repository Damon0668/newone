package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.guard.GuardDeviceVo;

/**
 * 门禁设备查询
 * 
 * @author 蔡少东
 * @date 2016年3月1日
 */
public interface GuardDeviceQueryRepository extends BaseRepository<GuardDeviceVo> {
	
	/**
	 * 根据磁卡ID查询授权设备
	 * @param cardId 磁卡ID
	 * @return 授权设备列表
	 */
	public List<GuardDeviceVo> queryPrivilegeDevice(String cardId);
}
