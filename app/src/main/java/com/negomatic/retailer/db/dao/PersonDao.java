package com.negomatic.retailer.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.Person;
import com.negomatic.retailer.util.converter.date.LocalDateTimeConverter;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(LocalDateTimeConverter.class)
public interface PersonDao {
    //region Customer
    @Query("SELECT *FROM persons where type = 'customers'")
    LiveData<List<Person>> getCustomers();

    @Insert(onConflict = REPLACE)
    long saveCustomer(Person customer);

    @Query("SELECT * FROM persons WHERE id = :id and type = 'customers'")
    LiveData<Person> getCustomerById(int id);
    //endregion
}
