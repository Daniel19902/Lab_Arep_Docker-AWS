package edu.escuelaing.arep;

import java.time.ZonedDateTime;

public class Mensaje {

    private String mensaje;
    private ZonedDateTime date;


    public Mensaje(String mensaje, ZonedDateTime date) {
        this.mensaje = mensaje;
        this.date = date;
    }

    public Mensaje(){

    }


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
}
