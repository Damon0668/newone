package com.liefeng.property.api.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.liefeng.core.entity.DataValue;
import com.liefeng.core.error.IErrorCode;
import com.liefeng.intf.service.ftp.IFileService;

@Api(value = "文件模块")
@RestController
@RequestMapping(value = "/api/common/file")
public class FileController {

	private static Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private IFileService fileService;

	@ApiOperation(value = "上传文件到OSS")
	@RequestMapping(value = "/uploadFileToOss", method = RequestMethod.POST)
	@ResponseBody
	public DataValue<String> uploadFileToOss(@ApiParam(value = "上传文件", required = true) @RequestParam MultipartFile file) {
		String fileName = file.getOriginalFilename(); // 文件名
		byte[] contents; // 文件字节数组
		
		try { 
			contents = file.getBytes();
		} catch (IOException e) {
			logger.error("上传文件失败，获取文件字节数据失败。exception=【{}】", e);
			return new DataValue<String>(IErrorCode.SYSTEM_ERROR, "获取文件字节数据失败");
		}

		String fileUrl = fileService.uploadFile(fileName, contents); // 返回文件保存在服务器上的路径
		logger.info("文件访问路径为：{}", fileUrl);
		return DataValue.success(fileUrl);
	}
}
