package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.fee.FeeItemVo;

/**
 * 费用查询
 * @author wuzhijing
 * @date 2016年2月23日19:38:33
 */
public interface FeeItemQueryRepository extends BaseRepository<FeeItemVo>{

	public Long queryAllByCount(PagingParamVo param);

	public List<FeeItemVo> queryAllByPage(PagingParamVo param);

	public Long queryPersonalByCount(PagingParamVo param);

	public List<FeeItemVo> queryPersonalByPage(PagingParamVo param);

}
