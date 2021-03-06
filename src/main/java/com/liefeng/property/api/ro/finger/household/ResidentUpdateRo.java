package com.liefeng.property.api.ro.finger.household;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

/**
 * 修改住户资料参数封装类
 * @author xhw
 * @date 2016年3月21日 下午7:12:30
 */
@ApiModel
public class ResidentUpdateRo extends BaseValue {
	
	private static final long serialVersionUID = 1678474876917483111L;

	@ApiModelProperty(value="住户ID", required=true)
	@NotNull
	private String residentId;
	
	@ApiModelProperty(value="客户全局唯一标识", required=true)
	@NotNull
	private String custGlobalId;
	
	@ApiModelProperty(value="房间ID", required=true)
	@NotNull
	private String houseId;
	
	@ApiModelProperty(value="头像")
	private String pic;
	
	@ApiModelProperty(value="姓名", required=true)
	@NotNull
	private String name;
	
	@ApiModelProperty(value="性别", required=true)
	@NotNull
	private String sex;
	
	@ApiModelProperty(value="身份证号码", required=true)
	@NotNull
	private String idNum;
	
	@ApiModelProperty(value="手机号码", required=true)
	@NotNull
	private String mobile;
	
	@ApiModelProperty(value="与业主的关系", required=true)
	@NotNull
	private String residentRelation;
	
	@ApiModelProperty(value="工作单位")
	private String workUnit;
	
	@ApiModelProperty(value="籍贯")
	private String nativePlace;

	public String getResidentId() {
		return residentId;
	}

	public void setResidentId(String residentId) {
		this.residentId = residentId;
	}

	public String getCustGlobalId() {
		return custGlobalId;
	}

	public void setCustGlobalId(String custGlobalId) {
		this.custGlobalId = custGlobalId;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getResidentRelation() {
		return residentRelation;
	}

	public void setResidentRelation(String residentRelation) {
		this.residentRelation = residentRelation;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	

}
