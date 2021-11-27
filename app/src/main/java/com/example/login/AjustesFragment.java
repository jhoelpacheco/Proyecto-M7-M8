package com.example.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class AjustesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ajustes, container, false);

        Button cerrar = view.findViewById(R.id.btn_cerrar_sesion);
        Button cambiarIdioma = view.findViewById(R.id.btn_cambiar_idioma);

        //sharedPreference that saves the login credentials
        SharedPreferences prefs = getContext().getSharedPreferences("login_preferencia", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        //button that clears the login preference
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear().commit();
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        cambiarIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //shows an alertDialog that gives you the option to change
                //and save the application language through a sharedPreferences
                dialogChangeLang();
            }
        });
        return view;
    }

    private void dialogChangeLang() {

        final String[] listItem = {"English", "Spanish", "Catalan"};

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    setLocate("en");
                    recreate();
                }else if (i == 1) {
                    setLocate("es");
                    recreate();
                }else if (i == 2) {
                    setLocate("ca");
                    recreate();
                }
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    public void setLocate(String lang) {
        //save data to shared preferences
        SharedPreferences preferences = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Slang", lang);
        editor.apply();
    }
    public void recreate(){
        Intent intent = new Intent(getContext(),MainActivity.class);
        startActivity(intent);
    }

}