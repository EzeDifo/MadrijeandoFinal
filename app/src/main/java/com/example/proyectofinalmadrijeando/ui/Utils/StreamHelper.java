package com.example.proyectofinalmadrijeando.ui.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamHelper {

    public static String GetFullStringFromInputReader(InputStream inputStream) {
        BufferedReader responseReader;
        String responseLine;
        String strResultado="";
        StringBuilder sbResponse;
        sbResponse = new StringBuilder();
        try{
            responseReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((responseLine = responseReader.readLine()) != null)
            {
                sbResponse.append(responseLine);
            }
          responseReader.close();
        }
        catch (Exception e){

        }
        return sbResponse.toString();
    }
}
