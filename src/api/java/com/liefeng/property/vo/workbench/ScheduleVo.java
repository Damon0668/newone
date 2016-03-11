package com.liefeng.property.vo.workbench;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 日程值对象
 * @author xhw
 * @2016年3月2日 上午8:42:55
 */
public class ScheduleVo extends BaseValue{

	private static final long serialVersionUID = -7006170263203936781L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 日程主题
	 */
	private String title;

	/**
	 * 日程内容
	 */
	private String content;

	/**
	 * 日程日期
	 */
	private Date scheduleDate;

	/**
	 * 时间段。如果为空，则表示全天
	 */
	private String period;

	/**
	 * 重复周期。1：每天；2：每周
	 */
	private String repeats;

	/**
	 * 次数。-1表示无数次
	 */
	private Integer times;

	/**
	 * 创建人ID
	 */
	private String creatorId;

	/**
	 * 创建时间
	 */
	private Date createTime;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getRepeats() {
		return repeats;
	}

	public void setRepeats(String repeats) {
		this.repeats = repeats;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}


}
