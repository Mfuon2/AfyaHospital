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
import java.sql.Statement;

/**
 *
 * @author root
 */
public class Prescription {
    
    private int id;
    private String description;
    private String date;
    private int doctor_id;
    private int patient_id;
    private int pat_rec_id;
    private Connection connection;
    private PreparedStatement pstatement;
    private ResultSet resultSet;
    
    public Prescription(int pat_id,int pat_rec_id,int doc_id) throws ClassNotFoundException, SQLException{
        this.connection = Connectionz.getConnection();
        this.patient_id = pat_id;
        this.doctor_id = doc_id;
        this.pat_rec_id = pat_rec_id;
    }
    
    public Prescription(){
    }
    
    private void write2DB() throws SQLException{
        String queryStr = "insert into Prescription (description, Doctor_id, Patient_id) values ('"+this.description+"', '"+this.doctor_id+"', '"+this.patient_id+"')"; 
        Statement stmt = this.connection.createStatement();
        stmt.executeUpdate(queryStr, Statement.RETURN_GENERATED_KEYS);
        this.resultSet = stmt.getGeneratedKeys();
        if(this.resultSet.next()){
            this.id = this.resultSet.getInt(1);
        }
        updatePatientRecord();
    }
    
    public boolean checkExistDesc() throws SQLException{
        boolean exists = false;
        String queryStr = "select Prescription_id from PatientRecord where id = ? and Patient_id = ?";
        this.pstatement = this.connection.prepareStatement(queryStr);
        this.pstatement.setInt(1, this.pat_rec_id);
        this.pstatement.setInt(2, this.patient_id);
        this.resultSet = this.pstatement.executeQuery();
        if(this.resultSet.next()){
            this.id = this.resultSet.getInt("Prescription_id");
        }
        this.resultSet = null;
        queryStr = "select description from Prescription where id = ?";
        this.pstatement = this.connection.prepareStatement(queryStr);
        this.pstatement.setInt(1, this.id);
        this.resultSet = this.pstatement.executeQuery();
        if(this.resultSet.next()){
            this.description = this.resultSet.getString(1);
            exists = true;
        }
        return exists;
    }
    
    private void updatePatientRecord() throws SQLException{
        String queryStr = "update PatientRecord set Prescription_id = ? where id = ?"; 
        this.pstatement = this.connection.prepareStatement(queryStr);
        this.pstatement.setInt(1, this.id);
        this.pstatement.setInt(2, this.pat_rec_id);
        this.pstatement.executeUpdate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws SQLException {
        this.description = description;
        write2DB();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }
    
}
