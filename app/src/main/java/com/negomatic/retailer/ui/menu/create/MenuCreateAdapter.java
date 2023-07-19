package com.negomatic.retailer.ui.menu.create;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.negomatic.retailer.App;
import com.negomatic.retailer.R;
import com.negomatic.retailer.model.Menu;

import java.util.List;

public class MenuCreateAdapter extends RecyclerView.Adapter<MenuCreateAdapter.ViewHolder> {
    private List<Menu> items;
    private static ClickListener clickListener;

    MenuCreateAdapter(List<Menu> items, ClickListener clickListener){
        this.items = items;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_menu_create_bottom_sheet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuCreateAdapter.ViewHolder holder, int position) {
        Menu item = items.get(position);
        holder.title.setText(item.getDescription());
        int iconId = App.context.getResources().getIdentifier(item.getIcon(), "drawable", App.context.getPackageName());
        holder.icon.setImageResource(iconId);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.menu_title);
            icon = (ImageView) itemView.findViewById(R.id.menu_icon);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                default:
                    clickListener.onClick(view, getLayoutPosition());
                    break;
            }
        }
    }
    interface ClickListener {
        void onClick(View v, int position);
    }

}
