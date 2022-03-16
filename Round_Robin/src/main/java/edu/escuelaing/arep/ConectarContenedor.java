package edu.escuelaing.arep;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class ConectarContenedor {

    private Gson gson;

    public ConectarContenedor() {
        this.gson = new Gson();
    }

    public String mensajeContenedor(String mensaje) {
        System.out.println(mensaje);
        String url = "http://localhost:8089/hello/mensaje";
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




}
