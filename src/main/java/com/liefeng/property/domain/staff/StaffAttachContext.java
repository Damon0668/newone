package com.liefeng.property.domain.staff;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.staff.StaffAttachPo;
import com.liefeng.property.repository.mybatis.StaffAttachQueryRepository;
import com.liefeng.property.repository.staff.StaffAttachRepository;
import com.liefeng.property.vo.staff.StaffAttachVo;

/**
 * 员工材料领域
 * @author 蔡少东
 * @date 2016年3月21日
 */
@Service
@Scope("prototype")
public class StaffAttachContext {
	
	private static Logger logger = LoggerFactory.getLogger(StaffAttachContext.class);
	
	@Autowired
	private StaffAttachRepository staffAttachRepository;
	
	@Autowired
	private StaffAttachQueryRepository staffAttachQueryRepository;
	
	/**
	 * 员工ID
	 */
	private String staffId;
	
	/**
	 * 员工材料视图对象
	 */
	private StaffAttachVo staffAttach;

	protected void setStaffAttach(StaffAttachVo staffAttach) {
		this.staffAttach = staffAttach;
	}

	protected void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	private static StaffAttachContext getInstance() {
		return SpringBeanUtil.getBean(StaffAttachContext.class);
	}
	
	public static StaffAttachContext build() {
		StaffAttachContext staffAttachContext = getInstance();
		return staffAttachContext;
	}
	
	public static StaffAttachContext build(StaffAttachVo staffAttach) {
		StaffAttachContext staffAttachContext = getInstance();
		staffAttachContext.setStaffAttach(staffAttach);
		return staffAttachContext;
	}
	
	/**
	 * 根据员工ID加载上下文
	 * @return
	 */
	public static StaffAttachContext loadByStaffId(String staffId){
		StaffAttachContext staffAttachContext = getInstance();
		staffAttachContext.setStaffId(staffId);
		return staffAttachContext;
	}
	
	public void create(){
		if(staffAttach != null && ValidateHelper.isEmptyString(staffAttach.getId())){
			StaffAttachPo staffAttachPo = MyBeanUtil.createBean(staffAttach, StaffAttachPo.class);
			staffAttachPo.setId(UUIDGenerator.generate());
			staffAttachPo.setCreateTime(new Date());
			logger.info("create staffAttachPo = {}", staffAttachPo);
			staffAttachRepository.save(staffAttachPo);
		}
	}
	
	/**
	 * 批量创建
	 * @param staffAttachs
	 */
	@Transactional
	public void createAttachs(List<StaffAttachVo> staffAttachs){
		if(ValidateHelper.isNotEmptyString(staffId)){
			deleteAll();
			for (StaffAttachVo staffAttachVo : staffAttachs) {
				StaffAttachPo staffAttachPo = MyBeanUtil.createBean(staffAttachVo, StaffAttachPo.class);
				staffAttachPo.setCreateTime(new Date());
				staffAttachPo.setStaffId(staffId);
				staffAttachPo.setId(UUIDGenerator.generate());
				logger.info("createAttachs staffAttachPo = {}", staffAttachPo);
				staffAttachRepository.save(staffAttachPo);
			}
		}
	}
	
	public void deleteAll(){
		if(ValidateHelper.isNotEmptyString(staffId)){
			staffAttachRepository.deleteByStaffId(staffId);
		}
	}
	
	public List<StaffAttachVo> getStaffAttachs(){
		if(ValidateHelper.isNotEmptyString(staffId)){
			return staffAttachQueryRepository.findByStaffId(staffId);
		}
		return null;
	}
}
