package com.liefeng.property.api.common;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.core.entity.DataValue;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统接口
 * @author 蔡少东
 * @date 2016年4月5日
 */

@Api(value="系统模块")
@RestController
@RequestMapping(value = "/api/common/sys")
public class SysController {
	
	@ApiOperation(value="获取系统时间", notes="服务可用检测")
	@RequestMapping(value="/getServerTime", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<Long> getServerTime(){
		return DataValue.success(new Date().getTime());
	}
}
