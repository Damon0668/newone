package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.base.vo.UserVo;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.property.bo.household.CheckinQueueBo;
import com.liefeng.property.bo.household.ProprietorBo;
import com.liefeng.property.bo.household.ResidentBo;
import com.liefeng.property.bo.household.ResidentFeedbackBo;
import com.liefeng.property.vo.household.AppFriendVo;
import com.liefeng.property.vo.household.AppMsgSettingVo;
import com.liefeng.property.vo.household.CheckinMaterialVo;
import com.liefeng.property.vo.household.CheckinQueueVo;
import com.liefeng.property.vo.household.CheckinScheduleVo;
import com.liefeng.property.vo.household.ProprietorHouseVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ProprietorVo;
import com.liefeng.property.vo.household.ResidentCarVo;
import com.liefeng.property.vo.household.ResidentFeedbackVo;
import com.liefeng.property.vo.household.ResidentHouseVo;
import com.liefeng.property.vo.household.ResidentVo;
import com.liefeng.property.vo.household.UserClientIdVo;
import com.liefeng.property.vo.household.VisitorVo;

/**
 * household包相关表接口类
 * 
 * @author ZhenTingJun
 * @date 2015-12-28
 */
public interface IHouseholdService {
	/**
	 * 保存业主信息
	 * 
	 * @param singleHouse 业主单个房产信息对象
	 */
	public void saveProprietor(ProprietorSingleHouseVo singleHouse) throws LiefengException;
	
	/**
	 * 更新业主信息
	 * 
	 * @param singleHouse 业主单个房产信息对象
	 */
	public void updatePropritor(ProprietorSingleHouseVo singleHouse) throws LiefengException;
	
	/**
	 * 保存住户信息
	 * @param resident 住户信息值对象
	 */
	public void saveResident(ResidentVo resident) throws LiefengException;
	
	/**
	 * 更新住户信息
	 * @param resident 住户信息值对象
	 */
	public void updateResident(ResidentVo resident) throws LiefengException;
	
	/**
	 * 分页查询业主综合信息
	 * 
	 * @param params 查询参数封装类对象
	 * @param pageSize 分页大小
	 * @param currentPage 分页当前页
	 * @return 业主分页数据
	 */
	public DataPageValue<ProprietorSingleHouseVo> listProprietorSingleHouse4Page(ProprietorBo params, Integer pageSize,
			Integer currentPage);
	
	/**
	 * 获取业主某个房产的信息
	 * @param proprietorHouseId 业主房产ID
	 * @return 业主房产信息
	 */
	public ProprietorSingleHouseVo getProprietorSingleHouse(String proprietorHouseId);
	
	/**
	 * 分页查询住户信息
	 * @param params 查询参数封装类对象
	 * @param pageSize 分页大小
	 * @param currentPage 分页当前页
	 * @return 住户分页信息
	 */
	public DataPageValue<ResidentVo> listResident4Page(ResidentBo params, Integer pageSize, Integer currentPage);
	
	/**
	 * 查询住户信息
	 * @param residentId 住户ID
	 * @param houseId 房子ID
	 * @return 住户信息
	 */
	public ResidentVo getResident(String residentId, String houseId);
	
	/**
	 * 根据项目ID和房号查询业主房产信息
	 * @param projectId 项目ID
	 * @param houseNum 房号
	 * @return 业主房产信息
	 */
	public ProprietorHouseVo getProprietorHouse(String projectId, String houseNum);
	
	/**
	 * 根据业主房产ID查询入住资料信息
	 * @param proprietorHouseId 业主房产ID
	 * @return 入住资料列表
	 */
	public List<CheckinMaterialVo> getMaterialByProprietorHouseId(String proprietorHouseId);
	
	/**
	 * 批量保存入住资料
	 * @param checkinMaterialList
	 */
	public void saveCheckinMaterials(List<CheckinMaterialVo> checkinMaterialList) throws Exception;
	
	/**
	 * 根据业主房产ID删除入住资料信息
	 * @param proprietorHouseId 业主房产ID
	 */
	public void delMaterialByProprietorHouseId(String proprietorHouseId) throws Exception;
	
	/**
	 * 根据业主ID获取业主信息
	 * @param id 业主ID
	 * @return 业主信息
	 */
	public ProprietorVo getProprietorById(String id);
	
	/**
	 * 分页查询业主用户信息
	 * @param params 查询过滤参数
	 * @param currentPage 分页当前页
	 * @param pageSize 分页大小
	 * @return
	 */
	public DataPageValue<UserVo> listUsers(ProprietorBo params, Integer currentPage, Integer pageSize);
	
	/**
	 * 获取账号关联房子中的住户或业主
	 * @param projectId 项目ID
	 * @param custGlobalId 业主关联客户全局ID
	 * @return 住户或业主信息列表
	 */
	public List<ResidentVo> queryRelatedHouses(String projectId, String custGlobalId);
	
	/**
	 * 分页查询入住排队信息
	 * @param params 查询过滤参数
	 * @param pageSize 分页大小
	 * @param currentPage 分页当前页
	 * @return 入住排队分页数据
	 */
	public DataPageValue<CheckinQueueVo> getCheckinQueues(CheckinQueueBo params, Integer pageSize, Integer currentPage);
	
	/**
	 * 更新入住排队信息
	 * @param checkinQueue 入住排队值对象
	 * @throws LiefengException
	 */
	public void updateCheckinQueue(CheckinQueueVo checkinQueue) throws LiefengException;
	
	/**
	 * 根据项目查询入住安排时间
	 * @param projectId 项目ID
	 * @return 入住安排时间列表
	 */
	public List<CheckinScheduleVo> getScheduleByProjectId(String projectId);
	
	/**
	 * 批量保存入住安排时间
	 * @param projectId 项目ID
	 * @param checkinScheduleList 入住安排时间列表
	 */
	public void saveCheckinSchedule(String projectId, List<CheckinScheduleVo> checkinScheduleList);
	
	/**
	 * 根据项目id、楼栋id，获取入住安排时间
	 * @param projectId 项目id
	 * @param buildingId 楼栋id
	 * @return 
	 * @author xhw
	 * @date 2016年3月8日 下午1:40:18
	 */
	public CheckinScheduleVo getCheckinSchedule(String projectId, String buildingId);
	
	/**
	 * 根据项目id、楼栋id，手机端用户id，创建排队号
	 * @param projectId 项目id
	 * @param buildingId 楼栋id
	 * @param userId 手机端用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月8日 下午2:27:21
	 */
	public CheckinQueueVo createCheckinQueue(String projectId, String houseId, String userId) throws LiefengException;
	
	/**
	 * 创建入住排队信息（PC端使用）
	 * @param checkinQueue 入住排队值对象
	 * @return 入住排队信息
	 * @throws LiefengException
	 */
	public CheckinQueueVo createCheckinQueue(CheckinQueueVo checkinQueue) throws LiefengException;
	
	/**
	 * 获取今天用户某状态的排队
	 * @param userId 手机端用户id
	 * @param projectId 项目id
	 * @param houseId 房间id
	 * @param status 状态
	 * @param queryDate 查询时间
	 * @return 
	 * @author xhw
	 * @date 2016年3月8日 下午4:23:31
	 */
	public CheckinQueueVo getCheckinQueueOfToday(String userId, String projectId, String houseId, String status, String queryDate);
	
	/**
	 * 通过项目id、日期，获取这一天的所有排队
	 * @param projectId 项目id
	 * @param queryDate 日期
	 * @return 
	 * @author xhw
	 * @date 2016年3月8日 下午7:42:22
	 */
	public List<CheckinQueueVo> getAllOfTody(String projectId, String queryDate);
	
	/**
	 * 根据项目id，房间id，手机用户id，获取排队（用于app接口）
	 * @param projectId 项目id
	 * @param houseId 房间id
	 * @param userId 手机端用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月8日 下午8:24:12
	 */
	public CheckinQueueVo getCheckinQueue(String projectId, String houseId, String userId) throws LiefengException;
	
	/**
	 * 根据手机端用户id，项目id，房间id，状态，获取最近一条排队
	 * @param userId 手机端用户id
	 * @param projectId 项目id
	 * @param houseId 房间id
	 * @param status 状态
	 * @return 
	 * @author xhw
	 * @date 2016年3月8日 下午8:30:57
	 */
	public CheckinQueueVo getCheckinQueueOfStatus(String userId, String projectId, String houseId, String status);
	
	/**
	 * 根据项目id、状态，获取今天最新的该状态的排队
	 * @param projectId 项目id
	 * @param status 状态
	 * @param queryDate 时间
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 上午10:04:02
	 */
	public CheckinQueueVo getLatestOfCheckinQueue(String projectId, String status, String queryDate);
	
	/**
	 * 根据项目id、状态、时间，获取非此状态的排队(分页）
	 * @param projectId 项目id
	 * @param status 状态
	 * @param queryDate 时间
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 上午10:51:54
	 */
	public DataPageValue<CheckinQueueVo> getCheckinQueueOfNotStatus(String projectId, String status, String queryDate, Integer page, Integer size);
	
	/**
	 * 判断业主是否已经登记入住
	 * @param proprietorId 业主id
	 * @param userId 手机端用户id
	 * @param projectId 项目id
	 * @param houseId 房间id
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 下午4:18:37
	 */
	public void checkProrietorStatus(String proprietorId, String userId, String projectId, String houseId) throws LiefengException;
	
	/**
	 * 业主情况登记
	 * @param singleHouse 
	 * @author xhw
	 * @date 2016年3月9日 下午5:44:31
	 */
	public void registerProprietor(ProprietorSingleHouseVo singleHouse) throws LiefengException;
	
	/**
	 * 根据业主id，获取业主的登记情况
	 * @param proprietorId
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 下午8:00:19
	 */
	public ProprietorSingleHouseVo getProprietorOfRegister(String proprietorId);
	
	
	/**
	 * 创建用户反馈
	 * @param residentFeedbackVo
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 上午10:36:09
	 */
	public ResidentFeedbackVo createResidentFeedback(ResidentFeedbackVo residentFeedbackVo);
	
	/**
	 * 创建用户手机端消息设置
	 * @param appMsgSettingVo
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 下午1:47:40
	 */
	public AppMsgSettingVo createAppMsgSetting(AppMsgSettingVo appMsgSettingVo);
	
	/**
	 * 根据用户id，获取用户手机端消息设置
	 * @param userId
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 下午1:48:30
	 */
	public AppMsgSettingVo getAppMsgSetting(String userId);
	
	/**
	 * 更新用户手机端消息设置
	 * @param appMsgSettingVo
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 下午1:49:21
	 */
	public AppMsgSettingVo updateAppMsgSetting(AppMsgSettingVo appMsgSettingVo);
	
	/**
	 * 查询用户反馈（分页）
	 * @param params 查询过滤参数
	 * @param currentPage 当前页
	 * @param pageSize 页大小
	 * @return 
	 * @author xhw
	 * @date 2016年3月15日 下午2:32:26
	 */
	public DataPageValue<ResidentFeedbackVo> getResidentFeedbackPage(ResidentFeedbackBo params, Integer currentPage, Integer pageSize);
	
	/**
	 * 创建好友
	 * @param appFriendVo
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午3:59:25
	 */
	public AppFriendVo createAppFriend(AppFriendVo appFriendVo);
	
	/**
	 * 删除好友
	 * @param userId 用户id
	 * @param friendId  好友id
	 * @author xhw
	 * @date 2016年3月16日 下午4:01:24
	 */
	public void deleteAppFriend(String userId, String friendId);
	
	/**
	 * 根据用户id，获取用户的好友列表
	 * @param userId 用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午4:02:30
	 */
	public List<AppFriendVo> getAppFriendList(String userId);
	
	/**
	 * 更新手机好友申请状态
	 * @param id
	 * @param status 
	 * @author xhw
	 * @date 2016年3月16日 下午5:06:08
	 */
	public void updateAppFriend(String id, String status);
	
	/**
	 * 查询用户（通讯录）
	 * @param userId 用户id
	 * @param condition 过滤条件
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午8:31:54
	 */
	public List<AppFriendVo> getUserList(String userId, String condition);
	
	/**
	 * 根据用户id，获取好友操作历史
	 * @param userId 用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月17日 上午10:57:37
	 */
	public List<AppFriendVo> getAppFriendHistoryList(String userId);
	
	/**
	 * 根据用户id，好友id、状态，获取记录
	 * @param userId 用户id
	 * @param friendId 好友id
	 * @param status 状态
	 * @return 
	 * @author xhw
	 * @date 2016年3月17日 下午1:42:10
	 */
	public AppFriendVo getAppFriend(String userId, String friendId, String status);
	
	/**
	 * 根据住户id，房间id获取residentHouse
	 * @param residentId 住户id
	 * @param houseId 房间id
	 * @return 
	 * @author xhw
	 * @date 2016年3月17日 下午5:34:52
	 */
	public ResidentHouseVo getResidentHouse(String residentId, String houseId);
	
	/**
	 * 根据项目id和业主项目查询业主信息
	 * @param projectId 项目id
	 * @param proprietorName 业主姓名
	 * @return 业主信息
	 */
	public ProprietorVo findProprietor(String projectId, String proprietorName);
	
	/***
	 * 创建业主车俩
	 * @param residentCarVo
	 */
	public void createResidentCar(ResidentCarVo residentCarVo);
	
	/**
	 * 修改业主车俩
	 * @param residentCarVo
	 */
	public void updateResidentCar(ResidentCarVo residentCarVo);

	/**
	 * 根据车位id查询车俩信息
	 * @param pakingId 车位id
	 * @return
	 */
	public List<ResidentCarVo> findResidentCarByPakingId(String pakingId);
	
	/**
	 * 添加访客
	 * @param visitorVo
	 * @return 
	 * @author xhw
	 * @date 2016年3月23日 上午11:42:11
	 */
	public VisitorVo createVisitor(VisitorVo visitorVo);
	
	/**
	 * 获取用户的访客列表
	 * @param userId
	 * @return 
	 * @author xhw
	 * @date 2016年3月23日 下午2:53:58
	 */
	public List<VisitorVo> getVisitorList(String userId);
	
	/**
	 * 获取访客的访问记录
	 * @param phone 手机号码
	 * @return 
	 * @author xhw
	 * @date 2016年3月23日 下午3:22:36
	 */
	public List<VisitorVo> getVisitorHistory(String phone);
	
	/**
	 * 获取访客的信息
	 * @param visitorId
	 * @return 
	 * @author xhw
	 * @date 2016年3月23日 下午4:07:12
	 */
	public VisitorVo getVisitor(String visitorId);
	
	/**
	 * 通过房产id获取车俩信息
	 * @param houseId
	 * @return
	 */
	public List<ResidentCarVo> findResidentCarByHouseId(String houseId);
	
	/**
	 * 根据ID查询住户车辆信息
	 * @param carId 住户车辆ID
	 * @return 住户车辆信息
	 */
	public ResidentCarVo findResidentCarById(String carId);
	
	/**
	 * 根据projectId、buildingId获取用户的clientId
	 * @param buildingId
	 * @param projectId
	 * @return 
	 * @author xhw
	 * @date 2016年4月9日 下午1:34:03
	 */
	List<UserClientIdVo> listClientIdByBuildingIdAndProjectId(String buildingId, String projectId);
	
	/**
	 * 根据projectId获取用户的cleintId
	 * @param projectId
	 * @return 
	 * @author xhw
	 * @date 2016年4月11日 下午5:08:26
	 */
	public List<UserClientIdVo> listClientIdByProjectId(String projectId);
	
	/**
	 * 根据手机号码，推送消息给用户（业主、住户）
	 * @param phone 
	 * @author xhw
	 * @date 2016年4月12日 上午9:44:52
	 */
	public void pushMsgToUserByPhone(String phone);
	
	/**
	 * 根据projectId、houseNum获取用户的cleintId
	 * @param projectId
	 * @param houseNum
	 * @return 
	 * @author xhw
	 * @date 2016年4月12日 下午2:54:21
	 */
	public List<UserClientIdVo> listClientIdByProjectIdAndHouseNum(String projectId, String houseNum);
	
	/**
	 * 根据projectId、status、seq、日期，获取当天大于该排队号的排队
	 * @param projectId
	 * @param status
	 * @param seq
	 * @param queryDate
	 * @return 
	 * @author xhw
	 * @date 2016年4月12日 下午6:14:37
	 */
	public CheckinQueueVo getCheckinQueueMoreThanSeq(String projectId, String status, Integer seq, String queryDate);
	
	/**
	 * 根据projectId、houseNum获取业主资料信息
	 * @param projectId
	 * @param houseNum
	 * @return 
	 * @author xhw
	 * @date 2016年4月14日 上午10:13:00
	 */
	public ProprietorSingleHouseVo findProprietorSingleHouseVo(String projectId, String houseNum);
	
	/**
	 * 分页查询访客
	 * @param projectId
	 * @param name
	 * @param phone
	 * @param page
	 * @param size
	 * @return 
	 * @author xhw
	 * @date 2016年4月14日 下午3:05:01
	 */
	public DataPageValue<VisitorVo> findVisitorByPage(String projectId, String manageProjectIds, String name, String phone, Integer page, Integer size);
	
	/**
	 * 更新访客信息
	 * @param visitorVo
	 * @return 
	 * @author xhw
	 * @date 2016年4月15日 下午6:01:44
	 */
	public VisitorVo updateVisitor(VisitorVo visitorVo);
	
}
