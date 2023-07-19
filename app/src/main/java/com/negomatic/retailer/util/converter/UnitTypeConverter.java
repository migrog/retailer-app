package com.negomatic.retailer.util.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order.UnitType;

import java.lang.reflect.Type;

public class UnitTypeConverter {
    @TypeConverter
    public static String fromModel(UnitType value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<UnitType>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static UnitType toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<UnitType>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
