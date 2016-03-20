package com.liefeng.property.vo.household;

import java.util.List;

import com.liefeng.core.entity.BaseValue;

/**
 * 房产视图数据值对象
 * 
 * @author ZhenTingJun
 * @date 2016年3月18日
 */
public class HouseGraphVo extends BaseValue {

	private static final long serialVersionUID = 2864123496083435578L;
	
	/**
	 * 楼栋名称
	 */
	private String buildingName;
	
	/**
	 * 楼栋中房型数量
	 */
	private Long houseSpecCount;
	
	/**
	 * 楼栋中的房子
	 */
	private List<ProprietorSingleHouseVo> houses;
	
	/**
	 * 已初始化房子数量
	 */
	private Integer initializedCount;
	
	/**
	 * 未初始化房子数量
	 */
	private Integer noInitializeCount;

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	

	public Long getHouseSpecCount() {
		return houseSpecCount;
	}

	public void setHouseSpecCount(Long houseSpecCount) {
		this.houseSpecCount = houseSpecCount;
	}

	public List<ProprietorSingleHouseVo> getHouses() {
		return houses;
	}

	public void setHouses(List<ProprietorSingleHouseVo> houses) {
		this.houses = houses;
	}

	public Integer getInitializedCount() {
		return initializedCount;
	}

	public void setInitializedCount(Integer initializedCount) {
		this.initializedCount = initializedCount;
	}

	public Integer getNoInitializeCount() {
		return noInitializeCount;
	}

	public void setNoInitializeCount(Integer noInitializeCount) {
		this.noInitializeCount = noInitializeCount;
	}
	
	
	
}
