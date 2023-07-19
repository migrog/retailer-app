package com.negomatic.retailer.ui.customers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.Person;
import com.negomatic.retailer.ui.inventory.InventoryAdapter;
import com.negomatic.retailer.util.adapter.SelectableAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomersAdapter extends SelectableAdapter<CustomersAdapter.ViewHolder> {
    private List<Person> items;
    private static ClickListener clickListener;

    public CustomersAdapter(List<Person> items, ClickListener clickListener){
        this.items = items;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_customers, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person item = items.get(position);
        holder.name.setText(item.getName());
        holder.address.setText(item.getAddress());
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
    }
    private void removeRange(int positionStart, int itemCount) {
        for (int i = 0; i < itemCount; ++i) {
            items.remove(positionStart);
        }
        notifyItemRangeRemoved(positionStart, itemCount);
    }
    public List<Person> getItems(){
        return items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView name;
        public TextView address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.txt_name);
            address = (TextView)itemView.findViewById(R.id.txt_address);
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
