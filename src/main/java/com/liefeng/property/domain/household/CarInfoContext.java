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

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.household.CarInfoBo;
import com.liefeng.property.po.household.CarInfoPo;
import com.liefeng.property.repository.household.CarInfoRepository;
import com.liefeng.property.repository.mybatis.CarInfoQueryRepository;
import com.liefeng.property.util.DictionaryUtil;
import com.liefeng.property.vo.household.CarInfoVo;

/**
 * 车辆信息领域模型
 * 
 * @author ZhenTingJun
 * @date 2016年4月25日
 */
@Service
@Scope("prototype")
public class CarInfoContext {

	private static Logger logger = LoggerFactory.getLogger(CarInfoContext.class);
	
	@Autowired
	private CarInfoRepository carInfoRepository;
	
	@Autowired
	private CarInfoQueryRepository carInfoQueryRepository;

	/**
	 * 车辆信息ID
	 */
	private String carInfoId;
	
	/**
	 * 车辆信息值对象
	 */
	private CarInfoVo carInfoVo;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static CarInfoContext getInstance(){
		return SpringBeanUtil.getBean(CarInfoContext.class);
	}
	
	public static CarInfoContext build(CarInfoVo carInfoVo) {
		CarInfoContext carInfoContext = getInstance();
		carInfoContext.setCarInfoVo(carInfoVo);
		
		return carInfoContext;
	}
	
	public static CarInfoContext build() {
		CarInfoContext carInfoContext = getInstance();
		
		return carInfoContext;
	}
	
	public static CarInfoContext loadById(String carInfoId) {
		CarInfoContext carInfoContext = getInstance();
		carInfoContext.setCarInfoId(carInfoId);
		
		return carInfoContext;
	}
	
	public CarInfoVo get() {
		if(carInfoVo == null) {
			if(ValidateHelper.isNotEmptyString(carInfoId)) {
				carInfoVo = carInfoQueryRepository.queryById(carInfoId);
				carInfoVo = DictionaryUtil.transformDicValueToDicName(carInfoVo);
			}
		}
		
		return carInfoVo;
	}
	
	public CarInfoVo create() {
		if(carInfoVo != null) {
			carInfoVo.setId(UUIDGenerator.generate());
			carInfoVo.setCreateTime(new Date());
			carInfoVo.setOemCode(ContextManager.getInstance().getOemCode()); 
			
			CarInfoPo carInfoPo =  MyBeanUtil.createBean(carInfoVo, CarInfoPo.class);
			carInfoRepository.save(carInfoPo);
			logger.info("保存车辆信息成功，车辆信息ID = {}", carInfoPo.getId());
		}
		
		return carInfoVo;
	}
	
	public CarInfoVo update() {
		if(carInfoVo != null && ValidateHelper.isNotEmptyString(carInfoVo.getId())) {
			logger.info("更新车辆信息，车辆信息ID = {}", carInfoVo.getId());
			CarInfoPo carInfoPo = carInfoRepository.findOne(carInfoVo.getId());
			
			if(carInfoPo != null) {
				MyBeanUtil.copyBeanNotNull2Bean(carInfoVo, carInfoPo);
				carInfoRepository.save(carInfoPo);
				logger.info("更新车辆信息成功，车辆信息ID = {}", carInfoPo.getId());
				
				carInfoVo = MyBeanUtil.createBean(carInfoPo, CarInfoVo.class);
			}
		}
		
		return carInfoVo;
	}
	
	public void delete() {
		logger.info("删除车辆信息，车辆信息ID = {}", carInfoId);
		if(ValidateHelper.isNotEmptyString(carInfoId)) {
			carInfoRepository.delete(carInfoId);
			logger.info("删除车辆信息成功，车辆信息ID = {}", carInfoId);
		}
	}
	
	public DataPageValue<CarInfoVo> listCarInfo(CarInfoBo params, Integer pageSize, Integer currentPage) {
		// 查询参数拷贝
		Map<String, String> extra = MyBeanUtil.bean2Map(params);
		
		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(extra);
		pagingParamVo.setRows(pageSize);
		pagingParamVo.setPage(currentPage);

		Long count = carInfoQueryRepository.queryByCount(pagingParamVo);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		pagingParamVo.getPager().setRowCount(count);
		List<CarInfoVo> dataList = carInfoQueryRepository.queryByPage(pagingParamVo);
		dataList = (ValidateHelper.isEmptyCollection(dataList) ? new ArrayList<CarInfoVo>() : dataList);
		// 对字典类型字段【值-名】转换
		dataList = DictionaryUtil.transformDicValueToDicName(dataList);
		
		DataPageValue<CarInfoVo> dataPage = new DataPageValue<CarInfoVo>(dataList, count, pageSize, currentPage);
		
		return dataPage;
	}

	protected void setCarInfoId(String carInfoId) {
		this.carInfoId = carInfoId;
	}

	protected void setCarInfoVo(CarInfoVo carInfoVo) {
		this.carInfoVo = carInfoVo;
	}
	
}
