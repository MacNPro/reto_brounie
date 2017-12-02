package io.llamas.retobrounie.Model;

import java.io.Serializable;

/**
 * Created by MacNPro on 12/2/17.
 */

public class Pais implements Serializable {

    private String id;
    private String nombre;
    private String capital;
    private String imagen;

    public Pais() {
    }

    public Pais(String id, String nombre, String capital, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.capital = capital;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
