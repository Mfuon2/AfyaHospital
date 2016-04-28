/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cashiermode;

/**
 *
 * @author Daniel
 */
public class Test {
   private String title;
   private double charge;
   
 public Test(String title,double charge){
 this.title = title;
 this.charge=charge;
 
 }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }
 
 
}
