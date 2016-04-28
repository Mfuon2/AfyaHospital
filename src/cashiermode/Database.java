/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cashiermode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class Database {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/HospitalSys";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
    Connection conn = null;
    Statement stm;

    public Database() {
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection estalished..");

        } catch (Exception e) {
            System.err.println("Connection FAiled " + e.getMessage());
        }
        try {
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

        } catch (SQLException se) {
            System.err.println("Error Connecting to the database.." + se.getMessage());
        }

    }

    public ArrayList<Object> getPatientRecords(int patientId, int recordId) {
        ArrayList<Object> records = new ArrayList<>();

        int labtestId = 0;
        int state = 0;
        try {
            stm = conn.createStatement();

            ResultSet payState = stm.executeQuery("SELECT PatientBill_id FROM PatientRecord  WHERE Patient_id=" + patientId + " AND id=" + recordId);
            while (payState.next()) {
                state = payState.getInt("PatientBill_id");
                System.out.println("State" + state);
            }
            if (state < 1) {
                records.add(0);
            } else {
                records.add(1);
            }

            ResultSet patientDets = stm.executeQuery("SELECT Patient.Firstname,PatientRecord.LabTest_id FROM Patient INNER JOIN PatientRecord ON Patient.id=PatientRecord.Patient_id WHERE Patient.id=" + patientId + " AND PatientRecord.id=" + recordId);
            while (patientDets.next()) {
                records.add(new Patient(patientId, patientDets.getString("Patient.Firstname")));
                labtestId = patientDets.getInt("PatientRecord.LabTest_id");
                records.add(recordId);
                System.out.println("name " + patientDets.getString("Patient.Firstname") + "  and id " + labtestId);
            }
            if (!records.isEmpty()) {
                ResultSet doce = stm.executeQuery("SELECT DISTINCT doctorsfee FROM Test");
                while (doce.next()) {
                    records.add(doce.getDouble("doctorsfee"));
                    System.out.println("Doctors fee " + doce.getDouble("doctorsfee"));

                }
            }
            if (labtestId > 0) {
                ResultSet testDets = stm.executeQuery("SELECT Test.title,Test.charge FROM Test JOIN LabTest_has_Test ON LabTest_has_Test.Test_id=Test.id WHERE LabTest_has_Test.LabTest_id=" + labtestId);
                while (testDets.next()) {
                    records.add(new Test(testDets.getString("Test.title"), testDets.getDouble("Test.charge")));
                    System.out.println("Test title " + testDets.getString("Test.title"));
                    System.out.println("Test charge " + testDets.getDouble("Test.charge"));
                }

            }

        } catch (SQLException e) {
            System.out.println("Cannot retrieve the years DB..." + e.getMessage());
        }

        return records;
    }

    public boolean updatePaymentState(int recordId, int patientId, int cashier, double doctorsfee, double labtest, double total) {
        boolean update = false;
        try {
            stm = conn.createStatement();
            stm.executeUpdate("INSERT INTO PatientBill(labtestfee,doctorsfee,totalfee,paidstatus,Patient_id,Cashier_id)"
                    + " VALUES(" + labtest + "," + doctorsfee + "," + total + "," + 1 + "," + patientId + "," + cashier + ")");
            stm.executeUpdate("UPDATE PatientRecord  SET PatientBill_id =(SELECT id FROM PatientBill ORDER BY id DESC LIMIT 1)  WHERE id=" + recordId);

            update = true;

        } catch (Exception e) {
            System.err.println("Error occurred Updating Patient Patyment Status ." + e.getMessage());

        }
        return update;
    }

    public String getCashierName(int id) {
        String name = "";
        try {
            stm = conn.createStatement();
            ResultSet cashDets = stm.executeQuery("SELECT name FROM Cashier WHERE id=" + id);
            while (cashDets.next()) {
                name = cashDets.getString("name");

            }

        } catch (Exception e) {
            e.getMessage();
        }
        return name;

    }

    public ArrayList<Object> getAllUnpaidPatientRecords(int patientId) {
        ArrayList<Object> records = new ArrayList<>();
        ArrayList<Integer> testId = new ArrayList<>();
        ArrayList<Integer> recordId = new ArrayList<>();
        ArrayList<Double> docFee = new ArrayList<>();
        ArrayList<Test> tests;
        int labtestId = 0;
        int def=0;
        try {
            stm = conn.createStatement();

            ResultSet patientRecs = stm.executeQuery("SELECT id,LabTest_id,PatientBill_id FROM PatientRecord  WHERE Patient_id=" + patientId );
            while (patientRecs.next()) {  //IN(SELECT id FROM PatientBill WHERE Patient_id=" + patientId + " AND paidstatus=" + 0 + ")
                if(patientRecs.getInt("PatientBill_id")<1){
                testId.add(patientRecs.getInt("LabTest_id"));
                recordId.add(patientRecs.getInt("id"));
                System.out.println("ID.."+patientRecs.getInt("LabTest_id"));
                System.out.println("BILL ID " + patientRecs.getInt("PatientBill_id"));
                }
            }
            if (!testId.isEmpty()) {
                ResultSet testDets;
                for (int test : testId) {
                    System.out.println("Test id " + test);
                    tests = new ArrayList<>();
                    testDets = stm.executeQuery("SELECT Test.title,Test.charge FROM Test JOIN LabTest_has_Test ON LabTest_has_Test.Test_id=Test.id WHERE LabTest_has_Test.LabTest_id=" + test);
                    while (testDets.next()) {
                        tests.add(new Test(testDets.getString("Test.title"), testDets.getDouble("Test.charge")));
                        System.out.println("Test title " + testDets.getString("Test.title"));
                        System.out.println("Test charge " + testDets.getDouble("Test.charge"));
                    }

                    ResultSet doce = stm.executeQuery("SELECT  DISTINCT doctorsfee FROM Test");
                    while (doce.next()) {
                        docFee.add(doce.getDouble("doctorsfee"));
                        System.out.println("Doctors fee " + doce.getDouble("doctorsfee"));
                    }
                    if (!tests.isEmpty()) {
                        records.add(tests);
                    }
                }
                records.add(recordId);

                ResultSet patientDets = stm.executeQuery("SELECT Firstname FROM Patient WHERE id=" + patientId);
                while (patientDets.next()) {
                    records.add(new Patient(patientId, patientDets.getString("Firstname")));

                    System.out.println("name " + patientDets.getString("Firstname"));
                }

                records.add(docFee);
            }

        } catch (SQLException e) {
            System.out.println("Cannot retrieve the years DB..." + e.getMessage());
        }

        return records;
    }

    public boolean paidStatus(int patientId, int recordId) {
        boolean state = false;
        int mystate = 0;

        try {
            stm = conn.createStatement();
            ResultSet payState = stm.executeQuery("SELECT PatientBill_id FROM PatientRecord  WHERE Patient_id=" + patientId + " AND id=" + recordId);
            while (payState.next()) {
                mystate = payState.getInt("PatientBill_id");
                System.out.println("name " + state);
            }
            if (mystate < 1) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {

        }
        return false;
    }
}
