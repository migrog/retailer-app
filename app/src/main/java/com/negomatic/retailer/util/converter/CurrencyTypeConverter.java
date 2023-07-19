package com.negomatic.retailer.util.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order.CurrencyType;

import java.lang.reflect.Type;

public class CurrencyTypeConverter {
    @TypeConverter
    public static String fromModel(CurrencyType value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<CurrencyType>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static CurrencyType toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<CurrencyType>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
