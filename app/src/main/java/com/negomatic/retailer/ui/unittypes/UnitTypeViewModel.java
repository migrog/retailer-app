package com.negomatic.retailer.ui.unittypes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.negomatic.retailer.entity.UnitType;
import com.negomatic.retailer.repository.ItemRepository;

import java.util.List;

import javax.inject.Inject;

public class UnitTypeViewModel extends ViewModel {
    private LiveData<List<UnitType>> list;
    private ItemRepository repository;

    @Inject UnitTypeViewModel(ItemRepository repository){
        this.repository = repository;
    }

    public void init(){
        if(this.list != null){
            return;
        }
        this.list = repository.listUnitType();
    }

    public LiveData<List<UnitType>> list(){
        return this.list;
    }
    public UnitType saveUnitType(UnitType unitType){return repository.saveUnitType(unitType);}
    public UnitType updateUnitType(UnitType unitType){
        return repository.updateUnitType(unitType);
    }

    public boolean saveUnitTypes(List<UnitType> unitTypes) {
        return repository.saveUnitTypes(unitTypes);
    }
}
