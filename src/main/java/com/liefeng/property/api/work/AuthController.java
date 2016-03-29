package com.liefeng.property.api.work;

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
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.core.error.IErrorCode;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.intf.service.msg.ISmsService;
import com.liefeng.mq.type.SMSMsgEvent;
import com.liefeng.property.api.ro.finger.auth.UpdatePwdRo;
import com.liefeng.property.api.ro.work.auth.CheckMobileRo;
import com.liefeng.property.api.ro.work.auth.StaffLoginRo;
import com.liefeng.property.api.ro.work.auth.UpdatePwdLoginRo;
import com.liefeng.property.error.PropertyStaffErrorCode;
import com.liefeng.property.error.StaffErrorCode;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.property.vo.staff.StaffArchiveVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="权限模块")
@RestController("staffAuthController")
@RequestMapping(value = "/api/work/auth")
public class AuthController {
	
	private static Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private IPropertyStaffService propertyStaffService;
	
	@Autowired
	private ISmsService smsService;
	
	@ApiOperation(value="登陆", notes="登陆接口")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<PropertyStaffVo> login(@Valid @ModelAttribute StaffLoginRo staffLoginRo){

		PropertyStaffVo staff = propertyStaffService.findPropertyStaffByAccount(staffLoginRo.getAccount());
		
		if(staff == null){
			throw new LiefengException(PropertyStaffErrorCode.STAFF_NOT_EXIST);
		}
		
		if(!staff.getPassword().equals(staffLoginRo.getPassword())){
			throw new LiefengException(PropertyStaffErrorCode.PASSWORD_ERROR);
		}
		
		propertyStaffService.settIngStaffMsgClientId(staff.getId(), staffLoginRo.getClientId());

		return DataValue.success(staff);
	}
	
	@ApiOperation(value="手机号码有效性", notes="手机号码是否存在员工表中且激活在职")
	@RequestMapping(value="/checkMobileAvailable", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue checkMobileAvailable(@Valid @ModelAttribute CheckMobileRo checkMobileRo){
		
		PropertyStaffVo staff = propertyStaffService.findPropertyStaffByAccount(checkMobileRo.getAccount());
		
		if(staff != null){
			StaffArchiveVo staffArchive = propertyStaffService.findStaffArchByStaffId(staff.getId());
			if(checkMobileRo.getMobile().equals(staffArchive.getPhone())){
				return ReturnValue.success();
			}
		}

		throw new LiefengException(PropertyStaffErrorCode.Mobile_NOT_MATCHING);
	}
	
	
	@ApiOperation(value="忘记密码-修改密码", notes="忘记密码后,修改密码")
	@RequestMapping(value="/updatePwdByForget", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue updatePwdByForget(@Valid @ModelAttribute UpdatePwdRo updatePwdRo){
		
		smsService.verifySMSCode(updatePwdRo.getMobile(), SMSMsgEvent.SD_UPDATAPWD_MSG.getEventCode(), updatePwdRo.getCode());
		
		return ReturnValue.success();
	}
	
	@ApiOperation(value="登陆后-修改密码", notes="登陆后,修改密码")
	@RequestMapping(value="/updatePwdAfterLogin", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue updatePwdAfterLogin(@Valid @ModelAttribute UpdatePwdLoginRo updatePwdLoginRo){
		propertyStaffService.updateStaffPassword(updatePwdLoginRo.getStaffId(), updatePwdLoginRo.getOldpassword(), updatePwdLoginRo.getNewpassword());
		return ReturnValue.success();
	}
}
