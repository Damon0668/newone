package com.liefeng.property.vo.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.liefeng.core.entity.BaseValue;

/**
 * 员工通讯录值对象（移动办公）
 * @author xhw
 * @date 2016年3月28日 下午1:57:49
 */
@ApiModel(value="员工通讯录")
public class StaffContactVo extends BaseValue{

	private static final long serialVersionUID = 8567923795299262457L;

	/**
	 * 部门ID
	 */
	@ApiModelProperty(value="部门ID")
	private String departmentId;

	/**
	 * 部门名称
	 */
	@ApiModelProperty(value="部门名称")
	private String departmentName;

	/**
	 * 部门中的所有员工信息
	 */
	@ApiModelProperty(value="部门中的所有员工信息")
	private List<PropertyStaffVo> staffList;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}


	public List<PropertyStaffVo> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<PropertyStaffVo> staffList) {
		this.staffList = staffList;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
