package com.liefeng.property.repository.workbench;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.workbench.WebsiteMsgPo;


/**
 * 站内消息仓储层
 * @author xhw
 * @2016年3月2日 下午3:49:26
 */
@Transactional
public interface WebsiteMsgRepository extends JpaRepository<WebsiteMsgPo, String> {
	
	/**
	 * 根据消息id，删除消息及其回复消息
	 * @param id 消息id
	 * @author xhw
	 * @2016年3月2日 下午4:05:44
	 */
	public void deleteByIdOrParentId(String id, String parentId);
	
	/**
	 * 查询站内消息的回复信息
	 * @param parentId
	 * @return
	 * @author xhw
	 * @2016年3月3日 下午2:16:01
	 */
	public List<WebsiteMsgPo> findByParentIdOrderByCreateTimeDesc(String parentId);
	
	 /**
	  * 根据创建人id，获取创建人创建的消息（父消息）（分页）
	  * @param creatorId
	  * @param pageable
	  * @return
	  * @author xhw
	  * @2016年3月3日 下午2:55:41
	  */
    public Page<WebsiteMsgPo> findByCreatorIdAndParentIdIsNull(String creatorId, Pageable pageable);
}
