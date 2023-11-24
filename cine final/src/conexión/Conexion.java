/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexión;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author daner
 */
public class Conexion {
    private static Connection conexion;
    private String user = "movie"; //cambiar todas las credenciales segun usuario sql
    private String pass = "movie";
    private String url = "jdbc:oracle:thin:" + user + "/" + pass + "@localhost:1521:orcl";
    
    public Conexion(){
        try{
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
            this.conexion = DriverManager.getConnection(url, user, pass);
        }catch(Exception e){
            System.out.println("Error en conexion"+e.getMessage());
        }
    }
    
    public static Connection getConexion(){
        if(conexion == null){
            new Conexion();
        }
        return conexion;
    }
    
    
      
   
}
