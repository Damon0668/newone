package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.project.HouseCheckVo;
import com.liefeng.property.vo.project.HouseSpecVo;

/**
 * 房屋验收单
 * @author xhw
 * @date 2016年5月6日 上午9:15:01
 */
public interface HouseCheckQueryRepository extends BaseRepository<HouseCheckVo>{
	/**
	 * 获取某房间的验收单
	 * @param paramMap
	 * @return 
	 * @author xhw
	 * @date 2016年5月6日 上午9:34:42
	 */
	public List<HouseCheckVo> queryByProjectIdAndHouseNum(PagingParamVo paramMap);
}
