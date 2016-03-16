package com.liefeng.property.repository.household;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.AppFriendPo;


/**
 * 手机端好友仓储层
 * @author xhw
 * @date 2016年3月16日 下午2:02:31
 */
@Transactional
public interface AppFriendRepository extends JpaRepository<AppFriendPo, String> {
	
	/**
	 * 删除好友
	 * @param userId 用户id
	 * @param friendId 好友id
	 * @author xhw
	 * @date 2016年3月16日 下午2:53:56
	 */
	public void deleteByUserIdAndFriendId(String userId, String friendId);
	
	/**
	 * 根据用户id、好友状态，获取用户的好友列表
	 * @param userId 用户id
	 * @param status 好友状态
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午3:51:53
	 */
	public List<AppFriendPo> findByUserIdAndStatus(String userId, String status);
	
}
