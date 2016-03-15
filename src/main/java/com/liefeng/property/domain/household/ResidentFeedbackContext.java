package com.liefeng.property.domain.household;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.base.vo.UserVo;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.household.ResidentFeedbackBo;
import com.liefeng.property.po.household.ResidentFeedbackPo;
import com.liefeng.property.repository.household.ResidentFeedbackRepository;
import com.liefeng.property.repository.mybatis.ResidentFeedbackQueryRepository;
import com.liefeng.property.vo.household.ResidentFeedbackVo;


/**
 * 用户反馈领域上下文对象
 * @author xhw
 * @date 2016年3月14日 上午10:03:17
 */
@Service
@Scope("prototype")
public class ResidentFeedbackContext {
	
	private static Logger logger = LoggerFactory.getLogger(ResidentFeedbackContext.class);
	
	@Autowired
	private ResidentFeedbackRepository residentFeedbackRepository;
	
	@Autowired
	private ResidentFeedbackQueryRepository residentFeedbackQueryRepository;
	
	/**
	 * 客户值对象
	 */
	private ResidentFeedbackVo residentFeedbackVo;
	
	/**
	 * 反馈Id
	 */
	private String id;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static ResidentFeedbackContext getInstance() {
		return SpringBeanUtil.getBean(ResidentFeedbackContext.class);
	}
	
	/**
	 * 根据反馈对象，构建上下文对象
	 * @param residentFeedbackVo
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 上午10:12:09
	 */
	public static ResidentFeedbackContext build(ResidentFeedbackVo residentFeedbackVo) {
		ResidentFeedbackContext residentFeedbackContext= getInstance();
		residentFeedbackContext.setResidentFeedbackVo(residentFeedbackVo);
		
		return residentFeedbackContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 反馈上下文
	 */
	public static ResidentFeedbackContext build() {
		ResidentFeedbackContext residentFeedbackContext = getInstance();
		
		return residentFeedbackContext;
	}
	
	/**
	 * 根据Id加载反馈上下文
	 * @param id
	 * @return 
	 * @author xhw
	 * @date 2016年3月15日 下午1:42:45
	 */
	public static ResidentFeedbackContext loadById(String id){
		ResidentFeedbackContext residentFeedbackContext = getInstance();
		residentFeedbackContext.setId(id);
		
		return residentFeedbackContext;
	}
	
	
	/**
	 * 创建反馈
	 * @return 
	 * @author xhw
	 * @date 2016年3月15日 下午1:42:58
	 */
	public ResidentFeedbackVo create() {
		if (residentFeedbackVo != null) {
			residentFeedbackVo.setId(UUIDGenerator.generate());
			residentFeedbackVo.setOemCode(ContextManager.getInstance().getOemCode());
			residentFeedbackVo.setCreateTime(new Date());

			ResidentFeedbackPo residentFeedbackPo = MyBeanUtil.createBean(residentFeedbackVo, ResidentFeedbackPo.class);
			residentFeedbackRepository.save(residentFeedbackPo);

			logger.info("Create residentFeedback : {} success.", residentFeedbackPo);
		}

		return residentFeedbackVo;
	}
	
	/**
	 * 分页查询用户反馈
	 * @param params 查询过滤参数
	 * @param currentPage 当前页
	 * @param pageSize 分页大小
	 * @return 
	 * @author xhw
	 * @date 2016年3月15日 下午1:43:47
	 */
	public DataPageValue<ResidentFeedbackVo> getResidentFeedPage(ResidentFeedbackBo params, Integer currentPage, Integer pageSize) {
		logger.info("查询参数为{}，分页当前页为{}，分页大小为{}", params, currentPage, pageSize);
		// 查询参数拷贝
		Map<String, String> extra = MyBeanUtil.bean2Map(params);
		
		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(extra);
		pagingParamVo.setRows(pageSize);
		pagingParamVo.setPage(currentPage);

		Long count = residentFeedbackQueryRepository.queryResidentFeedbackCount(pagingParamVo);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		pagingParamVo.getPager().setRowCount(count);
		List<ResidentFeedbackVo> feedbackList = residentFeedbackQueryRepository.queryResidentFeedback(pagingParamVo);
		feedbackList = (ValidateHelper.isEmptyCollection(feedbackList) ? 
				new ArrayList<ResidentFeedbackVo>() : feedbackList);

		DataPageValue<ResidentFeedbackVo> feedbackPage = new DataPageValue<ResidentFeedbackVo>(
				feedbackList, count, pageSize, currentPage);
		
		return feedbackPage;
	}
	
	protected void setResidentFeedbackVo(ResidentFeedbackVo residentFeedbackVo) {
		this.residentFeedbackVo = residentFeedbackVo;
	}

	protected void setId(String id) {
		this.id = id;
	}
	
	
}
