package com.negomatic.retailer.ui.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.negomatic.retailer.App;
import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.OrderNoteItem;
import com.negomatic.retailer.prefs.CartPrefs;
import com.negomatic.retailer.util.adapter.SelectableAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CartAdapter extends SelectableAdapter<CartAdapter.ViewHolder> {
    private List<OrderNoteItem> items;
    private ViewHolder.ClickListener clickListener;

    public CartAdapter(List<OrderNoteItem> items, ViewHolder.ClickListener clickListener){
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

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }
    public void removeItems(List<Integer> positions) {
        // Reverse-sort the list
        Collections.sort(positions, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return rhs - lhs;
            }
        });

        // Split the list in ranges
        while (!positions.isEmpty()) {
            if (positions.size() == 1) {
                removeItem(positions.get(0));
                positions.remove(0);
            } else {
                int count = 1;
                while (positions.size() > count && positions.get(count).equals(positions.get(count - 1) - 1)) {
                    ++count;
                }

                if (count == 1) {
                    removeItem(positions.get(0));
                } else {
                    removeRange(positions.get(count - 1), count);
                }

                for (int i = 0; i < count; ++i) {
                    positions.remove(0);
                }
            }
        }

        //save changes
        String items_cart = new Gson().toJson(items);
        CartPrefs.get(App.context).saveItems(items_cart);
    }
    private void removeRange(int positionStart, int itemCount) {
        for (int i = 0; i < itemCount; ++i) {
            items.remove(positionStart);
        }
        notifyItemRangeRemoved(positionStart, itemCount);
    }
    public List<OrderNoteItem> getItems(){
        return items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView quantity_add;
        TextView item_name_add;
        TextView subtotal_add;
        View selected_overlay;

        private ClickListener listener;

        public ViewHolder(@NonNull View itemView, ClickListener listener) {
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
