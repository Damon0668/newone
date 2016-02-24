package com.liefeng.property.domain.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.Po2VoConverter;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.core.dubbo.filter.ContextManager;
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
	
	public void setRole(SysRoleVo role) {
		this.role = role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private static SysRoleContext getInstance() {
		return SpringBeanUtil.getBean(SysRoleContext.class);
	}
	
	public static SysRoleContext build() {
		SysRoleContext sysRoleContext = getInstance();
		
		return sysRoleContext;
	}
	
	public static SysRoleContext build(SysRoleVo role) {
		SysRoleContext sysRoleContext = getInstance();
		sysRoleContext.setRole(role);
		return sysRoleContext;
	}
	
	public static SysRoleContext loadById(Long id) {
		SysRoleContext sysRoleContext = getInstance();
		sysRoleContext.setId(id);
		return sysRoleContext;
	}
	
	/**
	 * 查找角色
	 * @return
	 */
	public SysRoleVo findRole(){
		SysRolePo sysRolePo = sysRoleRepository.findOne(id);
		role = MyBeanUtil.createBean(sysRolePo, SysRoleVo.class);
		return role;
	}
	
	/**
	 * 创建
	 */
	public void create() {
		SysRolePo sysRolePo = MyBeanUtil.createBean(role, SysRolePo.class);
		sysRolePo.setOemCode(ContextManager.getInstance().getOemCode());
		sysRoleRepository.save(sysRolePo);
	}
	
	/**
	 * 更新
	 */
	public void update(){
		
		if(role !=null && role.getId() != null){
			
			SysRolePo sysRolePo = sysRoleRepository.findOne(role.getId());
			
			if(sysRolePo != null){
				
				MyBeanUtil.copyBeanNotNull2Bean(role, sysRolePo);
				
				sysRoleRepository.save(sysRolePo);
				
				role = MyBeanUtil.createBean(sysRolePo, SysRoleVo.class);
			}
		}
	}
	
	public void delete() {
		sysRoleRepository.delete(id);
	}
	
	/**
	 * 查找角色列表
	 * @param name 角色名字
	 * @param page
	 * @param size
	 * @return
	 */
	public DataPageValue<SysRoleVo> findRolesByName4page(String name, int page, int size) {
		
		Page<SysRoleVo> voPage = null;
		
		Page<SysRolePo> poPage = sysRoleRepository.findByNameLike(name, new PageRequest(page - 1, size));
		
		voPage = poPage.map(new Po2VoConverter<SysRolePo, SysRoleVo>(SysRoleVo.class));
		
		return new DataPageValue<SysRoleVo>(voPage.getContent(), voPage.getTotalElements(), size, page);
	}
	
	public List<SysRoleVo> findAll(){
		String oemCode = ContextManager.getInstance().getOemCode();
		return MyBeanUtil.createList(sysRoleRepository.findByOemCode(oemCode),SysRoleVo.class);
	}
}
