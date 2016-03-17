package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.household.AppFriendVo;
import com.liefeng.property.vo.household.ResidentFeedbackVo;

/**
 * 通讯录好友查询
 * @author xhw
 * @date 2016年3月16日 下午6:40:28
 */
public interface AppFriendQueryRepository extends BaseRepository<AppFriendVo> {
	
	/**
	 * 查询业主/住户
	 * @param param
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午6:45:13
	 */
	public List<AppFriendVo> queryUserList(PagingParamVo param);
	
	
}
