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
	
	
}
