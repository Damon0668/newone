package com.liefeng.property.domain.guard;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.guard.GuardOpenLogBo;
import com.liefeng.property.po.guard.GuardOpenLogPo;
import com.liefeng.property.repository.guard.GuardOpenLogRepository;
import com.liefeng.property.repository.mybatis.GuardOpenLogQueryRepository;
import com.liefeng.property.util.DictionaryUtil;
import com.liefeng.property.vo.guard.GuardOpenLogVo;

/**
 * 开门日志领域模型
 * @author Huangama
 * @author ZhenTingJun
 * @date 2016-2-29
 */
@Service
@Scope("prototype")
public class GuardOpenLogContext {
	
	private static Logger logger = LoggerFactory.getLogger(GuardOpenLogContext.class);

	@Autowired
	private GuardOpenLogRepository guardOpenLogRepository;
	
	@Autowired
	private GuardOpenLogQueryRepository guardOpenLogQueryRepository;
	
	/**
	 * 开门日志对象
	 */
	private GuardOpenLogVo guardOpenLog;
	
	private static GuardOpenLogContext getInstance() {
		GuardOpenLogContext guardOpenLogContext =  SpringBeanUtil.getBean(GuardOpenLogContext.class);
		return guardOpenLogContext;
	}
	
	public static GuardOpenLogContext build(GuardOpenLogVo guardOpenLog) {
		GuardOpenLogContext guardOpenLogContext = getInstance();
		guardOpenLogContext.setGuardOpenLog(guardOpenLog);
		return guardOpenLogContext;
	}
	
	public static GuardOpenLogContext build() {
		GuardOpenLogContext guardOpenLogContext = getInstance();
		return guardOpenLogContext;
	}
	
	public GuardOpenLogVo create() {
		if(guardOpenLog != null) {
			guardOpenLog.setId(UUIDGenerator.generate());
			guardOpenLog.setCreateTime(new Date());
			guardOpenLog.setOemCode(ContextManager.getInstance().getOemCode());
			
			GuardOpenLogPo guardOpenLogPo = MyBeanUtil.createBean(guardOpenLog, GuardOpenLogPo.class);
			guardOpenLogRepository.save(guardOpenLogPo);
		}
		
		return guardOpenLog;
	}
	
	/**
	 * 分页查询开门日志
	 * @param params 查询参数
	 * @param currentPage 分页当前页
	 * @param pageSize 分页大小
	 * @author ZhenTingJun
	 * @date 2016年4月28日
	 */
	public DataPageValue<GuardOpenLogVo> listGuardOpenLog(GuardOpenLogBo params, Integer currentPage, Integer pageSize) {
		logger.info("listGuardOpenLog ==> params = {}, currentPage = {}, pageSize = {}", params, currentPage, pageSize);
		
		// 参数拷贝
		Map<String, String> extra = MyBeanUtil.bean2Map(params);
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(currentPage);
		param.setPageSize(pageSize);
		
		Long count = guardOpenLogQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		
		List<GuardOpenLogVo> list = guardOpenLogQueryRepository.queryByPage(param);
		list = DictionaryUtil.transformDicValueToDicName(list); // 字典【值-名】转换

		DataPageValue<GuardOpenLogVo> returnPage = new DataPageValue<GuardOpenLogVo>(
				list, count, pageSize, currentPage);
		
		return returnPage;
	}
	
	protected void setGuardOpenLog(GuardOpenLogVo guardOpenLog) {
		this.guardOpenLog = guardOpenLog;
	}
	
}
