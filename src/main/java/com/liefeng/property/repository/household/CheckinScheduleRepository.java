package com.liefeng.property.repository.household;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.CheckinSchedulePo;

/**
 * 入住时间安排仓储层
 * 
 * @author ZhenTingJun
 * @author xhw
 * @date 2016年3月6日
 */
@Transactional
public interface CheckinScheduleRepository extends JpaRepository<CheckinSchedulePo, String> {
	
	/**
	 * 更具项目ID和OEM编码删除入住安排时间
	 * @param projectId 项目ID
	 * @param oemCode OEM编码
	 */
	public void deleteByProjectIdAndOemCode(String projectId, String oemCode);
	
	/**
	 * 根据项目id、楼栋id，获取入住办理安排时间
	 * @param projectId 项目id
	 * @param buildingId 楼栋id
	 * @return 
	 * @author xhw
	 * @date 2016年3月8日 上午11:59:27
	 */
	public CheckinSchedulePo findByProjectIdAndBuildingId(String projectId, String buildingId);
}
