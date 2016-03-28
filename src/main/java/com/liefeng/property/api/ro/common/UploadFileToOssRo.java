package com.liefeng.property.api.ro.common;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 上传文件到OSS参数类
 * 
 * @author ZhenTingJun
 * @date 2013-03-28
 */
@ApiModel
public class UploadFileToOssRo extends BaseValue {

	private static final long serialVersionUID = -7920390802582811449L;

	@ApiModelProperty(value = "文件扩展名", required = true)
	@NotNull
	public String fileExtension;

	@ApiModelProperty(value = "文件字节数组", required = true)
	@NotNull
	public byte contents[];

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public byte[] getContents() {
		return contents;
	}

	public void setContents(byte[] contents) {
		this.contents = contents;
	}
}
