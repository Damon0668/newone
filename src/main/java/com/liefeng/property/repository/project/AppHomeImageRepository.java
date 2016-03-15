package com.liefeng.property.repository.project;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.project.AppHomeImagePo;

/**
 * 业主手机端首页轮播图仓储层
 * 
 * @author ZhenTingJun
 * @date 2016年3月11日
 */
@Transactional
public interface AppHomeImageRepository extends JpaRepository<AppHomeImagePo, String> {
	
	/**
	 * 分页查询首页轮播图
	 * @param projectId 小区ID
	 * @param pageable 分页参数
	 * @return 轮播图分页数据
	 */
	public Page<AppHomeImagePo> findByProjectIdOrderBySeq(String projectId, Pageable pageable);

}
