package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 家庭相关错误码
 * 
 * @author ZhenTingJun
 * @date 2016-02-24
 */
public enum HouseholdErrorCode implements IErrorCode {

	/**
	 * 保存业主信息失败
	 */
	CREATE_PROPRIETOR_FAILED("保存业主信息失败"),

	/**
	 * 业主手机号码为空
	 */
	PROPRIETOR_PHONE_NULL("业主手机号码为空"),

	/**
	 * 业主名字为空
	 */
	PROPRIETOR_NAME_NULL("业主名字为空"),

	/**
	 * 业主信息已存在
	 */
	PROPRIETOR_ALREADY_EXIST("业主信息已存在"),
	
	/**
	 * 手机号已被其他客户绑定
	 */
	PHONE_ALREADY_BINDING("手机号已被其他客户绑定"),
	
	/**
	 * 住户手机号码为空
	 */
	RESIDENT_PHONE_NULL("住户手机号码为空"),
	
	/**
	 * 住户名字为空
	 */
	RESIDENT_NAME_NULL("业主名字为空"),
	
   /***************** 入住安排时间相关 *******************/
	
	CHECKIN_SCHEDULE_INFO_NULL("入住安排时间为空"),
	
	CHECKIN_SCHEDULE_NOT_START("入住办理尚未开始"),
	
   /***************** 入住排队相关 *******************/

	CHECKIN_QUEUE_FINISHED("入住办理已经完成"),
	
	CHECKIN_QUEUE_HANDLING("入住办理正在办理中"),
	
   /***************** 业主登记相关 *******************/
	CHECKIN_PROPRIETOR_CLOSE("业主登记功能已经关闭"),
	
	CHECKIN_PROPRIETOR_CHECK("业主情况登记"),
	
	CHECKIN_PROPRIETOR_MODIFY("修改业主登记资料"), 
	
	/****************住户历史********************/
	RESIDENT_ALREADY_CANCEL("该住户已经注销了"),
	RESIDENT_ALREADY_ACTIVE("该住户已经激活了"),
	;

	private String desc;

	private HouseholdErrorCode(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public String toString() {
		return this.desc;
	}

}
