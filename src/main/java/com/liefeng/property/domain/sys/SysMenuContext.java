package com.liefeng.property.domain.sys;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.Po2VoConverter;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.po.sys.SysMenuPo;
import com.liefeng.property.repository.mybatis.SysMenuQueryRepository;
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
	
	private static Logger logger = LoggerFactory.getLogger(SysMenuContext.class);
	
	@Autowired
	private SysMenuRepository sysMenuRepository;
	
	@Autowired
	private SysMenuQueryRepository sysMenuQueryRepository;
	
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
	
	private String code;
	
	protected void setSysMenu(SysMenuVo sysMenu) {
		this.sysMenu = sysMenu;
	}
	
	protected void setId(Long id) {
		this.id = id;
	}

	protected void setCode(String code) {
		this.code = code;
	}

	private static SysMenuContext getInstance() {
		SysMenuContext sysMenuContext =  SpringBeanUtil.getBean(SysMenuContext.class);
		return sysMenuContext;
	}
	
	public static SysMenuContext build() {
		return getInstance();
	}
	
	public static SysMenuContext build(SysMenuVo sysMenuVo) {
		SysMenuContext sysMenuContext = getInstance();
		sysMenuContext.setSysMenu(sysMenuVo);
		return sysMenuContext;
	}
	
	public static SysMenuContext loadById(Long id) {
		SysMenuContext sysMenuContext = getInstance();
		sysMenuContext.setId(id);
		return sysMenuContext;
	}
	
	public static SysMenuContext loadByCode(String code) {
		SysMenuContext sysMenuContext = getInstance();
		sysMenuContext.setCode(code);
		return sysMenuContext;
	}
	
	/**
	 * 获取菜单
	 * id 优先  > code
	 * @return
	 */
	public SysMenuVo getMenu(){
		
		SysMenuPo sysMenuPo = null;
		
		if(sysMenu == null){
			
			if(id != null){
				sysMenuPo = sysMenuRepository.findOne(id);
			}
			
			if(code !=null){
				sysMenuPo = sysMenuRepository.findByCode(code);
			}
			
			if(sysMenuPo != null){
				sysMenu = MyBeanUtil.createBean(sysMenuPo, SysMenuVo.class);
			}
		}
		
		
		
		return sysMenu;
	}
	/**
	 * 创建菜单
	 */
	public SysMenuVo create() {
		SysMenuPo sysMenuPo = MyBeanUtil.createBean(sysMenu, SysMenuPo.class);
		sysMenuPo.setOemCode("test");
		sysMenuRepository.save(sysMenuPo);
		sysMenu = MyBeanUtil.createBean(sysMenuPo, SysMenuVo.class);
		return sysMenu;
	}
	
	/**
	 * 更新菜单
	 * @return
	 */
	public SysMenuVo update(){
		
		if(sysMenu !=null && sysMenu.getId() != null){
			
			SysMenuPo sysMenuPo = sysMenuRepository.findOne(sysMenu.getId());
			
			if(sysMenuPo != null){
				
				MyBeanUtil.copyBeanNotNull2Bean(sysMenu, sysMenuPo);
				
				sysMenuRepository.save(sysMenuPo);
				
				sysMenu = MyBeanUtil.createBean(sysMenuPo, SysMenuVo.class);
			}
		}
		
		return sysMenu;
	}

	/**
	 * 批量删除菜单
	 * @param ids 菜单ID值数组
	 */
	@Transactional(rollbackOn=Exception.class)
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
		List<SysMenuVo> sysMenuList = MyBeanUtil.createList(sysMenuRepository.findAll(), SysMenuVo.class);
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
	
	/**
	 * 查询菜单（只是按钮类型）
	 * @param parentId
	 * @param page
	 * @param size
	 * @return
	 */
	public DataPageValue<SysMenuVo> findButtons(Long parentId, int page, int size){
		
		Page<SysMenuVo> voPage = null;
		
		Page<SysMenuPo> poPage = sysMenuRepository.findButtons(parentId, new PageRequest(page - 1, size));
		
		voPage = poPage.map(new Po2VoConverter<SysMenuPo, SysMenuVo>(SysMenuVo.class));
		
		return new DataPageValue<SysMenuVo>(voPage.getContent(), voPage.getTotalElements(), size, page);
	}
	
	/**
	 * 查询菜单
	 * @param page
	 * @param size
	 * @return
	 */
	public DataPageValue<SysMenuVo> findMenus(int page, int size) {
		
		Page<SysMenuVo> voPage = null;
		
		Page<SysMenuPo> poPage = sysMenuRepository.findAll(new PageRequest(page - 1, size));
		
		voPage = poPage.map(new Po2VoConverter<SysMenuPo, SysMenuVo>(SysMenuVo.class));
		
		return new DataPageValue<SysMenuVo>(voPage.getContent(), voPage.getTotalElements(), size, page);
	}
	
	/**
	 * 查询菜单
	 * @param parentId
	 * @param page
	 * @param size
	 * @return
	 */
	public DataPageValue<SysMenuVo> findSubMenu(Long parentId, int page, int size){
		
		Page<SysMenuVo> voPage = null;
		
		Page<SysMenuPo> poPage = sysMenuRepository.findByParentId(parentId, new PageRequest(page - 1, size));
		
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
		
		//结果集合
		List<SysMenuPo> resultMenuList = new ArrayList<SysMenuPo>();
		
		List<SysMenuPo> sysMenuList =
				sysMenuRepository.findByParentId(parentId);
		
		resultMenuList.addAll(sysMenuList);
		
		if(isRecursion){
			
			for (SysMenuPo sysMenuPo : sysMenuList) {
				this.findSubMenu(resultMenuList,sysMenuPo.getId());
			}
			
		}
		
		return MyBeanUtil.createList(resultMenuList, SysMenuVo.class);
	}
	
	/**
	 * 查找菜单树
	 */
	public List<SysMenuVo> findMenuTree(){
		
		if(menuTree == null){
			
			List<SysMenuPo> allMenuList = sysMenuRepository.findMenusIgnoreButton();
			
			if(ValidateHelper.isNotEmptyCollection(allMenuList)){

				menuTree = buildSubMenu(MyBeanUtil.createList(allMenuList, SysMenuVo.class));
			}
			
		}
		
		return menuTree;
	}

	/**
	 * 根据用户ID查询此用户拥有的按钮的code值
	 * @param userId 用户Id
	 * @return
	 */
	public List<String> findButtonsCodeByUserId(String userId){
		return sysMenuQueryRepository.queryButtonsCodeByUserId(userId);
	}
	
	/**
	 * 根据用户ID查找此用户拥有的菜单
	 * 不包含按钮级别菜单
	 * 
	 * @param userId 用户ID
	 * @return
	 */
	public List<SysMenuVo> findMenusByUserId(String userId){
		
		logger.info("findMenusByUserId userId = {}", userId);
		
		List<SysMenuVo> menuList = null;
		
		List<SysMenuVo> userMenuList = sysMenuQueryRepository.queryMenusByUserId(userId);
		
		logger.info("userMenuList = {}", userMenuList);
		
		if(ValidateHelper.isNotEmptyCollection(userMenuList)){
			
			menuList = buildSubMenu(MyBeanUtil.createList(userMenuList, SysMenuVo.class));
			
		}
		return menuList;
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
	 * 构建子菜单
	 * @param SysMenuList
	 * @param sysMenuPo
	 */
	private List<SysMenuVo> buildSubMenu(List<SysMenuVo> sysMenuList){
		
		List<SysMenuVo> menuList = new ArrayList<SysMenuVo>();
		
		if(ValidateHelper.isNotEmptyCollection(sysMenuList)){
			
			for (SysMenuVo sysMenu : sysMenuList) {
				
				if(sysMenu.getParentId() == 0){
					menuList.add(sysMenu);
				}else{
					buildSubMenu(menuList, sysMenu);
				}
				
			}
			
		}
		
		return menuList;
	}
	
	private void buildSubMenu(List<SysMenuVo> SysMenuList, SysMenuVo sysMenu){
		for (SysMenuVo sysMenuVo : SysMenuList) {
			if(sysMenuVo.getId().longValue() == sysMenu.getParentId().longValue()){
				sysMenuVo.getSubMenus().add(MyBeanUtil.createBean(sysMenu, SysMenuVo.class));
				break;
			}else{
				buildSubMenu(sysMenuVo.getSubMenus(), sysMenu);
			}
		}
	}
	

}
