/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto;

/**
 *
 * @author daner
 */
public class Genero {
    private int id;
    private String descripcion;

    public Genero() {
    }

    
    public Genero(int id, String descripcion) {
        setId(id);
        setDescripcion(descripcion);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Genero{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
