package com.negomatic.retailer.util.converter.order;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order.Customer;

import java.lang.reflect.Type;

public class CustomerConverter {
    @TypeConverter
    public static String fromModel(Customer value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<Customer>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static Customer toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<Customer>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
