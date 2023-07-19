package com.negomatic.retailer.ui.inventory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.negomatic.retailer.App;
import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.OrderNoteItem;
import com.negomatic.retailer.prefs.CartPrefs;
import com.negomatic.retailer.util.adapter.SelectableAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InventoryAdapter extends SelectableAdapter<InventoryAdapter.ViewHolder> {
    private List<Item> items;
    private static ClickListener clickListener;

    public InventoryAdapter(List<Item> items, ClickListener clickListener){
        this.items = items;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_inventory_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);

        holder.name.setText(item.getName());
        holder.sale_unit_price.setText("S/" + " " + item.getSaleUnitPrice());
        holder.stock.setText("Stock:" + " " + item.getStock());
        holder.image.setImageResource(R.drawable.ic_egg_green);
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
        //String items_cart = new Gson().toJson(items);
        //CartPrefs.get(App.context).saveItems(items_cart);
    }
    private void removeRange(int positionStart, int itemCount) {
        for (int i = 0; i < itemCount; ++i) {
            items.remove(positionStart);
        }
        notifyItemRangeRemoved(positionStart, itemCount);
    }
    public List<Item> getItems(){
        return items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView name;
        public TextView sale_unit_price;
        public TextView stock;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.txt_name);
            sale_unit_price = (TextView)itemView.findViewById(R.id.txt_sale_unit_price);
            stock = (TextView)itemView.findViewById(R.id.txt_stock);
            image=(ImageView) itemView.findViewById(R.id.img_image_item);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null){
                switch (view.getId()){
                    default:
                        clickListener.onClick(view, getLayoutPosition());
                        break;
                }
            }

        }

        @Override
        public boolean onLongClick(View view) {
            if (clickListener != null) {
                return clickListener.onLongClick(view, getLayoutPosition());
            }
            return false;
        }

    }

    public interface ClickListener {
        void onClick(View v, int position);
        boolean onLongClick(View v, int position);
    }

}
