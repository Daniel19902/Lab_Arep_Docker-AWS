package edu.escuelaing.arep;

import com.google.gson.Gson;
import spark.Filter;

import java.io.UnsupportedEncodingException;

import static spark.Spark.*;
public class SparkWebApp
{
    public static void main( String[] args )
    {
        Gson gson = new Gson();
        port(getPort());
        staticFiles.location("public");
        get("/publicar/:mensaje", (req, res) -> roundRobin(req.params(":mensaje")));
        get("/publicar2/:mensaje", (req, res) -> roundRobin2(req.params(":mensaje")));
    }

    public static String roundRobin(String mensaje) throws UnsupportedEncodingException {
        ConectarContenedor conectarContenedor = new ConectarContenedor();
        return conectarContenedor.mensajeContenedor(mensaje);
    }
    public static String roundRobin2(String mensaje) throws UnsupportedEncodingException {
        Gson gson = new Gson();
        String s = "[{\"fecha\":\"2022-03-23T00:31:20.382Z[Etc/UTC]\",\"mensaje\":\"hola que hace\"}, {\"fecha\":\"2022-03-23T00:39:19.513Z[Etc/UTC]\",\"mensaje\":\"porfis:c\"}, {\"fecha\":\"2022-03-23T00:39:35.692Z[Etc/UTC]\",\"mensaje\":\"porfis:cx2\"}]";
        return gson.toJson(s);
    }

    public static int getPort(){
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }

}

