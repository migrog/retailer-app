package com.negomatic.retailer.util.converter.order;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.model.order.WarehouseRoot;

import java.lang.reflect.Type;
import java.util.List;

public class WarehouseRootConverter {
    @TypeConverter
    public static String fromWarehouseRootList(List<WarehouseRoot> warehouseRootList){
        if (warehouseRootList == null || warehouseRootList.size() == 0) {
            return (null);
        }
        Type type = new TypeToken<List<WarehouseRoot>>(){}.getType();
        return new Gson().toJson(warehouseRootList, type);
    }

    @TypeConverter
    public static List<WarehouseRoot> toWarehouseRootList(String warehouseRootString){
        if(warehouseRootString == null){
            return (null);
        }
        Type type = new TypeToken<List<WarehouseRoot>>(){}.getType();
        return new Gson().fromJson(warehouseRootString,type);
    }
}
