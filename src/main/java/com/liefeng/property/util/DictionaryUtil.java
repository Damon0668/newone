package com.liefeng.property.util;

import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.annotation.Dict;
import com.liefeng.property.domain.sys.SysDictContext;
import com.liefeng.property.vo.sys.SysDictVo;

/**
 * 字典操作工具类
 * 
 * @author ZhenTingJun
 * @date 2016年3月30日
 */
public class DictionaryUtil {

	private static Logger logger = LoggerFactory.getLogger(DictionaryUtil.class);

	/**
	 * 将对象中标注有字典注解的属性进行【值-名】转换
	 * @param object 转换对象
	 * @return 转换后的对象
	 */
	public static <T> T transformDicValueToDicName(T object) {
		Class<?> realClass = object.getClass();
		Field[] fields = realClass.getDeclaredFields();

		for (Field field : fields) {
			Dict dicAnnotation = field.getAnnotation(Dict.class); // 获取属性上字典注解
			if (dicAnnotation != null) {
				try {
					String groupCode = dicAnnotation.group(); // 字典组编码
					field.setAccessible(true); // 设置私有属性可以访问
					String fieldVaule = (String) field.get(object); // 获取属性值

					SysDictContext sysDictContext = SysDictContext.build(groupCode);
					SysDictVo sysDict = sysDictContext.getByValue(fieldVaule); // 获取对应字典记录

					if (sysDict != null) { // 将属性原值使用字典名字替换
						String dicName = sysDict.getName();
						field.set(object, dicName);
					}
				} catch (Exception e) {
					logger.error("字典值名转换失败，报错异常{}", e);
				}
			}
		}

		return object;
	}

	/**
	 * 将对象中标注有字典注解的属性进行【值-名】转换
	 * @param list 转换对象列表
	 * @return 转换后的对象列表
	 */
	public static <T> List<T> transformDicValueToDicName(List<T> list) {
		if (ValidateHelper.isNotEmptyCollection(list)) {
			for (T object : list) {
				Class<?> realClass = object.getClass();
				Field[] fields = realClass.getDeclaredFields();

				for (Field field : fields) {
					Dict dicAnnotation = field.getAnnotation(Dict.class); // 获取属性上字典注解
					if (dicAnnotation != null) {
						try {
							String groupCode = dicAnnotation.group(); // 字典组编码
							field.setAccessible(true); // 设置私有属性可以访问
							String fieldVaule = (String) field.get(object); // 获取属性值

							SysDictContext sysDictContext = SysDictContext.build(groupCode);
							SysDictVo sysDict = sysDictContext.getByValue(fieldVaule); // 获取对应字典记录

							if (sysDict != null) { // 将属性原值使用字典名字替换
								String dicName = sysDict.getName();
								field.set(object, dicName);
							}
						} catch (Exception e) {
							logger.error("字典值名转换失败，报错异常{}", e);
						}
					}
				}
			}
		}

		return list;
	}
	
	/**
	 * 将对象中标注有字典注解的属性进行【名-值】转换
	 * @param object 转换对象
	 * @return 转换后的对象
	 */
	public static <T> T transformDicNameToDicValue(T object) {
		Class<?> realClass = object.getClass();
		Field[] fields = realClass.getDeclaredFields();

		for (Field field : fields) {
			Dict dicAnnotation = field.getAnnotation(Dict.class); // 获取属性上字典注解
			if (dicAnnotation != null) {
				try {
					String groupCode = dicAnnotation.group(); // 字典组编码
					field.setAccessible(true); // 设置私有属性可以访问
					String fieldVaule = (String) field.get(object); // 获取属性值

					SysDictContext sysDictContext = SysDictContext.build(groupCode);
					SysDictVo sysDict = sysDictContext.getByName(fieldVaule); // 获取对应字典记录

					if (sysDict != null) { // 将属性原值使用字典值替换
						String dicValue = sysDict.getValue();
						field.set(object, dicValue);
					}
				} catch (Exception e) {
					logger.error("字典值名转换失败，报错异常{}", e);
				}
			}
		}

		return object;
	}

	/**
	 * 将对象中标注有字典注解的属性进行【名-值】转换
	 * @param list 转换对象列表
	 * @return 转换后的对象列表
	 */
	public static <T> List<T> transformDicNameToDicValue(List<T> list) {
		if (ValidateHelper.isNotEmptyCollection(list)) {
			for (T object : list) {
				Class<?> realClass = object.getClass();
				Field[] fields = realClass.getDeclaredFields();

				for (Field field : fields) {
					Dict dicAnnotation = field.getAnnotation(Dict.class); // 获取属性上字典注解
					if (dicAnnotation != null) {
						try {
							String groupCode = dicAnnotation.group(); // 字典组编码
							field.setAccessible(true); // 设置私有属性可以访问
							String fieldVaule = (String) field.get(object); // 获取属性值

							SysDictContext sysDictContext = SysDictContext.build(groupCode);
							SysDictVo sysDict = sysDictContext.getByName(fieldVaule); // 获取对应字典记录

							if (sysDict != null) { // 将属性原值使用字典值替换
								String dicValue = sysDict.getValue();
								field.set(object, dicValue);
							}
						} catch (Exception e) {
							logger.error("字典值名转换失败，报错异常{}", e);
						}
					}
				}
			}
		}

		return list;
	}
}
