package com.negomatic.retailer.ui.orders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.negomatic.retailer.entity.OrderNote;
import com.negomatic.retailer.entity.OrderNoteItem;
import com.negomatic.retailer.repository.OrderNoteRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class OrderNoteViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private LiveData<List<OrderNote>> list;
    private LiveData<List<OrderNoteItem>> orderNoteItems;
    private LiveData<OrderNote> order;
    private OrderNoteRepository repository;

    @Inject
    public OrderNoteViewModel(OrderNoteRepository repository){this.repository =  repository;}
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

    public LiveData<OrderNote> getById(int id){
        this.order = repository.getById(id);
        return order;
    }
    public LiveData<List<OrderNoteItem>> getOrderNoteItems(int orderNoteId){
        this.orderNoteItems = repository.getOrderNoteItems(orderNoteId);
        return this.orderNoteItems;
    }
}
