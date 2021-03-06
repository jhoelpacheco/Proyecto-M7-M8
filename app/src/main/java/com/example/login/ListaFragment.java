package com.example.login;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.login.DB.HeroesDBHelper;
import com.example.login.Model.Hero;

import java.util.ArrayList;


public class ListaFragment extends Fragment {

    //Create the instance of dbHelper and db
    private HeroesDBHelper dbHelper;
    private SQLiteDatabase db;
    Bundle bundle = new Bundle();

    public ListaFragment(){}

    public ListaFragment(HeroesDBHelper dbHelper, SQLiteDatabase db){
        this.dbHelper = dbHelper;
        this.db = db;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista, container, false);

        //hero arraylist that receives all existing heroes in the database
        ArrayList<Hero> arrayHeroes = dbHelper.getAllData(db);

        for (int i=0; i<arrayHeroes.size();i++){
            bundle.putSerializable("Hero", arrayHeroes.get(i));
        }
        //Bundle incompleto


        //a list with all the heroes is displayed
        RecyclerView recyclerView = view.findViewById(R.id.reciclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(arrayHeroes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;

    }
}