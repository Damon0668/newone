package com.liefeng.property.repository.guard;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.guard.CameraPo;

/**
 * 摄像头仓储
 * @author 蔡少东
 * @date 2016年4月13日
 */
@Transactional
public interface CameraRepository extends JpaRepository<CameraPo, String>{
	/**查询摄像头设备
	 * @param type 摄像头类型  0 独立 1 附属
	 * @param oemCode
	 * @return
	 */
	List<CameraPo> findByTypeAndOemCode(String type,String oemCode);
}
