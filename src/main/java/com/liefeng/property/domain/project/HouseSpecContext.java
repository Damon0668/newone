package com.liefeng.property.domain.project;

import java.util.List;
import java.util.Map;

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
import com.liefeng.property.bo.project.HouseSpecBo;
import com.liefeng.property.error.ProjectErrorCode;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.po.project.HouseSpecPo;
import com.liefeng.property.repository.mybatis.HouseSpecQueryRepository;
import com.liefeng.property.repository.project.HouseSpecRepository;
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
	
	@Autowired
	private HouseSpecQueryRepository houseSpecQueryRepository;
	
	/**
	 * 房产规格ID 或 以“,”分隔的ID串
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
	public HouseSpecVo create() throws PropertyException {
		if(houseSpec != null) {
			
			if(houseSpecRepository.findByProjectIdAndBuildingIdAndNum(
			   houseSpec.getProjectId(), houseSpec.getBuildingId(), houseSpec.getNum()) != null) {
				throw new PropertyException(ProjectErrorCode.HOUSESPEC_ALREADY_EXIST);
			}
			
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
	 * 更新房产规格
	 */
	public void delete() {
		if(ValidateHelper.isNotEmptyString(houseSpecId)) {
			String[] ids = houseSpecId.split(",");
			if(ids != null && ids.length > 0) {
				for(int i=0; i<ids.length; i++) {
					houseSpecRepository.delete(ids[i]);
				}
			}
		}
	}
	
	/**
	 * 根据项目ID查询房产规格
	 * @param params 查询参数封装类
	 * @param page 第几页，最小为1
	 * @param size 页面大小
	 * @return
	 */
	public DataPageValue<HouseSpecVo> findHouseSpecs4Page(HouseSpecBo params, Integer page, Integer size) {
		// 参数拷贝
		Map<String, String> extra = MyBeanUtil.bean2Map(params);
		
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(page);
		param.setPageSize(size);
		
		Long count = houseSpecQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		List<HouseSpecVo> list = houseSpecQueryRepository.queryByPage(param);
		DataPageValue<HouseSpecVo> returnPage = new DataPageValue<HouseSpecVo>(list, count, size, page);
		
		return returnPage;
	}
}
