package com.negomatic.retailer.util.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ObjectListConverter {
    @TypeConverter
    public static String fromModel(List<Object> value){
        if (value == null || value.size() == 0) {
            return (null);
        }
        Type type = new TypeToken<List<Object>>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static List<Object> toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<List<Object>>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
