package com.liefeng.property.domain.project;

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
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.bo.project.HouseSpecBo;
import com.liefeng.property.po.project.HouseSpecPo;
import com.liefeng.property.repository.HouseSpecRepository;
import com.liefeng.property.vo.project.HouseSpecVo;

/**
 * 房产规格领域模型
 * @author ZhenTingJun
 * @date 2016-01-18
 */
@Service
@Scope("prototype")
public class HouseSpecContext {

	private static Logger logger = LoggerFactory.getLogger(HouseSpecContext.class);
	
	@Autowired
	private HouseSpecRepository houseSpecRepository;
	
	/**
	 * 房产规格ID
	 */
	private String houseSpecId;

	/**
	 * 房产规格值对象
	 */
	private HouseSpecVo houseSpec;
	
	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	private static HouseSpecContext getInstance() {
		return SpringBeanUtil.getBean(HouseSpecContext.class);
	}
	
	/**
	 * 根据房产规格值对象构建上下文
	 * @param houseSpec 房产规格值对象
	 * @return 房产规格上下文
	 */
	public static HouseSpecContext build(HouseSpecVo houseSpec) {
		HouseSpecContext houseSpecContext = getInstance();
		houseSpecContext.houseSpec = houseSpec;
		
		return houseSpecContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 房产规格上下文
	 */
	public static HouseSpecContext build() {
		HouseSpecContext houseSpecContext = getInstance();
		
		return houseSpecContext;
	}
	
	/**
	 * 根据房产规格ID加载上下文
	 * @param houseSpecId 房产规格ID
	 * @return 房产规格上下文
	 */
	public static HouseSpecContext loadById(String houseSpecId) {
		HouseSpecContext houseSpecContext = getInstance();
		houseSpecContext.houseSpecId = houseSpecId;
		
		return houseSpecContext;
	}
	
	/**
	 * 获取房产规格信息
	 * @return 房产规格值对象
	 */
	public HouseSpecVo getHouseSpec() {
		if(houseSpec == null) {
			HouseSpecPo houseSpecPo = null;
			// 根据房产ID查询
			if(ValidateHelper.isNotEmptyString(houseSpecId)) {
				houseSpecPo = houseSpecRepository.findOne(houseSpecId);
			} 
			
			if(houseSpecPo != null) {
				houseSpec = MyBeanUtil.createBean(houseSpecPo, HouseSpecVo.class);
			}
		}
		
		return houseSpec;
	}
	
	/**
	 * 保存房产规格
	 */
	public HouseSpecVo create() {
		if(houseSpec != null) {
			houseSpec.setId(UUIDGenerator.generate());
			houseSpec.setOemCode(ContextManager.getInstance().getOemCode()); 
			
			HouseSpecPo houseSpecPo = MyBeanUtil.createBean(houseSpec, HouseSpecPo.class);
			houseSpecRepository.save(houseSpecPo);
		}
		
		return houseSpec;
	}

	/**
	 * 更新房产规格
	 */
	public HouseSpecVo update() {
		if(houseSpec != null && ValidateHelper.isNotEmptyString(houseSpec.getId())) {
			HouseSpecPo houseSpecPo = houseSpecRepository.findOne(houseSpec.getId());
			
			MyBeanUtil.copyBeanNotNull2Bean(houseSpec, houseSpecPo);;
			houseSpecRepository.save(houseSpecPo);
		}
		
		return houseSpec;
	}
	
	/**
	 * 根据项目ID查询房产规格
	 * @param params 查询参数封装类
	 * @param page 第几页，最小为1
	 * @param size 页面大小
	 * @return
	 */
	public DataPageValue<HouseSpecVo> findHouseSpecs4Page(HouseSpecBo params, Integer page, Integer size) {
		Page<HouseSpecVo> voPage = null;
		Page<HouseSpecPo> poPage = null;

		if(ValidateHelper.isNotEmptyString(params.getProjectId()) && ValidateHelper.isNotEmptyString(params.getBuildingId())) {
			// spring-data 的page从0开始
			poPage = houseSpecRepository.findByProjectIdAndBuildingId(params.getProjectId(), params.getBuildingId(), new PageRequest(page-1, size));
		} else if(ValidateHelper.isNotEmptyString(params.getProjectId())) {
			// spring-data 的page从0开始
			poPage = houseSpecRepository.findByProjectId(params.getProjectId(), new PageRequest(page-1, size));
		}
		
		voPage = poPage.map(new Po2VoConverter<HouseSpecPo, HouseSpecVo>(HouseSpecVo.class));

		return new DataPageValue<HouseSpecVo>(voPage.getContent(), voPage.getTotalElements(), size, page);
	}
}
