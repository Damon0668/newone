package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.household.AppFriendVo;

/**
 * 通讯录好友查询
 * @author xhw
 * @date 2016年3月16日 下午6:40:28
 */
public interface AppFriendQueryRepository extends BaseRepository<AppFriendVo> {
	
	/**
	 * 查询业主/住户
	 * @param param {userId：用户id；condition：过滤条件}
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午6:45:13
	 */
	public List<AppFriendVo> queryUserList(PagingParamVo param);
	
	/**
	 * 查询用户的好友列表
	 * @param param {userId：用户id}
	 * @return 
	 * @author xhw
	 * @date 2016年3月17日 上午9:10:18
	 */
	public List<AppFriendVo> queryFriendList(PagingParamVo param);
	
	/**
	 * 查询用户的好友操作历史
	 * @param param {userId：用户id}
	 * @return 
	 * @author xhw
	 * @date 2016年3月17日 上午9:10:18
	 */
	public List<AppFriendVo> queryFriendHistoryList(PagingParamVo param);
	
}
