package com.negomatic.retailer.util.converter.order;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order.OrderPurchaseField;

import java.lang.reflect.Type;

public class OrderPurchaseFieldConverter {
    @TypeConverter
    public static String fromModel(OrderPurchaseField value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<OrderPurchaseField>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static OrderPurchaseField toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<OrderPurchaseField>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
