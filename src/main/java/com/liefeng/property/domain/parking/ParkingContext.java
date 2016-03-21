package com.liefeng.property.domain.parking;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.StringUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.parking.ParkingBo;
import com.liefeng.property.error.ParkingErrorCode;
import com.liefeng.property.exception.ParkingException;
import com.liefeng.property.po.parking.ParkingPo;
import com.liefeng.property.repository.mybatis.ParkingQueryRepository;
import com.liefeng.property.repository.parking.ParkingRepository;
import com.liefeng.property.vo.parking.ParkingSingleRentalVo;
import com.liefeng.property.vo.parking.ParkingVo;

/**
 * 车位信息领域模型
 * 
 * @author ZhenTingJun
 * @date 2015-12-25
 */
@Service
@Scope("prototype")
public class ParkingContext {

	private static Logger logger = LoggerFactory
			.getLogger(ParkingContext.class);

	@Autowired
	private ParkingRepository parkingRepository;

	@Autowired
	private ParkingQueryRepository parkingQueryRepository;

	/**
	 * 车位信息ID
	 */
	private String parkingId;

	/**
	 * 车位信息值对象
	 */
	private ParkingVo parking;

	/**
	 * 获取本类实例，每次返回一个新的对象
	 * 
	 * @return 本类实例
	 */
	private static ParkingContext getInstance() {
		return SpringBeanUtil.getBean(ParkingContext.class);
	}

	/**
	 * 根据车位信息值对象构建上下文
	 * 
	 * @param parking
	 *            车位信息值对象
	 * @return 车位信息上下文
	 */
	public static ParkingContext build(ParkingVo parking) {
		ParkingContext parkingContext = getInstance();
		parkingContext.setParking(parking);

		return parkingContext;
	}

	/**
	 * 构建上下文（无参）
	 * 
	 * @return 车位信息上下文
	 */
	public static ParkingContext build() {
		ParkingContext parkingContext = getInstance();

		return parkingContext;
	}

	/**
	 * 根据车位信息ID加载上下文
	 * 
	 * @param parkingId
	 *            车位信息ID
	 * @return 车位信息上下文
	 */
	public static ParkingContext loadById(String parkingId) {
		ParkingContext parkingContext = getInstance();
		parkingContext.setParkingId(parkingId);

		return parkingContext;
	}

	/**
	 * 获取车位信息
	 * 
	 * @return 车位信息值对象
	 */
	public ParkingVo getParking() {
		if (parking == null) {
			ParkingPo parkingPo = null;
			if (ValidateHelper.isNotEmptyString(parkingId)) {
				parkingPo = parkingRepository.findOne(parkingId);
			}

			if (parkingPo != null) {
				parking = MyBeanUtil.createBean(parkingPo, ParkingVo.class);
			}
		}

		return parking;
	}

	/**
	 * 保存车位信息
	 */
	public ParkingVo create() {
		if (parking != null) {
			
			ParkingPo parkingPoExist = parkingRepository.findByProjectIdAndNum(parking.getProjectId(),parking.getNum());
			//判断是否存在
			if(parkingPoExist != null){
				throw new ParkingException(ParkingErrorCode.PARKING_ALREADY_EXIST,parkingPoExist.getNum());
			}
			parking.setId(UUIDGenerator.generate());
			parking.setOemCode(ContextManager.getInstance().getOemCode());

			ParkingPo parkingPo = MyBeanUtil.createBean(parking,
					ParkingPo.class);
			return MyBeanUtil.createBean(parkingRepository.save(parkingPo),ParkingVo.class);
		}
		return null;
	}
	
	/**
	 *	批量创建
	 * @param startNum
	 * @param endNum
	 */
	public void createMany(String startString, String endString) {
		String prefix = StringUtil.getSamePrefix(startString, endString);
		Integer startNum = 0;
		Integer endNum = 0;
		
		try {
			if (prefix.equals("")) {
				startNum = Integer.parseInt(startString);
				endNum = Integer.parseInt(endString);
			} else {
				startNum = Integer.parseInt(startString.substring(prefix
						.length()));
				endNum = Integer.parseInt(endString.substring(prefix.length()));
			}
		} catch (Exception e) {
			throw new ParkingException(ParkingErrorCode.PARAMETER_FORMAT_ERROR);
		}
		String existNum = "";
		for(int num=startNum; num<=endNum;num++){
			String zero="";
			if(num+"".length() < startString.length() - prefix.length()){
				for(int i = num+"".length();i< startString.length() - prefix.length();i++){
					zero+="0";
				}
			}
			String parkNum=prefix+zero+num;
			ParkingPo parkingPoExist = parkingRepository.findByProjectIdAndNum(parking.getProjectId(),parkNum);
			//判断是否存在
			if(parkingPoExist != null){
				existNum+=parkNum+",";		
			}else{
				logger.info("create parking num is {}", parkNum);
				ParkingPo parkingPo = MyBeanUtil.createBean(parking,
						ParkingPo.class);
				parkingPo.setCode(parkingPo.getCode()+parkNum);
				parkingPo.setId(UUIDGenerator.generate());
				parkingPo.setOemCode(ContextManager.getInstance().getOemCode());
				parkingPo.setNum(parkNum);
				parkingRepository.save(parkingPo);
			}
		}
		//抛出已经存在的车位号码
		if(!existNum.equals("")){
			throw new ParkingException(ParkingErrorCode.PARKING_ALREADY_EXIST,existNum);
		}
	}
	
	public void delete() {
		logger.info("delete parking id :{0}",parkingId);
		
		parkingRepository.delete(parkingId);
	}
	
	public ParkingSingleRentalVo get() {
		return parkingQueryRepository.queryById(parkingId);
	}
	
	public DataPageValue<ParkingSingleRentalVo> list(ParkingBo parkingBo,
			Integer page, Integer size) {
		// 参数拷贝
		Map<String, String> extra = MyBeanUtil.bean2Map(parkingBo);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(page);
		param.setPageSize(size);

		Long count = parkingQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("总数量：count={0}", count);
		
		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		
		List<ParkingSingleRentalVo> parkingSingleRentalVos = parkingQueryRepository.queryByPage(param);
		DataPageValue<ParkingSingleRentalVo> returnPage = new DataPageValue<ParkingSingleRentalVo>(parkingSingleRentalVos, count, size, page);
		
		return returnPage;
	}
	
	public List<ParkingSingleRentalVo> findByProjectId(String projectId) {
		
		List<ParkingSingleRentalVo> parkingSingleRentalVos = parkingQueryRepository.queryByProjectId(projectId);
		return parkingSingleRentalVos;
	}
	
	public void update() {
		if(parking!=null && ValidateHelper.isNotEmptyString(parking.getId())){
				parking.setOemCode(ContextManager.getInstance().getOemCode());
				parkingRepository.save(MyBeanUtil.createBean(parking,ParkingPo.class));
		}
	}


	protected void setParking(ParkingVo parking) {
		this.parking = parking;
	}

	protected void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}

	

	
	

	


}
