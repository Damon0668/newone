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
	
	/**
	 * 流程变量里的状态 已完成
	 */
	public static final String ORDER_COMPLETE = "2";

	public static interface  AssigneeOther {
		
		/**
		 * 所有人
		 */
		public static final String ALL = "all";
		
		/**
		 * 操作人
		 */
		public static final String OPERATOR = "op";
		
	}
	
	public static interface AssigneeType{
		
		public static final String USER = "user";
		
		public static final String DEPT = "dept";
		
		public static final String ROLE = "role";
		
		public static final String DIRECTOR = "director";

		public static final String POSTS = "posts";

		public static final String ALL = "all";

		public static final String OTHER = "other";

		public static final String TASKNAME = "taskname";
	}
	
}
