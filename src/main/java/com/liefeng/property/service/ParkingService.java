package com.liefeng.property.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IParkingService;
import com.liefeng.property.bo.parking.ParkingBo;
import com.liefeng.property.domain.parking.ParkingAttachmentContext;
import com.liefeng.property.domain.parking.ParkingContext;
import com.liefeng.property.domain.parking.ParkingRentalContext;
import com.liefeng.property.domain.project.ProjectContext;
import com.liefeng.property.vo.parking.ParkingAttachmentVo;
import com.liefeng.property.vo.parking.ParkingRentalVo;
import com.liefeng.property.vo.parking.ParkingSingleRentalVo;
import com.liefeng.property.vo.parking.ParkingVo;
import com.liefeng.property.vo.project.ProjectVo;

/**
 * 
 * <pre>
 * Title:车位相关表接口类
 * Description:
 * Company:广州列丰科技有限公司
 * @author  wuzhijing
 * @version 1.0      
 * </pre>
 */
@Service
public class ParkingService implements IParkingService {

	private static Logger logger = LoggerFactory.getLogger(ParkingService.class);

	@Override
	public void createParking(ParkingVo parkingVo) {

		ProjectContext projectContext =ProjectContext.loadById(parkingVo.getProjectId());
		ProjectVo projectVo = projectContext.getProject();
		parkingVo.setCode(projectVo.getCode()+parkingVo.getNum());
		
		ParkingContext parkingContext = ParkingContext.build(parkingVo);
		parkingContext.create();
	}
	
	@Override
	public void createManyParking(ParkingVo parkingVo,Integer startNum,Integer endNum) {
		
		ProjectContext projectContext =ProjectContext.loadById(parkingVo.getProjectId());
		ProjectVo projectVo = projectContext.getProject();
		parkingVo.setCode(projectVo.getCode());
		
		ParkingContext parkingContext = ParkingContext.build(parkingVo);
		parkingContext.createMany(startNum,endNum);
	}

	@Override
	public void updateParking(ParkingSingleRentalVo parkingSingleRentalVo) {
		ParkingVo parkingVo = MyBeanUtil.createBean(parkingSingleRentalVo, ParkingVo.class);
		ParkingRentalVo parkingRentalVo = MyBeanUtil.createBean(parkingSingleRentalVo, ParkingRentalVo.class);
		parkingRentalVo.setId(parkingSingleRentalVo.getParkingRentalId());
		
		ParkingContext parkingContext = ParkingContext.build(parkingVo);
		parkingContext.update();
		
		parkingRentalVo.setId(parkingSingleRentalVo.getParkingRentalId());
		parkingRentalVo.setParkingId(parkingVo.getId());
		ParkingRentalContext parkingRentalContext = ParkingRentalContext.build(parkingRentalVo);
		
		if(ValidateHelper.isNotEmptyString(parkingRentalVo.getId())){
			parkingRentalContext.update();
		}else{
			parkingRentalContext.create();
		}
		
		
	}

	@Override
	public void deleteParking(String parkingId) {
		ParkingContext parkingContext = ParkingContext.loadById(parkingId);
		parkingContext.delete();
	}

	@Override
	public ParkingSingleRentalVo getParking(String parkingId) {
		ParkingContext parkingContext = ParkingContext.loadById(parkingId);
		return parkingContext.get();
	}

	@Override
	public DataPageValue<ParkingSingleRentalVo> getParkingList(
			ParkingBo parkingBo, Integer page, Integer size) {
		ParkingContext parkingContext = ParkingContext.build();
		
		return parkingContext.list(parkingBo, page, size);
	}

	@Override
	public void createParkingAttachment(ParkingAttachmentVo attachmentVo) {
		ParkingAttachmentContext attachmentContext = ParkingAttachmentContext.build(attachmentVo);
		attachmentContext.create();
	}

	@Override
	public void deleteParkingAttachment(String id) {
		ParkingAttachmentContext attachmentContext = ParkingAttachmentContext.loadById(id);
		attachmentContext.delete();
	}

	@Override
	public List<ParkingAttachmentVo> getParkingAttachment(String parkingRentalId) {
		ParkingAttachmentContext attachmentContext = ParkingAttachmentContext.build();
		return attachmentContext.get(parkingRentalId);
	}

}
