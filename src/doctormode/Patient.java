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
public class Patient {
    
    private int id;
    private int nationalID;
    private String firstname;
    private String lastname;
    private int age;
    private String gender;
    private String blood_group;
    private String health_history;
    private long cellphone;
    private long homephone;
    private int postalcode;
    private String po_box;
    private String city;
    private String street;
    private Connection connection;
    private PreparedStatement pstatement;
    private ResultSet resultSet;
    private ResultSet tempresultSet;

    public Patient(int nationalID, String firstname, String lastname, int age, String gender, String blood_group, String health_history, long cellphone, long homephone, int postalcode, String po_box, String city, String street) throws ClassNotFoundException, SQLException {
        this.nationalID = nationalID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.blood_group = blood_group;
        this.health_history = health_history;
        this.cellphone = cellphone;
        this.homephone = homephone;
        this.postalcode = postalcode;
        this.po_box = po_box;
        this.city = city;
        this.street = street;
        this.connection = Connectionz.getConnection();
    }
    
    public Patient(int natID,String firstname,String lastname,int age,String gender) throws SQLException, ClassNotFoundException{
        this.nationalID = natID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.connection = Connectionz.getConnection();
        // call add to db to add patinet to db wit param 4
    }
    
    public Patient(String firstname,String lastname,int age,String gender) throws ClassNotFoundException, SQLException{
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.connection = Connectionz.getConnection();
        // call add to db to add patinet to db3
    }
    
    public Patient(int pat_id) throws ClassNotFoundException, SQLException{
        this.id = pat_id;
        this.connection = Connectionz.getConnection();
    }
    
    public Patient() throws ClassNotFoundException, SQLException{
        this.connection = Connectionz.getConnection();
    }
    
    public int addToDb13() throws SQLException{
        //after adding set the patient id
        String queryStr = "insert into Patient (nationalID,Firstname,Lastname,age,gender,blood_group,"
                + "health_history,cellphone,homephone,postalcode,po_box,city,street) values ("
                + "'"+this.nationalID+"','"+this.firstname+"','"+this.lastname+"','"+this.age+""
                + "','"+this.gender+"','"+this.blood_group+"','"+this.health_history+"','"+this.cellphone+""
                + "','"+this.homephone+"','"+this.postalcode+"','"+this.po_box+"','"+this.city+""
                + "','"+this.street+"')";
        Statement stmt = this.connection.createStatement();
        stmt.executeUpdate(queryStr, Statement.RETURN_GENERATED_KEYS);
        this.resultSet = stmt.getGeneratedKeys();
        if(this.resultSet.next()){
            this.id = this.resultSet.getInt(1);
        }
        
        return this.id;
    }
    
    public void addToDb4(){
        //after adding set the patient id
    }
    
    public boolean searchPatient(int id) throws SQLException{
        //perform a db search for patient with above id
        //then initialize menbers wit search results if true
        boolean found = false;
        String queryStr = "select id, nationalID, Firstname, Lastname, age, gender from Patient where id = ?";
        this.pstatement = this.connection.prepareStatement(queryStr);
        this.pstatement.setInt(1, id);
        this.resultSet = pstatement.executeQuery();
        
        this.tempresultSet = this.resultSet;
        
        if(this.resultSet.next()){
            this.id = resultSet.getInt("id");
            this.nationalID = resultSet.getInt("nationalID");
            this.firstname = resultSet.getString("Firstname");
            this.lastname = resultSet.getString("Lastname");
            this.age = resultSet.getInt("age");
            this.gender = resultSet.getString("gender");
            found = true;
            this.resultSet.previous();
        }
        return found;
    }
    
    public ResultSet getAllPatients() throws SQLException{
        ResultSet rst;
        String queryStr = "select id, nationalID, Firstname, Lastname, age, gender from Patient";
        this.pstatement = this.connection.prepareStatement(queryStr);
        rst = pstatement.executeQuery();
        return rst; 
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

    public ResultSet getTempresultSet() {
        return tempresultSet;
    }

    public void setNationalID(int nationalID) {
        this.nationalID = nationalID;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    
}
