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
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class Test {
    
    private int id;
    private String title;
    private double charge;
    private String description;
    private String results;
    private Connection connection;
    private PreparedStatement pstatement;
    private ResultSet resultSet;
    private static ArrayList<Test> alltests = new ArrayList<>();
    private static String [] testTitles;
    
    public Test() throws ClassNotFoundException, SQLException{
        this.connection = Connectionz.getConnection();
        setAlltests();
    }
    
    public Test(int id,String desc,String result) throws ClassNotFoundException, SQLException{
        this.connection = Connectionz.getConnection();
        this.id = id;
        this.description = desc;
        this.results = result;
        setTitlefroDB();
    }
    
    public Test(int id,String title,double charge){
        this.id = id;
        this.title = title;
        this.charge = charge;
    }
    
    private void setTitlefroDB() throws SQLException{
        String queryStr = "select title from Test where id = ?"; 
        this.pstatement = this.connection.prepareStatement(queryStr);
        this.pstatement.setInt(1, this.id);
        this.resultSet = this.pstatement.executeQuery();
        if(this.resultSet.next()){
            this.title = this.resultSet.getString("title");
        }
    }

    public static ArrayList<Test> getAlltests() {
        return alltests;
    }

    private void setAlltests() throws SQLException {
        if(alltests.isEmpty()){
            int i = 0;
            int idd;
            String titl,desc,reslt;
            double charg;
            String queryStr = "select id, title, charge from Test";
            this.pstatement = this.connection.prepareStatement(queryStr);
            this.resultSet = pstatement.executeQuery();
            while(this.resultSet.next()){
                i++;
            }
            testTitles = new String[i];
            this.resultSet = pstatement.executeQuery();
            i = 0;
            
            while(this.resultSet.next()){
                idd = this.resultSet.getInt("id");
                titl = this.resultSet.getString("title");
                charg = this.resultSet.getDouble("charge");
                alltests.add(new Test(idd,titl,charg));
                testTitles[i] = titl;
                i++;
            }
        }
        
    }

    public static String[] getTestTitles() {
        return testTitles;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getResults() {
        return results;
    }
}
