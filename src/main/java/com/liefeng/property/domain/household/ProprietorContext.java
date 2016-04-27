package com.liefeng.property.domain.household;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.base.vo.UserVo;
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
import com.liefeng.property.repository.household.ProprietorRepository;
import com.liefeng.property.repository.mybatis.ProprietorQueryRepository;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ProprietorVo;
import com.liefeng.property.vo.household.UserClientIdVo;

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
	private String proprietorId;
	
	/**
	 * 业主信息值对象
	 */
	private ProprietorVo proprietor;
	
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
	public ProprietorVo get() {
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
	public ProprietorVo create() {
		if(proprietor != null) {
			proprietor.setId(UUIDGenerator.generate());
			proprietor.setOemCode(ContextManager.getInstance().getOemCode()); 
			proprietor.setRegisterTime(new Date());
			
			ProprietorPo proprietorPo = MyBeanUtil.createBean(proprietor, ProprietorPo.class);
			proprietorRepository.save(proprietorPo);
			logger.info("创建业主成功，业主ID = {}", proprietorPo.getId());
		}
		
		return proprietor;
	}
	
	/**
	 * 更新业主信息
	 */
	public ProprietorVo update() {
		if(proprietor != null && ValidateHelper.isNotEmptyString(proprietor.getId())) {
			logger.info("更新业主信息，业主ID = {}", proprietor.getId());
			ProprietorPo proprietorPo = proprietorRepository.findOne(proprietor.getId());
			
			if(proprietorPo != null) {
				MyBeanUtil.copyBeanNotNull2Bean(proprietor, proprietorPo);
				proprietorRepository.save(proprietorPo);
				logger.info("更新业主信息成功，业主ID = {}", proprietor.getId());
				
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
	
	/**
	 * 根据项目ID和客户全局ID查询业主信息
	 * @param projectId 项目ID
	 * @param custGlobalId 客户全局ID
	 * @return
	 */
	public ProprietorVo get(String projectId, String custGlobalId) {
		ProprietorPo proprietorPo = proprietorRepository.findByProjectIdAndCustGlobalId(projectId, custGlobalId);
		
		if(proprietorPo != null) {
			proprietor = MyBeanUtil.createBean(proprietorPo, ProprietorVo.class);
		}
		
		return proprietor;
	}
	
	/**
	 * 分页查询业主用户信息
	 * @param params 查询过滤参数
	 * @param currentPage 分页当前页
	 * @param pageSize 分页大小
	 * @return
	 */
	public DataPageValue<UserVo> listUsers(ProprietorBo params, Integer currentPage, Integer pageSize) {
		logger.info("查询参数为{}，分页当前页为{}，分页大小为{}", params, currentPage, pageSize);
		// 查询参数拷贝
		Map<String, String> extra = MyBeanUtil.bean2Map(params);
		
		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(extra);
		pagingParamVo.setRows(pageSize);
		pagingParamVo.setPage(currentPage);

		Long count = proprietorQueryRepository.queryUserByCount(pagingParamVo);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		pagingParamVo.getPager().setRowCount(count);
		List<UserVo> userList = proprietorQueryRepository.queryUserByPage(pagingParamVo);
		userList = (ValidateHelper.isEmptyCollection(userList) ? 
				new ArrayList<UserVo>() : userList);

		DataPageValue<UserVo> userPage = new DataPageValue<UserVo>(
				userList, count, pageSize, currentPage);
		
		return userPage;
	}
	
	/**
	 * 根据项目id和业主项目查询业主信息
	 * @param projectId 项目id
	 * @param proprietorName 业主姓名
	 * @return
	 */
	public ProprietorVo findByProjectIdAndName(String projectId,
			String proprietorName) {
		ProprietorPo proprietorPo = proprietorRepository.findByProjectIdAndName(projectId,proprietorName);
		return MyBeanUtil.createBean(proprietorPo, ProprietorVo.class);
	}

	/**
	 * 根据projectId、buildingId获取用户的clientId
	 * @param buildingId
	 * @param projectId
	 * @return 
	 * @author xhw
	 * @date 2016年4月9日 下午1:33:46
	 */
	public List<UserClientIdVo> listClientIdByBuildingIdAndProjectId(String buildingId, String projectId){
		String oemCode = ContextManager.getInstance().getOemCode();
		Map<String, String> extra = new HashMap<String, String>();
		extra.put("buildingId", buildingId);
		extra.put("projectId", projectId);
		extra.put("oemCode", oemCode);
		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(extra);
		return proprietorQueryRepository.queryClientId(pagingParamVo);
	}
	
	/**
	 * 根据projectId获取用户的cleintId
	 * @param projectId
	 * @return 
	 * @author xhw
	 * @date 2016年4月11日 下午5:08:26
	 */
	public List<UserClientIdVo> listClientIdByProjectId(String projectId){
		String oemCode = ContextManager.getInstance().getOemCode();
		Map<String, String> extra = new HashMap<String, String>();
		extra.put("projectId", projectId);
		extra.put("oemCode", oemCode);
		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(extra);
		return proprietorQueryRepository.queryAllClientIdsByProjectId(pagingParamVo);
	}
	
	/**
	 * 根据projectId、houseNum获取用户的cleintId
	 * @param projectId
	 * @return 
	 * @author xhw
	 * @date 2016年4月12日 下午2:52:30
	 */
	public List<UserClientIdVo> listClientIdByProjectIdAndHouseNum(String projectId, String houseNum){
		String oemCode = ContextManager.getInstance().getOemCode();
		Map<String, String> extra = new HashMap<String, String>();
		extra.put("projectId", projectId);
		extra.put("houseNum", houseNum);
		extra.put("oemCode", oemCode);
		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(extra);
		return proprietorQueryRepository.queryAllClientIdsByProjectIdAndHouseNum(pagingParamVo);
	}
	
	/**
	 * 根据projectId、houseNum获取业主资料信息
	 * @param projectId
	 * @param houseNum
	 * @return 
	 * @author xhw
	 * @date 2016年4月14日 上午10:13:00
	 */
	public ProprietorSingleHouseVo findProprietorSingleHouseVo(String projectId, String houseNum){
		String oemCode = ContextManager.getInstance().getOemCode();
		Map<String, String> extra = new HashMap<String, String>();
		extra.put("projectId", projectId);
		extra.put("houseNum", houseNum);
		extra.put("oemCode", oemCode);
		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(extra);
		return proprietorQueryRepository.queryProprietorByProjectIdAndHouseNum(pagingParamVo);
	}
	
	/**
	 * 根据房号查询业主
	 * @param projectId 项目ID
	 * @param houseNum 房号
	 * @author ZhenTingJun
	 * @date 2016年4月26日
	 */
	public List<ProprietorVo> listProprietorByHouseNum(String projectId, String houseNum) {
		String oemCode = ContextManager.getInstance().getOemCode();
		return proprietorQueryRepository.queryByHouseNum(projectId, houseNum, oemCode);
	}
	
	protected void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
	}

	protected void setProprietor(ProprietorVo proprietor) {
		this.proprietor = proprietor;
	}

	
}
