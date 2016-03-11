package com.liefeng.property.domain.workbench;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.repository.mybatis.ProprietorContactsQueryRepository;
import com.liefeng.property.vo.workbench.ProprietorContactsVo;


/**
 * 业主通讯录上下文对象
 * @author xhw
 * @2016年3月4日 下午4:24:53
 */
@Service
@Scope("prototype")
public class ProprietorContactsContext {
	
	private static Logger logger = LoggerFactory.getLogger(ProprietorContactsContext.class);
	
	@Autowired
	private ProprietorContactsQueryRepository proprietorContactsQueryRepository;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static ProprietorContactsContext getInstance() {
		return SpringBeanUtil.getBean(ProprietorContactsContext.class);
	}
	
	
	/**
	 * 构建上下文（无参）
	 * @return 业主通讯录上下文
	 */
	public static ProprietorContactsContext build() {
		ProprietorContactsContext proprietorContactsContext = getInstance();
		
		return proprietorContactsContext;
	}
	

	/**
	 * 业主通讯录总数
	 * @param projectId 项目id
	 * @param buildingId 楼栋id
	 * @param status 状态
	 * @return
	 * @author xhw
	 * @2016年3月4日 下午5:09:58
	 */
	public Long findCount(String projectId, String buildingId, String status) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("projectId", projectId);
		paramMap.put("status", status);
		paramMap.put("buildingId", buildingId);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);

		Long count = proprietorContactsQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("业主通讯录总数量：count=" + count);

		return count;
	}

	/**
	 * 查询业主通讯录（分页）
	 * @param projectId 项目id
	 * @param buildingId 楼栋id
	 * @param status 状态
	 * @param page
	 * @param size
	 * @return
	 * @author xhw
	 * @2016年3月4日 下午5:12:23
	 */
	public DataPageValue<ProprietorContactsVo> findByPage(String projectId, String buildingId, String status, Integer page, Integer size) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("projectId", projectId);
		paramMap.put("status", status);
		paramMap.put("buildingId", buildingId);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);
		param.setPage(page);
		param.setPageSize(size);

		Long count = proprietorContactsQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("业主通讯录总数量：count=" + count);

		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		List<ProprietorContactsVo> list = proprietorContactsQueryRepository.queryByPage(param);
		DataPageValue<ProprietorContactsVo> returnPage = new DataPageValue<ProprietorContactsVo>(list, count, size, page);

		return returnPage;
	}


}
