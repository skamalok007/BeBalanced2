package com.example.bebalanced.bebalanced;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import org.json.JSONException;

import java.net.MalformedURLException;

public class Meditation extends AppCompatActivity {


private static MediaPlayer mplayer;
SeekBar seekBar;
ImageButton playbtn;
Runnable runnable;
Handler handler;

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
        mplayer=new MediaPlayer();
        songlist.setLayoutManager(new LinearLayoutManager(this));
        final String [] song_title= {"Track 1","Track 2"};
        final int[] song_path= {R.raw.track01,R.raw.track02};

        songlist.setAdapter(new SongAdapter(song_title,song_path,this));
        getIcomingIntent();

    }

    private void getIcomingIntent(){

        if(getIntent().hasExtra("path")){

           final int path=getIntent().getIntExtra("path",0);
            Log.d("Music will start", String.valueOf(path));
            songplayer(path);
        }
    }

    public void songplayer(final int path){

        if(mplayer!=null ){
            Log.d("Music Stop", String.valueOf(path));

           if(mplayer.isPlaying()){

               Log.d("Music Stop1", String.valueOf(path));


                mplayer.stop();
                mplayer.reset();
                mplayer.setLooping(false);

                mplayer.release();
                mplayer=null;

            }else {
               Log.d("Music Stop2", String.valueOf(path));

               mplayer.stop();
               mplayer.reset();
               mplayer.setLooping(false);

                mplayer.release();
                mplayer=null;

            }

        }

        mplayer=MediaPlayer.create(this, path);

       // mplayer.reset();

        seekBar=findViewById(R.id.seekBar1);
        playbtn=findViewById(R.id.play);

        handler=new Handler();

        mplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                        seekBar.setMax(mp.getDuration());
                        mp.start();
                        Log.d("Music Started", String.valueOf(path));
                        changeSeekbar();

                        }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){

                    mplayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void changeSeekbar() {

        seekBar.setProgress(mplayer.getCurrentPosition());
        if(mplayer.isPlaying()){
            runnable=new Runnable() {
                @Override
                public void run() {
                    changeSeekbar();
                }
            };

            handler.postDelayed(runnable, 1000);
        }
    }

}
