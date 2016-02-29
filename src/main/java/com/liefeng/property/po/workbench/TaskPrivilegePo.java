package com.liefeng.property.po.workbench;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;


/**
 * 任务权限持久化对象
 * 
 * @author xhw
 * @date 2016年2月22日下午3:33:27
 */
@Entity
@Table(name="t_task_privilege")
public class TaskPrivilegePo extends BaseValue{

	private static final long serialVersionUID = -908480321826175669L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 任务ID
	 */
	@Column(name = "task_id")
	private String taskId;

	/**
	 * 员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;

	/**
	 * OEM编码
	 */
	@Column(name = "oem_code", updatable = false)
	private String oemCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
