package com.liefeng.property.domain.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.property.po.sys.SysParamPo;
import com.liefeng.property.repository.sys.SysParamRepository;
import com.liefeng.property.vo.sys.SysParamVo;

/**
 * 系统参数领域模型
 * @author Huangama
 * @date 2016-2-23
 */
@Service
@Scope("prototype")
public class SysParamContext {

	private static Logger logger = LoggerFactory.getLogger(SysParamContext.class);
	
	@Autowired
	private SysParamRepository sysParamRepository;
	
	/**
	 * 系统参数编码
	 */
	private String code;
	
	/**
	 * 系统参数VO
	 */
	private SysParamVo sysParam;
	
	private static SysParamContext getInstance() {
		SysParamContext sysParamContext =  SpringBeanUtil.getBean(SysParamContext.class);
		return sysParamContext;
	}
	
	public static SysParamContext build(String code) {
		SysParamContext sysParamContext = getInstance();
		sysParamContext.setCode(code);
		return sysParamContext;
	}
	
	public static SysParamContext build(SysParamVo sysParam) {
		SysParamContext sysParamContext = getInstance();
		sysParamContext.setSysParam(sysParam);
		return sysParamContext;
	}
	
	/**
	 * 根据系统参数编码获取系统参数
	 * @return 系统参数
	 */
	public SysParamVo get() {
		String oemCode = ContextManager.getInstance().getOemCode();
		SysParamPo sysParamPo = 
				sysParamRepository.findByCodeAndOemCode(code, oemCode);
		return MyBeanUtil.createBean(sysParamPo, SysParamVo.class);
	}
	
	public void update() {
		if (sysParam != null) {
			String oemCode = ContextManager.getInstance().getOemCode();
			String newValue = sysParam.getValue();
			SysParamPo sysParamPo = 
					sysParamRepository.findByCodeAndOemCode(sysParam.getCode(), oemCode);
			String originValue = sysParamPo.getValue();
			
			if (ValidateHelper.isNotEmptyString(sysParam.getValue())) {
				sysParamPo.setValue(newValue);
			}
			sysParamRepository.save(sysParamPo);
			
			logger.info("Update system param '{}', origin value:'{}', new value:'{}'.", 
					sysParam.getCode(), originValue, newValue);
		}
	}
	
	protected void setCode(String code) {
		this.code = code;
	}
	
	protected void setSysParam(SysParamVo sysParam) {
		this.sysParam = sysParam;
	}
}
