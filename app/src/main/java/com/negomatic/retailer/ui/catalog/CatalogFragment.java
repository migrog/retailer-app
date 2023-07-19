package com.negomatic.retailer.ui.catalog;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.ui.cart.item.CartItemActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import static com.negomatic.retailer.App.context;

public class CatalogFragment extends Fragment implements CatalogAdapter.ClickListener {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;

    List<Item> items = new ArrayList<>();

    @Inject
    ViewModelProvider.Factory factory;
    private CatalogViewModel mViewModel;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progressBarItems) ProgressBar progressBarItems;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.items_fragment, container, false);
        View view = inflater.inflate(R.layout.fragment_catalog,container,false);
        ButterKnife.bind(this,view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureLayouts();
        this.configureViewModel();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        /*SearchView searchView =(SearchView)menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                search(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });*/
    }
    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }
    private void configureViewModel(){
        mViewModel = new ViewModelProvider(this,factory).get(CatalogViewModel.class);
        mViewModel.init();
        mViewModel.list().observe(getViewLifecycleOwner(),items->updateUI(items));
    }
    private void configureLayouts(){
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CatalogAdapter(this.items,this);
        recyclerView.setAdapter(adapter);
    }
    private void updateUI(List<Item> list){
        //this.progressBarItems.setVisibility(View.VISIBLE);
        if(list != null){
            Log.d("update-items","xxx");
            this.items.addAll(list);
            adapter.notifyDataSetChanged();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                        isScrolling=true;
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    currentItems = layoutManager.getChildCount();
                    totalItems = layoutManager.getItemCount();
                    scrollOutItems = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
                    if(isScrolling &&(currentItems +  scrollOutItems == totalItems))
                        isScrolling = false;
                }
            });
        }
        //this.progressBarItems.setVisibility(View.GONE);
    }
    private void search(String query){
        mViewModel.init();
        ArrayList<Item> result = mViewModel.search(query);
        updateUI(result);
    }

    @Override
    public void onClick(View v, int position) {
        Toast.makeText(context, "Clicked User : " + items.get(position).getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, CartItemActivity.class);
        intent.putExtra("itemId", items.get(position).getId());
        startActivity(intent);
    }
}