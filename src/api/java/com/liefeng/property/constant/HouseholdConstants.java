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
	
	/**
	 * 入住办理页面状态 1：没有排号； 2：有排号； 3：已经办理
	 */
	public static interface CheckinPageStatus {
		
		/**
		 * 没有排号
		 */
		public String NONUMBER = "1";
		
		/**
		 * 有排号 
		 */
		public String HASNUMBER = "2";
		
		/**
		 * 处理完成
		 */
		public String FINISHED = "3";
		
	}
	
	/**
	 * 是否是业主
	 *  
	 * @author xhw
	 * @date 2016年3月14日 上午11:07:04
	 */
	public static interface IsProprietor {
		
		/**
		 * 不是业主：0
		 */
		public String NO = "0";
		
		/**
		 * 是业主：1 
		 */
		public String YES = "1";
		
		
	}
}