package com.liefeng.property.repository;


import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.fee.LadderFeeSettingPo;

/**
 * 阶梯收费存储层
 * @author wuzhijing
 *
 */
@Transactional
public interface LadderFeeSettingRepository extends JpaRepository<LadderFeeSettingPo, String> {

	/**
	 * 查询查阶梯收费(分页）
	 * @param projectId
	 * @param pageable
	 * @return
	 */
	public Page<LadderFeeSettingPo> findByProjectId(String projectId,
			Pageable pageable);


	/**
	 *  根据id查询阶梯收费设置
	 * @param id
	 * @return
	 */
	public LadderFeeSettingPo findById(String id);

	/**
	 * 根据项目id和费用类型查询
	 * @param projectId 项目id
	 * @param feeType 费用类型
	 * @return
	 */
	public LadderFeeSettingPo findByProjectIdAndFeeType(String projectId,
			String feeType);

	/**
	 * 项目id、费用类型、使用性质 查询
	 * @param projectId 项目id
	 * @param feeType 费用类型
	 * @param useType使用性质
	 * @return
	 */
	public LadderFeeSettingPo findByProjectIdAndFeeTypeAndUseType(
			String projectId, String feeType, String useType);
}
