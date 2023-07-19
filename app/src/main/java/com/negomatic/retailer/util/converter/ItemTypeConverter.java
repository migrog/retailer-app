package com.negomatic.retailer.util.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order.ItemType;

import java.lang.reflect.Type;

public class ItemTypeConverter {
    @TypeConverter
    public static String fromModel(ItemType value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<ItemType>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static ItemType toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<ItemType>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
