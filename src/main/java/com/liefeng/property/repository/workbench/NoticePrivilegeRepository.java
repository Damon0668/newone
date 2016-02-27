package com.liefeng.property.repository.workbench;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.workbench.NoticePrivilegePo;


/**
 * 通知权限仓储层
 * @author xhw
 * @date 2016年2月26日下午6:48:02
 */
@Transactional
public interface NoticePrivilegeRepository extends JpaRepository<NoticePrivilegePo, String> {
	
	/**
	 * 根据通知id，删除通知的权限
	 * @param noticeId                      
	 * @author xhw
	 * @date 2016年2月26日 下午7:13:51
	 */
	public void deleteByNoticeId(String noticeId);
	
	/**
	 * 根据通知id，获取通知的权限
	 * @param noticeId
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午7:20:27
	 */
	public List<NoticePrivilegePo> findByNoticeId(String noticeId);
}
