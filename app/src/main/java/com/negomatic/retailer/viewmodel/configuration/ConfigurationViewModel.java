package com.negomatic.retailer.viewmodel.configuration;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.negomatic.retailer.entity.IdentityDocumentType;
import com.negomatic.retailer.entity.StateType;
import com.negomatic.retailer.repository.ConfigurationRepository;

import java.util.List;

import javax.inject.Inject;

public class ConfigurationViewModel extends ViewModel {
    private LiveData<List<StateType>> stateTypes;
    private LiveData<List<IdentityDocumentType>> identityDocumentTypes;
    private ConfigurationRepository repository;

    @Inject
    public  ConfigurationViewModel(ConfigurationRepository repository){this.repository =  repository;}
    //region state_types
    public void initStateTypes(){
        if(this.stateTypes != null){
            return;
        }
        this.stateTypes = repository.listStateTypes();
    }
    public LiveData<List<StateType>> listStateTypes(){
        return this.stateTypes;
    }
    //endregion

    //region document_types
    public void initIdentityDocumentTypes(String countryId){
        if(this.identityDocumentTypes != null){
            return;
        }
        this.identityDocumentTypes = repository.listIdentityDocumentTypes(countryId);
    }
    public LiveData<List<IdentityDocumentType>> listIdentityDocumentTypes(){return this.identityDocumentTypes;}
    //endregion
}
