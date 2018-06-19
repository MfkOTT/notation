package com.ski.notation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SimpleDateUtil {
	
	/**
	 * 获取指定时间的格式化串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
		return sdf.format(date);
	}
	
	/**
	 * 获取指定格式时间串的date值
	 * 
	 * @param date
	 * @param pattern 指定格式
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String date, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
		return sdf.parse(date);
	}
	
	/**
	 * 获取指定时间${date}的前/后${dayNum}天的零点毫秒值
	 * 
	 * @param date 指定时间
	 * @param dayNum 天数
	 */
	public static long getDateAlter(Date date, int dayNum) {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.setTime(date);
		cal.add(Calendar.DATE, dayNum);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	public static void main(String[] args) {
		long ti = getDateAlter(new Date(),1);
		System.out.println(ti);
		System.out.println(new Date(ti));
	}
}
