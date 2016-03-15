package com.liefeng.property.bo.household;

import com.liefeng.core.entity.BaseValue;

/**
 * 用户反馈查询参数封装类
 *  
 * @author xhw
 * @date 2016年3月15日 下午1:37:11
 */
public class ResidentFeedbackBo extends BaseValue {

	private static final long serialVersionUID = 4374347928856990706L;

	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	
	/**
	 * 楼层ID
	 */
	private String floorId;
	
	/**
	 * 楼栋ID
	 */
	private String buildingId;
	
	/**
	 * 房号编码
	 */
	private String houseNum;
	
	/**
	 * 用户姓名
	 */
	private String residentName;
	
	/**
	 * 系统标识
	 */
	private String oemCode;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getResidentName() {
		return residentName;
	}

	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}


}
