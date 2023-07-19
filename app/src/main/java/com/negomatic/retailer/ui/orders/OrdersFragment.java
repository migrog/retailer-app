package com.negomatic.retailer.ui.orders;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.OrderNote;
import com.negomatic.retailer.entity.StateType;
import com.negomatic.retailer.viewmodel.configuration.ConfigurationViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class OrdersFragment extends Fragment implements OrdersAdapter.OnItemListener {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;

    List<OrderNote> orders = new ArrayList<>();
    List<StateType> stateTypes = new ArrayList<>();

    @Inject
    ViewModelProvider.Factory factory;
    private OrdersViewModel mViewModel;
    private ConfigurationViewModel configurationViewModel;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progressBarOrders)
    ProgressBar progressBar;

    /*public static OrdersFragment newInstance() {
        return new OrdersFragment();
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.orders_fragment, container, false);
        View view = inflater.inflate(R.layout.fragment_orders, container,false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);
        // TODO: Use the ViewModel
        this.configureDagger();
        this.configureLayouts();
        this.configureViewModel();
    }
    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }
    private void configureLayouts(){
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrdersAdapter(this.orders, this.stateTypes, this);
        recyclerView.setAdapter(adapter);
    }

    private void configureViewModel(){
        configurationViewModel = new ViewModelProvider(this, factory).get(ConfigurationViewModel.class);
        mViewModel = new ViewModelProvider(this,factory).get(OrdersViewModel.class);

        configurationViewModel.initStateTypes();
        mViewModel.init();

        configurationViewModel.listStateTypes().observe(getViewLifecycleOwner(), stateTypes -> setDataConfiguration(stateTypes));
        mViewModel.list().observe(getViewLifecycleOwner(),items->updateUI(items));
    }

    private void setDataConfiguration(List<StateType> stateTypes){
        this.stateTypes.addAll(stateTypes);
    }
    private void updateUI(List<OrderNote> list){
        if(list != null){
            this.orders.addAll(list);
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
    }

    @Override
    public void onItemClick(View view, int position) {
        //impl
    }
}
