package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    EditText txtUsername;
    EditText txtPassword;
    Button btnLogin;
    CheckBox check_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //option to lock screen orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        //hide the action bar, because it doesn't look good
        getSupportActionBar().hide();

        //the text fields where the user will be entered and password plus the login button
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        check_login = findViewById(R.id.checkBox_login);

        //toast. which shows a message if the login is correct or not
        CharSequence text_error = "Error. Usuario o contraseña incorrecto!";
        Toast toast_error_login = Toast.makeText(this, text_error, Toast.LENGTH_SHORT);
        CharSequence text_correcto = "Login correcto!";
        Toast toast_login_correcto = Toast.makeText(this, text_correcto, Toast.LENGTH_SHORT);

        SharedPreferences prefs= getSharedPreferences("SharedP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if(prefs.getBoolean("login", false) == true){
            startActivity(new Intent(getApplicationContext(), Menu.class));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(txtUsername.getText().toString().equals("admin")&&
                        txtPassword.getText().toString().equals("admin")){
                    toast_login_correcto.show();
                    Log.i("Test", "Login correcte");

                    if(check_login.isChecked()){
                        editor.putBoolean("login", true).commit();
                    }

                    //if the condition is fulfilled we go to the menu screen
                    startActivity(new Intent(getApplicationContext(), Menu.class));

                }else{
                    toast_error_login.show();
                    Log.i("Test", "Login incorrecte");
                }
                //option that hides the mobile keyboard when clicking the login button
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(btnLogin.getWindowToken(), 0);
            }
        });

        //splashscreen, faulty because it won't move
        SystemClock.sleep(700);
        setTheme(R.style.Theme_Login);
    }
}