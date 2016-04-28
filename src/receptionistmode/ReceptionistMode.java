/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package receptionistmode;

import doctormode.Main;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class ReceptionistMode {
    String hos_name = Main.hos_name;
    
    public ReceptionistMode(int recept_id) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ReceptionistMain(recept_id,hos_name).setVisible(true);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(ReceptionistMode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
