package com.liefeng.property.domain.sys;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
	private Long id;
	
	private static SysMenuContext getInstance() {
		return SpringBeanUtil.getBean(SysMenuContext.class);
	}
	
	public static SysMenuContext build() {
		return getInstance();
	}
	
	public static SysMenuContext build(SysMenuVo sysMenuVo) {
		SysMenuContext sysMenuContext = getInstance();
		sysMenuContext.sysMenu = sysMenuVo;
		return sysMenuContext;
	}
	
	public static SysMenuContext loadById(Long id) {
		SysMenuContext sysMenuContext = getInstance();
		sysMenuContext.id = id;
		return sysMenuContext;
	}
	
	public SysMenuVo getMenu(){
		
		if(id != null && sysMenu == null){
			SysMenuPo sysMenuPo = sysMenuRepository.findOne(id);
			sysMenu = MyBeanUtil.createBean(sysMenuPo, SysMenuVo.class);
		}
		
		return sysMenu;
	}
	/**
	 * 创建菜单
	 */
	public void create() {
		SysMenuPo sysMenuPo = MyBeanUtil.createBean(sysMenu, SysMenuPo.class);
		sysMenuPo.setOemCode("test");
		sysMenuRepository.save(sysMenuPo);
	}

	/**
	 * 批量删除菜单
	 * @param ids 菜单ID值数组
	 */
	public void delMenus(String[] ids){
		if(ids != null){
			
			List<SysMenuVo> listMenu = new ArrayList<SysMenuVo>();
			
			for (String id : ids) {
				
				SysMenuVo sysMenu = SysMenuContext.loadById(Long.parseLong(id)).getMenu();
				
				listMenu.add(sysMenu);
				
				listMenu.addAll(this.findSubMenu(sysMenu.getId(), true));
			}
			
			for (SysMenuVo sysMenuVo : listMenu) {
				
				sysMenuRepository.delete(sysMenuVo.getId());
				
				SysRoleMenuContext.build().deleteRoleMenu(sysMenuVo.getId());
			}
		}
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
		
		if(sysMenuList != null){
			
			for (SysMenuVo sysMenuVo : sysMenuList) {
				if(selectedMenus != null && selectedMenus.contains(sysMenuVo.getId())){
					sysMenuVo.setIsSelect(1);
				}
			}
			
		}
		
		return sysMenuList;
	}
	
	
	/**
	 * 查询菜单（不包含按钮）
	 * @param parentId 父级ID
	 * @param page
	 * @param size
	 * @return
	 */
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
	 * 查找子菜单
	 * @param parentId 父id
	 * @param isRecursion 是否需要递归查找子菜单
	 * @return
	 */
	public List<SysMenuVo> findSubMenu(Long parentId, boolean isRecursion){
		List<SysMenuPo> sysMenuList =
				sysMenuRepository.findByParentId(parentId);
		
		if(isRecursion){
			
			for (SysMenuPo sysMenuPo : sysMenuList) {
				this.findSubMenu(sysMenuList,sysMenuPo.getId());
			}
			
		}
		
		return MyBeanUtil.createList(sysMenuList, SysMenuVo.class);
	}
	
	/**
	 * 查找子菜单
	 * @param sysMenuList
	 * @param parentId 父菜单ID
	 * @return
	 */
	private List<SysMenuPo> findSubMenu(List<SysMenuPo> sysMenuList, Long parentId){
		
		List<SysMenuPo> subMenuList = sysMenuRepository.findByParentId(parentId);
		
		if(!subMenuList.isEmpty()){
			
			sysMenuList.addAll(subMenuList);
			
			for (SysMenuPo sysMenuPo : subMenuList) {
				findSubMenu(sysMenuList,sysMenuPo.getId());
			}
			
		}
		return sysMenuList;
	}
	
	
	/**
	 * 查找菜单树
	 */
	public List<SysMenuVo> findMenuTree(){
		
		if(menuTree == null){
			
			List<SysMenuPo> AllMenuList = sysMenuRepository.findMenusIgnoreButton();
			
			if(AllMenuList != null){
				menuTree = new ArrayList<SysMenuVo>();
			
				for (SysMenuPo sysMenu : AllMenuList) {
					
					if(sysMenu.getParentId() == 0){
						menuTree.add(MyBeanUtil.createBean(sysMenu, SysMenuVo.class));
					}else{
						buildSubMenu(menuTree, sysMenu);
					}
					
				}
			
			}
			
		}
		
		return menuTree;
	}

	/**
	 * 构建子菜单
	 * @param SysMenuList
	 * @param sysMenuPo
	 */
	private void buildSubMenu(List<SysMenuVo> SysMenuList, SysMenuPo sysMenuPo){
		for (SysMenuVo sysMenuVo : SysMenuList) {
			if(sysMenuVo.getId() == sysMenuPo.getParentId()){
				sysMenuVo.getSubMenus().add(MyBeanUtil.createBean(sysMenuPo, SysMenuVo.class));
				break;
			}else{
				buildSubMenu(sysMenuVo.getSubMenus(), sysMenuPo);
			}
		}
	}
}
