package com.doublea.matrixify.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
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

public class MatrixAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ArrayList<Rational>> matrices;

    public MatrixAdapter() {

        matrices = null;

    }

    public MatrixAdapter(@NonNull ArrayList<ArrayList<Rational>> full_matrix) {

        matrices = full_matrix;
    }

    public void addRow(ArrayList<Rational> row) {

        matrices.addElement(row);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View row = layoutInflater.inflate(R.layout.item_row, parent, false);
        RecyclerView.ViewHolder viewHolder = new MatrixViewHolder(row);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        ((MatrixViewHolder)holder).row.setAdapter(new RowAdapter(matrices.getElement(position)));

    }

    @Override
    public int getItemCount() {
        return matrices.numElements() + 1;
    }

    public class MatrixViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView row;

        public MatrixViewHolder(View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.recycler_view_rows);
            row.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));

        }
    }
}
