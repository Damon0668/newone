package com.liefeng.property.domain.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.Po2VoConverter;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.po.sys.SysRolePo;
import com.liefeng.property.repository.sys.SysRoleRepository;
import com.liefeng.property.vo.sys.SysRoleVo;

/**
 * 系统角色领
 * 域层
 * @author 蔡少东
 * @date 2016年2月1日
 */
@Service
@Scope("prototype")
public class SysRoleContext {

	@Autowired
	private SysRoleRepository sysRoleRepository;
	
	private SysRoleVo role;
	
	private Long id;
	
	private static SysRoleContext getInstance() {
		return SpringBeanUtil.getBean(SysRoleContext.class);
	}
	
	public static SysRoleContext build() {
		SysRoleContext sysRoleContext = getInstance();
		
		return sysRoleContext;
	}
	
	public static SysRoleContext build(SysRoleVo role) {
		SysRoleContext sysRoleContext = getInstance();
		sysRoleContext.role = role;
		return sysRoleContext;
	}
	
	public static SysRoleContext loadById(Long id) {
		SysRoleContext sysRoleContext = getInstance();
		sysRoleContext.id = id;
		return sysRoleContext;
	}
	
	public void create() {
		SysRolePo sysRolePo = MyBeanUtil.createBean(role, SysRolePo.class);
		sysRoleRepository.save(sysRolePo);
	}
	
	public void delete() {
		sysRoleRepository.delete(id);
	}
	
	public DataPageValue<SysRoleVo> findRoles(int page, int size) {
		
		Page<SysRoleVo> voPage = null;
		
		Page<SysRolePo> poPage = sysRoleRepository.findAll(new PageRequest(page - 1, size));
		
		voPage = poPage.map(new Po2VoConverter<SysRolePo, SysRoleVo>(SysRoleVo.class));
		
		return new DataPageValue<SysRoleVo>(voPage.getContent(), voPage.getTotalElements(), size, page);
	}
}
