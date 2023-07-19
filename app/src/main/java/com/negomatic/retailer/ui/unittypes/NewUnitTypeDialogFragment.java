package com.negomatic.retailer.ui.unittypes;

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
import com.negomatic.retailer.entity.UnitType;
import com.negomatic.retailer.util.enumerado.ActionType;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class NewUnitTypeDialogFragment extends DialogFragment {
    private UnitType unitType = new UnitType();

    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.et_description)
    TextInputEditText et_description;
    @BindView(R.id.et_symbol)
    TextInputEditText et_symbol;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private UnitTypeViewModel viewModel;

    public static NewUnitTypeDialogFragment newInstance(String title, UnitType unitType){
        NewUnitTypeDialogFragment fragment = new NewUnitTypeDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("unittype", new Gson().toJson(unitType, new TypeToken<UnitType>(){}.getType()));
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unittype_new, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AndroidSupportInjection.inject(this);
        String title = getArguments().getString("title", "Nueva Unidad");
        unitType = new Gson().fromJson(getArguments().getString("unittype"), new TypeToken<UnitType>(){}.getType());

        getDialog().setTitle(title);
        et_description.setText(unitType == null ? "" : unitType.getDescription());
        et_description.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        viewModel = new ViewModelProvider(this, viewModelFactory).get(UnitTypeViewModel.class);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(unitType == null){
                    unitType = new UnitType();
                    unitType.setDescription(et_description.getText().toString());
                    unitType.setSymbol(et_symbol.getText().toString());
                    unitType.setActive(1);
                    unitType = viewModel.saveUnitType(unitType);
                    NewUnitTypeDialogListener listener = (NewUnitTypeDialogListener)getTargetFragment();
                    listener.setUnitType(unitType, ActionType.ADD);
                }else {
                    unitType.setDescription(et_description.getText().toString());
                    unitType.setSymbol(et_symbol.getText().toString());
                    viewModel.updateUnitType(unitType);
                    NewUnitTypeDialogListener listener = (NewUnitTypeDialogListener)getTargetFragment();
                    listener.setUnitType(unitType, ActionType.EDIT);
                }
                getDialog().dismiss();
            }
        });
    }

    public interface NewUnitTypeDialogListener{
        void setUnitType(UnitType value, ActionType actionType);
    }
}
