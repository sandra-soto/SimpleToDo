package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// responsible for displaying data from the model
// into a row in the recycler view
public class itemsAdapter extends RecyclerView.Adapter<itemsAdapter.ViewHolder>{

    public interface OnLongClickListener{
        // class implemnting this method (main activity) needs to know index 2 remove
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;

    // constructor
    public itemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    // creating each view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // use layout inflator to inflate a view
        // layout inflator tales xml file as input and build View objects from it
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inside a viewholder and return it
        return new ViewHolder(todoView);
    }

    // binding data to a particular viewholder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // grab the item at the positon
        String item = items.get(position);
        // bind the item into the specified viewholder
        holder.bind(item);


    }

    // tell RV number of items available in the data list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access to views
    // that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // from built-in android xml resource
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // update the view inside of the view holder with the argument data
        public void bind(String item) {
            // update text in textview
            tvItem.setText(item);
            // update the view inside the view holder with this data
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    // notifying listener which position was long presses
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
