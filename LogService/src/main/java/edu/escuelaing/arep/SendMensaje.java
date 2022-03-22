package edu.escuelaing.arep;


import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

public class SendMensaje {

    private Gson gson;

    public SendMensaje(){
        this.gson = new Gson();
    }

    public String sendMensaje(Mensaje mensaje){

        String url = "http://localhost:8087/mongo/mensaje";
        try {

            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            //connection.setRequestProperty("Content-Type:","application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            System.out.println("Enviando mensaje !!!!");
            connection.getOutputStream().write(gson.toJson(mensaje).getBytes(StandardCharsets.UTF_8));

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input;
            String outPut = "";
            while ((input = in.readLine()) != null){
                outPut += input;
            }
            System.out.println(outPut);

            return outPut;


        }catch (Exception e){
            e.printStackTrace();
        }
        return "error en el post";


    }
}
