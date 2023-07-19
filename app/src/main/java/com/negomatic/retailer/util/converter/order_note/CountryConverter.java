package com.negomatic.retailer.util.converter.order_note;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order_note.Country;

import java.lang.reflect.Type;

public class CountryConverter {
    @TypeConverter
    public static String fromModel(Country value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<Country>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static Country toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<Country>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
