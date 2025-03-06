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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public List<Customer> getCustomers(int order, boolean onlyActive, int limitTo) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "select * from customer";
        
        if (onlyActive) query += " where active = 1";
        
        switch (Math.abs(order)) {
            case 1:
                query += " order by customer_id";
                break;
            case 2:
                query += " order by first_name";
                break;
            case 3:
                query += " order by create_date";
                break;
            case 4:
                query += " order by last_update";
                break;
        }
        
        if (order < 0) query += " desc";
        
        if (limitTo != 0) query += " limit " + limitTo;
        
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
        
        st.close();
        return customers;
    }
    
    public Customer getCustomer(int id) throws SQLException {
        Customer c = null;
        
        String query = "select * from customer"
                + " where customer_id = " + id;
        
        Statement st = con.createStatement();
        
        ResultSet rs = st.executeQuery(query);
        
        if (rs.next()) {
            c = new Customer(rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getInt(6),
                            rs.getBoolean(7),
                            rs.getTimestamp(8),
                            rs.getTimestamp(9)
            );
        }
        
        st.close();
        
        return c;
    }
    
    public void updateCustomer(int id, Customer c) throws SQLException {
        String sql = "update customer"
                + " set store_id = ?,"
                + " first_name = ?,"
                + " last_name = ?,"
                + " email = ?,"
                + " address_id = ?,"
                + " active = ?"
                + " where customer_id = ?";
        
        PreparedStatement pst = con.prepareStatement(sql);
        
        pst.setInt(1, c.getStore_id());
        pst.setString(2, c.getFirst_name());
        pst.setString(3, c.getLast_name());
        pst.setString(4, c.getEmail());
        pst.setInt(5, c.getAddress_id());
        pst.setBoolean(6, c.isActive());
        pst.setInt(7, id);
        
        pst.execute();
        pst.close();
    }
    
    public void deleteCustomer(int id) throws SQLException {
        String sql = "delete from payment where customer_id = ?";
        
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        pst.execute();
        
        sql = "delete from rental where customer_id = ?";
        
        pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        pst.execute();
        
        sql = "delete from customer where customer_id = ?";
        
        pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        
        pst.execute();
        pst.close();
    }
    
    public int getCountOfRows() {
        int count = 0;
        try {
            String query = "select count(*) from customer";
            
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}