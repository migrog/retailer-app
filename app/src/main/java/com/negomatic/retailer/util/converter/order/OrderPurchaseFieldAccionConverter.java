package com.negomatic.retailer.util.converter.order;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order.OrderPurchaseFieldAccion;

import java.lang.reflect.Type;

public class OrderPurchaseFieldAccionConverter {
    @TypeConverter
    public static String fromModel(OrderPurchaseFieldAccion value){
        if (value == null) {
            return (null);
        }
        Type type = new TypeToken<OrderPurchaseFieldAccion>(){}.getType();
        return new Gson().toJson(value, type);
    }

    @TypeConverter
    public static OrderPurchaseFieldAccion toModel(String value){
        if(value == null){
            return (null);
        }
        Type type = new TypeToken<OrderPurchaseFieldAccion>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
