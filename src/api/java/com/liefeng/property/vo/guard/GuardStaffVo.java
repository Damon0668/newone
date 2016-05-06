package com.liefeng.property.vo.guard;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.liefeng.common.util.TimeUtil;
import com.liefeng.core.entity.BaseValue;

/**
 * 出入管理
 * 员工信息
 * @author 蔡少东
 * @date 2016年4月28日
 */
public class GuardStaffVo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8474948325161019879L;

	/**
	 * 员工ID
	 */
	private String id;
	
	/**
	 * 项目名字
	 */
	private String projectName;
	
	/**
	 * 部门名字
	 */
	private String deptName;
	
	/**
	 * 岗位名字
	 */
	private String positionName;
	
	/**
	 * 员工名字
	 */
	private String staffName;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 身份证
	 */
	private String idNum;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 出生日期
	 */
	private Date birthday;
	
	/**
	 * 在职状态。1：在职；2：离职
	 */
	private String workStatus;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 管理的项目
	 */
	private List<String> manageProjects;
	
	/**
	 * 磁卡ID
	 */
	private String cardId;
	
	/**
	 * 磁卡编号
	 */
	private String cardSn;
	
	/**
	 * 磁卡类型
	 */
	private String cardType;
	
	/**
	 * 磁卡状态
	 * 状态。0：已注销；1：正常；2：已挂失
	 */
	private String cardStatus;
	
	/**
	 * 有效开始时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	/**
	 * 有效结束时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardSn() {
		return cardSn;
	}

	public void setCardSn(String cardSn) {
		this.cardSn = cardSn;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public List<String> getManageProjects() {
		return manageProjects;
	}

	public void setManageProjects(List<String> manageProjects) {
		this.manageProjects = manageProjects;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Integer getAge() {
		if(birthday != null){
			this.age = TimeUtil.getAge(birthday);
		}
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
