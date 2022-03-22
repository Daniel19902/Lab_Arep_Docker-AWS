package edu.escuelaing.arep;


import com.google.gson.Gson;

import com.mongodb.*;
import org.json.JSONObject;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ConnectionMongo {

    private Mensaje mensaje;

    public ConnectionMongo(String mensaje) {

        JSONObject jsonObject = new JSONObject(mensaje);
        //this.mensaje = new Mensaje(jsonObject.getString("mensaje"), (ZonedDateTime) jsonObject.get("date"));
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");

        this.mensaje = new Mensaje(jsonObject.getString("mensaje"), ZonedDateTime.now());

    }

    public void connectionDb(){
        System.out.println(mensaje.getMensaje() + " conecction");
        MongoClient mongoClient = new MongoClient("db:27017");
        DB db = mongoClient.getDB("mensajes");
        DBCollection dbCollection = db.getCollection("mensaje");
        BasicDBObject m = new BasicDBObject();
        m.put("id", 1);
        m.put("mensaje", mensaje.getMensaje());
        m.put("fecha", String.valueOf(mensaje.getDate()));
        dbCollection.insert(m);
        mongoClient.close();

    }


    public List<JSONObject> consultaMongo(){
        MongoClient mongoClient = new MongoClient("db:27017");

        DB db = mongoClient.getDB("mensajes");
        DBCollection dbCollection = db.getCollection("mensaje");
        List<JSONObject> jsonObject = new LinkedList<>();
        DBCursor cursor = dbCollection.find();
        while (cursor.hasNext()){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("mensaje", cursor.next().get("mensaje"));
            jsonObject1.put("fecha", cursor.next().get("fecha"));
            jsonObject.add(jsonObject1);
        }

        mongoClient.close();
        return jsonObject;

    }


    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }
}
