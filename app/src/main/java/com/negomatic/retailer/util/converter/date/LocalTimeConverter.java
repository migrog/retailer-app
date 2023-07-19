package com.negomatic.retailer.util.converter.date;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

public class LocalTimeConverter {
    @TypeConverter
    public static LocalTime toDate(Long value) {
        return value == null ? null : Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalTime();
    }

    @TypeConverter
    public static Long toTimestamp(LocalTime value) {
        return value == null ? null : ZonedDateTime.of(LocalDate.now() ,value, ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
