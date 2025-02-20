/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetosakila;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author arthu
 */
public class CustomerDAO {
    
    private Connection con;
    
    public CustomerDAO() throws SQLException {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void insertCustomer(Customer c) throws SQLException {
        String sql = "insert into customer (store_id, first_name, last_name, email, address_id, active)"
                + " values (?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pst = con.prepareStatement(sql);
        
        pst.setInt(1, c.getStore_id());
        pst.setString(2, c.getFirst_name());
        pst.setString(3, c.getLast_name());
        pst.setString(4, c.getEmail());
        pst.setInt(5, c.getAddress_id());
        pst.setBoolean(6, c.isActive());
        
        pst.execute();
        pst.close();
    }
    
    public List<Customer> getCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "select * from customer order by customer_id desc";
        
        Statement st = con.createStatement();
        
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()) {
            customers.add(new Customer(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getBoolean(7),
                    rs.getTimestamp(8),
                    rs.getTimestamp(9)
            ));
        }
        
        return customers;
    }
    
    public void updateCustomer(int id) {
        
    }
    
    public void deleteCustomer(int id) {
        
    }
}
