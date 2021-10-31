package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //option to lock screen orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        //hide the action bar, because it doesn't look good
        getSupportActionBar().hide();

        //the text fields where the user will be entered and password plus the login button
        EditText txtUsername = findViewById(R.id.txtUsername);
        EditText txtPassword = findViewById(R.id.txtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        //toast que muestra un mensaje si el login es correcto o no
        Context context = this;
        int duration = Toast.LENGTH_SHORT;

        CharSequence text_error = "Error. Usuario o contraseña incorrecto!";
        Toast toast_error_login = Toast.makeText(context, text_error, duration);

        CharSequence text_correcto = "Login correcto!";
        Toast toast_login_correcto = Toast.makeText(context, text_correcto, duration);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(txtUsername.getText().toString().equals("admin")&&
                        txtPassword.getText().toString().equals("admin")){
                    toast_login_correcto.show();
                    Log.i("Test", "Login correcte");
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

        SystemClock.sleep(700);
        setTheme(R.style.Theme_Login);
    }
}