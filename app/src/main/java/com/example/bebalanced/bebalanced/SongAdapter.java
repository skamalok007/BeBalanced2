package com.example.bebalanced.bebalanced;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongAdapterViewHolder > {
    private String[] data;
    public SongAdapter(String[] data){

        this.data=data;

    }

    @NonNull
    @Override
    public SongAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view= layoutInflater.inflate(R.layout.song_list, viewGroup, false);

        return new SongAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapterViewHolder songAdapterViewHolder, int i) {

        String title= data[i];
        songAdapterViewHolder.txtTitle.setText(title);



    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class SongAdapterViewHolder extends RecyclerView.ViewHolder{

        ImageView imgIcon;
        TextView txtTitle;
        public SongAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon=(ImageView) itemView.findViewById(R.id.song_icon);
            txtTitle=(TextView) itemView.findViewById(R.id.song_name);
        }
    }
}
