package com.liefeng.property.repository.mybatis;

import java.util.List;
import java.util.Map;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.guard.AttendantVo;

/**
 * 服务人员
 * @author xhw
 * @date 2016年4月18日 下午6:07:03
 */
public interface AttendantQueryRepository extends BaseRepository<AttendantVo>{
	
	/**
	 * 查询总数
	 * @param paramMap
	 * @return 
	 * @author xhw
	 * @date 2016年4月14日 下午2:46:23
	 */
	public Long queryByCount(Map<String, String> paramMap);
	
	/**
	 * 分页查询
	 * @param paramMap
	 * @return 
	 * @author xhw
	 * @date 2016年4月14日 下午2:46:56
	 */
	public List<AttendantVo> queryByPage(Map<String, Object> paramMap);
	
}
