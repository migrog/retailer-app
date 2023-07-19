package com.negomatic.retailer.di.module;

import com.negomatic.retailer.ui.LoginActivity;
import com.negomatic.retailer.ui.MainActivity;
import com.negomatic.retailer.ui.cart.CartActivity;
import com.negomatic.retailer.ui.cart.item.CartItemActivity;
import com.negomatic.retailer.ui.customers.CustomersActivity;
import com.negomatic.retailer.ui.customers.newcustomer.NewCustomerActivity;
import com.negomatic.retailer.ui.customers.viewcustomer.ViewCustomerActivity;
import com.negomatic.retailer.ui.inventory.InventoryActivity;
import com.negomatic.retailer.ui.inventory.newitem.NewItemActivity;
import com.negomatic.retailer.ui.inventory.viewitem.ViewItemActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributePrincipalActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CartItemActivity contributeDetailItemCartActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CartActivity contributeCartActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract InventoryActivity contributeInventoryActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract NewItemActivity contributeNewItemActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ViewItemActivity contributeViewItemActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CustomersActivity contributeCustomersActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract NewCustomerActivity contributeNewCustomerActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ViewCustomerActivity contributeViewCustomerActivity();

}
