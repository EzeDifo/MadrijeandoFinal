package com.example.proyectofinalmadrijeando.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinalmadrijeando.R;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

     private ArrayList<String> ListDatos;

    public AdapterDatos(ArrayList<String> listDatos) {
        ListDatos = listDatos;
    }

    @NonNull
     @Override
     public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist,null,false);
         return new ViewHolderDatos(view);
     }

     @Override
     public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
holder.asignardatos(ListDatos.get(position));
     }

     @Override
     public int getItemCount() {
         return ListDatos.size();
     }

     public static class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato;
         public ViewHolderDatos(@NonNull View itemView) {
             super(itemView);
dato=itemView.findViewById(R.id.iddato);
         }

         public void asignardatos(String datos) {
             dato.setText(dato.getText());
         }
     }

 }
