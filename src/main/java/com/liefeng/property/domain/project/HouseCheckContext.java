package com.liefeng.property.domain.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.project.HouseCheckPo;
import com.liefeng.property.repository.project.HouseCheckRepository;
import com.liefeng.property.vo.project.HouseCheckVo;

/**
 * 房屋验收单上下文对象
 * @author xhw
 * @date 2016年5月4日 上午10:39:04
 */
@Service
@Scope("prototype")
public class HouseCheckContext {
	
	private static Logger logger = LoggerFactory.getLogger(HouseCheckContext.class);
	
	@Autowired
	private HouseCheckRepository houseCheckRepository;
	
	/**
	 * 配置id
	 */
	private String id;
	
	/**
	 * 房屋验收单值对象
	 */
	private HouseCheckVo houseCheckVo;
	
	/**
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	private static HouseCheckContext getInstance() {
		return SpringBeanUtil.getBean(HouseCheckContext.class);
	}
	
	/**
	 * 根据房屋验收单值对象获取上下文对象
	 * @param houseCheck
	 * @return 
	 * @author xhw
	 * @date 2016年5月4日 上午10:42:53
	 */
	public static HouseCheckContext build(HouseCheckVo houseCheck) {
		HouseCheckContext houseCheckContext = getInstance();
		houseCheckContext.setHouseCheckVo(houseCheck);
		
		return houseCheckContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 房屋验收单上下文
	 */
	public static HouseCheckContext build() {
		HouseCheckContext houseCheckContext = getInstance();
		
		return houseCheckContext;
	}
	
	/**
	 * 根据id获取上下文对象
	 * @param id
	 * @return 
	 * @author xhw
	 * @date 2016年5月4日 上午10:44:08
	 */
	public static HouseCheckContext loadById(String id) {
		HouseCheckContext houseCheckContext = getInstance();
		houseCheckContext.setId(id);
		
		return houseCheckContext;
	}
	
	/**
	 * 获取房屋验收单
	 * @return 
	 * @author xhw
	 * @date 2016年5月4日 上午11:02:29
	 */
	public HouseCheckVo get() {
		if(houseCheckVo == null) {
			if(ValidateHelper.isNotEmptyString(id)) {
				HouseCheckPo houseCheckPo = houseCheckRepository.findOne(id);
				houseCheckVo = MyBeanUtil.createBean(houseCheckPo, HouseCheckVo.class);
				
				logger.info("[HouseCheckContext.get()] get houseCheckVo:{} by id:{}", houseCheckVo, id);
			}
			
		}
		
		return houseCheckVo;
	}
	
	protected void setId(String id) {
		this.id = id;
	}

	protected void setHouseCheckVo(HouseCheckVo houseCheckVo) {
		this.houseCheckVo = houseCheckVo;
	}
	
}
