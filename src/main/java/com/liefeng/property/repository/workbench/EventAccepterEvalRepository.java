package com.liefeng.property.repository.workbench;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.liefeng.property.po.household.CheckinQueuePo;
import com.liefeng.property.po.workbench.EventAccepterEvalPo;


/**
 * 事件办理人评价仓储层
 * @author xhw
 * @date 2016年3月25日 下午1:44:25
 */
@Transactional
public interface EventAccepterEvalRepository extends JpaRepository<EventAccepterEvalPo, String> {
	/**
	 * 获取员工的今日点赞
	 * @param staffId
	 * @param queryDate
	 * @return 
	 * @author xhw
	 * @date 2016年4月13日 下午2:39:05
	 */
	@Query("select e from EventAccepterEvalPo e where e.accepterId=?1 and e.likes='1' and datediff(?2, e.createTime)=0")
	public List<EventAccepterEvalPo> findByAccepterIdAndCreateTime(String staffId, String queryDate);
	
	/**
	 * 获取员工的历史点赞
	 * @param staffId
	 * @param queryDate
	 * @return 
	 * @author xhw
	 * @date 2016年4月13日 下午2:39:05
	 */
	@Query("select e from EventAccepterEvalPo e where e.accepterId=?1 and e.likes='1'")
	public List<EventAccepterEvalPo> findByAccepterId(String staffId);
}
