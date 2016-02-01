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
import com.liefeng.property.po.sys.SysMenuPo;
import com.liefeng.property.repository.sys.SysMenuRepository;
import com.liefeng.property.vo.sys.SysMenuVo;

/**
 * 系统角色
 * 领域层
 * @author 蔡少东
 * @date 2016年2月1日
 */
@Service
@Scope("prototype")
public class SysMenuContext {

	@Autowired
	private SysMenuRepository sysMenuRepository;
	
	/**
	 * 菜单值对象
	 */
	private SysMenuVo sysMenu;
	
	/**
	 * 菜单id
	 */
	private Long menuId;
	
	private static SysMenuContext getInstance() {
		return SpringBeanUtil.getBean(SysMenuContext.class);
	}
	
	public static SysMenuContext build() {
		SysMenuContext sysMenuContext = getInstance();
		
		return sysMenuContext;
	}
	
	public static SysMenuContext build(SysMenuVo sysMenuVo) {
		SysMenuContext sysMenuContext = getInstance();
		sysMenuContext.sysMenu = sysMenuVo;
		return sysMenuContext;
	}
	
	public static SysMenuContext build(Long menuId) {
		SysMenuContext sysMenuContext = getInstance();
		sysMenuContext.menuId = menuId;
		return sysMenuContext;
	}
	
	public void create() {
		SysMenuPo sysMenuPo = MyBeanUtil.createBean(sysMenu, SysMenuPo.class);
		sysMenuRepository.save(sysMenuPo);
	}
	
	public void delete() {
		sysMenuRepository.delete(menuId);
	}
	
	public DataPageValue<SysMenuVo> findMenus(int page, int size) {
		
		Page<SysMenuVo> voPage = null;
		
		Page<SysMenuPo> poPage = sysMenuRepository.findAll(new PageRequest(page - 1, size));
		
		voPage = poPage.map(new Po2VoConverter<SysMenuPo, SysMenuVo>(SysMenuVo.class));
		
		return new DataPageValue<SysMenuVo>(voPage.getContent(), voPage.getTotalElements(), size, page);
	}

}
