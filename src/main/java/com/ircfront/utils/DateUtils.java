package com.ircfront.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

public class DateUtils {
    public static String getStringDate(Timestamp date){
        return date.toString();
    }
}
