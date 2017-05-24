package com.talkweb.ei.di.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {

	private static Log log = LogFactory.getLog(DateUtil.class);

	private static String[] days = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
			"星期六" };

	/**
	 * 获取今天的信息，格式如下：今天是 2010 年 1 月 22 日 星期五
	 * 
	 * @return
	 */
	public static String getTodayInfo() {
		GregorianCalendar cal = new GregorianCalendar();
		StringBuilder sb = new StringBuilder();
		sb.append("今天是 ");
		sb.append(cal.get(Calendar.YEAR));
		sb.append(" 年 ");
		sb.append(cal.get(Calendar.MONTH) + 1);
		sb.append(" 月 ");
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		sb.append(cal.get(Calendar.DAY_OF_MONTH));
		sb.append(" 日 ");
		sb.append(days[cal.get(Calendar.DAY_OF_WEEK) - 1]);
		return sb.toString();
	}

	/** 取得指定月份的第一天 */
	public static String getMonthBegin(String strdate) {
		java.util.Date date = parseDate(strdate);
		return formatDateByFormat(date, "yyyy-MM") + "-01";
	}
	
	/**  取得月份的第一天  无参数 默认为当月 */
	public static String getMonthBegin(){
		return getMonthBegin(formatDateByFormat(new Date(), "yyyy-MM-dd"));
	}

	/** 取得指定月份的最后一天 */
	public static String getMonthEnd(String strdate) {
		java.util.Date date = parseDate(getMonthBegin(strdate));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}
	
	public static String getMonthEnd(){
		return getMonthEnd(formatDateByFormat(new Date(), "yyyy-MM-dd"));
	}
	
	/** 取得当前月份的第一天 
	 *  dongqian
	 *  2010-6-30
	 * */
	public static String getCurMonthBegin() {
		java.util.Date date = new Date();
		return formatDateByFormat(date, "yyyy-MM") + "-01";
	}

	/** 取得当前月份的最后一天 
	 * dongqian
	 * 2010-6-30
	 * */
	public static String getCurMonthEnd() {
		java.util.Date date = parseDate(getCurMonthBegin());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}

	/** 常用的格式化日期 */
	public static String formatDate(java.util.Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	/** 以指定的格式来格式化日期 */
	public static String formatDateByFormat(java.util.Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/** 格式化日期 */
	public static java.util.Date parseDate(String dateStr, String format) {
		java.util.Date date = null;
		try {
			java.text.DateFormat df = new java.text.SimpleDateFormat(format);
			// String dt = Normal.parse(dateStr).replaceAll("-", "/");
			String dt = dateStr;
			if ((!dt.equals("")) && (dt.length() < format.length())) {
				dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
						"0");
			}
			date = (java.util.Date) df.parse(dt);
		} catch (Exception e) {
		}
		return date;
	}

	public static java.util.Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy-MM-dd");
	}

	public static java.util.Date parseDate(java.sql.Date date) {
		return date;
	}

	public static java.sql.Date parseSqlDate(java.util.Date date) {
		if (date != null)
			return new java.sql.Date(date.getTime());
		else
			return null;
	}

	public static java.sql.Date parseSqlDate(String dateStr, String format) {
		java.util.Date date = parseDate(dateStr, format);
		return parseSqlDate(date);
	}

	public static java.sql.Date parseSqlDate(String dateStr) {
		return parseSqlDate(dateStr, "yyyy-MM-dd");
	}

	public static Timestamp parseTimestamp(String dateStr, String format) {
		java.util.Date date = parseDate(dateStr, format);
		if (date != null) {
			long t = date.getTime();
			return new java.sql.Timestamp(t);
		} else
			return null;
	}

	public static Timestamp parseTimestamp(String dateStr) {
		return parseTimestamp(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	/** 格式化输出日期 */
	public static String format(java.util.Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new java.text.SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static String format(java.util.Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/** 返回年份 */
	public static int getYear(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}

	/** 返回月份 */
	public static int getMonth(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}

	/** 返回日份 */
	public static int getDay(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}

	/** 返回小时 */
	public static int getHour(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}

	/** 返回分钟 */
	public static int getMinute(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MINUTE);
	}

	/** 返回秒钟 */
	public static int getSecond(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}

	/** 返回毫秒 */
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/** 返回毫秒 */
	public static long getMillis() {
		return System.currentTimeMillis();
	}

	/** 返回字符型日期 */
	public static String getDate(java.util.Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/** 返回字符型时间 */
	public static String getTimeStr(java.util.Date date) {
		return format(date, "HH:mm:ss");
	}

	/** 返回字符型日期时间 */
	public static String getDateTime(java.util.Date date) {
		return format(date, "yyyy-MM-dd  HH:mm:ss");
	}

	/** 日期相加 */
	public static java.util.Date addDate(java.util.Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/** 日期相减 */
	public static int diffDate(java.util.Date date, java.util.Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/***************************************************************************
	 * 获得java时间，把符合格式的string ->date
	 **************************************************************************/
	public static Date getDateTime(String datetime) {
		/**
		 * yyyy-MM-dd HH:mm:ss" 24小时制 yyyy-mm-dd hh:mm:ss" 12小时制
		 * 
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * //规定日期格式 Date date = df.parse("2003-07-26 18:30:35");
		 * //将符合格式的String转换为Date String s = df.format(date);
		 * //将Date转换为符合格式的String
		 */

		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (datetime.length() < 11) {
			datetime = datetime + " 12:00:00";
		}

		try {
			java.util.Date udate = dt.parse(datetime);
			return udate;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 设定最大时间期限
	public static Date getEndDateTime() {
		String date = "2039-12-31 12:00:00";
		return getDateTime(date);

	}

	// 设定最大时间期限
	public static Date getbeginDateTime() {
		String date = "1970-01-01 12:00:00";
		return getDateTime(date);

	}

	// 返回当前时间戳(用于insert语句)--------------------------------
	public static java.sql.Timestamp getNowTimestamp() {
		return new java.sql.Timestamp((new java.util.Date()).getTime());
	}

	public static java.sql.Timestamp getTimestamp(Date date) {
		return new java.sql.Timestamp(date.getTime());
	}

	public static java.sql.Time getTime(Date date) {
		return new java.sql.Time(date.getTime());
	}

	public static java.sql.Time getNowTime() {
		return new java.sql.Time((new java.util.Date()).getTime());
	}

	public static Date getNowDate() {
		return new java.util.Date();

	}

	// 返回当前时间(格式化后)
	public static String getNowDateStr() {
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new java.util.Date());

	}

	public static String getDateStr(java.util.Date date) {
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 静态常量
	 */
	public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";

	public static final int C_ONE_SECOND = 1000;
	public static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;
	public static final long C_ONE_HOUR = 60 * C_ONE_MINUTE;
	public static final long C_ONE_DAY = 24 * C_ONE_HOUR;

	/**
	 * 计算当前月份的最大天数
	 * 
	 * @return
	 */
	public static int findMaxDayInMonth() {
		return findMaxDayInMonth(0, 0);
	}

	/**
	 * 计算指定日期月份的最大天数
	 * 
	 * @param date
	 * @return
	 */
	public static int findMaxDayInMonth(Date date) {
		if (date == null) {
			return 0;
		}
		return findMaxDayInMonth(date2Calendar(date));
	}

	/**
	 * 计算指定日历月份的最大天数
	 * 
	 * @param calendar
	 * @return
	 */
	public static int findMaxDayInMonth(Calendar calendar) {
		if (calendar == null) {
			return 0;
		}

		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 计算当前年某月份的最大天数
	 * 
	 * @param month
	 * @return
	 */
	public static int findMaxDayInMonth(int month) {
		return findMaxDayInMonth(0, month);
	}

	/**
	 * 计算某年某月份的最大天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int findMaxDayInMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		if (year > 0) {
			calendar.set(Calendar.YEAR, year);
		}

		if (month > 0) {
			calendar.set(Calendar.MONTH, month - 1);
		}

		return findMaxDayInMonth(calendar);
	}

	/**
	 * Calendar 转换为 Date
	 * 
	 * @param calendar
	 * @return
	 */
	public static Date calendar2Date(Calendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.getTime();
	}

	/**
	 * Date 转换为 Calendar
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar date2Calendar(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 拿到默认格式的SimpleDateFormat
	 * 
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat() {
		return getSimpleDateFormat(null);
	}

	/**
	 * 拿到指定输出格式的SimpleDateFormat
	 * 
	 * @param format
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat(String format) {
		SimpleDateFormat sdf;
		if (format == null) {
			sdf = new SimpleDateFormat(C_TIME_PATTON_DEFAULT);
		} else {
			sdf = new SimpleDateFormat(format);
		}

		return sdf;
	}

	/**
	 * 转换当前时间为默认格式
	 * 
	 * @return
	 */
	public static String formatDate2Str() {
		return formatDate2Str(new Date());
	}

	/**
	 * 转换指定时间为默认格式
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate2Str(Date date) {
		return formatDate2Str(date, C_TIME_PATTON_DEFAULT);
	}

	/**
	 * 转换指定时间为指定格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate2Str(Date date, String format) {
		if (date == null) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		SimpleDateFormat sdf = getSimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 转换默认格式的时间为Date
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date formatStr2Date(String dateStr) {
		return formatStr2Date(dateStr, null);
	}

	/**
	 * 转换指定格式的时间为Date
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date formatStr2Date(String dateStr, String format) {
		if (dateStr == null) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		SimpleDateFormat sdf = getSimpleDateFormat(format);
		return sdf.parse(dateStr, new ParsePosition(0));
	}

	/**
	 * 转换默认格式的时间为指定格式时间
	 * 
	 * @param dateStr
	 * @param defineFormat
	 * @return
	 */
	public static String formatDefault2Define(String dateStr,
			String defineFormat) {
		return formatSource2Target(dateStr, C_TIME_PATTON_DEFAULT, defineFormat);
	}

	/**
	 * 转换源格式的时间为目标格式时间
	 * 
	 * @param dateStr
	 * @param sourceFormat
	 * @param targetFormat
	 * @return
	 */
	public static String formatSource2Target(String dateStr,
			String sourceFormat, String targetFormat) {
		Date date = formatStr2Date(dateStr, sourceFormat);
		return formatDate2Str(date, targetFormat);
	}

	/**
	 * 计算当天是该年中的第几周
	 * 
	 * @return
	 */
	public static int findWeeksNoInYear() {
		return findWeeksNoInYear(new Date());
	}

	/**
	 * 计算指定日期是该年中的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int findWeeksNoInYear(Date date) {
		if (date == null) {
			return 0;
		}
		return findWeeksNoInYear(date2Calendar(date));
	}

	/**
	 * 计算指定日历是该年中的第几周
	 * 
	 * @param calendar
	 * @return
	 */
	public static int findWeeksNoInYear(Calendar calendar) {
		if (calendar == null) {
			return 0;
		}
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 计算一年中的第几星期是几号
	 * 
	 * @param year
	 * @param weekInYear
	 * @param dayInWeek
	 * @return
	 */
	public static Date findDateInWeekOnYear(int year, int weekInYear,
			int dayInWeek) {
		Calendar calendar = Calendar.getInstance();
		if (year > 0) {
			calendar.set(Calendar.YEAR, year);
		}

		calendar.set(Calendar.WEEK_OF_YEAR, weekInYear);
		calendar.set(Calendar.DAY_OF_WEEK, dayInWeek);

		return calendar.getTime();
	}

	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param days
	 * @return
	 */
	public static Date dayBefore2Date(int days) {
		Date date = new Date();
		return dayBefore2Object(days, null, date);
	}

	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param days
	 * @return
	 */
	public static String dayBefore2Str(int days) {
		String string = formatDate2Str();
		return dayBefore2Object(days, null, string);
	}

	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param days
	 * @param format
	 * @param instance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T dayBefore2Object(int days,
			String format, T instance) {
		Calendar calendar = Calendar.getInstance();
		if (days == 0) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}

		calendar.add(Calendar.DATE, days);
		if (instance instanceof Date) {
			return (T) calendar.getTime();
		} else if (instance instanceof String) {
			return (T) formatDate2Str(calendar2Date(calendar), format);
		}
		return null;
	}

	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date defineDayBefore2Date(Date date, int days) {
		Date dateInstance = new Date();
		return defineDayBefore2Object(date, days, null, dateInstance);
	}

	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static String defineDayBefore2Str(Date date, int days) {
		String stringInstance = formatDate2Str();
		return defineDayBefore2Object(date, days, null, stringInstance);
	}

	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param <T>
	 * @param date
	 * @param days
	 * @param format
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T defineDayBefore2Object(Date date,
			int days, String format, T instance) {
		if (date == null || days == 0) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}

		Calendar calendar = date2Calendar(date);
		calendar.add(Calendar.DATE, days);
		if (instance instanceof Date) {
			return (T) calendar.getTime();
		} else if (instance instanceof String) {
			return (T) formatDate2Str(calendar2Date(calendar), format);
		}
		return null;
	}

	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param months
	 * @return
	 */
	public static Date monthBefore2Date(int months) {
		Date date = new Date();
		return monthBefore2Object(months, null, date);
	}

	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param months
	 * @return
	 */
	public static String monthBefore2Str(int months) {
		String string = formatDate2Str();
		return monthBefore2Object(months, null, string);
	}

	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param <T>
	 * @param months
	 * @param format
	 * @param instance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T monthBefore2Object(int months,
			String format, T instance) {
		if (months == 0) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, months);

		if (instance instanceof Date) {
			return (T) calendar.getTime();
		} else if (instance instanceof String) {
			return (T) formatDate2Str(calendar2Date(calendar), format);
		}

		return null;
	}

	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date defineMonthBefore2Date(Date date, int months) {
		Date dateInstance = new Date();
		return defineMonthBefore2Object(date, months, null, dateInstance);
	}

	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static String defineMonthBefore2Str(Date date, int months) {
		String stringInstance = formatDate2Str();
		return defineMonthBefore2Object(date, months, null, stringInstance);
	}

	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param <T>
	 * @param date
	 * @param months
	 * @param format
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T defineMonthBefore2Object(Date date,
			int months, String format, T instance) {
		if (months == 0) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}

		Calendar calendar = date2Calendar(date);
		calendar.add(Calendar.MONTH, months);

		if (instance instanceof Date) {
			return (T) calendar.getTime();
		} else if (instance instanceof String) {
			return (T) formatDate2Str(calendar2Date(calendar), format);
		}

		return null;
	}

	/**
	 * 计算两个日期直接差的天数
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int caculate2Days(Date firstDate, Date secondDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate);
		int dayNum1 = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(secondDate);
		int dayNum2 = calendar.get(Calendar.DAY_OF_YEAR);
		return Math.abs(dayNum1 - dayNum2);
	}

	/**
	 * 计算两个日期直接差的天数
	 * 
	 * @param firstCalendar
	 * @param secondCalendar
	 * @return
	 */
	public static int caculate2Days(Calendar firstCalendar,
			Calendar secondCalendar) {
		if (firstCalendar.after(secondCalendar)) {
			Calendar calendar = firstCalendar;
			firstCalendar = secondCalendar;
			secondCalendar = calendar;
		}

		long calendarNum1 = firstCalendar.getTimeInMillis();
		long calendarNum2 = secondCalendar.getTimeInMillis();
		return Math.abs((int) ((calendarNum1 - calendarNum2) / C_ONE_DAY));
	}

	public static void main(String[] args) {
		// System.out.println("当前月份的最大天数:" + findMaxDayInMonth(new Date()));
		// System.out.println("6月份的最大天数:" + findMaxDayInMonth(6));
		// System.out.println("1999-02月份的最大天数:" + findMaxDayInMonth(1999, 2));
		// System.out.println("2000-02月份的最大天数:" + findMaxDayInMonth(2000, 2));

		// System.out.println(formatSource2Target("2009-07-24 11:02:35", null,
		// "yyyy/MM/dd"));
		// System.out.println(findWeeksNoInYear());

		// System.out.println("2003年的第30个星期一是那天:" + findDateInWeekOnYear(2003,
		// 30, Calendar.MONDAY));
		// System.out.println("2009年的第30个星期一是那天:" + findDateInWeekOnYear(2009,
		// 30, Calendar.FRIDAY));

		// System.out.println("【日期格式】当前日期的前7天是:" + dayBefore2Date(-7));
		// System.out.println("【字符格式】当前日期的前7天是:" + dayBefore2Str(-7));
		// System.out.println("【日期格式】当前日期的后7天是:" + dayBefore2Date(7));
		// System.out.println("【字符格式】当前日期的后7天是:" + dayBefore2Str(7));

		// System.out.println(formatStr2Date("2009-07-22", "yyyy-MM-dd"));

		// System.out.println("【日期格式】2009-07-22的前7天是:" +
		// defineDayBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"),
		// -7));
		// System.out.println("【字符格式】2009-07-22的前7天是:" +
		// defineDayBefore2Str(formatStr2Date("2009-07-22", "yyyy-MM-dd"), -7));
		// System.out.println("【日期格式】2009-07-22的后7天是:" +
		// defineDayBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"), 7));
		// System.out.println("【字符格式】2009-07-22的后7天是:" +
		// defineDayBefore2Str(formatStr2Date("2009-07-22", "yyyy-MM-dd"), 7));

		// System.out.println("【日期格式】相对于当前时间的前2月日期是:" + monthBefore2Date(-2));
		// System.out.println("【字符格式】相对于当前时间的前2月日期是:" + monthBefore2Date(-2));
		// System.out.println("【日期格式】相对于当前时间的后2月日期是:" + monthBefore2Date(2));
		// System.out.println("【字符格式】相对于当前时间的后2月日期是:" + monthBefore2Date(2));

		// System.out.println("【日期格式】2009-07-22的前2月日期是:" +
		// defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"),
		// -2));
		// System.out.println("【字符格式】2009-07-22的前2月日期是:" +
		// defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"),
		// -2));
		// System.out.println("【日期格式】2009-07-22的后2月日期是:" +
		// defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"),
		// 2));
		// System.out.println("【字符格式】2009-07-22的后2月日期是:" +
		// defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"),
		// 2));

		// Date firstDate = formatStr2Date("2009-07-22", "yyyy-MM-dd");
		// Date secondDate = formatStr2Date("2009-07-18", "yyyy-MM-dd");
		// System.out.println(caculate2Days(firstDate, secondDate));

		//Calendar firstC = date2Calendar(formatStr2Date("2009-07-22",
		//		"yyyy-MM-dd"));
		//Calendar secondC = date2Calendar(formatStr2Date("2009-07-18",
		//		"yyyy-MM-dd"));
		//System.out.println(caculate2Days(firstC, secondC));

		//System.out.println(getTodayInfo());

		System.out.println(getMonthBegin());
		System.out.println(getCurMonthEnd());
	}
	
	/** 取得当前年liujing:2010-7-9 */
	public static String getCuryear() {		
		String currentyear="";
		int year=Calendar.getInstance().get(Calendar.YEAR);	 //当前年
		currentyear=String.valueOf(year);
		return currentyear;
	}
	
	/** 取得当前月liujing:2010-7-9 */
	public static String getCurmonth() {		
		String curmonth="";
		int month= 1+Calendar.getInstance().get(Calendar.MONTH);//当前月 
		if(month<10){
			curmonth="0"+String.valueOf(month);
		}else{
		curmonth=String.valueOf(month);
		}
		return curmonth;
	}
	
	/** 将时间字符YYYY-M-D串格式化成YYYY-MM-DD  huqiang:2010-12-06*/
	public static String formatTime1(String dateStr){
		String[] str=dateStr.split("-");
		String returnDate="";
		for(int i=0;i<str.length;i++){
			if(i!=0){
				if(str[i].length()==1){
					str[i]="0"+str[i];
				}
			}
			returnDate+=str[i]+"-";
		}
		return returnDate.substring(0, returnDate.length()-1);
	}
	
	/**
	 * 将指定的日期字符串转换为新的日期格式字符串
	 * @param dateString 当前日期字符串
	 * @param currentPattern 当前日期格式
	 * @param newPattern 新的日期格式
	 * @return 转换后新的日期字符串
	 */
	public static String format(String dateString, String currentPattern, String newPattern) {
		if (dateString.equals("自合同签订之日起") || dateString.equals("履行完毕且无争议")) {
			return dateString;
		}
		DateFormat df = getSimpleDateFormat(currentPattern);
		Date date = null;
		try {
			date = df.parse(dateString);
		} catch (ParseException e) {
			log.error("错误的日期格式", e);
		}
		df = getSimpleDateFormat(newPattern);
		
		return df.format(date);
	}
	
}
