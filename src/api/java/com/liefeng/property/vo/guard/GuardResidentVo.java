package com.liefeng.property.vo.guard;

import com.liefeng.property.vo.household.ResidentVo;

/**
 * 门禁模块
 * 住户列表
 * @author 蔡少东
 * @date 2016年3月6日
 */
public class GuardResidentVo extends ResidentVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8727900934197883423L;

	/**
	 * 门禁卡ID
	 */
	private String guardCardId;
	
	/**
	 * 门禁磁卡编号
	 */
	private String guardCardSn;
	
	/**
	 * 门禁卡状态
	 */
	private String guardCardStatus;
	
	/**
	 * 车牌号
	 */
	private String plateNum;

	public String getGuardCardSn() {
		return guardCardSn;
	}

	public void setGuardCardSn(String guardCardSn) {
		this.guardCardSn = guardCardSn;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getGuardCardId() {
		return guardCardId;
	}

	public void setGuardCardId(String guardCardId) {
		this.guardCardId = guardCardId;
	}

	public String getGuardCardStatus() {
		return guardCardStatus;
	}

	public void setGuardCardStatus(String guardCardStatus) {
		this.guardCardStatus = guardCardStatus;
	}
	
	
}
