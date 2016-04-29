package com.liefeng.property.vo.guard;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;
import com.liefeng.property.annotation.Dict;

/**
 * 开门日志视图对象
 * 
 * @author Huangama
 * @author ZhenTingJun
 * @date 2016-2-29
 */
public class GuardOpenLogVo extends BaseValue {

	private static final long serialVersionUID = 8429125119674403155L;

	private String id;

	/**
	 * 磁卡ID
	 */
	private String cardId;

	/**
	 * 门禁设备ID
	 */
	private String guardDeviceId;

	/**
	 * 动作。1：开门；2：关门
	 */
	private String action;

	/**
	 * 开门方式。1：密码；2：刷卡；3：二维码；4：无线对讲(按房号，手机开门,我的门铃开门)；5：管理员开门 ；6：异常开门
	 */
	private String openType;

	/**
	 * 操作用户类型。0：物业员工；1：业主；2：住户；3：访客；4：外来服务人员。
	 */
	private String operUserType;

	/**
	 * 操作用户ID。如果是业主，则对应t_proprietor表的id；住户，则对应t_resident表的id；访客，则对应t_visitor表的id
	 * ；物业员工，则对应t_property_staff表的id。
	 */
	private String operUserId;

	/**
	 * 操作终端。1：手机；2：电视；3：刷卡；4：密码。
	 */
	private String operClient;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * OEM编码
	 */
	private String oemCode;

	/* =================== 冗余字段 =================== */

	/**
	 * 操作用户姓名
	 */
	private String operUserName;

	/**
	 * 操作人手机号
	 */
	private String mobile;

	/**
	 * 磁卡编号
	 */
	private String cardNum;

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 设备类型
	 */
	@Dict(group = "DEVICE_TYPE")
	private String guardDeviceType;

	/**
	 * 设备名称
	 */
	private String guardDeviceName;

	/**
	 * 设备编号
	 */
	private String guardDeviceNum;

	/**
	 * 位置名称
	 */
	private String positionName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getGuardDeviceId() {
		return guardDeviceId;
	}

	public void setGuardDeviceId(String guardDeviceId) {
		this.guardDeviceId = guardDeviceId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getOperUserType() {
		return operUserType;
	}

	public void setOperUserType(String operUserType) {
		this.operUserType = operUserType;
	}

	public String getOperUserId() {
		return operUserId;
	}

	public void setOperUserId(String operUserId) {
		this.operUserId = operUserId;
	}

	public String getOperClient() {
		return operClient;
	}

	public void setOperClient(String operClient) {
		this.operClient = operClient;
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

	public String getOperUserName() {
		return operUserName;
	}

	public void setOperUserName(String operUserName) {
		this.operUserName = operUserName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getGuardDeviceType() {
		return guardDeviceType;
	}

	public void setGuardDeviceType(String guardDeviceType) {
		this.guardDeviceType = guardDeviceType;
	}

	public String getGuardDeviceName() {
		return guardDeviceName;
	}

	public void setGuardDeviceName(String guardDeviceName) {
		this.guardDeviceName = guardDeviceName;
	}

	public String getGuardDeviceNum() {
		return guardDeviceNum;
	}

	public void setGuardDeviceNum(String guardDeviceNum) {
		this.guardDeviceNum = guardDeviceNum;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
