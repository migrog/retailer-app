package com.negomatic.retailer.util.converter.date;

import androidx.room.TypeConverter;

import java.util.Date;
import java.util.Timer;

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toTimestamp(Date value) {
        return value == null ? null : value.getTime();
    }
}
