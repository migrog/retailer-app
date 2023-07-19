package com.negomatic.retailer.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.negomatic.retailer.entity.IdentityDocumentType;
import com.negomatic.retailer.entity.OrderNote;
import com.negomatic.retailer.entity.OrderNoteItem;
import com.negomatic.retailer.entity.StateType;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ConfigurationDao {
    //state_types
    @Insert(onConflict = REPLACE)
    void InsertStateTypes(List<StateType> list);

    @Query("SELECT * FROM state_types")
    LiveData<List<StateType>> ListStateTypes();

    //document_types
    @Insert(onConflict = REPLACE)
    void InsertIdentityDocumentType(List<IdentityDocumentType> list);

    @Query("SELECT * FROM cat_identity_document_types WHERE countryId = :countryId")
    LiveData<List<IdentityDocumentType>> ListIdentityDocumentTypes(String countryId);

}