package zsl.college.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangshenglan on 16/5/3.
 */
public class DateUtil {
    private DateUtil() {
    }

    public static final String formatPattern = "yyyy-MM-dd HH:mm:ss";
    public static final String formatPattern_yyyyMMdd = "yyyy-MM-dd";
    public static final String formatPattern_short = "yyyyMMdd";

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        return getCurrentDate(formatPattern);
    }

    public static String getCurrentDate(String formatPattern) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        return format.format(new Date());
    }

    /**
     * 根据完整的日期字符串截取只有年月日的日期字符串
     *
     * @param str
     * @return
     */
    public static String getCutYMDDate(String str) {
        Date date = stringToDate(str);
        SimpleDateFormat format = new SimpleDateFormat(formatPattern_yyyyMMdd);
        return format.format(date);
    }

    /**
     * 计算两个日期之间相隔多少天
     *
     * @param lowDate
     * @param bigDate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String lowDate, String bigDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(lowDate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bigDate));
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(betweenDays));
    }

    /**
     * 获取制定毫秒数之前的日期
     *
     * @param timeDiff
     * @return
     */
    public static String getDesignatedDate(long timeDiff) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        long nowTime = System.currentTimeMillis();
        long designTime = nowTime - timeDiff;
        return format.format(designTime);
    }

    /**
     * 获取制定毫秒数之前的日期
     *
     * @param timeDiff
     * @return
     * @author
     */
    public static String getLogisticsDate(long timeDiff) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        long nowTime = System.currentTimeMillis();
        long designTime = nowTime + timeDiff;
        return format.format(designTime);
    }

    /**
     * 获取前几天的日期
     */
    public static String getPrefixDate(String count) {
        Calendar cal = Calendar.getInstance();
        int day = 0 - Integer.parseInt(count);
        cal.add(Calendar.DATE, day);   // int amount   代表天数
        Date datNew = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        return format.format(datNew);
    }

    /**
     * 获取后几天的日期
     */
    public static String getSubfixDate(int count) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, count);
        Date dateNew = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        return format.format(dateNew);
    }

    /**
     * 获取后几天的日期, 并将日期设置为23时59分钟59秒
     */
    public static String getSubfixDateByFullPassDay(int count) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, count);
        Date dateNew = cal.getTime();
        dateNew = setFullPassDay(dateNew);
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        return format.format(dateNew);
    }

    /**
     * 根据end时间字符串与当前时间相比,是否已过期
     *
     * @param end
     * @return
     */
    public static boolean isExpired(String end) {
        Date currTime = new Date();
        Date endTime = stringToDate(end);
        if (currTime.getTime() > endTime.getTime()) {
            return true;
        }
        return false;
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        return format.format(date);
    }

    /**
     * 字符串转换日期
     *
     * @param str
     * @return
     */
    public static Date stringToDate(String str) {
        //str =  " 2016-03-10 19:20:00 " 格式
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        if (str != null && !str.equals("")) {
            try {
                return format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 计算两个时间如：“21:57”和“08:20”相差的分钟数、小时数 java计算两个时间差小时 分钟 秒 .
    public String timeSubtract(String beginStr, String endStr) {
        // beginStr = "2004-01-02 11:30:24"   endStr = "2004-03-26 13:31:40"
        String echo = "";
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = null;
        Date end = null;
        try {
            begin = dfs.parse(beginStr);
            end = dfs.parse(endStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
        long day1 = between / (24 * 3600);
        long hour1 = between % (24 * 3600) / 3600;
        long minute1 = between % 3600 / 60;
        long second1 = between % 60;
        echo = "" + day1 + "天" + hour1 + "小时" + minute1 + "分" + second1 + "秒";
        return echo;
    }

    /**
     * 将时间置为23时59分钟59秒
     *
     * @param date
     * @return
     */
    public static Date setFullPassDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 将时间后退2小时
     *
     * @param date
     * @return
     */
    public static Date getFallBack2Hour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY,
                calendar.get(Calendar.HOUR_OF_DAY) - 2);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取两个时间间隔的天数
     *
     * @param
     * @return
     */
    public static long getDiffDays(Date startDate, Date endDate) {
        long difftime = endDate.getTime() - startDate.getTime();
        return difftime / (24L * 60L * 60L * 1000L);
    }

    /**
     * 根据日期获取当天起始时间
     *
     * @param date
     * @return
     */
    public static Date getStartDateOfCurrentDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取昨天起始时间
     *
     * @param date
     * @return
     */
    public static Date getStartYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 根据日期获取下一天起始时间
     *
     * @param date
     * @return
     */
    public static Date getStartDateOfNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 根据日期当前日期顺延一周后的起始时间
     *
     * @param date
     * @return
     */
    public static Date getStartDateOfNextSevenDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 根据日期当前日期顺延一月后的起始时间
     *
     * @param date
     * @return
     */
    public static Date getStartDateOfNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /*
     * 封装一天内的时间区域
     */
    public static List<Date> getStaticByDateDateArea(Date date) {
        List<Date> dates = new ArrayList<Date>();
        Date startdate = getStartDateOfCurrentDay(date);
        Date nextday = getStartDateOfNextDay(date);
        int step = 2;
        dates.add(startdate);
        for (int i = 1; i < 12; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startdate);
            calendar.add(Calendar.HOUR_OF_DAY, i * step);
            dates.add(calendar.getTime());
        }
        dates.add(nextday);
        return dates;
    }

    /**
     * 根据日期当前日期顺延一周后的起始时间
     *
     * @param date
     * @return
     */
    public static Date getStartDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /*
     * 封装一周之内时间区域
     */
    public static List<Date> getStaticByWeekDateArea(Date date) {
        List<Date> dates = new ArrayList<Date>();
        Date startdate = getStartDateOfCurrentDay(date);
        Date nextday = getStartDateOfNextSevenDay(date);
        dates.add(startdate);
        for (int i = 1; i < 7; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startdate);
            calendar.add(Calendar.DAY_OF_MONTH, i);
            dates.add(calendar.getTime());
        }
        dates.add(nextday);
        return dates;
    }

    /*
     * 封装一周之内时间区域List<String>
     */
    public static List<String> getStaticByWeekLabel(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        List<String> dates = new ArrayList<String>();
        Date startdate = getStartDateOfCurrentDay(date);
        Date nextday = getStartDateOfNextSevenDay(date);
        dates.add(dateFormat.format(startdate));
        for (int i = 1; i < 7; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startdate);
            calendar.add(Calendar.DAY_OF_MONTH, i);
            dates.add(dateFormat.format(calendar.getTime()));
        }
        return dates;
    }

    /*
     * 封装一月之内时间区域
     */
    public static List<Date> getStaticByMonthDateArea(Date date) {
        List<Date> dates = new ArrayList<Date>();
        Date startdate = getStartDateOfMonth(date);
        Date nextday = getStartDateOfNextMonth(date);
        long daydiff = getDiffDays(startdate, nextday);
        dates.add(startdate);
        for (int i = 1; i < daydiff; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startdate);
            calendar.add(Calendar.DAY_OF_MONTH, i);
            dates.add(calendar.getTime());
        }
        dates.add(nextday);
        return dates;
    }

    public static String formatDate(Date date, String format) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            result = sdf.format(date);
        }
        return result;
    }


    public static Date formatString(String str, String format) throws Exception {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    public static String getCurrentDateStr() throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String getStartDateStr() throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern_yyyyMMdd);
        return sdf.format(date) + " 00:00:00";
    }

    public static String getEndDateStr() throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern_yyyyMMdd);
        return sdf.format(date) + " 23:59:59";
    }

    public static void main(String args[]) throws Exception {
//        System.out.println(getStartDateStr());
//        System.out.println(getEndDateStr());
        System.out.println("".getBytes());
    }

}
