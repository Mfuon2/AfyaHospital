package labtechmode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Gen_all_tests_table {

    String host = "jdbc:mysql://localhost:3306/HospitalSys";
    String user = "root";
    String pass = "";

    //Get Test and tEST rEsults and display in a table
    public DefaultTableModel testResults() {
        DefaultTableModel dtm = new DefaultTableModel();

        dtm.addColumn("No");
        dtm.addColumn("Test Id");
        dtm.addColumn("Tested");
        dtm.addColumn("Description");
        dtm.addColumn("Results");

        JFlabTech jlt = new JFlabTech();

        //Sql to get patient's id and the name of the test
      
        String sqlget = "SELECT Test.title,LabTest_has_Test.LabTest_id, LabTest_has_Test.description, LabTest_has_Test.results FROM LabTest_has_Test INNER JOIN Test ON LabTest_has_Test.Test_id=Test.id ";
        try {
            Connection conn = DriverManager.getConnection(host, user, pass);
            Statement s = conn.prepareStatement(sqlget);
            ResultSet rs = s.executeQuery(sqlget);
            
            System.out.println("SELLECTION SUCCESSFULL 11");
            
            int i = 1;
            while (rs.next()) {
                String test_descr = rs.getString("LabTest_has_Test.description");
                                                  
                String test_title = rs.getString("Test.title");
                String test_res = rs.getString("LabTest_has_Test.results");
                int labtestid=rs.getInt("LabTest_has_Test.LabTest_id");
                dtm.addRow(new Object[]{i, labtestid, test_title, test_descr, test_res});
                i++;
            }
            return dtm;
        } catch (Exception e) {
            System.out.println("Complications while updating Table LABTESThASrESULTS!");
            e.printStackTrace();
        }

        return dtm;

    }
}
