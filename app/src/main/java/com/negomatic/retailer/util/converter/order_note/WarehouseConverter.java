package com.negomatic.retailer.util.converter.order_note;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order_note.Warehouse;

import java.lang.reflect.Type;
import java.util.List;

public class WarehouseConverter {
    @TypeConverter
    public static String fromModel(List<Warehouse> value){
        if (value == null || value.size() == 0) {
            return (null);
        }
        Type type = new TypeToken<List<Warehouse>>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static List<Warehouse> toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<List<Warehouse>>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
