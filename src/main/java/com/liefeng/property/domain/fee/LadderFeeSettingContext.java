package com.liefeng.property.domain.fee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.vo.fee.LadderFeeSettingVo;

/**
 * 
 * <pre>      
 * Title:阶梯费用领域模型
 * Description:
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  wuzhijing        
 * @version 1.0      
 * @created 2016年2月18日下午9:04:53
 * </pre>
 */
@Service
@Scope("prototype")
public class LadderFeeSettingContext {

	private static Logger logger = LoggerFactory.getLogger(LadderFeeSettingContext.class);

	private LadderFeeSettingVo ladderFeeSetting;
	protected void setLadderFeeSetting(LadderFeeSettingVo ladderFeeSetting) {
		this.ladderFeeSetting = ladderFeeSetting;
	}
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * 
	 * @return 本类实例
	 */
	private static LadderFeeSettingContext getInstance() {
		return SpringBeanUtil.getBean(LadderFeeSettingContext.class);
	}
	
	public static LadderFeeSettingContext build(LadderFeeSettingVo ladderFeeSetting) {
		LadderFeeSettingContext  ladderFeeSettingContext= getInstance();
		ladderFeeSettingContext.setLadderFeeSetting(ladderFeeSetting);
		return ladderFeeSettingContext;
	}
}
