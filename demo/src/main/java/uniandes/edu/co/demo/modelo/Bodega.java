package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bodegas")
public class Bodega {

    @Id
    private String id; // Usamos String como ID
    private String nombre;
    private String tamaño;  // Tamaño de la bodega en metros cuadrados
    private String sucursalId; // ID de la sucursal a la que pertenece

    // Constructor
    public Bodega(String nombre, String tamaño, String sucursalId) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.sucursalId = sucursalId;
    }

    // Getters y Setters
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

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(String sucursalId) {
        this.sucursalId = sucursalId;
    }
}
