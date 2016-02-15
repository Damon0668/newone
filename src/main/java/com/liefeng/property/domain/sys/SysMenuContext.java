package com.liefeng.property.domain.sys;

import java.util.ArrayList;
import java.util.List;

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
	 * 菜单树
	 */
	private List<SysMenuVo> menuTree;
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
	
	/**
	 * 创建菜单
	 */
	public void create() {
		SysMenuPo sysMenuPo = MyBeanUtil.createBean(sysMenu, SysMenuPo.class);
		sysMenuRepository.save(sysMenuPo);
	}
	
	public void delete() {
		sysMenuRepository.delete(menuId);
	}
	
	/**
	 * 查询所有菜单
	 * @return
	 */
	public List<SysMenuVo> findAllMenus(){
		List<SysMenuVo> sysMenuList = MyBeanUtil.createList(sysMenuRepository.findAll(),SysMenuVo.class);
		return sysMenuList;
	}
	
	/**
	 * 查询所有菜单，根据角色ID选中菜单
	 * @param roleId
	 * @return
	 */
	public List<SysMenuVo> findMenusAndCheck(Long roleId){
		List<SysMenuVo> sysMenuList = findAllMenus();
		//TODO 可以优化效率
		List<Long> selectedMenus = SysRoleMenuContext.loadByRoleId(roleId).findSelectedMenuIdsByRoleId();
		
		for (SysMenuVo sysMenuVo : sysMenuList) {
			if(selectedMenus.contains(sysMenuVo.getId())){
				sysMenuVo.setIsSelect(1);
			}
		}
		
		return sysMenuList;
	}
	
	
	public DataPageValue<SysMenuVo> findMenusIgnoreButton(Long parentId, int page, int size) {
		
		Page<SysMenuVo> voPage = null;
		
		Page<SysMenuPo> poPage = sysMenuRepository.findMenusIgnoreButton(parentId, new PageRequest(page - 1, size));
		
		voPage = poPage.map(new Po2VoConverter<SysMenuPo, SysMenuVo>(SysMenuVo.class));
		
		return new DataPageValue<SysMenuVo>(voPage.getContent(), voPage.getTotalElements(), size, page);
	}
	
	public DataPageValue<SysMenuVo> findMenus(int page, int size) {
		
		Page<SysMenuVo> voPage = null;
		
		Page<SysMenuPo> poPage = sysMenuRepository.findAll(new PageRequest(page - 1, size));
		
		voPage = poPage.map(new Po2VoConverter<SysMenuPo, SysMenuVo>(SysMenuVo.class));
		
		return new DataPageValue<SysMenuVo>(voPage.getContent(), voPage.getTotalElements(), size, page);
	}
	
	/**
	 * 查找菜单树
	 */
	public List<SysMenuVo> findMenuTree(){
		
		if(menuTree == null){
			
			List<SysMenuPo> SysMenuPoList = sysMenuRepository.findAll();
			
			if(!SysMenuPoList.isEmpty()){
				menuTree = new ArrayList<SysMenuVo>();
			
				for (SysMenuPo sysMenuPo : SysMenuPoList) {
					
					if(sysMenuPo.getParentId() == 0){
						menuTree.add(MyBeanUtil.createBean(sysMenuPo, SysMenuVo.class));
					}else{
						buildSonMenu(sysMenuPo);
					}
					
				}
			
			}
			
		}
		
		return menuTree;
	}

	private void buildSonMenu(SysMenuPo sysMenuPo){
		//TODO 设计-二级菜单获取
	}
	
	public SysMenuVo getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenuVo sysMenu) {
		this.sysMenu = sysMenu;
	}

	public List<SysMenuVo> getMenuTree() {
		return menuTree;
	}

	public void setMenuTree(List<SysMenuVo> menuTree) {
		this.menuTree = menuTree;
	}
}
