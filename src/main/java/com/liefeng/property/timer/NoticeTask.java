package com.liefeng.property.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.constant.WorkbenchConstants;

public class NoticeTask {

	private static Logger logger = LoggerFactory.getLogger(NoticeTask.class);
	
	@Autowired
	private IWorkbenchService workbenchService;
	
	public void generateNotice() {
		ContextManager.getInstance().setOemCode("property");
	
		logger.info("**********开始检测通知************");
		
		//检测“待审核”的通知，如果到发布时间了，通知还没审核通过，则将该通知归档
		workbenchService.autoCheckNotice(WorkbenchConstants.NoticeStatus.CHECKING);
		
		//检测“审核不通过”的通知，如果到发布时间了，通知还没审核通过，则将该通知归档
		workbenchService.autoCheckNotice(WorkbenchConstants.NoticeStatus.NOTPASS);
		
		//检测“待发布”的通知，如果到发布时间了，自动将该通知发布
		workbenchService.autoCheckNotice(WorkbenchConstants.NoticeStatus.PUBLISHING);
		
		//检测“待归档”的通知，如果过了发布时效，则将该通知归档
		workbenchService.autoCheckNotice(WorkbenchConstants.NoticeStatus.ARCHIVING);
		
		logger.info("**********检测通知结束************");
		
	}
}
