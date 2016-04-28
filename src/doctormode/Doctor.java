/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctormode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author root
 */
public class Doctor {

    private int id;
    private int nationalID;
    private String name;
    private String username;
    private String password;
    private Connection connection;
    private PreparedStatement pstatement;
    private ResultSet resultSet;
    private static String[] doctorIDs;

    public Doctor(int id) throws SQLException, ClassNotFoundException {
        this.connection = Connectionz.getConnection();
        this.id = id;
        initializeDoc();
    }

    public Doctor() {
    }

    private void initializeDoc() throws SQLException {
        String queryStr = "select * from Doctor where id = ?";
        this.pstatement = this.connection.prepareStatement(queryStr);
        this.pstatement.setInt(1, this.id);
        this.resultSet = this.pstatement.executeQuery();
        if (this.resultSet.next()) {
            this.nationalID = this.resultSet.getInt("nationalID");
            this.name = this.resultSet.getString("name");
        }
    }

    public static String[] getDocIds() throws ClassNotFoundException, SQLException {
        int i = 0;
        int idd;
        Connection connection = Connectionz.getConnection();
        PreparedStatement pstatement;
        ResultSet resultSet;
        String queryStr = "select id from Doctor";
        pstatement = connection.prepareStatement(queryStr);
        resultSet = pstatement.executeQuery();

        while (resultSet.next()) {
            i++;
        }
        doctorIDs = new String[i];
        resultSet = pstatement.executeQuery();
        i = 0;

        while (resultSet.next()) {
            idd = resultSet.getInt("id");
            doctorIDs[i] = idd + "";
            i++;
        }

        return doctorIDs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNationalID() {
        return nationalID;
    }

    public void setNationalID(int nationalID) {
        this.nationalID = nationalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
