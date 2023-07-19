package com.negomatic.retailer.util.converter.order;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order.Warehouse;

import java.lang.reflect.Type;

public class WarehouseConverter {
    @TypeConverter
    public static String fromWarehouse(Warehouse warehouse){
        if (warehouse == null) {
            return (null);
        }
        Type type = new TypeToken<Warehouse>(){}.getType();
        return new Gson().toJson(warehouse, type);
    }

    @TypeConverter
    public static Warehouse toWarehouse(String warehouseString){
        if(warehouseString == null){
            return (null);
        }
        Type type = new TypeToken<Warehouse>(){}.getType();
        return new Gson().fromJson(warehouseString,type);
    }
}