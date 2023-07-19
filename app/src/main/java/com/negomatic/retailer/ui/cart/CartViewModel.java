package com.negomatic.retailer.ui.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.App;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.OrderNote;
import com.negomatic.retailer.entity.OrderNoteItem;
import com.negomatic.retailer.prefs.CartPrefs;
import com.negomatic.retailer.repository.OrderNoteRepository;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class CartViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<OrderNoteItem> > list;
    private OrderNoteRepository repository;

    @Inject
    public CartViewModel(OrderNoteRepository repository){
        this.repository = repository;
    }

    public void init(){
        if(this.list != null){
            return;
        }
        String json = CartPrefs.get(App.context).getString("PREF_CART_ITEMS",null);
        Type type = new TypeToken<List<OrderNoteItem>>(){}.getType();
        List<OrderNoteItem> value = new Gson().fromJson(json, type);
        list = new MutableLiveData<>();
        list.setValue(value);
    }
    public LiveData<List<OrderNoteItem>> list(){

        return this.list;
    }
    public boolean saveOrderAndItems(OrderNote order, List<OrderNoteItem> items)
    {
        return repository.saveOrderAndItems(order, items);
    }
}