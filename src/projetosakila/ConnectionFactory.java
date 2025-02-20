/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetosakila;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author arthu
 */
public class ConnectionFactory {
    private String url;
    private String user;
    private String password;
    
    public ConnectionFactory() {
        this.url = "jdbc:mysql://localhost/sakila";
        this.user = "root";
        this.password = "arthur120";
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
