package com.liefeng.property.po.project;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 房屋验收单持久化对象
 * @author xhw
 * @date 2016年5月4日 上午10:22:42
 */
@Entity
@Table(name = "t_house_check")
public class HouseCheckPo extends BaseValue {

	private static final long serialVersionUID = 1960654720425306331L;

	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 项目ID 
	 */
	@Column(name = "project_id")
	private String projectId;
	
	/**
	 * 房间号
	 */
	@Column(name = "house_num")
	private String houseNum;
	
	/**
	 * 检查项ID
	 */
	@Column(name = "item_id")
	private String itemId;
	
	/**
	 * 验收结果
	 */
	@Column(name = "result_id")
	private String resultId;
	
	
	/**
	 * 录入员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date create_time;
	
	/**
	 * 关闭时间
	 */
	@Column(name = "close_time")
	private Date closeTime;

	/**
	 * OEM编码
	 */
	@Column(name = "oem_code")
	private String oemCode;

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

}
