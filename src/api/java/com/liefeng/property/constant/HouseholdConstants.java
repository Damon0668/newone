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
	 * 手机好友状态
	 */
	public static interface AppFriendStatus {
		
		/**
		 * 等待验证
		 */
		public String WAITING = "0";
		
		/**
		 * 已为好友
		 */
		public String ASFRIEND = "1";
		
		/**
		 * 拒绝
		 */
		public String REFUSE = "2";
		
	}
	
	/**
	 * 访客录入类型
	 * @author xhw
	 * @date 2016年3月23日 下午1:56:40
	 */
	public static interface VisitorType {
		
		/**
		 * 0：物业录入
		 */
		public String STAFF_CHECKIN = "0";
		
		/**
		 * 1：业主/住户用APP申请录入
		 */
		public String USER_CHECKIN = "1";
		
	}
	

	/**
	 * 住户历史记录 业务类型
	 */
	public static interface busitype {
		
		/**
		 * 0：删除
		 */
		public String DELETE = "0";
		
		/**
		 * 1：迁出
		 */
		public String  MOVEDOUT = "1";
		
		/**
		 * 2：迁入
		 */
		public String  MOVEDINTO  = "2";
		
	}
	
}