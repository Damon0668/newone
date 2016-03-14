package com.liefeng.property.repository.project;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.project.ProjectNoticePo;

/**
 * 小区通告仓储层
 * 
 * @author ZhenTingJun
 * @date 2016年3月11日
 */
@Transactional
public interface ProjectNoticeRepository extends JpaRepository<ProjectNoticePo, String> {
	/**
	 * 根据小区ID查询小区通告
	 * @param projectId 小区ID
	 * @param pageable 分页参数
	 * @return 小区通告分页数据
	 */
	public Page<ProjectNoticePo> findByProjectIdOrderByCreateTime(String projectId, Pageable pageable);
}
