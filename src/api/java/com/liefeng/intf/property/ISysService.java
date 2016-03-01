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
	
	/**
	 * 根据字典名查找字典值
	 * @param groupCode 字典组编码
	 * @param name 字典名
	 * @return 字典值
	 */
	public String getDictValueByName(String groupCode, String name);
	
	/**
	 * 根据字典值反向查找字典名
	 * @param groupCode 字典组编码
	 * @param value 字典值
	 * @return 字典名
	 */
	public String getDictNameByValue(String groupCode, String value);
	
}
