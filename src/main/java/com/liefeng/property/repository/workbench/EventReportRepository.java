package com.liefeng.property.repository.workbench;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.workbench.EventReportPo;

/**
 * 报事仓储层
 * @author Huangama
 * @date 2016-3-3
 */
@Transactional
public interface EventReportRepository extends JpaRepository<EventReportPo, String> {

	public EventReportPo findByWfOrderId(String wfOrderId);
	
	/**
	 * 获取用户的所有报事
	 * @param projectId 项目id
	 * @param houseNum 房间号
	 * @param phone 手机号码
	 * @return 
	 * @author xhw
	 * @date 2016年3月18日 下午4:44:52
	 */
	public List<EventReportPo> findByProjectIdAndHouseNumAndPhone(String projectId, String houseNum, String phone);

}
