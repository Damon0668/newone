package com.liefeng.property.repository;

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
	
	@Query("select c from CheckinQueuePo c where c.userId=?1 and c.projectId=?2 and c.houseId=?3 and c.status='0'  and datediff(?4, c.createTime)=0 ")
	public CheckinQueuePo findCheckinQueue(String userId, String projectId, String houseId, String queryDate);
}
