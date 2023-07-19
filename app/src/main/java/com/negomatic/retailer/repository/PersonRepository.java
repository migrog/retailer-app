package com.negomatic.retailer.repository;

import androidx.lifecycle.LiveData;

import com.negomatic.retailer.db.dao.ItemDao;
import com.negomatic.retailer.db.dao.PersonDao;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.Person;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PersonRepository {
    private final PersonDao dao;
    private final Executor executor;
    private final ExecutorService executorService;

    @Inject
    public PersonRepository(PersonDao dao, Executor executor, ExecutorService executorService){
        this.dao = dao;
        this.executor = executor;
        this.executorService = executorService;
    }

    //region Customer
    public LiveData<List<Person>> listCustomer(){return dao.getCustomers();}

    public Person saveCustomer(Person customer) {
        Callable<Long> insertCallable = ()-> dao.saveCustomer(customer);
        long rowId = 0;
        Future<Long> future = executorService.submit(insertCallable);
        try {
            rowId = future.get();
            customer.setId((int) rowId);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return customer;
    }
    public LiveData<Person> getCustomerById(int id){
        return dao.getCustomerById(id);
    }
    //endregion
}
