package com.negomatic.retailer.ui.documenttypes;

import android.os.Bundle;
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
import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.IdentityDocumentType;
import com.negomatic.retailer.entity.UnitType;
import com.negomatic.retailer.util.enumerado.ActionType;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class DocumentTypesBottomSheetFragment extends BottomSheetDialogFragment implements IdentityDocumentTypeAdapter.ClickListener{
    ArrayList<IdentityDocumentType> identityDocumentTypes = new ArrayList<>();
    private IdentityDocumentTypeAdapter adapter;

    @Inject
    ViewModelProvider.Factory factory;
    private IdentityDocumentTypeViewModel viewModel;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.tv_void)
    TextView tv_void;

    public static DocumentTypesBottomSheetFragment newInstance(){
        final DocumentTypesBottomSheetFragment fragment = new DocumentTypesBottomSheetFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_documenttypes_bottom_sheet, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        adapter = new IdentityDocumentTypeAdapter(identityDocumentTypes, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this, factory).get(IdentityDocumentTypeViewModel.class);
        viewModel.init("PE");//impl
        viewModel.list().observe(getViewLifecycleOwner(), list->updateUI(list, true));
    }

    @Override
    public void onClick(View view, int position) {
        DocumentTypesDialogListener listener = (DocumentTypesDialogListener) getTargetFragment();
        listener.setDocumentType(this.identityDocumentTypes.get(position), ActionType.SELECT);
        getDialog().dismiss();
    }

    @Override
    public boolean onLongClick(View view, int position) {
        return false;
    }

    private void updateUI(List<IdentityDocumentType> list, boolean isObserve){
        if(list.size()==0)
            tv_void.setVisibility(View.VISIBLE);
        else
            tv_void.setVisibility(View.GONE);

        if(isObserve){
            this.identityDocumentTypes.clear();
            this.identityDocumentTypes.addAll(list);
        }
        adapter.notifyDataSetChanged();
    }

    public interface DocumentTypesDialogListener{
        void setDocumentType(IdentityDocumentType value, ActionType actionType);
    }
}
