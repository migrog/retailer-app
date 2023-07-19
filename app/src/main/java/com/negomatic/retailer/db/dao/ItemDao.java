package com.negomatic.retailer.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.negomatic.retailer.entity.Category;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.OrderNoteItem;
import com.negomatic.retailer.entity.UnitType;
import com.negomatic.retailer.util.converter.date.DateConverter;
import com.negomatic.retailer.util.converter.date.LocalDateTimeConverter;

import java.time.LocalDateTime;
import java.util.List;

import kotlin.Unit;

import static androidx.room.OnConflictStrategy.REPLACE;
@Dao
@TypeConverters(LocalDateTimeConverter.class)
public interface ItemDao {
    //region Product
    @Query("SELECT * FROM items")
    LiveData<List<Item>> list();

    @Query("SELECT * FROM items WHERE id = :id")
    LiveData<Item> getById(int id);

    @Insert(onConflict = REPLACE)
    long save(Item item);
    //endregion

    //region Category
    @Query("SELECT * FROM categories")
    LiveData<List<Category>> listCategory();

    @Insert(onConflict = REPLACE)
    long saveCategory(Category category);

    @Insert(onConflict = REPLACE)
    void insertAllCategories(List<Category> list);

    @Query("DELETE FROM categories")
    void deleteAllCategories();

    @Query("UPDATE categories SET name = :name, updatedAt = :updateAt WHERE id = :id")
    int updateCategory(int id, String name, LocalDateTime updateAt);

    @Query("SELECT * FROM categories WHERE id = :id")
    LiveData<Category> getCategoryById(int id);
    //endregion

    //region UnitType
    @Query("SELECT * FROM cat_unit_types")
    LiveData<List<UnitType>> listUnitType();

    @Insert(onConflict = REPLACE)
    long saveUnitType(UnitType unitType);

    @Query("UPDATE cat_unit_types SET description = :description, symbol = :symbol WHERE id = :id")
    int updateUnitType(int id, String description, String symbol);

    @Query("DELETE FROM cat_unit_types")
    void deleteAllUnitTypes();

    @Insert(onConflict = REPLACE)
    void insertAllUnitTypes(List<UnitType> unitTypes);
    //endregion
}
