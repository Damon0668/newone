package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.bo.parking.ParkingBo;
import com.liefeng.property.vo.parking.ParkingAttachmentVo;
import com.liefeng.property.vo.parking.ParkingSingleRentalVo;
import com.liefeng.property.vo.parking.ParkingVo;

/**
 *	车位管理相关表接口类
 * @author wuzhijing
 *
 */
public interface IParkingService {

	/**
	 * 创建车位
	 */
	public void createParking(ParkingVo parkingVo);
	
	/**
	 * 修改车位信息
	 */
	public void updateParking(ParkingSingleRentalVo parkingSingleRentalVo);
	
	/**
	 *	删除车位
	 */
	public void deleteParking(String parkingId);
	
	/**
	 * 获取车位详情
	 */
	public ParkingSingleRentalVo getParking(String parkingId);
	
	/**
	 * 获取车位信息列表
	 * @param parkingBo
	 * @return
	 */
	public DataPageValue<ParkingSingleRentalVo> getParkingList(ParkingBo parkingBo,Integer page, Integer size);

	/**
	 * 批量创建车位
	 * @param parkingVo
	 * @param startNum
	 * @param endNum
	 */
	public void createManyParking(ParkingVo parkingVo, Integer startNum, Integer endNum);

	/**
	 * 创建附件
	 */
	public void createParkingAttachment(ParkingAttachmentVo attachmentVo);
	
	/**
	 * 删除附件
	 */
	public void deleteParkingAttachment(String id);
	
	/**
	 * 根据租售信息id查询附件
	 */
	public List<ParkingAttachmentVo> getParkingAttachment(String parkingRentalId);
}
