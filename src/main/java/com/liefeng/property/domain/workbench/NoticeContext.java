package com.liefeng.property.domain.workbench;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.constant.WorkbenchConstants;
import com.liefeng.property.po.workbench.NoticePo;
import com.liefeng.property.repository.mybatis.NoticeQueryRepository;
import com.liefeng.property.repository.workbench.NoticeRepository;
import com.liefeng.property.vo.workbench.NoticeVo;


/**
 * 通知领域上下问对象
 * @author xhw
 * @date 2016年2月26日下午3:33:11
 */
@Service
@Scope("prototype")
public class NoticeContext {
	
	private static Logger logger = LoggerFactory.getLogger(NoticeContext.class);
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private NoticeQueryRepository noticeQueryRepository;
	
	/**
	 * 客户值对象
	 */
	private NoticeVo noticeVo;
	
	/**
	 * 通知Id
	 */
	private String id;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static NoticeContext getInstance() {
		return SpringBeanUtil.getBean(NoticeContext.class);
	}
	
	/**
	 * 根据通知值对象构建上下文
	 * @param noticeVo
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午3:39:58
	 */
	public static NoticeContext build(NoticeVo noticeVo) {
		NoticeContext noticeContext = getInstance();
		noticeContext.setNoticeVo(noticeVo);
		
		return noticeContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 任务上下文
	 */
	public static NoticeContext build() {
		NoticeContext noticeContext = getInstance();
		
		return noticeContext;
	}
	
	/**
	 * 根据通知Id加载任务上下文
	 * @param id
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午3:42:23
	 */
	public static NoticeContext loadById(String id){
		NoticeContext noticeContext = getInstance();
		noticeContext.setId(id);
		
		return noticeContext;
	}
	
	/**
	 * 通过通知id，获取通知
	 * @param id
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午3:58:42
	 */
	public NoticeVo getById(){
		if (noticeVo == null) {
			if (ValidateHelper.isNotEmptyString(id)) {
				NoticePo noticePo = noticeRepository.findOne(id);

				noticeVo = MyBeanUtil.createBean(noticePo, NoticeVo.class);
			}
		}
		return noticeVo;
	}
	
	/**
	 * 创建通知对象
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午4:35:45
	 */
	public NoticeVo create(){
		if (noticeVo != null) {
			noticeVo.setId(UUIDGenerator.generate());
			noticeVo.setOemCode(ContextManager.getInstance().getOemCode());
			noticeVo.setCreateTime(new Date());
			noticeVo.setStatus(WorkbenchConstants.NoticeStatus.CHECKING);

			NoticePo noticePo = MyBeanUtil.createBean(noticeVo, NoticePo.class);
			noticeRepository.save(noticePo);
				
		}

		return noticeVo;
	}

	/**
	 * 更新通知
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午4:43:09
	 */
	public NoticeVo update(){
		if(noticeVo != null && ValidateHelper.isNotEmptyString(noticeVo.getId())){
			NoticePo noticePo = noticeRepository.findOne(noticeVo.getId());
			
			MyBeanUtil.copyBeanNotNull2Bean(noticeVo, noticePo);
			noticeRepository.save(noticePo);
			
			logger.info("Update notice of id: {} success.", noticeVo.getId());
			
			noticeVo = MyBeanUtil.createBean(noticePo, NoticeVo.class);
		}
		
		return noticeVo;
	}

	/**
	 * 根据状态，员工id，管理的项目，获取通知总数
	 * @param status
	 * @param staffId
	 * @param manageProject
	 * @return                      
	 * @author xhw
	 * @date 2016年2月29日 上午11:23:02
	 */
	public Long queryByCount(String status, String staffId, String manageProject) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("staffId", staffId);
		paramMap.put("status", status);
		paramMap.put("manageProject", manageProject);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);

		Long count = noticeQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("通知总数量：count=" + count);

		return count;
	}

	/**
	 * 根据状态，员工id，所管理项目，部门id查询通知
	 * @param status 状态
	 * @param staffId 员工id
	 * @param manageProject 管理的项目
	 * @param orderBy 排序
	 * @param page
	 * @param size
	 * @return                      
	 * @author xhw
	 * @date 2016年2月29日 下午2:27:11
	 */
	public DataPageValue<NoticeVo> findByPage(String status, String staffId, String manageProject, String orderBy, Integer page, Integer size) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("staffId", staffId);
		paramMap.put("status", status);
		paramMap.put("manageProject", manageProject);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);
		param.setPage(page);
		param.setPageSize(size);
		
		param.setSort(orderBy);

		Long count = noticeQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("通知总数量：count=" + count);

		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		List<NoticeVo> list = noticeQueryRepository.queryByPage(param);
		DataPageValue<NoticeVo> returnPage = new DataPageValue<NoticeVo>(list, count, size, page);

		return returnPage;
	}
	
	/**
	 * 根据员工id，管理的项目，部门id，获取已发布通知总数
	 * @param staffId
	 * @param manageProject
	 * @param deptId
	 * @return                      
	 * @author xhw
	 * @date 2016年2月29日 上午11:23:02
	 */
	public Long queryByCountOfPublished(String staffId, String manageProject, String deptId) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("staffId", staffId);
		paramMap.put("manageProject", manageProject);
		paramMap.put("deptId", deptId);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);

		Long count = noticeQueryRepository.queryPublishedByCount(param);
		count = (count == null ? 0 : count);
		logger.info("通知总数量：count=" + count);

		return count;
	}

	/**
	 * 根据员工id，管理的项目，部门id，获取已发布通知（分页）
	 * @param staffId 员工id
	 * @param manageProject 管理的项目
	 * @param deptId 部门id
	 * @param page
	 * @param size
	 * @return                      
	 * @author xhw
	 * @date 2016年2月29日 下午2:31:22
	 */
	public DataPageValue<NoticeVo> findByPageOfPublished(String staffId, String manageProject, String deptId, Integer page, Integer size) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("staffId", staffId);
		paramMap.put("manageProject", manageProject);
		paramMap.put("deptId", deptId);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);
		param.setPage(page);
		param.setPageSize(size);

		Long count = noticeQueryRepository.queryPublishedByCount(param);
		count = (count == null ? 0 : count);
		logger.info("通知总数量：count=" + count);

		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		List<NoticeVo> list = noticeQueryRepository.queryPublishedByPage(param);
		DataPageValue<NoticeVo> returnPage = new DataPageValue<NoticeVo>(list, count, size, page);

		return returnPage;
	}
	
	/**
	 * 根据状态，查相应的通知
	 * @param status 状态
	 * @return                      
	 * @author xhw
	 * @date 2016年3月1日 下午2:33:29
	 */
	public List<NoticeVo> findByStatus(String status){
		List<NoticeVo> noticeVos = null;
		if(ValidateHelper.isNotEmptyString(status)){
			List<NoticePo> noticePos = noticeRepository.findByStatus(status);
			
			noticeVos = MyBeanUtil.createList(noticePos, NoticeVo.class);
		}
		return noticeVos;
	}
	protected void setNoticeVo(NoticeVo noticeVo) {
		this.noticeVo = noticeVo;
	}

	/**
	 * 查看已发布通知（分页、app）
	 * @param terminal 接收端类型
	 * @param naticeType 通知类型
	 * @param projectId 项目id（员工：所管理的项目id字符串，业主：所在项目id）
	 * @param groupId （员工：部门id，业主：楼栋id）
	 * @param privilegeType 接收人类型（员工：1，业主：2）
	 * @param page
	 * @param size
	 * @return
	 * @author xhw
	 * @2016年3月7日 下午3:24:55
	 */
	public DataPageValue<NoticeVo> findOfPublished(String terminal, String noticeType, String projectId, String groupId, String privilegeType, Integer page, Integer size) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("terminal", terminal);
		paramMap.put("noticeType", noticeType);
		paramMap.put("projectId", projectId);
		paramMap.put("groupId", groupId);
		paramMap.put("privilegeType", privilegeType);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);
		param.setPage(page);
		param.setPageSize(size);

		Long count = noticeQueryRepository.queryCountOfPublished(param);
		count = (count == null ? 0 : count);
		logger.info("通知总数量：count=" + count);

		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		List<NoticeVo> list = noticeQueryRepository.queryOfPublished(param);
		DataPageValue<NoticeVo> returnPage = new DataPageValue<NoticeVo>(list, count, size, page);

		return returnPage;
	}
	protected void setId(String id) {
		this.id = id;
	}
}
