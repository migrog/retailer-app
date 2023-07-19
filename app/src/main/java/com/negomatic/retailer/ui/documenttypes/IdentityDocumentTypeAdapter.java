package com.negomatic.retailer.ui.documenttypes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.IdentityDocumentType;
import com.negomatic.retailer.util.adapter.SelectableAdapter;

import java.util.List;

public class IdentityDocumentTypeAdapter extends SelectableAdapter<IdentityDocumentTypeAdapter.ViewHolder> {
    private List<IdentityDocumentType> items;
    private static ClickListener clickListener;

    IdentityDocumentTypeAdapter(List<IdentityDocumentType> items, ClickListener clickListener){
        this.items = items;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_documenttype_bottom_sheet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IdentityDocumentType item = items.get(position);
        holder.text.setText(item.getDescription());

        // Highlight the item if it's selected
        holder.selected_overlay.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
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
                        clickListener.onClick(view,getLayoutPosition());
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
