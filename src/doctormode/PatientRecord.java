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
public class PatientRecord {
    
    private int id;
    private String date;
    private int diagnosis_id;
    private int prescription_id;
    private int labtest_id;
    private int appointment_id;
    private int patientbill_id;
    private int patient_id;
    private boolean status;
    private Connection connection;
    private PreparedStatement pstatement;
    private ResultSet resultSet;
    
    public PatientRecord(int patient_id) throws ClassNotFoundException, SQLException{
        this.patient_id = patient_id;
        this.connection = Connectionz.getConnection();
    }
    
    public PatientRecord() throws ClassNotFoundException, SQLException{
        this.connection = Connectionz.getConnection();
    }
    
    public int createRecord() throws SQLException{
        String queryStr = "insert into PatientRecord (Patient_id) values ("
                + "'"+this.patient_id+"')";
        Statement stmt = this.connection.createStatement();
        stmt.executeUpdate(queryStr, Statement.RETURN_GENERATED_KEYS);
        this.resultSet = stmt.getGeneratedKeys();
        if(this.resultSet.next()){
            this.id = this.resultSet.getInt(1);
        }
        return this.id;
    }
    
    public boolean checkRecordExists(int pat_id,int rec_id) throws SQLException{
        boolean exists = false;
        String queryStr = "select id from PatientRecord where Patient_id = ?";
        this.pstatement = this.connection.prepareStatement(queryStr);
        this.pstatement.setInt(1, pat_id);
        this.resultSet = pstatement.executeQuery();
        
        while(this.resultSet.next()){
            if(rec_id == this.resultSet.getInt("id")){
                this.id = rec_id;
                exists = true;
            }
        }
        
        return exists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDiagnosis_id() {
        return diagnosis_id;
    }

    public void setDiagnosis_id(int diagnosis_id) {
        this.diagnosis_id = diagnosis_id;
    }

    public int getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(int prescription_id) {
        this.prescription_id = prescription_id;
    }

    public int getLabtest_id() {
        return labtest_id;
    }

    public void setLabtest_id(int labtest_id) {
        this.labtest_id = labtest_id;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public int getPatientbill_id() {
        return patientbill_id;
    }

    public void setPatientbill_id(int patientbill_id) {
        this.patientbill_id = patientbill_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
