package com.liefeng.property.vo.approvalFlow;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * 流程定义复制类，由于一些变量无法序列化
 * @author john
 *
 */
public class ProcessVo extends BaseValue{
	
	
	private static final long serialVersionUID = -8048856632536493991L;
	
	@ApiModelProperty(value="流程主键")
	private String id;
	
	@ApiModelProperty(value="版本")
 	private Integer version;
    
	@ApiModelProperty(value="流程名称")
	private String name;
   
	@ApiModelProperty(value="流程显示名称")
	private String displayName;
   
	@ApiModelProperty(value="流程类型【1：审批流程 2：工单报事】")
	private String type;
	
	@ApiModelProperty(value="实例url")
	private String instanceUrl;
    
	@ApiModelProperty(value="流程是否可用")
	private Integer state;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
	@ApiModelProperty(value="创建人，目前为oem")
	private String creator;

	public String getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getType() {
		return type;
	}

	public String getInstanceUrl() {
		return instanceUrl;
	}

	public Integer getState() {
		return state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setInstanceUrl(String instanceUrl) {
		this.instanceUrl = instanceUrl;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
