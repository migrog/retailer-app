package com.negomatic.retailer.ui.documenttypes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.negomatic.retailer.entity.IdentityDocumentType;
import com.negomatic.retailer.repository.ConfigurationRepository;

import java.util.List;

import javax.inject.Inject;

public class IdentityDocumentTypeViewModel extends ViewModel {
    private LiveData<List<IdentityDocumentType>> list;
    private ConfigurationRepository repository;

    @Inject IdentityDocumentTypeViewModel(ConfigurationRepository repository){
        this.repository = repository;
    }

    public void init(String countryId){
        if(this.list != null){
            return;
        }
        this.list = repository.listIdentityDocumentTypes(countryId);
    }

    public LiveData<List<IdentityDocumentType>> list(){
        return this.list;
    }
}
