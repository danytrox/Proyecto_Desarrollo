/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import conexión.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import modelo.dto.Pelicula;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.dto.Usuario;

/**
 *
 */
public class HoytsMarkPlanet {
    private Connection conexion;

    public HoytsMarkPlanet(){
        try {
            conexion = Conexion.getConexion();
        } catch (Exception e) {
            System.out.println("Error en dao: "+e.getMessage());
        }
    }
    public void insertPelicula(Pelicula pelicula) {
    try {
        String query = "INSERT INTO MOVIE (ANIO,DURACION,TITULO,DIRECTOR,ID_GENERO) VALUES (?,?,?,?,?)";
        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setInt(1, pelicula.getAnio());
        statement.setInt(2, pelicula.getDuracion());
        statement.setString(3,pelicula.getTitulo());
        statement.setString (4,pelicula.getDirector());
        statement.setInt(5,pelicula.getGenero());
        statement.executeUpdate();
        statement.close();
        } catch (Exception e) {
            System.out.println("Error en insertPelicula: "+e.getMessage());
        }   
    }
    
    public void eliminarPelicula(int id) {
       try {
            String query = "DELETE FROM MOVIE WHERE ID_MOVIE=?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error en eliminarPelicula: "+e.getMessage());
        }
    }    
    
    public void updatePelicula(Pelicula pelicula) {
        try {
            String query = "UPDATE MOVIE SET ANIO=?, DURACION=?, TITULO=?, DIRECTOR=?, ID_GENERO=? WHERE ID_MOVIE=?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, pelicula.getAnio());
            statement.setInt(2, pelicula.getDuracion());
            statement.setString(3, pelicula.getTitulo());
            statement.setString(4, pelicula.getDirector());
            statement.setInt(5, pelicula.getGenero());
            statement.setInt(6, pelicula.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error en updatePelicula: " + e.getMessage());
        }
    }
       
    public Pelicula buscarPelicula(int id) {
        Pelicula pelicula = null;
        String titulo = null;
        int anio = 0;
        String director = null;
        int duracion = 0;
        int id_genero = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM MOVIE WHERE ID_MOVIE=?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                titulo = resultSet.getString("TITULO");
                anio = resultSet.getInt("ANIO");
                director = resultSet.getString("DIRECTOR");
                duracion = resultSet.getInt("DURACION");
                id_genero = resultSet.getInt("ID_GENERO");

                pelicula = new Pelicula(id, anio, duracion, titulo, director, id_genero);
            }
        } catch (Exception e) {
            System.out.println("Buscar Pelicula: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return pelicula;
    }
            
    public Usuario consultarUsuario(String user, String pass){
        Usuario usu  = new Usuario();
        try {
            String query = "SELECT * FROM usuario where nombre = ? and  pass= ?";   
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, pass);

            ResultSet resultquery = statement.executeQuery();
            
            if (resultquery.next()){
                int permiso = resultquery.getInt(1);
                String usuario = resultquery.getString(2);
                String clave = resultquery.getString(3);
                int tipo_usuario = resultquery.getInt(4);
                usu = new Usuario(permiso, usuario, clave, tipo_usuario);
            }
            else {
                JOptionPane.showMessageDialog(null, "Error no se encontro usuario");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        return usu;
    }
    
    public List<Pelicula> listarPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            String query = "SELECT * FROM MOVIE";
            PreparedStatement statement = conexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID_MOVIE");
                int anio = resultSet.getInt("ANIO");
                int duracion = resultSet.getInt("DURACION");
                String titulo = resultSet.getString("TITULO");
                String director = resultSet.getString("DIRECTOR");
                int idGenero = resultSet.getInt("ID_GENERO");

                Pelicula pelicula = new Pelicula(id, anio, duracion, titulo, director, idGenero);
                peliculas.add(pelicula);
            }

            statement.close();
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Error en listarPeliculas: " + e.getMessage());
        }
        return peliculas;
    }
    
    public boolean existePelicula(String nombrePelicula) {
        try {
            String query = "SELECT COUNT(*) FROM MOVIE WHERE TITULO = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, nombrePelicula);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int count = resultSet.getInt(1);

            statement.close();

            return count > 0;
        } catch (SQLException e) {
            System.out.println("Error al verificar si existe la película: " + e.getMessage());
            return false;
        }
    }
    
    public void insertUsuario(Usuario usuario) {
    try {
        String query = "INSERT INTO USUARIO (NOMBRE,PASS,ID_TIPO_USUARIO) VALUES (?,?,?)";
        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getPass());
        statement.setInt(3,usuario.getTipo_usuario());
        statement.executeUpdate();
        statement.close();
        } catch (Exception e) {
            System.out.println("Error en insertUsuario: "+e.getMessage());
        }   
    }
    
    public void eliminarUsuario(int id) {
       try {
            String query = "DELETE FROM USUARIO WHERE ID_USUARIO=?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error en elimarUsuario: "+e.getMessage());
        }
    } 
    
    public void updateUsuario(Usuario usuario) {
        try {
            String query = "UPDATE USUARIO SET NOMBRE=?, PASS=?, ID_TIPO_USUARIO=? WHERE ID_USUARIO=?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getPass());
            statement.setInt(3, usuario.getTipo_usuario());
            statement.setInt(4, usuario.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error en updatePelicula: " + e.getMessage());
        }
    }
    
    public Usuario buscarUsuario(int id) {
        Usuario usuario = null;
        String nombre = null;
        String pass = null;
        int tipo_usuario = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM USUARIO WHERE ID_USUARIO=?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nombre = resultSet.getString("NOMBRE");
                pass = resultSet.getString("PASS");
                tipo_usuario = resultSet.getInt("ID_TIPO_USUARIO");
                
                usuario = new Usuario(id, nombre, pass, tipo_usuario);
            }
        } catch (Exception e) {
            System.out.println("Buscar Usuario: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return usuario;
    }
    
    public List<Usuario> listarUsuario() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String query = "SELECT * FROM USUARIO";
            PreparedStatement statement = conexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID_USUARIO");
                String nombre = resultSet.getString("NOMBRE");
                String pass = resultSet.getString("PASS");
                int tipo_usuario = resultSet.getInt("ID_TIPO_USUARIO");

                Usuario usuario = new Usuario(id, nombre, pass, tipo_usuario);
                usuarios.add(usuario);
            }

            statement.close();
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Error en listarUsuario: " + e.getMessage());
        }
        return usuarios;
    }
    
    public boolean existeUsuario(String nUsuario) {
        try {
            String query = "SELECT COUNT(*) FROM USUARIO WHERE NOMBRE = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, nUsuario);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int count = resultSet.getInt(1);

            statement.close();

            return count > 0;
        } catch (SQLException e) {
            System.out.println("Error al verificar si existe el usuario: " + e.getMessage());
            return false;
        }
    }
    
    public List<Usuario> filtrarUsuario(int tipo) {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String query = "SELECT * FROM USUARIO where ID_TIPO_USUARIO = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, tipo);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID_USUARIO");
                String nombre = resultSet.getString("NOMBRE");
                String pass = resultSet.getString("PASS");
                int tipo_usuario = resultSet.getInt("ID_TIPO_USUARIO");

                Usuario usuario = new Usuario(id, nombre, pass, tipo_usuario);
                usuarios.add(usuario);
            }

            statement.close();
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Error en filtrarUsuario: " + e.getMessage());
        }
        return usuarios;
    }
    
    public List<Pelicula> filtrarPeli(int id_genero) {
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            String query = "SELECT * FROM MOVIE WHERE ID_GENERO = ? ";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id_genero);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_MOVIE");
                int anio = resultSet.getInt("ANIO");
                int duracion = resultSet.getInt("DURACION");
                String titulo = resultSet.getString("TITULO");
                String director = resultSet.getString("DIRECTOR");
                id_genero = resultSet.getInt("ID_GENERO");

                Pelicula pelicula = new Pelicula(id, anio, duracion, titulo, director, id_genero);
                peliculas.add(pelicula);
            }

            statement.close();
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Error en filtrarPelicula: " + e.getMessage());
        }
        return peliculas;
    }
}

