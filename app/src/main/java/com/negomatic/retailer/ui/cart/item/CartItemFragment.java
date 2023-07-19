package com.negomatic.retailer.ui.cart.item;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.App;
import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.OrderNoteItem;
import com.negomatic.retailer.model.order_note.Item;
import com.negomatic.retailer.prefs.CartPrefs;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class CartItemFragment extends Fragment {

    //entities
    private com.negomatic.retailer.entity.Item item = null;

    //models
    private List<OrderNoteItem> adds;
    private OrderNoteItem i = new OrderNoteItem();

    //variables
    private double quantity = 1;
    private double totalPrice = 0.00;
    private double unitValue = 0.00;
    private double totalBaseIgv = 0.00;
    private double percentageIgv = 0.00;
    private double totalIgv = 0.00;
    private double totalBaseIsc = 0.00;
    private double totalIsc = 0.00;
    private double totalBaseOtherTaxes = 0.00;
    private double percentageOtherTaxes = 0.00;
    private double totalOtherTaxes = 0.00;
    private double totalTaxes = 0.00;
    private double unitPrice = 0.00;
    private double totalValue = 0.00;
    private double totalDiscount = 0.00;
    private double totalCharge = 0.00;
    private double total = 0.00;
    private boolean isItemAdd = false;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private CartItemViewModel mViewModel;

    @BindView(R.id.iv_image_item)
    ImageView iv_image_item;

    @BindView(R.id.tv_name_item)
    TextView tv_name_item;

    @BindView(R.id.tv_unit_price)
    TextView tv_unit_price;

    @BindView(R.id.tv_quantity)
    TextView tv_quantity;

    @BindView(R.id.btn_increase)
    Button btn_increase;

    @BindView(R.id.btn_decrease)
    Button btn_decrease;

    @BindView(R.id.btn_add_cart)
    Button btn_add_cart;

    /*
    public static DetailItemCartFragment newInstance() {
        return new DetailItemCartFragment();
    }
     */
    public CartItemFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_item, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureDagger();
        Bundle bundle = getActivity().getIntent().getExtras();
        int itemId = bundle.getInt("itemId");
        this.getItemAdd(itemId);

        mViewModel = new ViewModelProvider(this,viewModelFactory).get(CartItemViewModel.class);
        mViewModel.getItem(itemId).observe(getViewLifecycleOwner(),item->updateUI(item));

        this.btn_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increase();
            }
        });
        this.btn_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrease();
            }
        });

        this.btn_add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setEnabled(false);
                boolean success = save();
                if(success){
                    Snackbar.make(view, "Guardado exitoso!", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    //Toast.makeText(context,"Guardado exitoso!",Toast.LENGTH_SHORT).show();
                }

                view.setEnabled(true);
            }
        });
    }

    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }
    private void getItemAdd(int itemId){
        String json = CartPrefs.get(App.context).getString("PREF_CART_ITEMS", null);
        Type type = new TypeToken<List<OrderNoteItem>>() {}.getType();
        adds = new Gson().fromJson(json, type);
        if(adds != null){
            Optional<OrderNoteItem> optional = adds.stream().filter(m -> m.getItem_id() == itemId).findFirst();
            if (optional.isPresent()) {
                isItemAdd = true;
                i = optional.get();
                this.quantity = optional.get().getQuantity();
            }

        }else{
            adds = new ArrayList<>();
        }
    }
    private void updateUI(com.negomatic.retailer.entity.Item item){
        this.item = item;
        if(item != null){
            this.calculateTotal();
            tv_name_item.setText(item.getName());
            tv_unit_price.setText("S/" + " " + item.getSaleUnitPrice());
            tv_quantity.setText(""+this.quantity);
            btn_add_cart.setText((isItemAdd ? "Actualizar pedido":"Agregar al pedido") + " " + "S/" + this.totalPrice);

            if(this.quantity == 1)
                btn_decrease.setEnabled(false);
            else
                btn_decrease.setEnabled(true);
        }
    }
    private void increase(){
        this.quantity++;
        updateUI(item);
    }
    private void decrease(){
        double qtemp = this.quantity;
        qtemp--;
        if(qtemp <= 0)
            this.quantity = 1;
        else
            this.quantity--;
        updateUI(item);
    }
    private void calculateTotal(){
        this.totalPrice = this.quantity * this.item.getSaleUnitPrice();
    }
    private boolean save(){

        //CALCULATE
        unitPrice = item.getSaleUnitPrice();
        unitValue = unitPrice;
        percentageIgv = 18;//imp

        String affectationIgvTypeId = item.getSaleAffectationIgvTypeId();
        if(affectationIgvTypeId == "10"){
            unitValue = unitPrice/(1+percentageIgv/100);
        }

        double totalValuePartial = unitValue * quantity;

        double discountBase = 0.00;
        double discountNoBase = 0.00;
        double chargeBase = 0.00;
        double chargeNoBase = 0.00;

        totalDiscount = discountBase + discountNoBase;
        totalCharge = chargeBase + chargeNoBase;
        totalValue = totalValuePartial - totalDiscount + totalCharge;
        totalBaseIgv = totalValuePartial - discountBase  + totalIsc;

        if(affectationIgvTypeId == "10"){
            totalIgv = totalBaseIgv*percentageIgv/100;
        }
        if(affectationIgvTypeId == "20"){//Exonerated
            totalIgv = 0;
        }
        if(affectationIgvTypeId == "30"){//Unaffected
            totalIgv = 0;
        }

        totalTaxes = totalIgv + totalIsc + totalOtherTaxes;
        total = totalValue + totalTaxes;

        totalCharge =  Math.round(totalCharge*100.0)/100.0;
        totalDiscount = Math.round(totalDiscount*100.0)/100.0;
        totalValue = Math.round(totalValue*100.0)/100.0;
        totalBaseIgv = Math.round(totalBaseIgv*100.0)/100.0;
        totalIgv = Math.round(totalIgv*100.0)/100.0;
        totalTaxes = Math.round(totalTaxes*100.0)/100.0;
        total = Math.round(total*100.0)/100.0;

        Log.d("total", ""+total);


        //item
        Item _item = new Item();
        _item.setId(item.getId());
        _item.setDescription(item.getName());

        OrderNoteItem _i = new OrderNoteItem();
        _i.setItem_id(item.getId());
        _i.setItem(_item);
        _i.setQuantity(quantity);
        _i.setUnitValue(unitValue);
        _i.setAffectationIgvTypeId(item.getSaleAffectationIgvTypeId());
        _i.setTotalBaseIgv(totalBaseIgv);//imp
        _i.setPercentageIgv(percentageIgv);//imp
        _i.setTotalIgv(totalIgv);//imp
        _i.setSystemIscTypeId(item.getSystemIscTypeId());
        _i.setTotalBaseIsc(totalBaseIsc);
        _i.setPercentageIsc(item.getPercentageIsc());
        _i.setTotalIsc(totalIsc);
        _i.setTotalBaseOtherTaxes(totalBaseOtherTaxes);
        _i.setPercentageOtherTaxes(percentageOtherTaxes);
        _i.setTotalOtherTaxes(totalOtherTaxes);
        _i.setTotalTaxes(totalTaxes);//imp
        _i.setPriceTypeId("01");
        _i.setUnitPrice(unitPrice);
        _i.setTotalValue(totalValue);
        _i.setTotal(total);
        _i.setWarehouseId(item.getWarehouse_id());

        if(isItemAdd){
            adds.set(adds.indexOf(i),_i);
        }
        else{
            adds.add(_i);
        }
        i = _i;


        String items_cart = new Gson().toJson(adds);
        CartPrefs.get(App.context).saveItems(items_cart);
        this.getItemAdd(_i.getItem_id());

        return true;
    }

}