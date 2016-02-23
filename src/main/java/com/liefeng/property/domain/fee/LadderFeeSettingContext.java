package com.liefeng.property.domain.fee;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.Po2VoConverter;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.property.error.FeeErrorCode;
import com.liefeng.property.exception.FeeException;
import com.liefeng.property.po.fee.LadderFeeSettingPo;
import com.liefeng.property.repository.LadderFeeSettingRepository;
import com.liefeng.property.vo.fee.LadderFeeSettingVo;

/**
 * 
 * <pre>
 * Title:阶梯费用领域模型
 * Description:
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  wuzhijing        
 * @version 1.0      
 * @created 2016年2月18日下午9:04:53
 * </pre>
 */
@Service
@Scope("prototype")
public class LadderFeeSettingContext {

	private static Logger logger = LoggerFactory
			.getLogger(LadderFeeSettingContext.class);
	
	@Autowired
	private LadderFeeSettingRepository ladderFeeSettingRepository;

	private LadderFeeSettingVo ladderFeeSetting;

	private String projectId;

	private String id;

	/**
	 * 获取本类实例，每次返回一个新对象
	 * 
	 * @return 本类实例
	 */
	private static LadderFeeSettingContext getInstance() {
		return SpringBeanUtil.getBean(LadderFeeSettingContext.class);
	}

	/**
	 * 获取本类实例，每次返回一个新对象
	 * 
	 * @param ladderFeeSetting
	 * @return
	 */
	public static LadderFeeSettingContext build(
			LadderFeeSettingVo ladderFeeSetting) {
		LadderFeeSettingContext ladderFeeSettingContext = getInstance();
		ladderFeeSettingContext.setLadderFeeSetting(ladderFeeSetting);
		return ladderFeeSettingContext;
	}

	/**
	 * 获取本类实例，每次返回一个新对象(项目id)
	 * 
	 * @param projectId
	 * @return
	 */
	public static LadderFeeSettingContext loadByProjectId(String projectId) {
		LadderFeeSettingContext ladderFeeSettingContext = getInstance();
		ladderFeeSettingContext.setProjectId(projectId);
		return ladderFeeSettingContext;
	}

	/**
	 * 获取本类实例，每次返回一个新对象(id)
	 * 
	 * @param id
	 * @return
	 */
	public static LadderFeeSettingContext loadById(String id) {
		LadderFeeSettingContext ladderFeeSettingContext = getInstance();
		ladderFeeSettingContext.setId(id);
		return ladderFeeSettingContext;
	}

	public void delete() throws LiefengException {
		logger.info("执行费用设置删除id:" + id);

		LadderFeeSettingPo ladderLadderFeeSettingPo = ladderFeeSettingRepository
				.findOne(id);
		if (ladderLadderFeeSettingPo == null) {
			throw new FeeException(FeeErrorCode.FEESETTING_NOT_EXISTS);
		}
		ladderFeeSettingRepository.delete(ladderLadderFeeSettingPo);
	}

	public void update() throws LiefengException {
		LadderFeeSettingPo ladderFeeSettingPo = ladderFeeSettingRepository
				.findOne(ladderFeeSetting.getId());
		if (ladderFeeSettingPo == null) {
			throw new FeeException(FeeErrorCode.LADDERFEESETTING_NOT_EXISTS);
		}

		// 判断是否存在相同仪表
		LadderFeeSettingPo ladderFeeSettingTypeIsExists = ladderFeeSettingRepository
				.findByProjectIdAndFeeType(ladderFeeSetting.getProjectId(),
						ladderFeeSetting.getFeeType());
		if (ladderFeeSettingTypeIsExists != null
				&& !ladderFeeSettingTypeIsExists.getId().equals(
						ladderFeeSetting.getId())) {
			throw new FeeException(FeeErrorCode.LADDERFEESETTING_EXISTS);
		}
		
		ladderFeeSettingPo.setFeeType(ladderFeeSetting.getFeeType());
		ladderFeeSettingPo.setLadder1(ladderFeeSetting.getLadder1());
		ladderFeeSettingPo.setLadder1Price(ladderFeeSetting.getLadder1Price());
		ladderFeeSettingPo.setLadder2(ladderFeeSetting.getLadder2());
		ladderFeeSettingPo.setLadder2Price(ladderFeeSetting.getLadder2Price());
		ladderFeeSettingPo.setLadder3Price(ladderFeeSetting.getLadder3Price());
		ladderFeeSettingPo.setProjectId(ladderFeeSetting.getProjectId());
		ladderFeeSettingPo.setUseType(ladderFeeSetting.getUseType());
		ladderFeeSettingRepository.save(ladderFeeSettingPo);
	}

	public LadderFeeSettingVo findById() {
		LadderFeeSettingPo ladderFeeSettingPo = ladderFeeSettingRepository
				.findOne(id);
		return MyBeanUtil.createBean(ladderFeeSettingPo,
				LadderFeeSettingVo.class);
	}

	public void create() throws LiefengException {
		if (ladderFeeSetting != null) {
			LadderFeeSettingPo ladderFeeSettingPo = ladderFeeSettingRepository
					.findByProjectIdAndFeeType(ladderFeeSetting.getProjectId(),
							ladderFeeSetting.getFeeType());
			if (ladderFeeSettingPo != null) {
				throw new FeeException(FeeErrorCode.LADDERFEESETTING_EXISTS);
			}
			ladderFeeSetting.setOemCode(ContextManager.getInstance()
					.getOemCode());
			ladderFeeSetting.setId(UUIDGenerator.generate());
			ladderFeeSetting.setCreateTime(new Date());
			ladderFeeSettingRepository.save(MyBeanUtil.createBean(
					ladderFeeSetting, LadderFeeSettingPo.class));
		}
	}

	public DataPageValue<LadderFeeSettingVo> findByProjectId4Page(
			 Integer currentPage,Integer pageSize) {
		Page<LadderFeeSettingVo> voPage = null;

		// 分页查询
		Pageable pageable = new PageRequest(currentPage - 1, pageSize);
		Page<LadderFeeSettingPo> ladderFeeSettingPage = ladderFeeSettingRepository
				.findByProjectId(projectId, pageable);

		// 转换
		voPage = ladderFeeSettingPage
				.map(new Po2VoConverter<LadderFeeSettingPo, LadderFeeSettingVo>(
						LadderFeeSettingVo.class));

		return new DataPageValue<LadderFeeSettingVo>(voPage.getContent(),
				voPage.getTotalElements(), pageSize, currentPage);
	}

	protected void setLadderFeeSetting(LadderFeeSettingVo ladderFeeSetting) {
		this.ladderFeeSetting = ladderFeeSetting;
	}

	protected void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	protected void setId(String id) {
		this.id = id;
	}
}
