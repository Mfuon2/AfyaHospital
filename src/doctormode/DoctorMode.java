/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctormode;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class DoctorMode {
    int doc_id = -1;
    String module = "Doctor";
    String hos_name = Main.hos_name;
    
    public DoctorMode(String module,int doc_id){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new DoctorMain(module,doc_id,hos_name).setVisible(true);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DoctorMode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
