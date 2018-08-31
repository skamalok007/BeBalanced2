package com.example.bebalanced.bebalanced;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.DateFormat;
import java.util.Calendar;

import static com.example.bebalanced.bebalanced.R.layout.row_data;

public class HomeActivity extends AppCompatActivity {

TextView t1,t2;
Typeface tf1;
GridView gridView;
    String [] activity_top_msg={"Have you eaten fruits today?","Have you eaten veggie today?","Have you eaten meat today?","Have you eaten crackers today?","Have you taken your drops today?","How much water did you drink?", "Have you exercised today?", "Did you have a bowel movement today?", "What is your weight today?"};
String [] activity_menu={"Fruit","Veggie","Meat","Crackers","Drops","Water", "Exercise", "Bowel Movements", "Weight"};
int [] activity_menuu_images= {R.drawable.apple,R.drawable.broccoli,R.drawable.steak,R.drawable.cracker,R.drawable.raindrop,R.drawable.glassofwater,R.drawable.exercise,R.drawable.steak,R.drawable.scale};
String [] activity_val={"0/2","0/2","0/2","0/5","Yes/No","0/8","Yes/No","Yes/No","Yes/No"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        t1= (TextView)findViewById(R.id.textOne);
        t2=(TextView)findViewById(R.id.textTwo);


        tf1= Typeface.createFromAsset(getAssets(), "KGSevenSixteen.ttf");
        t1.setTypeface(tf1);


        Calendar calendar=Calendar.getInstance();
        String currentDate= DateFormat.getDateInstance().format(calendar.getTime());
        t2.setText(currentDate);

        gridView=findViewById(R.id.gridview);
        CustomAdapter customAdapter=new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getApplicationContext(), GridViewActivity.class);
                intent.putExtra("fruits",activity_menu[i]);
                intent.putExtra("images", activity_menuu_images[i]);
                intent.putExtra("gridvalue",activity_val[i]);
                intent.putExtra("gridvalue1", activity_top_msg[i]);
                startActivity(intent);

            }
        });


    }

    private class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return activity_menuu_images.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1;
            view1 = getLayoutInflater().inflate(row_data,null);
            TextView name=view1.findViewById(R.id.fruits);
            ImageView images=view1.findViewById(R.id.images);
            TextView gridval=view1.findViewById(R.id.gridvalue);


            name.setText(activity_menu[i]);
            images.setImageResource(activity_menuu_images[i]);
            gridval.setText(activity_val[i]);
            return view1;
        }
    }
}
