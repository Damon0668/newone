package com.liefeng.property.repository.guard;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.guard.DevicePositionPo;

@Transactional
public interface DevicePositionRepository extends JpaRepository<DevicePositionPo, String>{
	
	/** 
	 * 查找位置列表
	 * @param projectId 项目ID
	 * @return
	 */
	List<DevicePositionPo> findByProjectId(String projectId);
	
	/**
	 * 查找位置列表(分页)
	 * @param projectId 项目ID
	 * @param pageable 分页参数
	 * @return
	 */
	Page<DevicePositionPo> findByProjectId(String projectId,Pageable pageable);
}
