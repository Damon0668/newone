package com.liefeng.property.bo.workbench;

import com.liefeng.core.entity.BaseValue;

/**
 * 查看通知列表请求参数封装类
 * @author xhw
 * @date 2016年3月21日 下午2:01:02
 */
public class NoticeBo  extends BaseValue {
	private static final long serialVersionUID = -7597480780998364521L;

	private String terminal;
	
	private String noticeType;
	
	private String projectId;

	private String groupId;
	
	private String privilegeType;
	
	private Integer page;
	
	private Integer size;
	
	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getPrivilegeType() {
		return privilegeType;
	}

	public void setPrivilegeType(String privilegeType) {
		this.privilegeType = privilegeType;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
}
