package com.liefeng.property.domain.household;

import java.util.ArrayList;
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
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.household.ResidentBo;
import com.liefeng.property.po.household.ResidentPo;
import com.liefeng.property.repository.ResidentRepository;
import com.liefeng.property.repository.mybatis.ResidentQueryRepository;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * 住户信息领域模型
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class ResidentContext {
	
	private static Logger logger = LoggerFactory.getLogger(ResidentContext.class);
	
	@Autowired
	private ResidentRepository residentRepository;
	
	@Autowired
	private ResidentQueryRepository residentQueryRepository;
	
	/**
	 * 住户信息ID
	 */
	private String residentId;
	
	/**
	 * 住户信息值对象
	 */
	private ResidentVo resident;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static ResidentContext getInstance() {
		return SpringBeanUtil.getBean(ResidentContext.class);
	}
	
	/**
	 * 根据住户信息值对象构建上下文
	 * @param resident 住户信息值对象
	 * @return 住户信息上下文
	 */
	public static ResidentContext build(ResidentVo resident) {
		 ResidentContext residentContext = getInstance();
		 residentContext.resident = resident;
		 
		 return residentContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 住户信息上下文
	 */
	public static ResidentContext build() {
		 ResidentContext residentContext = getInstance();
		 
		 return residentContext;
	}
	
	/**
	 * 根据住户信息ID加载上下文
	 * @param residentId 住户信息ID
	 * @return 住户信息上下文
	 */
	public static ResidentContext loadById(String residentId) {
		ResidentContext residentContext = getInstance();
		 residentContext.residentId = residentId;
		 
		 return residentContext;
	}
	
	/**
	 * 查询住户信息
	 * @return 住户信息值对象
	 */
	public ResidentVo getResident() {
		if(resident == null) {
			if(ValidateHelper.isNotEmptyString(residentId)) {
				resident = residentQueryRepository.queryById(residentId);
				
				// 对身份证号进行解密
				String idnum = resident.getCustomer().getIdNum();
				resident.getCustomer().setIdNum(EncryptionUtil.decryptCustIdNum(idnum));
			}
		}
		
		return resident;
	}
	
	/**
	 * 保存住户信息
	 */
	public ResidentVo create() {
		if(resident != null) {
			resident.setId(UUIDGenerator.generate());
			resident.setOemCode(""); // TODO 待确定后补齐
			
			ResidentPo residentPo = MyBeanUtil.createBean(resident, ResidentPo.class);
			residentRepository.save(residentPo);
		}
		
		return resident;
	}
	
	/**
	 * 更新住户信息
	 */
	public ResidentVo update() {
		if(resident != null && ValidateHelper.isNotEmptyString(resident.getId())) {
			ResidentPo residentPo = residentRepository.findOne(resident.getId());
			
			if(residentPo != null) {
				MyBeanUtil.copyBeanNotNull2Bean(resident, residentPo);
				residentRepository.save(residentPo);
				
				resident = MyBeanUtil.createBean(residentPo, ResidentVo.class);
			}
			
			
		}
		
		return resident;
	}
	
	/**
	 * 分页查询住户信息
	 * @param params 查询参数封装类对象
	 * @param pageSize 分页大小
	 * @param currentPage 分页当前页
	 * @return
	 */
	public DataPageValue<ResidentVo> listResident4Page(ResidentBo params, Integer pageSize,
			Integer currentPage) {
		// 参数拷贝
		Map<String, String> extra = MyBeanUtil.bean2Map(params);
		
		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(extra);
		pagingParamVo.setRows(pageSize);
		pagingParamVo.setPage(currentPage);

		Long count = residentQueryRepository.queryByCount(pagingParamVo);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		pagingParamVo.getPager().setRowCount(count);
		List<ResidentVo> residentVoList = residentQueryRepository.queryByPage(pagingParamVo);
		residentVoList = (ValidateHelper.isEmptyCollection(residentVoList) ? 
				new ArrayList<ResidentVo>() : residentVoList);

		DataPageValue<ResidentVo> residentPage = new DataPageValue<ResidentVo>(
				residentVoList, count, pageSize, currentPage);

		return residentPage;
	}
}
