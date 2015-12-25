package com.liefeng.common.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 * @author Huangama
 * @date 2015-12-16
 */
public class TimeUtil {

	public static final String PATTERN_1 = "yyyy-MM-dd";
	public static final String PATTERN_2 = "yyyy/MM/dd";
	public static final String PATTERN_3 = "yyyy.MM.dd";
	public static final String PATTERN_4 = "yyyyMMdd";
	public static final String PATTERN_5 = "yyyy.MM.dd HH:mm:ss";
	public static final String PATTERN_6 = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_7 = "yyyy/MM/dd HH:mm:ss";
	public static final String PATTERN_8 = "yyyyMMddHHmmss";
	public static final String PATTERN_9 = "yy/MM/dd";
	public static final String PATTERN_10 = "yy-MM-dd";
	public static final String PATTERN_11 = "yy.MM.dd";
	public static final String PATTERN_12 = "yyMMdd";
	public static final String PATTERN_13 = "yy.MM.dd HH:mm:ss";
	public static final String PATTERN_14 = "yy-MM-dd HH:mm:ss";
	public static final String PATTERN_15 = "yy/MM/dd HH:mm:ss";
	public static final String PATTERN_16 = "yyMMddHHmmss";
	
	/**
	 * 获取当前时间
	 * @return yyyy-MM-dd格式的时间
	 */
	public static String getNowTime()
	{
		return getNowTime(PATTERN_1);
	}
	
	/**
	 * 获取当前时间
	 * @param dateformat 时间格式
	 * @return 当前时间
	 */
	public static String getNowTime(String dateformat)
	{
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
		String formatedTime = dateFormat.format(now);
		
		return formatedTime;
	}
	
	/**
	 * 获取给定日期的前几天
	 * @param oldDate 给定的日期
	 * @param days 提前的天数
	 * @return 日期对象
	 */
	public static Date getDayBefore(Date oldDate, int days)
	{
		Calendar calendar = Calendar.getInstance();  	//得到日历
		calendar.setTime(oldDate);						//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -days); 	//设置为前days天
		
		return calendar.getTime();
	}
	
	/**
	 * 获取给定日期的后几天
	 * @param oldDate 给定的日期
	 * @param days 推后的天数
	 * @return 日期对象
	 */
	public static Date getDayAfter(Date oldDate, int days)
	{
		Calendar calendar = Calendar.getInstance();  	//得到日历
		calendar.setTime(oldDate);						//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, days); 		//设置为后days天
		
		return calendar.getTime();
	}
	
	/**
	 * 获取给定日期的前几个月的日期
	 * @param oldDate 给定的日期
	 * @param months 提前的月数
	 * @return 日期对象
	 */
	public static Date getDayBeforeByMonth(Date oldDate, int months)
	{
		Calendar calendar = Calendar.getInstance();  	//得到日历
		calendar.setTime(oldDate);						//把当前时间赋给日历
		calendar.add(Calendar.MONTH, -months); 	//设置为前months月
		
		return calendar.getTime();
	}
	
	/**
	 * 去掉日期中的时分秒
	 * @param date 日期
	 * @return 不含时分秒的日期
	 */
	public static Date getDate(Date date) {
		return TimeUtil.format(TimeUtil.format(date, PATTERN_1), PATTERN_1);
	}
	
	/**
	 * 日期格式化
	 * @param date 日期
	 * @param format 日期格式
	 * @return 格式化后的日期字符串
	 */
	public static String format(Date date, String format)
	{
		if (date == null || ValidateHelper.isEmptyString(format))
		{
			return null;
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String formatedTime = dateFormat.format(date);
		
		return formatedTime;
	}
	
	/**
	 * 日期格式化
	 * @param dateStr 日期字符串
	 * @param format 日期格式
	 * @return 格式化后的日期对象
	 */
	public static Date format(String dateStr, String format)
	{
		if (ValidateHelper.isEmptyString(dateStr) || ValidateHelper.isEmptyString(format))
		{
			return null;
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat(format);
	    ParsePosition pos = new ParsePosition(0);
	    
	    Date date = formatter.parse(dateStr, pos);
	    
	    return date;
	}
	
	/**
	 * 获取当前年份获取当前年份的后两位(eg:2014年1月8日，返回2014)
	 * @return 当前年份
	 */
	public static int getCurrentYear()
	{
		Calendar cal = Calendar.getInstance();
	    return cal.get(Calendar.YEAR);
	}
	
	/**
	 * 获取当前年份的后两位(eg:2014年1月8日，返回14)
	 * @return 当前年份的后两位
	 */
	public static String getCurrentSimpleYear()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yy", Locale.CHINESE);
		return formatter.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * 获取当前月份(eg:2014年1月8日，返回1)
	 * @return 当前月份
	 */
	public static int getCurrentMonth()
	{
		Calendar cal = Calendar.getInstance();
	    return cal.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 获取当前月份(eg:2014年1月8日，返回01)
	 * @return 当前月份
	 */
	public static String getCurrentFullMonth()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("MM", Locale.CHINESE);
		return formatter.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * 获取当前日(eg:2014年1月8日，返回8)
	 * @return 当前日
	 */
	public static int getCurrentDay()
	{
		Calendar cal = Calendar.getInstance();
	    return cal.get(Calendar.DATE);
	}
	
	/**
	 * 获取当前日(eg:2014年1月8日，返回08)
	 * @return 当前日
	 */
	public static String getCurrentFullDay()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd", Locale.CHINESE);
		return formatter.format(Calendar.getInstance().getTime());
	}
	
	public static void main(String[] args) {
		System.out.println(TimeUtil.getDayBeforeByMonth(new Date(), 1));
		System.out.println(TimeUtil.getDayBeforeByMonth(new Date(), 3));
		System.out.println(TimeUtil.getDayBeforeByMonth(new Date(), 6));
		
		System.out.println(TimeUtil.getDate(TimeUtil.getDayBeforeByMonth(new Date(), 1)));
		System.out.println(TimeUtil.getDate(TimeUtil.getDayBeforeByMonth(new Date(), 3)));
		System.out.println(TimeUtil.getDate(TimeUtil.getDayBeforeByMonth(new Date(), 6)));
	}
}
