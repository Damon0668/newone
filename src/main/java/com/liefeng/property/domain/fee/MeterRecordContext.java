package com.liefeng.property.domain.fee;

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
import com.liefeng.common.util.TimeUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.bo.fee.MeterRecordBo;
import com.liefeng.property.constant.FeeConstants;
import com.liefeng.property.error.FeeErrorCode;
import com.liefeng.property.exception.FeeException;
import com.liefeng.property.po.fee.MeterRecordPo;
import com.liefeng.property.po.fee.MeterSettingPo;
import com.liefeng.property.po.project.ProjectBuildingPo;
import com.liefeng.property.repository.MeterRecordRepository;
import com.liefeng.property.repository.MeterSettingRepository;
import com.liefeng.property.repository.ProjectBuildingRepository;
import com.liefeng.property.repository.mybatis.MeterRecordQueryRepository;
import com.liefeng.property.vo.fee.MeterRecordVo;

/**
 * 抄表记录领域模型
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class MeterRecordContext {

	private static Logger logger = LoggerFactory
			.getLogger(MeterRecordContext.class);

	@Autowired
	private MeterRecordRepository meterRecordRepository;

	@Autowired
	private MeterSettingRepository meterSettingRepository;
	
	@Autowired
	private MeterRecordQueryRepository meterRecordQueryRepository;

	@Autowired 
	private ProjectBuildingRepository projectBuildingRepository;
	
	/**
	 * 抄表记录ID
	 */
	private String meterRecordId;

	/**
	 * 抄表记录值对象
	 */
	private MeterRecordVo meterRecord;
	
	private String projectId;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * 
	 * @return 本类实例
	 */
	private static MeterRecordContext getInstance() {
		return SpringBeanUtil.getBean(MeterRecordContext.class);
	}

	/**
	 * 根据抄表记录值对象构建上下文
	 * 
	 * @param meterRecord
	 *            抄表记录值对象
	 * @return 抄表记录上下文
	 */
	public static MeterRecordContext build(MeterRecordVo meterRecord) {
		MeterRecordContext meterRecordContext = getInstance();
		meterRecordContext.setMeterRecord(meterRecord);
		return meterRecordContext;
	}

	/**
	 * 构建上下文（无参）
	 * 
	 * @return 抄表记录上下文
	 */
	public static MeterRecordContext build() {
		MeterRecordContext meterRecordContext = getInstance();

		return meterRecordContext;
	}

	/**
	 * 根据抄表记录ID加载上下文
	 * 
	 * @param meterRecordId
	 *            抄表记录ID
	 * @return 抄表记录上下文
	 */
	public static MeterRecordContext loadById(String meterRecordId) {
		MeterRecordContext meterRecordContext = getInstance();
		meterRecordContext.setMeterRecordId(meterRecordId);
		return meterRecordContext;
	}
	
	/**
	 * 
	 * @param projectId
	 * @return
	 */
	public static MeterRecordContext loadByProjectId(String projectId) {
		MeterRecordContext meterRecordContext = getInstance();
		meterRecordContext.setProjectId(projectId);
		return meterRecordContext;
	}

	/**
	 * 查询抄表记录
	 * 
	 * @return 抄表记录值对象
	 */
	public MeterRecordVo getMeterRecord() {
		if (meterRecord == null) {
			MeterRecordPo meterRecordPo = null;
			if (ValidateHelper.isEmptyString(meterRecordId)) {
				meterRecordPo = meterRecordRepository.findOne(meterRecordId);
			}

			if (meterRecordPo != null) {
				meterRecord = MyBeanUtil.createBean(meterRecordPo,
						MeterRecordVo.class);
			}
		}

		return meterRecord;
	}

	/**
	 * 创建抄表记录
	 */
	public void create() throws LiefengException {
		if (meterRecord != null) {
			//判断当前日期是否可以抄表
			MeterSettingPo meterSettingPo=meterSettingRepository.findByProjectIdAndType(meterRecord.getProjectId(),meterRecord.getMeterType());
			if(meterSettingPo == null){
				throw new FeeException(FeeErrorCode.METERSETTING_ISNULL);
			}

			if(meterSettingPo.getStartDay()>TimeUtil.getCurrentDay()
				||meterSettingPo.getStartDay()+meterSettingPo.getLastingDay()-1<TimeUtil.getCurrentDay()){
				throw new FeeException(FeeErrorCode.METERRECORD_CURRENT_DATE_CANNOT_CREATE);
			}
			//读数大于回程判断
			if(meterRecord.getCurrNum()>meterSettingPo.getModNum()){
				throw new FeeException(FeeErrorCode.METERRECORD_CURRENT_NUMBER_ERRER);
			}
			
			//判断本月是否已抄表
			MeterRecordPo currentMeterRecordPo = meterRecordRepository
					.getPreTypeAndMeterOwner(meterRecord.getProjectId(),
							meterRecord.getHouseNum(),meterRecord.getMeterType(),meterRecord.getMeterOwner(),new Date());
			if(currentMeterRecordPo != null){
				throw new FeeException(FeeErrorCode.METERRECORD_CURRENT_MONTH_EXIST);
			}

			//获取上个月读数操作
			if (meterRecord.getPreNum() == null) {
				Date preDate=TimeUtil.getDayBeforeByMonth(new Date(), 1);
				
				MeterRecordPo preMeterRecordPo = meterRecordRepository
						.getPreTypeAndMeterOwner(meterRecord.getProjectId(),
									meterRecord.getHouseNum(),meterRecord.getMeterType(),meterRecord.getMeterOwner(),preDate);

				if (preMeterRecordPo != null) {
					meterRecord.setPreNum(preMeterRecordPo.getCurrNum());
				} else {
					meterRecord.setPreNum(0.00);
				}		
			}
			
			//用量计算 上次读数大于本期读数将计算回程, 否者按本次读数减上次读数
			if(meterRecord.getPreNum()>meterRecord.getCurrNum()){
				//回程-上次读数+本次读数
				Double useAmount = meterSettingPo.getModNum()-meterRecord.getPreNum()+meterRecord.getCurrNum();
				meterRecord.setUseAmount(useAmount);
			}else{ 
				meterRecord.setUseAmount(meterRecord.getCurrNum()-meterRecord.getPreNum());
			}
			
			meterRecord.setStartDate(TimeUtil.format(TimeUtil.format(TimeUtil.getFirstDayOfCurrMonth(),"yyyy-MM-dd"),"yyyy-MM-dd"));
			meterRecord.setEndDate(TimeUtil.format(TimeUtil.format(TimeUtil.getLastDayOfCurrMonth(),"yyyy-MM-dd"),"yyyy-MM-dd"));
			meterRecord.setId(UUIDGenerator.generate());
			meterRecord.setCreateTime(new Date());
            meterRecord.setOemCode(ContextManager.getInstance().getOemCode());
			MeterRecordPo meterRecordPo = MyBeanUtil.createBean(meterRecord,
					MeterRecordPo.class);
			meterRecordRepository.save(meterRecordPo);
		}
	}
	
	/**
	 * 抄表列表
	 * @return
	 */
	public DataPageValue<MeterRecordVo> listMeterRecordVo4Page(MeterRecordBo meterRecordBo, Integer currentPage,Integer pageSize){
		
		Map<String, String> extra = MyBeanUtil.bean2Map(meterRecordBo);
		
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(currentPage);
		param.setPageSize(pageSize);
		
		Long total = meterRecordQueryRepository.queryByCount(param);
		total = (total == null ? 0 : total);
		logger.info("总数量：total=" + total);
	
		param.getPager().setRowCount(total);
		
		List<MeterRecordVo> meterRecordVos = meterRecordQueryRepository.queryByPage(param);
		return new DataPageValue<MeterRecordVo>(meterRecordVos, total, pageSize, currentPage);
	}
	
	/**
	 * 获取上个期读数
	 * @param date 当前日期
	 * @return
	 */
	public MeterRecordVo getPre(Date date){
		Date preDate=TimeUtil.getDayBeforeByMonth(date, 1);
		MeterRecordPo preMeterRecordPo = new MeterRecordPo();
		if(meterRecord.getMeterOwner().equals(FeeConstants.MeterRecord.METEROWNER_YSE)){
			preMeterRecordPo = meterRecordRepository
				.getPreTypeAndMeterOwner(meterRecord.getProjectId(),
							meterRecord.getHouseNum(),meterRecord.getMeterType(),meterRecord.getMeterOwner(),preDate);
		}else if(meterRecord.getMeterOwner().equals(FeeConstants.MeterRecord.METEROWNER_YSE)){
			preMeterRecordPo = meterRecordRepository
					.getPrePublicTypeAndMeterOwner(meterRecord.getProjectId(),
								meterRecord.getBuildingId(),meterRecord.getMeterType(),meterRecord.getMeterOwner(),preDate);
		}
		return MyBeanUtil.createBean(preMeterRecordPo, MeterRecordVo.class);
	}
	
	/**
	 * 获取上一期某个月的读数
	 * @param houseNum 房间号  查询公摊为空
	 * @param buildingId 楼栋id 查询业主表为空
	 * @param meterType 表类型
	 * @param meterOwner 是否是公摊
	 * @param date 时间
	 * @return
	 */
	public MeterRecordVo get(String houseNum,String buildingId, String meterType,
			String meterOwner, Date date) {
		MeterRecordPo preMeterRecordPo = new MeterRecordPo();
		if(meterOwner.equals(FeeConstants.MeterRecord.METEROWNER_YSE)){
			preMeterRecordPo = meterRecordRepository
				.getPreTypeAndMeterOwner(projectId,
						houseNum,meterType,meterOwner,date);
		}else if(meterOwner.equals(FeeConstants.MeterRecord.METEROWNER_YSE)){
			preMeterRecordPo = meterRecordRepository
					.getPrePublicTypeAndMeterOwner(projectId,
							buildingId,meterType,meterOwner,date);
		}
		return MyBeanUtil.createBean(preMeterRecordPo, MeterRecordVo.class) ;
	}
	
	/**
	 * 根据id获取
	 * @return
	 */
	public MeterRecordVo get(){
		MeterRecordPo meterRecordPo=meterRecordRepository.findOne(meterRecordId);
		return MyBeanUtil.createBean(meterRecordPo, MeterRecordVo.class) ;
	}
	
	public void update(){
		//判断当前日期是否可以抄表
		MeterSettingPo meterSettingPo=meterSettingRepository.findByProjectIdAndType(meterRecord.getProjectId(),meterRecord.getMeterType());
		if(meterSettingPo == null){
			throw new FeeException(FeeErrorCode.METERSETTING_ISNULL);
		}
		
		MeterRecordPo meterRecordPo=meterRecordRepository.findOne(meterRecord.getId());
		meterRecordPo.setCurrNum(meterRecord.getCurrNum());
		meterRecordPo.setReadDate(meterRecord.getReadDate());
		
		//用量计算 上次读数大于本期读数将计算回程, 否者按本次读数减上次读数
		if(meterRecordPo.getPreNum()>meterRecordPo.getCurrNum()){
			//回程-上次读数+本次读数
			Double useAmount = meterSettingPo.getModNum()-meterRecordPo.getPreNum()+meterRecordPo.getCurrNum();
			meterRecordPo.setUseAmount(useAmount);
		}else{ 
			meterRecordPo.setUseAmount(meterRecordPo.getCurrNum()-meterRecordPo.getPreNum());
		}
		
		meterRecordRepository.save(meterRecordPo);
	}
	// TODO 公摊
	
	/**
	 * 公摊抄表列表
	 * @return
	 */
	public DataPageValue<MeterRecordVo> listPublicMeterRecordVo4Page(MeterRecordBo meterRecordBo, Integer currentPage,Integer pageSize){
		
		Map<String, String> extra = MyBeanUtil.bean2Map(meterRecordBo);
		
		PagingParamVo param = new PagingParamVo();
		param.setExtra(extra);
		param.setPage(currentPage);
		param.setPageSize(pageSize);
		
		Long total = meterRecordQueryRepository.queryPublicMeterRecordByCount(param);
		total = (total == null ? 0 : total);
		logger.info("总数量：total=" + total);
	
	//	param.getPager().setRowCount(total);
		
		List<MeterRecordVo> meterRecordVos = meterRecordQueryRepository.queryPublicMeterRecordByPage(param);
		return new DataPageValue<MeterRecordVo>(meterRecordVos, total, pageSize, currentPage);
	}
	
	/**
	 * 创建公摊抄表记录
	 */
	public void createPublic() throws LiefengException {
		if (meterRecord != null) {
			//判断当前日期是否可以抄表
			MeterSettingPo meterSettingPo=meterSettingRepository.findByProjectIdAndType(meterRecord.getProjectId(),meterRecord.getMeterType());
			if(meterSettingPo == null){
				throw new FeeException(FeeErrorCode.METERSETTING_ISNULL);
			}

			if(meterSettingPo.getStartDay()>TimeUtil.getCurrentDay()
				||meterSettingPo.getStartDay()+meterSettingPo.getLastingDay()-1<TimeUtil.getCurrentDay()){
				throw new FeeException(FeeErrorCode.METERRECORD_CURRENT_DATE_CANNOT_CREATE);
			}
			//读数大于回程判断
			if(meterRecord.getCurrNum()>meterSettingPo.getModNum()){
				throw new FeeException(FeeErrorCode.METERRECORD_CURRENT_NUMBER_ERRER);
			}
			
			//判断本月是否已抄表
			MeterRecordPo currentMeterRecordPo = meterRecordRepository
					.getPrePublicTypeAndMeterOwner(meterRecord.getProjectId(),
							meterRecord.getBuildingId(),meterRecord.getMeterType(),meterRecord.getMeterOwner(),new Date());
			if(currentMeterRecordPo != null){
				throw new FeeException(FeeErrorCode.METERRECORD_CURRENT_MONTH_EXIST);
			}

			//获取上个月读数操作
			if (meterRecord.getPreNum() == null) {
				Date preDate=TimeUtil.getDayBeforeByMonth(new Date(), 1);
				
				MeterRecordPo preMeterRecordPo = meterRecordRepository
						.getPrePublicTypeAndMeterOwner(meterRecord.getProjectId(),
									meterRecord.getBuildingId(),meterRecord.getMeterType(),meterRecord.getMeterOwner(),preDate);

				if (preMeterRecordPo != null) {
					meterRecord.setPreNum(preMeterRecordPo.getCurrNum());
				} else {
					meterRecord.setPreNum(0.00);
				}		
			}
			
			//用量计算 上次读数大于本期读数将计算回程, 否者按本次读数减上次读数
			if(meterRecord.getPreNum()>meterRecord.getCurrNum()){
				//回程-上次读数+本次读数
				Double useAmount = meterSettingPo.getModNum()-meterRecord.getPreNum()+meterRecord.getCurrNum();
				meterRecord.setUseAmount(useAmount);
			}else{ 
				meterRecord.setUseAmount(meterRecord.getCurrNum()-meterRecord.getPreNum());
			}
			
			ProjectBuildingPo projectBuildingPo = projectBuildingRepository.findOne(meterRecord.getBuildingId());
			
			meterRecord.setProprietorName(projectBuildingPo.getName());
			meterRecord.setStartDate(TimeUtil.format(TimeUtil.format(TimeUtil.getFirstDayOfCurrMonth(),"yyyy-MM-dd"),"yyyy-MM-dd"));
			meterRecord.setEndDate(TimeUtil.format(TimeUtil.format(TimeUtil.getLastDayOfCurrMonth(),"yyyy-MM-dd"),"yyyy-MM-dd"));
			meterRecord.setId(UUIDGenerator.generate());
			meterRecord.setCreateTime(new Date());
            meterRecord.setOemCode(ContextManager.getInstance().getOemCode());
			MeterRecordPo meterRecordPo = MyBeanUtil.createBean(meterRecord,
					MeterRecordPo.class);
			meterRecordRepository.save(meterRecordPo);
		}
	}
	
	protected void setMeterRecord(MeterRecordVo meterRecord) {
		this.meterRecord = meterRecord;
	}
	
	protected void setMeterRecordId(String meterRecordId) {
		this.meterRecordId = meterRecordId;
	}

	protected void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	


	

}
