package com.liefeng.property.constant;

public class ApprovalFlowConstants {
	
	

	private ApprovalFlowConstants(){}
	
	/**
	 * 用户id的前缀
	 */
	public static final String USER_PREFIXES = "user_";
	
	/**
	 * 结束节点名称
	 */
	public static final String NODE_END = "end";
	
	/**
	 * 流程变量创建人的键名称 
	 */
	public static final String ORDER_CREATE_NAME = "createName";
	
	/**
	 *  流程变量状态的键名称 
	 */
	public static final String ORDER_STATUS = "status";
	
	
	/**
	 * 流程变量里的状态 待签收
	 */
	public static final String ORDER_STATUS_WAIT_SIGN = "0"; 
	
	/**
	 * 流程变量里的状态 处理中
	 */
	public static final String ORDER_STATUS_PROCESSING = "1"; 
	
}
