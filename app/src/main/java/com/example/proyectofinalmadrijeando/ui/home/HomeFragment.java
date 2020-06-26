package com.example.proyectofinalmadrijeando.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinalmadrijeando.R;
import com.example.proyectofinalmadrijeando.ui.AdapterDatos;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private ArrayList<String> ListDatos;
    private RecyclerView Recicler;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.txtMateriales);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

    }

    @Override
    public void onStart() {
        super.onStart();

        Recicler=getActivity().findViewById(R.id.ReciclerId);
        Recicler.setLayoutManager(new LinearLayoutManager(getContext() ));
        ListDatos=new ArrayList<String>();
        for(int i=0; i<=10;i++){
            ListDatos.add("Dato #" + i + "Dato");
        }
        AdapterDatos Adapter = new AdapterDatos(ListDatos);
        Recicler.setAdapter(Adapter);
    }
}
