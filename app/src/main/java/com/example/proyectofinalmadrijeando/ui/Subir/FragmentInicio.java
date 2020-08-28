package com.example.proyectofinalmadrijeando.ui.Subir;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.proyectofinalmadrijeando.R;
import com.example.proyectofinalmadrijeando.ui.Manager.UsuariosManager;
import com.example.proyectofinalmadrijeando.ui.Subir.SubirViewModel;
import com.example.proyectofinalmadrijeando.ui.Utils.StreamHelper;
import com.example.proyectofinalmadrijeando.ui.entities.Usuario;

import java.net.HttpURLConnection;
import java.net.URL;

public class  FragmentInicio extends Fragment
{
    private SubirViewModel SubirViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("Eze", "Inicio");
        SubirViewModel =
                ViewModelProviders.of(this).get(SubirViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inicio, container, false);
        final TextView textView = root.findViewById(R.id.txtSubir);
        SubirViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        LlamarALaAPI();

        return root;

    }

    private void LlamarALaAPI() {
        int intId;
        Log.d("Eze", "onClick");
        Personas_ObtenerPorIdTask miTarea = new Personas_ObtenerPorIdTask();
        miTarea.setId(3);
        miTarea.execute();
        Log.d("Eze", "Fin onClick");
    }

    private class Personas_ObtenerPorIdTask extends AsyncTask<String,Void,String> {
        private int Id = 0;
        public int getId() {return this.Id;}

        public void setId(int id) { this.Id= id;}

        @Override
        protected void onPreExecute(){super.onPreExecute();}

        @Override
        protected String doInBackground(String... parametros){
            HttpURLConnection miconexion = null;
            URL strAPIUrl;
            int intResponseCode;
            String strResultado="";

            try {
                Log.d("Eze","doInBackground");
                //startAPIUrl = new URL("http://localhost:61247//api/usuario/1");

                strAPIUrl = new URL("http://192.168.0.15:61247/api/usuario/3" );
                //startAPIUrl = new URL("http://localhost:61247//api/usuario")
                miconexion = (HttpURLConnection) strAPIUrl.openConnection();
                Log.d("Eze","strAPIUrl es:¨" +strAPIUrl.toString());
                miconexion.setRequestMethod("GET");
                intResponseCode = miconexion.getResponseCode();
                Log.d("Eze","ResponseCode es:¨" + String.valueOf(intResponseCode));
                switch (intResponseCode) {
                    case 200:
                        strResultado = StreamHelper.GetFullStringFromInputReader(miconexion.getInputStream());
                        Log.d("Restultado",strResultado);
                        break;
                    case 400:
                        strResultado=null;
                        break;
                    case 404:
                        strResultado= "";
                        break;
                }
            }catch (Exception e){
                Log.d("Eze",e.getMessage());
            }
            finally {
                if (miconexion != null){
                    miconexion.disconnect();
                }
            }
            return strResultado;
        }
        //@Override
        protected void OnPostExecute(String resultado){
            super.onPostExecute(resultado);
            Log.d("Eze","OnPostExecute");
            ParseandoJSONObtenerporid(resultado);
        }
    }

    private void ParseandoJSONObtenerporid(String resultado) {
        Usuario usu;
        usu = UsuariosManager.convertPersonaToJSONString(resultado);
        Log.d("Eze",usu.Nombre);
    }
}

