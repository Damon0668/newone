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
		 * 报事状态 归档
		 */
		public static final String STATUS_FILE = "2";
		
		/**
		 * 报事状态 已反馈
		 */
		public static final String STATUS_ALREADYFEEDBACK = "2";
		
		/**
		 * 报事流程名称
		 */
		public static final String EVENT_REPORT_FLOW_NAME = "eventReportFlow";

		/**
		 * 未签收
		 */
		public static final String SIGNFOR_NO = "0"; 
		
		/**
		 * 已经签收
		 */
		public static final String SIGNFOR_YES = "1";
		
		/**
		 * 退回
		 */
		public static final String SIGNFOR_SENDBACK = "2";
		
		/**
		 * 办结
		 */
		public static final String SIGNFOR_FINISH = "3";

		/**
		 * 附件类型 附件
		 */
		public static final String EVENTPROCATTACH_ATTACH = "1"; 
		
		/**
		 * 附件类型 图片
		 */
		public static final String EVENTPROCATTACH_PIC = "2";

		/**
		 * 部门审核通过 
		 */
		public static final String AUDITSTATUS_YES = "1"; 
		
		/**
		 * 部门审核不通过
		 */
		public static final String AUDITSTATUS_NO = "0";
		
		/**
		 * 是 抢单
		 */
		public static final String GRAB_YES = "1"; 
		
		/**
		 * 不 抢单
		 */
		public static final String GRAB_NO = "0";
		
		/**
		 * 用户评价
		 */
		public static final String RETURNVISIT_OWNER = "owner";
		
		/**
		 * 报事方式 一般
		 */
		public static final String EVENTTYPE_NORMAL = "01";

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
	
	/**
	 * 报事方式
	 */
	public static interface EventReportMode{
		/**
		 * 来电:1
		 */
		public static final String PHONE = "1"; 
		
		/**
		 * app客服端：2
		 */
		public static final String APP = "2"; 
		
		/**
		 * 上门
		 */
		public static final String VISIT = "3"; 
	}
	
	/**
	 * app报事状态
	 * @author xhw
	 * @date 2016年3月18日 下午5:06:50
	 */
	public static interface EventStatusAPP{
		/**
		 * 未处理：0
		 */
		public static final String UNTREATED = "0"; 
		
		/**
		 * 已派工：1
		 */
		public static final String ALREADYWORKERS = "1"; 
		
		/**
		 * 未评价：2
		 */
		public static final String NOTEVALUATE = "2"; 
		
		/**
		 * 已完成：3
		 */
		public static final String OVER = "3"; 
	}
	
	/**
	 * 流程管理步骤
	 * @author xhw
	 * @date 2016年3月20日 上午9:55:13
	 */
	public static interface EventProcessStatus{
		
		/**
		 * 派单
		 */
		public static final String DISTRIBUTELEAFLETS = "distributeLeaflets"; 
		
		/**
		 * 派工
		 */
		public static final String DISPATCHING = "dispatching"; 
		
		/**
		 * 办理
		 */
		public static final String HANDLE = "handle"; 
		
		/**
		 * 审核
		 */
		public static final String AUDIT = "audit"; 
		
		/**
		 * 客服回访
		 */
		public static final String RETURNVISIT = "returnVisit";
	}
	
	/**
	 * 工单列表类型
	 * @author ZhenTingJun
	 * @date 2016-03-25
	 */
	public static interface EventListType {
		/**
		 * 待处理
		 */
		public static final String WAIT_DEAL = "1";
		
		/**
		 * 流转中
		 */
		public static final String FLOWING = "2";
		
		/**
		 * 已完成
		 */
		public static final String COMPLETED = "3";
	}
	
	public static interface ReturnVisitType {
		/**
		 * 上门:1
		 */
		public static final String VISIT = "1";
		
		/**
		 * 电话:2
		 */
		public static final String CALL = "2";
		
		/**
		 * 客户app:3
		 */
		public static final String CUSTOMER = "3";
		
		/**
		 * 员工app:4
		 */
		public static final String STAFF = "4";
		
	}
	
	/**
	 * 报事直接反馈
	 */
	public static final String DIRECTFEEDBACK = "1";
	
	/**
	 * 通知类型
	 * @author xhw
	 * @date 2016年4月11日 下午3:45:51
	 */
	public static interface NoticeType {
		/**
		 * 社区通告:1
		 */
		public static final String ONE = "1";
		
		/**
		 * 温馨提醒:2
		 */
		public static final String TWO = "2";
		
		/**
		 * 通知:3
		 */
		public static final String THREE = "3";
		
		/**
		 * 社区动态:4
		 */
		public static final String FOUR = "4";
		
	}
	
	/**
	 * 0:系统
	 */
	public static final String SystemOperate = "0";
	
	/**
	 * 系统名称：系统
	 */
	public static final String SystemName = "系统";
}