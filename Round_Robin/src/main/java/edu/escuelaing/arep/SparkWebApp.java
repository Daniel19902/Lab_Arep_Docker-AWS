package edu.escuelaing.arep;

import com.google.gson.Gson;

import static spark.Spark.*;
public class SparkWebApp
{
    public static void main( String[] args )
    {
        Gson gson = new Gson();
        port(getPort());
        staticFiles.location("public");
        get("/publicar/:mensaje", (req, res) -> roundRobin(req.params(":mensaje")));
    }

    public static String roundRobin(String mensaje){
        ConectarContenedor conectarContenedor = new ConectarContenedor();
        return conectarContenedor.mensajeContenedor(mensaje);
    }

    public static int getPort(){
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}

