package com.negomatic.retailer.ui.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.negomatic.retailer.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<Menu>> mainList;
    private MutableLiveData<List<Menu>> createList;

    public void init(){
        if(this.mainList != null && this.createList != null){
            return;
        }
        //main
        List<Menu> _mainList = new ArrayList<>();
        _mainList.add(new Menu(1, "inventory","Inventario",1,"ic_menu_inventory"));
        _mainList.add(new Menu(2, "purchases","Compras",2,"ic_menu_purchases"));
        _mainList.add(new Menu(3, "customers","Clientes",3,"ic_menu_customers"));
        _mainList.add(new Menu(4, "reports","Reportes",4,"ic_menu_reports"));

        mainList = new MutableLiveData<>();
        mainList.setValue(_mainList);

        //create
        List<Menu> _createList = new ArrayList<>();
        _createList.add(new Menu(1, "product","Producto", 1, "ic_menu_inventory"));
        _createList.add(new Menu(3, "customer","Cliente",2,"ic_menu_customers"));

        createList = new MutableLiveData<>();
        createList.setValue(_createList);
    }

    public LiveData<List<Menu>> mainList(){
        return this.mainList;
    }
    public LiveData<List<Menu>> createList(){return this.createList;}

}
