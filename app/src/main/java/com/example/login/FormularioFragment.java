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

    //Create the instance of dbHelper and db
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

        //text field where the hero's name is entered, spinners to save the role and sub-role, buttons to save new heroes and delete all heroes
        EditText nombre_pj = view.findViewById(R.id.nombre_pj);
        Spinner spinner_roles = (Spinner) view.findViewById(R.id.roles);
        Spinner spinner_sub_roles = (Spinner) view.findViewById(R.id.sub_rol);
        Button btn_save = view.findViewById(R.id.btn_save);
        Button btn_delete = view.findViewById(R.id.btn_delete);

        //adapters that is responsible for filling the spinners with arrays found in @values/roles.xml
        ArrayAdapter<CharSequence> adapter_rol = ArrayAdapter.createFromResource(getContext(),
                R.array.h_roles, android.R.layout.simple_spinner_item);
        adapter_rol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_roles.setAdapter(adapter_rol);

        ArrayAdapter<CharSequence> adapter_sub_rol = ArrayAdapter.createFromResource(getContext(),
                R.array.h_sub_roles, android.R.layout.simple_spinner_item);
        adapter_sub_rol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sub_roles.setAdapter(adapter_sub_rol);

        //Toast error. displays an error message when the name field is empty
        Context context = getContext();
        CharSequence text_error = "Error. campo nombre del heroe vacio!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast_nombre_pj = Toast.makeText(context, text_error, duration);

        //Toast
        CharSequence text_successful = "Se introducio el heroe nuevo correctamente!";
        Toast toast_nombre_pj_ok = Toast.makeText(context, text_successful, duration);


        //AlertDialog. Displays a message asking for a confirmation to remove all heroes from the database.
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Borrar todos los heroes");
        builder.setMessage("¿Estas seguro que quieres borrar todos los heroes de la lista?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    //when it is given to ok all the heroes are deleted
                    public void onClick(DialogInterface dialog, int id) {
                        dbHelper.delete();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //when canceled it just does nothing
                    }
                });
        AlertDialog dialog = builder.create();



        //save button
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre_pj.getText().toString().equals("")){
                    //If the hero's name field is empty, only the toast will be shown and nothing will be entered
                    toast_nombre_pj.show();
                }else {
                    //if text is entered, it is saved in the database as a new hero and the name field will be cleared
                    Hero h = new Hero(nombre_pj.getText().toString(),
                            spinner_roles.getSelectedItem().toString(),
                            spinner_sub_roles.getSelectedItem().toString());
                    dbHelper.insertHero(db, h);
                    nombre_pj.setText("");
                    //and it will also show a toast mentioning that the hero was entered correctly
                    toast_nombre_pj_ok.show();
                }
            }
        });
        //delete button. which calls the aforementioned alert dialog
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        return view;
    }
}