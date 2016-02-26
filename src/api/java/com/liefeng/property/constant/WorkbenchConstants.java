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
	
	/**
	 * “我的主页”默认显示个数
	 * @author xhw
	 * @date 2016年2月26日上午10:25:47
	 */
	public static interface ObjectSize {
		/**
		 * 任务（4条）
		 */
		public static final Integer TASKSIZE = 4;
	}
	
	/**
	 * 通知状态
	 * @author xhw
	 * @date 2016年2月26日下午4:27:09
	 */
	public static interface NoticeStatus {
		/**
		 * 待审核
		 */
		public static final String CHECKING = "1";
		
		/**
		 * 审核不通过
		 */
		public static final String NOTPASS= "2";
		
		/**
		 * 待发布
		 */
		public static final String PUBLISHING = "3";
		
		/**
		 * 待归档
		 */
		public static final String ARCHIVING = "4";
		
		/**
		 * 已归档
		 */
		public static final String ARCHIVED = "5";
	}
}
