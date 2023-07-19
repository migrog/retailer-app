package com.negomatic.retailer.ui.customers.viewcustomer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.Category;
import com.negomatic.retailer.entity.IdentityDocumentType;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.Person;
import com.negomatic.retailer.entity.UnitType;
import com.negomatic.retailer.ui.customers.CustomerViewModel;
import com.negomatic.retailer.ui.inventory.InventoryViewModel;
import com.negomatic.retailer.viewmodel.configuration.ConfigurationViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class ViewCustomerFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private CustomerViewModel mViewModel;
    private ConfigurationViewModel configurationViewModel;

    @BindView(R.id.til_document_type)
    TextInputLayout til_document_type;
    @BindView(R.id.et_document_type)
    TextInputEditText et_document_type;

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

    public ViewCustomerFragment(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customers_view_customer, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        Bundle bundle = getActivity().getIntent().getExtras();
        int id = bundle.getInt("customerId");

        //document types
        configurationViewModel = new ViewModelProvider(this, viewModelFactory).get(ConfigurationViewModel.class);
        configurationViewModel.initIdentityDocumentTypes("PE");//impl

        //customer
        mViewModel = new ViewModelProvider(this, viewModelFactory).get(CustomerViewModel.class);
        mViewModel.getCustomer(id).observe(getViewLifecycleOwner(), i->updateUI(i));
    }
    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }
    private void updateUI(Person customer){
        if(customer != null){
            et_number.setText(customer.getNumber());
            et_name.setText(customer.getName());
            et_trade_name.setText(customer.getTradeName());
            et_internal_code.setText(customer.getInternalCode());
            et_address.setText(customer.getAddress());
            et_telephone.setText(customer.getTelephone());
            et_email.setText(customer.getEmail());

            //document type
            configurationViewModel.listIdentityDocumentTypes().observe(getViewLifecycleOwner(), _documentTypes->{
                if(_documentTypes != null){
                    IdentityDocumentType _documentType = _documentTypes.stream().filter(x->String.valueOf(x.getId()).equals(customer.getIdentityDocumentTypeId())).findAny().orElse(null);
                    et_document_type.setText(_documentType.getDescription());
                }
            });

        }
    }
}