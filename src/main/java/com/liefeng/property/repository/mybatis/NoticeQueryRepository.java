package com.liefeng.property.repository.mybatis;

import java.util.List;
import java.util.Map;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.workbench.NoticeVo;

/**
 * 通知信息查询接口
 * @author xhw
 * @date 2016年2月29日上午10:44:24
 */
public interface NoticeQueryRepository extends BaseRepository<NoticeVo>{
	/**
	 * 查询通知列表（分页）
	 * @param paramMap {staffId：员工ID；status：状态； manageProject：管理的项目； orderBy：排序类型}
	 * @return                      
	 * @author xhw
	 * @date 2016年2月29日 上午10:45:54
	 */
	public List<NoticeVo> queryByPage(Map<String, String> paramMap);
	
	/**
	 * 查询总数
	 * @param paramMap {staffId：员工ID；status：状态； manageProject：管理的项目； orderBy：排序类型}
	 * @return                      
	 * @author xhw
	 * @date 2016年2月29日 上午10:47:29
	 */
	public Long queryByCount(Map<String, String> paramMap);
	
	/**
	 * 查询已发布通知列表（分页）
	 * @param paramMap {staffId：员工ID；status：状态； manageProject：管理的项目； deptId：员工所在部门}
	 * @return                      
	 * @author xhw
	 * @date 2016年2月29日 上午10:45:54
	 */
	public List<NoticeVo> queryPublishedByPage(PagingParamVo paramMap);
	
	/**
	 * 查询已发布通知总数
	 * @param paramMap {staffId：员工ID；status：状态； manageProject：管理的项目； deptId：员工所在部门}
	 * @return                      
	 * @author xhw
	 * @date 2016年2月29日 上午10:47:29
	 */
	public Long queryPublishedByCount(PagingParamVo paramMap);
	
	/**
	 * 查看已发布通知（分页、app）
	 * @param paramMap {terminal：接收端类型；noticeType：通知类型； projectId：项目id；groupId：部门id（楼栋id）；privilegeType：接收人类型；}
	 * @return
	 * @author xhw
	 * @2016年3月7日 下午3:18:03
	 */
	public List<NoticeVo> queryOfPublished(PagingParamVo paramMap);
	
	/**
	 * 查询已发布通知总数(app)
	 * @param paramMap {terminal：接收端类型；noticeType：消息类型； projectId：项目id；groupId：部门id（楼栋id）；privilegeType：接收人类型；}
	 * @return
	 * @author xhw
	 * @2016年3月7日 下午3:18:56
	 */
	public Long queryCountOfPublished(PagingParamVo paramMap);
	
}
