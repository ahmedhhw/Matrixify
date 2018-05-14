package com.doublea.matrixify.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.doublea.matrixify.R;
import com.doublea.matrixify.Utils.ArrayList;
import com.doublea.matrixify.Utils.MyTextView;
import com.doublea.matrixify.Utils.Rational;

/**
 * Created by archlinux on 5/13/18.
 */

public class RowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList<Rational> row_items;

    public RowAdapter() {

        row_items=null;

    }
    public RowAdapter(@NonNull ArrayList<Rational> numbers ) {

        row_items=numbers;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof RowViewHolder){

            ((RowViewHolder)holder).text.setText(row_items.getElement(position).toString());

        }

    }

    @Override
    public int getItemCount() {
        return(row_items != null)?row_items.numElements():0;
    }
    private class RowViewHolder extends RecyclerView.ViewHolder {

        public MyTextView text;

        public RowViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.row_item_data);
        }
    }
}
