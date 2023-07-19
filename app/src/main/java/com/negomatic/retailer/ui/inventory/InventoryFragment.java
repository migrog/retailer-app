package com.negomatic.retailer.ui.inventory;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.ui.inventory.newitem.NewItemActivity;
import com.negomatic.retailer.ui.inventory.viewitem.ViewItemActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

import static com.negomatic.retailer.App.context;

public class InventoryFragment extends Fragment implements InventoryAdapter.ClickListener {

    private InventoryAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ActionModeCallback actionModeCallback = new ActionModeCallback();
    private ActionMode actionMode;

    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;

    List<Item> items = new ArrayList<>();

    @Inject
    ViewModelProvider.Factory factory;
    private InventoryViewModel mViewModel;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progressBarItems)
    ProgressBar progressBarItems;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
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

    }

    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }
    private void configureViewModel(){
        mViewModel = new ViewModelProvider(this,factory).get(InventoryViewModel.class);
        mViewModel.init();
        mViewModel.list().observe(getViewLifecycleOwner(),items->updateUI(items, true));
    }
    private void configureLayouts(){
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InventoryAdapter(this.items,this);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.swapAdapter(adapter, true);
    }
    private void updateUI(List<Item> list, boolean isObserve){
        //this.progressBarItems.setVisibility(View.VISIBLE);
        if(list != null){
            if(isObserve)
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

    @Override
    public void onClick(View v, int position) {
        if (actionMode != null) {
            toggleSelection(position);
        }else{
            //impl
            Intent intent = new Intent(context, ViewItemActivity.class);
            intent.putExtra("itemId", items.get(position).getId());
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
        private final String TAG = InventoryFragment.ActionModeCallback.class.getSimpleName();

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