package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.project.HouseSpecVo;

/**
 * 房产规格Mybatis查询仓储层
 * 
 * @author ZhenTingJun
 * @date 2016年2月18日
 */
public interface HouseSpecQueryRepository extends BaseRepository<HouseSpecVo>{
	
	/**
	 * 查询已关联房型的房产数量
	 * @param houseSpecId 房型ID
	 * @return 房产数量
	 */
	public Long queryRelatedHouseCount(String houseSpecId);
	
	
	/**
	 * 根据id，获取房间规格信息
	 * @param paramMap
	 * @return 
	 * @author xhw
	 * @date 2016年4月22日 上午11:15:32
	 */
	public HouseSpecVo queryByHouseSpecId(PagingParamVo paramMap);
	
	/**
	 * 获取某项目某楼栋中的每一层的房间数
	 * @param paramMap
	 * @return 
	 * @author xhw
	 * @date 2016年4月24日 下午3:49:38
	 */
	public List<HouseSpecVo> countByProjectIdAndBuildingId(PagingParamVo paramMap);
	
	/**
	 * "验收管理列表"专用:获取总数
	 * @param paramMap
	 * @return 
	 * @author xhw
	 * @date 2016年5月5日 上午10:38:09
	 */
	public Long countForHouseCheck(PagingParamVo paramMap);
	
	/**
	 * "验收管理列表"专用：获取列表
	 * @param paramMap
	 * @return 
	 * @author xhw
	 * @date 2016年5月5日 上午10:39:48
	 */
	public List<HouseSpecVo> queryForHouseCheck(PagingParamVo paramMap);
}
