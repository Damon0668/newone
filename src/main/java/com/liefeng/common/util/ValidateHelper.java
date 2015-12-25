package com.liefeng.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * 常用校验帮助类
 * @author Huangama
 * @date 2014-8-11
 */
public class ValidateHelper {
	
	/**
	 * 校验字符串是否为空
	 * @param str 待校验的字符串
	 * @return true:空；false：非空
	 */
	public static boolean isEmptyString(String str) {
		
		return null == str || str.trim().length() == 0;
	}
	
	/**
	 * 校验字符串是否为非空
	 * @param str 待校验的字符串
	 * @return true:非空；false：空
	 */
	public static boolean isNotEmptyString(String str) {
		return !isEmptyString(str);
	}
	
	/**
	 * 校验集合是否为空
	 * @param collection 待校验的集合
	 * @return true:空；false：非空
	 */
	public static boolean isEmptyCollection(Collection<?> collection) {
		
		return null == collection || collection.size() == 0;
	}
	
	/**
	 * 校验集合是否为非空
	 * @param collection 待校验的集合
	 * @return true:非空；false：空
	 */
	public static boolean isNotEmptyCollection(Collection<?> collection) {
		
		return !isEmptyCollection(collection);
	}
	
	/**
	 * 校验Map是否为空
	 * @param collection 待校验的Map
	 * @return true:空；false：非空
	 */
	public static boolean isEmptyMap(Map<?, ?> map) {
		
		return null == map || map.isEmpty();
	}
	
	/**
	 * 校验Map是否为非空
	 * @param collection 待校验的Map
	 * @return true:非空；false：空
	 */
	public static boolean isNotEmptyMap(Map<?, ?> map) {
		
		return !isEmptyMap(map);
	}
}
