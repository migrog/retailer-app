package com.negomatic.retailer.util.converter.order_note;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order_note.Department;
import com.negomatic.retailer.model.order_note.IdentityDocumentType;

import java.lang.reflect.Type;

public class IdentityDocumentTypeConverter {
    @TypeConverter
    public static String fromModel(IdentityDocumentType value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<IdentityDocumentType>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static IdentityDocumentType toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<IdentityDocumentType>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
