package com.example.bebalanced.bebalanced;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class GridViewActivity extends AppCompatActivity {
    TextView name,gridval1,gridval2;
    String drp_down_val;
    ImageView image;
    EditText cracker;
    Spinner spinner,spinner1;
    int drp_val,drp_val1=1,drp_val2;
    ArrayAdapter<CharSequence> adapter,adapter1;
    Button btn1, btn2, btn3, btn4, btn5,btn6;
    int cnt_apple=0;
    JSONObject Params;

    //String spinnerval1="",spinnerval2="";
    URL url;

    @Override
    public void onBackPressed() {

        Toast.makeText(getApplicationContext(), "onback",
                Toast.LENGTH_LONG).show();

        try {
            SendPostRequest sd=new SendPostRequest(Params, url);
            sd.execute();
            Toast.makeText(getApplicationContext(), "onback",
                    Toast.LENGTH_LONG).show();

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
        btn1=findViewById(R.id.button);
        btn2=findViewById(R.id.button2);
        btn3=findViewById(R.id.add);
        btn4=findViewById(R.id.pls);
        btn5=findViewById(R.id.mns);



        cracker=findViewById(R.id.cracker);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        spinner=(Spinner)findViewById(R.id.spinner);


        Intent intent= getIntent();
        name.setText(intent.getStringExtra("fruits"));
        image.setImageResource(intent.getIntExtra("images", 0));
        gridval1.setText(intent.getStringExtra("gridvalue"));
        gridval2.setText(intent.getStringExtra("gridvalue1"));
        drp_down_val=intent.getStringExtra("drop_down_val");
        drp_val = Integer.parseInt(drp_down_val);
        final Context context = getApplicationContext();

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
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);


                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String fruits1 =parent.getItemAtPosition(position).toString();


                        if(position!=0 && cnt_apple<2){

                            cnt_apple=cnt_apple+1;

                        }else if(position==0 && cnt_apple<=2 && cnt_apple>0){
                            cnt_apple = cnt_apple - 1;

                        }
                        else if(cnt_apple!=0 && cnt_apple<2){

                                cnt_apple = cnt_apple - 1;

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

                        if(position!=0 && cnt_apple<2){

                            cnt_apple=cnt_apple+1;

                        }
                        else if(position==0 && cnt_apple<=2 && cnt_apple>0){
                            cnt_apple = cnt_apple - 1;

                        }
                        else if(cnt_apple!=0 && cnt_apple<2){

                            cnt_apple = cnt_apple - 1;


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

                    Params.put("date", dateToStr);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;
            case 2: //meats
                drp_val1=R.array.veggies_name;
                adapter=ArrayAdapter.createFromResource(this, drp_val1, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner1.setAdapter(adapter);
                cracker.setVisibility(View.GONE);
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);

                break;
            case 3: //veggies
                drp_val1=R.array.meat_name;
                adapter=ArrayAdapter.createFromResource(this, drp_val1, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner1.setAdapter(adapter);
                cracker.setVisibility(View.GONE);
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);

                break;
            case 4: //veggies
                btn2.setVisibility(View.GONE);
                btn1.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);

                break;
            case 5: //veggies

                btn2.setVisibility(View.GONE);
                btn1.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);

                break;
            case 6: //veggies
                cracker.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);

                break;
            case 7: //veggies
                cracker.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);

                break;
            case 8: //veggies
                cracker.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);

                break;
            case 9: //veggies
                cracker.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);

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
