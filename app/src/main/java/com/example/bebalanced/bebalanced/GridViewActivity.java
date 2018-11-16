package com.example.bebalanced.bebalanced;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class GridViewActivity extends AppCompatActivity {
    TextView name,gridval1,gridval2,text_val1,text_val2,text_val3;
    String drp_down_val;
    ImageView image;
    EditText cracker;
    Spinner spinner,spinner1,spinner_veggies1, spinner_veggies2, spinner_cupsize1,spinner_cupsize2;
    int drp_val,drp_val1,drp_val2,drp_val3;
    ArrayAdapter<CharSequence> adapter,adapter1,adapter2;

    ImageButton btn3;
    Switch switch1, switch2, switch3, switch4;
    int cnt_apple=0, fruits1_cnt=0, fruits2_cnt=0, meats1_cnt=0, meats2_cnt=0,exer_val=0,bowel_val=0,weight_val=0;
    JSONObject Params;

    int drop_mrng=0;
    int drp_after=0;
    int drp_evening=0;
    int cracker_cnt=0;

   // LinearLayout ln;

    int veggies_pos1=0,veggies_pos2=0;

    boolean rs;

    //String spinnerval1="",spinnerval2="";
    URL url;
    DatabaseHelper myDb;


    @Override
    public void onBackPressed() {

        Toast.makeText(getApplicationContext(), "onback",
                Toast.LENGTH_LONG).show();

        try {
            if(Params.getString("section").equals("fruits")){
                rs = myDb.insertFruits(Params);
                myDb.insertUserLog(Params);


            }else if(Params.getString("section").equals("veggies")){
                rs = myDb.insertVeggies(Params);
                myDb.insertUserLog(Params);

            }else if(Params.getString("section").equals("protein")){
                rs = myDb.insertProteins(Params);
                myDb.insertUserLog(Params);

            }else if(Params.getString("section").equals("cracker")){
                rs = myDb.insertCrackers(Params);
                myDb.insertUserLog(Params);

            }else if(Params.getString("section").equals("drop")){
                rs = myDb.insertDrops(Params);
                myDb.insertUserLog(Params);

            }else if(Params.getString("section").equals("water")){
                rs = myDb.insertWater(Params);
                myDb.insertUserLog(Params);

            }else if(Params.getString("section").equals("weight")){
                rs = myDb.insertWeight(Params);
                myDb.insertUserLog(Params);

            }else if(Params.getString("section").equals("exercise")){
                rs = myDb.insertExercise(Params);
                myDb.insertUserLog(Params);

            }else if(Params.getString("section").equals("bowel")){
                rs = myDb.insertBowel(Params);
                myDb.insertUserLog(Params);

            }else if(Params.getString("section").equals("tea")){
                rs = myDb.insertTea(Params);
                myDb.insertUserLog(Params);

            }


            if(rs){
                Toast.makeText(getApplicationContext(), "inserted",
                        Toast.LENGTH_LONG).show();
                SendPostRequest sd=new SendPostRequest(Params, url);
                sd.execute();
                Toast.makeText(getApplicationContext(), "onback",
                        Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(getApplicationContext(), "not inserted",
                        Toast.LENGTH_LONG).show();

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        name=findViewById(R.id.griddata);
        image=findViewById(R.id.imageView);
        gridval1=findViewById(R.id.grid_val);
        gridval2=findViewById(R.id.griddata1);

        btn3=findViewById(R.id.add);
        text_val1=findViewById(R.id.text_val1);
        text_val2=findViewById(R.id.text_val2);
        text_val3=findViewById(R.id.text_val3);




        cracker=findViewById(R.id.cracker);
        spinner1= findViewById(R.id.spinner1);
        spinner= findViewById(R.id.spinner);
        spinner_veggies1= findViewById(R.id.spinner2);
        spinner_cupsize1= findViewById(R.id.spinner3);
        spinner_veggies2= findViewById(R.id.spinner4);
        spinner_cupsize2= findViewById(R.id.spinner5);

        switch1=findViewById(R.id.switch1);
        switch2=findViewById(R.id.switch2);
        switch3=findViewById(R.id.switch3);
        switch4=findViewById(R.id.switch4);



        Intent intent= getIntent();
        name.setText(intent.getStringExtra("fruits"));
        image.setImageResource(intent.getIntExtra("images", 0));
        gridval1.setText(intent.getStringExtra("gridvalue"));
        gridval2.setText(intent.getStringExtra("gridvalue1"));
        drp_down_val=intent.getStringExtra("drop_down_val");
        drp_val = Integer.parseInt(drp_down_val);
        final Context context = getApplicationContext();

        myDb=new DatabaseHelper(this);

        switch (drp_val)//position is the array index
        {


            case 1: //fruits
               // String spinnerval1 = null,spinnerval2 = null;

                Params =new JSONObject();

                drp_val1=R.array.fruits_name;
                drp_val2=R.array.fruits_name1;
                adapter=ArrayAdapter.createFromResource(this, drp_val1, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapter1=ArrayAdapter.createFromResource(this, drp_val2, android.R.layout.simple_spinner_item);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter1);
                spinner1.setAdapter(adapter);
                cracker.setVisibility(View.GONE);

                btn3.setVisibility(View.GONE);

                spinner_cupsize1.setVisibility(View.GONE);
                spinner_veggies1.setVisibility(View.GONE);
                spinner_cupsize2.setVisibility(View.GONE);
                spinner_veggies2.setVisibility(View.GONE);

                switch1.setVisibility(View.GONE);
                switch2.setVisibility(View.GONE);
                switch3.setVisibility(View.GONE);
                switch4.setVisibility(View.GONE);

                text_val1.setVisibility(View.GONE);
                text_val2.setVisibility(View.GONE);
                text_val3.setVisibility(View.GONE);


                Cursor res=myDb.getFruitsData();

                if(res.getCount()!=0){

                    while (res.moveToNext()) {

                        String bf_fruits1= res.getString(3);
                        String bf_fruits2= res.getString(4);
                        if(!bf_fruits1.equals("Fruits 1")){


                                int spinnerPosition = adapter.getPosition(bf_fruits1);

                                spinner1.setSelection(spinnerPosition);

                                cnt_apple=cnt_apple+1;
                                fruits1_cnt=1;

                        }

                        if(!bf_fruits2.equals("Fruits 2")){

                            int spinnerPosition = adapter.getPosition(bf_fruits2);

                            spinner.setSelection(spinnerPosition);
                            cnt_apple=cnt_apple+1;
                            fruits2_cnt=1;

                        }


                    }

                }

                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String fruits1 =parent.getItemAtPosition(position).toString();

                        if(position!=0 && cnt_apple<2 ){

                            if(cnt_apple==1) {


                                if (fruits1_cnt == 0) {
                                    cnt_apple = cnt_apple + 1;
                                    fruits1_cnt = fruits1_cnt + 1;

                                }
                            }else {
                                cnt_apple = cnt_apple + 1;
                                fruits1_cnt = fruits1_cnt + 1;

                            }



                        }else if(position==0 && cnt_apple<=2 && cnt_apple>0){

                            if(fruits1_cnt==1 ){
                                cnt_apple = cnt_apple - 1;
                                fruits1_cnt=fruits1_cnt-1;

                            }

                        }

                        if(cnt_apple==0){

                            image.setImageResource(R.drawable.apple);
                            gridval1.setText(cnt_apple+"/2");

                        }else if(cnt_apple==1){
                            image.setImageResource(R.drawable.applehalf);
                            gridval1.setText(cnt_apple+"/2");

                        }else{

                            image.setImageResource(R.drawable.applefull);
                            gridval1.setText(cnt_apple+"/2");

                        }


                        String text1 = String.valueOf(cnt_apple);
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text1+ fruits1, duration);
                        toast.show();
                        try {
                            Params.put("fruits1", fruits1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String fruits2 = parent.getItemAtPosition(position).toString();

                        if(position!=0 && cnt_apple<2 ){

                            if(cnt_apple==1) {


                                if (fruits2_cnt == 0) {
                                    cnt_apple = cnt_apple + 1;
                                    fruits2_cnt = fruits2_cnt + 1;

                                }
                            }else {
                                cnt_apple = cnt_apple + 1;
                                fruits2_cnt = fruits2_cnt + 1;

                            }



                        }else if(position==0 && cnt_apple<=2 && cnt_apple>0){

                            if(fruits2_cnt==1){
                                cnt_apple = cnt_apple - 1;
                                fruits2_cnt=fruits2_cnt-1;

                            }

                        }
                        if(cnt_apple==0){

                            image.setImageResource(R.drawable.apple);
                            gridval1.setText(cnt_apple+"/2");

                        }else if(cnt_apple==1){

                            image.setImageResource(R.drawable.halfapple);
                            gridval1.setText(cnt_apple+"/2");

                        }else{

                            image.setImageResource(R.drawable.applefull);
                            gridval1.setText(cnt_apple+"/2");

                        }

                        String text1 = String.valueOf(cnt_apple);

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text1+ fruits2, duration);

                        toast.show();
                        try {
                            Params.put("fruits2", fruits2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                try {
                    url=new URL("http://vnoi.in/BeBalanced/cust_insert.php");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


                try {

                    Date today = new Date();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    String dateToStr = format.format(today);

                    int duration = Toast.LENGTH_SHORT;

                    Params.put("name","bill");
                    Params.put("section","fruits");
                    Params.put("date", dateToStr);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;
            case 2: //veggies

                Params =new JSONObject();
                drp_val1=R.array.veggies_name1;

                drp_val3=R.array.cupsize;

                adapter=ArrayAdapter.createFromResource(this, drp_val1, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                adapter2=ArrayAdapter.createFromResource(this, drp_val3, android.R.layout.simple_spinner_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner_veggies1.setAdapter(adapter);
                spinner_cupsize1.setAdapter(adapter2);
                spinner_veggies2.setAdapter(adapter);
                spinner_cupsize2.setAdapter(adapter2);
                text_val1.setVisibility(View.GONE);
                text_val2.setVisibility(View.GONE);
                text_val3.setVisibility(View.GONE);

                switch1.setVisibility(View.GONE);
                switch2.setVisibility(View.GONE);
                switch3.setVisibility(View.GONE);
                switch4.setVisibility(View.GONE);

/*
                btn3.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast toast = Toast.makeText(context, "on click", Toast.LENGTH_LONG);
                        toast.show();

                        ln=new LinearLayout(context);
                        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                        ln.setOrientation(HORIZONTAL);
                        ln.setBackgroundColor(Color.parseColor("#0000ff"));
                        ln.setLayoutParams(params);



                        *//*Spinner sp= new Spinner(GridViewActivity.this);
                        params.weight= (float) 0.5;
                        params.setMargins(30,0,30,0);
                        sp.setBackgroundResource(R.drawable.gradient_spinner);
                        sp.setLayoutParams(params);
                        Spinner sp1= new Spinner(GridViewActivity.this);
                        params.setMargins(30,0,30,0);
                        sp1.setBackgroundResource(R.drawable.gradient_spinner);
                        sp1.setLayoutParams(params);
                        ln.addView(sp,params);
                        ln.addView(sp1, params);*//*


                    }
                });*/


                cracker.setVisibility(View.GONE);


                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);

                spinner_veggies1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String veggies1 =parent.getItemAtPosition(position).toString();

                        veggies_pos1=position;


                        String text1 = String.valueOf(cnt_apple);
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text1+ veggies1, duration);
                        toast.show();
                        try {
                            Params.put("veggies1", veggies1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                spinner_veggies2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String veggies2 =parent.getItemAtPosition(position).toString();

                        veggies_pos2=position;


                        String text1 = String.valueOf(cnt_apple);
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text1+ veggies2, duration);
                        toast.show();
                        try {
                            Params.put("veggies2", veggies2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



                spinner_cupsize1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String cupsize1 = parent.getItemAtPosition(position).toString();


                        if(veggies_pos1>0  && position!=0 && cnt_apple<2){


                            if(position==1){
                                cnt_apple= (int) (cnt_apple+0.5);

                            }else if(position==2){

                                cnt_apple= cnt_apple+1;
                            }



                        }else if(veggies_pos1>0  && position==0 && cnt_apple<=2 && cnt_apple>0){


                            cnt_apple = cnt_apple - 1;
                        }


                        if((veggies_pos1>0 || veggies_pos2>0) && cnt_apple==0){

                            image.setImageResource(R.drawable.broccoli);
                            gridval1.setText(cnt_apple+"/2");

                        }else if((veggies_pos1>0 || veggies_pos2>0) && cnt_apple==1){
                            image.setImageResource(R.drawable.broccoli2);
                            gridval1.setText(cnt_apple+"/2");

                        }else{

                            image.setImageResource(R.drawable.broccoli4);
                            gridval1.setText(cnt_apple+"/2");

                        }

                        String text1 = String.valueOf(cnt_apple);
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text1+ cupsize1, duration);
                        toast.show();
                        try {
                            Params.put("cupsize1", cupsize1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                spinner_cupsize2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String cupsize2 =parent.getItemAtPosition(position).toString();

                        if((veggies_pos1>0 || veggies_pos2>0) && position!=0 && cnt_apple<2){

                            if(position==1){
                                cnt_apple= (int) (cnt_apple+0.5);

                            }else if(position==2){

                                cnt_apple= cnt_apple+1;
                            }

                        }else if((veggies_pos1>0 || veggies_pos2>0) && position==0 && cnt_apple<=2 && cnt_apple>0){


                            cnt_apple = cnt_apple - 1;

                        }
                        else if((veggies_pos1>0 || veggies_pos2>0) && cnt_apple!=0 && cnt_apple<2){

                            if(position==1){
                                cnt_apple= (int) (cnt_apple+0.5);

                            }else if(position==2){

                                cnt_apple= cnt_apple+1;
                            }


                        }
                        if((veggies_pos1>0 || veggies_pos2>0) && cnt_apple==0){

                            image.setImageResource(R.drawable.broccoli);
                            gridval1.setText(cnt_apple+"/2");

                        }else if((veggies_pos1>0 || veggies_pos2>0) && cnt_apple==1){
                            image.setImageResource(R.drawable.broccoli2);
                            gridval1.setText(cnt_apple+"/2");

                        }else if((veggies_pos1>0 || veggies_pos2>0) && cnt_apple==0){
                            image.setImageResource(R.drawable.broccoli);
                            gridval1.setText(cnt_apple+"/2");

                        }
                        else {

                            image.setImageResource(R.drawable.broccoli4);
                            gridval1.setText(cnt_apple+"/2");

                        }

                        String text1 = String.valueOf(cnt_apple);
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text1+ cupsize2, duration);
                        toast.show();
                        try {
                            Params.put("cupsize2", cupsize2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });




                try {
                    url=new URL("http://vnoi.in/BeBalanced/cust_insert.php");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                try {

                    Date today = new Date();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    String dateToStr = format.format(today);



                    Params.put("name","bill");
                    Params.put("section","veggies");
                    Params.put("date", dateToStr);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case 3: //meats

                Params =new JSONObject();

                drp_val1=R.array.meat_name;
                drp_val2=R.array.meat_name1;
                adapter=ArrayAdapter.createFromResource(this, drp_val1, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapter1=ArrayAdapter.createFromResource(this, drp_val2, android.R.layout.simple_spinner_item);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter1);
                spinner1.setAdapter(adapter);
                cracker.setVisibility(View.GONE);

                btn3.setVisibility(View.GONE);
                spinner_cupsize1.setVisibility(View.GONE);
                spinner_veggies1.setVisibility(View.GONE);
                spinner_cupsize2.setVisibility(View.GONE);
                spinner_veggies2.setVisibility(View.GONE);


                switch1.setVisibility(View.GONE);
                switch2.setVisibility(View.GONE);
                switch3.setVisibility(View.GONE);
                switch4.setVisibility(View.GONE);


                text_val1.setVisibility(View.GONE);
                text_val2.setVisibility(View.GONE);
                text_val3.setVisibility(View.GONE);




                res=myDb.getProteinsData();

                if(res.getCount()!=0){

                    while (res.moveToNext()) {

                        String bf_meat1= res.getString(3);
                        String bf_meat2= res.getString(4);
                        if(!bf_meat1.equals("Protein 1")){


                            int spinnerPosition = adapter.getPosition(bf_meat1);

                            spinner1.setSelection(spinnerPosition);

                            cnt_apple=cnt_apple+1;
                            meats1_cnt=1;

                        }

                        if(!bf_meat2.equals("Protein 2")){

                            int spinnerPosition = adapter.getPosition(bf_meat2);

                            spinner.setSelection(spinnerPosition);
                            cnt_apple=cnt_apple+1;
                            meats2_cnt=1;

                        }


                    }

                }




                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String meats1 =parent.getItemAtPosition(position).toString();


                        if(position!=0 && cnt_apple<2 ){

                            if(cnt_apple==1) {


                                if (meats1_cnt == 0) {
                                    cnt_apple = cnt_apple + 1;
                                    meats1_cnt = meats1_cnt + 1;

                                }
                            }else {
                                cnt_apple = cnt_apple + 1;
                                meats1_cnt = meats1_cnt + 1;

                            }



                        }else if(position==0 && cnt_apple<=2 && cnt_apple>0){

                            if(meats1_cnt==1 ){
                                cnt_apple = cnt_apple - 1;
                                meats1_cnt=meats1_cnt-1;

                            }

                        }
                        if(cnt_apple==0){

                            image.setImageResource(R.drawable.steak);
                            gridval1.setText(cnt_apple+"/2");

                        }else if(cnt_apple==1){
                            image.setImageResource(R.drawable.steakhalf);
                            gridval1.setText(cnt_apple+"/2");

                        }else{

                            image.setImageResource(R.drawable.steakfull);
                            gridval1.setText(cnt_apple+"/2");

                        }

                        String text1 = String.valueOf(cnt_apple);
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text1+meats1, duration);
                        toast.show();
                        try {
                            Params.put("protein1", meats1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String meats2 = parent.getItemAtPosition(position).toString();


                        if(position!=0 && cnt_apple<2 ){

                            if(cnt_apple==1) {


                                if (meats2_cnt == 0) {
                                    cnt_apple = cnt_apple + 1;
                                    meats2_cnt = meats2_cnt + 1;

                                }
                            }else {
                                cnt_apple = cnt_apple + 1;
                                meats2_cnt = meats2_cnt + 1;

                            }



                        }else if(position==0 && cnt_apple<=2 && cnt_apple>0){

                            if(meats2_cnt==1 ){
                                cnt_apple = cnt_apple - 1;
                                meats2_cnt=meats2_cnt-1;

                            }

                        }
                        if(cnt_apple==0){

                            image.setImageResource(R.drawable.steak);
                            gridval1.setText(cnt_apple+"/2");

                        }else if(cnt_apple==1){

                            image.setImageResource(R.drawable.steakhalf);
                            gridval1.setText(cnt_apple+"/2");

                        }else{

                            image.setImageResource(R.drawable.steakfull);
                            gridval1.setText(cnt_apple+"/2");

                        }

                        String text1 = String.valueOf(cnt_apple);

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text1+meats2, duration);

                        toast.show();
                        try {
                            Params.put("protein2", meats2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                try {
                    url=new URL("http://vnoi.in/BeBalanced/cust_insert.php");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


                try {

                    Date today = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    String dateToStr = format.format(today);

                    int duration = Toast.LENGTH_SHORT;

                    Params.put("name","bill");
                    Params.put("section","protein");
                    Params.put("date", dateToStr);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;
            case 4: //Crackers

                Params =new JSONObject();



                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);
                spinner_cupsize1.setVisibility(View.GONE);
                spinner_veggies1.setVisibility(View.GONE);
                spinner_cupsize2.setVisibility(View.GONE);
                spinner_veggies2.setVisibility(View.GONE);

                switch1.setVisibility(View.GONE);
                switch2.setVisibility(View.GONE);
                switch3.setVisibility(View.GONE);
                switch4.setVisibility(View.GONE);


                btn3.setVisibility(View.GONE);
                text_val1.setVisibility(View.GONE);
                text_val2.setVisibility(View.GONE);
                text_val3.setVisibility(View.GONE);



                /*res=myDb.getCrackersData();

                if(res.getCount()!=0){

                    while (res.moveToNext()) {

                        int bf_cracker= Integer.parseInt(res.getString(3));

                        if(bf_cracker==0){

                            cracker.setText(bf_cracker);
                            cracker_cnt=0;
                            gridval1.setText(bf_cracker+"/5");


                        }else if(bf_cracker<=5){

                            cracker.setText(bf_cracker);
                            gridval1.setText(bf_cracker+"/5");
                            cracker_cnt=bf_cracker;

                        }




                    }

                }*/



                cracker.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        String cnt = cracker.getText().toString();

                        if(cnt.equals("")){
                            cnt="0";
                        }

                        cracker_cnt=Integer.parseInt(cnt);

                        if(cracker_cnt==1){

                            image.setImageResource(R.drawable.cracker1);
                            gridval1.setText(cracker_cnt+"/5");

                        }else if(cracker_cnt==2){
                            image.setImageResource(R.drawable.cracker2);
                            gridval1.setText(cracker_cnt+"/5");
                        }else if(cracker_cnt==3){
                            image.setImageResource(R.drawable.cracker3);
                            gridval1.setText(cracker_cnt+"/5");

                        }else if(cracker_cnt==4){
                            image.setImageResource(R.drawable.cracker4);
                            gridval1.setText(cracker_cnt+"/5");

                        }else if(cracker_cnt==5){
                            image.setImageResource(R.drawable.cracker5);
                            gridval1.setText(cracker_cnt+"/5");

                        }else if(cracker_cnt>5){
                            image.setImageResource(R.drawable.cracker5);
                            gridval1.setText("5/5");

                        }else{
                            image.setImageResource(R.drawable.cracker);
                            gridval1.setText(cracker_cnt+"/5");


                        }

                        Date today = new Date();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                        String dateToStr = format.format(today);



                        try {

                            Params.put("cracker_cnt", cracker_cnt);
                            Params.put("name","bill");
                            Params.put("section","cracker");
                            Params.put("date", dateToStr);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });




                break;

            case 5: //Drops

                Params =new JSONObject();


                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);

                spinner_cupsize1.setVisibility(View.GONE);
                spinner_veggies1.setVisibility(View.GONE);
                spinner_cupsize2.setVisibility(View.GONE);
                spinner_veggies2.setVisibility(View.GONE);

                switch4.setVisibility(View.GONE);



                btn3.setVisibility(View.GONE);
                cracker.setVisibility(View.GONE);


/*
                res=myDb.getDropsData();

                if(res.getCount()!=0){

                    while (res.moveToNext()) {

                        int bf_mrng= Integer.parseInt(res.getString(3));
                        int bf_after= Integer.parseInt(res.getString(4));
                        int bf_evening= Integer.parseInt(res.getString(5));

                        if(bf_mrng==1){
                            drop_mrng=1;

                        }else{
                            drop_mrng=0;
                        }

                        if(bf_after==1){
                            drp_after=1;

                        }else{
                            drp_after=0;
                        }
                        if(bf_evening==1){
                            drp_evening=1;

                        }else{
                            drp_evening=0;
                        }



                    }

                }
*/





                switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                        if(isChecked){

                            drop_mrng=1;
                            int drop_mrng1=1;

                             if(drp_evening ==0 && drp_after==0){
                                image.setImageResource(R.drawable.raindrop1);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drp_evening ==1 && drp_after==0){
                                image.setImageResource(R.drawable.raindrop2);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drp_evening ==1 && drp_after==1){
                                image.setImageResource(R.drawable.raindrop3);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drp_evening ==0 && drp_after==1){
                                image.setImageResource(R.drawable.raindrop5);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }

                            try {
                                Params.put("mrng", drop_mrng1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }else{
                            drop_mrng=0;
                            int drop_mrng1=0;
                            if(drp_evening ==0 && drp_after==0){

                                image.setImageResource(R.drawable.raindrop);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drp_evening ==1 && drp_after==1){
                                image.setImageResource(R.drawable.raindrop4);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drp_evening ==0 && drp_after==1){
                                image.setImageResource(R.drawable.raindrop6);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drp_evening ==1 && drp_after==0){
                                image.setImageResource(R.drawable.raindrop7);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }

                            try {
                                Params.put("mrng", drop_mrng1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }




                    }
                });

                switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                        if(isChecked){
                            drp_evening=1;
                            int drp_evening1=1;

                            if(drop_mrng==1 && drp_after==0){
                                image.setImageResource(R.drawable.raindrop2);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drop_mrng==1 &&  drp_after==1){
                                image.setImageResource(R.drawable.raindrop3);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drop_mrng==0 &&  drp_after==1){
                                image.setImageResource(R.drawable.raindrop4);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drop_mrng==0  && drp_after==0){
                                image.setImageResource(R.drawable.raindrop7);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }

                            try {
                                Params.put("evening", drp_evening1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }else{
                            drp_evening=0;
                            int drp_evening1=0;

                            if(drop_mrng==0  && drp_after==0){

                                image.setImageResource(R.drawable.raindrop);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drop_mrng==1 && drp_after==0){
                                image.setImageResource(R.drawable.raindrop1);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drop_mrng==1  && drp_after==1){
                                image.setImageResource(R.drawable.raindrop5);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drop_mrng==0 && drp_after==1){
                                image.setImageResource(R.drawable.raindrop6);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }

                            try {
                                Params.put("evening", drp_evening1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }



                    }
                });

                switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                        if(isChecked){

                            drp_after=1;
                            int drp_after1=1;

                          if(drop_mrng==1 && drp_evening ==1 ){
                                image.setImageResource(R.drawable.raindrop3);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drop_mrng==0 && drp_evening ==1 ){
                                image.setImageResource(R.drawable.raindrop4);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drop_mrng==1 && drp_evening ==0 ){
                                image.setImageResource(R.drawable.raindrop5);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drop_mrng==0 && drp_evening ==0 ){
                                image.setImageResource(R.drawable.raindrop6);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }

                            try {
                                Params.put("after", drp_after1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            drp_after=0;

                            int drp_after1=0;

                            if(drop_mrng==0 && drp_evening ==0){

                                image.setImageResource(R.drawable.raindrop);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drop_mrng==1 && drp_evening ==0 ){
                                image.setImageResource(R.drawable.raindrop1);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drop_mrng==1 && drp_evening ==1 ){
                                image.setImageResource(R.drawable.raindrop2);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }else if(drop_mrng==0 && drp_evening ==1 ){
                                image.setImageResource(R.drawable.raindrop7);
                                gridval1.setText("M:"+drop_mrng+"A:"+drp_after+"E:"+drp_evening);


                            }

                            try {
                                Params.put("after", drp_after1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                    }
                });

                Date today = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                String dateToStr = format.format(today);



                try {



                    Params.put("name","bill");
                    Params.put("section","drop");
                    Params.put("date", dateToStr);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;

            case 6: //Water

                Params =new JSONObject();




                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);
                spinner_cupsize1.setVisibility(View.GONE);
                spinner_veggies1.setVisibility(View.GONE);
                spinner_cupsize2.setVisibility(View.GONE);
                spinner_veggies2.setVisibility(View.GONE);

                switch1.setVisibility(View.GONE);
                switch2.setVisibility(View.GONE);
                switch3.setVisibility(View.GONE);
                switch4.setVisibility(View.GONE);


                btn3.setVisibility(View.GONE);
                text_val1.setVisibility(View.GONE);
                text_val2.setVisibility(View.GONE);
                text_val3.setVisibility(View.GONE);



                int water_val=0;

                Cursor res_water=myDb.getWaterData();

                if(res_water.getCount()!=0){

                    while (res_water.moveToNext()) {

                        water_val= Integer.parseInt(res_water.getString(3));

                        cracker_cnt=water_val;




                    }
                }

                Cursor res_weight=myDb.getWeightData();

                if(res_weight.getCount()!=0){

                    while (res_weight.moveToNext()) {

                        weight_val= Integer.parseInt(res_weight.getString(3));
                        if(weight_val>0) {

                            gridval1.setText(cracker_cnt + "/" + (weight_val / 2));
                        }else{
                            weight_val=60;
                            gridval1.setText(cracker_cnt + "/" + (weight_val / 2));

                        }

                    }
                }


                cracker.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        String cnt = cracker.getText().toString();

                        if(cnt.equals("")){
                            cnt="0";
                        }

                        cracker_cnt=Integer.parseInt(cnt);

                        if(cracker_cnt==1){

                            image.setImageResource(R.drawable.glass1);
                            gridval1.setText(cracker_cnt+"/"+(weight_val/2));

                        }else if(cracker_cnt==2){
                            image.setImageResource(R.drawable.glass2);
                            gridval1.setText(cracker_cnt+"/"+(weight_val/2));
                        }else if(cracker_cnt==3){
                            image.setImageResource(R.drawable.glass3);
                            gridval1.setText(cracker_cnt+"/"+(weight_val/2));

                        }else if(cracker_cnt==4){
                            image.setImageResource(R.drawable.glass4);
                            gridval1.setText(cracker_cnt+"/"+(weight_val/2));

                        }else if(cracker_cnt==5){
                            image.setImageResource(R.drawable.glass5);
                            gridval1.setText(cracker_cnt+"/"+(weight_val/2));

                        }else if(cracker_cnt==6){
                            image.setImageResource(R.drawable.glass6);
                            gridval1.setText(cracker_cnt+"/"+(weight_val/2));

                        }else if(cracker_cnt==7){
                            image.setImageResource(R.drawable.glass7);
                            gridval1.setText(cracker_cnt+"/"+(weight_val/2));

                        }else if(cracker_cnt==8){
                            image.setImageResource(R.drawable.glass8);
                            gridval1.setText(cracker_cnt+"/"+(weight_val/2));

                        }else{
                            image.setImageResource(R.drawable.glassofwater);
                            gridval1.setText(cracker_cnt+"/"+(weight_val/2));


                        }

                        Date today = new Date();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                        String dateToStr = format.format(today);



                        try {

                            Params.put("water_cnt", cracker_cnt);
                            Params.put("name","bill");
                            Params.put("section","water");
                            Params.put("date", dateToStr);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                break;

            case 7: //supplements

                cracker.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);

                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);
                spinner_cupsize1.setVisibility(View.GONE);
                spinner_veggies1.setVisibility(View.GONE);
                spinner_cupsize2.setVisibility(View.GONE);
                spinner_veggies2.setVisibility(View.GONE);

                switch1.setVisibility(View.GONE);
                switch2.setVisibility(View.GONE);
                switch3.setVisibility(View.GONE);
                switch4.setVisibility(View.GONE);

                text_val1.setVisibility(View.GONE);
                text_val2.setVisibility(View.GONE);
                text_val3.setVisibility(View.GONE);


                btn3.setVisibility(View.GONE);

                break;
            case 8: //Meditation

                intent= new Intent(GridViewActivity.this, Meditation.class);
                startActivity(intent);


                break;
            case 9: //Weight

                Params =new JSONObject();


                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);
                spinner_cupsize1.setVisibility(View.GONE);
                spinner_veggies1.setVisibility(View.GONE);
                spinner_cupsize2.setVisibility(View.GONE);
                spinner_veggies2.setVisibility(View.GONE);

                switch1.setVisibility(View.GONE);
                switch2.setVisibility(View.GONE);
                switch3.setVisibility(View.GONE);
                switch4.setVisibility(View.GONE);


                btn3.setVisibility(View.GONE);
                text_val1.setVisibility(View.GONE);
                text_val2.setVisibility(View.GONE);
                text_val3.setVisibility(View.GONE);

                int weight_val=0;

                res_weight=myDb.getWeightData();

                if(res_weight.getCount()!=0){

                    while (res_weight.moveToNext()) {

                        weight_val= Integer.parseInt(res_weight.getString(3));

                        cracker_cnt=weight_val;

                        gridval1.setText("Weight Today : "+cracker_cnt+" Lbs");


                    }
                }


                        cracker.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        String cnt = cracker.getText().toString();

                        if(cnt.equals("")){
                            cnt="0";
                        }

                        cracker_cnt=Integer.parseInt(cnt);


                        gridval1.setText("Weight Today : "+cracker_cnt+" Lbs");


                        Date today = new Date();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                        String dateToStr = format.format(today);



                        try {

                            Params.put("weight_val", cracker_cnt);
                            Params.put("name","bill");
                            Params.put("section","weight");
                            Params.put("date", dateToStr);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                break;

            case 10: //Exercise

                Params =new JSONObject();


                cracker.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);

                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);
                spinner_cupsize1.setVisibility(View.GONE);
                spinner_veggies1.setVisibility(View.GONE);
                spinner_cupsize2.setVisibility(View.GONE);
                spinner_veggies2.setVisibility(View.GONE);

                switch1.setVisibility(View.GONE);
                switch2.setVisibility(View.GONE);
                switch3.setVisibility(View.GONE);

                text_val1.setVisibility(View.GONE);
                text_val2.setVisibility(View.GONE);
                text_val3.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);


                switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if(isChecked){
                            exer_val=1;

                            image.setImageResource(R.drawable.exercise1);
                            gridval1.setText("Yes");

                        }else{
                            exer_val=0;
                            image.setImageResource(R.drawable.exercise);
                            gridval1.setText("No");

                        }


                        Date today = new Date();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                        String dateToStr = format.format(today);

                        try {

                            Params.put("exercise_val", exer_val);
                            Params.put("name","bill");
                            Params.put("section","exercise");
                            Params.put("date", dateToStr);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });



                break;

            case 11: //Bowel Movements

                Params =new JSONObject();

                cracker.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);

                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);
                spinner_cupsize1.setVisibility(View.GONE);
                spinner_veggies1.setVisibility(View.GONE);
                spinner_cupsize2.setVisibility(View.GONE);
                spinner_veggies2.setVisibility(View.GONE);

                switch1.setVisibility(View.GONE);
                switch2.setVisibility(View.GONE);
                switch3.setVisibility(View.GONE);


                text_val1.setVisibility(View.GONE);
                text_val2.setVisibility(View.GONE);
                text_val3.setVisibility(View.GONE);


                btn3.setVisibility(View.GONE);


                switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if(isChecked){
                            bowel_val=1;

                            image.setImageResource(R.drawable.man_at_restroom1);
                            gridval1.setText("Yes");

                        }else{
                            bowel_val=0;
                            image.setImageResource(R.drawable.man_at_restroom);
                            gridval1.setText("No");

                        }


                        Date today = new Date();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                        String dateToStr = format.format(today);

                        try {

                            Params.put("bowel_val", bowel_val);
                            Params.put("name","bill");
                            Params.put("section","bowel");
                            Params.put("date", dateToStr);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });



                break;


            case 12: //TeaCoffee

                Params =new JSONObject();


                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);
                spinner_cupsize1.setVisibility(View.GONE);
                spinner_veggies1.setVisibility(View.GONE);
                spinner_cupsize2.setVisibility(View.GONE);
                spinner_veggies2.setVisibility(View.GONE);

                switch1.setVisibility(View.GONE);
                switch2.setVisibility(View.GONE);
                switch3.setVisibility(View.GONE);
                switch4.setVisibility(View.GONE);


                btn3.setVisibility(View.GONE);
                text_val1.setVisibility(View.GONE);
                text_val2.setVisibility(View.GONE);
                text_val3.setVisibility(View.GONE);

                int tea_val=0;

                Cursor res_teacof=myDb.getTeaData();

                if(res_teacof.getCount()!=0){

                    while (res_teacof.moveToNext()) {

                        tea_val= Integer.parseInt(res_teacof.getString(3));

                        cracker_cnt=tea_val;

                        gridval1.setText("Tea/Cofee : "+cracker_cnt+" Oz");


                    }
                }


                cracker.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        String cnt = cracker.getText().toString();

                        if(cnt.equals("")){
                            cnt="0";
                        }

                        cracker_cnt=Integer.parseInt(cnt);

                        gridval1.setText("Tea/Coffee : "+cracker_cnt+" Oz");

                        Date today = new Date();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                        String dateToStr = format.format(today);

                        try {

                            Params.put("tea_val", cracker_cnt);
                            Params.put("name","bill");
                            Params.put("section","tea");
                            Params.put("date", dateToStr);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                break;

        }


    }



    @SuppressLint("StaticFieldLeak")
    public class SendPostRequest extends AsyncTask<String, Void, String> {

        URL url1;
        JSONObject postDataParams;

        SendPostRequest(JSONObject params, URL url) throws MalformedURLException, JSONException {

            postDataParams=params;
            url1=url;


        }


        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {


                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {


                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuilder sb = new StringBuilder("");
                    String line="";

                    while ((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return "false : " + responseCode;
                }
            }
            catch(Exception e){
                return "Exception: " + e.getMessage();
            }

        }


        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();
        }

    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }


}
