package com.liefeng.property.repository.workbench;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.workbench.SchedulePo;


/**
 * 日程仓储层
 * @author xhw
 * @2016年3月2日 上午8:45:36
 */
@Transactional
public interface ScheduleRepository extends JpaRepository<SchedulePo, String> {
	
	/**
	 * 根据日程id，删除日程
	 * @param id 日程id
	 * @author xhw
	 * @2016年3月2日 上午9:12:15
	 */
	public void deleteById(String id);
	
	/**
	 * 根据创建人id，删除所有属于该创建人的所有日程
	 * @param creatorId 创建人id
	 * @author xhw
	 * @2016年3月2日 上午9:13:06
	 */
	public void deleteByCreatorId(String creatorId);
}
