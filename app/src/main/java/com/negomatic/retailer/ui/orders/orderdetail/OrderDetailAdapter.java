package com.negomatic.retailer.ui.orders.orderdetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.OrderNoteItem;
import com.negomatic.retailer.util.adapter.SelectableAdapter;

import java.util.List;

public class OrderDetailAdapter extends SelectableAdapter<OrderDetailAdapter.ViewHolder> {
    private List<OrderNoteItem> items;
    private ViewHolder.ClickListener clickListener;

    public OrderDetailAdapter(List<OrderNoteItem> items, ViewHolder.ClickListener clickListener){
        this.items = items;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cart_item_add,parent,false);
        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderNoteItem item = items.get(position);
        holder.quantity_add.setText(item.getQuantity()+"x");
        holder.item_name_add.setText(item.getItem().getDescription());
        holder.subtotal_add.setText("S/"+item.getTotal());

        // Highlight the item if it's selected
        holder.selected_overlay.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView quantity_add;
        TextView item_name_add;
        TextView subtotal_add;
        View selected_overlay;

        private ViewHolder.ClickListener listener;

        public ViewHolder(@NonNull View itemView, ViewHolder.ClickListener listener) {
            super(itemView);
            quantity_add = (TextView)itemView.findViewById(R.id.tv_quantity_add);
            item_name_add = (TextView)itemView.findViewById(R.id.tv_item_name_add);
            subtotal_add = (TextView)itemView.findViewById(R.id.tv_subtotal_add);
            selected_overlay = itemView.findViewById(R.id.selected_overlay);

            this.listener = listener;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(listener != null){
                switch (view.getId()){
                    default:
                        listener.onClick(view, getLayoutPosition());
                        break;
                }
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (listener != null) {
                return listener.onLongClick(view, getLayoutPosition());
            }
            return false;
        }

        public interface ClickListener {
            void onClick(View v, int position);
            boolean onLongClick(View v, int position);
        }
    }
}
