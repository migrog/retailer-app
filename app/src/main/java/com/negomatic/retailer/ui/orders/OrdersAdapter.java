package com.negomatic.retailer.ui.orders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.OrderNote;
import com.negomatic.retailer.entity.OrderNoteItem;
import com.negomatic.retailer.entity.StateType;
import com.negomatic.retailer.model.order_note.Customer;

import java.util.List;
import java.util.Optional;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    private List<OrderNote> items;
    private List<StateType> stateTypes;
    private static OnItemListener itemListener;

    public OrdersAdapter(List<OrderNote> orders, List<StateType> stateTypes, OnItemListener itemListener){
        this.items = orders;
        this.stateTypes = stateTypes;
        this.itemListener = itemListener;
    }

    public interface OnItemListener {
        void onItemClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderNote order = items.get(position);
        Customer customer = order.getCustomer();
        StateType stateType = this.stateTypes.stream().filter(m -> m.getId().equals(order.getStateTypeId())).findAny().orElse(null);

        holder.txt_order_name.setText("N° pedido: "+order.getId());
        holder.txt_total.setText("S/"+order.getTotal());
        if(customer.getName()==""){
            holder.txt_customer.setText(customer.getName());
        }
        else
            holder.txt_customer.setVisibility(View.GONE);

        holder.txt_order_date.setText("Creado: "+order.getCreatedAt());
        holder.txt_order_status.setText(stateType.getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txt_order_name;
        public TextView txt_total;
        public TextView txt_customer;
        public TextView txt_order_date;
        public TextView txt_order_status;

        public ViewHolder(@NonNull View view) {
            super(view);
            txt_order_name = (TextView)view.findViewById(R.id.txt_order_name);
            txt_total = (TextView)view.findViewById(R.id.txt_total);
            txt_customer = (TextView)view.findViewById(R.id.txt_customer);
            txt_order_date = (TextView)view.findViewById(R.id.txt_order_date);
            txt_order_status = (TextView)view.findViewById(R.id.txt_order_status);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                default:
                    itemListener.onItemClick(view, getLayoutPosition());
                    break;
            }
        }
    }
}
