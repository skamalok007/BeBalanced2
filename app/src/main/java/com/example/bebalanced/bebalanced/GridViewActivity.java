package com.example.bebalanced.bebalanced;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewActivity extends AppCompatActivity {
    TextView name,gridval1,gridval2;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        name=findViewById(R.id.griddata);
        image=findViewById(R.id.imageView);
        gridval1=findViewById(R.id.grid_val);
        gridval2=findViewById(R.id.griddata1);


        Intent intent= getIntent();
        name.setText(intent.getStringExtra("fruits"));
        image.setImageResource(intent.getIntExtra("images", 0));
        gridval1.setText(intent.getStringExtra("gridvalue"));
        gridval2.setText(intent.getStringExtra("gridvalue1"));



    }
}
