package com.negomatic.retailer.di.module;

import com.negomatic.retailer.ui.cart.CartFragment;
import com.negomatic.retailer.ui.cart.item.CartItemFragment;
import com.negomatic.retailer.ui.categories.CategoriesBottomSheetFragment;
import com.negomatic.retailer.ui.categories.newcategory.NewCategoryDialogFragment;
import com.negomatic.retailer.ui.customers.CustomersFragment;
import com.negomatic.retailer.ui.customers.newcustomer.NewCustomerFragment;
import com.negomatic.retailer.ui.customers.viewcustomer.ViewCustomerFragment;
import com.negomatic.retailer.ui.documenttypes.DocumentTypesBottomSheetFragment;
import com.negomatic.retailer.ui.inventory.InventoryFragment;
import com.negomatic.retailer.ui.catalog.CatalogFragment;
import com.negomatic.retailer.ui.inventory.newitem.NewItemFragment;
import com.negomatic.retailer.ui.inventory.viewitem.ViewItemFragment;
import com.negomatic.retailer.ui.menu.MenuFragment;
import com.negomatic.retailer.ui.menu.create.MenuCreateBottomSheetFragment;
import com.negomatic.retailer.ui.orders.OrdersFragment;
import com.negomatic.retailer.ui.unittypes.NewUnitTypeDialogFragment;
import com.negomatic.retailer.ui.unittypes.UnitTypesBottomSheetFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract CatalogFragment contributeItemsFragment();

    @ContributesAndroidInjector
    abstract NewItemFragment contributeNewItemFragment();

    @ContributesAndroidInjector
    abstract ViewItemFragment contributeViewItemFragment();

    @ContributesAndroidInjector
    abstract CartItemFragment contributeDetailItemCartFragment();

    @ContributesAndroidInjector
    abstract CartFragment contributeItemsCartFragment();

    @ContributesAndroidInjector
    abstract OrdersFragment contributeIOrdersFragment();

    @ContributesAndroidInjector
    abstract MenuFragment contributeMenuFragment();

    @ContributesAndroidInjector
    abstract MenuCreateBottomSheetFragment contributeMenuCreateBottomSheetFragment();

    @ContributesAndroidInjector
    abstract InventoryFragment contributeInventoryFragment();

    @ContributesAndroidInjector
    abstract NewCategoryDialogFragment contributeNewCategoryDialogFragment();

    @ContributesAndroidInjector
    abstract CategoriesBottomSheetFragment contributeCategoriesBottomSheetFragment();

    @ContributesAndroidInjector
    abstract UnitTypesBottomSheetFragment contributeUnitTypesBottomSheetFragment();

    @ContributesAndroidInjector
    abstract NewUnitTypeDialogFragment contributeNewUnitTypeDialogFragment();

    @ContributesAndroidInjector
    abstract CustomersFragment contributeCustomersFragment();

    @ContributesAndroidInjector
    abstract NewCustomerFragment contributeNewCustomerFragment();

    @ContributesAndroidInjector
    abstract ViewCustomerFragment contributeViewCustomerFragment();

    @ContributesAndroidInjector
    abstract DocumentTypesBottomSheetFragment contributeDocumentTypesBottomSheetFragment();



}
