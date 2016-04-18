package com.liefeng.property.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.vo.household.UserClientIdVo;

/**
 * 字典操作工具类
 * 
 * @author ZhenTingJun
 * @date 2016年3月30日
 */
public class UserClientIdUtil {

	private static Logger logger = LoggerFactory.getLogger(UserClientIdUtil.class);


	/**
	 * 获取对象中的userId
	 * @param list
	 * @return 
	 * @author xhw
	 * @date 2016年4月18日 下午12:18:06
	 */
	public static  List<String> getUserIdList(List<UserClientIdVo> list) {
		List<String> userIdList = new ArrayList<String>();
		if (ValidateHelper.isNotEmptyCollection(list)) {
			for (UserClientIdVo object : list) {
				if(ValidateHelper.isNotEmptyString(object.getUserId())){
					userIdList.add(object.getUserId());
				}
			}
		}

		return userIdList;
	}
	
	/**
	 * 获取对象中的clientId
	 * @param list
	 * @return 
	 * @author xhw
	 * @date 2016年4月18日 下午12:18:06
	 */
	public static  List<String> getClientIdList(List<UserClientIdVo> list) {
		List<String> clientIdList = new ArrayList<String>();
		if (ValidateHelper.isNotEmptyCollection(list)) {
			for (UserClientIdVo object : list) {
				if(ValidateHelper.isNotEmptyString(object.getClientId())){
					clientIdList.add(object.getClientId());
				}
			}
		}

		return clientIdList;
	}
}
