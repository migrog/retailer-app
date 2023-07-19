package com.negomatic.retailer.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.negomatic.retailer.db.dao.ConfigurationDao;
import com.negomatic.retailer.db.dao.ItemDao;
import com.negomatic.retailer.db.dao.OrderNoteDao;
import com.negomatic.retailer.db.dao.PersonDao;
import com.negomatic.retailer.db.dao.ProductDao;
import com.negomatic.retailer.entity.CurrencyType;
import com.negomatic.retailer.entity.Category;
import com.negomatic.retailer.entity.Country;
import com.negomatic.retailer.entity.IdentityDocumentType;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.OrderNote;
import com.negomatic.retailer.entity.OrderNoteItem;
import com.negomatic.retailer.entity.Person;
import com.negomatic.retailer.entity.Product;
import com.negomatic.retailer.db.dao.UserDao;
import com.negomatic.retailer.entity.StateType;
import com.negomatic.retailer.entity.UnitType;
import com.negomatic.retailer.entity.User;

@Database(entities = {User.class,Product.class, Item.class, OrderNote.class, OrderNoteItem.class, StateType.class, Category.class, UnitType.class, Person.class, IdentityDocumentType.class, CurrencyType.class, Country.class}, version = 1, exportSchema = false)
//@TypeConverters({DateConverter.class})
public abstract class MyDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile MyDatabase INSTANCE;

    // --- DAO ---
    public abstract UserDao userDao();
    public abstract ProductDao productDao();
    public abstract ItemDao itemDao();
    public abstract OrderNoteDao orderNoteDao();
    public abstract ConfigurationDao configurationDao();
    public abstract PersonDao personDao();
}
