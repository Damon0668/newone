package com.liefeng.property.repository;

import java.util.List;

import javax.transaction.Transactional;

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
	 * 根据手机端用户id、项目id、房间id、非此状态的入住排队
	 * @param userId 手机端用户id
	 * @param projectId 项目id
	 * @param houseId 房间id
	 * @param status 状态
	 * @return 
	 * @author xhw
	 * @date 2016年3月8日 下午3:46:03
	 */
	public CheckinQueuePo findByUserIdAndProjectIdAndHouseIdAndStatusNot(String userId, String projectId, String houseId, String status);
	
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
}
