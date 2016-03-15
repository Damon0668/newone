package com.liefeng.property.domain.project;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.vo.project.AppHomeImageVo;

/**
 * 首页轮播图领域模型测试类
 * 
 * @author ZhenTingJun
 * @date 2016年3月11日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("dev")
public class AppHomeImageContextTest {
	
	@Test
	public void create() {
		AppHomeImageVo appHomeImage = new AppHomeImageVo();
		appHomeImage.setImageUrl("http://wuye.oss-cn-shenzhen.aliyuncs.com/dev/property/property/20160310/20160310202504qVSsdD2R.jpg");
		appHomeImage.setStatus("0");
		appHomeImage.setProjectId("222");
		appHomeImage.setSeq(1);
		appHomeImage.setStartDate(new Date());
		appHomeImage.setEndDate(new Date());
		
		AppHomeImageContext appHomeImageContext = AppHomeImageContext.build(appHomeImage);
		appHomeImageContext.create();
		
		System.out.println(appHomeImageContext);
	}
	
	@Test
	public void get() {
		AppHomeImageContext appHomeImageContext = AppHomeImageContext.loadById("402881fd5372c81f015372c81f6a0000");
		AppHomeImageVo appHomeImage = appHomeImageContext.get();
		System.out.println(appHomeImage);
	}
	
	@Test
	public void update() {
		AppHomeImageVo appHomeImage = new AppHomeImageVo();
		appHomeImage.setId("402881fd5372c81f015372c81f6a0000");
		appHomeImage.setSeq(2);
		appHomeImage.setEndDate(new Date());
		
		AppHomeImageContext appHomeImageContext = AppHomeImageContext.build(appHomeImage);
		appHomeImage = appHomeImageContext.update();
		
		System.out.println(appHomeImage);
	}
	
	@Test
	public void findAppHomeImages() {
		
		AppHomeImageContext appHomeImageContext = AppHomeImageContext.build();
		DataPageValue<AppHomeImageVo> dataPage = appHomeImageContext.findAppHomeImages("222", 1, 10);
		
		System.out.println(dataPage);
	}
}
