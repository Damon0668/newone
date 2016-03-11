package com.liefeng.property.vo.workbench;

import com.liefeng.core.entity.BaseValue;

/**
 * 任务权限对象
 * @author xhw
 * @date 2016年2月22日下午3:39:54
 */
public class TaskPrivilegeVo extends BaseValue{

	private static final long serialVersionUID = 140069505301606742L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 任务ID
	 */
	private String taskId;
	
	/**
	 * 员工ID
	 */
	private String staffId;
	
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
