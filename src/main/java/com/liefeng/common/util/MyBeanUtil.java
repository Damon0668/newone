package com.liefeng.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;

import com.alibaba.fastjson.JSON;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author  周俊迪
 * @version 1.0
 */
public class MyBeanUtil extends BeanUtils {
	
	/**
	 * 根据原对象生成给定类型的bean（相同属性，且相同数据类型才赋值）
	 * @param origObj 原对象
	 * @param destClazz 目标bean类型
	 * @return 目标bean
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createBean(Object origObj, Class<T> destClazz) {
		T destObjct = null;
		if (origObj != null) {
			try {
				destObjct = (T) Class.forName(destClazz.getName())
						.newInstance();
				convert(destObjct, origObj);
			} catch (Exception ignore) {}
		}
		
		return destObjct;
	}
	
	/**
	 * 根据原对象列表生成给定类型的bean列表
	 * @param origList 原对象列表
	 * @param destClazz 目标bean类型
	 * @return 目标bean列表
	 */
	public static <T> List<T> createList(List<?> origList, Class<T> destClazz){
		List<T> destObjList = null;
		if(ValidateHelper.isNotEmptyCollection(origList)){
			destObjList = new ArrayList<T>(origList.size());
			for (Object origObj : origList) {
				T destObj = createBean(origObj, destClazz);
				if(destObj != null) {
					destObjList.add(destObj);
				}
			}
		}
		
		return destObjList;
	} 
	
    private static void convert(Object dest, Object orig) {

      // Validate existence of the specified beans
      if (dest == null) {
          throw new IllegalArgumentException
              ("No destination bean specified");
      }
      if (orig == null) {
          throw new IllegalArgumentException("No origin bean specified");
      }

      // Copy the properties, converting as necessary
      if (orig instanceof DynaBean) {
          DynaProperty origDescriptors[] =
              ( (DynaBean) orig).getDynaClass().getDynaProperties();
          for (int i = 0; i < origDescriptors.length; i++) {
              String name = origDescriptors[i].getName();
              if (PropertyUtils.isWriteable(dest, name)) {
                  Object value = ( (DynaBean) orig).get(name);
                  try {
                      copyProperty(dest, name, value);
                  }
                  catch (Exception e) {
                      ; // Should not happen
                  }

              }
          }
      }
      else if (orig instanceof Map) {
          Iterator names = ( (Map) orig).keySet().iterator();
//          Iterator<?> names = ( (Map<?,?>) orig).entrySet().iterator(); //使用效率更高的entrySet
          while (names.hasNext()) {
              String name = (String) names.next();
              if (PropertyUtils.isWriteable(dest, name)) {
                  Object value = ( (Map) orig).get(name);
                  try {
                      copyProperty(dest, name, value);
                  }
                  catch (Exception e) {
                      ; // Should not happen
                  }

              }
          }
      }
      else
      /* if (orig is a standard JavaBean) */
      {
          PropertyDescriptor origDescriptors[] =
              PropertyUtils.getPropertyDescriptors(orig);
          for (int i = 0; i < origDescriptors.length; i++) {
              String name = origDescriptors[i].getName();
//              String type = origDescriptors[i].getPropertyType().toString();
              if ("class".equals(name)) {
                  continue; // No point in trying to set an object's class
              }
              if (PropertyUtils.isReadable(orig, name) &&
                  PropertyUtils.isWriteable(dest, name)) {
                  try {
                      Object value = PropertyUtils.getSimpleProperty(orig, name);
                      copyProperty(dest, name, value);
                  }
                  catch (java.lang.IllegalArgumentException ie) {
                      ; // Should not happen
                  }
                  catch (Exception e) {
                      ; // Should not happen
                  }

              }
          }
      }

  }

  
  /**
	 * 对象拷贝
	 * 数据对象空值不拷贝到目标对象
	 * 
	 * @param databean 数据源对象
	 * @param tobean 目标对象
	 * @throws NoSuchMethodException
	 * copy
	 */
  public static void copyBeanNotNull2Bean(Object databean, Object tobean)
  {
	  PropertyDescriptor origDescriptors[] =
          PropertyUtils.getPropertyDescriptors(databean);
      for (int i = 0; i < origDescriptors.length; i++) {
          String name = origDescriptors[i].getName();
//          String type = origDescriptors[i].getPropertyType().toString();
          if ("class".equals(name)) {
              continue; // No point in trying to set an object's class
          }
          if (PropertyUtils.isReadable(databean, name) &&
              PropertyUtils.isWriteable(tobean, name)) {
              try {
                  Object value = PropertyUtils.getSimpleProperty(databean, name);
                  if(value!=null){
                	    copyProperty(tobean, name, value);
                  }
              }
              catch (java.lang.IllegalArgumentException ie) {
                  ; // Should not happen
              }
              catch (Exception e) {
                  ; // Should not happen
              }

          }
      }
  }
  
  
  /**
   * 把orig和dest相同属性的value复制到dest中
   * @param dest
   * @param orig
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static void copyBean2Bean(Object dest, Object orig) {
      convert(dest, orig);
  }

  public static void copyBean2Map(Map map, Object bean){
	PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(bean);
	for (int i =0;i<pds.length;i++)
	{
		PropertyDescriptor pd = pds[i];
		String propname = pd.getName();
		if(propname.equals("class"))
			continue;
		try {
			Object propvalue = PropertyUtils.getSimpleProperty(bean,propname);
			map.put(propname, propvalue);
		} catch (IllegalAccessException e) {
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			//e.printStackTrace();
		} catch (NoSuchMethodException e) {
			//e.printStackTrace();
		}
	}
  }
  
  public static void copyBean2Map(Map map, Object bean,String keyPrefix){
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(bean);
		for (int i =0;i<pds.length;i++)
		{
			PropertyDescriptor pd = pds[i];
			String propname = pd.getName();
			if(propname.equals("class"))
				continue;
			try {
				Object propvalue = PropertyUtils.getSimpleProperty(bean,propname);
				if( propvalue instanceof Map ){
					continue;
				}
				map.put(keyPrefix+propname, propvalue);
			} catch (IllegalAccessException e) {
				//e.printStackTrace();
			} catch (InvocationTargetException e) {
				//e.printStackTrace();
			} catch (NoSuchMethodException e) {
				//e.printStackTrace();
			}
		}
	  }

  /**
   * 将Map内的key与Bean中属性相同的内容复制到BEAN中
   * @param bean Object
   * @param properties Map
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static void copyMap2Bean(Object bean, Map properties) throws
      IllegalAccessException, InvocationTargetException {
      // Do nothing unless both arguments have been specified
      if ( (bean == null) || (properties == null)) {
          return;
      }
      // Loop through the property name/value pairs to be set
      Iterator names = properties.keySet().iterator();
//      Iterator<?> names = properties.entrySet().iterator(); //使用效率更高的entrySet
      while (names.hasNext()) {
          String name = (String) names.next();
          // Identify the property name and value(s) to be assigned
          if (name == null) {
              continue;
          }
          Object value = properties.get(name);
          try {
              Class clazz = PropertyUtils.getPropertyType(bean, name);
              if (null == clazz) {
                  continue;
              }
              String className = clazz.getName();
              if (className.equalsIgnoreCase("java.sql.Timestamp")) {
                  if (value == null || value.equals("")) {
                      continue;
                  }
              }
              setProperty(bean, name, value);
          }
          catch (NoSuchMethodException e) {
              continue;
          }
      }
  }
  

  /**
   * 自动转Map key值大写
   * 将Map内的key与Bean中属性相同的内容复制到BEAN中
   * @param bean Object
   * @param properties Map
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static void copyMap2Bean_Nobig(Object bean, Map properties) throws
      IllegalAccessException, InvocationTargetException {
      // Do nothing unless both arguments have been specified
      if ( (bean == null) || (properties == null)) {
          return;
      }
      // Loop through the property name/value pairs to be set
      Iterator names = properties.keySet().iterator();
//      Iterator<?> names = properties.entrySet().iterator(); //使用效率更高的entrySet
      while (names.hasNext()) {
          String name = (String) names.next();
          // Identify the property name and value(s) to be assigned
          if (name == null) {
              continue;
          }
          Object value = properties.get(name);
          // 命名应该大小写应该敏感(否则取不到对象的属性)
          //name = name.toLowerCase();
          try {
        	  if (value == null) {	// 不光Date类型，好多类型在null时会出错
                  continue;	// 如果为null不用设 (对象如果有特殊初始值也可以保留？)
              }
              Class clazz = PropertyUtils.getPropertyType(bean, name);
              if (null == clazz) {	// 在bean中这个属性不存在
                  continue;
              }
              String className = clazz.getName();
              // 临时对策（如果不处理默认的类型转换时会出错）
              if (className.equalsIgnoreCase("java.util.Date")) {
                  value = new java.util.Date(((java.sql.Timestamp)value).getTime());// wait to do：貌似有时区问题, 待进一步确认
              }
//              if (className.equalsIgnoreCase("java.sql.Timestamp")) {
//                  if (value == null || value.equals("")) {
//                      continue;
//                  }
//              }
              setProperty(bean, name, value);
          }
          catch (NoSuchMethodException e) {
              continue;
          }
      }
  }

  /**
   * Map内的key与Bean中属性相同的内容复制到BEAN中
   * 对于存在空值的取默认值
   * @param bean Object
   * @param properties Map
   * @param defaultValue String
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static void copyMap2Bean(Object bean, Map properties, String defaultValue) throws
      IllegalAccessException, InvocationTargetException {
      // Do nothing unless both arguments have been specified
      if ( (bean == null) || (properties == null)) {
          return;
      }
      // Loop through the property name/value pairs to be set
      Iterator names = properties.keySet().iterator();
//      Iterator<?> names = properties.entrySet().iterator(); //使用效率更高的entrySet
      while (names.hasNext()) {
          String name = (String) names.next();
          // Identify the property name and value(s) to be assigned
          if (name == null) {
              continue;
          }
          Object value = properties.get(name);
          try {
              Class clazz = PropertyUtils.getPropertyType(bean, name);
              if (null == clazz) {
                  continue;
              }
              String className = clazz.getName();
              if (className.equalsIgnoreCase("java.sql.Timestamp")) {
                  if (value == null || value.equals("")) {
                      continue;
                  }
              }
              if (className.equalsIgnoreCase("java.lang.String")) {
                  if (value == null) {
                      value = defaultValue;
                  }
              }
              setProperty(bean, name, value);
          }
          catch (NoSuchMethodException e) {
              continue;
          }
      }
  }
  
  public MyBeanUtil() {
    super();
  }
  
  /**
	 * 强制复制，只要变量名称相同，就复制
	 * @param orig  被复制的对象
	 * @param cls 泛型类
	 * @return
	 */
	public static <T> T copyObjectForce(Object orig, Class<T> cls){
		String json = JSON.toJSONString(orig);
		return JSON.parseObject(json, cls);
	}
	
	/**
	 * 强制复制，只要变量名称相同，就复制
	 * @param list  被复制的对象
	 * @param cls  泛型类
	 * @return
	 */
	public static <T> List<T> copyListForce(List list, Class<T> cls){
		ArrayList<T> tempList = null;
		if(list != null){
			tempList = new ArrayList<T>();
			for (Object obj : list) {
				T t = copyObjectForce(obj, cls);
				tempList.add(t);
			}
		}
		return tempList;
	}
	
	/**
	 * 强制复制，只要变量名称相同，就复制
	 * @param map 被复制的对象
	 * @param cls  泛型类
	 * @return
	 */
	public static <T> Map<String, T> copyMapForce(Map map, Class<T> cls){
		HashMap<String, T> tempMap = null;
		if(map != null){
			tempMap = new HashMap<String, T>();
			for (Object key : map.keySet()) {
				T t = copyObjectForce(map.get(key), cls);
				tempMap.put(key.toString(), t);
			}
		}
		return tempMap;
	}
	
	
	/**
	 * 普通复制，变量名称相同，且数据类型相同
	 * @param map 被复制的对象
	 * @param destCls  复制的类
	 * @return
	 */
	public static <T> Map<String, T> copyMap(Map map, Class<T> destCls){
		
		Map<String, T> ts = null;
		if(map != null){
			ts = new HashMap<String, T>();
			for (Object key : map.keySet()) {
				T t = createBean(map.get(key), destCls);
				ts.put(key.toString(), t);
			}
		}
		return ts;
	}
	
}
