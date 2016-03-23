package com.liefeng.property.constant;

/**
 * 系统相关（字典、系统参数等）常量类
 * @author Huangama
 * @date 2016-2-20
 */
public class SysConstants {

	private SysConstants() {}
	
	/**
	 * 默认oem_code
	 */
	public static final String DEFAULT_OEM_CODE = "property";
	
	/**
	 * 默认app_code
	 */
	public static final String DEFAULT_APP_CODE = "property";
	
	
	/**
	 * 字典缓存后缀
	 */
	public static final String DICT_CACHE_KEY_SUFFIX = "@DICT_PROPERTY";
	
	/**
	 * 字典组编码
	 */
	public static interface DictGroup {
		
		/**
		 * 费用类型
		 */
		public static final String FEE_TYPE = "FEE_TYPE";
		
		/**
		 * 房屋产权归属
		 */
		public static final String HOUSE_OWNERSHIP = "HOUSE_OWNERSHIP";
		
		/**
		 * 房屋户型
		 */
		public static final String HOUSE_TYPE = "HOUSE_TYPE";
		
		/**
		 * 房屋使用类型
		 */
		public static final String HOUSE_USE_TYPE = "HOUSE_USE_TYPE";
		
		/**
		 * 仪表类型
		 */
		public static final String METER_TYPE = "METER_TYPE";
	}
}
