package com.futao.fund.spider.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/20
 */
public class TimeUtil {

    private TimeUtil() {
    }

    /**
     * 当前日期时间
     *
     * @return 当前日期时间
     */
    public static LocalDateTime currentDateTime() {
        return LocalDateTime.now(ZoneOffset.ofHours(8));
    }

    /**
     * 当前日期
     *
     * @return 当前日期
     */
    public static LocalDate currentDate() {
        return currentDateTime().toLocalDate();
    }

    /**
     * 当前时间
     *
     * @return 当前时间
     */
    public static LocalTime currentTime() {
        return currentDateTime().toLocalTime();
    }

    /**
     * 转时间戳
     *
     * @param dateTime 时间
     * @return 时间戳
     */
    public static long toTimestamp(LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 转时间戳
     *
     * @param localDate 时间戳
     * @return
     */
    public static long toTimestamp(LocalDate localDate) {
        return localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
    }
}
