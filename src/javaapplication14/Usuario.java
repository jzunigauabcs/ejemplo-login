/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication14;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jzuniga
 */
public class Usuario {
    public static final String TABLA = "usuarios";
    private int id;
    private String email;
    private String password;

    public Usuario() {
        
    }

    
    
    public Usuario(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int create() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        String query = "INSERT INTO " + Usuario.TABLA + "(email, password) " +
                "value(?,?)"; 
        Connection connection = DB.getConnection();
        PreparedStatement sp = connection.prepareStatement(query);
        sp.setString(1, this.getEmail());
        sp.setString(2, Helper.sha1(this.getPassword()));
        
        int result = sp.executeUpdate();
        connection.close();
        return result;
    }
    
    public static Usuario Login(String email, String password) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
    
        String query = "SELECT * FROM "+ Usuario.TABLA + " WHERE email=?"; 
        
        //Obtengo conexión
        Connection connection = DB.getConnection();
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, email);
        
        ResultSet rs = pst.executeQuery();
        
        Usuario usuario = null;
        if(rs.next()) {
            if(rs.getString("password").equals(Helper.sha1(password))) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
            }
        }
        return usuario;
        
    }   
}