package com.negomatic.retailer.ui.orders.orderdetail;

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
import android.widget.TextView;

import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.OrderNote;
import com.negomatic.retailer.entity.OrderNoteItem;
import com.negomatic.retailer.ui.orders.OrderNoteViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class OrderDetailFragment extends Fragment implements OrderDetailAdapter.ViewHolder.ClickListener{

    private OrderDetailAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    List<OrderNoteItem> items = new ArrayList<>();

    double total = 0.00;

    @Inject
    ViewModelProvider.Factory factory;
    private OrderNoteViewModel mViewModel;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView((R.id.tv_subtotal))
    TextView tv_subtotal;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        configureDagger();
        configureLayouts();
        configureViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onClick(View v, int position) {

    }

    @Override
    public boolean onLongClick(View v, int position) {
        return false;
    }
    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }
    private void configureLayouts(){
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderDetailAdapter(this.items,this);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.swapAdapter(adapter, true);
    }
    private void configureViewModel(){
        Bundle bundle = getActivity().getIntent().getExtras();
        int orderId = bundle.getInt("orderNoteId");

        mViewModel = new ViewModelProvider(this,factory).get(OrderNoteViewModel.class);
        mViewModel.getById(orderId).observe(getViewLifecycleOwner(),res->updateUI(res));
    }
    private void updateUI(OrderNote result){
        if(result!=null){
            mViewModel.getOrderNoteItems(result.getId()).observe(getViewLifecycleOwner(), _orderNoteItems ->{
                if(_orderNoteItems != null){
                    this.items.addAll(_orderNoteItems);
                    adapter.notifyDataSetChanged();
                    calculateSumary();
                    tv_subtotal.setText("S/"+total);
                }
            });
        }
    }
    private void calculateSumary(){
        total = 0.00;
        for(OrderNoteItem i: items){
            total = total + i.getTotal();
        }
    }
}