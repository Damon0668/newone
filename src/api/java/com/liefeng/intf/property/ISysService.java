package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.property.vo.sys.SysDictVo;

/**
 * 系统服务（字典、系统参数相关）对外暴露接口
 * @author Huangama
 * @date 2016-2-20
 */
public interface ISysService {
	
	/**
	 * 根据字典组编码查询字典列表
	 * @param groupCode 字典组编码
	 * @return 字典列表
	 */
	public List<SysDictVo> getDictByGroupCode(String groupCode);
	
}
