package com.liefeng.property.api.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.core.entity.DataValue;
import com.liefeng.intf.service.ftp.IFileService;
import com.liefeng.property.api.ro.common.UploadFileToOssRo;

@Api(value="文件模块")
@RestController
@RequestMapping(value = "/api/common/file")
public class FileController {
	
	private static Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private IFileService fileService;
	
	@ApiOperation(value="上传文件到OSS")
	@RequestMapping(value="/uploadFileToOss", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<String> uploadFileToOss(@Valid @ModelAttribute UploadFileToOssRo uploadFileToOssRo) {
		String fileName = "fileName."+uploadFileToOssRo.getFileExtension(); // 临时文件名，仅作传参使用
		byte[] contents = uploadFileToOssRo.getContents();
		
		String fileUrl = fileService.uploadFile(fileName, contents); // 返回文件保存在服务器上的路径
		logger.info("文件访问路径为：{}", fileUrl);
		return DataValue.success(fileUrl);
	}
}
