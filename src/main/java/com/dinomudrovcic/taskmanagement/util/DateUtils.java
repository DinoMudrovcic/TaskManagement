package com.dinomudrovcic.taskmanagement.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    private static String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static Date getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String nowStr = LocalDateTime.now().format(formatter);
        LocalDateTime formattedLocalDateTime = LocalDateTime.parse(nowStr, formatter);

        return Date.from(formattedLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
