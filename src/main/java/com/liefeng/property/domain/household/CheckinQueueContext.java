package com.liefeng.property.domain.household;

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
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.household.CheckinQueueBo;
import com.liefeng.property.constant.HouseholdConstants;
import com.liefeng.property.po.household.CheckinQueuePo;
import com.liefeng.property.repository.CheckinQueueRepository;
import com.liefeng.property.repository.mybatis.CheckinQueueQueryRepository;
import com.liefeng.property.vo.household.CheckinQueueVo;

/**
 * 入住排队上下文
 * 
 * @author ZhenTingJun
 * @date 2016年3月6日
 */
@Service
@Scope("prototype")
public class CheckinQueueContext {

	private static Logger logger = LoggerFactory.getLogger(CheckinQueueContext.class);
	
	@Autowired
	private CheckinQueueRepository checkinQueueRepository;
	
	@Autowired
	private CheckinQueueQueryRepository checkinQueueQueryRepository;

	/**
	 * 入住排队ID
	 */
	private String checkinQueueId;;
	
	/**
	 * 入住排队值对象
	 */
	private CheckinQueueVo checkinQueue;
	
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static CheckinQueueContext getInstance(){
		return SpringBeanUtil.getBean(CheckinQueueContext.class);
	}
	
	/**
	 * 根据入住排队值对象构建上下文
	 * @param checkinQueue 入住排队值对象
	 * @return 入住排队上下文
	 */
	public static CheckinQueueContext build(CheckinQueueVo checkinQueue) {
		CheckinQueueContext checkinQueueContext = getInstance();
		checkinQueueContext.setCheckinQueue(checkinQueue);
		
		return checkinQueueContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 入住排队上下文
	 */
	public static CheckinQueueContext build() {
		CheckinQueueContext checkinQueueContext = getInstance();
		
		return checkinQueueContext;
	}
	
	/**
	 * 根据入住排队ID加载上下文
	 * @param checkinQueueId 入住排队ID
	 * @return 入住排队上下文
	 */
	public static CheckinQueueContext loadById(String checkinQueueId) {
		CheckinQueueContext checkinQueueContext = getInstance();
		checkinQueueContext.setCheckinQueueId(checkinQueueId);
		
		return checkinQueueContext;
	}
	
	
	/**
	 * 查询入住排队信息
	 */
	public CheckinQueueVo get() {
		if(checkinQueue == null) {
			CheckinQueuePo checkinQueuePo = null;
			if(ValidateHelper.isNotEmptyString(checkinQueueId)) {
				checkinQueuePo = checkinQueueRepository.findOne(checkinQueueId);
			}
			
			if(checkinQueuePo != null) {
				checkinQueue = MyBeanUtil.createBean(checkinQueuePo, CheckinQueueVo.class);
			}
		}
		
		return checkinQueue;
	}
	
	/**
	 * 保存入住排队信息
	 */
	public void create() {
		if(checkinQueue != null) {
			checkinQueue.setId(UUIDGenerator.generate());
			checkinQueue.setOemCode(ContextManager.getInstance().getOemCode());
			
			CheckinQueuePo checkinQueuePo = MyBeanUtil.createBean(checkinQueue, CheckinQueuePo.class);
			checkinQueueRepository.save(checkinQueuePo);
			logger.info("保存入住排队信息成功，checkinQueue={}",checkinQueue);
		}
	}
	
	/**
	 * 更新入住排队信息
	 */
	public void update() {
		if(checkinQueue != null) {
			
			if(!ValidateHelper.isNotEmptyString(checkinQueue.getId())) {
				logger.error("更新入住排队信息失败，checkinQueue.getId()为空！");
			}
			
			CheckinQueuePo checkinQueuePo = checkinQueueRepository.findOne(checkinQueue.getId());
			MyBeanUtil.copyBeanNotNull2Bean(checkinQueue, checkinQueuePo);
			
			// 完成办理时更新完成时间字段
			if(checkinQueuePo.getStatus().equals(HouseholdConstants.CheckinQueueStatus.FINISHED)) {
				checkinQueuePo.setTranTime(new Date());
			}
			
			checkinQueueRepository.save(checkinQueuePo);
			logger.info("更新入住排队信息成功，checkinQueueId={}",checkinQueue.getId());
		} else {
			
			logger.error("更新入住排队信息失败，checkinQueue 对象为空！");
		}
	}
	
	/**
	 * 删除入住排队信息
	 */
	public void delete() {
		if(ValidateHelper.isNotEmptyString(checkinQueueId)) {
			checkinQueueRepository.delete(checkinQueueId);
		} 
	}
	
	/**
	 * 分页查询入住排队信息
	 * @param params 查询过滤参数
	 * @param pageSize 分页大小
	 * @param cuerrentPage 分页当前页 
	 * @return 入住排队列表
	 */
	public DataPageValue<CheckinQueueVo> getCheckinQueues(CheckinQueueBo params,  Integer pageSize, Integer currentPage) {
		// 参数拷贝
		Map<String, String> extra = MyBeanUtil.bean2Map(params);
		
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(currentPage);
		param.setPageSize(pageSize);
		
		Long count = checkinQueueQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		List<CheckinQueueVo> list = checkinQueueQueryRepository.queryByPage(param);
		DataPageValue<CheckinQueueVo> returnPage = new DataPageValue<CheckinQueueVo>(list, count, pageSize, currentPage);
		
		return returnPage;
	}

	protected void setCheckinQueueId(String checkinQueueId) {
		this.checkinQueueId = checkinQueueId;
	}

	protected void setCheckinQueue(CheckinQueueVo checkinQueue) {
		this.checkinQueue = checkinQueue;
	}
	
}