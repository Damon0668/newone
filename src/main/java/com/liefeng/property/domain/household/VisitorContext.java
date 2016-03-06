package com.liefeng.property.domain.household;

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
import com.liefeng.property.po.household.VisitorPo;
import com.liefeng.property.repository.VisitorRepository;
import com.liefeng.property.vo.household.VisitorVo;

/**
 * 访客信息领域模型
 * 
 * @author ZhenTingJun
 * @author 蔡少东
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class VisitorContext {
	
	private static Logger logger = LoggerFactory.getLogger(VisitorContext.class);
	
	@Autowired
	private VisitorRepository visitorRepository;
	
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
	 * 保存访客信息
	 */
	public void create() {
		if(visitor != null) {
			visitor.setId(UUIDGenerator.generate());
			visitor.setOemCode(ContextManager.getInstance().getOemCode());
			VisitorPo visitorPo = MyBeanUtil.createBean(visitor, VisitorPo.class);
			
			logger.info("create visitor = {}", visitor);
			visitorRepository.save(visitorPo);
		}
	}
	
}
