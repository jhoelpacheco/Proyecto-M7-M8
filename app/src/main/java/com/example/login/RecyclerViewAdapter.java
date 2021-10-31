package com.example.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Model.Hero;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Hero> arrayHeroes;

    public RecyclerViewAdapter(ArrayList<Hero> arrN){
        arrayHeroes = arrN;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nom_item.setText(arrayHeroes.get(position).getNom());
        holder.rol_item.setText(arrayHeroes.get(position).getRol());
        holder.sub_rol_item.setText(arrayHeroes.get(position).getSub_rol());
    }

    @Override
    public int getItemCount() {
        return arrayHeroes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nom_item, rol_item, sub_rol_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nom_item = itemView.findViewById(R.id.nom_item_list);
            rol_item = itemView.findViewById(R.id.rol_item_list);
            sub_rol_item = itemView.findViewById(R.id.sub_rol_item_list);
        }
    }
}
