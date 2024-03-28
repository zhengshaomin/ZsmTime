package cn.zhengshaomin.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 作者：daboluo on 2024/3/29 01:00
 * Email:daboluo719@gmail.com
 */
public class ZsmTime {
    private ZsmTime() {
        // 私有构造方法，防止意外实例化
    }

    /**
     * 格式化时间差
     * @param inputDate 输入时间
     * @return 格式化后的时间差字符串
     */
    public static String formatTimeDifference(String inputDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(inputDate);

            Calendar currentDate = Calendar.getInstance();
            currentDate.setTime(new Date());

            Calendar inputCalendar = Calendar.getInstance();
            inputCalendar.setTime(date);

            long timeDifferenceMillis = currentDate.getTimeInMillis() - date.getTime();
            long timeDifferenceMinutes = Math.abs(timeDifferenceMillis / (60 * 1000));

            if (timeDifferenceMinutes < 60) {
                return timeDifferenceMinutes + " 分钟前";
            }

            if (isSameDay(currentDate, inputCalendar)) {
                return "今天 " + getTimeString(date);
            }

            currentDate.add(Calendar.DAY_OF_MONTH, -1);
            if (isSameDay(currentDate, inputCalendar)) {
                return "昨天 " + getTimeString(date);
            }

            currentDate.add(Calendar.DAY_OF_MONTH, -1);
            if (isSameDay(currentDate, inputCalendar)) {
                return "前天 " + getTimeString(date);
            }

            if (currentDate.get(Calendar.YEAR) != inputCalendar.get(Calendar.YEAR)) {
                return new SimpleDateFormat("yyyy-MM-dd").format(date);
            }

            currentDate.add(Calendar.DAY_OF_MONTH, 3);
            if (date.before(currentDate.getTime())) {
                return new SimpleDateFormat("MM-dd HH:mm").format(date);
            }

            return ""; // Handle other cases or return appropriate result
        } catch (ParseException e) {
            e.printStackTrace();
            return ""; // 返回空字符串表示日期解析失败
        }
    }

    /**
     * 判断是否是同一天
     * @param cal1 日历对象1
     * @param cal2 日历对象2
     * @return 是否是同一天
     */
    private static boolean isSameDay(Calendar cal1, Calendar cal2) {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取时间字符串
     * @param date 时间对象
     * @return 时间字符串
     */
    private static String getTimeString(Date date) {
        return new SimpleDateFormat("HH:mm").format(date);
    }

    /**
     * 计算天数差
     * @param startDateString 开始日期字符串
     * @return 天数差
     */
    public static String getDaysDifference(String startDateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date startDate = format.parse(startDateString);
            Date endDate = new Date(); // 获取当前日期和时间
            long difference = endDate.getTime() - startDate.getTime();
            return String.valueOf(difference / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
            return "-1"; // 返回负数表示日期解析失败
        }
    }

    /**
     * 时间格式化
     * @param date 时间字符串
     * @return 格式化后的时间字符串
     */
    public static String time(String date) {
        return formatTimeDifference(date);
    }

    /**
     * 计算两个日期之间的天数差
     * @param startDateString 开始日期字符串
     * @return 天数差字符串
     */
    public static String day(String startDateString) {
        return getDaysDifference(startDateString);
    }
}
