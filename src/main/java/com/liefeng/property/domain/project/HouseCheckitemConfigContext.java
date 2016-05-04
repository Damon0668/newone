package com.liefeng.property.domain.project;

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
import com.liefeng.property.po.project.HouseCheckitemConfigPo;
import com.liefeng.property.repository.mybatis.HouseCheckitemConfigQueryRepository;
import com.liefeng.property.repository.project.HouseCheckitemConfigRepository;
import com.liefeng.property.vo.project.HouseCheckitemConfigVo;

/**
 * 房屋验收项配置上下文对象
 * @author xhw
 * @date 2016年4月26日 下午4:30:25
 */
@Service
@Scope("prototype")
public class HouseCheckitemConfigContext {
	
	private static Logger logger = LoggerFactory.getLogger(HouseCheckitemConfigContext.class);
	
	@Autowired
	private HouseCheckitemConfigRepository houseCheckitemConfigRepository;
	
	@Autowired
	private HouseCheckitemConfigQueryRepository houseCheckitemConfigQueryRepository;
	
	/**
	 * 配置id
	 */
	private String id;
	
	/**
	 * 验收配置项值对象
	 */
	private HouseCheckitemConfigVo houseCheckitemConfigVo;
	
	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	private static HouseCheckitemConfigContext getInstance() {
		return SpringBeanUtil.getBean(HouseCheckitemConfigContext.class);
	}
	
	/**
	 * 根据房屋验收项配置获取上下文对象
	 * @param houseCheckitemConfig
	 * @return 
	 * @author xhw
	 * @date 2016年4月26日 下午4:36:54
	 */
	public static HouseCheckitemConfigContext build(HouseCheckitemConfigVo houseCheckitemConfig) {
		HouseCheckitemConfigContext houseCheckitemConfigContext = getInstance();
		houseCheckitemConfigContext.setHouseCheckitemConfigVo(houseCheckitemConfig);
		
		return houseCheckitemConfigContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 房屋验收项配置上下文
	 */
	public static HouseCheckitemConfigContext build() {
		HouseCheckitemConfigContext houseCheckitemConfigContext = getInstance();
		
		return houseCheckitemConfigContext;
	}
	
	/**
	 * 根据房屋验收项配置ID加载上下文
	 * @param id
	 * @return 
	 * @author xhw
	 * @date 2016年4月26日 下午4:39:06
	 */
	public static HouseCheckitemConfigContext loadById(String id) {
		HouseCheckitemConfigContext houseCheckitemConfigContext = getInstance();
		houseCheckitemConfigContext.setId(id);
		
		return houseCheckitemConfigContext;
	}
	
	/**
	 * 获取房屋验收项配置
	 * @return 
	 * @author xhw
	 * @date 2016年4月26日 下午4:45:37
	 */
	public HouseCheckitemConfigVo get() {
		if(houseCheckitemConfigVo == null) {
			if(ValidateHelper.isNotEmptyString(id)) {
				HouseCheckitemConfigPo houseCheckitemConfigPo = houseCheckitemConfigRepository.findOne(id);
				houseCheckitemConfigVo = MyBeanUtil.createBean(houseCheckitemConfigPo, HouseCheckitemConfigVo.class);
				
				logger.info("[HouseCheckitemConfigContext.get()] get houseCheckitemConfigVo:{} by id:{}", houseCheckitemConfigVo, id);
			}
			
		}
		
		return houseCheckitemConfigVo;
	}
	
	/**
	 * 添加房屋验收项配置
	 * @return 
	 * @author xhw
	 * @date 2016年5月4日 上午11:04:53
	 */
	public HouseCheckitemConfigVo create() {
		if(houseCheckitemConfigVo != null) {
			houseCheckitemConfigVo.setId(UUIDGenerator.generate());
			houseCheckitemConfigVo.setOemCode(ContextManager.getInstance().getOemCode());
			
			HouseCheckitemConfigPo houseCheckitemConfigPo = MyBeanUtil.createBean(houseCheckitemConfigVo, HouseCheckitemConfigPo.class);
			
			houseCheckitemConfigRepository.save(houseCheckitemConfigPo);
			
			logger.info("[HouseCheckitemConfigContext.create()] create houseCheckitemConfigVo:{}", houseCheckitemConfigVo);
		}
		return houseCheckitemConfigVo;
	}
	
	/**
	 * 获取默认排序号
	 * @return 
	 * @author xhw
	 * @date 2016年4月26日 下午4:52:35
	 */
	public Long getSortindex(String projectId, String parentId){
		long sortindex = 0;
		if(ValidateHelper.isNotEmptyString(projectId) && ValidateHelper.isNotEmptyString(parentId)){
			
			String oemCode = ContextManager.getInstance().getOemCode();
			
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("oemCode", oemCode);
			paramMap.put("projectId", projectId);
			paramMap.put("parentId", parentId);
			
			PagingParamVo param = new PagingParamVo();
			param.setExtra(paramMap);
			
			sortindex = houseCheckitemConfigQueryRepository.queryByCount(param);
			sortindex = sortindex + 1;
		}
		return sortindex;
	}
	
	/**
	 * 分页获取房屋验收项配置
	 * @param projectId
	 * @param parentId
	 * @param page
	 * @param size
	 * @return 
	 * @author xhw
	 * @date 2016年4月26日 下午5:43:56
	 */
	public DataPageValue<HouseCheckitemConfigVo> findByPage(String projectId, String parentId, Integer page, Integer size) {
		String oemCode = ContextManager.getInstance().getOemCode();
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("projectId", projectId);
		paramMap.put("parentId", parentId);
		paramMap.put("oemCode", oemCode);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);
		param.setPage(page);
		param.setPageSize(size);

		Long count = houseCheckitemConfigQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);

		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		List<HouseCheckitemConfigVo> list = houseCheckitemConfigQueryRepository.queryByPage(param);
		DataPageValue<HouseCheckitemConfigVo> returnPage = new DataPageValue<HouseCheckitemConfigVo>(list, count, size, page);

		return returnPage;
	}

	/**
	 * 更新房屋验收项配置
	 * @return 
	 * @author xhw
	 * @date 2016年4月26日 下午5:49:00
	 */
	public HouseCheckitemConfigVo update() {
		if(houseCheckitemConfigVo != null && ValidateHelper.isNotEmptyString(houseCheckitemConfigVo.getId())) {
			HouseCheckitemConfigPo houseCheckitemConfigPo = houseCheckitemConfigRepository.findOne(houseCheckitemConfigVo.getId());
			
			if(houseCheckitemConfigPo != null) {
				MyBeanUtil.copyBeanNotNull2Bean(houseCheckitemConfigVo, houseCheckitemConfigPo);
				houseCheckitemConfigRepository.save(houseCheckitemConfigPo);
				
				houseCheckitemConfigVo = MyBeanUtil.createBean(houseCheckitemConfigPo, HouseCheckitemConfigVo.class);
				
				logger.info("[HouseCheckitemConfigContext.update()] update houseCheckitemConfigVo:{}", houseCheckitemConfigVo);
			}
		}
		
		return houseCheckitemConfigVo;
	}

	/**
	 * 根据id，删除配置（包括子配置）
	 *  
	 * @author xhw
	 * @date 2016年4月26日 下午6:01:57
	 */
	public void delete(){
		if(ValidateHelper.isNotEmptyString(id)){
			houseCheckitemConfigRepository.deleteByIdOrParentId(id, id);
			
			logger.info("[HouseCheckitemConfigContext.delete()] delete houseCheckitemConfigVo By id:{}", id);
		}
	}

	protected void setId(String id) {
		this.id = id;
	}


	protected void setHouseCheckitemConfigVo(
			HouseCheckitemConfigVo houseCheckitemConfigVo) {
		this.houseCheckitemConfigVo = houseCheckitemConfigVo;
	}
	
	
}
