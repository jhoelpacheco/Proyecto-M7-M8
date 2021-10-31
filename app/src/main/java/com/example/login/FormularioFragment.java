package com.example.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.login.DB.HeroesDBHelper;
import com.example.login.Model.Hero;

public class FormularioFragment extends Fragment {

    //Create the instance of dbHelper
    private HeroesDBHelper dbHelper;
    private SQLiteDatabase db;

    public FormularioFragment(){}

    public FormularioFragment(HeroesDBHelper dbHelper, SQLiteDatabase db){
        this.dbHelper = dbHelper;
        this.db = db;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_formulario, container, false);

        EditText nombre_pj = view.findViewById(R.id.nombre_pj);
        Button btn_save = view.findViewById(R.id.btn_save);
        Button btn_delete = view.findViewById(R.id.btn_delete);

        ////////////////////////////////////////////////////////////////////
        Spinner spinner_roles = (Spinner) view.findViewById(R.id.roles);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.h_roles, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_roles.setAdapter(adapter);

        Spinner spinner_sub_roles = (Spinner) view.findViewById(R.id.sub_rol);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),
                R.array.h_sub_roles, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sub_roles.setAdapter(adapter1);

        //toast de error cuando el campo del nombre este vacio
        Context context = getContext();
        CharSequence text = "Error. campo de nombre del heroe vacio!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast_nombre_pj = Toast.makeText(context, text, duration);

        //AlertDialog de confirmacion para borrar toda la tabla

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Borrar todos los heroes");
        builder.setMessage("¿Estas seguro que quieres borrar todos los heroes de la lista?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dbHelper.delete();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //no hacer nada
                    }
                });
        AlertDialog dialog = builder.create();



        //botones
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if(nombre_pj.getText().toString().equals("")){
                    //si el campo del nombre del heroe esta vacio, solo se mostrara el toast
                    //y no se introducira nada
                    toast_nombre_pj.show();
                }else {
                    Hero h = new Hero(nombre_pj.getText().toString(),
                            spinner_roles.getSelectedItem().toString(),
                            spinner_sub_roles.getSelectedItem().toString());
                    dbHelper.insertHero(db, h);
                    nombre_pj.setText("");
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        return view;
    }
}