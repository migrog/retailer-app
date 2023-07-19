package com.negomatic.retailer.ui.menu;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.negomatic.retailer.App;
import com.negomatic.retailer.R;
import com.negomatic.retailer.model.Menu;
import com.negomatic.retailer.ui.orders.OrdersAdapter;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    @SuppressWarnings("unused")
    private static final String TAG = MenuAdapter.class.getSimpleName();
    private List<Menu> menus;
    private static MenuAdapter.OnItemListener itemListener;

    public MenuAdapter(List<Menu> menus, OnItemListener itemListener){
        this.menus = menus;
        this.itemListener = itemListener;
    }

    public interface OnItemListener {
        void onItemClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_menu, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Menu menu = menus.get(position);
        holder.title.setText(menu.getDescription());
        int iconId = App.context.getResources().getIdentifier(menu.getIcon(), "drawable", App.context.getPackageName());
        holder.icon.setImageResource(iconId);
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.menu_title);
            icon = (ImageView) itemView.findViewById(R.id.menu_icon);

            itemView.setOnClickListener(this);
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
