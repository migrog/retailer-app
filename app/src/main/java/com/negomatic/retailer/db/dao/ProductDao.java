package com.negomatic.retailer.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.negomatic.retailer.entity.Product;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM product")
    LiveData<List<Product>> load();

    @Insert(onConflict = REPLACE)
    void save(Product user);
}
