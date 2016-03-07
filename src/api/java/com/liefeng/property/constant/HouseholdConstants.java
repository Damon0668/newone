package com.liefeng.property.constant;

/**
 * 家庭相关常量类
 * 
 * @author ZhenTingJun
 * @date 2016年2月29日
 */
public class HouseholdConstants {
	
	private HouseholdConstants(){
		
	}
	
	/**
	 * 业主状态
	 */
	public static interface ProprietorStatus {
		
		/**
		 * 激活
		 */
		public String ACTIVE = "1";
		
		/**
		 * 注销
		 */
		public String CANCEL = "2";
		
	}
	
	/**
	 * 住户状态
	 */
	public static interface ResidentStatus {
		
		/**
		 * 激活
		 */
		public String ACTIVE = "1";
		
		/**
		 * 注销
		 */
		public String CANCEL = "2";
		
	}
	
	/**
	 * 入住排队状态
	 */
	public static interface CheckinQueueStatus {
		
		/**
		 * 未处理的
		 */
		public String UNTREATED = "0";
		
		/**
		 * 处理中
		 */
		public String HANDLING = "1";
		
		/**
		 * 处理完成
		 */
		public String FINISHED = "2";
		
	}
	
	/**
	 * 资料完善状态
	 */
	public static interface MaterialPerfectStatus {
		
		/**
		 * 未完善的
		 */
		public String NOT_COMPLETE = "0";
		
		/**
		 * 完善的
		 */
		public String COMPLETE = "1";
		
	}
	
	
}
