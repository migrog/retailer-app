package com.negomatic.retailer.util.converter.order;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order.OrderPurchaseFieldItem;

import java.lang.reflect.Type;
import java.util.List;

public class OrderPurchaseFieldItemConverter {
    @TypeConverter
    public static String fromModel(List<OrderPurchaseFieldItem> value){
        if (value == null || value.size() == 0) {
            return (null);
        }
        Type type = new TypeToken<List<OrderPurchaseFieldItem>>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static List<OrderPurchaseFieldItem> toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<List<OrderPurchaseFieldItem>>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
