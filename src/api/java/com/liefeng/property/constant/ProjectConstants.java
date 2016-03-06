package com.liefeng.property.constant;

/**
 * 项目相关常量类
 * @author Huangama
 * @author ZhenTingJun
 * @date 2015-12-22
 */
public class ProjectConstants {

	private ProjectConstants() {
		
	}
	
	/**
	 * 房子销售状态
	 */
	public static interface HouseSaleStatus {
		
		/**
		 * 未售
		 */
		public String NO_SALE = "0";
		
		/**
		 * 待售
		 */
		public String WAIT_SALE = "1";
		
		/**
		 * 已售
		 */
		public String HAD_SALE = "2";
	}
	
	
}
