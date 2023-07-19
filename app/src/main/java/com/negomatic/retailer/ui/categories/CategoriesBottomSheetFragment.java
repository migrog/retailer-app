package com.negomatic.retailer.ui.categories;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.Category;
import com.negomatic.retailer.ui.categories.newcategory.NewCategoryDialogFragment;
import com.negomatic.retailer.util.adapter.SelectableAdapter;
import com.negomatic.retailer.util.enumerado.ActionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.Action;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     CategoriesBottomSheetFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class CategoriesBottomSheetFragment extends BottomSheetDialogFragment implements CategoryAdapter.ClickListener, NewCategoryDialogFragment.NewCategoryDialogListener {

    ArrayList<Category> categories = new ArrayList<>();

    private CategoryAdapter adapter;
    private boolean actionMode = false;


    @Inject
    ViewModelProvider.Factory factory;
    private CategoryViewModel viewModel;

    // TODO: Customize parameter argument names
    //private static final String ARG_ITEM_COUNT = "item_count";
    @BindView(R.id.list) RecyclerView recyclerView;
    @BindView(R.id.btn_add)
    MaterialButton btn_add;
    @BindView(R.id.btn_edit)
    MaterialButton btn_edit;
    @BindView(R.id.btn_remove)
    MaterialButton btn_remove;

    @BindView(R.id.tv_void)
    TextView tv_void;

    // TODO: Customize parameters
    public static CategoriesBottomSheetFragment newInstance() {
        final CategoriesBottomSheetFragment fragment = new CategoriesBottomSheetFragment();
        final Bundle args = new Bundle();
        //args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_categories_bottom_sheet, container, false);
        View view = inflater.inflate(R.layout.fragment_categories_bottom_sheet, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        //final RecyclerView recyclerView = (RecyclerView) view;

        adapter = new CategoryAdapter(categories/*getArguments().getInt(ARG_ITEM_COUNT)*/, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(this, factory).get(CategoryViewModel.class);
        viewModel.init();
        viewModel.list().observe(getViewLifecycleOwner(),list -> updateUI(list, true));

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NewCategoryDialogFragment newCategoryDialogFragment = NewCategoryDialogFragment.newInstance("Nueva categoría", null);
                newCategoryDialogFragment.setTargetFragment(CategoriesBottomSheetFragment.this, 300);
                newCategoryDialogFragment.show(getFragmentManager(),"dialog");
                getDialog().dismiss();

            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category _category = categories.get(adapter.getSelectedItems().get(0));
                NewCategoryDialogFragment newCategoryDialogFragment = NewCategoryDialogFragment.newInstance("Editar categoría", _category);
                newCategoryDialogFragment.setTargetFragment(CategoriesBottomSheetFragment.this, 300);
                newCategoryDialogFragment.show(getFragmentManager(),"dialog");
                getDialog().dismiss();
            }
        });
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "menu_remove");
                List<Category> _categoriesBefore = new ArrayList<>();
                List<Category> _categoriesAfter = new ArrayList<>();
                _categoriesBefore.addAll(categories);
                _categoriesAfter.addAll(categories);

                for(int position : adapter.getSelectedItems()){
                    _categoriesAfter.remove(categories.get(position));
                }

                boolean success = viewModel.saveCategories(_categoriesAfter);
                if(success){
                    adapter.removeItems(adapter.getSelectedItems());
                    actionMode = false;

                    btn_add.setVisibility(View.VISIBLE);
                    btn_edit.setVisibility(View.GONE);
                    btn_remove.setVisibility(View.GONE);

                    for(int position : adapter.getSelectedItems()){
                        CategoriesDialogListener listener = (CategoriesDialogListener) getTargetFragment();
                        listener.setCategory(_categoriesBefore.get(position), ActionType.DELETE);
                    }
                    adapter.clearSelection();

                    Snackbar.make(view, "Guardado exitoso!", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
                else{
                    Snackbar.make(view, "Error al intentar guardar :(", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
                updateUI(categories, false);
            }
        });

    }

    @Override
    public void onClick(View v, int position) {
        if (actionMode != false) {
            toggleSelection(position);
        }else{
            //impl
            CategoriesDialogListener listener = (CategoriesDialogListener) getTargetFragment();
            listener.setCategory(this.categories.get(position), ActionType.SELECT);
            getDialog().dismiss();
        }
    }

    @Override
    public boolean onLongClick(View v, int position) {
        if (actionMode == false) {
            actionMode = true;
        }
        toggleSelection(position);

        return true;
    }

    private void updateUI(List<Category> list, boolean isObserve){
        if(list.size()==0)
            tv_void.setVisibility(View.VISIBLE);
        else
            tv_void.setVisibility(View.GONE);

        if(isObserve){
            this.categories.clear();
            this.categories.addAll(list);
        }
        adapter.notifyDataSetChanged();
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
            actionMode = false;
            btn_add.setVisibility(View.VISIBLE);
            btn_edit.setVisibility(View.GONE);
            btn_remove.setVisibility(View.GONE);
        }else if(count == 1){
            btn_add.setVisibility(View.GONE);
            btn_edit.setVisibility(View.VISIBLE);
            btn_remove.setVisibility(View.VISIBLE);
        }else {
            btn_add.setVisibility(View.GONE);
            btn_edit.setVisibility(View.GONE);
            btn_remove.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setCategory(Category value, ActionType actionType) {
        CategoriesDialogListener listener = (CategoriesDialogListener) getTargetFragment();
        listener.setCategory(value, actionType);
    }

    public interface CategoriesDialogListener{
        void setCategory(Category value, ActionType actionType);
    }

}
class CategoryAdapter extends SelectableAdapter<CategoryAdapter.ViewHolder> {
    private List<Category> items;
    private static ClickListener clickListener;

    CategoryAdapter(List<Category> items, ClickListener clickListener) {
        this.items = items;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category_bottom_sheet, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category item = items.get(position);
        holder.text.setText(item.getName());

        // Highlight the item if it's selected
        holder.selected_overlay.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void removeItem(int position){
        items.remove(position);
        notifyItemRemoved(position);
    }
    public void removeItems(List<Integer> positions){
        // Reverse-sort the list
        Collections.sort(positions, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return rhs - lhs;
            }
        });

        // Split the list in ranges
        while (!positions.isEmpty()){
            if(positions.size() == 1){
                removeItem(positions.get(0));
                positions.remove(0);
            }else {
                int count = 1;
                while (positions.size() > count && positions.get(count).equals(positions.get(count - 1) - 1)){
                    ++count;
                }

                if(count == 1){
                    removeItem(positions.get(0));
                }else {
                    removeRange(positions.get(count - 1), count);
                }

                for (int i = 0; i < count; ++i){
                    positions.remove(0);
                }
            }
        }
        //save changes
    }
    private void removeRange(int positionStart, int itemCount){
        for(int i = 0; i < itemCount; ++i){
            items.remove(positionStart);
        }
        notifyItemRangeRemoved(positionStart, itemCount);
    }
    public List<Category> getItems(){return items;}

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        final TextView text;
        View selected_overlay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            selected_overlay = itemView.findViewById(R.id.selected_overlay);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null){
                switch (view.getId()){
                    default:
                        clickListener.onClick(view, getLayoutPosition());
                        break;
                }
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (clickListener != null) {
                return clickListener.onLongClick(view, getLayoutPosition());
            }
            return false;
        }
    }
    interface ClickListener {
        void onClick(View v, int position);
        boolean onLongClick(View v, int position);
    }
}