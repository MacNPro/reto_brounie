package io.llamas.retobrounie.Model;

import java.io.Serializable;

/**
 * Created by MacNPro on 12/2/17.
 */

public class Ciudad implements Serializable{

    private String id;
    private String nombre;
    private String pid;
    private String pais;
    private String imagen;
    private String latitude;
    private String longitude;

    public Ciudad() {
    }

    public Ciudad(String id, String nombre, String pid, String pais, String imagen, String latitude, String longitude) {
        this.id = id;
        this.nombre = nombre;
        this.pid = pid;
        this.pais = pais;
        this.imagen = imagen;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
