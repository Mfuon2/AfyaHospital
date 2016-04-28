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
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class LabTest {
    
    private int id;
    private String date;
    private int labtech_id;
    private int patient_id;
    private int pat_rec_id;
    private String results;
    private ArrayList<Test> tests = new ArrayList<>();
    private Connection connection;
    private PreparedStatement pstatement;
    private ResultSet resultSet;
    
    public LabTest(int pat_id,int pat_rec_id) throws ClassNotFoundException, SQLException{
        this.connection = Connectionz.getConnection();
        this.patient_id = pat_id;
        this.pat_rec_id = pat_rec_id;
    }
    
    public LabTest() throws ClassNotFoundException, SQLException{
        this.connection = Connectionz.getConnection();
    }
    
    public void addTest(Test test){
        this.tests.add(test);
    }
    
    public void writeLabtest2DB() throws ClassNotFoundException, SQLException{
        newDBLabTest();
        for (Test test: this.tests){
            linkTest(test.getId());
        }
    }
    
    public void getTestsfromDB(int pat_rec_id) throws SQLException, ClassNotFoundException{
        this.pat_rec_id = pat_rec_id;
        String queryStr = "select LabTest_id from PatientRecord where id = ?"; 
        this.pstatement = this.connection.prepareStatement(queryStr);
        this.pstatement.setInt(1, this.pat_rec_id);
        this.resultSet = this.pstatement.executeQuery();
        if(this.resultSet.next()){
            this.id = this.resultSet.getInt("LabTest_id");
        }
        setTestsfroDB();
    }
    
    private void setTestsfroDB() throws SQLException, ClassNotFoundException{
        String queryStr = "select Test_id, description, results from LabTest_has_Test where LabTest_id = ?"; 
        this.pstatement = this.connection.prepareStatement(queryStr);
        this.pstatement.setInt(1, this.id);
        this.resultSet = this.pstatement.executeQuery();
        this.tests = new ArrayList<>();
        while(this.resultSet.next()){
            this.tests.add(new Test(this.resultSet.getInt("Test_id"),this.resultSet.getString("description"),this.resultSet.getString("results")));
        }
    }
    
    private void linkTest(int testid) throws SQLException{
        String queryStr = "insert into LabTest_has_Test (LabTest_id, Test_id) values (?, ?)"; 
        this.pstatement = this.connection.prepareStatement(queryStr);
        this.pstatement.setInt(1, this.id);
        this.pstatement.setInt(2, testid);
        this.pstatement.executeUpdate();
    }
    
    private void newDBLabTest() throws SQLException{
        String queryStr = "insert into LabTest (Patient_id) values ("+this.patient_id+")";
        Statement stmt = this.connection.createStatement();
        stmt.executeUpdate(queryStr, Statement.RETURN_GENERATED_KEYS);
        this.resultSet = stmt.getGeneratedKeys();
        if(this.resultSet.next()){
            this.id = this.resultSet.getInt(1);
        }
        updatePatientRecord();
    }
    
    private void updatePatientRecord() throws SQLException{
        String queryStr = "update PatientRecord set LabTest_id = ? where id = ?"; 
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLabtech_id() {
        return labtech_id;
    }

    public void setLabtech_id(int labtech_id) {
        this.labtech_id = labtech_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
    }
}
