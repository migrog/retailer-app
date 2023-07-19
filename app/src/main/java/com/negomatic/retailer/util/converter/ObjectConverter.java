package com.negomatic.retailer.util.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ObjectConverter {
    @TypeConverter
    public static String fromModel(Object value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<Object>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static Object toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<Object>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
