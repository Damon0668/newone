package com.liefeng.property.repository;

import javax.transaction.Transactional;

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

}
