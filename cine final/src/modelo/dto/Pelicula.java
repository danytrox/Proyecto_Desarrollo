/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto;

/**
 *
 * @author daner
 */
public class Pelicula {
    private int id,anio,duracion ;
    private String titulo,director;
    private int genero;

    public Pelicula() {
    }

    public Pelicula(int id, int anio, int duracion, String titulo, String director, int genero) {
        setId(id);
        setAnio(anio);
        setDuracion(duracion);
        setTitulo(titulo);
        setDirector(director);
        setGenero(genero);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "id=" + id + ", anio=" + anio + ", duracion=" + duracion + ", titulo=" + titulo + ", director=" + director + ", genero=" + genero + '}';
    }
    
    
    
    
    
}
