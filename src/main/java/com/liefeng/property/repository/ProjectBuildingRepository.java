package com.liefeng.property.repository;

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
	 * 根据名字和系统标识查找楼栋
	 * @param name 名称
	 * @param oemCode 系统标识
	 * @return
	 */
	public ProjectBuildingPo findByNameAndOemCode(String name, String oemCode);

    /**
     * 分页查询项目下的楼栋
     * @param projectId 项目id
     * @return
     */
	Page<ProjectBuildingPo> findBuildingsByProjectIdAndParentIdIsNull(String projectId, Pageable pageable);
	
    /**
     * 分页查询楼栋下的楼层
     * @param parentId 楼栋id，楼层记录才有parentId
     * @return
     */
    public Page<ProjectBuildingPo> findFloorsByParentId(String parentId, Pageable pageable);
}
