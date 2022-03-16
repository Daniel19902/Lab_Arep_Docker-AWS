package edu.escuelaing.arep;

import java.time.ZonedDateTime;

public class Mensaje {

    private String mensaje;
    private ZonedDateTime date;

    public Mensaje (String mensaje){
        this.mensaje = mensaje;
        this.date = ZonedDateTime.now();

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
