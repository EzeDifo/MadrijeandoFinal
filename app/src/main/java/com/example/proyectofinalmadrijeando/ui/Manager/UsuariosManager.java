package com.example.proyectofinalmadrijeando.ui.Manager;

import com.example.proyectofinalmadrijeando.ui.entities.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UsuariosManager {
    private static Gson getGsonInstance(){

        Gson returnGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        return returnGson;
    }
    public static Usuario convertPersonaToJSONString(String strJSON){
        Usuario     returnEntity = null;
        Gson        gson;

        if (strJSON != null){
            gson = getGsonInstance();
            //gson = new GsonBuilder()
            //        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            //        .create();

            returnEntity = gson.fromJson(strJSON, Usuario.class);
        }

        return returnEntity;
    }

}
