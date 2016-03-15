package com.liefeng.property.domain.project;

import java.util.Date;

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
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.po.project.AppHomeImagePo;
import com.liefeng.property.repository.project.AppHomeImageRepository;
import com.liefeng.property.vo.project.AppHomeImageVo;

/**
 * 业主手机端首页轮播图领域模型
 * 
 * @author ZhenTingJun
 * @date 2016年3月11日
 */
@Service
@Scope("prototype")
public class AppHomeImageContext {
	
	private static Logger logger = LoggerFactory.getLogger(AppHomeImageContext.class);

	@Autowired
	private AppHomeImageRepository appHomeImageRepository;
	
	/**
	 * 轮播图ID
	 */
	private String appHomeImageId;
	
	/**
	 * 轮播图值对象
	 */
	private AppHomeImageVo appHomeImage;
	
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static AppHomeImageContext getInstance() {
		return SpringBeanUtil.getBean(AppHomeImageContext.class);
	}
	
	/**
	 * 根据轮播图值对象构建上下文
	 * @param appHomeImage 轮播图值对象
	 * @return 轮播图上下文
	 */
	public static AppHomeImageContext build(AppHomeImageVo appHomeImage) {
		AppHomeImageContext appHomeImageContext = getInstance();
		appHomeImageContext.setAppHomeImage(appHomeImage);
		
		return appHomeImageContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 轮播图上下文
	 */
	public static AppHomeImageContext build() {
		AppHomeImageContext appHomeImageContext = getInstance();
		
		return appHomeImageContext;
	}
	
	/**
	 * 根据轮播图ID加载项目上下文
	 * @param apphomeImageId 轮播图ID
	 * @return 轮播图上下文
	 */
	public static AppHomeImageContext loadById(String apphomeImageId) {
		AppHomeImageContext appHomeImageContext = getInstance();
		appHomeImageContext.setAppHomeImageId(apphomeImageId);
		
		return appHomeImageContext;
	}
	
	
	public AppHomeImageVo get() {
		if (appHomeImage == null) {
			
			AppHomeImagePo appHomeImagePo = null;
			if (ValidateHelper.isNotEmptyString(appHomeImageId)) {
				appHomeImagePo = appHomeImageRepository.findOne(appHomeImageId);
			}
			
			if (appHomeImagePo != null) {
				appHomeImage = MyBeanUtil.createBean(appHomeImagePo, AppHomeImageVo.class);
			}
		}
		
		return appHomeImage;
	}


	public AppHomeImageVo create() {
		if(appHomeImage != null) {
			
			appHomeImage.setId(UUIDGenerator.generate());
			appHomeImage.setCreateTime(new Date());

			AppHomeImagePo appHomeImagePo = MyBeanUtil.createBean(appHomeImage, AppHomeImagePo.class);
			appHomeImageRepository.save(appHomeImagePo);
		}
		
		return appHomeImage;
	}

	public AppHomeImageVo update() {
		if(appHomeImage != null && ValidateHelper.isNotEmptyString(appHomeImage.getId())) {
			logger.info("更新首页轮播图，轮播图ID={}", appHomeImage.getId());
			AppHomeImagePo appHomeImagePo = appHomeImageRepository.findOne(appHomeImage.getId());
			
			MyBeanUtil.copyBeanNotNull2Bean(appHomeImage, appHomeImagePo);
			appHomeImageRepository.save(appHomeImagePo);
			logger.info("更新首页轮播图成功，轮播图ID={}", appHomeImage.getId());
			
			// 更新后拷贝最新内容返回
			appHomeImage = MyBeanUtil.createBean(appHomeImagePo, AppHomeImageVo.class);
		}
		
		return appHomeImage;
	}

	public void delete() {
		if(appHomeImageId != null) {
			String[] ids = appHomeImageId.split(",");
			if(ids != null && ids.length > 0) {
				for(int i=0; i<ids.length; i++) {
					appHomeImageRepository.delete(ids[i]);
				}
			}
		}
	}
	
    /**
     * 分页查询首页轮播图
     * @param projectId 项目ID
     * @param page 分页大小
     * @param size 分页大小
     * @return 小区通告分页数据
     */
	public DataPageValue<AppHomeImageVo> findAppHomeImages(String projectId, int page, int size) {
		logger.info("首页轮播图分页查询，projectId={}，page={}，size={}", projectId, page, size);
		
		// spring-data的page从0开始
		Page<AppHomeImagePo> poPage = appHomeImageRepository.findByProjectIdOrderBySeq(
				projectId, new PageRequest(page - 1, size));
		Page<AppHomeImageVo> voPage = poPage.map(new Po2VoConverter<AppHomeImagePo,
				AppHomeImageVo>(AppHomeImageVo.class));
		
		return new DataPageValue<AppHomeImageVo>(voPage.getContent(), voPage.getTotalElements(), size, page);
	}

	protected void setAppHomeImageId(String appHomeImageId) {
		this.appHomeImageId = appHomeImageId;
	}

	protected void setAppHomeImage(AppHomeImageVo appHomeImage) {
		this.appHomeImage = appHomeImage;
	}
	
}
