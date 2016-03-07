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
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.property.domain.project.HouseContext;
import com.liefeng.property.po.parking.ParkingAttachmentPo;
import com.liefeng.property.repository.ParkingAttachmentRepository;
import com.liefeng.property.vo.parking.ParkingAttachmentVo;
import com.liefeng.property.vo.project.HouseVo;

/**
 * 车位租售附件领域模型
 * 
 * @author wuzhijing
 */
@Service
@Scope("prototype")
public class ParkingAttachmentContext {

	private static Logger logger = LoggerFactory
			.getLogger(ParkingAttachmentContext.class);

	@Autowired
	private ParkingAttachmentRepository parkingAttachmentRepository;

	/**
	 * 车位信息ID
	 */
	private String parkingAttachmentId;

	/**
	 * 车位信息值对象
	 */
	private ParkingAttachmentVo parkingAttachmentVo;

	/**
	 * 获取本类实例，每次返回一个新的对象
	 * 
	 * @return 本类实例
	 */
	private static ParkingAttachmentContext getInstance() {
		return SpringBeanUtil.getBean(ParkingAttachmentContext.class);
	}

	/**
	 * 值对象构建上下文
	 */
	public static ParkingAttachmentContext build(
			ParkingAttachmentVo parkingAttachmentVo) {
		ParkingAttachmentContext parkingAttachmentContext = getInstance();
		parkingAttachmentContext.setParkingAttachmentVo(parkingAttachmentVo);

		return parkingAttachmentContext;
	}

	/**
	 * 构建上下文（无参）
	 */
	public static ParkingAttachmentContext build() {
		ParkingAttachmentContext parkingAttachmentContext = getInstance();

		return parkingAttachmentContext;
	}

	/**
	 * ID加载上下文
	 */
	public static ParkingAttachmentContext loadById(String parkingAttachmentId) {
		ParkingAttachmentContext parkingAttachmentContext = getInstance();
		parkingAttachmentContext.setParkingAttachmentId(parkingAttachmentId);

		return parkingAttachmentContext;
	}

	public void delete() {
		logger.info("delete parkingAttachment id :{}", parkingAttachmentId);
		parkingAttachmentRepository.delete(parkingAttachmentId);
	}

	public void create(){
		if(parkingAttachmentVo!=null){
			parkingAttachmentVo.setId(UUIDGenerator.generate());
			parkingAttachmentRepository.save(MyBeanUtil.createBean(parkingAttachmentVo, ParkingAttachmentPo.class));
		}
	}
	
	public List<ParkingAttachmentVo> get(String parkingRentalId){
		List<ParkingAttachmentPo> attachmentPos = parkingAttachmentRepository.findByParkingRentalId(parkingRentalId);
		return MyBeanUtil.createList(attachmentPos, ParkingAttachmentVo.class);
	}

	protected void setParkingAttachmentId(String parkingAttachmentId) {
		this.parkingAttachmentId = parkingAttachmentId;
	}

	public void setParkingAttachmentVo(ParkingAttachmentVo parkingAttachmentVo) {
		this.parkingAttachmentVo = parkingAttachmentVo;
	}
}
