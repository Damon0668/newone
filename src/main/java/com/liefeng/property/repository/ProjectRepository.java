package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.project.ProjectPo;

/**
 * 项目仓储层
 * 
 * @author Huangama
 * @date 2015-12-22
 */
@Transactional
public interface ProjectRepository extends JpaRepository<ProjectPo, String> {

		Page<ProjectPo> findByOemCode(String oemCode, Pageable pageable);
		
		/**
		 * 根据项目全名查找项目
		 * @param fullName 项目全名
		 * @param oemCode 系统标识
		 * @return 具体项目
		 */
		ProjectPo findByFullNameAndOemCode(String fullName, String oemCode);
}
