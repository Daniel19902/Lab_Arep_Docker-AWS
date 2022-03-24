package edu.escuelaing.arep;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
    private final List<String[]> puertos = new LinkedList<>();
    private int puerto;

    public ConectarContenedor(int puerto) {
        this.gson = new Gson();
        this.puerto = puerto;
        this.puertos.add(new String[] {"loadbalancer1", "8090"});
        this.puertos.add(new String[] {"loadbalancer2", "8091"});
        this.puertos.add(new String[] {"loadbalancer3", "8092"});
    }

    public String mensajeContenedor(String mensaje) throws UnsupportedEncodingException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("mensaje", mensaje));
        System.out.println(mensaje + "puerto 8090");
        String[] puerto1 = puertos.get(puerto);
        String url = "http://"+puerto1[0]+":"+puerto1[1]+"/publicar/mensaje";
        System.out.println(puerto1[0]+" "+puerto1[1]);

        try {

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



}
