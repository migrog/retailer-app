package com.negomatic.retailer.ui.cart.;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.negomatic.retailer.ui.cart.R;

public class DetailItemCartFragment extends Fragment {

    private DetailItemCartViewModel mViewModel;

    public static DetailItemCartFragment newInstance() {
        return new DetailItemCartFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_item_cart_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetailItemCartViewModel.class);
        // TODO: Use the ViewModel
    }

}