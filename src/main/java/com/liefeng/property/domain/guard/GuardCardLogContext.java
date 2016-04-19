package com.liefeng.property.domain.guard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.repository.guard.GuardCardLogRepository;
import com.liefeng.property.repository.mybatis.GuardCardLogQueryRepository;
import com.liefeng.property.vo.guard.GuardCardLogVo;

/**
 * 磁卡操作日志领域模型
 * @author Huangama
 * @date 2016-2-25
 */
@Service
@Scope("prototype")
public class GuardCardLogContext {
	
	private static Logger logger = LoggerFactory.getLogger(GuardCardLogContext.class);

	@Autowired
	private GuardCardLogRepository guardCardLogRepository;
	
	@Autowired
	private GuardCardLogQueryRepository guardCardLogQueryRepository ;
	
	/**
	 * 磁卡ID
	 */
	private String cardId;
	
	/**
	 * 磁卡操作日志对象
	 */
	private GuardCardLogVo guardCardLog;
	
	private static GuardCardLogContext getInstance() {
		GuardCardLogContext guardCardLogContext =  SpringBeanUtil.getBean(GuardCardLogContext.class);
		return guardCardLogContext;
	}
	
	public static GuardCardLogContext build(GuardCardLogVo guardCardLog) {
		GuardCardLogContext guardCardLogContext = getInstance();
		guardCardLogContext.setGuardCardLog(guardCardLog);
		return guardCardLogContext;
	}
	
	public static GuardCardLogContext build() {
		GuardCardLogContext guardCardLogContext = getInstance();
		return guardCardLogContext;
	}
	
	public static GuardCardLogContext build(String cardId) {
		GuardCardLogContext guardCardLogContext = getInstance();
		guardCardLogContext.setCardId(cardId);
		return guardCardLogContext;
	}
	
	/**
	 * 分页查询磁卡日志
	 * @param cardId 磁卡ID
	 * @param currentPage 分页当前页
	 * @param pageSize 分页大小
	 * @return 分页磁卡日志数据
	 */
	public DataPageValue<GuardCardLogVo> listGuardCardLog(String cardId, Integer currentPage, Integer pageSize) {
		// 参数拷贝
		Map<String, String> extra = new HashMap<String, String>();
		extra.put("cardId", cardId);
		extra.put("oemCode", ContextManager.getInstance().getOemCode());
		
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(currentPage);
		param.setPageSize(pageSize);
		
		Long count = guardCardLogQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		
		List<GuardCardLogVo> list = guardCardLogQueryRepository.queryByPage(param);

		DataPageValue<GuardCardLogVo> returnPage = new DataPageValue<GuardCardLogVo>(
				list, count, pageSize, currentPage);
		
		return returnPage;
	}
	
	protected void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	protected void setGuardCardLog(GuardCardLogVo guardCardLog) {
		this.guardCardLog = guardCardLog;
	}
	
}
