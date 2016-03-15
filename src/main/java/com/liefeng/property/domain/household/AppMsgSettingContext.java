package com.liefeng.property.domain.household;

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
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.po.household.AppMsgSettingPo;
import com.liefeng.property.po.household.ResidentFeedbackPo;
import com.liefeng.property.po.workbench.TaskPo;
import com.liefeng.property.repository.AppMsgSettingRepository;
import com.liefeng.property.vo.household.AppMsgSettingVo;
import com.liefeng.property.vo.workbench.TaskVo;


/**
 * 用户手机端消息设置领域上下文
 *  
 * @author xhw
 * @date 2016年3月14日 上午11:42:51
 */
@Service
@Scope("prototype")
public class AppMsgSettingContext {
	
	private static Logger logger = LoggerFactory.getLogger(AppMsgSettingContext.class);
	
	@Autowired
	private AppMsgSettingRepository appMsgSettingRepository;
	
	
	/**
	 * 客户值对象
	 */
	private AppMsgSettingVo appMsgSettingVo;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static AppMsgSettingContext getInstance() {
		return SpringBeanUtil.getBean(AppMsgSettingContext.class);
	}
	
	/**
	 * 根据消息设置对象，构建上下文对象
	 * @param appMsgSettingVo
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 上午11:47:50
	 */
	public static AppMsgSettingContext build(AppMsgSettingVo appMsgSettingVo) {
		AppMsgSettingContext appMsgSettingContext = getInstance();
		appMsgSettingContext.setAppMsgSettingVo(appMsgSettingVo);
		
		return appMsgSettingContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 消息设置上下文
	 */
	public static AppMsgSettingContext build() {
		AppMsgSettingContext appMsgSettingContext = getInstance();
		
		return appMsgSettingContext;
	}
	
	/**
	 * 根据用户id，构建消息设置上下文对象
	 * @param userId
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 上午11:49:29
	 */
	public static AppMsgSettingContext loadByUserId(String userId){
		AppMsgSettingContext appMsgSettingContext = getInstance();
		appMsgSettingContext.setUserId(userId);
		
		return appMsgSettingContext;
	}
	
	/**
	 * 根据用户id，获取用户手机消息设置
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 下午1:44:39
	 */
	public AppMsgSettingVo get(){
		if(appMsgSettingVo == null){
			AppMsgSettingPo appMsgSettingPo = null;
			if(ValidateHelper.isNotEmptyString(userId)){
				appMsgSettingPo = appMsgSettingRepository.findByUserId(userId);
			}
			
			appMsgSettingVo = MyBeanUtil.createBean(appMsgSettingPo, AppMsgSettingVo.class);
		}
		
		return appMsgSettingVo;
	}
	
	/**
	 * 创建消息设置
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 上午11:55:47
	 */
	public AppMsgSettingVo create() {
		if (appMsgSettingVo != null) {
			appMsgSettingVo.setId(UUIDGenerator.generate());
			appMsgSettingVo.setOemCode(ContextManager.getInstance().getOemCode());
			appMsgSettingVo.setUpdateTime(new Date());

			AppMsgSettingPo appMsgSettingPo = MyBeanUtil.createBean(appMsgSettingVo, AppMsgSettingPo.class);
			appMsgSettingRepository.save(appMsgSettingPo);

			logger.info("Create appMsgSettingPo : {} success.", appMsgSettingPo);
		}

		return appMsgSettingVo;
	}
	
	/**
	 * 修改用户手机消息设置
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 下午1:36:11
	 */
	public AppMsgSettingVo update() {
		if (appMsgSettingVo != null && ValidateHelper.isNotEmptyString(appMsgSettingVo.getUserId())) {
			AppMsgSettingPo appMsgSettingPo = appMsgSettingRepository.findByUserId(appMsgSettingVo.getUserId());

			MyBeanUtil.copyBeanNotNull2Bean(appMsgSettingVo, appMsgSettingPo);
			appMsgSettingRepository.save(appMsgSettingPo);

			logger.info("Update  appMsgSetting of userid: {} success.", appMsgSettingVo.getUserId());
			
			appMsgSettingVo = MyBeanUtil.createBean(appMsgSettingPo, AppMsgSettingVo.class);
		}

		return appMsgSettingVo;
	}

	protected void setAppMsgSettingVo(AppMsgSettingVo appMsgSettingVo) {
		this.appMsgSettingVo = appMsgSettingVo;
	}

	protected void setUserId(String userId) {
		this.userId = userId;
	}
	

	
	
}
