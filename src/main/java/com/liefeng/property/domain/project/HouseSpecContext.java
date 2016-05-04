package com.liefeng.property.domain.project;

import java.util.HashMap;
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
	 * 获取房产规格信息(项目名称、楼栋名称、楼层名称）
	 * @return 房产规格值对象
	 */
	public HouseSpecVo findHouseSpec() {
		if(houseSpec == null) {
			// 根据房产ID查询
			if(ValidateHelper.isNotEmptyString(houseSpecId)) {
				String oemCode = ContextManager.getInstance().getOemCode();
				HashMap<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("id", houseSpecId);
				paramMap.put("oemCode", oemCode);

				PagingParamVo param = new PagingParamVo();
				param.setExtra(paramMap);
				
				houseSpec = houseSpecQueryRepository.queryByHouseSpecId(param);
			} 
		}
		
		return houseSpec;
	}
	
	/**
	 * 保存房产规格
	 */
	public HouseSpecVo create() throws PropertyException {
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
	 * 更新房产规格
	 */
	public String delete() {
		// 返回信息
		StringBuffer message = new StringBuffer("");
		
		if(ValidateHelper.isNotEmptyString(houseSpecId)) {
			String[] ids = houseSpecId.split(",");
			if(ids != null && ids.length > 0) {
				for(int i=0; i<ids.length; i++) {
					if(houseSpecQueryRepository.queryRelatedHouseCount(ids[i]) == 0) {
						houseSpecRepository.delete(ids[i]);
						logger.info("主键为'{}'的房型删除成功！");
					} else {
						houseSpec = houseSpecQueryRepository.queryById(ids[i]);
						message.append(houseSpec.getBuildingName())
							   .append(houseSpec.getFloorName())
							   .append("尾号为【")
							   .append(houseSpec.getNum())
							   .append("】的房型已关联数据，无法删除")
							   .append("\n");
						logger.info("主键为'{}'的房型已关联房产数据，无法删除！");
					}
				}
			}
		}
		
		return message.toString();
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
	
	/**
	 * 获取房型的数量
	 * @param params 查询过滤参数
	 * @return
	 */
	public Long getHouseSpecCount(HouseSpecBo params) {
		// 参数拷贝
		Map<String, String> extra = MyBeanUtil.bean2Map(params);
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		
		Long count = houseSpecQueryRepository.queryByCount(param);
		
		return count;
	}
	
	/**
	 * 获取某项目某楼栋中的每一层的房间数
	 * @param projectId
	 * @param buildingId
	 * @return 
	 * @author xhw
	 * @date 2016年4月24日 下午3:53:03
	 */
	public List<HouseSpecVo> countByProjectIdAndBuildingId(String projectId, String buildingId){
		
		List<HouseSpecVo> houseSpecList = null;
		
		if(ValidateHelper.isNotEmptyString(projectId) && ValidateHelper.isNotEmptyString(buildingId)){
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("projectId", projectId);
			paramMap.put("buildingId", buildingId);

			PagingParamVo param = new PagingParamVo();
			param.setExtra(paramMap);
			
			houseSpecList = houseSpecQueryRepository.countByProjectIdAndBuildingId(param);
		}
		return houseSpecList;
	}
}
