package com.liefeng.property.vo.api;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;
import com.liefeng.property.annotation.Dict;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 员工信息
 * @author 蔡少东
 * @date 2016年4月8日
 */
@ApiModel(parent=BaseValue.class)
public class StaffInfoVo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4053366868455840708L;
	
	
	/**
	 * 主键
	 */
	@ApiModelProperty(value="staffId")
	private String id;
	/**
	 * 员工工号
	 */
	@ApiModelProperty(value="员工工号")
	private String number;
	
	/**
	 * 入职时间
	 */
	@ApiModelProperty(value="入职时间")
	private Date entryTime;
	
	/**
	 * 在职状态
	 * 1：在职；2：离职
	 */
	@Dict(group = "WORK_STATUS")
	@ApiModelProperty(value = "在职状态[1：在职；2：离职]")
	private String workStatus;
	
	/**
	 * 所属部门ID
	 */
	@ApiModelProperty(value="所属部门ID")
	private String departmentId;
	
	/**
	 * 上级领导
	 */
	@ApiModelProperty(value="上级领导")
	private String director;
	
	/**
	 * 职位
	 */
	@Dict(group="POSITION")
	@ApiModelProperty(value="职位")
	private String position;

	/**
	 * 部门名称
	 */
	@ApiModelProperty(value="部门名称")
	private String departmentName;
	
	/**
	 * 职位名称
	 */
	@ApiModelProperty(value="职位名称")
	private String positionName;

	/**
	 * 员工姓名
	 */
	@ApiModelProperty(value="员工姓名")
	private String name;
	
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value="手机号码")
	private String phone;
	
	/**
	 * 毕业学校
	 */
	@ApiModelProperty(value="毕业学校")
	private String school;
	
	/**
	 * 毕业时间
	 */
	@ApiModelProperty(value="毕业时间")
	private Date  graduateTime;
	
	/**
	 * 专业
	 */
	@ApiModelProperty(value="专业")
	private String major;
	
	/**
	 * 语言能力：多选用“,”分隔
	 */
	@ApiModelProperty(value="多选用“,”分隔")
	private String language;
	
	/**
	 * 健康状况
	 */
	@ApiModelProperty(value="健康状况")
	private String health;
	
	/**
	 * 现住地址
	 */
	@ApiModelProperty(value="现住地址")
	private String address;
	
	/**
	 * 电子邮件
	 */
	@ApiModelProperty(value="电子邮件")
	private String email;
	
	/**
	 * 紧急联系人
	 */
	@ApiModelProperty(value="紧急联系人")
	private String emergencyContact;
	
	/**
	 * 紧急联系电话
	 */
	@ApiModelProperty(value="紧急联系电话")
	private String emergencyPhone;
	
	/**
	 * OEM编码
	 */
	@ApiModelProperty(value="OEM编码")
	private String oemCode;
	
	/**
	 * 客户全局唯一标识Id（系统自动生成）
	 */
	@ApiModelProperty(value="客户全局唯一标识Id")
	private String globalId;
	
	/**
	 * 身份证号码
	 */
	@ApiModelProperty(value="身份证号码")
	private String idNum;

	/**
	 * 性别。0：未知；1：男；2：女
	 */
	@ApiModelProperty(value="性别。0：未知；1：男；2：女")
	private String sex;
	
	/**
	 * 出生日期
	 */
	@ApiModelProperty(value="出生日期")
	private Date birthday;
	
	/**
	 * 籍贯
	 */
	@ApiModelProperty(value="籍贯")
	private String nativePlace;
	
	/**
	 * 民族
	 */
	@ApiModelProperty(value="民族")
	private String national;
	
	/**
	 * 婚姻状况。0：未知；1：未婚；2：已婚。
	 */
	@ApiModelProperty(value="婚姻状况。0：未知；1：未婚；2：已婚。")
	private String maritalStatus;
	
	/**
	 * 文化程度。0：未知；1：小学；2：初中；3：高中；4：大学；5：硕士；6：博士
	 */
	@ApiModelProperty(value="文化程度。0：未知；1：小学；2：初中；3：高中；4：大学；5：硕士；6：博士")
	private String eduDegree;
	
	/**
	 * 身高
	 */
	@ApiModelProperty(value="身高")
	private Double height;
	
	/**
	 * 体重
	 */
	@ApiModelProperty(value="体重")
	private Double weight;
	
	/**
	 * 步长
	 */
	@ApiModelProperty(value="步长")
	private Double step;
	
	/**
	 * 肖像URL
	 */
	@ApiModelProperty(value="肖像URL")
	private String portraitUrl;
	
	/**
	 * 音频URL
	 */
	@ApiModelProperty(value="音频URL")
	private String audioUrl;
	
	/**
	 * 视频URL
	 */
	@ApiModelProperty(value="视频URL")
	private String videoUrl;

	/**
	 * 客户状态
	 */
	@ApiModelProperty(value="客户状态")
	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Date getGraduateTime() {
		return graduateTime;
	}

	public void setGraduateTime(Date graduateTime) {
		this.graduateTime = graduateTime;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getGlobalId() {
		return globalId;
	}

	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getEduDegree() {
		return eduDegree;
	}

	public void setEduDegree(String eduDegree) {
		this.eduDegree = eduDegree;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getStep() {
		return step;
	}

	public void setStep(Double step) {
		this.step = step;
	}

	public String getPortraitUrl() {
		return portraitUrl;
	}

	public void setPortraitUrl(String portraitUrl) {
		this.portraitUrl = portraitUrl;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
}
