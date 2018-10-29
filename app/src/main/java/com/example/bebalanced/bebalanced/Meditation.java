package com.example.bebalanced.bebalanced;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONException;

import java.net.MalformedURLException;

public class Meditation extends AppCompatActivity {



    public void onBackPressed() {

        Intent intent= new Intent(Meditation.this, HomeActivity.class);
        startActivity(intent);

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);
        RecyclerView songlist  =findViewById(R.id.meditate_recycle);
        songlist.setLayoutManager(new LinearLayoutManager(this));
        String [] languages= {"ln1","ln2","ln3"};

        songlist.setAdapter(new SongAdapter(languages));

    }
}
