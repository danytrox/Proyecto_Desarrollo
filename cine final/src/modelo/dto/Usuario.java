/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto;

/**
 *
 * @author daner
 */
public class Usuario {
    int id;
    String usuario;
    String pass;
    int tipo_usuario;

    public Usuario() {
    }

    public Usuario(int id, String usuario, String pass, int tipo_usuario) {
        this.id = id;
        this.usuario = usuario;
        this.pass = pass;
        this.tipo_usuario = tipo_usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", usuario=" + usuario + ", pass=" + pass + ", tipo_usuario=" + tipo_usuario + '}';
    }
        
}
