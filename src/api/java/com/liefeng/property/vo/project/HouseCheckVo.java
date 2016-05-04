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
	 * 房屋ID 
	 */
	private String houseId;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
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

}
