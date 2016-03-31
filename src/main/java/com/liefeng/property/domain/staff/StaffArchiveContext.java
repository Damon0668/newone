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
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.property.error.PropertyStaffErrorCode;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.po.staff.StaffArchivePo;
import com.liefeng.property.repository.staff.StaffArchiveRepository;
import com.liefeng.property.vo.staff.StaffArchiveVo;

/**
 * 员工档案领域模型
 * 
 * @author ZhenTingJun
 * @author 蔡少东
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
	 * 员工ID
	 */
	private String staffId;
	
	/**
	 * 员工档案值对象
	 */
	private StaffArchiveVo staffArchive;
	
	protected void setStaffArchiveId(String staffArchiveId) {
		this.staffArchiveId = staffArchiveId;
	}

	protected void setStaffArchive(StaffArchiveVo staffArchive) {
		this.staffArchive = staffArchive;
	}
	
	protected void setStaffId(String staffId) {
		this.staffId = staffId;
	}

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
		staffArchiveContext.setStaffArchive(staffArchive);
		
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
		staffArchiveContext.setStaffArchiveId(staffArchiveId);
		
		return staffArchiveContext;
	}
	
	/**
	 * 根据员工ID加载上下文
	 * @return
	 */
	public static StaffArchiveContext loadByStaffId(String staffId){
		StaffArchiveContext staffArchiveContext = getInstance();
		staffArchiveContext.setStaffId(staffId);
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
			
			if(ValidateHelper.isNotEmptyString(staffId)) {
				staffArchivePo = staffArchiveRepository.findByStaffId(staffId);
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
			//校验手机是否存在
			isExistPhoneNum();
			
			staffArchive.setId(UUIDGenerator.generate());
			staffArchive.setOemCode(ContextManager.getInstance().getOemCode());
			StaffArchivePo staffArchivePo = MyBeanUtil.createBean(staffArchive, StaffArchivePo.class);
			staffArchiveRepository.save(staffArchivePo);
		}
	}
	
	/**
	 * 保存员工档案
	 */
	public void update() {
		if(staffArchive != null && ValidateHelper.isNotEmptyString(staffArchive.getId())) {
			logger.info("update id = {}", staffArchive.getId());
			StaffArchivePo staffArchivePo = staffArchiveRepository.findOne(staffArchive.getId());
			if(staffArchivePo != null){
				MyBeanUtil.copyBeanNotNull2Bean(staffArchive, staffArchivePo);
				
				String oemCode = ContextManager.getInstance().getOemCode();
				
				StaffArchivePo staffArchiveByPhone = staffArchiveRepository.findByPhoneAndOemCode(staffArchive.getPhone(), oemCode);
				
				if(!staffArchivePo.getId().equals(staffArchiveByPhone.getId())){
					throw new PropertyException(PropertyStaffErrorCode.MOBILE_HAS_EXIST);
				}

				staffArchiveRepository.save(staffArchivePo);
			}
			
		}
	}
	
	/**
	 * 检查手机号是否已存在
	 */
	public void isExistPhoneNum(){
		if(staffArchive != null) {
			String oemCode = ContextManager.getInstance().getOemCode();
			StaffArchivePo staffArchivePo = staffArchiveRepository.findByPhoneAndOemCode(staffArchive.getPhone(), oemCode);
			if(staffArchivePo != null){
				throw new PropertyException(PropertyStaffErrorCode.MOBILE_HAS_EXIST);
			}
		}
	}
}
