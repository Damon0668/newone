package com.liefeng.property.domain.fee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.vo.fee.FeeSettingVo;

/**
 * <pre>      
 * Title:标准费用设置领域模型
 * Description:
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  wuzhijing
 * @version 1.0      
 * @created 2016年2月18日下午9:12:15
 * </pre>
 */
public class FeeSettingContext {

	private static Logger logger = LoggerFactory.getLogger(FeeSettingContext.class);

	private FeeSettingVo feeSetting;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * 
	 * @return 本类实例
	 */
	public static FeeSettingContext getInstance() {
		return SpringBeanUtil.getBean(FeeSettingContext.class);
	}

	/**
	 * 获取本类实例，每次返回一个新对象
	 * @param feeSetting
	 * @return
	 */
	public static FeeSettingContext build(FeeSettingVo feeSetting) {
		FeeSettingContext feeSettingContext = getInstance();
		feeSettingContext.setFeeSetting(feeSetting);
		return feeSettingContext;
	}

	protected void setFeeSetting(FeeSettingVo feeSetting) {
		this.feeSetting = feeSetting;
	}
}
