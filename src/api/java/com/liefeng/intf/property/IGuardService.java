package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.property.bo.guard.DevicePositionBo;
import com.liefeng.property.bo.guard.GuardCardBo;
import com.liefeng.property.bo.guard.GuardDeviceBo;
import com.liefeng.property.bo.guard.GuardOpenLogBo;
import com.liefeng.property.bo.guard.GuardPRUserBo;
import com.liefeng.property.vo.guard.AttendantVo;
import com.liefeng.property.vo.guard.DevicePositionVo;
import com.liefeng.property.vo.guard.GuardCardLogVo;
import com.liefeng.property.vo.guard.GuardCardPrivilegeVo;
import com.liefeng.property.vo.guard.GuardCardUserVo;
import com.liefeng.property.vo.guard.GuardCardVo;
import com.liefeng.property.vo.guard.GuardDeviceVo;
import com.liefeng.property.vo.guard.GuardOpenLogVo;
import com.liefeng.property.vo.guard.GuardPRUserVo;
import com.liefeng.property.vo.household.VisitorVo;

/**
 * 门禁服务
 * 对外暴露接口
 * @author 蔡少东
 * @date 2016年3月1日
 */
public interface IGuardService {
	
	/*
	 * ****************** 设备位置相关 ******************
	 */
	/**
	 * 添加设备位置
	 * @param devicePosition
	 */
	public void createDevicePosition(DevicePositionVo devicePosition) throws LiefengException;
	
	/**
	 * 更新设备位置
	 * @param devicePosition
	 */
	public void updateDevicePosition(DevicePositionVo devicePosition) throws LiefengException;;
	
	/**
	 * 删除设备位置
	 * @param ID
	 */
	public void deleteDevicePosition(String id);
	
	/**
	 * 查询项目位置
	 * @param devicePositionBo 
	 * @return
	 */
	public List<DevicePositionVo> findDevicePosition(DevicePositionBo devicePositionBo);
	
	/**
	 * 查询项目位置
	 * @param projectId 项目ID
	 * @param page 
	 * @param size
	 * @return 
	 */
	public DataPageValue<DevicePositionVo> findDevicePosition(DevicePositionBo devicePositionBo, Integer page, Integer size);
	
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
	
	/**查找门禁设备
	 * @param projectId
	 * @param type
	 * @return
	 */
	public List<GuardDeviceVo> findGuardDevice(String projectId, String type);
	
	/**查找门禁设备
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
	public DataPageValue<GuardPRUserVo> listGuardPRUser(GuardPRUserBo guardResidentBo, Integer currentPage, Integer pageSize);
	
	/**
	 * 分页查询磁卡信息列表数据
	 * @param guardCardBo 查询参数
	 * @param pageSize 分页大小
	 * @param currentPage 分页当前页
	 * @return 列表数据
	 */
	public DataPageValue<GuardCardVo> listGuardCard(GuardCardBo guardCardBo, Integer pageSize, Integer currentPage);
	
	/**
	 * 获取磁卡详细信息
	 * @param cardId 磁卡ID
	 * @return 磁卡信息
	 */
	public GuardCardVo getGuardCardDetail(String cardId);
	
	/**
	 * 分页查询磁卡日志
	 * @param cardId 磁卡ID
	 * @param currentPage 分页当前页
	 * @param pageSize 分页大小
	 * @return 磁卡日志分页数据
	 */
	public DataPageValue<GuardCardLogVo> listGuardCardLog(String cardId, Integer currentPage, Integer pageSize);
	
	/**
	 * 创建服务人员
	 * @param attendantVo
	 * @return 
	 * @author xhw
	 * @date 2016年4月19日 下午3:44:40
	 */
	public AttendantVo createAttendant(AttendantVo attendantVo);
	
	/**
	 * 获取服务人员信息
	 * @param id
	 * @return 
	 * @author xhw
	 * @date 2016年4月19日 下午3:47:50
	 */
	public AttendantVo getAttendant(String id);
	
	/**
	 * 获取服务人员列表
	 * @param projectId 项目id
	 * @param manageProjectIds 员工所管理的项目
	 * @param name 名称
	 * @param phone 手机号码
	 * @param serviceType 服务类型
	 * @param page
	 * @param size
	 * @return 
	 * @author xhw
	 * @date 2016年4月19日 下午4:03:52
	 */
	public DataPageValue<AttendantVo> listAttendants(String projectId, String manageProjectIds, String name, String phone, String serviceType, Integer page, Integer size);
	
	/**
	 * 更新服务人员信息
	 * @param attendantVo
	 * @return 
	 * @author xhw
	 * @date 2016年4月19日 下午5:45:36
	 */
	public AttendantVo updateAttendant(AttendantVo attendantVo);
	
	/**
	 * 根据磁卡ID查询授权设备
	 * @param cardId 磁卡ID
	 * @return 授权设备列表
	 * @author ZhenTingJun
	 * @date 2016年4月20日
	 */
	public List<GuardDeviceVo> listPrivilegeDevice(String cardId);
	
	/**
	 * 分页查询开门日志
	 * @param params 查询过滤参数
	 * @param currentPage 分页当前页
	 * @param pageSize 分页大小
	 * @return 开门日志
	 * @author ZhenTingJun
	 * @date 2016年4月28日
	 */
	public DataPageValue<GuardOpenLogVo> listGuardOpenLog(GuardOpenLogBo params, Integer currentPage, Integer pageSize);
}