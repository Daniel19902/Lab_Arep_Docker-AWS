package edu.escuelaing.arep;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;


public class ConectarContenedor {

    private Gson gson;
    private List<String> puertos = new LinkedList<>();
    private int position = -1;

    public ConectarContenedor() {
        this.gson = new Gson();
        this.puertos.add("8088");
        this.puertos.add("8089");
        this.puertos.add("8090");
    }

    public String mensajeContenedor(String mensaje) {
        System.out.println(mensaje);
        String url = "http://localhost:8088/publicar/mensaje";
        try {

            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            //connection.setRequestProperty("Accept-Language", "UTF-8");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.getOutputStream().write(mensaje.getBytes(StandardCharsets.UTF_8));
            //connection.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input;
            String outPut = "";
            while ((input = in.readLine()) != null){
                outPut += input;
            }
            System.out.println(outPut);
            return gson.toJson(outPut);

        }catch (Exception e){
            e.printStackTrace();
        }
        return gson.toJson("error al pedir información");
    }

    public String getPort(){

        if(position < 3){
            position+=1;
        }else {
            position = 0;
        }
        return puertos.get(position);
    }

}
