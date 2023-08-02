package com.negomatic.retailer.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.negomatic.retailer.db.dao.OrderNoteDao;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.OrderNote;
import com.negomatic.retailer.entity.OrderNoteItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class OrderNoteRepository {
    private final OrderNoteDao dao;
    private final Executor executor;
    private final ExecutorService executorService;

    @Inject
    public OrderNoteRepository(OrderNoteDao dao, Executor executor, ExecutorService executorService){
        this.dao = dao;
        this.executor = executor;
        this.executorService = executorService;
    }
    public boolean saveItems(List<OrderNoteItem> list){
        boolean success = false;
        Runnable task = () -> dao.InsertAllItems(list);
        Future<?> future = executorService.submit(task);
        try {
            future.get();
            success = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return success;
    }
    public OrderNote saveOrder(OrderNote value){
        Callable<Long> insertCallable = ()-> dao.InsertOrder(value);
        long rowId = 0;
        Future<Long> future = executorService.submit(insertCallable);
        try {
            rowId = future.get();
            value.setId((int) rowId);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return value;
    }
    public boolean saveOrderAndItems(OrderNote order, List<OrderNoteItem> items){
        long rowId = 0;
        boolean success = false;
        Callable<Long> orderTask = ()-> dao.InsertOrder(order);
        Future<Long> orderFuture = executorService.submit(orderTask);
        try {
            rowId = orderFuture.get();
            order.setId((int) rowId);
            for(OrderNoteItem i : items){
                i.setOrderNoteId((int) rowId);
            }
            Runnable itemsTask = () -> dao.InsertAllItems(items);
            Future<?> itemsFuture = executorService.submit(itemsTask);
            itemsFuture.get();
            success = true;
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return success;
    }
    public LiveData<List<OrderNote>> list(){
        return dao.ListOrder();
    }
    public LiveData<OrderNote> getById(int id){
        return dao.getById(id);
    }
    public LiveData<List<OrderNoteItem>> getOrderNoteItems(int orderNoteId){
        return dao.getOrderNoteItems(orderNoteId);
    }
}
