package com.liefeng.property.domain.staff;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.staff.ManageProjectPo;
import com.liefeng.property.repository.mybatis.ManageProjectQueryRepository;
import com.liefeng.property.repository.staff.ManageProjectRepository;
import com.liefeng.property.vo.staff.ManageProjectVo;

/**
 * 员工与项目关系
 * 领域模型
 * @author 蔡少东
 * @date 2016年2月23日
 */
@Service
@Scope("prototype")
public class ManageProjectContext {
	
	private static Logger logger = LoggerFactory.getLogger(ManageProjectContext.class);
	
	@Autowired
	private ManageProjectRepository manageProjectRepository;
	
	@Autowired
	private ManageProjectQueryRepository manageProjectQueryRepository;
	
	/**
	 * 员工ID
	 */
	private String staffId;
	
	
	protected void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	private static ManageProjectContext getInstance() {
		return SpringBeanUtil.getBean(ManageProjectContext.class);
	}
	
	public static ManageProjectContext build(String staffId) {
		ManageProjectContext manageProjectContext = getInstance();
		manageProjectContext.setStaffId(staffId);
		return manageProjectContext;
	}
	
	public static ManageProjectContext build() {
		ManageProjectContext manageProjectContext = getInstance();
		return manageProjectContext;
	}
	
	/**
	 * 授权管理相关项目
	 * @param projectIds
	 */
	public void grantManageProject(String[] projectIds){
		if(staffId != null){
			if(projectIds != null && projectIds.length > 0){
				deleteAll();
				for (String projectId : projectIds) {
					ManageProjectPo manageProjectPo = new ManageProjectPo();
					manageProjectPo.setId(UUIDGenerator.generate());
					manageProjectPo.setStaffId(staffId);
					manageProjectPo.setProjectId(projectId);
					manageProjectRepository.save(manageProjectPo);
				}
			}
		}
	}
	
	public void deleteAll(){
		manageProjectRepository.deleteByStaffId(staffId);
	}
	
	public  void deleteByProjectId(String projectId){
		logger.info("deleteByProjectId projectId = {}", projectId);
		manageProjectRepository.deleteByProjectId(projectId);
	}
	
	public List<String> findManageProjectName(){
		if(ValidateHelper.isNotEmptyString(staffId)){
			List<ManageProjectVo> ManageProjectList = manageProjectQueryRepository.findByStaffId(staffId);
			if(ValidateHelper.isNotEmptyCollection(ManageProjectList)){
				List<String> projectName = new ArrayList<String>();
				for (ManageProjectVo manageProjectVo : ManageProjectList) {
					projectName.add(manageProjectVo.getProjectName());
				}
				return projectName;
			}
			
		}
		return new ArrayList<String>();
	}
}
