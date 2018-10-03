package com.example.bebalanced.bebalanced;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DateFormat;
import java.util.Calendar;

import static com.example.bebalanced.bebalanced.R.layout.row_data;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;


TextView t1,t2;
Typeface tf1;
GridView gridView;
    String [] activity_top_msg={"What fruit have you eaten today?","What veggies have you eaten today?","What protein have you eaten today?","How many crackers have you eaten today?","Have you taken your drops today?","How much water did you drink?", "Have you exercised today?", "Did you have a bowel movement today?", "What is your weight today?"};
    String [] activity_menu={"Fruit","Veggie","Protein","Crackers","Drops","Water","Supplements","Meditation","Weight","Exercise", "Bowel Movements","Tea/Coffee"};
    int [] activity_menuu_images= {R.drawable.apple,R.drawable.broccoli,R.drawable.steak,R.drawable.cracker,R.drawable.raindrop,R.drawable.glassofwater,R.drawable.pill,R.drawable.lotus,R.drawable.scale,R.drawable.exercise,R.drawable.man_at_restroom,R.drawable.mug};
    String [] activity_val={"0/2","0/2","0/2","0/5","Yes/No","0/8","Yes/No","Yes/No","Yes/No","Yes/No","Yes/No","Yes/No"};
    String [] activity_drop_down={"1", "2", "3","4","5","6","7","8","9","10","11","12"};

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


        //Menu Nav Bar
        dl=(DrawerLayout)findViewById(R.id.dl);
        abdt= new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.bebalanced_logo);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#b96823")));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayUseLogoEnabled(true);

        final NavigationView nav_view=(NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id= menuItem.getItemId();
                if(id==R.id.meditation){
                    Toast.makeText(HomeActivity.this, "Meditation", Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.badges){
                    Toast.makeText(HomeActivity.this, "Badges", Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.alarms){
                    Toast.makeText(HomeActivity.this, "Alarms", Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.about){
                    Toast.makeText(HomeActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.w_l_g){
                    Toast.makeText(HomeActivity.this, "Weight Loss Graph", Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.history){
                    Toast.makeText(HomeActivity.this, "History", Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.sign_out){
                    Toast.makeText(HomeActivity.this, "Sign Out", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        // Grid View

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
                intent.putExtra("drop_down_val", activity_drop_down[i]);
                startActivity(intent);

            }
        });



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);


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

        @SuppressLint("ViewHolder")
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
