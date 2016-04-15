package com.liefeng.property.api.finger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.base.bo.PushedMsgBo;
import com.liefeng.base.vo.message.PushedMsgVo;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.base.msg.IMessageCenterService;
import com.liefeng.property.api.ro.finger.msg.MsgHistoryRo;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.service.constant.PushMsgConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="消息模块")
@RestController
@RequestMapping(value = "/api/finger/msg")
public class MsgCenterController {
	
	@Autowired
	private IMessageCenterService messageCenterService;
	
	@ApiOperation(value="获取消息历史")
	@RequestMapping(value="/getMsgHistory", method=RequestMethod.GET)
	@ResponseBody
	public DataPageValue<PushedMsgVo> getMsgHistory(@Valid @ModelAttribute MsgHistoryRo msgHistoryRo){
		
		PushedMsgBo pushedMsgBo = new PushedMsgBo();
		pushedMsgBo.setUserId(msgHistoryRo.getUserId());
		pushedMsgBo.setSendStatus(PushMsgConstants.SendStatus.SUCCESS);
		pushedMsgBo.setTerminalType(PushMsgConstants.TerminalType.MOBILE_PROPERTY);
		if("sys".equals(msgHistoryRo.getMsgType())){
			pushedMsgBo.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
		}
		return messageCenterService.findPushedMsg(pushedMsgBo, msgHistoryRo.getPage(), msgHistoryRo.getSize());
	}
}
