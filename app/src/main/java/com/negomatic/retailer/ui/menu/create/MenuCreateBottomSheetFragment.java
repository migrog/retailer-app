package com.negomatic.retailer.ui.menu.create;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.negomatic.retailer.R;
import com.negomatic.retailer.model.Menu;
import com.negomatic.retailer.ui.customers.newcustomer.NewCustomerActivity;
import com.negomatic.retailer.ui.inventory.newitem.NewItemActivity;
import com.negomatic.retailer.ui.menu.MenuViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

import static com.negomatic.retailer.App.context;

public class MenuCreateBottomSheetFragment extends BottomSheetDialogFragment implements MenuCreateAdapter.ClickListener{
    List<Menu> menus = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private MenuViewModel viewModel;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    public static MenuCreateBottomSheetFragment newInstance(){
        final MenuCreateBottomSheetFragment fragment = new MenuCreateBottomSheetFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_create_bottom_sheet, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);

        adapter = new MenuCreateAdapter(menus, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new MenuViewModel();
        viewModel.init();
        viewModel.createList().observe(getViewLifecycleOwner(), list ->updateUI(list));
    }

    private void updateUI(List<Menu> list){
        this.menus.clear();
        this.menus.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v, int position) {
        Menu menu = menus.get(position);
        Intent intent = null;
        switch (menu.getValue()){
            case "customer":
                intent = new Intent(context, NewCustomerActivity.class);
                break;
            case "product":
                intent = new Intent(context, NewItemActivity.class);
                break;
        }
        startActivity(intent);
        getDialog().dismiss();
    }
}
