package com.negomatic.retailer.util.converter.order_note;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order_note.Province;

import java.lang.reflect.Type;

public class ProvinceConverter {
    @TypeConverter
    public static String fromModel(Province value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<Province>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static Province toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<Province>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
