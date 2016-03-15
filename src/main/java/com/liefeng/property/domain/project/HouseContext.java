package com.liefeng.property.domain.project;

import java.util.ArrayList;
import java.util.Date;
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
import com.liefeng.property.bo.project.HouseBo;
import com.liefeng.property.po.project.HousePo;
import com.liefeng.property.repository.mybatis.HouseQueryRepository;
import com.liefeng.property.repository.project.HouseRepository;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.project.HouseVo;
import com.liefeng.property.vo.project.ProjectVo;

/**
 * 房产信息领域模型
 * @author ZhenTingJun
 * @author levy
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class HouseContext {

	private static Logger logger = LoggerFactory.getLogger(HouseContext.class);
	
	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	private HouseQueryRepository houseQueryRepository;
	
	/**
	 * 房产信息ID
	 */
	private String houseId;

	/**
	 * 房产所属项目ID
	 */
	protected String projectId;
	
	/**
	 * 房产所属楼栋ID
	 */
	protected String buildingId;
	
	/**
	 * 房产编码
	 */
	private String houseNum;
	
	/**
	 * 房产信息值对象
	 */
	private HouseVo house;
	
	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	private static HouseContext getInstance() {
		return SpringBeanUtil.getBean(HouseContext.class);
	}
	
	/**
	 * 根据房产信息值对象构建上下文
	 * @param house 房产信息值对象
	 * @return 房产信息上下文
	 */
	public static HouseContext build(HouseVo house) {
		HouseContext houseContext = getInstance();
		houseContext.house = house;
		
		return houseContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 房产信息上下文
	 */
	public static HouseContext build() {
		HouseContext houseContext = getInstance();
		
		return houseContext;
	}
	
	/**
	 * 根据房产信息ID加载上下文
	 * @param houseId 房产信息ID
	 * @return 房产信息上下文
	 */
	public static HouseContext loadById(String houseId) {
		HouseContext houseContext = getInstance();
		houseContext.houseId = houseId;
		
		return houseContext;
	}
	
	/**
	 * 根据房产编码加载上下文
	 * @param projectId 房产所属项目ID
	 * @param houseNum 房产编码
	 * @return 房产信息上下文
	 */
	public static HouseContext loadByProjectIdAndHouseNum(String projectId, String houseNum) {
		HouseContext houseContext = getInstance();
		houseContext.projectId = projectId;
		houseContext.houseNum = houseNum;
		
		return houseContext;
	}
	
	/**
	 * 根据房产编码加载上下文
	 * @param projectId 房产所属项目ID
	 * @param buildingId 房产所属楼栋ID
	 * @return 房产信息上下文
	 */
	public static HouseContext loadByProjectIdAndBuildingId(String projectId, String buildingId) {
		HouseContext houseContext = getInstance();
		houseContext.setProjectId(projectId);
		houseContext.setBuildingId(buildingId);
		
		return houseContext;
	}
	
	/**
	 * 获取房产信息
	 * @return 房产信息值对象
	 */
	public HouseVo get() {
		if(house == null) {
			HousePo housePo = null;
			// 根据房产ID查询
			if(ValidateHelper.isNotEmptyString(houseId)) {
				housePo = houseRepository.findOne(houseId);
			} 
			// 根据所属项目ID和房产编码查询
			else if(ValidateHelper.isNotEmptyString(projectId) && ValidateHelper.isNotEmptyString(houseNum)) {
				housePo = houseRepository.findByProjectIdAndHouseNum(projectId, houseNum);
			}
			
			if(housePo != null) {
				house = MyBeanUtil.createBean(housePo, HouseVo.class);
			}
		}
		
		return house;
	}
	
	/**
	 * 获取房产资料列表
	 */
	public List<HouseVo> getHouseList() {
		List<HouseVo> hosueList = null;
		
		if(ValidateHelper.isNotEmptyString(projectId) 
				&& ValidateHelper.isNotEmptyString(buildingId)) {
			List<HousePo> housePoList = houseRepository.findByProjectIdAndBuildingId(projectId, buildingId);
			
			hosueList = MyBeanUtil.createList(housePoList, HouseVo.class);
		} else {
			hosueList = new ArrayList<HouseVo>();
		}
		
		return hosueList;
	}
	
	/**
	 * 获取业主单套房产的信息
	 */
	public ProprietorSingleHouseVo getSingleHouse() {
		ProprietorSingleHouseVo singleHouse = new ProprietorSingleHouseVo();
		if(ValidateHelper.isNotEmptyString(houseId)) {
			singleHouse = houseQueryRepository.queryById(houseId);
		}
		
		return singleHouse;
	}
	
	/**
	 * 保存房产信息
	 */
	public HouseVo create() {
		if(house != null) {
			house.setId(UUIDGenerator.generate());
			house.setOemCode(ContextManager.getInstance().getOemCode());
			house.setRegisterTime(new Date());
			
			HousePo housePo = MyBeanUtil.createBean(house, HousePo.class);
			houseRepository.save(housePo);
		}
		return house;
	}

	public HouseVo update() {
		if(house != null && ValidateHelper.isNotEmptyString(house.getId())) {
			logger.info("更新房子信息，房子ID（{}）", house.getId());
			HousePo housePo = houseRepository.findOne(house.getId());
			
			MyBeanUtil.copyBeanNotNull2Bean(house, housePo);
			houseRepository.save(housePo);
		}
		
		return house;
	}

	public DataPageValue<ProprietorSingleHouseVo> listHouse4Page(HouseBo houseBo, Integer page, Integer size) {
		// 参数拷贝
		Map<String, String> extra = MyBeanUtil.bean2Map(houseBo);
		
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(page);
		param.setPageSize(size);
		
		Long count = houseQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		
		List<ProprietorSingleHouseVo> list = houseQueryRepository.queryByPage(param);

		DataPageValue<ProprietorSingleHouseVo> returnPage = new DataPageValue<ProprietorSingleHouseVo>(
				list, count, size, page);
		
		return returnPage;
	}
	
	/**
	 * 查询所有项目
	 * @return
	 */
	public List<ProjectVo> findAll() {
		List<HousePo> projectVos = houseRepository.findAll();
		return MyBeanUtil.createList(projectVos, ProjectVo.class);
	}

	protected String getProjectId() {
		return projectId;
	}

	protected void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	protected String getBuildingId() {
		return buildingId;
	}

	protected void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}


	
}
