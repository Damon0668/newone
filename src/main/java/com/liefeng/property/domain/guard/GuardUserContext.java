package com.liefeng.property.domain.guard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.StringUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.guard.GuardPRUserBo;
import com.liefeng.property.repository.mybatis.GuardUserQueryRepository;
import com.liefeng.property.vo.guard.GuardPRUserVo;

/**
 * 出入管理
 * 使用用户领域模型
 * @author 蔡少东
 * @date 2016年4月26日
 */
@Service
@Scope("prototype")
public class GuardUserContext {
	
	private static Logger logger = LoggerFactory.getLogger(GuardUserContext.class);
	
	@Autowired
	private GuardUserQueryRepository guardUserQueryRepository;
	
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static GuardUserContext getInstance() {
		return SpringBeanUtil.getBean(GuardUserContext.class);
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 住户信息上下文
	 */
	public static GuardUserContext build() {
		GuardUserContext guardUserContext = getInstance();
		return guardUserContext;
	}
	
	/**
	 * 门禁模块
	 * 查询业主住户信息
	 * 
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public DataPageValue<GuardPRUserVo> listGuardPRUser4Page(GuardPRUserBo guardPRUserBo, Integer currentPage, Integer pageSize){
		
		guardPRUserBo.setOemCode(ContextManager.getInstance().getOemCode());
		
		Map<String, String> extra = MyBeanUtil.bean2Map(guardPRUserBo);
		if(ValidateHelper.isNotEmptyCollection(guardPRUserBo.getProjectIds())){
			extra.put("projectIds", StringUtil.fmtToSqlInCondition(guardPRUserBo.getProjectIds()));
		}
		PagingParamVo pagingParam = new PagingParamVo();
		pagingParam.setExtra(extra);
		pagingParam.setRows(pageSize);
		pagingParam.setPage(currentPage);
		
		Long count = guardUserQueryRepository.queryGuardPRUserByCount(pagingParam);
		count = (count == null ? 0 : count);
		
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		pagingParam.getPager().setRowCount(count);
		
		List<GuardPRUserVo> guardPRUserList = guardUserQueryRepository.queryGuardPRUser(pagingParam);
		
		guardPRUserList = (ValidateHelper.isEmptyCollection(guardPRUserList) ? 
				new ArrayList<GuardPRUserVo>() : guardPRUserList);

		DataPageValue<GuardPRUserVo> guardResidentPage = new DataPageValue<GuardPRUserVo>(
				guardPRUserList, count, pageSize, currentPage);
		
		return guardResidentPage;
	}
	
}
