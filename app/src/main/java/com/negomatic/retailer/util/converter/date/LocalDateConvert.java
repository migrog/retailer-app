package com.negomatic.retailer.util.converter.date;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateConvert {
    @TypeConverter
    public static LocalDate toDate(Long value) {
        return value == null ? null : Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @TypeConverter
    public static Long toTimestamp(LocalDate value) {
        return value == null ? null : value.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
