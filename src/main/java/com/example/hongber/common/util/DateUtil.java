package com.example.hongber.common.util;

import com.example.hongber.common.constant.ConstDateAmount;
import com.example.hongber.common.constant.ConstDateFormat;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static String getNowDate() {
        SimpleDateFormat sDate = new SimpleDateFormat(ConstDateFormat.YYYY_MM_DD);
        return sDate.format(new Date());
    }

    public static String getFullLastDt() {
        return ConstDateFormat.LAST_YYYY_MM_DD_HH_MM_SS;
    }

    public static String getLastDt() {
        return ConstDateFormat.LAST_YYYY_MM_DD;
    }

    public static String getFullStartDt() {
        return ConstDateFormat.START_YYYY_MM_DD_HH_MM_SS;
    }

    public static String getStartDt() {
        return ConstDateFormat.START_YYYY_MM_DD;
    }

    public static String getTodayString(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getNowDate(String pattern) {
        SimpleDateFormat sDate = new SimpleDateFormat(pattern);
        return sDate.format(new Date());
    }

    public static String getDateToString(Date date, String pattern) {
        SimpleDateFormat sDate = new SimpleDateFormat(pattern);
        return sDate.format(date);
    }

    public static String getStartDtBySearchType(String type) {
        if (ConstDateAmount.WEEK.equals(type)) {
            return getDateToString(DateUtils.addDays(new Date(), - 7), ConstDateFormat.YYYY_MM_DD);
        } else if (ConstDateAmount.MONTH.equals(type)) {
            return getDateToString(DateUtils.addDays(new Date(), - 30), ConstDateFormat.YYYY_MM_DD);
        } else {
            return DateUtil.getNowDate();
        }
    }

    public static int getTimeDiff(Date startDt, Date endDt) {
        long startDtTime = startDt.getTime();
        long endDtTime = endDt.getTime();
        long timeDiff = (startDtTime - endDtTime) / 60000;

        return (int) timeDiff;
    }
}
