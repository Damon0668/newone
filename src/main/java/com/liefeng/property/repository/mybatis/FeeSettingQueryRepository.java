package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.fee.FeeSettingVo;

/**
 * 抄表列表
 * @author wuzhijing
 * @date 2016年2月23日19:38:33
 */
public interface FeeSettingQueryRepository extends BaseRepository<FeeSettingVo>{

	public List<FeeSettingVo> findByProjectIdAndChargeable(String projectId);
}
