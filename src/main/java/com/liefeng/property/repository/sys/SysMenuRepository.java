package com.liefeng.property.repository.sys;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.liefeng.property.po.sys.SysMenuPo;

/**
 * 系统菜单
 * @author 蔡少东
 * @date 2016年2月1日
 */
@Transactional
public interface SysMenuRepository extends JpaRepository<SysMenuPo, Long>{
	
	/**
	 * 查询菜单（不包含按钮）
	 * @param parentId 父ID
	 * @param pageable 分页对象
	 * @return
	 */
	@Query("select m from SysMenuPo m where m.parentId=:parentId and m.type <> 2 ")
	public Page<SysMenuPo> findMenusIgnoreButton(@Param("parentId")Long parentId, Pageable pageable);
	
	/**
	 * 查询菜单（只是按钮类型）
	 * @param parentId 父ID
	 * @param pageable 分页对象
	 * @return
	 */
	@Query("select m from SysMenuPo m where m.parentId=:parentId and m.type = 2 order by m.id asc")
	public Page<SysMenuPo> findButtons(@Param("parentId")Long parentId, Pageable pageable);
	
	/**
	 * 查询菜单（不包含按钮）
	 * @return
	 */
	@Query("select m from SysMenuPo m where m.type <> 2 order by m.parentId,m.order")
	public List<SysMenuPo> findMenusIgnoreButton();
	
	/**
	 * 查找子菜单
	 * @param parentId 父ID
	 * @return 子菜单列表
	 */
	public List<SysMenuPo> findByParentId(Long parentId);
	
	/**
	 * 查询菜单
	 * @param code 编码字段
	 * @return
	 */
	public SysMenuPo findByCode(String code);
}
