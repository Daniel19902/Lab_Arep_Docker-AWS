package edu.escuelaing.arep;

/**
 * Hello world!
 *
 */


import org.json.JSONObject;
import sun.plugin2.message.Message;

import java.util.List;

import static spark.Spark.*;
public class SparkWebMongo
{
    public static void main( String[] args )
    {


        port(getPort());
        post("/mongo/mensaje", (req, res) -> mensaje(req.body()));
        /*
        System.out.println( "Hello World!" );
        ConnectionMongo connectionMongo = new ConnectionMongo();
        connectionMongo.connectionDb();

         */
    }


    public static List<JSONObject> mensaje(String message){
        System.out.println(message);
        ConnectionMongo connectionMongo = new ConnectionMongo(message);
        return connectionMongo.connectionDb();


    }



    public static int getPort(){
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8090;
    }

}
