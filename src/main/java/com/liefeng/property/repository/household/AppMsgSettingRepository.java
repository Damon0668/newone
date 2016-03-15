package com.liefeng.property.repository.household;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.AppMsgSettingPo;


/**
 * 用户手机端消息设置仓储层
 *  
 * @author xhw
 * @date 2016年3月14日 上午11:41:18
 */
@Transactional
public interface AppMsgSettingRepository extends JpaRepository<AppMsgSettingPo, String> {
	
	/**
	 * 通过用户id，获取用户手机端消息设置
	 * @param userId 用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 上午11:58:51
	 */
	public AppMsgSettingPo findByUserId(String userId);
}
