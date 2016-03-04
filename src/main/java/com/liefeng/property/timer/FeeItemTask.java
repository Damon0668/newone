package com.liefeng.property.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IFeeService;
import com.liefeng.intf.property.IProjectService;
import com.liefeng.property.constant.FeeConstants;
import com.liefeng.property.vo.project.ProjectVo;

public class FeeItemTask {

	private static Logger logger = LoggerFactory.getLogger(FeeItemTask.class);
	
	@Autowired
	private IFeeService feeService;
	
	@Autowired
	private IProjectService projectService;
	
	public void generateFeeItem() {
//		FeeService feeService = SpringBeanUtil.getBean(FeeService.class);
//		ProjectService projectService = SpringBeanUtil.getBean(ProjectService.class);
		DataPageValue<ProjectVo> projectDataPageValue = projectService.findProjects(1, 1000);
	
		logger.info("**********开始生成费用************");
		for (ProjectVo projectvo : projectDataPageValue.getDataList()) {
			ContextManager.getInstance().setOemCode(projectvo.getOemCode());
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
