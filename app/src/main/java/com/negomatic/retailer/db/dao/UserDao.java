package com.negomatic.retailer.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.negomatic.retailer.entity.User;
import com.negomatic.retailer.util.converter.date.DateConverter;

import java.util.Date;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public interface UserDao {

    @Insert(onConflict = REPLACE)
    void save(User user);

    @Query("SELECT * FROM user WHERE name = :userLogin")
    LiveData<User> load(String userLogin);

    @Query("SELECT * FROM user WHERE name = :userLogin AND lastRefresh > :lastRefreshMax LIMIT 1")
    User hasUser(String userLogin, Date lastRefreshMax);
}