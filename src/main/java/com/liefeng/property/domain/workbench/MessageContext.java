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
import com.liefeng.property.po.workbench.MessagePo;
import com.liefeng.property.repository.mybatis.MessageQueryRepository;
import com.liefeng.property.repository.workbench.MessageRepository;
import com.liefeng.property.vo.workbench.MessageVo;
import com.liefeng.property.vo.workbench.TaskVo;


/**
 * 消息值对象上下文对象
 * @author xhw
 * @2016年3月2日 下午3:50:35
 */
@Service
@Scope("prototype")
public class MessageContext {
	
	private static Logger logger = LoggerFactory.getLogger(MessageContext.class);
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private MessageQueryRepository messageQueryRepository;
	
	/**
	 * 客户值对象
	 */
	private MessageVo messageVo;
	
	/**
	 * 消息Id
	 */
	private String id;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static MessageContext getInstance() {
		return SpringBeanUtil.getBean(MessageContext.class);
	}
	
	/**
	 * 根据消息构建消息上下文对象
	 * @param messageVo
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午3:53:43
	 */
	public static MessageContext build(MessageVo messageVo) {
		MessageContext messageContext = getInstance();
		messageContext.setMessageVo(messageVo);
		
		return messageContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 消息上下文
	 */
	public static MessageContext build() {
		MessageContext messageContext = getInstance();
		
		return messageContext;
	}
	
	/**
	 * 根据消息Id加载任务上下文
	 * @param id 消息id
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午3:54:41
	 */
	public static MessageContext loadById(String id){
		MessageContext messageContext = getInstance();
		messageContext.setId(id);
		
		return messageContext;
	}
	
	/**
	 * 根据消息id，获取消息
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午3:55:59
	 */
	public MessageVo getById(){
		if (messageVo == null) {
			if (ValidateHelper.isNotEmptyString(id)) {
				MessagePo messagePo = messageRepository.findOne(id);

				messageVo = MyBeanUtil.createBean(messagePo, MessageVo.class);
			}
		}
		return messageVo;
	}
	
	/**
	 * 创建消息、
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午3:59:12
	 */
	public MessageVo create(){
		if (messageVo != null) {
			messageVo.setId(UUIDGenerator.generate());
			messageVo.setOemCode(ContextManager.getInstance().getOemCode());
			messageVo.setCreateTime(new Date());
			MessagePo messagePo = MyBeanUtil.createBean(messageVo, MessagePo.class);
			messageRepository.save(messagePo);
			
			logger.info("create message : {} success.", messageVo);
		}

		return messageVo;
	}
	
	/**
	 * 根据消息id，删除消息
	 * @author xhw
	 * @2016年3月2日 下午4:08:32
	 */
	public void deleteById(){
		if(ValidateHelper.isNotEmptyString(id)){
			messageRepository.deleteById(id);
			
			logger.info("delete message of id : {} success.", id);

		}
	}

	/**
	 * 获取消息总数
	 * @param type 消息类型：1：系统，2：个人
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

		Long count = messageQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("消息总数量：count=" + count);

		return count;
	}

	/**
	 * 获取消息（分页）
	 * @param type 消息类型：1：系统，2：个人
	 * @param staffId 员工id
	 * @param deptId 部门id
	 * @param manageProject 管理的项目
	 * @param page
	 * @param size
	 * @return
	 * @author xhw
	 * @2016年3月2日 下午8:35:57
	 */
	public DataPageValue<MessageVo> findByPage(String type, String staffId, String deptId, String manageProject, Integer page, Integer size) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("type", type);
		paramMap.put("staffId", staffId);
		paramMap.put("deptId", deptId);
		paramMap.put("manageProject", manageProject);

		PagingParamVo param = new PagingParamVo();
		param.setExtra(paramMap);
		param.setPage(page);
		param.setPageSize(size);

		Long count = messageQueryRepository.queryByCount(param);
		count = (count == null ? 0 : count);
		logger.info("消息总数量：count=" + count);

		// 设置数据总行数，用于计算偏移量
		param.getPager().setRowCount(count);
		List<MessageVo> list = messageQueryRepository.queryByPage(param);
		DataPageValue<MessageVo> returnPage = new DataPageValue<MessageVo>(list, count, size, page);

		return returnPage;
	}
	
	protected void setMessageVo(MessageVo messageVo) {
		this.messageVo = messageVo;
	}

	protected void setId(String id) {
		this.id = id;
	}

}
