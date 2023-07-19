package com.negomatic.retailer.ui.customers.newcustomer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.IdentityDocumentType;
import com.negomatic.retailer.entity.Person;
import com.negomatic.retailer.util.enumerado.ActionType;
import com.negomatic.retailer.viewmodel.configuration.ConfigurationViewModel;
import com.negomatic.retailer.ui.customers.CustomerViewModel;
import com.negomatic.retailer.ui.documenttypes.DocumentTypesBottomSheetFragment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class NewCustomerFragment extends Fragment implements DocumentTypesBottomSheetFragment.DocumentTypesDialogListener {

    private Person customer = new Person();
    private IdentityDocumentType identityDocumentType = new IdentityDocumentType();

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private CustomerViewModel viewModel;
    private ConfigurationViewModel configurationViewModel;

    @BindView(R.id.til_document_type)
    TextInputLayout til_document_type;
    @BindView(R.id.tv_document_type)
    AutoCompleteTextView tv_document_type;

    @BindView(R.id.til_number)
    TextInputLayout til_number;
    @BindView(R.id.et_number)
    TextInputEditText et_number;

    @BindView(R.id.til_name)
    TextInputLayout til_name;
    @BindView(R.id.et_name)
    TextInputEditText et_name;

    @BindView(R.id.et_trade_name)
    TextInputEditText et_trade_name;
    @BindView(R.id.et_internal_code)
    TextInputEditText et_internal_code;
    @BindView(R.id.et_address)
    TextInputEditText et_address;
    @BindView(R.id.et_telephone)
    TextInputEditText et_telephone;

    @BindView(R.id.til_email)
    TextInputLayout til_email;
    @BindView(R.id.et_email)
    TextInputEditText et_email;

    @BindView(R.id.sv_item)
    ScrollView sv_item;

    @BindView(R.id.btn_save)
    Button btn_save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_customers_new_customer, container,false);
        ButterKnife.bind(this, view);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();

        viewModel = new ViewModelProvider(this, viewModelFactory).get(CustomerViewModel.class);

        Bundle bundle = getActivity().getIntent().getExtras();
        if(bundle != null){
            getActivity().setTitle("Editar cliente");
            int customerId = bundle.getInt("customerId");

            //document types
            configurationViewModel = new ViewModelProvider(this, viewModelFactory).get(ConfigurationViewModel.class);
            configurationViewModel.initIdentityDocumentTypes("PE");//impl

            //customer
            viewModel.getCustomer(customerId).observe(getViewLifecycleOwner(), i->updateUI(i));
        }
        tv_document_type.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                til_document_type.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tv_document_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDocumentTypes(view);
            }
        });
        til_document_type.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(identityDocumentType == null){
                    showDocumentTypes(view);
                }else{
                    if(identityDocumentType.getCode() == ""){
                        showDocumentTypes(view);
                        return;
                    }
                    identityDocumentType = null;
                    tv_document_type.setText("");
                    til_document_type.setEndIconDrawable(R.drawable.ic_baseline_arrow_drop_down_24);
                }
            }
        });
        et_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                til_number.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                til_name.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(view);
            }
        });
    }
    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }
    void save(View view){
        if(!isInputsValid()){
            return;
        }

        customer.setType("customers");
        customer.setIdentityDocumentTypeId(String.valueOf(identityDocumentType.getId()));
        customer.setNumber(et_number.getText().toString());
        customer.setName(et_name.getText().toString());
        customer.setEnabled(1);

        customer.setTradeName(et_trade_name.getText().toString());
        customer.setInternalCode(et_internal_code.getText().toString());
        customer.setAddress(et_address.getText().toString());
        customer.setTelephone(et_telephone.getText().toString());
        customer.setEmail(et_email.getText().toString());
        customer.setCreatedAt(LocalDateTime.now());

        customer.setCountryId("");//impl

        customer = viewModel.saveCustomer(customer);
        Snackbar.make(view, "Guardado exitoso!", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
        getActivity().finish();

    }
    boolean isInputsValid(){
        String number = et_number.getText().toString();
        String name = et_name.getText().toString();
        boolean b_document_type = isDocumentTypeValidValid();
        boolean b_number = isNumberValid(number);
        boolean b_name = isNameValid(name);

        if(b_number && b_name && b_document_type){
            return true;
        }
        return false;
    }
    boolean isNumberValid(String value){
        if(value.isEmpty()){
            til_number.setError("Requerido");
            sv_item.smoothScrollTo(til_number.getScrollX(), til_number.getScrollY());
            return false;
        }
        return true;
    }
    boolean isNameValid(String value){
        if(value.isEmpty()){
            til_name.setError("Requerido");
            sv_item.smoothScrollTo(til_number.getScrollX(), til_number.getScrollY());
            return false;
        }
        return true;
    }
    boolean isDocumentTypeValidValid(){

        boolean is_document_type_valid = false;
        if(this.identityDocumentType != null){
            if(this.identityDocumentType.getCode() != null)
                is_document_type_valid = true;
        }

        if(!is_document_type_valid){
            til_document_type.setError("Requerido");
            sv_item.smoothScrollTo(til_document_type.getScrollX(), til_document_type.getScrollY());
        }

        return is_document_type_valid;
    }
    public void showDocumentTypes(View view){
        DocumentTypesBottomSheetFragment b = DocumentTypesBottomSheetFragment.newInstance();
        b.setStyle(DialogFragment.STYLE_NORMAL,R.style.BottomSheetDialogTheme);
        b.setTargetFragment(NewCustomerFragment.this, 300);
        b.show(getFragmentManager(), "dialog");
    }

    @Override
    public void setDocumentType(IdentityDocumentType value, ActionType actionType) {
        if(actionType == ActionType.SELECT){
            updateDocumentType(value);
            this.identityDocumentType = value;
        }
    }

    public void updateDocumentType(IdentityDocumentType value){
        tv_document_type.setText(value.getDescription());
        til_document_type.setEndIconDrawable(R.drawable.ic_baseline_close_24);

        if(value.getCode().equals("0")) {
            et_number.setText("99999999");
            et_name.setText("Clientes - Varios");
            return;
        }

        if(this.identityDocumentType.getCode() == null){
            et_number.requestFocus();
            return;
        }

        if(!this.identityDocumentType.getCode().equals(value.getCode())){
            et_number.setText("");
            et_number.requestFocus();
        }

        if(this.identityDocumentType.getCode().equals("0")){
            et_name.setText("");
        }
    }
    private void updateUI(Person customer){
        this.customer = customer;
        if(customer != null){
            //document type
            configurationViewModel.listIdentityDocumentTypes().observe(getViewLifecycleOwner(), _documentTypes->{
                if(_documentTypes != null){
                    IdentityDocumentType _documentType = _documentTypes.stream().filter(x->String.valueOf(x.getId()).equals(customer.getIdentityDocumentTypeId())).findAny().orElse(null);
                    if(_documentType != null) {
                        tv_document_type.setText(_documentType.getDescription());
                        til_document_type.setEndIconDrawable(R.drawable.ic_baseline_close_24);
                        this.identityDocumentType = _documentType;
                    }
                }
            });

            et_number.setText(customer.getNumber());
            et_name.setText(customer.getName());
            et_trade_name.setText(customer.getTradeName());
            et_internal_code.setText(customer.getInternalCode());
            et_address.setText(customer.getAddress());
            et_telephone.setText(customer.getTelephone());
            et_email.setText(customer.getEmail());
        }
    }
}
