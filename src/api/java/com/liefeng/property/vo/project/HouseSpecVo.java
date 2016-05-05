package com.liefeng.property.vo.project;

import com.liefeng.core.entity.BaseValue;

/**
 * 房产规格值对象
 * 
 * @author ZhenTingJun
 * @date 2016年2月17日
 */
public class HouseSpecVo extends BaseValue {

	private static final long serialVersionUID = 8468192169017197650L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 楼栋ID
	 */
	private String buildingId;
	
	/**
	 * 楼层ID
	 */
	private String floorId;
	
	/**
	 * 楼层名称
	 */
	private String floorName;
	
	/**
	 * 楼栋名称
	 */
	private String buildingName;
	
	/**
	 * 楼栋编码
	 */
	private String buildingCode;
	
	/**
	 * 房号（后两位）
	 */
	private String num;

	/**
	 * 户型
	 */
	private String houseType;

	/**
	 * 实际面积
	 */
	private Double actualArea;

	/**
	 * 建筑面积
	 */
	private Double grossArea;

	/**
	 * 使用面积
	 */
	private Double usableArea;

	/**
	 * 分摊面积
	 */
	private Double apportionArea;

	/**
	 * 物业管理费单价
	 */
	private Double propertyFee;

	/**
	 * 产权归属
	 * 1：商品房；2：小产权；3：大产权；4：集资房；5：安居房。
	 */
	private String ownership;

	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/**
	 * 楼层编码
	 */
	private String floorCode;
	
	/**
	 * 数量（非标字段）
	 */
	private int number;
	
	/**
	 * 标记使用
	 */
	private String flag;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingCode() {
		return buildingCode;
	}

	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public Double getActualArea() {
		return actualArea;
	}

	public void setActualArea(Double actualArea) {
		this.actualArea = actualArea;
	}

	public Double getGrossArea() {
		return grossArea;
	}

	public void setGrossArea(Double grossArea) {
		this.grossArea = grossArea;
	}

	public Double getUsableArea() {
		return usableArea;
	}

	public void setUsableArea(Double usableArea) {
		this.usableArea = usableArea;
	}

	public Double getApportionArea() {
		return apportionArea;
	}

	public void setApportionArea(Double apportionArea) {
		this.apportionArea = apportionArea;
	}

	public Double getPropertyFee() {
		return propertyFee;
	}

	public void setPropertyFee(Double propertyFee) {
		this.propertyFee = propertyFee;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
