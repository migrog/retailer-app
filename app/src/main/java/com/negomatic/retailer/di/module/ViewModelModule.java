package com.negomatic.retailer.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.negomatic.retailer.ui.LoginViewModel;
import com.negomatic.retailer.ui.cart.CartViewModel;
import com.negomatic.retailer.ui.cart.item.CartItemViewModel;
import com.negomatic.retailer.ui.categories.CategoryViewModel;
import com.negomatic.retailer.ui.documenttypes.IdentityDocumentTypeViewModel;
import com.negomatic.retailer.viewmodel.configuration.ConfigurationViewModel;
import com.negomatic.retailer.ui.customers.CustomerViewModel;
import com.negomatic.retailer.ui.inventory.InventoryViewModel;
import com.negomatic.retailer.ui.catalog.CatalogViewModel;
import com.negomatic.retailer.ui.orders.OrderNoteViewModel;
import com.negomatic.retailer.ui.unittypes.UnitTypeViewModel;
import com.negomatic.retailer.viewmodel.FactoryViewModel;
import com.negomatic.retailer.di.key.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel repoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CatalogViewModel.class)
    abstract ViewModel bindItemsViewModel(CatalogViewModel repoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CartItemViewModel.class)
    abstract ViewModel bindDetailItemCartViewModel(CartItemViewModel repoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel.class)
    abstract ViewModel bindItemsCartViewModel(CartViewModel repoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(OrderNoteViewModel.class)
    abstract ViewModel bindOrdersViewModel(OrderNoteViewModel repoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ConfigurationViewModel.class)
    abstract ViewModel bindConfigurationViewModel(ConfigurationViewModel repoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(InventoryViewModel.class)
    abstract ViewModel bindInventoryViewModel(InventoryViewModel repoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel.class)
    abstract ViewModel bindCategoryViewModel(CategoryViewModel repoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UnitTypeViewModel.class)
    abstract ViewModel bindUnitTypeViewModel(UnitTypeViewModel repoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CustomerViewModel.class)
    abstract ViewModel bindCustomerViewModel(CustomerViewModel repoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(IdentityDocumentTypeViewModel.class)
    abstract ViewModel bindIdentityDocumentTypeViewModel(IdentityDocumentTypeViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
