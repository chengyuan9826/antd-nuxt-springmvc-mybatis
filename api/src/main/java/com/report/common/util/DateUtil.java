package com.report.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
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
}
