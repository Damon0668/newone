package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.bo.guard.GuardDeviceBo;
import com.liefeng.property.bo.guard.GuardResidentBo;
import com.liefeng.property.vo.guard.GuardCardPrivilegeVo;
import com.liefeng.property.vo.guard.GuardCardUserVo;
import com.liefeng.property.vo.guard.GuardCardVo;
import com.liefeng.property.vo.guard.GuardDeviceVo;
import com.liefeng.property.vo.guard.GuardResidentVo;
import com.liefeng.property.vo.household.VisitorVo;

/**
 * 门禁服务
 * 对外暴露接口
 * @author 蔡少东
 * @date 2016年3月1日
 */
public interface IGuardService {
	
	/*
	 * ****************** 门禁设备相关 ******************
	 */
	
	/**
	 * 添加门禁设备
	 * @param guardDevice 门禁设备信息
	 */
	public void createGuardDevice(GuardDeviceVo guardDevice);
	
	/**
	 * 更新门禁设备
	 * @param guardDevice
	 */
	public void updateGuradDevice(GuardDeviceVo guardDevice);
	
	/**
	 * 删除门禁设备
	 * @param guardDeviceIds 门禁设备id列表
	 */
	public void deleteGuradDevice(List<String> guardDeviceIds);
	
	/**
	 * 分页查询门禁设备
	 * @param guardDeviceBo 查询条件
	 * @param page 第几页
	 * @param size 每页数据条数
	 * @return
	 */
	public DataPageValue<GuardDeviceVo> listGuardDevice(GuardDeviceBo guardDeviceBo, int page, int size);
	
	/**
	 * 查找门禁设备
	 * @param guardDeviceId 门禁设备ID
	 * @return
	 */
	public GuardDeviceVo findGuardDevice(String guardDeviceId);
	
	/**
	 * 
	 * @param projectId
	 * @return
	 */
	public List<GuardDeviceVo> findGuardDeviceByProjectId(String projectId);
	
	/**
	 * 检测门禁设备号是否存储
	 * @param guardNum 门禁设备号
	 * @return true 存在 false 不存在
	 */
	public Boolean isExistGuardNum(String guardNum);
	
	/*
	 * ****************** 磁卡相关 ******************
	 */
	
	/**
	 * 查询门禁磁卡
	 * @param cardId 门禁卡ID
	 * @return
	 */
	public GuardCardVo findGuardCard(String cardId);
	
	/**
	 * 查询门禁磁卡用户关系
	 * @param cardId 门禁磁卡ID
	 * @return
	 */
	public GuardCardUserVo findGuardCardUser(String cardId);
	
	/**
	 * 查询门禁磁卡权限
	 * @param cardId 门禁磁卡ID
	 * @return
	 */
	public List<GuardCardPrivilegeVo> findGuardCarPrivilege(String cardId);
	
	/**
	 * 创建门禁卡
	 * @param guardCardUser 门禁卡用户关系
	 * @param guardCard 门禁卡对象
	 * @param guardDeviceIds 门禁授权设备ID
	 */
	public void createGuardCard(GuardCardUserVo guardCardUser, GuardCardVo guardCard, List<String> guardDeviceIds);
	
	/**
	 * 更新门禁卡
	 * @param guardCard
	 * @param guardDeviceIds
	 */
	public void updateGuardCard(GuardCardVo guardCard, List<String> guardDeviceIds);
	
	/**
	 * 磁卡授权
	 * @param guardCardId 磁卡ID
	 * @param guardDeviceId 门口机ID列表
	 */
	public void grantGuardCard(String guardCardId, List<String> guardDeviceId);
	
	/**
	 * 修改门禁卡状态
	 * @param cardId
	 * @param status
	 */
	public void updateGuardCardStatus(String cardId, String status);
	
	/**
	 * 检查门禁卡的sn号是否存在
	 * @param sn
	 * @return true
	 */
	public Boolean isExistCardSn(String sn);
	
	/*
	 * ****************** 访客相关  ******************
	 */
	
	/**
	 * 新增访客信息
	 * @param visitorVo
	 */
	public void createVisitorInfo(VisitorVo visitor);
	
	/*
	 * ****************** 住户相关 ******************
	 */

	/**
	 * 门禁模块
	 * 查询住户列表
	 * @param page 当前页
	 * @param size 每页集合数
	 * @return
	 */
	public DataPageValue<GuardResidentVo> listGuardRedisent(GuardResidentBo guardResidentBo, Integer pageSize, Integer currentPage);
}