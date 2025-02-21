/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetosakila;

import java.sql.SQLException;

/**
 *
 * @author arthu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        Customer c = dao.getCustomer(612);
        
        System.out.println(c);
    }
    
}
