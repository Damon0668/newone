package com.liefeng.property.domain.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.property.po.staff.ManageProjectPo;
import com.liefeng.property.repository.staff.ManageProjectRepository;

/**
 * 员工与项目关系
 * 领域模型
 * @author 蔡少东
 * @date 2016年2月23日
 */
@Service
@Scope("prototype")
public class ManageProjectContext {
	
	@Autowired
	private ManageProjectRepository manageProjectRepository;
	
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
}
