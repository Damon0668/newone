package com.liefeng.property.repository.fee;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.fee.MeterSettingPo;

/**
 * 
 * <pre>      
 * Title 仪表设置存储层:
 * Description:
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  wuzhijing        
 * @version 1.0      
 * @created 2016年2月18日下午4:12:28
 * </pre>
 */
@Transactional
public interface MeterSettingRepository extends JpaRepository<MeterSettingPo, String> {

	/**
	 * 查询查表(分页）
	 * @param projectId
	 * @param pageable
	 * @return
	 */
	public Page<MeterSettingPo> findByProjectId(String projectId,
			Pageable pageable);

	/**
	 * 根据项目id仪表类型查询仪表设置
	 * @param projectId
	 * @param type
	 * @return
	 */
	public MeterSettingPo findByProjectIdAndType(String projectId, String type);

	/**
	 *  根据id查询仪表设置
	 * @param id
	 * @return
	 */
	public MeterSettingPo findById(String id);

	/**
	 * 获取项目下要抄的表
	 * @param projectId
	 * @param chargeableYes
	 * @return
	 */
	public List<MeterSettingPo> findByProjectIdAndChargeable(String projectId,
			String chargeableYes);
}
