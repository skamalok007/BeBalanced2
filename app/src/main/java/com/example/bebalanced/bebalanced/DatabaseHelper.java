package com.example.bebalanced.bebalanced;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME= "bebalanced.db";

    public static final String col_1   = "name";
    public static final String col_2   = "userid";

    public static final String col_5  = "date";

    private SQLiteDatabase db= this.getWritableDatabase();


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null , 12);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table fruits (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, userid TEXT, fruits1 TEXT, fruits2 TEXT,date DATE)");
        db.execSQL("create table veggies (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, userid TEXT, veggies1 TEXT,cupsize1 TEXT, veggies2 TEXT,cupzize2 TEXT,date DATE)");
        db.execSQL("create table protein (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, userid TEXT, protein1 TEXT, protein2 TEXT,date DATE)");
        db.execSQL("create table cracker (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, userid TEXT, cracker_cnt INT,date DATE)");
        db.execSQL("create table drop_dt (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, userid TEXT, mrng INT,afternoon INT,evening INT,date DATE)");
        db.execSQL("create table water_data (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, userid TEXT, water_cnt INT,date DATE)");
        db.execSQL("create table weight_data (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, userid TEXT, weight INT,date DATE)");
        db.execSQL("create table exercise (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, userid TEXT, exercise_val INT,date DATE)");
        db.execSQL("create table bowel (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, userid TEXT, bowel_val INT,date DATE)");
        db.execSQL("create table tea_data (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, userid TEXT, tea INT,date DATE)");
        db.execSQL("create table user_log (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, userid TEXT, log_data TEXT, date DATE)");







    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS fruits");
        db.execSQL("DROP TABLE IF EXISTS veggies");
        db.execSQL("DROP TABLE IF EXISTS protein");
        db.execSQL("DROP TABLE IF EXISTS cracker");
        db.execSQL("DROP TABLE IF EXISTS drop_dt");
        db.execSQL("DROP TABLE IF EXISTS water_data");
        db.execSQL("DROP TABLE IF EXISTS weight_data");
        db.execSQL("DROP TABLE IF EXISTS exercise");
        db.execSQL("DROP TABLE IF EXISTS bowel");
        db.execSQL("DROP TABLE IF EXISTS tea_data");

        onCreate(db);


    }


    public boolean insertFruits(JSONObject params) throws JSONException {
        SQLiteDatabase db= this.getWritableDatabase();

        String col_3  = "fruits1";
        String col_4   = "fruits2";


        String user= params.getString("name");
        String fruits1= params.getString("fruits1");
        String fruits2= params.getString("fruits2");
        String date= params.getString("date");



        ContentValues contentValues= new ContentValues();
        contentValues.put(col_2,user);
        contentValues.put(col_3,fruits1);
        contentValues.put(col_4,fruits2);
        contentValues.put(col_5,date);

        long result= db.insert("fruits", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }


    }


    public Cursor getFruitsData(){
        SQLiteDatabase db= this.getWritableDatabase();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        String strDate = mdformat.format(calendar.getTime());

        String Query="select * from fruits where date like  '%"+strDate+"%' order by id desc limit 1";
        Cursor res= db.rawQuery(Query, null);

        return res;

    }


    public boolean insertVeggies(JSONObject params) throws JSONException {
        SQLiteDatabase db= this.getWritableDatabase();

        String col_3  = "veggies1";
        String col_4   = "cupsize1";
        String col_6  = "veggies2";
        String col_7   = "cupsize2";


        String user= params.getString("name");
        String veggies1= params.getString("veggies1");
        String cupsize1= params.getString("cupsize1");
        String veggies2= params.getString("veggies2");
        String cupsize2= params.getString("cupsize2");
        String date= params.getString("date");



        ContentValues contentValues= new ContentValues();
        contentValues.put(col_2,user);
        contentValues.put(col_3,veggies1);
        contentValues.put(col_4,veggies1);
        contentValues.put(col_6,veggies2);
        contentValues.put(col_7,veggies1);
        contentValues.put(col_5,date);

        long result= db.insert("veggies", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }


    }


    public Cursor getVeggiesData(){
        SQLiteDatabase db= this.getWritableDatabase();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        String strDate = mdformat.format(calendar.getTime());

        String Query="select * from veggies where date like  '%"+strDate+"%' order by id desc limit 1";
        Cursor res= db.rawQuery(Query, null);

        return res;

    }




    //Protein Insert

    public boolean insertProteins(JSONObject params) throws JSONException {
        SQLiteDatabase db= this.getWritableDatabase();

        String col_3  = "protein1";
        String col_4   = "protein2";


        String user= params.getString("name");
        String protein1= params.getString("protein1");
        String protein2= params.getString("protein2");
        String date= params.getString("date");



        ContentValues contentValues= new ContentValues();
        contentValues.put(col_2,user);
        contentValues.put(col_3,protein1);
        contentValues.put(col_4,protein2);
        contentValues.put(col_5,date);

        long result= db.insert("protein", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }


    }


    public Cursor getProteinsData(){
        SQLiteDatabase db= this.getWritableDatabase();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        String strDate = mdformat.format(calendar.getTime());

        String Query="select * from protein where date like  '%"+strDate+"%' order by id desc limit 1";
        Cursor res= db.rawQuery(Query, null);

        return res;

    }

    public boolean insertCrackers(JSONObject params) throws JSONException {
        SQLiteDatabase db= this.getWritableDatabase();

        String col_3  = "cracker_cnt";



        String user= params.getString("name");
        String cracker_cnt= params.getString("cracker_cnt");
        String date= params.getString("date");


        ContentValues contentValues= new ContentValues();
        contentValues.put(col_2,user);
        contentValues.put(col_3,cracker_cnt);

        contentValues.put(col_5,date);

        long result= db.insert("cracker", null, contentValues);

        if(result==-1){
            return false;
        }else{
            return true;
        }


    }


    public Cursor getCrackersData(){

        SQLiteDatabase db= this.getWritableDatabase();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        String strDate = mdformat.format(calendar.getTime());

        String Query="select * from cracker where date like  '%"+strDate+"%' order by id desc limit 1";
        Cursor res= db.rawQuery(Query, null);

        return res;

    }

    public boolean insertDrops(JSONObject params) throws JSONException {
        SQLiteDatabase db= this.getWritableDatabase();

        String col_3  = "mrng";
        String col_4  = "afternoon";
        String col_6  = "evening";



        String user= params.getString("name");
        String mrng= params.getString("mrng");
        String after= params.getString("after");
        String evening= params.getString("evening");
        String date= params.getString("date");


        ContentValues contentValues= new ContentValues();
        contentValues.put(col_2,user);
        contentValues.put(col_3,mrng);
        contentValues.put(col_4,after);
        contentValues.put(col_6,evening);

        contentValues.put(col_5,date);

        long result= db.insert("drop_dt", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }


    }


    public Cursor getDropsData(){

        SQLiteDatabase db= this.getWritableDatabase();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        String strDate = mdformat.format(calendar.getTime());

        String Query="select * from drop_dt where date like  '%"+strDate+"%' order by id desc limit 1";
        Cursor res= db.rawQuery(Query, null);

        return res;

    }



    public boolean insertWater(JSONObject params) throws JSONException {
        SQLiteDatabase db= this.getWritableDatabase();

        String col_3  = "water_cnt";



        String user= params.getString("name");
        String water_cnt= params.getString("water_cnt");
        String date= params.getString("date");


        ContentValues contentValues= new ContentValues();
        contentValues.put(col_2,user);
        contentValues.put(col_3,water_cnt);
        contentValues.put(col_5,date);

        long result= db.insert("water_data", null, contentValues);

        if(result==-1){
            return false;
        }else{
            return true;
        }


    }


    public Cursor getWaterData(){

        SQLiteDatabase db= this.getWritableDatabase();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        String strDate = mdformat.format(calendar.getTime());

        String Query="select * from water_data where date like  '%"+strDate+"%' order by id desc limit 1";
        Cursor res= db.rawQuery(Query, null);

        return res;

    }


    public boolean insertWeight(JSONObject params) throws JSONException {
        SQLiteDatabase db= this.getWritableDatabase();

        String col_3  = "weight";



        String user= params.getString("name");
        String weight_val= params.getString("weight_val");
        String date= params.getString("date");


        ContentValues contentValues= new ContentValues();
        contentValues.put(col_2,user);
        contentValues.put(col_3,weight_val);
        contentValues.put(col_5,date);

        long result= db.insert("weight_data", null, contentValues);

        if(result==-1){
            return false;
        }else{
            return true;
        }


    }


    public Cursor getWeightData(){

        SQLiteDatabase db= this.getWritableDatabase();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        String strDate = mdformat.format(calendar.getTime());

        String Query="select * from weight_data where date like  '%"+strDate+"%' order by id desc limit 1";
        Cursor res= db.rawQuery(Query, null);

        return res;

    }


    public boolean insertExercise(JSONObject params) throws JSONException {
        SQLiteDatabase db= this.getWritableDatabase();

        String col_3  = "exercise_val";

        String user= params.getString("name");
        String exercise_val= params.getString("exercise_val");
        String date= params.getString("date");


        ContentValues contentValues= new ContentValues();
        contentValues.put(col_2,user);
        contentValues.put(col_3,exercise_val);
        contentValues.put(col_5,date);

        long result= db.insert("exercise", null, contentValues);

        if(result==-1){
            return false;
        }else{
            return true;
        }


    }


    public Cursor getExerciseData(){

        SQLiteDatabase db= this.getWritableDatabase();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        String strDate = mdformat.format(calendar.getTime());

        String Query="select * from exercise where date like  '%"+strDate+"%' order by id desc limit 1";
        Cursor res= db.rawQuery(Query, null);

        return res;

    }

    public boolean insertBowel(JSONObject params) throws JSONException {
        SQLiteDatabase db= this.getWritableDatabase();

        String col_3  = "bowel_val";

        String user= params.getString("name");
        String exercise_val= params.getString("bowel_val");
        String date= params.getString("date");


        ContentValues contentValues= new ContentValues();
        contentValues.put(col_2,user);
        contentValues.put(col_3,exercise_val);
        contentValues.put(col_5,date);

        long result= db.insert("bowel", null, contentValues);

        if(result==-1){
            return false;
        }else{
            return true;
        }


    }


    public Cursor getBowelData(){

        SQLiteDatabase db= this.getWritableDatabase();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        String strDate = mdformat.format(calendar.getTime());

        String Query="select * from bowel where date like  '%"+strDate+"%' order by id desc limit 1";
        Cursor res= db.rawQuery(Query, null);

        return res;

    }

    public boolean insertTea(JSONObject params) throws JSONException {
        SQLiteDatabase db= this.getWritableDatabase();

        String col_3  = "tea";



        String user= params.getString("name");
        String tea_val= params.getString("tea_val");
        String date= params.getString("date");


        ContentValues contentValues= new ContentValues();
        contentValues.put(col_2,user);
        contentValues.put(col_3,tea_val);
        contentValues.put(col_5,date);

        long result= db.insert("tea_data", null, contentValues);

        if(result==-1){
            return false;
        }else{
            return true;
        }


    }


    public Cursor getTeaData(){

        SQLiteDatabase db= this.getWritableDatabase();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        String strDate = mdformat.format(calendar.getTime());

        String Query="select * from tea_data where date like  '%"+strDate+"%' order by id desc limit 1";
        Cursor res= db.rawQuery(Query, null);

        return res;

    }

    public Cursor getGraphWeightData(){

        SQLiteDatabase db= this.getWritableDatabase();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String strDate = mdformat.format(calendar.getTime());

        String dateBefore =getCalculatedDate(strDate, "yyyy-MM-dd hh:mm:ss a", -14);

        String Query="select * from weight_data where date <='"+strDate+"' and date >='"+dateBefore+"'";

        Cursor res= db.rawQuery(Query, null);

        return res;

    }



    public static String getCalculatedDate(String date, String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return s.format(new Date(String.valueOf(cal.getTime())));

    }




    public boolean insertUserLog(JSONObject params) throws JSONException {
        SQLiteDatabase db= this.getWritableDatabase();

        String col_3  = "log_data";



        String user= params.getString("name");
        String log_data = "0";
        String date= params.getString("date");

        if(params.has("fruits1")){
            log_data="Fruits";
        }else if(params.has("veggies1")){
            log_data="Veggies";

        }else if(params.has("protein1")){
            log_data="Protein";

        }else if(params.has("cracker_cnt")){
            log_data="Cracker";

        }else if(params.has("mrng")){
            log_data="Drop";

        }else if(params.has("water_cnt")){
            log_data="Water";

        }else if(params.has("weight_val")){
            log_data="Weight";

        }else if(params.has("exercise_val")){
            log_data="Exercise";

        }else if(params.has("bowel_val")){
            log_data="Bowel";

        }else if(params.has("tea_val")){
            log_data="Tea";

        }



        ContentValues contentValues= new ContentValues();
        contentValues.put(col_2,user);

        contentValues.put(col_3,log_data);

        contentValues.put(col_5,date);

        long result= db.insert("user_log", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }


    }


    public Cursor getUserLog(){
        SQLiteDatabase db= this.getWritableDatabase();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        String strDate = mdformat.format(calendar.getTime());

        String Query="select * from user_log Group By date";
        Cursor res= db.rawQuery(Query, null);

        return res;

    }

}





