package com.liefeng.property.domain.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.po.sys.SysDictPo;
import com.liefeng.property.repository.sys.SysDictRepository;
import com.liefeng.property.vo.sys.SysDictVo;

/**
 * 数据字典领域模型
 * @author Huangama
 * @date 2016-2-20
 */
@Service
@Scope("prototype")
public class SysDictContext {

	@Autowired
	private SysDictRepository sysDictRepository;
	
	/**
	 * 字典组编码
	 */
	private String groupCode;
	
	private static SysDictContext getInstance() {
		SysDictContext sysDictContext =  SpringBeanUtil.getBean(SysDictContext.class);
		return sysDictContext;
	}
	
	public static SysDictContext build(String groupCode) {
		SysDictContext sysDictContext = getInstance();
		sysDictContext.setGroupCode(groupCode);
		return sysDictContext;
	}
	
	/**
	 * 根据字典组编码获取字典列表
	 * @return 字典列表
	 */
	public List<SysDictVo> get() {
		List<SysDictPo> dictPoList = sysDictRepository.findByGroupCode(groupCode);
		return MyBeanUtil.createList(dictPoList, SysDictVo.class);
	}
	
	protected void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
}
