package com.negomatic.retailer.util.converter.order;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order.OrderPurchaseFieldTotal;

import java.lang.reflect.Type;

public class OrderPurchaseFieldTotalConverter {
    @TypeConverter
    public static String fromModel(OrderPurchaseFieldTotal value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<OrderPurchaseFieldTotal>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static OrderPurchaseFieldTotal toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<OrderPurchaseFieldTotal>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
