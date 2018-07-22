package com.hante.common.utils.date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

    /**60分钟*/
    private static final int SIXTY_MINUTE = 60;

    /**99小时*/
    private static final int NINETY_NINE_HOUR = 99;

    public static final String YYYY_MM_DD_HH_MM_SS  = "yyyy-MM-dd HH:mm:ss";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static List<String> getDateAfterTwoMonth() {
        // 当前日期
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(new Date());

        Calendar endDate = Calendar.getInstance();
        endDate.setTime(new Date());
        // 向后推迟60天
        endDate.add(Calendar.DAY_OF_MONTH, 60);

        return getDatesBetweenTwoDate(startDate.getTime(), endDate.getTime());
    }

    public static List<String> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<String> dateList = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // dateList.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                dateList.add(sdf.format(cal.getTime()));
            } else {
                break;
            }
        }
        // 把结束时间加入集合
        dateList.add(sdf.format(endDate));
        return dateList;
    }

    /**
     * 获取两个日期之间的日期
     * @param start 开始日期
     * @param end 结束日期
     * @return 日期集合
     */
    public static List<String> getBetweenDates(Date start, Date end) {
        List<String> result = new ArrayList<>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(convertDateToString("yyyy-MM-dd", tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        if(date==null){
            return "";
        }
        return df.format(date);
    }

    public static String nowDateToString(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return df.format(new Date());
    }

    /**
     * 日期转换成字符串
     */
    public static Date stringToDate(String date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);

        try {
            return df.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date addDate(int day) {
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(5, date.get(5) + day);
        return date.getTime();
    }

    public static Date addDate(Date beginDate, int day) {
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(5, date.get(5) + day);
        return date.getTime();
    }

    public static Date add(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, amount);
        return calendar.getTime();
    }

    public static Date add(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static int daysBetween(Date before, Date after) {
        if (before == null || after == null) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        try {
            cal.setTime(sdf.parse(sdf.format(before)));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(sdf.format(after)));
            time2 = cal.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweenDays)) + 1;
    }


    public static int daysBetweenTwo(Date before, Date after) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            after = sdf.parse(sdf.format(after));
            before = sdf.parse(sdf.format(before));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(after);
        long afterTime = cal.getTimeInMillis();
        cal.setTime(before);
        long beforeTime = cal.getTimeInMillis();
        long betweenDays = (afterTime - beforeTime) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(betweenDays));
    }


    /**
     * 获取当前Calendar
     *
     * @return 当前Calendar
     */
    public static Calendar getCurrentCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    public static String formatDate(String formatString, Date date) {
        SimpleDateFormat df = null;
        String returnValue = null;

        if (date != null) {
            df = new SimpleDateFormat(formatString);
            returnValue = df.format(date);
        }

        return returnValue;
    }

    public static String convertDateToString(String pattern, Date aDate) {
        return getDateTime(pattern, aDate);
    }

    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (aDate == null) {
            return returnValue;
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }
        return returnValue;
    }


    public static Date convertStringToDate(String formatString, String strDate) {
        if (StringUtils.isBlank(strDate)) {
            throw new IllegalArgumentException("arg strDate[" + strDate + "] format is wrong");
        }
        if (StringUtils.isBlank(formatString)) {
            throw new IllegalArgumentException("arg formatString[" + formatString + "] format is wrong");
        }

        SimpleDateFormat df = new SimpleDateFormat(formatString);
        Date date = null;

        try {
            date = df.parse(strDate);
        } catch (ParseException ex) {
            throw new IllegalArgumentException("arg formatString[" + formatString + "] format is wrong", ex);
        }

        return (date);
    }

    public static String format(Date date, String pattern) {
        DateFormat df = createDateFormat(pattern);
        return df.format(date);
    }

    private static DateFormat createDateFormat(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        TimeZone gmt = TimeZone.getTimeZone("GMT");
        sdf.setTimeZone(gmt);
        sdf.setLenient(true);
        return sdf;
    }

    public static Date subDate(int day) {
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.add(Calendar.DAY_OF_MONTH, 0 - day);
        return date.getTime();
    }
    public static Date subDate(Date beginDate,int day) {
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.add(Calendar.DAY_OF_MONTH, 0 - day);
        return date.getTime();
    }
    /**
     * 日期转日期零点
     */
    public static Date convertDateToMin(Date date) {
        if (date == null) {
            return date;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String dateS = sdf.format(date);
        try {
            return sdf.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0) {
            return "00:00";
        } else {
            minute = time / SIXTY_MINUTE;
            if (minute < SIXTY_MINUTE) {
                second = time % SIXTY_MINUTE;
                timeStr = unitFormat(minute) + "分" + unitFormat(second) + "秒";
            } else {
                hour = minute / SIXTY_MINUTE;
                if (hour > NINETY_NINE_HOUR) {
                    return "99:59:59";
                }
                minute = minute % SIXTY_MINUTE;
                second = time - hour * 3600 - minute * SIXTY_MINUTE;
                timeStr = unitFormat(hour) + "时" + unitFormat(minute) + "分" + unitFormat(second) + "秒";
            }
        }
        return timeStr;
    }

    private static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = Integer.toString(i);
        } else {
            retStr = "" + i;
        }
        return retStr;
    }

    /**
     * 推后月份计算
     *
     * @param date
     * @return
     */
    public static Date getOtherMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }


    /**
     * 日期转日期23:59:59
     */
    public static Date convertDateToMax(Date date) {
        if (date == null) {
            return date;
        }
        String dateStr = convertDateToString("yyyy-MM-dd", date) + " 23:59:59";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateS = sdf.format(convertStringToDate("yyyy-MM-dd HH:mm:ss", dateStr));
        try {
            return sdf.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int hourBetween(Date before, Date after) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        try {
            cal.setTime(sdf.parse(sdf.format(before)));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(sdf.format(after)));
            time2 = cal.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long betweenHour = (time2 - time1) / (1000 * 3600);

        return Integer.parseInt(String.valueOf(betweenHour)) + 1;
    }

    public static int daysCompare(Date date1, Date date2) throws Exception {
        if (date1 == null || date2 == null) {
            throw new Exception("传入参数不能为空");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        cal.setTime(sdf.parse(sdf.format(date1)));
        long time1 = cal.getTimeInMillis();

        cal.setTime(sdf.parse(sdf.format(date2)));
        long time2 = cal.getTimeInMillis();

        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweenDays));
    }
    public static void main(String[] args) throws Exception{
        Date date1 = DateUtils.convertStringToDate("yyyy-MM-dd","2018-05-10");
        Date date2 = DateUtils.convertStringToDate("yyyy-MM-dd","2018-05-11");

        System.out.println(DateUtils.daysBetween(date1,date2));
        System.out.println(DateUtils.daysBetweenTwo(date1,date2));
        System.out.println(DateUtils.daysCompare(date1,date2));

    }
    public static int hourBetweenTwo(Date before, Date after) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        try {
            cal.setTime(sdf.parse(sdf.format(before)));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(sdf.format(after)));
            time2 = cal.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long betweenHour = (time2 - time1) / (1000 * 3600);

        return Integer.parseInt(String.valueOf(betweenHour));
    }
    public static int minuteBetweenTwo(Date before, Date after) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        try {
            cal.setTime(sdf.parse(sdf.format(before)));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(sdf.format(after)));
            time2 = cal.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long betweenHour = (time2 - time1) / (1000 * 60);

        return Integer.parseInt(String.valueOf(betweenHour));
    }

    public static int monthBetween(Date before, Date after) {
       /* if (before.after(after)) {
            Date t = before;
            before = after;
            after = t;
        }*/
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(before);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(after);
        Calendar temp = Calendar.getInstance();
        temp.setTime(after);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return /*(year * 12 + month - 1) < 0 ? 0 : */(year * 12 + month);
        }
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.get(Calendar.YEAR);
        return calendar.get(Calendar.YEAR);
    }

    public static long millionSecondBetween(Date before, Date after) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        try {
            cal.setTime(sdf.parse(sdf.format(before)));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(sdf.format(after)));
            time2 = cal.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time2 - time1;
    }
    /**
     * 判断给定日期是否为月末的一天
     *
     * @param date
     * @return true:是|false:不是
     */
    public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }

    /**
     * 获取日期月初
     * */
    public static Date getMonthStartDate(Date date){
        if(date==null){
            return date;
        }
        String month = convertDateToString("yyyy-MM",date);
        month+= "-01 00:00:00";
        return convertStringToDate("yyyy-MM-dd HH:mm:ss",month);
    }

    /**
     * 获取日期下个月
     * */
    public static Date getNextMonthDate(Date date){
        if(date==null){
            return date;
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH, 1);
        return rightNow.getTime();
    }
    public static int compareDate (Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static  boolean isSameDay(Date o, Date t){
        return convertDateToString("yyyyMMdd", o).equals(convertDateToString("yyyyMMdd", t));
    }

    /**
     * 验证日期字符串是否为指定格式
     * @param str
     * @param formatText
     * @return
     */
    public static boolean isValidDate(String str, String formatText) {
        boolean convertSuccess=true;
        SimpleDateFormat format = new SimpleDateFormat(formatText);
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess=false;
        }
        return convertSuccess;
    }

    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
}
