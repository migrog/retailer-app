package com.negomatic.retailer.ui.unittypes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.UnitType;
import com.negomatic.retailer.util.adapter.SelectableAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UnitTypeAdapter extends SelectableAdapter<UnitTypeAdapter.ViewHolder> {
    private List<UnitType> items;
    private static ClickListener clickListener;

    UnitTypeAdapter(List<UnitType> items, ClickListener clickListener){
        this.items = items;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_unittype_bottom_sheet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UnitType item = items.get(position);
        holder.text.setText(item.getDescription());

        // Highlight the item if it's selected
        holder.selected_overlay.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void removeItem(int position){
        items.remove(position);
        notifyItemRemoved(position);
    }
    public void removeItems(List<Integer> positions){
        // Reverse-sort the list
        Collections.sort(positions, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return rhs - lhs;
            }
        });

        // Split the list in ranges
        while (!positions.isEmpty()){
            if(positions.size() == 1){
                removeItem(positions.get(0));
                positions.remove(0);
            }else {
                int count = 1;
                while (positions.size() > count && positions.get(count).equals(positions.get(count - 1) - 1)){
                    ++count;
                }

                if(count == 1){
                    removeItem(positions.get(0));
                }else {
                    removeRange(positions.get(count - 1), count);
                }

                for (int i = 0; i < count; ++i){
                    positions.remove(0);
                }
            }
        }
        //save changes
    }
    private void removeRange(int positionStart, int itemCount){
        for(int i = 0; i < itemCount; ++i){
            items.remove(positionStart);
        }
        notifyItemRangeRemoved(positionStart, itemCount);
    }
    public List<UnitType> getItems(){return items;}

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        final TextView text;
        View selected_overlay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            selected_overlay = itemView.findViewById(R.id.selected_overlay);

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
            if(clickListener != null){
                return clickListener.onLongClick(view, getLayoutPosition());
            }
            return false;
        }
    }
    interface ClickListener{
        void onClick(View view, int position);
        boolean onLongClick(View view, int position);
    }
}
