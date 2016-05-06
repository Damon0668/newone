package com.liefeng.property.vo.project;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 房屋验收值对象
 * @author xhw
 * @date 2016年5月4日 上午10:29:16
 */
public class HouseCheckVo extends BaseValue {

	private static final long serialVersionUID = -8858845388195460823L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 项目id
	 */
	private String projectId;
	
	/**
	 * 房间号
	 */
	private String houseNum;
	
	/**
	 * 检查项ID
	 */
	private String itemId;
	
	/**
	 * 验收结果
	 */
	private String resultId;
	
	
	/**
	 * 录入员工ID
	 */
	private String staffId;

	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 关闭时间
	 */
	private Date closeTime;

	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/**
	 * 非标字段
	 */
	private String itemResult;
	
	/**
	 * 检查项名称
	 */
	private String itemName;
	
	/**
	 * 验收结果名称
	 */
	private String resultName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getItemResult() {
		return itemResult;
	}

	public void setItemResult(String itemResult) {
		this.itemResult = itemResult;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

}
