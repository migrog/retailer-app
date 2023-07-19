package com.negomatic.retailer.repository;

import androidx.lifecycle.LiveData;

import com.negomatic.retailer.db.dao.ConfigurationDao;
import com.negomatic.retailer.entity.IdentityDocumentType;
import com.negomatic.retailer.entity.OrderNote;
import com.negomatic.retailer.entity.StateType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConfigurationRepository {
    private final ConfigurationDao dao;
    private final Executor executor;
    private final ExecutorService executorService;

    @Inject
    public ConfigurationRepository(ConfigurationDao dao, Executor executor, ExecutorService executorService){
        this.dao = dao;
        this.executor = executor;
        this.executorService = executorService;
    }

    public boolean saveStateTypes(List<StateType> list){
        boolean success = false;
        Runnable task = () -> dao.InsertStateTypes(list);
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
    public LiveData<List<StateType>> listStateTypes(){
        return dao.ListStateTypes();
    }

    public LiveData<List<IdentityDocumentType>> listIdentityDocumentTypes(String countryId) {
        return dao.ListIdentityDocumentTypes(countryId);
    }
}
