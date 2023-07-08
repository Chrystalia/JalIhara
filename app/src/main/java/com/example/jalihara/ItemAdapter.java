package com.example.jalihara;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private static ItemAdapter.buttonClickListener buttonClickListener;
    private List<Item> itemList;

    public ItemAdapter(List<Item> itemList, ItemAdapter.buttonClickListener buttonClickListener) {
        this.itemList = itemList;
        this.buttonClickListener = buttonClickListener;
    }

    public void setFilteredList(List<Item> filteredList){
        this.itemList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_tickets_view, parent, false);

        // Passing view to ViewHolder
        return new ViewHolder(view, ItemAdapter.buttonClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TypeCast Object to int type
        Item item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        List<Item> itemList;
        ItemAdapter.buttonClickListener buttonClickListener;
//        TextView itemIdx;
        ImageView itemImage;
        TextView itemDate;
        TextView itemTitle;
        TextView itemDescription;
        TextView itemPrice;
        Button order_btn;


        public ViewHolder(View view, ItemAdapter.buttonClickListener buttonClickListener) {
            super(view);
//            itemIdx = view.findViewById(R.id.text_view_idx);
            itemImage = view.findViewById(R.id.image_icon);
            itemTitle = view.findViewById(R.id.text_view_title);
            itemDate = view.findViewById(R.id.text_view_date);
            itemDescription = view.findViewById(R.id.text_view_desc);
            itemPrice = view.findViewById(R.id.text_view_price);
            order_btn = view.findViewById(R.id.order_btn);

            this.buttonClickListener = buttonClickListener;
            order_btn.setOnClickListener(this);
            // view.setOnClickListener(this);
        }

        public void bind(Item item) {
//            itemIdx.setText(item.getItemIdx());
            itemImage.setImageResource(item.getItemImage());
            itemTitle.setText(item.getItemTitle());
            itemDate.setText(item.getItemDate());
            itemDescription.setText(item.getItemDescription());
            itemPrice.setText(item.getItemPrice());

//            order_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    buttonClickListener.onButtonClick(getAdapterPosition());
//                }
//            });
        }

        @Override
        public void onClick(View v) {
            buttonClickListener.onButtonClick(getAdapterPosition());
        }
    }

    public interface buttonClickListener{
        void onButtonClick(int position);

        boolean onCreateOptionsMenu(Menu menu);
    }
}
