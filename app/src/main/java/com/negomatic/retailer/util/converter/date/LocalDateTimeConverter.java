package com.negomatic.retailer.util.converter.date;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class LocalDateTimeConverter {
    @TypeConverter
    public static LocalDateTime toDate(Long value) {
        return value == null ? null : Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalDateTime();
        //LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
    }
    @TypeConverter
    public static Long toTimestamp(LocalDateTime value) {
        return value == null ? null : value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
