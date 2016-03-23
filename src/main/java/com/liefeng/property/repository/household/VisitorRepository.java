package com.liefeng.property.repository.household;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.liefeng.property.po.household.VisitorPo;

/**
 * 访客信息仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface VisitorRepository extends JpaRepository<VisitorPo, String> {

	/**
	 * 获取用户添加的访客
	 * @param userId 即申请人id
	 * @return 
	 * @author xhw
	 * @date 2016年3月23日 下午2:45:47
	 */
	@Query("select v from VisitorPo v where v.applicantId=?1 group by v.phone order by v.inTime desc")
	public List<VisitorPo> getVisitorPOList(String userId);
	
	/**
	 * 获取访客的访问记录
	 * @param phone
	 * @return 
	 * @author xhw
	 * @date 2016年3月23日 下午3:21:22
	 */
	public List<VisitorPo> findByPhoneOrderByInTimeDesc(String phone);
	
}
