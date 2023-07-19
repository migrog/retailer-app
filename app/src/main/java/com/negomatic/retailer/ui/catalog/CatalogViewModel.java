package com.negomatic.retailer.ui.catalog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CatalogViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private LiveData<List<Item>> list;
    private ItemRepository repository;
    private int page = 1;
    private int pageSize = 10;


    @Inject
    public CatalogViewModel(ItemRepository repository){
        this.repository = repository;
    }
    public void init(){
        if(this.list != null)
            return;

        this.list = repository.list();
    }

    public LiveData<List<Item>> list(){

        return this.list;
    }

    public ArrayList<Item> search(String query){
        ArrayList<Item> found = new ArrayList<Item>();
        for(Item d: this.list.getValue()){
            if(d.getName() != null && d.getName().toLowerCase().contains(query.toLowerCase()))
                found.add(d);
        }
        return found;
    }

    public MutableLiveData<List<Item>> paginar(List<Item> list){
        int limit = page * pageSize;
        int total = (int) list.size();
        MutableLiveData<List<Item>> result;

        int mostrados = (pageSize*page < total? pageSize : total - (pageSize*page - pageSize));
        if(total>0){
            if(total>= limit){
                result = (MutableLiveData<List<Item>>) list.stream().skip((page - 1)*pageSize).limit(pageSize);
            }
            else {
                result = (MutableLiveData<List<Item>>) list.stream().skip((page - 1)*pageSize).limit(mostrados);
            }
        }
        else{
            result = (MutableLiveData<List<Item>>) list.stream();
        }
        return result;
    }
}