package com.liefeng.property.domain.fee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import com.liefeng.property.po.fee.MeterRecordPo;
import com.liefeng.property.repository.MeterRecordRepository;
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

	/**
	 * 抄表记录ID
	 */
	private String meterRecordId;

	/**
	 * 抄表记录值对象
	 */
	private MeterRecordVo meterRecord;
	protected void setMeterRecord(MeterRecordVo meterRecord) {
		this.meterRecord = meterRecord;
	}

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
		meterRecordContext.meterRecordId = meterRecordId;

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
	 * 保存抄表记录
	 */
	public void create() {
		if (meterRecord != null) {
			//获取上个月读数操作
			if (meterRecord.getPreNum() == null) {
				  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
				Calendar calendar=Calendar.getInstance();
				calendar.add(calendar.MONTH, -1);
				
				MeterRecordPo pre_meterRecordPo;
				try {
					pre_meterRecordPo = meterRecordRepository
							.getPreMeterRecord(meterRecord.getProjectId(),
									meterRecord.getHouseNum(),sdf.parse(sdf.format(calendar.getTime())));

					if (pre_meterRecordPo != null) {
						meterRecord.setPreNum(pre_meterRecordPo.getCurrNum());
					} else {
						meterRecord.setPreNum(0.00);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
			meterRecord.setId(UUIDGenerator.generate());
			meterRecord.setCreateTime(new Date());
            meterRecord.setOemCode("1");
			MeterRecordPo meterRecordPo = MyBeanUtil.createBean(meterRecord,
					MeterRecordPo.class);
			meterRecordRepository.save(meterRecordPo);
		}
	}

}
