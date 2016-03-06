package com.liefeng.property.bo.household;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 入住排队业务值对象
 * 
 * @author ZhenTingJun
 * @date 2016年3月6日
 */
public class CheckinQueueBo extends BaseValue {

	private static final long serialVersionUID = -2288362409250129474L;

	/**
	 * 房间号
	 */
	private String houseNum;

	/**
	 * 业主名字
	 */
	private String name;

	/**
	 * 排号
	 */
	private Integer seq;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * OEM编码
	 */
	private String oemCode;

	/**
	 * 处理状态
	 */
	private String status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 是否仅查询今天。是：y，否：n
	 */
	private String isToday;

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsToday() {
		return isToday;
	}

	public void setIsToday(String isToday) {
		this.isToday = isToday;
	}

}
