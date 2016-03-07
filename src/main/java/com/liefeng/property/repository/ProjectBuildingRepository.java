package com.liefeng.property.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.project.ProjectBuildingPo;

/**
 * 项目楼栋楼层仓储层
 * 
 * @author ZhenTingJun
 * @author levy
 * @date 2015-12-24
 */
@Transactional
public interface ProjectBuildingRepository extends JpaRepository<ProjectBuildingPo, String> {
	
	/**
	 * 根据项目ID和楼栋名称查找楼栋
	 * @param projectId 项目ID
	 * @param name 楼栋名称
	 * @return
	 */
	public ProjectBuildingPo findByProjectIdAndName(String projectId, String name);
	
	/**
	 * 根据所属楼栋ID和楼层名称查找楼层
	 * @param parentId 所属楼栋ID
	 * @param name 楼层名称
	 * @return
	 */
	public ProjectBuildingPo findByParentIdAndName(String parentId, String name);

    /**
     * 分页查询项目下的楼栋
     * @param projectId 项目ID
     * @return
     */
	Page<ProjectBuildingPo> findBuildingsByProjectIdAndParentIdIsNull(String projectId, Pageable pageable);
	
    /**
     * 分页查询楼栋下的楼层
     * @param parentId 楼栋ID，楼层记录才有parentId
     * @return
     */
    public Page<ProjectBuildingPo> findFloorsByParentId(String parentId, Pageable pageable);

    /**
     * 获取某项目下的所有楼栋
     * @param projectId
     * @return
     */
	public List<ProjectBuildingPo> findByProjectIdAndParentIdIsNull(
			String projectId);
}
