package com.liefeng.property.constant;

/**
 * 门禁相关常量
 * @author 蔡少东
 * @date 2016年3月3日
 */
public class GuardConstants {
	
	private GuardConstants(){
		
	}
	
	/**
	 * 门禁磁卡类型
	 */
	public static interface GuardCardType{
		
		/**
		 * 长期
		 */
		public String PERMANENT = "0";
		
		/**
		 * 临时
		 */
		public String TEMP = "1";
	}
	
	
	/**
	 * 门禁磁卡状态
	 */
	public static interface GuardCardStatus{
		
		/**
		 * 注销
		 */
		public String CANCEL = "0";
		
		/**
		 * 正常
		 */
		public String NORMAL = "1";
		
		/**
		 * 挂失
		 */
		public String LOSS = "2";
	}
	
	/**
	 * 摄像头类型
	 */
	public static interface GuardCameraType{
		
		/**
		 * 摄像头
		 */
		public String ALONE = "0";
		
		public String ATTACHMENT = "1";
	
	}
	
	/**
	 * 服务人员状态
	 */
	public static interface AttendantStatus{
		
		/**
		 * 正常：1
		 */
		public String NORMAL = "1";
		
		/**
		 * 注销:2
		 */
		public String CANCEL = "2";
	
	}
}
