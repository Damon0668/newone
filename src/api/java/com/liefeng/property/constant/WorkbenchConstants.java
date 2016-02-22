package com.liefeng.property.constant;

/**
 * 工作台常量
 * @author XHW
 * @date 2016年2月22日下午2:05:26
 */
public class WorkbenchConstants {
	
	private WorkbenchConstants(){
		
	}
	
	/**
	 * 任务状态
	 */
	public static interface TaskStatus {
		
		/**
		 * 待处理
		 */
		public static final String PENDING = "1";
		
		/**
		 * 处理中
		 */
		public static final String PROCESSING= "2";
		
		/**
		 * 已处理
		 */
		public static final String PROCESSED = "3";
		
		/**
		 * 已审核
		 */
		public static final String REVIEWED = "4";
		
		/**
		 * 已归档
		 */
		public static final String ARCHIVED = "5";
	} 
}
