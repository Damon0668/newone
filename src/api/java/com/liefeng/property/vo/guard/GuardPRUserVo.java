package com.liefeng.property.vo.guard;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.liefeng.property.vo.household.ResidentVo;

/**
 * 门禁模块
 * 住户列表
 * @author 蔡少东
 * @date 2016年3月6日
 */
public class GuardPRUserVo extends ResidentVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8727900934197883423L;
	
	/**
	 * 住户类型
	 */
	private String userType;
	
	/**
	 * 门禁卡ID
	 */
	private String cardId;
	
	/**
	 * 门禁磁卡编号
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

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
