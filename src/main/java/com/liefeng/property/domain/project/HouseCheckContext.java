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
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.po.project.HouseCheckPo;
import com.liefeng.property.repository.mybatis.HouseCheckQueryRepository;
import com.liefeng.property.repository.project.HouseCheckRepository;
import com.liefeng.property.vo.project.HouseCheckVo;

/**
 * 房屋验收单上下文对象
 * @author xhw
 * @date 2016年5月4日 上午10:39:04
 */
@Service
@Scope("prototype")
public class HouseCheckContext {
	
	private static Logger logger = LoggerFactory.getLogger(HouseCheckContext.class);
	
	@Autowired
	private HouseCheckRepository houseCheckRepository;
	
	@Autowired
	private HouseCheckQueryRepository houseCheckQueryRepository;
	
	/**
	 * 配置id
	 */
	private String id;
	
	/**
	 * 房屋验收单值对象
	 */
	private HouseCheckVo houseCheckVo;
	
	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	private static HouseCheckContext getInstance() {
		return SpringBeanUtil.getBean(HouseCheckContext.class);
	}
	
	/**
	 * 根据房屋验收单值对象获取上下文对象
	 * @param houseCheck
	 * @return 
	 * @author xhw
	 * @date 2016年5月4日 上午10:42:53
	 */
	public static HouseCheckContext build(HouseCheckVo houseCheck) {
		HouseCheckContext houseCheckContext = getInstance();
		houseCheckContext.setHouseCheckVo(houseCheck);
		
		return houseCheckContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 房屋验收单上下文
	 */
	public static HouseCheckContext build() {
		HouseCheckContext houseCheckContext = getInstance();
		
		return houseCheckContext;
	}
	
	/**
	 * 根据id获取上下文对象
	 * @param id
	 * @return 
	 * @author xhw
	 * @date 2016年5月4日 上午10:44:08
	 */
	public static HouseCheckContext loadById(String id) {
		HouseCheckContext houseCheckContext = getInstance();
		houseCheckContext.setId(id);
		
		return houseCheckContext;
	}
	
	/**
	 * 获取房屋验收单
	 * @return 
	 * @author xhw
	 * @date 2016年5月4日 上午11:02:29
	 */
	public HouseCheckVo get() {
		if(houseCheckVo == null) {
			if(ValidateHelper.isNotEmptyString(id)) {
				HouseCheckPo houseCheckPo = houseCheckRepository.findOne(id);
				houseCheckVo = MyBeanUtil.createBean(houseCheckPo, HouseCheckVo.class);
				
				logger.info("[HouseCheckContext.get()] get houseCheckVo:{} by id:{}", houseCheckVo, id);
			}
			
		}
		
		return houseCheckVo;
	}
	
	/**
	 * 创建房屋验收单
	 * @param houseCheckVoList 
	 * @author xhw
	 * @date 2016年5月5日 下午6:26:02
	 */
	public void create(List<HouseCheckVo> houseCheckVoList){
		if(ValidateHelper.isNotEmptyCollection(houseCheckVoList)){
			List<HouseCheckPo> houseCheckPoList = MyBeanUtil.createList(houseCheckVoList, HouseCheckPo.class);
			houseCheckRepository.save(houseCheckPoList);
			
			logger.info("[HouseCheckContext.create()] create houseCheckVoList:{} ", houseCheckVoList);
		}
	}
	
	/**
	 * 删除验收单
	 * @param projectId
	 * @param houseNum 
	 * @author xhw
	 * @date 2016年5月6日 上午8:35:37
	 */
	public void delete(String projectId, String houseNum){
		if(ValidateHelper.isNotEmptyString(projectId) && ValidateHelper.isNotEmptyString(houseNum)){
			String oemCode = ContextManager.getInstance().getOemCode();
			houseCheckRepository.deleteByProjectIdAndHouseNumAndOemCode(projectId, houseNum, oemCode);
			
			logger.info("[HouseCheckContext.delete()] delete houseCheckVo by projectId:{}, houseNum:{}, oemCode:{} ", projectId, houseNum, oemCode);
		}
	}
	
	/**
	 * 获取房屋验收单
	 * @param projectId
	 * @param houseNum
	 * @return 
	 * @author xhw
	 * @date 2016年5月6日 上午9:49:23
	 */
	public List<HouseCheckVo> getHouseCheck(String projectId, String houseNum){
		List<HouseCheckVo> houseCheckList = null;
		if(ValidateHelper.isNotEmptyString(projectId) && ValidateHelper.isNotEmptyString(houseNum)){
			
			String oemCode = ContextManager.getInstance().getOemCode();
			
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("oemCode", oemCode);
			paramMap.put("projectId", projectId);
			paramMap.put("houseNum", houseNum);
			
			PagingParamVo param = new PagingParamVo();
			param.setExtra(paramMap);
			
			houseCheckList = houseCheckQueryRepository.queryByProjectIdAndHouseNum(param);
		}
		
		return houseCheckList;
	}
	
	protected void setId(String id) {
		this.id = id;
	}

	protected void setHouseCheckVo(HouseCheckVo houseCheckVo) {
		this.houseCheckVo = houseCheckVo;
	}
	
}
