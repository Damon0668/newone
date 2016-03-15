package com.liefeng.property.repository.fee;


import java.util.List;

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

	/**
	 * 查询费用设置
	 * @param projectId 项目id
	 * @param feeType 费用类型id
	 * @param useType 使用性质id
	 * @return
	 */
	public FeeSettingPo findByProjectIdAndFeeTypeAndUseType(String projectId,
			String feeType, String useType);

	/**
	 * 根据项目id查询分页(费用类型排序)
	 * @param projectId
	 * @param pageable
	 * @return
	 */
	public Page<FeeSettingPo> findByProjectIdOrderByFeeTypeDesc(
			String projectId, Pageable pageable);

	/**
	 * 获取费用设置
	 * @param projectId 项目id
	 * @param useType 使用性质
	 * @param feeType 费用类型
	 * @param chargeableYes 1收取,2不收取
	 * @return 
	 */
	public FeeSettingPo findByProjectIdAndUseTypeAndFeeTypeAndChargeable(String projectId,String useType,
			String feeType, String chargeableYes);
	
	/**
	 * 获取项目下启动的费用项
	 * @param projectId
	 * @param chargeableYes
	 * @return
	 */
	public List<FeeSettingPo> findByProjectIdAndChargeable(String projectId,
			String chargeableYes);

}
