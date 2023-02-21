package com.wyci.utils.date;


import org.apache.commons.lang3.StringUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class DateUtil {
    private static final ThreadLocal<SimpleDateFormat> THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>();

    private static final Object OBJECT = new Object();

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int dayAmount) {
        return DateUtil.addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期字符串
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期字符串
     */
    public static String addDay(String date, int dayAmount) {
        return DateUtil.addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date       日期
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期
     */
    public static Date addHour(Date date, int hourAmount) {
        return DateUtil.addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date       日期字符串
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期字符串
     */
    public static String addHour(String date, int hourAmount) {
        return DateUtil.addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期
     */
    private static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期字符串
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期字符串
     */
    private static String addInteger(String date, int dateType, int amount) {
        String dateString = null;
        DateStyle dateStyle = DateUtil.getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = DateUtil.stringToDate(date, dateStyle);
            myDate = DateUtil.addInteger(myDate, dateType, amount);
            dateString = DateUtil.dateToString(myDate, dateStyle);
        }
        return dateString;
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date         日期
     * @param minuteAmount 增加数量。可为负数
     * @return 增加分钟后的日期
     */
    public static Date addMinute(Date date, int minuteAmount) {
        return DateUtil.addInteger(date, Calendar.MINUTE, minuteAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date         日期字符串
     * @param minuteAmount 增加数量。可为负数
     * @return 增加分钟后的日期字符串
     */
    public static String addMinute(String date, int minuteAmount) {
        return DateUtil.addInteger(date, Calendar.MINUTE, minuteAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date        日期
     * @param monthAmount 增加数量。可为负数
     * @return 增加月份后的日期
     */
    public static Date addMonth(Date date, int monthAmount) {
        return DateUtil.addInteger(date, Calendar.MONTH, monthAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date        日期
     * @param monthAmount 增加数量。可为负数
     * @return 增加月份后的日期字符串
     */
    public static String addMonth(String date, int monthAmount) {
        return DateUtil.addInteger(date, Calendar.MONTH, monthAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date         日期
     * @param secondAmount 增加数量。可为负数
     * @return 增加秒钟后的日期
     */
    public static Date addSecond(Date date, int secondAmount) {
        return DateUtil.addInteger(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date         日期字符串
     * @param secondAmount 增加数量。可为负数
     * @return 增加秒钟后的日期字符串
     */
    public static String addSecond(String date, int secondAmount) {
        return DateUtil.addInteger(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期
     */
    public static Date addYear(Date date, int yearAmount) {
        return DateUtil.addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期字符串
     */
    public static String addYear(String date, int yearAmount) {
        return DateUtil.addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 依据格式 增加时间日期
     *
     * @param date    传入的日期
     * @param addDate 格式1Y = add1Year<br>2M = add2Month<br>...<br>
     *                D = Days h = Hours m = minutes s = seconds
     * @return 修改之后的日期
     */
    public static Date add(Date date, String addDate) {
        try {
            if (addDate.matches("^(\\d+[YMDhms])+$")) {
                String[] expiras = StringUtils.splitByCharacterType(addDate);
                if (expiras.length % 2 == 0) {
                    for (int i = 0; i < expiras.length; i = i + 2) {
                        if ("Y".equals(expiras[i + 1])) {
                            date = DateUtil.addYear(date, Integer.parseInt(expiras[i]));
                        }
                        if ("M".equals(expiras[i + 1])) {
                            date = DateUtil.addMonth(date, Integer.parseInt(expiras[i]));
                        }
                        if ("D".equals(expiras[i + 1])) {
                            date = DateUtil.addDay(date, Integer.parseInt(expiras[i]));
                        }
                        if ("h".equals(expiras[i + 1])) {
                            date = DateUtil.addHour(date, Integer.parseInt(expiras[i]));
                        }
                        if ("m".equals(expiras[i + 1])) {
                            date = DateUtil.addMinute(date, Integer.parseInt(expiras[i]));
                        }
                        if ("s".equals(expiras[i + 1])) {
                            date = DateUtil.addSecond(date, Integer.parseInt(expiras[i]));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取两个日期相差的天数，后一个日期的天数减去前一个日期的天数。也就是说，如果后者时间大于前者，则返回正值，否则返回负值。
     *
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 相差天数。如果失败则返回-1
     */
    public static int calculateIntervalDays(Date date, Date otherDate) {
        int num = -1;
        Date dateTmp = DateUtil.stringToDate(DateUtil.getDate(date), DateStyle.YYYY_MM_DD);
        Date otherDateTmp = DateUtil.stringToDate(DateUtil.getDate(otherDate), DateStyle.YYYY_MM_DD);
        if (dateTmp != null && otherDateTmp != null) {
            long time = otherDateTmp.getTime() - dateTmp.getTime();
            num = (int) (time / (24 * 60 * 60 * 1000));
        }
        return num;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date      日期
     * @param dateStyle 日期风格
     * @return 日期字符串
     */
    public static String dateToString(Date date, DateStyle dateStyle) {
        String dateString = null;
        if (dateStyle != null) {
            dateString = DateUtil.dateToString(date, dateStyle.getValue());
        }
        return dateString;
    }


    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return 日期字符串
     */
    public static String dateToString(Date date, String pattern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = DateUtil.getDateFormat(pattern).format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }

    /**
     * 获取精确的日期
     *
     * @param timestamps 时间long集合
     * @return 日期
     */
    private static Date getAccurateDate(List<Long> timestamps) {
        Date date = null;
        long timestamp = 0;
        Map<Long, long[]> map = new HashMap<Long, long[]>();
        List<Long> absoluteValues = new ArrayList<Long>();
        if (timestamps != null && timestamps.size() > 0) {
            if (timestamps.size() > 1) {
                for (int i = 0; i < timestamps.size(); i++) {
                    for (int j = i + 1; j < timestamps.size(); j++) {
                        long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
                        absoluteValues.add(absoluteValue);
                        long[] timestampTmp = {timestamps.get(i), timestamps.get(j)};
                        map.put(absoluteValue, timestampTmp);
                    }
                }
                // 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的。此时minAbsoluteValue为0
                // 因此不能将minAbsoluteValue取默认值0
                long minAbsoluteValue = -1;
                if (!absoluteValues.isEmpty()) {
                    minAbsoluteValue = absoluteValues.get(0);
                    for (int i = 1; i < absoluteValues.size(); i++) {
                        if (minAbsoluteValue > absoluteValues.get(i)) {
                            minAbsoluteValue = absoluteValues.get(i);
                        }
                    }
                }
                if (minAbsoluteValue != -1) {
                    long[] timestampsLastTmp = map.get(minAbsoluteValue);
                    long dateOne = timestampsLastTmp[0];
                    long dateTwo = timestampsLastTmp[1];
                    if (absoluteValues.size() > 1) {
                        timestamp = Math.abs(dateOne) > Math.abs(dateTwo) ? dateOne : dateTwo;
                    }
                }
            } else {
                timestamp = timestamps.get(0);
            }
        }
        if (timestamp != 0) {
            date = new Date(timestamp);
        }
        return date;
    }

    /**
     * 获取当前年
     *
     * @return 当前年
     */
    public static int getCurrentYear() {
        return DateUtil.getYear(new Date());
    }

    /**
     * 获取当前小时
     *
     * @return 当前小时
     */
    public static int getCurrentHour() {
        return DateUtil.getHour(new Date());
    }

    /**
     * 获取日期。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期
     * @return 日期
     */
    public static String getDate(Date date) {
        return DateUtil.dateToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取日期 。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static String getDate(String date) {
        return DateUtil.stringToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取SimpleDateFormat
     *
     * @param pattern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
        SimpleDateFormat dateFormat = DateUtil.THREAD_LOCAL.get();
        if (dateFormat == null) {
            synchronized (DateUtil.OBJECT) {
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(pattern, Locale.US);
                    dateFormat.setLenient(false);
                    DateUtil.THREAD_LOCAL.set(dateFormat);
                }
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }

    /**
     * 获取日期字符串的日期风格。失敗返回null。
     *
     * @param date 日期字符串
     * @return 日期风格
     */
    public static DateStyle getDateStyle(String date) {
        DateStyle dateStyle = null;
        Map<Long, DateStyle> map = new HashMap<Long, DateStyle>();
        List<Long> timestamps = new ArrayList<Long>();
        for (DateStyle style : DateStyle.values()) {
            if (style.isShowOnly()) {
                continue;
            }
            Date dateTmp = null;
            if (date != null) {
                try {
                    ParsePosition pos = new ParsePosition(0);
                    dateTmp = DateUtil.getDateFormat(style.getValue()).parse(date, pos);
                    if (pos.getIndex() != date.length()) {
                        dateTmp = null;
                    }
                } catch (Exception e) {
                }
            }
            if (dateTmp != null) {
                timestamps.add(dateTmp.getTime());
                map.put(dateTmp.getTime(), style);
            }
        }
        Date accurateDate = DateUtil.getAccurateDate(timestamps);
        if (accurateDate != null) {
            dateStyle = map.get(accurateDate.getTime());
        }
        return dateStyle;
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期
     * @return 天
     */
    public static int getDay(Date date) {
        return DateUtil.getInteger(date, Calendar.DATE);
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期字符串
     * @return 天
     */
    public static int getDay(String date) {
        return DateUtil.getDay(DateUtil.stringToDate(date));
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期
     * @return 小时
     */
    public static int getHour(Date date) {
        return DateUtil.getInteger(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期字符串
     * @return 小时
     */
    public static int getHour(String date) {
        return DateUtil.getHour(DateUtil.stringToDate(date));
    }

    /**
     * 获取日期中的某数值。如获取月份
     *
     * @param date     日期
     * @param dateType 日期格式
     * @return 数值
     */
    private static int getInteger(Date date, int dateType) {
        int num = 0;
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            num = calendar.get(dateType);
        }
        return num;
    }

    /**
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 相差天数。如果失败则返回-1
     */
    public static int getIntervalDays(Date date, Date otherDate) {
        int num = -1;
        Date dateTmp = DateUtil.stringToDate(DateUtil.getDate(date), DateStyle.YYYY_MM_DD);
        Date otherDateTmp = DateUtil.stringToDate(DateUtil.getDate(otherDate), DateStyle.YYYY_MM_DD);
        if (dateTmp != null && otherDateTmp != null) {
            long time = Math.abs(dateTmp.getTime() - otherDateTmp.getTime());
            num = (int) (time / (24 * 60 * 60 * 1000));
        }
        return num;
    }

    /**
     * 获取两个日期相差的天数
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 相差天数。如果失败则返回-1
     */
    public static int getIntervalDays(String date, String otherDate) {
        return DateUtil.getIntervalDays(DateUtil.stringToDate(date), DateUtil.stringToDate(otherDate));
    }

    public static int getIntervalDaysToToday(Date date) {
        return DateUtil.getIntervalDays(date, new Date());
    }


    /**
     * 获取date中当月的第一天
     *
     * @param date 传入日期
     * @return 当月第一天
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取date中当月的最后一天
     *
     * @param date 传入日期
     * @return 当月最后一天
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.MONTH, 1);
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
//        return DateUtil.addDay(DateUtil.addMonth(DateUtil.stringToDate(DateUtil.dateToString(date, DateStyle.YYYY_MM_CN)), 1), -1);
    }

    /**
     * 获取季度 第一天或最后一天
     *
     * @param quarters 0本季度，1下季度，-1上季度 以此类推
     * @param isFirst  true获取开始时间 false获取结束时间
     * @return java.lang.String
     */
    public static String getStartOrEndDayOfQuarter(long quarters, Boolean isFirst) {
        LocalDate resDate = LocalDate.now().plusMonths(quarters * 3);
        Month month = resDate.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        if (isFirst) {
            resDate = LocalDate.of(resDate.getYear(), firstMonthOfQuarter, 1);
        } else {
            resDate = LocalDate.of(resDate.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(resDate.isLeapYear()));
        }
        return resDate.toString();
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期
     * @return 分钟
     */
    public static int getMinute(Date date) {
        return DateUtil.getInteger(date, Calendar.MINUTE);
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 分钟
     */
    public static int getMinute(String date) {
        return DateUtil.getMinute(DateUtil.stringToDate(date));
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        return DateUtil.getInteger(date, Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期字符串
     * @return 月份
     */
    public static int getMonth(String date) {
        return DateUtil.getMonth(DateUtil.stringToDate(date));
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期
     * @return 秒钟
     */
    public static int getSecond(Date date) {
        return DateUtil.getInteger(date, Calendar.SECOND);
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 秒钟
     */
    public static int getSecond(String date) {
        return DateUtil.getSecond(DateUtil.stringToDate(date));
    }

    /**
     * 获取简单农历对象
     *
     * @param date 日期
     * @return 简单农历对象
     */
    public static com.wyci.utils.date.SimpleLunarCalendar getSimpleLunarCalendar(Date date) {
        return new com.wyci.utils.date.SimpleLunarCalendar(date);
    }

    /**
     * 获取简单农历对象
     *
     * @param date 日期字符串
     * @return 简单农历对象
     */
    public static com.wyci.utils.date.SimpleLunarCalendar getSimpleLunarCalendar(String date) {
        return new com.wyci.utils.date.SimpleLunarCalendar(DateUtil.stringToDate(date));
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期
     * @return 时间
     */
    public static String getTime(Date date) {
        return DateUtil.dateToString(date, DateStyle.HH_MM_SS);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 时间
     */
    public static String getTime(String date) {
        return DateUtil.stringToString(date, DateStyle.HH_MM_SS);
    }

    /**
     * 获取本周第一天
     *
     * @return Date
     */
    public static Date getFirstDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date date = cal.getTime();
        return date;
    }

    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期
     * @return 星期
     */
    public static com.wyci.utils.date.Week getWeek(Date date) {
        com.wyci.utils.date.Week week = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (weekNumber) {
            case 0:
                week = com.wyci.utils.date.Week.SUNDAY;
                break;
            case 1:
                week = com.wyci.utils.date.Week.MONDAY;
                break;
            case 2:
                week = com.wyci.utils.date.Week.TUESDAY;
                break;
            case 3:
                week = com.wyci.utils.date.Week.WEDNESDAY;
                break;
            case 4:
                week = com.wyci.utils.date.Week.THURSDAY;
                break;
            case 5:
                week = com.wyci.utils.date.Week.FRIDAY;
                break;
            case 6:
                week = com.wyci.utils.date.Week.SATURDAY;
                break;
            default:
                week = null;
        }
        return week;
    }

    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期字符串
     * @return 星期
     */
    public static com.wyci.utils.date.Week getWeek(String date) {
        com.wyci.utils.date.Week week = null;
        DateStyle dateStyle = DateUtil.getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = DateUtil.stringToDate(date, dateStyle);
            week = DateUtil.getWeek(myDate);
        }
        return week;
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        return DateUtil.getInteger(date, Calendar.YEAR);
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期字符串
     * @return 年份
     */
    public static int getYear(String date) {
        return DateUtil.getYear(DateUtil.stringToDate(date));
    }

    /**
     * 判断字符串是否为日期字符串
     *
     * @param date 日期字符串
     * @return true orConditions false
     */
    public static boolean isDate(String date) {
        boolean isDate = false;
        if (date != null) {
            if (DateUtil.getDateStyle(date) != null) {
                isDate = true;
            }
        }
        return isDate;
    }

    /**
     * 判断日期是否在当前的月份
     *
     * @param date 当前日期
     * @return true在 false 不在
     */
    public static boolean isInCurrentMonth(Date date) {
        return DateUtil.getMonth(new Date()) - DateUtil.getMonth(date) == 0;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static Date stringToDate(String date) {
        DateStyle dateStyle = DateUtil.getDateStyle(date);
        return DateUtil.stringToDate(date, dateStyle);
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date      日期字符串
     * @param dateStyle 日期风格
     * @return 日期
     */
    public static Date stringToDate(String date, DateStyle dateStyle) {
        Date myDate = null;
        if (dateStyle != null) {
            myDate = DateUtil.stringToDate(date, dateStyle.getValue());
        }
        return myDate;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date    日期字符串
     * @param pattern 日期格式
     * @return 日期
     */
    public static Date stringToDate(String date, String pattern) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = DateUtil.getDateFormat(pattern).parse(date);
            } catch (Exception e) {
            }
        }
        return myDate;
    }

    /**
     * 描述:获取下一个月的第一天.
     *
     * @return 下个月的第一天
     */
    public static Date getPerFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String stringToString(String date, DateStyle newDateStyle) {
        DateStyle oldDateStyle = DateUtil.getDateStyle(date);
        return DateUtil.stringToString(date, oldDateStyle, newDateStyle);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddDteStyle 旧日期风格
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String stringToString(String date, DateStyle olddDteStyle, DateStyle newDateStyle) {
        String dateString = null;
        if (olddDteStyle != null && newDateStyle != null) {
            dateString = DateUtil.stringToString(date, olddDteStyle.getValue(), newDateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddDteStyle 旧日期风格
     * @param newParttern  新日期格式
     * @return 新日期字符串
     */
    public static String stringToString(String date, DateStyle olddDteStyle, String newParttern) {
        String dateString = null;
        if (olddDteStyle != null) {
            dateString = DateUtil.stringToString(date, olddDteStyle.getValue(), newParttern);
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date       旧日期字符串
     * @param newPattern 新日期格式
     * @return 新日期字符串
     */
    public static String stringToString(String date, String newPattern) {
        DateStyle oldDateStyle = DateUtil.getDateStyle(date);
        return DateUtil.stringToString(date, oldDateStyle, newPattern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddPattern  旧日期格式
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String stringToString(String date, String olddPattern, DateStyle newDateStyle) {
        String dateString = null;
        if (newDateStyle != null) {
            dateString = DateUtil.stringToString(date, olddPattern, newDateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date        旧日期字符串
     * @param olddPattern 旧日期格式
     * @param newPattern  新日期格式
     * @return 新日期字符串
     */
    public static String stringToString(String date, String olddPattern, String newPattern) {
        return DateUtil.dateToString(DateUtil.stringToDate(date, olddPattern), newPattern);
    }


    /**
     * 将指定日期的时分秒格式为零
     *
     * @param date 传入日期
     * @return 传入日期的零时
     */
    public static Date formatHhMmSsOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static int curDate() {

        return toDate(new Date());
    }

    public static int curTime() {
        return toTime(new Date());
    }

    public static long currentTimeSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis() % 1000;
    }

    public static int toDate(Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int toTime(Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY) * 10000 + calendar.get(Calendar.MINUTE) * 100 + calendar.get(Calendar.SECOND);
    }

    public static int toDate(String dateStr) {

        return toDate(stringToDate(dateStr));
    }

    public static int toTime(String dateStr) {
        return toTime(stringToDate(dateStr));

    }

    /**
     * 获取当前年月日  yyyyMMdd
     *
     * @return yyyyMMdd格式的日期
     */
    public static String getNowYearMonthDay() {
        return dateToString(new Date(), DateStyle.YYYYMMDD);
    }

    /**
     * 获取当前时分秒 HHmmss
     *
     * @return 当前秒
     */
    public static String getNowHourMinuteSecond() {
        return dateToString(new Date(), DateStyle.HHMMSS);
    }

    /**
     * 时间戳转时间
     *
     * @param time
     * @return
     */
    public static String conversionTime(Long time) {
        return DateUtil.conversionTimeByStyle(time, DateStyle.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 时间戳转时间
     *
     * @param time
     * @return
     */
    public static String conversionTimeByStyle(Long time, DateStyle dateStyle) {
        String value = null;
        if (dateStyle != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateStyle.getValue());
            Date date = new Date(time);
            value = simpleDateFormat.format(date);
        }
        return value;
    }

    /**
     * 获得本小时的开始时间
     *
     * @return
     */
    public static Date currentHourStartTime() {
        SimpleDateFormat longHourFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        Date now = new Date();
        try {
            now = longHourFormat.parse(longHourFormat.format(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取当前日期的前一天日期
     */
    public static String getPreviousDayDate() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(ca.getTime());
    }

    /**
     * 获取当前时间一小时之后的时间（精确到时间戳）
     */
    public static long getNextHours() {
        return System.currentTimeMillis() + 60 * 60 * 1000;
    }

    /**
     * 获取当天的开始时间
     *
     * @return
     */
    public static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取当天的结束时间
     *
     * @return
     */
    public static Date getnowEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }


    /**
     * 获取当前月的第一天
     *
     * @return 当月第一天
     */
    public static Date getFirstDayOfCurrentMonth() {
        return getFirstDayOfMonth(new Date());
    }

    /**
     * 获取当前月的最后一天
     *
     * @return 当月最后一天
     */
    public static Date getLastDayOfCurrentMonth() {
        return getLastDayOfMonth(new Date());
    }


    public static void main(String[] args) {


        final Date firstDayOfCurrentMonth = getFirstDayOfCurrentMonth();

        System.out.println(firstDayOfCurrentMonth);


        System.out.println(getLastDayOfCurrentMonth());


    }


}
