package com.liefeng.property.domain.household;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.household.MovedOutResidentBo;
import com.liefeng.property.po.household.ResidentHistoryPo;
import com.liefeng.property.po.project.AppHomeImagePo;
import com.liefeng.property.repository.household.ResidentHistoryRepository;
import com.liefeng.property.repository.mybatis.ResidentHistoryQueryRepository;
import com.liefeng.property.vo.fee.MeterRecordVo;
import com.liefeng.property.vo.household.ResidentHistoryVo;
import com.liefeng.property.vo.project.AppHomeImageVo;

/**
 * 住户历史信息领域模型
 * 
 * @author wuzhijing
 * @date 2016-04-25
 */
@Service
@Scope("prototype")
public class ResidentHistoryContext {
	
	private static Logger logger = LoggerFactory.getLogger(ResidentHistoryContext.class);
	
	@Autowired
	private ResidentHistoryRepository residentHistoryRepository;
	
	@Autowired
	private ResidentHistoryQueryRepository residentHistoryQueryRepository;
	
	/**
	 * 住户历史信息ID
	 */
	private String id;
	
	/**
	 * 住户历史信息值对象
	 */
	private ResidentHistoryVo resideHistory;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static ResidentHistoryContext getInstance() {
		return SpringBeanUtil.getBean(ResidentHistoryContext.class);
	}
	
	/**
	 * 根据住户信息值对象构建上下文
	 * @param resident 住户信息值对象
	 * @return 住户信息上下文
	 */
	public static ResidentHistoryContext build(ResidentHistoryVo resideHistory) {
		 ResidentHistoryContext residentHistoryContext = getInstance();
		 residentHistoryContext.setResideHistory(resideHistory);
		 
		 return residentHistoryContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 住户信息上下文
	 */
	public static ResidentHistoryContext build() {
		 ResidentHistoryContext residentContext = getInstance();
		 
		 return residentContext;
	}
	
	/**
	 * 根据住户信息ID加载上下文
	 * @param residentId 住户信息ID
	 * @return 住户信息上下文
	 */
	public static ResidentHistoryContext loadById(String id) {
		ResidentHistoryContext residentContext = getInstance();
		 residentContext.setId(id);
		 
		 return residentContext;
	}
	
	public void update() {
		residentHistoryRepository.save(MyBeanUtil.createBean(resideHistory,ResidentHistoryPo.class));
	}
	
	public void create() {
		resideHistory.setId(UUIDGenerator.generate());
		resideHistory.setCreateTime(new Date());
		residentHistoryRepository.save(MyBeanUtil.createBean(resideHistory,ResidentHistoryPo.class));
	}
	
	public ResidentHistoryVo get() {
		ResidentHistoryPo residentHistoryPo = residentHistoryRepository.findOne(id);
		return MyBeanUtil.createBean(residentHistoryPo, ResidentHistoryVo.class);
	}
	
	public DataPageValue<ResidentHistoryVo> list(MovedOutResidentBo movedOutResidentBo,Integer currentPage, Integer pageSize){
		Map<String, String> extra = MyBeanUtil.bean2Map(movedOutResidentBo);
		
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(currentPage);
		param.setPageSize(pageSize);
		
		Long total = residentHistoryQueryRepository.queryByCount(param);
		total = (total == null ? 0 : total);
		logger.info("总数量：total=" + total);
	
		param.getPager().setRowCount(total);
		
		List<ResidentHistoryVo> residentHistoryVos = residentHistoryQueryRepository.queryByPage(param);
		return new DataPageValue<ResidentHistoryVo>(residentHistoryVos, total, pageSize, currentPage);
	}
	
	public void delete() {
		residentHistoryRepository.delete(id);
	}
	
	protected void setId(String id) {
		this.id = id;
	}

	protected void setResideHistory(ResidentHistoryVo resideHistory) {
		this.resideHistory = resideHistory;
	}
	
	
}