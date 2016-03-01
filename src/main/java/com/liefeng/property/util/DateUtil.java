package com.liefeng.property.util;

import java.util.Calendar;
import java.util.Date;

import com.liefeng.common.util.TimeUtil;


public class DateUtil {
	/**
	 * 获取thisdate从属分期的时间段
	 * 
	 * @param thisdate
	 *            当前时间，可指定
	 * @param cycle
	 *            周期度量，例2个月一次则为2
	 * @param startMonth起始月份
	 *            ，一般设为1，即1月份开始
	 * @return 数组 date[0]为周期的起始时间，date[1]为结束时间
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	public static Date[] getCurrentDate(Date thisdate, int cycle, int startMonth) {

		Date[] date = new Date[2];
		@SuppressWarnings("deprecation")
		int thismonth = thisdate.getMonth() + 1;
		int count = thismonth / cycle;
		int i = thismonth % cycle;
		int start = 0;
		int end = 0;
		if (i == 0) {
			start = (count - 1) * cycle + 1;
			end = count * cycle;
		} else {
			start = count * cycle + 1;
			end = (count + 1) * cycle;
		}
		start += startMonth - 1;
		end += startMonth - 1;
		// System.out.println(start+"---------"+end);
		// 字符串转换为日期
		Date startdate = null;
		Date enddate = null;
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(thisdate);
			c.add(2, start - thismonth);
			startdate = c.getTime();

			c.setTime(startdate);
			c.add(2, end - start);
			enddate = c.getTime();

		} catch (Exception e) {
		}
		date[0] = TimeUtil.format(TimeUtil.format(startdate, TimeUtil.PATTERN_1),TimeUtil.PATTERN_1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(enddate);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		date[1] = TimeUtil.format(TimeUtil.format(calendar.getTime(), TimeUtil.PATTERN_1),TimeUtil.PATTERN_1);
		return date;
	}
}
