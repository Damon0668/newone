package com.liefeng.property.constant;

public class FeeConstants {
	
	

	private FeeConstants(){}

	public static interface FeeItem {
		
		/**
		 * 等待收取
		 */
		public static final String STATUS_WAIT = "0";

		/**
		 * 已收取
		 */
		public static final String STATUS_YES = "1";
		
		/**
		 * 收取异常
		 */
		public static final String STATUS_ERRER = "1";
		
	}
	
	public static interface MeterRecord {

		/**
		 * 业主
		 */
		public static final String METEROWNER_YSE = "1";
		
		/**
		 * 公摊
		 */
		public static final String METEROWNER_NO = "2";
		
		/**
		 * 水表
		 */
		public static final String METER_WATER = "1";
		
		/**
		 * 电表
		 */
		public static final String METER_ELECTRICITY  = "2";
		
		/**
		 * 气表
		 */
		public static final String METER_GAS = "3";
		
		/**
		 * 公摊水表
		 */
		public static final String METER_PUBLIC_WATER = "1";
		
		/**
		 * 公摊电表
		 */
		public static final String METER_PUBLIC_ELECTRICITY  = "2";
		
		/**
		 * 公摊气表
		 */
		public static final String METER_PUBLIC_GAS = "3";
		
		/**
		 * 是否可以抄表 可以
		 */
		public static final Integer READ_YES = 1;
		
		/**
		 * 是否可以抄表 不可以
		 */
		public static final Integer READ_NO = 0;
		
		/**
		 * 数据来源 web
		 */
		public static final String FROM_WEB = "1";
		
		/**
		 * 数据来源 andorid 客户端
		 */
		public static final String FROM_ANDROID = "2";
		
		/**
		 * 数据来源 ios 客户端
		 */
		public static final String FROM_IOS = "3";
		
		
	}

	
	
	public static interface MeterSetting {
		
		/**
		 * 抄表
		 */
		public static final String CHARGEABLE_YES = "1";
		
		/**
		 * 不抄表
		 */
		public static final String CHARGEABLE_NO = "2";
	}

	public static interface FeeSetting {

		public static final String CHARGEABLE_YES = "1";
		
		/**
		 * 水费
		 */
		public static final String FEE_WATER = "1";
		
		/**
		 * 电费
		 */
		public static final String FEE_ELECTRICITY  = "2";
		
		/**
		 * 气费
		 */
		public static final String FEE_GAS = "3";
		
		/**
		 * 公摊水费
		 */
		public static final String FEE_PUBLIC_WATER = "21";
		
		/**
		 * 公摊电费
		 */
		public static final String FEE_PUBLIC_ELECTRICITY  = "22";
		
		/**
		 * 公摊气费
		 */
		public static final String FEE_PUBLIC_GAS = "23";

		/**
		 * 物业管理费
		 */
		public static final String FEE_PROPERTYMANAGE = "4";

		/**
		 * 本体维修基金
		 */
		public static final String FEE_MAINTENANCE = "5";
		
		/**
		 * 排污费
		 */
		public static final String FEE_POLLU = "7";
		
		/**
		 * 垃圾处理费
		 */
		public static final String FEE_GARBAGE = "8";

		/**
		 * 车位管理费
		 */
		public static final String FEE_PARKING = "6";

		
	}
}
