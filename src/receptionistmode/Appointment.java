/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package receptionistmode;

import doctormode.Connectionz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author root
 */
public class Appointment {
    
    private int id;
    private int doctor_id;
    private int patient_id;
    private int receptionist_id;
    private String date;
    private Connection connection;
    private PreparedStatement pstatement;
    private ResultSet resultSet;

    public Appointment(int doctor_id, int patient_id, int receptionist_id) throws ClassNotFoundException, SQLException {
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.receptionist_id = receptionist_id;
        this.connection = Connectionz.getConnection();
    }
    
    public Appointment() throws ClassNotFoundException, SQLException{
        this.connection = Connectionz.getConnection();
    }
    
    public int createAppoint() throws SQLException{
        String queryStr = "insert into Appointment (Doctor_id,Patient_id,Receptionist_id) values ("
                + "'"+this.doctor_id+"','"+this.patient_id+"','"+this.receptionist_id+"')";
        Statement stmt = this.connection.createStatement();
        stmt.executeUpdate(queryStr, Statement.RETURN_GENERATED_KEYS);
        this.resultSet = stmt.getGeneratedKeys();
        if(this.resultSet.next()){
            this.id = this.resultSet.getInt(1);
        }
        return this.id;
    }

    public int getId() {
        return id;
    }
    
    public ResultSet getAllAppoints() throws SQLException{
        ResultSet rst;
        String queryStr = "select id, Doctor_id, date, Patient_id from Appointment";
        this.pstatement = this.connection.prepareStatement(queryStr);
        rst = pstatement.executeQuery();
        return rst;
    }
}
