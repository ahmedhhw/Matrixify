package com.doublea.matrixify;

import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doublea.matrixify.RecyclerView.MatrixAdapter;
import com.doublea.matrixify.Utils.MatrixOperations;

public class MatrixActivity extends AppCompatActivity {
    private MatrixOperations matrix;
    private Button addRow;
    private Button reduce;
    private EditText input;
    private TextView communicate;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        //Initiating matrix
        matrix = new MatrixOperations();
        matrix.initiateMatrix();

        //Initiating widgets
        addRow = findViewById(R.id.btnRow);
        reduce = findViewById(R.id.btnReduce);
        input = findViewById(R.id.txtIn);
      //  communicate = findViewById(R.id.txtCommWithUser);
       // output = findViewById(R.id.txtOutPut);
     //   Snackbar.make(getCurrentFocus(),"Row is invalid", BaseTransientBottomBar.LENGTH_LONG).show();

        //Doing the Commands
        addRow.setOnClickListener(e -> addNewRow());
     reduce.setOnClickListener(e -> reduce());
    }

    private void addNewRow() {
        String in = input.getText().toString();
        String arr[] = in.split(" ");
        if (matrix.checkForValidity(arr)){
            matrix.newMatrixRow(arr);
        }else{
            Snackbar.make(getCurrentFocus(),"Row is invalid", BaseTransientBottomBar.LENGTH_LONG).show();
            return;
        }
       // output.setText(matrix.print());
        input.setText("");
    }
    private void reduce() {
        matrix.reduce();
        input.setText("");
       // output.setText(matrix.print());

        RecyclerView recyclerView = findViewById(R.id.recycler_view_matrices);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MatrixAdapter(matrix.matrix));
    }
}
