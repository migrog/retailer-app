package com.negomatic.retailer.ui.customers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.Person;
import com.negomatic.retailer.repository.PersonRepository;

import java.util.List;

import javax.inject.Inject;

public class CustomerViewModel extends ViewModel {
    private LiveData<List<Person>> list;
    private PersonRepository repository;
    private LiveData<Person> person;

    @Inject
    public CustomerViewModel(PersonRepository repository){
        this.repository = repository;
    }

    public void init(){
        if(this.list != null)
            return;
        this.list = repository.listCustomer();
    }

    public LiveData<List<Person>> list(){
        return this.list;
    }

    public Person saveCustomer(Person customer) {
        return repository.saveCustomer(customer);
    }
    public LiveData<Person> getCustomer(int id){
        this.person = repository.getCustomerById(id);
        return person;
    }
}
