package com.negomatic.retailer.db.dao;

import android.icu.text.Replaceable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.negomatic.retailer.entity.OrderNote;
import com.negomatic.retailer.entity.OrderNoteItem;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface OrderNoteDao {
    @Insert(onConflict = REPLACE)
    void InsertAllItems(List<OrderNoteItem> list);

    @Insert(onConflict = REPLACE)
    long InsertOrder(OrderNote value);

    @Query("SELECT * FROM order_notes n ORDER BY n.createdAt DESC")
    LiveData<List<OrderNote>> ListOrder();
}