package com.liefeng.property.repository;


import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.fee.FeeSettingPo;


/**
 * 
 * <pre>      
 * Title 翻译设置存储层:
 * Description:
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  吴志敬        
 * @version 1.0      
 * @created 2016年2月18日下午4:12:28
 * </pre>
 */
@Transactional
public interface FeeSettingRepository extends JpaRepository<FeeSettingPo, String> {

	/**
	 * 根据项目id和费用类型查询费用设置
	 * @param period
	 * @param feeType
	 * @return
	 */
	public FeeSettingPo findByProjectIdAndFeeType(String project, String feeType);

	/**
	 * 根据项目id查询分页
	 * @param projectId
	 * @param pageable
	 * @return
	 */
	public Page<FeeSettingPo> findByProjectId(String projectId,
			Pageable pageable);

}
