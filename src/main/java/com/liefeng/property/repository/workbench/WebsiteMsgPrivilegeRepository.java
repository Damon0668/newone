package com.liefeng.property.repository.workbench;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.workbench.WebsiteMsgPrivilegePo;


/**
 * 站内消息权限仓储层
 * @author xhw
 * @2016年3月2日 下午4:58:00
 */
@Transactional
public interface WebsiteMsgPrivilegeRepository extends JpaRepository<WebsiteMsgPrivilegePo, String> {
	
	/**
	 * 根据消息id，获取消息的权限
	 * @param messageId 消息id
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午5:11:13
	 */
	public List<WebsiteMsgPrivilegePo> findByMessageId(String messageId);
	
	/**
	 * 根据消息id，删除消息的权限
	 * @param messageId 消息id
	 * @author xhw
	 * @2016年3月2日 下午5:13:59
	 */
	public void deleteByMessageId(String messageId);
}
