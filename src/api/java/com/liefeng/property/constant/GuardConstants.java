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
	 * 门禁类型
	 */
	public static interface GuardType{
		
		/**
		 * 摄像头
		 */
		public String CAMERA = "0";
	
	}
}
