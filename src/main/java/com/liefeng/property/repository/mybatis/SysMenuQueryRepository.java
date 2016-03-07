package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.sys.SysMenuVo;


/**
 * 菜单信息查询接口
 * @author 蔡少东
 * @date 2016年2月19日
 */
public interface SysMenuQueryRepository extends BaseRepository<SysMenuVo>{
	
	/**
	 * 根据用户ID查询此用户拥有的按钮的code值
	 * @param userId
	 * @return
	 */
	public List<String> queryButtonsCodeByUserId(String userId);
	
	/**
	 * 根据用户ID查询此用户拥有的菜单
	 * 不包含按钮级别
	 * @param userId
	 * @return
	 */
	public List<SysMenuVo> queryMenusByUserId(String userId);
	
}
