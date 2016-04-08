package com.liefeng.property.constant;

/**
 * 物业员工常量
 * @author 蔡少东
 * @date 2016年2月27日
 */
public class StaffConstants {
	
	private StaffConstants(){
		
	}
	
	/**
	 * 物业员工状态
	 */
	public static interface StaffStatus{
		
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
	 * 物业员工
	 * 在职状态
	 */
	public static interface WorkStatus{
		/**
		 * 在职
		 */
		public String IN_OFFICE = "1";
		
		/**
		 * 离职
		 */
		public String LEAVE_OFFICE = "2";
	}
	
	/**
	 * 员工档案
	 */
	public static interface StaffArchive{
		
		/**
		 * 身份证
		 */
		public String FILE_TYPE_ID_PIC = "0";
		
		/**
		 * 毕业证
		 */
		public String FILE_TYPE_DIPLOMA_PIC = "1";
		
		/**
		 * 学位证
		 */
		public String FILE_TYPE_DEGREE_PIC = "2";
		
		/**
		 * 其他
		 */
		public String FILE_TYPE_OTHER_PIC = "3";
		
	}
	
	/**
	 * 通告审核权限
	 */
	public static final String NOTICE_CHECK = "ZXTG_SH";
}
