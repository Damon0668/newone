package com.liefeng.property.api.common;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataListValue;
import com.liefeng.core.entity.DataValue;
import com.liefeng.intf.property.ISysService;
import com.liefeng.property.api.ro.common.DictGroupRo;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.vo.sys.SysDictVo;

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
	
	@Autowired
	private ISysService sysService;
	
	@ApiOperation(value="获取系统时间", notes="服务可用检测")
	@RequestMapping(value="/getServerTime", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<Long> getServerTime(){
		return DataValue.success(new Date().getTime());
	}
	
	@ApiOperation(value="获取字典", notes="获取字典")
	@RequestMapping(value="/dictList", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<SysDictVo> dictList(@Valid @ModelAttribute DictGroupRo dictGroupRo){
		List<SysDictVo> list = sysService.getDictByGroupCode(dictGroupRo.getGroupCode());
		return DataListValue.success(list);
	}
}
