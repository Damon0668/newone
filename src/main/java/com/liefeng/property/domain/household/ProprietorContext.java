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

import com.liefeng.common.util.EncryptionUtil;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.household.ProprietorBo;
import com.liefeng.property.po.household.ProprietorPo;
import com.liefeng.property.repository.ProprietorRepository;
import com.liefeng.property.repository.mybatis.ProprietorQueryRepository;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ProprietorVo;

/**
 * 业主信息领域模型
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class ProprietorContext {

	private static Logger logger = LoggerFactory.getLogger(ProprietorContext.class);
	
	@Autowired
	private ProprietorRepository proprietorRepository;
	
	@Autowired
	private ProprietorQueryRepository proprietorQueryRepository;
	
	/**
	 * 业主信息ID
	 */
	protected String proprietorId;
	
	/**
	 * 业主信息值对象
	 */
	protected ProprietorVo proprietor;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static ProprietorContext getInstance(){
		return SpringBeanUtil.getBean(ProprietorContext.class);
	}
	
	/**
	 * 根据业主信息值对象构建上下文
	 * @param proprietor 业主信息值对象
	 * @return 业主信息上下文
	 */
	public static ProprietorContext build(ProprietorVo proprietor) {
		ProprietorContext proprietorContext = getInstance();
		proprietorContext.setProprietor(proprietor);
		
		return proprietorContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 业主信息上下文
	 */
	public static ProprietorContext build() {
		ProprietorContext proprietorContext = getInstance();
		
		return proprietorContext;
	}
	
	/**
	 * 根据业主信息ID加载上下文
	 * @param proprietorId 业主信息ID
	 * @return 业主信息上下文
	 */
	public static ProprietorContext loadById(String proprietorId) {
		ProprietorContext proprietorContext = getInstance();
		proprietorContext.setProprietorId(proprietorId);
		
		return proprietorContext;
	}
	
	/**
	 * 查询业主信息
	 * @return 业主信息值对象
	 */
	public ProprietorVo getProprietor() {
		if(proprietor == null) {
			ProprietorPo proprietorPo = null;
			if(ValidateHelper.isNotEmptyString(proprietorId)) {
				proprietorPo = proprietorRepository.findOne(proprietorId);
			}
			
			if(proprietorPo != null) {
				proprietor = MyBeanUtil.createBean(proprietorPo, ProprietorVo.class);
			}
		}
		
		return proprietor;
	}
	
	/**
	 * 保存业主信息
	 */
	public ProprietorVo create() throws Exception {
		if(proprietor != null) {
			proprietor.setId(UUIDGenerator.generate());
			proprietor.setOemCode(ContextManager.getInstance().getOemCode()); 
			proprietor.setRegisterTime(new Date());
			
			ProprietorPo proprietorPo = MyBeanUtil.createBean(proprietor, ProprietorPo.class);
			proprietorRepository.save(proprietorPo);
		}
		
		return proprietor;
	}
	
	/**
	 * 更新业主信息
	 */
	public ProprietorVo update() throws Exception {
		if(proprietor != null && ValidateHelper.isNotEmptyString(proprietor.getId())) {
			logger.info("更新业主信息，业主ID（{}）,proprietor={}", proprietor.getId(), proprietor);
			ProprietorPo proprietorPo = proprietorRepository.findOne(proprietor.getId());
			
			if(proprietorPo != null) {
				MyBeanUtil.copyBeanNotNull2Bean(proprietor, proprietorPo);
				proprietorRepository.save(proprietorPo);
				logger.info("更新业主信息成功，业主ID（{}", proprietor.getId());
				
				proprietor = MyBeanUtil.createBean(proprietorPo, ProprietorVo.class);
			}
		}
		
		return proprietor;
	}
	
	/**
	 * 分页查询业主综合信息
	 * @param params 查询参数封装类对象
	 * @param pageSize 分页大小
	 * @param currentPage 分页当前页
	 * @return 业主综合信息分页数据
	 */
	public DataPageValue<ProprietorSingleHouseVo> listProprietorSingleHouse4Page(ProprietorBo params, Integer pageSize, Integer currentPage) {
		// 查询参数拷贝
		Map<String, String> extra = MyBeanUtil.bean2Map(params);
		
		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(extra);
		pagingParamVo.setRows(pageSize);
		pagingParamVo.setPage(currentPage);

		Long count = proprietorQueryRepository.queryByCount(pagingParamVo);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		pagingParamVo.getPager().setRowCount(count);
		List<ProprietorSingleHouseVo> proprietorList = proprietorQueryRepository.queryByPage(pagingParamVo);
		proprietorList = (ValidateHelper.isEmptyCollection(proprietorList) ? 
				new ArrayList<ProprietorSingleHouseVo>() : proprietorList);
		
		// 身份证号解密
		for(ProprietorSingleHouseVo singleHouse : proprietorList) {
			if(ValidateHelper.isNotEmptyString(singleHouse.getIdNum())) {
				String decryptIdNum = EncryptionUtil.decryptCustIdNum(singleHouse.getIdNum());
				singleHouse.setIdNum(decryptIdNum);
			}
		}

		DataPageValue<ProprietorSingleHouseVo> proprietorPage = new DataPageValue<ProprietorSingleHouseVo>(
				proprietorList, count, pageSize, currentPage);
		
		return proprietorPage;
	}
	
	/**
	 * 获取业主某房产信息
	 * @param params 查询参数
	 * @return 业主某房产信息
	 */
	public ProprietorSingleHouseVo getProprietorSingleHouse(String proprietorHouseId) {
		ProprietorSingleHouseVo singleHouse = proprietorQueryRepository.queryProprietorSingleHouse(proprietorHouseId);
		
		// 身份证号解密
		if(singleHouse != null && ValidateHelper.isNotEmptyString(singleHouse.getIdNum())) {
			String decryptIdNum = EncryptionUtil.decryptCustIdNum(singleHouse.getIdNum());
			singleHouse.setIdNum(decryptIdNum);
		}
		
		return singleHouse;
	}

	protected void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
	}

	protected void setProprietor(ProprietorVo proprietor) {
		this.proprietor = proprietor;
	}
}
