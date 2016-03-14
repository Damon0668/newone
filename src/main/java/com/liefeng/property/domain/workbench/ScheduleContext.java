package com.liefeng.property.domain.workbench;

import java.util.Date;
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
import com.liefeng.property.po.workbench.SchedulePo;
import com.liefeng.property.repository.workbench.ScheduleRepository;
import com.liefeng.property.vo.workbench.ScheduleVo;


/**
 * 日程领域上下文对象
 * @author xhw
 * @2016年3月2日 上午8:46:51
 */
@Service
@Scope("prototype")
public class ScheduleContext {
	
	private static Logger logger = LoggerFactory.getLogger(ScheduleContext.class);
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	
	/**
	 * 客户值对象
	 */
	private ScheduleVo scheduleVo;
	
	/**
	 * 日程id
	 */
	private String id;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static ScheduleContext getInstance() {
		return SpringBeanUtil.getBean(ScheduleContext.class);
	}
	
	/**
	 * 通过值领域对象构建上下文
	 * @param scheduleVo
	 * @return
	 * @author xhw
	 * @2016年3月2日 上午8:52:55
	 */
	public static ScheduleContext build(ScheduleVo scheduleVo) {
		ScheduleContext scheduleContext = getInstance();
		scheduleContext.setScheduleVo(scheduleVo);
		
		return scheduleContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 日程上下文
	 */
	public static ScheduleContext build() {
		ScheduleContext scheduleContext = getInstance();
		
		return scheduleContext;
	}
	
	/**
	 * 根据日程id，构建上下文对象
	 * @param id
	 * @return
	 * @author xhw
	 * @2016年3月2日 上午8:53:29
	 */
	public static ScheduleContext loadById(String id){
		ScheduleContext scheduleContext = getInstance();
		scheduleContext.setId(id);
		
		return scheduleContext;
	}
	
	/**
	 * 通过日程id，查找相应的日程
	 * @return
	 * @author xhw
	 * @2016年3月2日 上午8:56:18
	 */
	public ScheduleVo getById(){
		if (scheduleVo == null) {
			if (ValidateHelper.isNotEmptyString(id)) {
				SchedulePo schedulePo = scheduleRepository.findOne(id);

				scheduleVo = MyBeanUtil.createBean(schedulePo, ScheduleVo.class);
			}
		}
		return scheduleVo;
	}
	
	/**
	 * 创建日程
	 * @return
	 * @author xhw
	 * @2016年3月2日 上午8:58:19
	 */
	public ScheduleVo create(){
		if (scheduleVo != null) {
			scheduleVo.setId(UUIDGenerator.generate());
			scheduleVo.setOemCode(ContextManager.getInstance().getOemCode());
			scheduleVo.setCreateTime(new Date());

			SchedulePo schedulePo = MyBeanUtil.createBean(scheduleVo, SchedulePo.class);
			scheduleRepository.save(schedulePo);
				
		}

		return scheduleVo;
	}

	/**
	 * 更新日程
	 * @return
	 * @author xhw
	 * @2016年3月2日 上午9:01:44
	 */
	public ScheduleVo update(){
		if(scheduleVo != null && ValidateHelper.isNotEmptyString(scheduleVo.getId())){
			SchedulePo schedulePo = scheduleRepository.findOne(scheduleVo.getId());
			
			MyBeanUtil.copyBeanNotNull2Bean(scheduleVo, schedulePo);
			scheduleRepository.save(schedulePo);
			
			logger.info("Update schedule of id: {} success.", scheduleVo.getId());
			
			scheduleVo = MyBeanUtil.createBean(schedulePo, ScheduleVo.class);
		}
		
		return scheduleVo;
	}

	/**
	 * 根据日程id，删除日程
	 * @author xhw
	 * @2016年3月2日 上午9:14:40
	 */
	public void deleteById(){
		if(ValidateHelper.isNotEmptyString(id)){
			scheduleRepository.deleteById(id);
			
			logger.info("Delete schedule of id: {} success.", id);
		}
	}
	
	/**
	 * 根据创建人id，删除所有属于该创建人的所有日程
	 * @param creatorId 创建人id
	 * @author xhw
	 * @2016年3月2日 上午9:16:27
	 */
	public void deleteByCreatorId(String creatorId){
		if(ValidateHelper.isNotEmptyString(creatorId)){
			scheduleRepository.deleteByCreatorId(creatorId);
			
			logger.info("Delete schedule of creatorId : {} success.", creatorId);
		}
	}
	
	/**
	 * 通过创建人id、日期，查找该创建人在这一天的日程
	 * @param creatorId 创建人id
	 * @param queryDate 查询日期
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午2:07:38
	 */
	public List<ScheduleVo>  findByCreatorIdAndQueryDate(String creatorId, String queryDate){
		List<ScheduleVo> scheduleVos = null;
		if(ValidateHelper.isNotEmptyString(creatorId)&&ValidateHelper.isNotEmptyString(queryDate)){
			List<SchedulePo> schedulePos = scheduleRepository.findByCreatorIdAndQueryDate(creatorId, queryDate);
			
			scheduleVos = MyBeanUtil.createList(schedulePos, ScheduleVo.class);
		}
		return scheduleVos;
	}
	
	protected void setScheduleVo(ScheduleVo scheduleVo) {
		this.scheduleVo = scheduleVo;
	}

	protected void setId(String id) {
		this.id = id;
	}
	
}
