package com.negomatic.retailer.ui.catalog;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.Item;

import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {
    private List<Item> items;
    private static ClickListener clickListener;

    public CatalogAdapter(List<Item> items, ClickListener clickListener){
        this.items = items;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_catalog_item,parent,false);
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView sale_unit_price;
        public TextView stock;
        public ImageView image;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.txt_name);
            sale_unit_price = (TextView)view.findViewById(R.id.txt_sale_unit_price);
            stock = (TextView)view.findViewById(R.id.txt_stock);
            image=(ImageView) view.findViewById(R.id.img_image_item);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("view-item", String.valueOf(view. getId()));
            switch (view.getId()){
/*                case R.id.img_image_item:
                    break;*/
                default:
                    clickListener.onClick(view, getLayoutPosition());
                    break;
            }

        }
    }

    public interface ClickListener {
        void onClick(View v, int position);
    }
}
