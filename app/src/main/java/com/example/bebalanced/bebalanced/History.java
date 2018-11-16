package com.example.bebalanced.bebalanced;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        DatabaseHelper mydb= new DatabaseHelper(this);

        Cursor res= mydb.getUserLog();

        RecyclerView historylist=findViewById(R.id.history_date);
        Log.d("output_res", String.valueOf(res));

       // historylist.setLayoutManager(new LinearLayoutManager(this));

        //historylist.setAdapter(new HistoryAdapter(res, this));


    }
}
