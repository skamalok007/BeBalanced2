package com.example.bebalanced.bebalanced;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText UserName;
    private EditText Password;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserName=(EditText)findViewById(R.id.username);
        Password=(EditText)findViewById(R.id.password);
        Button login1 = (Button) findViewById(R.id.lg_btn);
        sp=getSharedPreferences("Login", MODE_PRIVATE);
        if(sp.getBoolean("logged", false)){

            Intent intent= new Intent(Login.this, HomeActivity.class);
            startActivity(intent);
        }


        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(UserName.getText().toString(),Password.getText().toString());
            }
        });



    }


    private void validate(String username, String Password){

        Context context = getApplicationContext();


        if((username.equals("bill")) && (Password.equals("12345"))){

            sp.edit().putBoolean("logged", true).apply();

            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, username, duration);
            toast.show();

            Intent intent= new Intent(Login.this, HomeActivity.class);
            startActivity(intent);
        }else{
            sp.edit().putBoolean("logged", false).apply();

            String text1 = "Username or Password Incorrect";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text1, duration);
            toast.show();


        }


    }


}
