package edu.escuelaing.arep;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


public class ConectarContenedor {

    private Gson gson;
    //private List<String> puertos = new LinkedList<>();
    private int position = -1;

    public ConectarContenedor() {
        this.gson = new Gson();
    }

    public String mensajeContenedor(String mensaje) throws UnsupportedEncodingException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("mensaje", mensaje));
        System.out.println(mensaje + "puerto 8090");
        String url = "http://loadbalancer:8090/publicar/mensaje";
        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpPost httpPost = new HttpPost("http://loadbalancer:8088/publicar/mensaje");
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        try {
            /*
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

             */
            System.out.println("si hay coneccion hp!!!!");


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
/*
    public String getPort(){

        if(position < 3){
            position+=1;
        }else {
            position = 0;
        }
        return puertos.get(position);
    }

 */

}
