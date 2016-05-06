package com.liefeng.property.repository.project;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.project.HouseCheckPo;
/**
 * 房屋验收单仓储层
 * @author xhw
 * @date 2016年5月4日 上午10:37:40
 */
@Transactional
public interface HouseCheckRepository extends JpaRepository<HouseCheckPo, String> {
	
	/**
	 * 删除房屋验收单
	 * @param projectId
	 * @param houseNum
	 * @param oemCode 
	 * @author xhw
	 * @date 2016年5月6日 上午8:31:29
	 */
	public void deleteByProjectIdAndHouseNumAndOemCode(String projectId, String houseNum, String oemCode);
}
