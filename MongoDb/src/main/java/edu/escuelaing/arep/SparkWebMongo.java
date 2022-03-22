package edu.escuelaing.arep;

/**
 * Hello world!
 *
 */


import org.json.JSONObject;


import java.util.List;

import static spark.Spark.*;
public class SparkWebMongo
{
    public static void main( String[] args )
    {


        port(getPort());
        post("/mongo/mensaje", (req, res) -> mensaje(req.body()));
    }


    public static List<JSONObject> mensaje(String message){
        System.out.println(message);
        ConnectionMongo connectionMongo = new ConnectionMongo(message);
        connectionMongo.connectionDb();
        return connectionMongo.consultaMongo();
    }

    public static int getPort(){
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8086;
    }

}
