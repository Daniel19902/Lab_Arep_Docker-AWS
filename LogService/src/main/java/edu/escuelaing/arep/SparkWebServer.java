package edu.escuelaing.arep;

/**
 * Hello world!
 *
 */
import com.google.gson.Gson;

import java.lang.management.GarbageCollectorMXBean;

import static spark.Spark.*;
public class SparkWebServer
{
    public static void main( String[] args )
    {
        port(getPort());
        post("/publicar/mensaje", (req, res) -> mensaje(req.body()));
    }

    public static String mensaje(String mensaje){
        System.out.println(mensaje);
        Mensaje mensaje1 = new Mensaje(mensaje);
        SendMensaje sendMensaje = new SendMensaje();
        //return "meee";
        return sendMensaje.sendMensaje(mensaje1);
    }


    public static int getPort(){
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8088;
    }

}