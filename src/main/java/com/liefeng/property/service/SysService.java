package com.liefeng.property.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.intf.property.ISysService;
import com.liefeng.intf.service.cache.IRedisService;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.domain.sys.SysDictContext;
import com.liefeng.property.vo.sys.SysDictVo;

/**
 * 系统服务（字典、系统参数相关）实现类
 * @author Huangama
 * @date 2016-2-20
 */
@Service
public class SysService implements ISysService {

	private static Logger logger = LoggerFactory.getLogger(SysService.class);
			
	@Autowired
	private IRedisService redisService;
	
	@Override
	public List<SysDictVo> getDictByGroupCode(String groupCode) {
		List<SysDictVo> dictList = new ArrayList<SysDictVo>();
		
		if (ValidateHelper.isNotEmptyString(groupCode)) {
			String groupCodeKey = groupCode + SysConstants.DICT_CACHE_KEY_SUFFIX;
			List<Serializable> dictStrList = null;
			
			//First, get dictionary from cache
			try {
				dictStrList = redisService.listView(groupCodeKey);
				dictList = MyBeanUtil.createList(dictStrList, SysDictVo.class);
				return dictList;
			} catch (Exception e) {
				logger.error("Get dictionary '{}' from redis failed: ", groupCode, e);
			}
			
			//Not hit cache, load from database
			if (ValidateHelper.isEmptyCollection(dictStrList)) {
				SysDictContext sysDictContext = SysDictContext.build(groupCode);
				dictList = sysDictContext.get();
				logger.info("Load dictionary '{}' from database.", groupCode);
				
				//Add to cache
				if (ValidateHelper.isNotEmptyCollection(dictList)) {
					logger.info("Successfully load dictionary '{}' from database, and add them to cache.", groupCode);
					
					try {
						for (SysDictVo dict: dictList) {
							redisService.listAdd(groupCodeKey, dict.toString());
						}
					} catch (Exception e) {
						logger.error("Add dictionary '{}' to redis failed: ", groupCode, e);
					}
				}
			}
		}
		
		return dictList;
	}

}
