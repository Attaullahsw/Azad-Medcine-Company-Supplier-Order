package com.example.mtci.azadmedicinecompany;

import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class LoginActivity extends AppCompatActivity {

    //View Reference Variables
    EditText edt_userName, edt_password;
    Button btn_login;
    TextView txterror;
    CheckBox checkBox;
    String user;
    String pass;

    SqliteDataBase sqliteDataBase;

    //For remember user and pass refernce variable
    SharedPreferences logPre;
    SharedPreferences.Editor preEditor;

    //Action bar refernce variable
    ActionBar actionBar;
    RelativeLayout relativeLayout;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            relativeLayout.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //        Action bar
        actionBar = getSupportActionBar();
        actionBar.hide();

        //StartUp Page Icon For 2 Second
        handler.postDelayed(runnable,2000);
        relativeLayout = (RelativeLayout)findViewById(R.id.r_log);

        checkBox = (CheckBox)findViewById(R.id.chb_remember);
        txterror = (TextView) findViewById(R.id.txt_errorMessage);


        logPre = getSharedPreferences("Login", MODE_PRIVATE);
        preEditor = logPre.edit();

        sqliteDataBase = new SqliteDataBase(this);


        rememberLogin();

        //View initaillize reference variables
        edt_userName = (EditText) findViewById(R.id.edt_userName);
        edt_password = (EditText) findViewById(R.id.edt_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        //Authuntication Function
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = edt_userName.getText().toString();
                pass = edt_password.getText().toString();
                if(user.isEmpty()){
                    edt_userName.requestFocus();
                    txterror.setText("Please Enter User Name");
                }else if(pass.isEmpty()){
                    edt_password.requestFocus();
                    txterror.setText("Please Enter Password");
                }else if (sqliteDataBase.checkDetail(user,pass)) {
                    if(checkBox.isChecked()){
                        preEditor.putString("user", user);
                        preEditor.putString("pass", pass);
                        preEditor.apply();
                        preEditor.commit();
                    }
                    finish();
                    Intent intent = new Intent(LoginActivity.this, ChooseArea.class);
                    startActivity(intent);


                }else {
                    txterror.setText("Invalid User Name Or Password...");
//                    ObjectAnimator
//                            .ofFloat(txterror, "translationX", 0, 25, -25, 25, -25,15, -15, 6, -6, 0)
//                            .setDuration(1000)
//                            .start();

                }
                ObjectAnimator
                        .ofFloat(txterror, "translationX", 0, 25, -25, 25, -25,15, -15, 6, -6, 0)
                        .setDuration(1000)
                        .start();

            }
        });


    }

    // Remeber user and skip get login data
    public void rememberLogin(){
        sqliteDataBase = new SqliteDataBase(this);
        String tempUser = logPre.getString("user", "");
        String tempPass = logPre.getString("pass", "");
        if (!tempUser.isEmpty() && !tempPass.isEmpty()) {
            if (sqliteDataBase.checkDetail(tempUser,tempPass)) {
                finish();
                Intent intent = new Intent(LoginActivity.this, ChooseArea.class);
                startActivity(intent);

            } else {

                preEditor.remove("user");
                preEditor.remove("pass");
                preEditor.commit();
                preEditor.apply();
            }
        }
    }
}