package com.liefeng.property.domain.staff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.staff.StaffArchivePo;
import com.liefeng.property.repository.StaffArchiveRepository;
import com.liefeng.property.vo.staff.StaffArchiveVo;

/**
 * 员工档案领域模型
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class StaffArchiveContext {
	
	private static Logger logger = LoggerFactory.getLogger(StaffArchiveContext.class);
	
	@Autowired
	private StaffArchiveRepository staffArchiveRepository;
	
	/**
	 * 员工档案ID
	 */
	private String staffArchiveId;
	
	/**
	 * 员工档案值对象
	 */
	private StaffArchiveVo staffArchive;
	
	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	private static StaffArchiveContext getInstance() {
		return SpringBeanUtil.getBean(StaffArchiveContext.class);
	}
	
	/**
	 * 根据员工档案值对象构建上下文
	 * @param staffArchive 员工档案值对象
	 * @return 员工档案上下文
	 */
	public static StaffArchiveContext build(StaffArchiveVo staffArchive) {
		StaffArchiveContext staffArchiveContext = getInstance();
		staffArchiveContext.staffArchive = staffArchive;
		
		return staffArchiveContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 员工档案上下文
	 */
	public static StaffArchiveContext build() {
		StaffArchiveContext staffArchiveContext = getInstance();
		
		return staffArchiveContext;
	}
	
	/**
	 * 根据员工档案ID加载上下文
	 * @param staffArchiveId 员工档案ID
	 * @return 员工档案上下文
	 */
	public static StaffArchiveContext loadById(String staffArchiveId) {
		StaffArchiveContext staffArchiveContext = getInstance();
		staffArchiveContext.staffArchiveId = staffArchiveId;
		
		return staffArchiveContext;
	}
	
	/**
	 * 查询员工档案信息
	 * @return 员工档案值对象
	 */
	public StaffArchiveVo getStaffArchive() {
		if(staffArchive == null) {
			StaffArchivePo staffArchivePo = null;
			if(ValidateHelper.isNotEmptyString(staffArchiveId)) {
				staffArchivePo = staffArchiveRepository.findOne(staffArchiveId);
			}
			
			if(staffArchivePo != null) {
				staffArchive = MyBeanUtil.createBean(staffArchivePo, StaffArchiveVo.class);
			}
		}
		
		return staffArchive;
	}

	/**
	 * 保存员工档案
	 */
	public void create() {
		if(staffArchive != null) {
			staffArchive.setId(UUIDGenerator.generate());
			staffArchive.setOemCode(""); // TODO 待确定后补齐
			
			StaffArchivePo staffArchivePo = MyBeanUtil.createBean(staffArchive, StaffArchivePo.class);
			staffArchiveRepository.save(staffArchivePo);
		}
	}
}
