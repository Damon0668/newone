package com.liefeng.property.repository.household;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.liefeng.property.po.household.CheckinQueuePo;

/**
 * 入住排队仓储层
 * 
 * @author ZhenTingJun
 * @date 2016年3月6日
 */
@Transactional
public interface CheckinQueueRepository extends JpaRepository<CheckinQueuePo, String> {
	
	/**
	 * 获取用户今天某状态的排队号
	 * @param userId 手机端用户id
	 * @param projectId 项目id
	 * @param houseId 房间id
	 * @param queryDate 查询日期
	 * @return 
	 * @author xhw
	 * @date 2016年3月8日 下午7:22:51
	 */
	@Query("select c from CheckinQueuePo c where c.userId=?1 and c.projectId=?2 and c.houseId=?3 and c.status=?4  and datediff(?5, c.createTime)=0 ")
	public CheckinQueuePo findCheckinQueue(String userId, String projectId, String houseId, String status, String queryDate);
	
	/**
	 * 根据项目id，时间，获取今天的所有排队
	 * @param projectId 项目id
	 * @param queryDay 日期
	 * @return 
	 * @author xhw
	 * @date 2016年3月8日 下午7:26:16
	 */
	@Query("select c from CheckinQueuePo c where c.projectId=?1 and datediff(?2, c.createTime)=0 ")
	public List<CheckinQueuePo> findCheckinQueueOfToday(String projectId, String queryDay);
	
	/**
	 * 根据手机端用户id，项目id，房间id，状态，获取最近一条排队
	 * @param userId 手机端用户id
	 * @param projectId 项目id
	 * @param houseId 房间id
	 * @param status 状态
	 * @return 
	 * @author xhw
	 * @date 2016年3月8日 下午8:30:57
	 */
	public CheckinQueuePo findByUserIdAndProjectIdAndHouseIdAndStatusOrderByCreateTimeDesc(String userId, String projectId, String houseId, String status);

	/**
	 * 根据项目id、状态，时间、获取最新的该状态的排队
	 * @param projectId 项目id
	 * @param status 状态
	 * param queryDate 时间
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 上午9:50:01
	 */
	@Query("select c from CheckinQueuePo c where c.projectId=?1 and c.status=?2 and datediff(?3, c.createTime)=0 order by c.seq desc")
	public CheckinQueuePo findByProjectIdAndStatusAndCreateTimeOrderBySeqDesc(String projectId, String status, String queryDate);
	
	/**
	 * 根据项目id、状态、时间，获取非此状态的排队
	 * @param projectId 项目id
	 * @param status 状态
	 * @param queryDate 时间
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 上午10:51:54
	 */
	@Query("select c from CheckinQueuePo c where c.projectId=?1 and c.status!=?2 and datediff(?3, c.createTime)=0 order by c.seq desc")
	public Page<CheckinQueuePo> findOfProjectIdAndTodayAndNotStatus(String projectId, String status, String queryDate, Pageable pageable);
	
	/**
	 * 根据项目id、状态，时间、获取最新的该状态的排队
	 * @param projectId 项目id
	 * @param status 状态
	 * param queryDate 时间
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 上午9:50:01
	 */
	@Query("select c from CheckinQueuePo c where c.projectId=?1 and c.status=?2 and c.seq>?3 and datediff(?4, c.createTime)=0 order by c.seq")
	public List<CheckinQueuePo> findByProjectIdAndStatusAndSeqAndCreateTimeOrderBySeq(String projectId, String status, Integer seq, String queryDate);
}
