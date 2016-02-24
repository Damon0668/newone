package com.liefeng.property.constant;

public class FeeConstants {

	private FeeConstants(){}
	
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
		
	}
}
