package com.negomatic.retailer.ui.inventory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.negomatic.retailer.entity.Category;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.repository.ItemRepository;

import java.util.List;

import javax.inject.Inject;

public class InventoryViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private LiveData<List<Item>> list;
    private ItemRepository repository;
    private LiveData<Item> item;
    private LiveData<Category> category;

    @Inject
    public InventoryViewModel(ItemRepository repository){
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

    public Item saveItem(Item item){
        return repository.save(item);
    }
    public LiveData<Item> getItem(int id){
        this.item = repository.getById(id);
        return item;
    }
    public LiveData<Category> getCategory(int id){
        this.category = repository.getCategoryById(id);
        return category;
    }
}
