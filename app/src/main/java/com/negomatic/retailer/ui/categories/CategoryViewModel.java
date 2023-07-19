package com.negomatic.retailer.ui.categories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.negomatic.retailer.entity.Category;
import com.negomatic.retailer.repository.ItemRepository;

import java.util.List;

import javax.inject.Inject;

public class CategoryViewModel extends ViewModel {
    private LiveData<List<Category>> list;
    private ItemRepository repository;

    @Inject CategoryViewModel(ItemRepository repository){this.repository = repository;}
    public void init(){
        if(this.list!=null){
            return;
        }
        this.list = repository.listCategory();
    }

    public LiveData<List<Category>> list(){
        return this.list;
    }
    public Category saveCategory(Category category){
        return repository.saveCategory(category);
    }
    public boolean saveCategories(List<Category> categories){
        return repository.saveCategories(categories);
    }
    public Category updateCategory(Category category){
        return repository.updateCategory(category);
    }
}
