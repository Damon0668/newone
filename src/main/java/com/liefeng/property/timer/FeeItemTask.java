package com.liefeng.property.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.constant.FeeConstants;
import com.liefeng.property.service.FeeService;
import com.liefeng.property.service.ProjectService;
import com.liefeng.property.vo.project.ProjectVo;

public class FeeItemTask {

	private static Logger logger = LoggerFactory.getLogger(FeeItemTask.class);
	
	public void generateFeeItem() {
		ContextManager.getInstance().setOemCode("property");
		FeeService feeService = SpringBeanUtil.getBean(FeeService.class);
		ProjectService projectService = SpringBeanUtil.getBean(ProjectService.class);
		DataPageValue<ProjectVo> projectDataPageValue = projectService.findProjects(1, 1000);
	
		logger.info("**********开始生成费用************");
		for (ProjectVo projectvo : projectDataPageValue.getDataList()) {
		
			feeService.createPropertyManageFee(projectvo.getId());
			feeService.createPolluFee(projectvo.getId());
			feeService.createGarbageFee(projectvo.getId());
			feeService.createMaintenanceFee(projectvo.getId());
			feeService.createOwnerMerterFee(projectvo.getId(),FeeConstants.FeeSetting.FEE_WATER);
			feeService.createOwnerMerterFee(projectvo.getId(),FeeConstants.FeeSetting.FEE_ELECTRICITY);
			feeService.createOwnerMerterFee(projectvo.getId(),FeeConstants.FeeSetting.FEE_GAS);
			feeService.createPublicMerterFee(projectvo.getId(),FeeConstants.FeeSetting.FEE_PUBLIC_GAS);
			feeService.createPublicMerterFee(projectvo.getId(),FeeConstants.FeeSetting.FEE_PUBLIC_ELECTRICITY);
			feeService.createPublicMerterFee(projectvo.getId(),FeeConstants.FeeSetting.FEE_PUBLIC_ELECTRICITY);

		}
		logger.info("**********生成费用结束************");
		
	}
}
