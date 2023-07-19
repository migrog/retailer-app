package com.negomatic.retailer.util.converter.order_note;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order_note.District;

import java.lang.reflect.Type;

public class DistrictConverter {
    @TypeConverter
    public static String fromModel(District value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<District>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static District toModel(String value) {
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<District>() {
        }.getType();
        return new Gson().fromJson(value, type);
    }
}

