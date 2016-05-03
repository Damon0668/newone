package com.liefeng.property.repository.guard;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.guard.GuardDevicePo;

/**
 * 门禁设备仓储层
 * @author Huangama
 * @author 蔡少东
 * @date 2016-2-25
 */
@Transactional
public interface GuardDeviceRepository extends JpaRepository<GuardDevicePo, String> {
	/**
	 * 查找门禁设备
	 * @param guardNum 门禁设备号
	 * @param oemCode
	 * @return
	 */
	public List<GuardDevicePo> findByGuardNumAndOemCode(String guardNum, String oemCode);
	
	/**
	 * 根据项目ID查询设备
	 * @param projectId 项目ID
	 * @return
	 */
	public List<GuardDevicePo> findByProjectId(String projectId);
	
	
	/**
	 * 查找设备
	 * @param positionId 位置ID
	 * @return
	 */
	public List<GuardDevicePo> findByPositionIdIn(List<String> positionId);
}
