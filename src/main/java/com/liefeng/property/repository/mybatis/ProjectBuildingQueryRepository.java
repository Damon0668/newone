package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.project.ProjectBuildingVo;

/**
 * 楼栋楼层
 * @author xhw
 * @date 2016年4月22日 上午11:14:00
 */
public interface ProjectBuildingQueryRepository extends BaseRepository<ProjectBuildingVo>{
	
	/**
	 * 获取某楼栋中的所有楼层（flag只是起标记作用）
	 * @param paramMap
	 * @return 
	 * @author xhw
	 * @date 2016年4月22日 上午11:15:32
	 */
	public List<ProjectBuildingVo> queryByBuildingIdAndNum(PagingParamVo paramMap);
	
}
