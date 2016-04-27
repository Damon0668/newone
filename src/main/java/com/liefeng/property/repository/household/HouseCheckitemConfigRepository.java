package com.liefeng.property.repository.household;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.HouseCheckitemConfigPo;

/**
 * 房屋验收配置项仓储层
 * @author xhw
 * @date 2016年4月26日 下午4:28:07
 */
@Transactional
public interface HouseCheckitemConfigRepository extends JpaRepository<HouseCheckitemConfigPo, String> {
	
	/**
	 * 删除房屋验收项配置
	 * @param id
	 * @param parentId 
	 * @author xhw
	 * @date 2016年4月26日 下午5:55:38
	 */
	public void deleteByIdOrParentId(String id, String parentId);

}
