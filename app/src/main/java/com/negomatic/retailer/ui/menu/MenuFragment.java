package com.negomatic.retailer.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.negomatic.retailer.R;
import com.negomatic.retailer.model.Menu;
import com.negomatic.retailer.ui.customers.CustomersActivity;
import com.negomatic.retailer.ui.inventory.InventoryActivity;
import com.negomatic.retailer.ui.inventory.newitem.NewItemActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

import static com.negomatic.retailer.App.context;

public class MenuFragment extends Fragment implements MenuAdapter.OnItemListener{

    List<Menu> menus = new ArrayList<>();

    private MenuViewModel menuViewModel;
    private RecyclerView.Adapter adapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureDagger();
        configureLayouts();
        configureViewModel();
    }
    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

    private void configureLayouts(){

        adapter = new MenuAdapter(menus, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,GridLayoutManager.VERTICAL));
    }

    private void configureViewModel(){
        menuViewModel = new MenuViewModel();
        menuViewModel.init();
        menuViewModel.mainList().observe(getViewLifecycleOwner(), items->updateUI(items));
    }

    private void updateUI(List<Menu> list){
        this.menus.clear();
        this.menus.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        Menu menu = menus.get(position);
        Intent intent = null;
        switch (menu.getValue()){
            case "inventory":
                intent = new Intent(context, InventoryActivity.class);
                break;
            case "customers":
                intent = new Intent(context, CustomersActivity.class);
                break;
            default:
                intent = new Intent(context, NewItemActivity.class);
                break;
        }

        startActivity(intent);
    }
}
