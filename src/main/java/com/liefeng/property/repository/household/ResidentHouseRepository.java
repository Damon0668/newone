package com.liefeng.property.repository.household;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.ResidentHousePo;

/**
 * 住户房屋信息仓储层
 * 
 * @author ZhenTingJun
 * @author xhw
 * @date 2016年3月15日
 */
@Transactional
public interface ResidentHouseRepository extends JpaRepository<ResidentHousePo, String> {
	
	/**
	 * 根据住户id，房间id获取residentHouse
	 * @param residentId 住户id
	 * @param houseId 房间id
	 * @return 
	 * @author xhw
	 * @date 2016年3月17日 下午5:37:21
	 */
	public ResidentHousePo findByResidentIdAndHouseId(String residentId, String houseId);
}
