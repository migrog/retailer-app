package com.negomatic.retailer.util.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order.ItemUnitType;

import java.lang.reflect.Type;

public class ItemUnitTypeConverter {
    @TypeConverter
    public static String fromModel(ItemUnitType value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<ItemUnitType>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static ItemUnitType toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<ItemUnitType>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
