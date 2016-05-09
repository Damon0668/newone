package com.liefeng.property.domain.guard;

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
import com.liefeng.property.po.guard.AttendantPo;
import com.liefeng.property.repository.guard.AttendantRepository;
import com.liefeng.property.repository.mybatis.AttendantQueryRepository;
import com.liefeng.property.util.DictionaryUtil;
import com.liefeng.property.vo.guard.AttendantVo;
import com.liefeng.property.vo.household.VisitorVo;

/**
 * 服务人员上下文对象
 * @author xhw
 * @date 2016年4月18日 下午5:47:44
 */
@Service
@Scope("prototype")
public class AttendantContext {
	
	private static Logger logger = LoggerFactory.getLogger(AttendantContext.class);
	
	@Autowired
	private AttendantRepository attendantRepository;
	
	@Autowired
	private AttendantQueryRepository attendantQueryRepository;
	
	/**
	 * 服务人员id
	 */
	private String id;
	
	/**
	 * 服务人员值对象
	 */
	private AttendantVo attendantVo;
	
	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	private static AttendantContext getInstance() {
		return SpringBeanUtil.getBean(AttendantContext.class);
	}
	
	/**
	 * 根据服务人员构建上下文
	 * @param attendantVo
	 * @return 
	 * @author xhw
	 * @date 2016年4月18日 下午5:51:16
	 */
	public static AttendantContext build(AttendantVo attendantVo) {
		AttendantContext attendantContext = getInstance();
		attendantContext.setAttendantVo(attendantVo);
		
		return attendantContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 访客信息上下文
	 */
	public static AttendantContext build() {
		AttendantContext attendantContext = getInstance();
		
		return attendantContext;
	}
	
	/**
	 * 根据访客信息ID加载上下文
	 * @param visitorId 访客信息ID
	 * @return 访客信息上下文
	 */
	public static AttendantContext loadById(String id) {
		AttendantContext attendantContext = getInstance();
		attendantContext.id= id;
		
		return attendantContext;
	}
	
	/**
	 * 获取服务人员信息
	 * @return 
	 * @author xhw
	 * @date 2016年4月18日 下午5:53:30
	 */
	public AttendantVo getAttendantVo() {
		if(attendantVo == null) {
			AttendantPo attendantPo = null;
			if(ValidateHelper.isNotEmptyString(id)) {
				attendantPo = attendantRepository.findOne(id);
			}
			
			if(attendantPo != null) {
				attendantVo = MyBeanUtil.createBean(attendantPo, AttendantVo.class);
			}
		}
		
		return attendantVo;
	}
	
	/**
	 * 创建服务人员
	 * @return 
	 * @author xhw
	 * @date 2016年4月18日 下午5:55:08
	 */
	public AttendantVo create() {
		if(attendantVo != null) {
			attendantVo.setId(UUIDGenerator.generate());
			attendantVo.setOemCode(ContextManager.getInstance().getOemCode());
			attendantVo.setCreateTime(new Date());
			AttendantPo attendantPo = MyBeanUtil.createBean(attendantVo, AttendantPo.class);
			
			attendantRepository.save(attendantPo);
			logger.info("create attendantVo = {}", attendantVo);
		}
		return attendantVo;
	}
	
	/**
	 * 分页查询
	 * @param projectId 项目id
	 * @param manageProjectIds 员工所管理的项目
	 * @param name 名称
	 * @param phone 手机号码
	 * @param serviceType 服务类型
	 * @param page
	 * @param size
	 * @return 
	 * @author xhw
	 * @date 2016年4月19日 下午4:03:52
	 */
	public DataPageValue<AttendantVo> findByPage(String projectId, String manageProjectIds, String name, String phone, String serviceType, Integer page, Integer size) {
		String oemCode = ContextManager.getInstance().getOemCode();
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("projectId", projectId);
		paramMap.put("name", name);
		paramMap.put("phone", phone);
		paramMap.put("manageProjectIds", manageProjectIds);
		paramMap.put("oemCode", oemCode);
		paramMap.put("serviceType", serviceType);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);
		param.setPage(page);
		param.setPageSize(size);

		Long count = attendantQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);

		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		List<AttendantVo> list = attendantQueryRepository.queryByPage(param);
		list = DictionaryUtil.transformDicValueToDicName(list); // 字典【值-名】转换
		DataPageValue<AttendantVo> returnPage = new DataPageValue<AttendantVo>(list, count, size, page);

		return returnPage;
	}

	/**
	 * 更新服务人员信息
	 */
	public AttendantVo update() {
		if(attendantVo != null && ValidateHelper.isNotEmptyString(attendantVo.getId())) {
			logger.info("更新服务人员信息，服务人员ID = {}", attendantVo.getId());
			AttendantPo attendantPo = attendantRepository.findOne(attendantVo.getId());
			
			if(attendantPo != null) {
				MyBeanUtil.copyBeanNotNull2Bean(attendantVo, attendantPo);
				attendantRepository.save(attendantPo);
				logger.info("更新服务人员信息成功，服务人员ID = {}", attendantVo.getId());
				
				attendantVo = MyBeanUtil.createBean(attendantPo, AttendantVo.class);
			}
		}
		
		return attendantVo;
	}
	

	protected void setId(String id) {
		this.id = id;
	}

	protected void setAttendantVo(AttendantVo attendantVo) {
		this.attendantVo = attendantVo;
	}
}
