package com.example.bebalanced.bebalanced;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongAdapterViewHolder > {
    private int[] data;
    private String [] song_title;
    Context mContext;

    public SongAdapter(String[] song_title, int[] data, Context context){

        this.song_title=song_title;
        this.data=data;
        mContext=context;
    }

    @NonNull
    @Override
    public SongAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view= layoutInflater.inflate(R.layout.song_list, viewGroup, false);
        return new SongAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SongAdapterViewHolder songAdapterViewHolder, final int i) {

        final String title= song_title[i];
        final int path=data[i];
        songAdapterViewHolder.txtTitle.setText(title);

        songAdapterViewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("clickbefore", String.valueOf(Uri.parse(String.valueOf(path))));

              Intent intent= new Intent(mContext, Meditation.class);
              intent.putExtra("path", path);
              mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class SongAdapterViewHolder extends RecyclerView.ViewHolder{

        ImageView imgIcon;
        TextView txtTitle;
        LinearLayout parent_layout;
        public SongAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon=(ImageView) itemView.findViewById(R.id.song_icon);
            txtTitle=(TextView) itemView.findViewById(R.id.song_name);
            parent_layout=itemView.findViewById(R.id.parent_layout);

        }
    }
}
