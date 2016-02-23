package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.fee.MeterRecordVo;

/**
 * 抄表列表
 * @author wuzhijing
 * @date 2016年2月23日19:38:33
 */
public interface MeterRecordQueryRepository extends BaseRepository<MeterRecordVo>{
	
	/**
	 * 抄表列表分页
	 * @param meterRecordVo
	 * @return
	 */
	public List<MeterRecordVo> queryByPage(MeterRecordVo meterRecordVo);
	
	/**
	 * 抄表列表分页 查询总数量
	 * @param meterRecordVo
	 * @return 总数
	 */
	public Long queryByCount(MeterRecordVo meterRecordVo);
}
