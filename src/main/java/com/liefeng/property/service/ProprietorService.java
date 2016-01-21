package com.liefeng.property.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.base.vo.UserVo;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.intf.service.tcc.ITccMsgDubboService;
import com.liefeng.mq.type.TccBasicEvent;
import com.liefeng.property.domain.household.ProprietorContext;
import com.liefeng.property.vo.household.ProprietorVo;

@Service
@Transactional(rollbackOn=Exception.class)
public class ProprietorService {
	
	@Autowired
	private ITccMsgDubboService tccMsgDubboService;
	
	public void createProprietor(ProprietorVo proprietor) throws Exception{
		proprietor = ProprietorContext.build(proprietor).create();
		ContextManager.getInstance().setOemCode(proprietor.getOemCode());
		UserVo user = new UserVo();
		user.setCreateTime(new Date());
		tccMsgDubboService.sendTccMsg(TccBasicEvent.CREATE_CUSTOMER, user.toString());
	}
	
}
