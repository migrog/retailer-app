package com.negomatic.retailer.util.converter.order_note;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order_note.Department;

import java.lang.reflect.Type;

public class DepartmentConverter {
    @TypeConverter
    public static String fromModel(Department value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<Department>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static Department toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<Department>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
