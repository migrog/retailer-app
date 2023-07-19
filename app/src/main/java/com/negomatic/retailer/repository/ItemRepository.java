package com.negomatic.retailer.repository;

import androidx.lifecycle.LiveData;

import com.negomatic.retailer.api.ItemApi;
import com.negomatic.retailer.db.dao.ItemDao;
import com.negomatic.retailer.entity.Category;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.UnitType;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemRepository {
    private final ItemApi webservice;
    private final ItemDao itemDao;
    private final Executor executor;
    private final ExecutorService executorService;

    @Inject
    public ItemRepository(ItemApi webservice, ItemDao itemDao, Executor executor,ExecutorService executorService){
        this.webservice = webservice;
        this.itemDao = itemDao;
        this.executor = executor;
        this.executorService = executorService;
    }

    //region Product
    public LiveData<List<Item>> list(){
        return itemDao.list();
    }

    public Item save(Item item){
        Callable<Long> insertCallable = ()-> itemDao.save(item);
        long rowId = 0;
        Future<Long> future = executorService.submit(insertCallable);
        try {
            rowId = future.get();
            item.setId((int) rowId);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return item;

    }
    public LiveData<Item> getById(int id){
        return itemDao.getById(id);
    }
    //endregion

    //region Category
    public LiveData<List<Category>> listCategory(){
        return itemDao.listCategory();
    }
    public Category saveCategory(Category category){
        Callable<Long> insertCallable = ()-> itemDao.saveCategory(category);
        long rowId = 0;
        Future<Long> future = executorService.submit(insertCallable);
        try {
            rowId = future.get();
            category.setId((int) rowId);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return category;

    }
    public boolean saveCategories(List<Category> categories){
        boolean success = false;
        Runnable task = ()-> itemDao.deleteAllCategories();
        Future<?> future = executorService.submit(task);
        try{
            future.get();
            Runnable categoriesTask = ()-> itemDao.insertAllCategories(categories);
            Future<?> categoriesFuture = executorService.submit(categoriesTask);
            categoriesFuture.get();

            success = true;
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return success;
    }
    public Category updateCategory(Category category){
        Callable<?> callable = ()-> itemDao.updateCategory(category.getId(), category.getName(), category.getUpdatedAt());
        Future<?> future = executorService.submit(callable);
        try {
            future.get();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return category;
    }
    public LiveData<Category> getCategoryById(int id){
        return itemDao.getCategoryById(id);
    }
    //endregion

    //region UnitType
    public LiveData<List<UnitType>> listUnitType() {
        return itemDao.listUnitType();
    }
    public UnitType saveUnitType(UnitType unitType){
        Callable<Long> insertCallable = ()-> itemDao.saveUnitType(unitType);
        long rowId = 0;
        Future<Long> future = executorService.submit(insertCallable);
        try {
            rowId = future.get();
            unitType.setId((int) rowId);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return unitType;

    }
    public UnitType updateUnitType(UnitType unitType){
        Callable<?> callable = ()-> itemDao.updateUnitType(unitType.getId(), unitType.getDescription(), unitType.getSymbol());
        Future<?> future = executorService.submit(callable);
        try {
            future.get();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return unitType;
    }

    public boolean saveUnitTypes(List<UnitType> unitTypes) {
        boolean success = false;
        Runnable task = ()-> itemDao.deleteAllUnitTypes();
        Future<?> future = executorService.submit(task);
        try{
            future.get();
            Runnable unitTypesTask = ()-> itemDao.insertAllUnitTypes(unitTypes);
            Future<?> unitTypesFuture = executorService.submit(unitTypesTask);
            unitTypesFuture.get();

            success = true;
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return success;
    }
    //endregion

}
