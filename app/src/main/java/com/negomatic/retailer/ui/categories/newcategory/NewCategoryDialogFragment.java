package com.negomatic.retailer.ui.categories.newcategory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.Category;
import com.negomatic.retailer.ui.categories.CategoryViewModel;
import com.negomatic.retailer.util.enumerado.ActionType;

import java.time.LocalDateTime;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class NewCategoryDialogFragment extends DialogFragment {

    private Category category = new Category();

    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.et_category_name)
    TextInputEditText et_category_name;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private CategoryViewModel viewModel;

    public static NewCategoryDialogFragment newInstance(String title, Category category) {
        NewCategoryDialogFragment fragment = new NewCategoryDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("category", new Gson().toJson(category, new TypeToken<Category>(){}.getType()));
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_new, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AndroidSupportInjection.inject(this);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Nueva Categoría");

        category = new Gson().fromJson(getArguments().getString("category"), new TypeToken<Category>(){}.getType());


        getDialog().setTitle(title);
        et_category_name.setText(category == null ? "": category.getName());
        // Show soft keyboard automatically and request focus to field
        et_category_name.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        viewModel = new ViewModelProvider(this, viewModelFactory).get(CategoryViewModel.class);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //impl
                if(category == null){
                    category = new Category();
                    category.setName(et_category_name.getText().toString());
                    category.setCreatedAt(LocalDateTime.now());
                    category = viewModel.saveCategory(category);
                    NewCategoryDialogListener listener = (NewCategoryDialogListener)getTargetFragment();
                    listener.setCategory(category, ActionType.ADD);

                }
                else {
                    category.setName(et_category_name.getText().toString());
                    category.setUpdatedAt(LocalDateTime.now());
                    viewModel.updateCategory(category);
                    NewCategoryDialogListener listener = (NewCategoryDialogListener)getTargetFragment();
                    listener.setCategory(category, ActionType.EDIT);
                }

                getDialog().dismiss();
            }
        });
    }

    public interface NewCategoryDialogListener{
        void setCategory(Category value, ActionType actionType);
    }
}

