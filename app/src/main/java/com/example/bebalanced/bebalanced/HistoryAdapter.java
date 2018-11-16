package com.example.bebalanced.bebalanced;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryAdapterHolder>{
 private ArrayList<String> arrayList=new ArrayList<>();
 Context mContext;
    public  HistoryAdapter(Cursor res, Context context){

        res.moveToFirst();



        while(!res.isAfterLast()) {
            arrayList.add(res.getString(res.getColumnIndex("log_data"))); //add the item
            res.moveToNext();
        }
        mContext=context;
    }

    @NonNull
    @Override
    public HistoryAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view= layoutInflater.inflate(R.layout.history_list, viewGroup, false);
        return new HistoryAdapter.HistoryAdapterHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryAdapterHolder historyAdapterHolder, int i) {

        final String date = arrayList.get(i);
        historyAdapterHolder.historyDate.setText(date);

        historyAdapterHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HistoryAdapterHolder extends RecyclerView.ViewHolder {

        TextView historyDate;
        LinearLayout parent_layout;


        public HistoryAdapterHolder(@NonNull View itemView) {
            super(itemView);
            historyDate=(TextView) itemView.findViewById(R.id.history_date_list);
            parent_layout=itemView.findViewById(R.id.history_parent);
        }
    }
}
