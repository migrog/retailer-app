package com.negomatic.retailer.ui.cart.item;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.OrderNoteItem;
import com.negomatic.retailer.repository.ItemRepository;

import javax.inject.Inject;

public class CartItemViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private LiveData<Item> item;
    private ItemRepository repository;

    @Inject
    public CartItemViewModel(ItemRepository repository){
        this.repository = repository;
    }

    public LiveData<Item> getItem(int id){
        this.item = repository.getById(id);
        return item;
    }
}