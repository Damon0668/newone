package com.liefeng.property.api.ro.finger.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

/**
 * 添加访客
 * @author xhw
 * @date 2016年3月23日 上午11:46:46
 */
@ApiModel
public class VisitorRo extends BaseValue {
	
	private static final long serialVersionUID = 5320190564278007353L;

	@ApiModelProperty(value="访客姓名", required=true)
	@NotNull
	private String name;
	
	@ApiModelProperty(value="身份证号")
	private String idNum;
	
	@ApiModelProperty(value="手机号码", required=true)
	@NotNull
	private String phone;
	
	@ApiModelProperty(value="项目ID", required=true)
	@NotNull
	private String projectId;
	
	@ApiModelProperty(value="被访问者", required=true)
	@NotNull
	private String interviewee;
	
	@ApiModelProperty(value="房间号", required=true)
	@NotNull
	private String houseNum;
	
	@ApiModelProperty(value="车牌号")
	private String plateNum;
	
	@ApiModelProperty(value="申请人ID【对应用户表的ID】", required=true)
	@NotNull
	private String applicantId;

	@ApiModelProperty(value="进入时间", required=true)
	@NotNull
	private String visitDate;
	
	@ApiModelProperty(value="备注")
	private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getInterviewee() {
		return interviewee;
	}

	public void setInterviewee(String interviewee) {
		this.interviewee = interviewee;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

}
