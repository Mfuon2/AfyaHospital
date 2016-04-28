/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctormode;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author root
 */
public class Login {
    private int id;
    boolean valid;
    private String module;
    private String username;
    private char[] password;
    private String passwordS;
    private Connection connection;
    private PreparedStatement pstatement;
    private ResultSet resultSet;
    
    public Login(String module,String username,char[] password) throws ClassNotFoundException, SQLException{
        this.connection = Connectionz.getConnection();
        this.module = module;
        this.username = username;
        this.password = password;
        this.passwordS = String.copyValueOf(password);
        validateCreds();
    }
    
    public Login(String module,int id) throws ClassNotFoundException, SQLException{
        this.connection = Connectionz.getConnection();
        this.id = id;
        this.module = module;
    }
    
    public boolean changePasswd(char[] newpass) throws SQLException{
        boolean success = false;
        String newpasshash = hashPass(String.copyValueOf(newpass));
        String queryStr = "update "+this.module+" set password = ? where id = ?"; 
        this.pstatement = this.connection.prepareStatement(queryStr);
        this.pstatement.setString(1, newpasshash);
        this.pstatement.setInt(2, this.id);
        if(this.pstatement.executeUpdate() >= 1){
            success = true;
        }
        return success;
    }
    
    private void validateCreds() throws SQLException{
        //String salt = "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*";
        //String passhash = hashPass(this.passwordS+salt);
        String passhash = hashPass(this.passwordS);
        String queryStr = "select * from "+this.module+" where username = ? and password = ?"; 
        this.pstatement = this.connection.prepareStatement(queryStr);
        this.pstatement.setString(1, this.username);
        this.pstatement.setString(2, passhash);
        this.resultSet = this.pstatement.executeQuery();
        if(this.resultSet.next()){
            this.id = this.resultSet.getInt("id");
            this.valid = true;
        }else{
            this.valid = false;
        }
    }
    
    private String hashPass(String input){
        String md5 = null;
         
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
        }
        return md5;
    }
    
    public static String getHash(String input){
        String md5 = null;
         
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
        }
        return md5;
    }

    public int getId() {
        return id;
    }
    
    public boolean isValid(){
        return valid;
    }
}
