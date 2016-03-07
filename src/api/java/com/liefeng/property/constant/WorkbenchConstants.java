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
	public static interface MyPageSize {
		/**
		 * 任务（4条）
		 */
		public static final Integer TASK_SIZE = 4;
		
		/**
		 * 通知（4条）
		 */
		public static final Integer NOTICE_SIZE = 4;
		
		/**
		 * 消息（4条）
		 */
		public static final Integer MSG_SIZE = 4;
		
		/**
		 * 日程（6条）
		 */
		public static final Integer SCHEDULE_SIZE = 6;
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
	
	/**
	 * 通知排序类型
	 * @author xhw
	 * @date 2016年2月29日上午11:56:07
	 */
	public static interface NoticeOrderBy {
		/**
		 * 创建时间
		 */
		public static final String CREATETIME = "create_time";
		
		/**
		 * 审核时间
		 */
		public static final String CHECKTIME= "review_time";
		
		/**
		 * 发布时间
		 */
		public static final String PUBLISHTIME = "publish_time";
		
		/**
		 * 归档时间
		 */
		public static final String ARCHIVETIME = "archive_time";
		
	}
	
	/**
	 * 通知接收人类型
	 * @author xhw
	 * @date 2016年2月29日下午7:39:20
	 */
	public static interface NoticePrivilegeType {
		/**
		 * 员工
		 */
		public static final String STAFF = "1";
		
		/**
		 * 业主/住户
		 */
		public static final String RESIDENT= "2";
		
	}
	
	/**
	 * 站内消息类型
	 * @author xhw
	 * @2016年3月3日 上午11:04:06
	 */
	public static interface WebsiteMsgType {
		/**
		 * 系统消息
		 */
		public static final String SYSTEM = "1";
		
		/**
		 * 个人消息
		 */
		public static final String PERSON= "2";
		
	}
	
	/**
	 * 报事
	 */
	public static interface EventReport{
		/**
		 * 报事状态 未处理
		 */
		public static final String STATUS_UNTREATED = "0"; 
		
		/**
		 * 报事状态 已派工
		 */
		public static final String STATUS_ALREADYWORKERS = "1"; 
		
		/**
		 * 报事状态 未处理
		 */
		public static final String STATUS_ALREADYFEEDBACK = "2"; 
	}
	
	/**
	 * 消息接收人类型
	 */
	public static interface ReceiveUserType{
		/**
		 * 员工
		 */
		public static final String TYPE_STAFF = "1"; 
		
		/**
		 * 业主
		 */
		public static final String TYPE_PROPRIETOR = "2"; 
		
	}
	
}
