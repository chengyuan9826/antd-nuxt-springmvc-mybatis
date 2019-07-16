package com.report.common.util;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取月份，两位数
     * @return
     */
    public static String getMonth(){
        SimpleDateFormat sf = new SimpleDateFormat("MM");
        return sf.format(new Date());
    }

    /**
     * 获取年份
     * @return
     */
    public static String getYear(){
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    /**
     * 获取本周周一的日期
     */
    public static String getWeekMondayDate(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        return df.format(c.getTime());
    }

    /**
     * 获取本周日的日期
     */
    public static String getWeekSundayDate(){
        Calendar c = Calendar.getInstance();
        //必须做以下设置，不然是以周日作为第一天的历法来计算的，结果是上周的周日
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        return df.format(c.getTime());
    }

}
