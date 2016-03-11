package com.liefeng.property.repository.workbench;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.workbench.NoticePo;


/**
 * 通知仓储层
 * @author xhw
 * @date 2016年2月26日下午3:35:09
 */
@Transactional
public interface NoticeRepository extends JpaRepository<NoticePo, String> {
	/**
	 * 获取在某个状态的所有通知
	 * @param status
	 * @return                      
	 * @author xhw
	 * @date 2016年3月1日 下午2:24:54
	 */
	public List<NoticePo> findByStatus(String status);
}
