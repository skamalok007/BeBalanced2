package com.example.bebalanced.bebalanced;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
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
DatabaseHelper myDb;
CustomAdapter customAdapter=new CustomAdapter();



    String [] activity_top_msg={"What fruit have you eaten today?","What veggies have you eaten today?","What protein have you eaten today?","How many crackers have you eaten today?","Have you taken your drops today?","How much water did you drink?","Have you taken your supplements today?","Meditation","What is your weight today?","Have you exercised today?", "Did you have a bowel movement today?", "",};
    String [] activity_menu={"Fruit","Veggie","Protein","Crackers","Drops","Water","Supplements","Meditation","Today's","Exercise", "Bowel Movements","Tea/Coffee"};
    int [] activity_menu_images= {R.drawable.apple,R.drawable.broccoli,R.drawable.steak,R.drawable.cracker,R.drawable.raindrop,R.drawable.glassofwater,R.drawable.pill,R.drawable.lotus,R.drawable.scale,R.drawable.exercise,R.drawable.man_at_restroom,R.drawable.mug};
    String [] activity_val={"0/2","0/2","0/2","0/0","M:0, A:0, E:0","0/8","Yes/No","","Weight","Yes/No","Yes/No",""};
    String [] activity_drop_down={"1", "2", "3","4","5","6","7","8","9","10","11","12"};
    @Override
    public  void onResume() {

        super.onResume();

        myDb = new DatabaseHelper(this);
        //Fetching Fruits value
        Cursor res = myDb.getFruitsData();
        Cursor protein_data=myDb.getProteinsData();
        Cursor cracker_data=myDb.getCrackersData();
        Cursor drop_data=myDb.getDropsData();
        Cursor water_data=myDb.getWaterData();
        Cursor weight_data=myDb.getWeightData();
        Cursor exercise_data=myDb.getExerciseData();
        Cursor bowel_data=myDb.getBowelData();
        Cursor tea_data=myDb.getTeaData();



        if (res.getCount() != 0) {

            while (res.moveToNext()) {

                String bf_fruits1 = res.getString(3);
                String bf_fruits2 = res.getString(4);
                if (!bf_fruits1.equals("Fruit 1") && !bf_fruits2.equals("Fruit 2")) {
                    activity_menu_images[0] = R.drawable.applefull;
                    activity_val[0] = "2/2";

                } else if (!bf_fruits1.equals("Fruit 1") && bf_fruits2.equals("Fruit 2")) {
                    activity_menu_images[0] = R.drawable.applehalf;
                    activity_val[0] = "1/2";

                } else if (bf_fruits1.equals("Fruit 1") && !bf_fruits2.equals("Fruit 2")) {
                    activity_menu_images[0] = R.drawable.applehalf;
                    activity_val[0] = "1/2";

                } else if (bf_fruits1.equals("Fruit 1") && bf_fruits2.equals("Fruit 2")) {
                    activity_menu_images[0] = R.drawable.apple;
                    activity_val[0] = "0/2";

                }

            }

        }

        //Protein

        if (protein_data.getCount() != 0) {

            while (protein_data.moveToNext()) {

                String bf_protein1 = protein_data.getString(3);
                String bf_protein2 = protein_data.getString(4);
                if (!bf_protein1.equals("Protein 1") && !bf_protein2.equals("Protein 2")) {
                    activity_menu_images[2] = R.drawable.steakfull;
                    activity_val[2] = "2/2";


                } else if (!bf_protein1.equals("Protein 1") && bf_protein2.equals("Protein 2")) {
                    activity_menu_images[2] = R.drawable.steakhalf;
                    activity_val[2] = "1/2";


                } else if (bf_protein1.equals("Protein 1") && !bf_protein2.equals("Protein 2")) {
                    activity_menu_images[2] = R.drawable.steakhalf;
                    activity_val[2] = "1/2";

                } else if (bf_protein1.equals("Protein 1") && bf_protein2.equals("Protein 2")) {
                    activity_menu_images[2] = R.drawable.steak;
                    activity_val[2] = "0/2";

                }

            }

        }

        // Crackers

        if (cracker_data.getCount() != 0) {

            while (cracker_data.moveToNext()) {

                int cracker_cnt = Integer.parseInt(cracker_data.getString(3));

                if(cracker_cnt==1){

                    activity_menu_images[3] = R.drawable.cracker1;
                    activity_val[3] = cracker_cnt+"/5";

                }else if(cracker_cnt==2){
                    activity_menu_images[3] = R.drawable.cracker2;
                    activity_val[3] = cracker_cnt+"/5";

                }else if(cracker_cnt==3){

                    activity_menu_images[3] = R.drawable.cracker3;
                    activity_val[3] = cracker_cnt+"/5";

                }else if(cracker_cnt==4){

                    activity_menu_images[3] = R.drawable.cracker4;
                    activity_val[3] = cracker_cnt+"/5";

                }else if(cracker_cnt==5){

                    activity_menu_images[3] = R.drawable.cracker5;
                    activity_val[3] = cracker_cnt+"/5";

                }else if(cracker_cnt>5){

                    activity_menu_images[3] = R.drawable.cracker5;
                    activity_val[3] = cracker_cnt+"/5";

                }
                else{
                    activity_menu_images[3] = R.drawable.cracker;
                    activity_val[3] = cracker_cnt+"/5";

                }


            }

        }

        //Drops

        if (drop_data.getCount() != 0) {

            while (drop_data.moveToNext()) {

                int drop_mrng = Integer.parseInt(drop_data.getString(3));
                int drp_after = Integer.parseInt(drop_data.getString(4));
                int drp_evening = Integer.parseInt(drop_data.getString(5));


                if(drop_mrng==0 && drp_evening ==0 && drp_after==0){

                    activity_menu_images[4] = R.drawable.raindrop;
                    activity_val[4] = "M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening;


                }else if(drop_mrng==1 && drp_evening ==0 && drp_after==0){
                    activity_menu_images[4] = R.drawable.raindrop1;
                    activity_val[4] = "M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening;


                }else if(drop_mrng==1 && drp_evening ==1 && drp_after==0){
                    activity_menu_images[4] = R.drawable.raindrop2;
                    activity_val[4] = "M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening;


                }else if(drop_mrng==1 && drp_evening ==1 && drp_after==1){
                    activity_menu_images[4] = R.drawable.raindrop3;
                    activity_val[4] = "M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening;


                }else if(drop_mrng==0 && drp_evening ==1 && drp_after==1){
                    activity_menu_images[4] = R.drawable.raindrop4;
                    activity_val[4] = "M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening;


                }else if(drop_mrng==1 && drp_evening ==0 && drp_after==1){
                    activity_menu_images[4] = R.drawable.raindrop5;
                    activity_val[4] = "M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening;


                }else if(drop_mrng==0 && drp_evening ==0 && drp_after==1){
                    activity_menu_images[4] = R.drawable.raindrop6;
                    activity_val[4] = "M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening;


                }else if(drop_mrng==0 && drp_evening ==1 && drp_after==0){
                    activity_menu_images[4] = R.drawable.raindrop7;
                    activity_val[4] = "M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening;


                }


            }

        }

        //Weight

        if (weight_data.getCount() != 0) {

            while (weight_data.moveToNext()) {

                int weight_val= Integer.parseInt(weight_data.getString(3));


                activity_val[8]="weight :"+weight_val;
                if(weight_val>0) {
                    activity_val[5]="0/"+(weight_val/2);
                }


            }

        }

        //Water

        if (water_data.getCount() != 0) {

            while (water_data.moveToNext()) {

                int water_cnt = Integer.parseInt(water_data.getString(3));

                String[] parts=activity_val[8].split(":");
               int weight_val1= Integer.parseInt(parts[1]);

                if(water_cnt==1){

                    activity_menu_images[5] = R.drawable.glass1;
                    activity_val[5] = water_cnt+"/"+(weight_val1/2);

                }else if(water_cnt==2){
                    activity_menu_images[5] = R.drawable.glass2;
                    activity_val[5] = water_cnt+"/"+(weight_val1/2);

                }else if(water_cnt==3){

                    activity_menu_images[5] = R.drawable.glass3;
                    activity_val[5] = water_cnt+"/"+(weight_val1/2);

                }else if(water_cnt==4){

                    activity_menu_images[5] = R.drawable.glass4;
                    activity_val[5] = water_cnt+"/"+(weight_val1/2);

                }else if(water_cnt==5){

                    activity_menu_images[5] = R.drawable.glass5;
                    activity_val[5] = water_cnt+"/"+(weight_val1/2);

                }else if(water_cnt==6){

                    activity_menu_images[5] = R.drawable.glass6;
                    activity_val[5] = water_cnt+"/"+(weight_val1/2);

                }else if(water_cnt==7){

                    activity_menu_images[5] = R.drawable.glass7;
                    activity_val[5] = water_cnt+"/"+(weight_val1/2);

                }else if(water_cnt==8){

                    activity_menu_images[5] = R.drawable.glass6;
                    activity_val[5] = water_cnt+"/"+(weight_val1/2);

                }

                else{
                    activity_menu_images[5] = R.drawable.glassofwater;
                    activity_val[5] = water_cnt+"/"+(weight_val1/2);
                }


            }

        }


        //Exercise

        if (exercise_data.getCount() != 0) {

            while (exercise_data.moveToNext()) {

                int exercise_val= Integer.parseInt(exercise_data.getString(3));

                if(exercise_val==1){

                    activity_menu_images[9]=R.drawable.exercise1;
                    activity_val[9]="yes";

                }else{

                    activity_menu_images[9]=R.drawable.exercise;
                    activity_val[9]="no";

                }

            }

        }

        //Bowel Movement

        if (bowel_data.getCount() != 0) {

            while (bowel_data.moveToNext()) {

                int bowel_val= Integer.parseInt(bowel_data.getString(3));

                if(bowel_val==1){

                    activity_menu_images[10]=R.drawable.man_at_restroom1;
                    activity_val[10]="yes";

                }else{


                    activity_menu_images[10]=R.drawable.man_at_restroom;
                    activity_val[10]="no";

                }

            }

        }

        //TeaCoffee

        if (tea_data.getCount() != 0) {

            while (tea_data.moveToNext()) {

                int tea_val= Integer.parseInt(tea_data.getString(3));

                activity_val[11]=""+tea_val+" Oz" ;
                activity_menu_images[11]=R.drawable.mug1;


            }

        }
        customAdapter.notifyDataSetChanged();

    }
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

        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getApplicationContext(), GridViewActivity.class);
                intent.putExtra("fruits",activity_menu[i]);
                intent.putExtra("images", activity_menu_images[i]);
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
            return activity_menu_images.length;
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
            images.setImageResource(activity_menu_images[i]);
            gridval.setText(activity_val[i]);

            return view1;
        }
    }
}
