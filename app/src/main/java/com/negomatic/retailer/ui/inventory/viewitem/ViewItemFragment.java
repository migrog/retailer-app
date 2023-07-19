package com.negomatic.retailer.ui.inventory.viewitem;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.Category;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.UnitType;
import com.negomatic.retailer.model.Attribute;
import com.negomatic.retailer.ui.categories.CategoryViewModel;
import com.negomatic.retailer.ui.inventory.InventoryViewModel;
import com.negomatic.retailer.ui.inventory.newitem.NewItemActivity;
import com.negomatic.retailer.ui.unittypes.UnitTypeViewModel;
import com.negomatic.retailer.util.enumerado.ActionType;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class ViewItemFragment extends Fragment {

    public static final int PICK_IMAGE = 1;
    public static final int CAPTURE_PHOTO = 2;
    String currentPhotoPath;

    private Item item = new Item();
    private List<Attribute> attributes = new ArrayList<>();

    private UnitTypeViewModel unitTypeViewModel;
    private CategoryViewModel categoryViewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private InventoryViewModel mViewModel;

    @BindView(R.id.iv_image_item)
    ImageView iv_image_item;

    @BindView(R.id.til_name_item)
    TextInputLayout til_name_item;
    @BindView(R.id.et_name_item)
    TextInputEditText et_name_item;


    @BindView(R.id.til_sale_unit_price)
    TextInputLayout til_sale_unit_price;
    @BindView(R.id.et_sale_unit_price)
    TextInputEditText et_sale_unit_price;

    @BindView(R.id.et_unit)
    TextInputEditText et_unit;

    @BindView(R.id.et_item_code)
    TextInputEditText et_item_code;

    @BindView(R.id.et_barcode)
    TextInputEditText et_barcode;

    @BindView(R.id.et_category)
    TextInputEditText et_category;

    @BindView(R.id.et_description)
    TextInputEditText et_description;

    @BindView(R.id.sv_view_item)
    ScrollView sv_view_item;

    public ViewItemFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_view_item, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        Bundle bundle = getActivity().getIntent().getExtras();
        int itemId = bundle.getInt("itemId");

        //unit types
        unitTypeViewModel = new ViewModelProvider(this, viewModelFactory).get(UnitTypeViewModel.class);
        unitTypeViewModel.init();

        //categories
        categoryViewModel = new ViewModelProvider(this, viewModelFactory).get(CategoryViewModel.class);
        categoryViewModel.init();

        //item
        mViewModel = new ViewModelProvider(this, viewModelFactory).get(InventoryViewModel.class);
        mViewModel.getItem(itemId).observe(getViewLifecycleOwner(), i->updateUI(i));
    }
    private void updateUI(Item item){
        this.item = item;
        if(item != null){
            et_name_item.setText(item.getName());
            et_sale_unit_price.setText(String.valueOf(item.getSaleUnitPrice()));

            et_item_code.setText(item.getItemCode());
            et_barcode.setText(item.getBarcode());

            et_description.setText(item.getDescription());

            //unidad
            unitTypeViewModel.list().observe(getViewLifecycleOwner(), _unitTypes->{
                if(_unitTypes != null){
                    UnitType _unitType = _unitTypes.stream().filter(x-> String.valueOf(x.getId()).equals(item.getUnitTypeId())).findFirst().orElse(null);
                    if(_unitType != null)
                        et_unit.setText(_unitType.getDescription());
                    else
                        et_unit.setText("Sin unidad");
                }
            });

            //categoria
            categoryViewModel.list().observe(getViewLifecycleOwner(),_categories->{
                if(_categories != null && _categories.size()>0){
                    Category _category = _categories.stream().filter(x->x.getId() == item.getCategoryId()).findFirst().orElse(null);
                    if(_category != null)
                        et_category.setText(_category.getName());
                    else
                        et_category.setText("Sin categoría");
                }
            });

        }
    }
    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

}