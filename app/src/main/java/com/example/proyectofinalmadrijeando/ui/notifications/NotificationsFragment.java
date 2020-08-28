package com.example.proyectofinalmadrijeando.ui.notifications;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.proyectofinalmadrijeando.R;
import com.example.proyectofinalmadrijeando.ui.Manager.UsuariosManager;
import com.example.proyectofinalmadrijeando.ui.Utils.StreamHelper;
import com.example.proyectofinalmadrijeando.ui.entities.Usuario;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.StreamHandler;

public class NotificationsFragment extends Fragment {
    Button btnTest;
    TextView Salida;
    EditText edid;
    TextView textView;
    View root;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        root = inflater.inflate(R.layout.fragment_notifications, container, false);
        ObtenerReferencias();

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });


        SetearListeners();
        return root;
    }

    private  void SetearListeners(){
        btnTest.setOnClickListener(btnTest_Click);
    }
    private void ObtenerReferencias()
    {
        textView = root.findViewById(R.id.txtred);
        btnTest = (Button) root.findViewById(R.id.btnNoti);
        Salida =(TextView)root.findViewById(R.id.TxtNoti);
        edid =(EditText)root.findViewById(R.id.edText);
    }


    private View.OnClickListener btnTest_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int intId;
            Log.d("Eze", "onClick");
            Personas_ObtenerPorIdTask miTarea = new Personas_ObtenerPorIdTask();
            intId = Integer.valueOf(edid.getText().toString());
            miTarea.setId(intId);
            miTarea.execute();
            Log.d("Eze", "Fin onClick");

        }
    };

    private class Personas_ObtenerPorIdTask extends AsyncTask<String,Void,String>{
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
                //startAPIUrl = new URL("http://localhost:61247//api/usuario/1");

                strAPIUrl = new URL("http://10.0.2.2:61247/api/usuario" + String.valueOf(this.getId()));
                //startAPIUrl = new URL("http://localhost:61247//api/usuario")
                miconexion = (HttpURLConnection) strAPIUrl.openConnection();
                miconexion.setRequestMethod("GET");
                intResponseCode = miconexion.getResponseCode();

                switch (intResponseCode) {
                    case 200:
                        strResultado = StreamHelper.GetFullStringFromInputReader(miconexion.getInputStream());
                        break;
                    case 400:
                        strResultado=null;
                        break;
                    case 404:
                        strResultado= "";
                        break;
                }
            }catch (Exception e){
                Log.d("EZE",e.getMessage());
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
            Log.d("EZE","OnPostExecute");
            ParseandoJSONObtenerporid(resultado);
        }
    }

    private void ParseandoJSONObtenerporid(String resultado) {
        Usuario usu;
        usu = UsuariosManager.convertPersonaToJSONString(resultado);
        Log.d("Eze",usu.Nombre);
    }


}
