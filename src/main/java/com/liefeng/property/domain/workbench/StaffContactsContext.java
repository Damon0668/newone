package com.liefeng.property.domain.workbench;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.Po2VoConverter;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.po.workbench.WebsiteMsgPo;
import com.liefeng.property.repository.mybatis.StaffContactsQueryRepository;
import com.liefeng.property.vo.workbench.StaffContactsVo;
import com.liefeng.property.vo.workbench.WebsiteMsgVo;


/**
 * 员工通讯录上下文对象
 * @author xhw
 * @2016年3月4日 下午4:24:53
 */
@Service
@Scope("prototype")
public class StaffContactsContext {
	
	private static Logger logger = LoggerFactory.getLogger(StaffContactsContext.class);
	
	@Autowired
	private StaffContactsQueryRepository staffContactsQueryRepository;
	
	
	/**
	 * 部门id
	 */
	private String departmentId;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static StaffContactsContext getInstance() {
		return SpringBeanUtil.getBean(StaffContactsContext.class);
	}
	
	
	/**
	 * 构建上下文（无参）
	 * @return 员工通讯录上下文
	 */
	public static StaffContactsContext build() {
		StaffContactsContext staffContactsContext = getInstance();
		
		return staffContactsContext;
	}
	
	/**
	 * 根据部门id，构建员工通讯录上下文
	 * @param departmentId
	 * @return
	 * @author xhw
	 * @2016年3月4日 下午4:29:39
	 */
	public static StaffContactsContext loadByDepartmentId(String departmentId){
		StaffContactsContext staffContactsContext = getInstance();
		staffContactsContext.setDepartmentId(departmentId);
		
		return staffContactsContext;
	}
	

	/**
	 * 员工通讯录的总数
	 * @param departmentId 部门id
	 * @param status 状态
	 * @param workStatus 在职状态
	 * @return
	 * @author xhw
	 * @2016年3月4日 下午4:37:16
	 */
	public Long findCount(String departmentId, String status, String workStatus) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("departmentId", departmentId);
		paramMap.put("status", status);
		paramMap.put("workStatus", workStatus);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);

		Long count = staffContactsQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("员工通讯录总数量：count=" + count);

		return count;
	}

	/**
	 * 查询员工通讯录
	 * @param departmentId 部门id
	 * @param status 状态
	 * @param workStatus 在职状态
	 * @param page
	 * @param size
	 * @return
	 * @author xhw
	 * @2016年3月4日 下午4:33:30
	 */
	public DataPageValue<StaffContactsVo> findByPage(String departmentId, String status, String workStatus, Integer page, Integer size) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("departmentId", departmentId);
		paramMap.put("status", status);
		paramMap.put("workStatus", workStatus);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);
		param.setPage(page);
		param.setPageSize(size);

		Long count = staffContactsQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("员工通讯录总数量：count=" + count);

		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		List<StaffContactsVo> list = staffContactsQueryRepository.queryByPage(param);
		DataPageValue<StaffContactsVo> returnPage = new DataPageValue<StaffContactsVo>(list, count, size, page);

		return returnPage;
	}
	


	protected void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	


}
