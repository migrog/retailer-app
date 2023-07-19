package com.negomatic.retailer.ui.cart;


import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.OrderNote;
import com.negomatic.retailer.entity.OrderNoteItem;
import com.negomatic.retailer.model.order_note.Customer;
import com.negomatic.retailer.model.order_note.Establishment;
import com.negomatic.retailer.ui.cart.item.CartItemActivity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

import static com.negomatic.retailer.App.context;

public class CartFragment extends Fragment implements CartAdapter.ViewHolder.ClickListener{

    private CartAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ActionModeCallback actionModeCallback = new ActionModeCallback();
    private ActionMode actionMode;

    List<OrderNoteItem> items = new ArrayList<>();
    OrderNote order = new OrderNote();
    Establishment establishment = new Establishment();
    Customer customer = new Customer();

    double total = 0.00;
    double totalCharge = 0.00;
    double totalDiscount = 0.00;
    double totalIgv = 0.00;
    double totalBaseIsc = 0.00;
    double totalIsc = 0.00;
    double totalBaseOtherTaxes = 0.00;
    double totalOtherTaxes = 0.00;
    double totalTaxes = 0.00;
    double totalValue = 0.00;
    @Inject
    ViewModelProvider.Factory factory;
    private CartViewModel mViewModel;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progressBarItems)
    ProgressBar progressBar;
    @BindView((R.id.tv_subtotal))
    TextView tv_subtotal;
    @BindView(R.id.btn_save_order)
    MaterialButton btn_save_order;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = new ViewModelProvider(this).get(ListItemCartViewModel.class);
        // TODO: Use the ViewModel
        configureDagger();
        configureLayouts();
        configureViewModel();
        this.btn_save_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(view);
            }
        });

    }
    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }
    private void configureLayouts(){
        //recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CartAdapter(this.items,this);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.swapAdapter(adapter, true);
    }
    private void configureViewModel(){
        mViewModel = new ViewModelProvider(this,factory).get(CartViewModel.class);
        mViewModel.init();
        mViewModel.list().observe(getViewLifecycleOwner(),items->updateUI(items, true));
    }
    private void updateUI(List<OrderNoteItem> list, boolean isObserve){
        if(list!=null){
            if(isObserve)
                this.items.addAll(list);
            adapter.notifyDataSetChanged();

            calculateSumary();
            tv_subtotal.setText("S/"+total);
        }
    }
    private void calculateSumary(){
        totalCharge = 0.00;
        totalDiscount = 0.00;
        totalIgv = 0.00;
        totalBaseIsc = 0.00;
        totalIsc = 0.00;
        totalBaseOtherTaxes = 0.00;
        totalOtherTaxes = 0.00;
        totalTaxes = 0.00;
        totalValue = 0.00;
        total = 0.00;
        for(OrderNoteItem i: items){

            totalCharge = totalCharge + i.getTotalCharge();
            totalDiscount = totalDiscount + i.getTotalDiscount();
            totalIgv = totalIgv + i.getTotalIgv();
            totalBaseIsc = totalBaseIsc + i.getTotalBaseIsc();
            totalIsc = totalIsc + i.getTotalIsc();
            totalBaseOtherTaxes = totalBaseOtherTaxes + i.getTotalBaseOtherTaxes();
            totalOtherTaxes = totalOtherTaxes + i.getTotalOtherTaxes();
            totalTaxes = totalTaxes + i.getTotalTaxes();
            totalValue = totalValue + i.getTotalValue();
            total = total + i.getTotal();
        }
    }
    private void save(View view){
        //order
        order.setExchangeRateSale(0.0);//impl
        order.setTotalPrepayment(0.0);
        order.setTotalCharge(totalCharge);
        order.setTotalDiscount(totalDiscount);
        order.setTotalExportation(0.0);
        order.setTotalFree(0.0);
        order.setTotalTaxed(0.00);//impl
        order.setTotalUnaffected(0.00);
        order.setTotalExonerated(0.00);
        order.setTotalIgv(totalIgv);
        order.setTotalBaseIsc(totalBaseIsc);
        order.setTotalIsc(totalIsc);
        order.setTotalBaseOtherTaxes(totalBaseOtherTaxes);
        order.setTotalOtherTaxes(totalOtherTaxes);
        order.setTotalValue(totalValue);
        order.setTotal(total);

        order.setExternalId(UUID.randomUUID().toString());
        order.setEstablishmentId(0);//impl
        order.setEstablishment(establishment);//impl
        order.setCustomerId(0);//impl
        order.setCustomer(customer);//impl
        order.setSoapTypeId("01");
        order.setStateTypeId("01");
        order.setPrefix("PD");
        order.setDateOfIssue(LocalDate.now());
        order.setTimeOfIssue(LocalTime.now());

        order.setCurrencyTypeId("PEN");//impl
        order.setCreatedAt(LocalDateTime.now());
        boolean success = mViewModel.saveOrderAndItems(order, items);
        if(success){
            Snackbar.make(view, "Guardado exitoso!", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }
        else{
            Snackbar.make(view, "Error al intentar guardar :(", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }
    }

    @Override
    public void onClick(View v, int position) {
        if (actionMode != null) {
            toggleSelection(position);
        }else{
            Intent intent = new Intent(context, CartItemActivity.class);
            intent.putExtra("itemId", items.get(position).getItem_id());
            startActivity(intent);
        }
    }

    @Override
    public boolean onLongClick(View v, int position) {
        if (actionMode == null) {
            actionMode = getActivity().startActionMode(actionModeCallback);
        }

        toggleSelection(position);

        return true;
    }
    /**
     * Toggle the selection state of an item.
     *
     * If the item was the last one in the selection and is unselected, the
     * selection is stopped.
     * Note that the selection must already be started (actionMode must not be
     * null).
     *
     * @param position Position of the item to toggle the selection state
     */
    private void toggleSelection(int position) {
        adapter.toggleSelection(position);
        int count = adapter.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }
    private class ActionModeCallback implements ActionMode.Callback {
        @SuppressWarnings("unused")
        private final String TAG = ActionModeCallback.class.getSimpleName();

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate (R.menu.selected_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_remove:
                    // TODO: actually remove items
                    Log.d(TAG, "menu_remove");
                    adapter.removeItems(adapter.getSelectedItems());
                    mode.finish();
                    updateUI(items, false);
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            adapter.clearSelection();
            actionMode = null;
        }
    }
}