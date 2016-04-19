package com.liefeng.property.domain.household;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.po.household.AppFriendPo;
import com.liefeng.property.repository.household.AppFriendRepository;
import com.liefeng.property.repository.mybatis.AppFriendQueryRepository;
import com.liefeng.property.vo.household.AppFriendVo;


/**
 * 手机端好友领域上下文对象
 * @author xhw
 * @date 2016年3月16日 下午2:04:15
 */
@Service
@Scope("prototype")
public class AppFriendContext {
	
	private static Logger logger = LoggerFactory.getLogger(AppFriendContext.class);
	
	@Autowired
	private AppFriendRepository appFriendRepository;
	
	@Autowired
	private AppFriendQueryRepository appFriendQueryRepository;
	
	
	/**
	 * 客户值对象
	 */
	private AppFriendVo appFriendVo;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static AppFriendContext getInstance() {
		return SpringBeanUtil.getBean(AppFriendContext.class);
	}
	
	/**
	 * 通过手机好友对象，构建好友上下文
	 * @param appFriendVo 手机好友
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午2:07:30
	 */
	public static AppFriendContext build(AppFriendVo appFriendVo) {
		AppFriendContext appFriendContext = getInstance();
		appFriendContext.setAppFriendVo(appFriendVo);
		
		return appFriendContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 消息设置上下文
	 */
	public static AppFriendContext build() {
		AppFriendContext appFriendContext = getInstance();
		
		return appFriendContext;
	}
	
	/**
	 * 根据用户id，构建好友上下文对象
	 * @param userId 用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 上午11:49:29
	 */
	public static AppFriendContext loadByUserId(String userId){
		AppFriendContext appFriendContext = getInstance();
		appFriendContext.setUserId(userId);
		
		return appFriendContext;
	}
	
	
	/**
	 * 创建好友
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午2:44:19
	 */
	public AppFriendVo create() {
		if (appFriendVo != null) {
			appFriendVo.setId(UUIDGenerator.generate());
			appFriendVo.setOemCode(ContextManager.getInstance().getOemCode());

			AppFriendPo appFriendPo = MyBeanUtil.createBean(appFriendVo, AppFriendPo.class);
			appFriendRepository.save(appFriendPo);

			logger.info("Create appFriendPo : {} success.", appFriendPo);
		}

		return appFriendVo;
	}
	
	/**
	 * 修改好友
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午2:49:39
	 */
	public AppFriendVo update() {
		if (appFriendVo != null && ValidateHelper.isNotEmptyString(appFriendVo.getId())) {
			AppFriendPo appFriendPo = appFriendRepository.findOne(appFriendVo.getId());

			MyBeanUtil.copyBeanNotNull2Bean(appFriendVo, appFriendPo);
			appFriendPo.setUpdateTime(new Date());
			appFriendRepository.save(appFriendPo);

			logger.info("Update  appFriendVo of id: {} success.", appFriendVo.getId());
			
			appFriendVo = MyBeanUtil.createBean(appFriendPo, AppFriendVo.class);
		}

		return appFriendVo;
	}

	/**
	 * 删除好友
	 * @param userId 用户id
	 * @param friendId 好友id
	 * @author xhw
	 * @date 2016年3月16日 下午3:47:12
	 */
	public void delete(String userId, String friendId){
		if(ValidateHelper.isNotEmptyString(userId) && ValidateHelper.isNotEmptyString(friendId)){
			appFriendRepository.deleteByUserIdAndFriendId(userId, friendId);
			
			logger.info("Update  appFriend of userId {1} and friendId {2} : success.", userId, friendId);
		}
	}
	
	/**
	 * 删除某状态的好友
	 * @param userId
	 * @param friendId
	 * @param status 
	 * @author xhw
	 * @date 2016年3月16日 下午8:54:21
	 */
	public void deleteOfStatus(String userId, String friendId, String status){
		if(ValidateHelper.isNotEmptyString(userId) && ValidateHelper.isNotEmptyString(friendId) &&ValidateHelper.isNotEmptyString(status)){
			appFriendRepository.deleteByUserIdAndFriendIdAndStatus(userId, friendId, status);
			
			logger.info("Update  appFriend of userId {1} and friendId {2} and status {3}: success.", userId, friendId, status);
		}
	}
	
	/**
	 * 根据用户id,获取用户的好友列表
	 * @param userId 用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午3:57:01
	 */
	public List<AppFriendVo> getAppFriendList(String userId){
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", userId);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);
		
		List<AppFriendVo> appFriendVoList = appFriendQueryRepository.queryFriendList(param);
		
		return appFriendVoList;
	}
	
	/**
	 * 查询用户（通讯录）
	 * @param userId 用户id
	 * @param condition 过滤条件
	 * @return 
	 * @author xhw
	 * @date 2016年3月16日 下午8:31:54
	 */
	public List<AppFriendVo> getUserList(String userId, String condition){
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", userId);
		paramMap.put("condition", condition);
		paramMap.put("oemCode", ContextManager.getInstance().getOemCode());

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);
		
		List<AppFriendVo> appFriendVoList = appFriendQueryRepository.queryUserList(param);
		
		return appFriendVoList;
	}
	
	/**
	 * 根据用户id，获取好友操作历史
	 * @param userId 用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月17日 上午10:57:37
	 */
	public List<AppFriendVo> getAppFriendHistoryList(String userId){
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", userId);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);
		
		List<AppFriendVo> appFriendVoList = appFriendQueryRepository.queryFriendHistoryList(param);
		
		return appFriendVoList;
	}
	
	/**
	 * 根据用户id，好友id、状态，获取记录
	 * @param userId 用户id
	 * @param friendId 好友id
	 * @param status 状态
	 * @return 
	 * @author xhw
	 * @date 2016年3月17日 下午1:42:10
	 */
	public AppFriendVo getAppFriend(String userId, String friendId, String status){
		AppFriendVo appFriendVo = null;
		if(ValidateHelper.isNotEmptyString(userId) && ValidateHelper.isNotEmptyString(friendId) &&ValidateHelper.isNotEmptyString(status)){
			AppFriendPo appFriendPo = appFriendRepository.findByUserIdAndFriendIdAndStatus(userId, friendId, status);
			
			appFriendVo = MyBeanUtil.createBean(appFriendPo, AppFriendVo.class);
		}
		return appFriendVo;
	}
	protected void setAppFriendVo(AppFriendVo appFriendVo) {
		this.appFriendVo = appFriendVo;
	}

	protected void setUserId(String userId) {
		this.userId = userId;
	}
	
}
