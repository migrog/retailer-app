package com.negomatic.retailer.ui.orders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.OrderNote;
import com.negomatic.retailer.repository.OrderNoteRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class OrdersViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private LiveData<List<OrderNote>> list;
    private OrderNoteRepository repository;

    @Inject
    public  OrdersViewModel(OrderNoteRepository repository){this.repository =  repository;}
    public void init(){
        if(this.list != null){
          return;
        }
        this.list = repository.list();
    }
    public LiveData<List<OrderNote>> list(){
        return this.list;
    }
    public ArrayList<OrderNote> search(String query){
        ArrayList<OrderNote> found = new ArrayList<>();
        for(OrderNote i: this.list.getValue()){
            found.add(i);
        }
        return found;
    }
}
