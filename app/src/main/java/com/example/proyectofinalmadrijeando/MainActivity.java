package com.example.proyectofinalmadrijeando;

import android.os.Bundle;

import com.example.proyectofinalmadrijeando.ui.AdapterDatos;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> ListDatos;
    RecyclerView Recicler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        Recicler=findViewById(R.id.ReciclerId);
        Recicler.setLayoutManager(new LinearLayoutManager(this ));
        ListDatos=new ArrayList<String>();
        for(int i=0; i<=10;i++){
            ListDatos.add("Dato #" + i + "Dato");
        }
        //
        AdapterDatos Adapter = new AdapterDatos(ListDatos);
        Recicler.setAdapter(Adapter);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}
