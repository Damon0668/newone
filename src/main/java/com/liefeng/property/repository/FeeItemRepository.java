package com.liefeng.property.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.liefeng.property.po.fee.FeeItemPo;

/**
 * 费用项仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface FeeItemRepository extends JpaRepository<FeeItemPo, String> {

	@Query("select fi from FeeItemPo fi where fi.projectId=?1 and  fi.houseNum=?2 and fi.feeType=?3  and ?4 between fi.startDate and  fi.endDate")
	public FeeItemPo getPreFeeItem(String projectId, String houseNum,
			String feeType, Date preDate);

	/**
	 * 获取该房号的所属时间段的所有费用数据
	 * 
	 * @param projectId
	 *            项目ID
	 * @param houseNum
	 *            房号
	 * @param startDate
	 *            计费所属开始时间
	 * @param endDate
	 *            计费所属结束时间
	 * @return
	 */
	@Query("select fi from FeeItemPo fi where fi.projectId=?1 and fi.houseNum=?2 and fi.startDate>=?3 and fi.endDate<=?4  order by fi.feeType")
	public List<FeeItemPo> getPreFeeItem(
			String projectId, String houseNum, Date startDate, Date endDate);

}
