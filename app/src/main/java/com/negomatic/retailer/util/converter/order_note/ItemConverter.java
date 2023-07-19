package com.negomatic.retailer.util.converter.order_note;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order_note.Item;

import java.lang.reflect.Type;
import java.util.List;

public class ItemConverter {
    @TypeConverter
    public static String fromModel(Item value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<Item>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static Item toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<Item>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
