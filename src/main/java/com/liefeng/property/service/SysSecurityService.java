package com.liefeng.property.service;

import org.springframework.stereotype.Service;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.ISysSecurityService;
import com.liefeng.property.domain.sys.SysRoleContext;
import com.liefeng.property.vo.sys.SysRoleVo;

@Service
public class SysSecurityService implements ISysSecurityService{

	@Override
	public DataPageValue<SysRoleVo> listRoles4page(String name, String type, int page, int size) {
		return SysRoleContext.build().findRoles(page, size);
	}

}
