package com.negomatic.retailer.util.converter.order_note;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order_note.Establishment;

import java.lang.reflect.Type;

public class EstablishmentConverter {
    @TypeConverter
    public static String fromModel(Establishment value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<Establishment>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static Establishment toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<Establishment>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
