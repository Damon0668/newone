package com.liefeng.property.domain.household;

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
import com.liefeng.property.po.household.ProprietorPo;
import com.liefeng.property.po.household.VisitorPo;
import com.liefeng.property.repository.household.VisitorRepository;
import com.liefeng.property.repository.mybatis.VisitorQueryRepository;
import com.liefeng.property.vo.household.ProprietorVo;
import com.liefeng.property.vo.household.VisitorVo;

/**
 * 访客信息领域模型
 * 
 * @author ZhenTingJun
 * @author 蔡少东
 * @author xhw
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class VisitorContext {
	
	private static Logger logger = LoggerFactory.getLogger(VisitorContext.class);
	
	@Autowired
	private VisitorRepository visitorRepository;
	
	@Autowired
	private VisitorQueryRepository visitorQueryRepository;
	
	/**
	 * 访客信息ID
	 */
	private String visitorId;
	
	/**
	 * 访客信息值对象
	 */
	private VisitorVo visitor;
	
	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	private static VisitorContext getInstance() {
		return SpringBeanUtil.getBean(VisitorContext.class);
	}
	
	/**
	 * 根据访客信息值对象构建上下文
	 * @param visitor 访客信息值对象
	 * @return 访客信息上下文
	 */
	public static VisitorContext build(VisitorVo visitor) {
		VisitorContext visitorContext = getInstance();
		visitorContext.visitor = visitor;
		
		return visitorContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 访客信息上下文
	 */
	public static VisitorContext build() {
		VisitorContext visitorContext = getInstance();
		
		return visitorContext;
	}
	
	/**
	 * 根据访客信息ID加载上下文
	 * @param visitorId 访客信息ID
	 * @return 访客信息上下文
	 */
	public static VisitorContext loadById(String visitorId) {
		VisitorContext visitorContext = getInstance();
		visitorContext.visitorId = visitorId;
		
		return visitorContext;
	}
	
	/**
	 * 查询访客信息
	 * @return 访客信息值对象
	 */
	public VisitorVo getVisitor() {
		if(visitor == null) {
			VisitorPo visitorPo = null;
			if(ValidateHelper.isNotEmptyString(visitorId)) {
				visitorPo = visitorRepository.findOne(visitorId);
			}
			
			if(visitorPo != null) {
				visitor = MyBeanUtil.createBean(visitorPo, VisitorVo.class);
			}
		}
		
		return visitor;
	}
	
	/**
	 * 添加访客
	 * @return 
	 * @author xhw
	 * @date 2016年3月23日 上午11:44:36
	 */
	public VisitorVo create() {
		if(visitor != null) {
			visitor.setId(UUIDGenerator.generate());
			visitor.setOemCode(ContextManager.getInstance().getOemCode());
			visitor.setInTime(new Date());
			VisitorPo visitorPo = MyBeanUtil.createBean(visitor, VisitorPo.class);
			
			logger.info("create visitor = {}", visitor);
			visitorRepository.save(visitorPo);
		}
		return visitor;
	}
	
	/**
	 * 获取用户的访客列表
	 * @param userId
	 * @return 
	 * @author xhw
	 * @date 2016年3月23日 下午2:50:29
	 */
	public List<VisitorVo> getVisitorList(String userId){
		List<VisitorVo> visitorVoList = null;
		if(ValidateHelper.isNotEmptyString(userId)){
			List<VisitorPo> visitorPoList = visitorRepository.getVisitorPOList(userId);
			
			visitorVoList = MyBeanUtil.createList(visitorPoList, VisitorVo.class);
		}
		return visitorVoList;
	}
	
	/**
	 * 获取访客的访问记录
	 * @param phone 手机号码
	 * @return 
	 * @author xhw
	 * @date 2016年3月23日 下午3:22:36
	 */
	public List<VisitorVo> getVisitor(String phone){
		List<VisitorVo> visitorVoList = null;
		if(ValidateHelper.isNotEmptyString(phone)){
			List<VisitorPo> visitorPoList = visitorRepository.findByPhoneOrderByInTimeDesc(phone);
			
			visitorVoList = MyBeanUtil.createList(visitorPoList, VisitorVo.class);
		}
		return visitorVoList;
	}
	
	/**
	 * 分页查询
	 * @param projectId
	 * @param name
	 * @param phone
	 * @param page
	 * @param size
	 * @return 
	 * @author xhw
	 * @date 2016年4月14日 下午3:05:01
	 */
	public DataPageValue<VisitorVo> findByPage(String projectId, String manageProjectIds, String name, String phone, Integer page, Integer size) {
		String oemCode = ContextManager.getInstance().getOemCode();
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("projectId", projectId);
		paramMap.put("name", name);
		paramMap.put("phone", phone);
		paramMap.put("manageProjectIds", manageProjectIds);
		paramMap.put("oemCode", oemCode);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);
		param.setPage(page);
		param.setPageSize(size);

		Long count = visitorQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);

		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		List<VisitorVo> list = visitorQueryRepository.queryByPage(param);
		DataPageValue<VisitorVo> returnPage = new DataPageValue<VisitorVo>(list, count, size, page);

		return returnPage;
	}

	/**
	 * 更新访客信息
	 */
	public VisitorVo update() {
		if(visitor != null && ValidateHelper.isNotEmptyString(visitor.getId())) {
			logger.info("更新访客信息，访客ID = {}", visitor.getId());
			VisitorPo visitorPo = visitorRepository.findOne(visitor.getId());
			
			if(visitorPo != null) {
				MyBeanUtil.copyBeanNotNull2Bean(visitor, visitorPo);
				visitorRepository.save(visitorPo);
				logger.info("更新访客信息成功，访客ID = {}", visitor.getId());
				
				visitor = MyBeanUtil.createBean(visitorPo, VisitorVo.class);
			}
		}
		
		return visitor;
	}
	
	protected void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	protected void setVisitor(VisitorVo visitor) {
		this.visitor = visitor;
	}
}
