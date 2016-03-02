package com.liefeng.property.repository.workbench;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.workbench.MessagePo;


/**
 * 消息仓储层
 * @author xhw
 * @2016年3月2日 下午3:49:26
 */
@Transactional
public interface MessageRepository extends JpaRepository<MessagePo, String> {
	
	/**
	 * 根据消息id，删除消息
	 * @param id 消息id
	 * @author xhw
	 * @2016年3月2日 下午4:05:44
	 */
	public void deleteById(String id);
	
}
