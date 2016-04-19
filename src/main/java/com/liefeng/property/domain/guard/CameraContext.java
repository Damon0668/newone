package com.liefeng.property.domain.guard;

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
import com.liefeng.property.po.guard.CameraPo;
import com.liefeng.property.repository.guard.CameraRepository;
import com.liefeng.property.vo.guard.CameraVo;

/**
 * 摄像头
 * @author 蔡少东
 * @date 2016年4月13日
 */
@Service
@Scope("prototype")
public class CameraContext {
	
	private static Logger logger = LoggerFactory.getLogger(CameraContext.class);
	
	@Autowired
	private CameraRepository cameraRepository;
	
	private CameraVo camera;

	private static CameraContext getInstance() {
		CameraContext cameraContext =  SpringBeanUtil.getBean(CameraContext.class);
		return cameraContext;
	}
	
	public static CameraContext build() {
		CameraContext cameraContext = getInstance();
		return cameraContext;
	}
	
	public static CameraContext build(CameraVo camera) {
		CameraContext cameraContext = getInstance();
		cameraContext.setCamera(camera);
		return cameraContext;
	}
	
	public void create(){
		if(camera != null){
			CameraPo cameraPo = MyBeanUtil.createBean(camera, CameraPo.class);
			cameraPo.setId(UUIDGenerator.generate());
			cameraPo.setCreateTime(new Date());
			cameraPo.setOemCode(ContextManager.getInstance().getOemCode());
			cameraRepository.save(cameraPo);
		}
	}
	
	public void update(){
		if(camera != null && ValidateHelper.isNotEmptyString(camera.getId())){
			CameraPo cameraPo = cameraRepository.findOne(camera.getId());
			MyBeanUtil.copyBeanNotNull2Bean(camera, cameraPo);
			cameraRepository.save(cameraPo);
		}
	}
	
	public List<CameraVo> listCarmerByType(String type){
		String oemCode = ContextManager.getInstance().getOemCode();
		List<CameraPo> dataList = cameraRepository.findByTypeAndOemCode(type, oemCode);
		if(ValidateHelper.isNotEmptyCollection(dataList)){
			return MyBeanUtil.createList(dataList, CameraVo.class);
		}else{
			return null;
		}
	}
	
	protected void setCamera(CameraVo camera) {
		this.camera = camera;
	}
	
}
