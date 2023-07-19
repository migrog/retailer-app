package com.negomatic.retailer.ui.customers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.Person;
import com.negomatic.retailer.ui.customers.newcustomer.NewCustomerActivity;
import com.negomatic.retailer.ui.customers.viewcustomer.ViewCustomerActivity;
import com.negomatic.retailer.ui.inventory.InventoryAdapter;
import com.negomatic.retailer.ui.inventory.InventoryFragment;
import com.negomatic.retailer.ui.inventory.InventoryViewModel;
import com.negomatic.retailer.ui.inventory.viewitem.ViewItemActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

import static com.negomatic.retailer.App.context;

public class CustomersFragment extends Fragment implements CustomersAdapter.ClickListener {

    private CustomersAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ActionModeCallback actionModeCallback = new ActionModeCallback();
    private ActionMode actionMode;

    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;

    List<Person> customers = new ArrayList<>();

    @Inject
    ViewModelProvider.Factory factory;
    private CustomerViewModel viewModel;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_customers, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureDagger();
        configureViewModel();
        configureLayouts();

    }

    @Override
    public void onClick(View v, int position) {
        if (actionMode != null) {
            toggleSelection(position);
        }else{
            //impl
            Intent intent = new Intent(context, ViewCustomerActivity.class);
            intent.putExtra("customerId", customers.get(position).getId());
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

    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }
    private void configureViewModel(){
        viewModel = new ViewModelProvider(this,factory).get(CustomerViewModel.class);
        viewModel.init();
        viewModel.list().observe(getViewLifecycleOwner(),c->updateUI(c, true));
    }
    private void configureLayouts(){
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CustomersAdapter(this.customers,this);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.swapAdapter(adapter, true);
    }
    private void updateUI(List<Person> list, boolean isObserve){
        if(list != null){
            if(isObserve)
                this.customers.addAll(list);

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
                    updateUI(customers, false);
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
