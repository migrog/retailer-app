package com.negomatic.retailer.ui.unittypes;

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
import com.negomatic.retailer.entity.UnitType;
import com.negomatic.retailer.ui.categories.CategoriesBottomSheetFragment;
import com.negomatic.retailer.util.enumerado.ActionType;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class UnitTypesBottomSheetFragment extends BottomSheetDialogFragment implements UnitTypeAdapter.ClickListener, NewUnitTypeDialogFragment.NewUnitTypeDialogListener {
    ArrayList<UnitType> unitTypes = new ArrayList<>();

    private UnitTypeAdapter adapter;
    private boolean actionMode = false;

    @Inject
    ViewModelProvider.Factory factory;
    private UnitTypeViewModel viewModel;

    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.btn_add)
    MaterialButton btn_add;
    @BindView(R.id.btn_edit)
    MaterialButton btn_edit;
    @BindView(R.id.btn_remove)
    MaterialButton btn_remove;

    @BindView(R.id.tv_void)
    TextView tv_void;

    public static UnitTypesBottomSheetFragment newInstance(){
        final UnitTypesBottomSheetFragment fragment = new UnitTypesBottomSheetFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_unittypes_bottom_sheet, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        AndroidSupportInjection.inject(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UnitTypeAdapter(unitTypes,this);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this, factory).get(UnitTypeViewModel.class);
        viewModel.init();
        viewModel.list().observe(getViewLifecycleOwner(), list->updateUI(list, true));

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewUnitTypeDialogFragment newUnitTypeDialogFragment = NewUnitTypeDialogFragment.newInstance("Nueva unidad", null);
                newUnitTypeDialogFragment.setTargetFragment(UnitTypesBottomSheetFragment.this, 300);
                newUnitTypeDialogFragment.show(getFragmentManager(),"dialog");
                getDialog().dismiss();
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UnitType _unitType = unitTypes.get(adapter.getSelectedItems().get(0));
                NewUnitTypeDialogFragment newUnitTypeDialogFragment = NewUnitTypeDialogFragment.newInstance("Editar unidad", _unitType);
                newUnitTypeDialogFragment.setTargetFragment(UnitTypesBottomSheetFragment.this, 300);
                newUnitTypeDialogFragment.show(getFragmentManager(), "dialog");
                getDialog().dismiss();
            }
        });
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "menu_remove");
                List<UnitType> _unitTypesBefore = new ArrayList<>();
                List<UnitType> _unitTypesAfter = new ArrayList<>();
                _unitTypesBefore.addAll(unitTypes);
                _unitTypesAfter.addAll(unitTypes);

                for(int position: adapter.getSelectedItems()){
                    _unitTypesAfter.remove(unitTypes.get(position));
                }

                boolean success = viewModel.saveUnitTypes(_unitTypesAfter);
                if(success){
                    adapter.removeItems(adapter.getSelectedItems());
                    actionMode = false;

                    btn_add.setVisibility(View.VISIBLE);
                    btn_edit.setVisibility(View.GONE);
                    btn_remove.setVisibility(View.GONE);

                    for(int position : adapter.getSelectedItems()){
                        UnitTypesDialogListener listener = (UnitTypesDialogListener) getTargetFragment();
                        listener.setUnitType(_unitTypesBefore.get(position), ActionType.DELETE);
                    }
                    adapter.clearSelection();

                    Snackbar.make(view, "Guardado exitoso!", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
                else{
                    Snackbar.make(view, "Error al intentar guardar :(", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
                updateUI(unitTypes, false);
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        if(actionMode != false){
            toggleSelection(position);
        }else{
            UnitTypesDialogListener listener = (UnitTypesDialogListener) getTargetFragment();
            listener.setUnitType(this.unitTypes.get(position), ActionType.SELECT);
            getDialog().dismiss();
        }
    }

    @Override
    public boolean onLongClick(View view, int position) {
        if (actionMode == false) {
            actionMode = true;
        }
        toggleSelection(position);

        return true;
    }
    private void updateUI(List<UnitType> list, boolean isObserve){
        if(list.size()==0)
            tv_void.setVisibility(View.VISIBLE);
        else
            tv_void.setVisibility(View.GONE);

        if(isObserve){
            this.unitTypes.clear();
            this.unitTypes.addAll(list);
        }
        adapter.notifyDataSetChanged();
    }

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
    public void setUnitType(UnitType value, ActionType actionType) {
        UnitTypesDialogListener listener = (UnitTypesDialogListener) getTargetFragment();
        listener.setUnitType(value, actionType);
    }

    public interface UnitTypesDialogListener{
        void setUnitType(UnitType value, ActionType actionType);
    }
}
