package com.liefeng.property.domain.workbench;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.po.workbench.WebsiteMsgPo;
import com.liefeng.property.repository.mybatis.WebsiteMsgQueryRepository;
import com.liefeng.property.repository.workbench.WebsiteMsgRepository;
import com.liefeng.property.vo.workbench.WebsiteMsgVo;


/**
 * 站内消息值对象上下文对象
 * @author xhw
 * @2016年3月2日 下午3:50:35
 */
@Service
@Scope("prototype")
public class WebsiteMsgContext {
	
	private static Logger logger = LoggerFactory.getLogger(WebsiteMsgContext.class);
	
	@Autowired
	private WebsiteMsgRepository websiteMsgRepository;
	
	@Autowired
	private WebsiteMsgQueryRepository websiteMsgQueryRepository;
	
	/**
	 * 客户值对象
	 */
	private WebsiteMsgVo websiteMsgVo;
	
	/**
	 * 站内消息Id
	 */
	private String id;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static WebsiteMsgContext getInstance() {
		return SpringBeanUtil.getBean(WebsiteMsgContext.class);
	}
	
	/**
	 * 根据站内消息上下文对象
	 * @param websiteMsgVo
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午3:53:43
	 */
	public static WebsiteMsgContext build(WebsiteMsgVo websiteMsgVo) {
		WebsiteMsgContext websiteMsgContext = getInstance();
		websiteMsgContext.setwebsiteMsgVo(websiteMsgVo);
		
		return websiteMsgContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 内消息上下文
	 */
	public static WebsiteMsgContext build() {
		WebsiteMsgContext websiteMsgContext = getInstance();
		
		return websiteMsgContext;
	}
	
	/**
	 * 根据站内消息Id加载任务上下文
	 * @param id 站内消息id
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午3:54:41
	 */
	public static WebsiteMsgContext loadById(String id){
		WebsiteMsgContext websiteMsgContext = getInstance();
		websiteMsgContext.setId(id);
		
		return websiteMsgContext;
	}
	
	/**
	 * 根据站内消息id，获取站内消息
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午3:55:59
	 */
	public WebsiteMsgVo getById(){
		if (websiteMsgVo == null) {
			if (ValidateHelper.isNotEmptyString(id)) {
				WebsiteMsgPo websiteMsgPo = websiteMsgRepository.findOne(id);

				websiteMsgVo = MyBeanUtil.createBean(websiteMsgPo, WebsiteMsgVo.class);
			}
		}
		return websiteMsgVo;
	}
	
	/**
	 * 站内消息、
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午3:59:12
	 */
	public WebsiteMsgVo create(){
		if (websiteMsgVo != null) {
			websiteMsgVo.setId(UUIDGenerator.generate());
			websiteMsgVo.setOemCode(ContextManager.getInstance().getOemCode());
			websiteMsgVo.setCreateTime(new Date());
			WebsiteMsgPo websiteMsgPo = MyBeanUtil.createBean(websiteMsgVo, WebsiteMsgPo.class);
			websiteMsgRepository.save(websiteMsgPo);
			
			logger.info("create websiteMsg : {} success.", websiteMsgVo);
		}

		return websiteMsgVo;
	}
	
	/**
	 * 根据站内消息id，删除站内消息
	 * @author xhw
	 * @2016年3月2日 下午4:08:32
	 */
	public void deleteById(){
		if(ValidateHelper.isNotEmptyString(id)){
			websiteMsgRepository.deleteById(id);
			
			logger.info("delete websiteMsg of id : {} success.", id);

		}
	}

	/**
	 * 获取站内消息总数
	 * @param type 站内消息类型：1：系统，2：个人
	 * @param staffId 员工id
	 * @param deptId  部门id
	 * @param manageProject 管理的项目
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午8:33:35
	 */
	public Long findCount(String type, String staffId, String deptId, String manageProject) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("type", type);
		paramMap.put("staffId", staffId);
		paramMap.put("deptId", deptId);
		paramMap.put("manageProject", manageProject);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);

		Long count = websiteMsgQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("站内消息总数量：count=" + count);

		return count;
	}

	/**
	 * 获取站内消息（分页）
	 * @param type 站内消息类型：1：系统，2：个人
	 * @param staffId 员工id
	 * @param deptId 部门id
	 * @param manageProject 管理的项目
	 * @param page
	 * @param size
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午8:35:57
	 */
	public DataPageValue<WebsiteMsgVo> findByPage(String type, String staffId, String deptId, String manageProject, Integer page, Integer size) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("type", type);
		paramMap.put("staffId", staffId);
		paramMap.put("deptId", deptId);
		paramMap.put("manageProject", manageProject);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);
		param.setPage(page);
		param.setPageSize(size);

		Long count = websiteMsgQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("站内消息总数量：count=" + count);

		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		List<WebsiteMsgVo> list = websiteMsgQueryRepository.queryByPage(param);
		DataPageValue<WebsiteMsgVo> returnPage = new DataPageValue<WebsiteMsgVo>(list, count, size, page);

		return returnPage;
	}
	
	protected void setwebsiteMsgVo(WebsiteMsgVo websiteMsgVo) {
		this.websiteMsgVo = websiteMsgVo;
	}

	protected void setId(String id) {
		this.id = id;
	}

}
