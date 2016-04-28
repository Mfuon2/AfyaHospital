package adminmode;


import java.sql.*;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mfuon
 */
public class connect {
    private static Connection con;

    public static Connection condb(){ 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/HospitalSys", "root", "");
            //JOptionPane.showMessageDialog(null, "Connected to server");
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "No Connection to server");
        }
        return con;
        
    }
} 
