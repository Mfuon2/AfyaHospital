package cashiermode;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Daniel
 */
public class Receipt extends javax.swing.JFrame {

    static Database db;
    static double total = 0;
    static double doce = 0;
    static double labTest = 0;
    static ArrayList<Object> records;
    static Patient patient;
    static Test test;
    static Date date;
    static JDialog frame;
    static SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    static int cashierId;
    static File file;
    static private int record_id;

    public Receipt(int id) {
        Receipt.cashierId = id;
        initComponents();
        /* Create and display the form */
        db = new Database();
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int x = 0; x < 3; x++) {
            recordsTable.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        patientIdL = new javax.swing.JLabel();
        searchPatientB = new javax.swing.JButton();
        patientIdTF = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        patientName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        recordsTable = new javax.swing.JTable();
        payBillButton = new javax.swing.JButton();
        paidStateL = new javax.swing.JLabel();
        patientNameL = new javax.swing.JLabel();
        logoutCashier = new javax.swing.JButton();
        record_idL = new javax.swing.JLabel();
        record_idTF = new javax.swing.JTextField();
        searchAllB = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cashier");

        patientIdL.setText("Patient ID");

        searchPatientB.setText("Search");
        searchPatientB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPatientBActionPerformed(evt);
            }
        });

        patientIdTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                patientIdTFKeyTyped(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        recordsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SrNo", "Description", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(recordsTable);

        payBillButton.setText("Pay");
        payBillButton.setEnabled(false);
        payBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payBillButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(patientName, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(payBillButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(patientNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(paidStateL, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paidStateL)
                    .addComponent(patientNameL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(payBillButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        logoutCashier.setText("Logout");
        logoutCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutCashierActionPerformed(evt);
            }
        });

        record_idL.setText("Record  ID");

        record_idTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                record_idTFKeyTyped(evt);
            }
        });

        searchAllB.setText("All Debt Records");
        searchAllB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAllBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(13, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(patientIdL, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(record_idL, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(patientIdTF, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(record_idTF, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchPatientB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchAllB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logoutCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(patientIdL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchPatientB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(patientIdTF))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchAllB, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(logoutCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(record_idTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(record_idL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchPatientBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPatientBActionPerformed
        // TODO add your handling code here:
        searchSingleRecordB();

    }//GEN-LAST:event_searchPatientBActionPerformed
    private void searchSingleRecordB() {
        payBillButton.setEnabled(false);
        patientNameL.setText("");
        paidStateL.setText("");
         record_id=0;
        DefaultTableModel model = (DefaultTableModel) recordsTable.getModel();
        //Clearing the table model before a new element is added to the system...
        if (model.getRowCount() > 0) {
            for (int j = model.getRowCount() - 1; j > -1; j--) {
                model.removeRow(j);
            }
        }

        String id = patientIdTF.getText().trim();
        String record = record_idTF.getText().trim();
        int patient_id;
        if (id == "" || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Patient ID First ", "Empty Patient ID", JOptionPane.ERROR_MESSAGE);
        } else if (record == "" || record.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Record ID First ", "Empty Record ID", JOptionPane.ERROR_MESSAGE);
        } else {
            patient_id = Integer.parseInt(id);
            record_id = Integer.parseInt(record);
            records = db.getPatientRecords(patient_id, record_id);
            if (records.isEmpty() || records.size() < 3) {
                JOptionPane.showMessageDialog(this, "No Patient Debt Records Found,Please Check The Id and Try Again", "No Debt Records", JOptionPane.INFORMATION_MESSAGE);

            } else {
                total = 0;
                doce = 0;
                labTest = 0;
                int i = 4;

                patient = (Patient) records.get(1);
                patientNameL.setText(patient.getName());
                patientNameL.setForeground(Color.BLUE);

                if ((Integer) records.get(0) >0) {
                    paidStateL.setText("Bill Status : Already Paid");
                    paidStateL.setForeground(Color.RED);
                    payBillButton.setEnabled(false); 
                    System.out.println("STTA "+(Integer) records.get(0));
                } else {
                   
                    paidStateL.setText("Bill Status : Not Paid");
                    paidStateL.setForeground(Color.BLUE);
                    payBillButton.setEnabled(true);
                     System.out.println("STTA "+(Integer) records.get(0));
                }
                if (records.size() >= 5) {
                    for (; i < records.size(); i++) {
                        test = (Test) records.get(i);

                        model.addRow(new Object[]{i - 3, test.getTitle(), test.getCharge()});
                        labTest += test.getCharge();
                        System.out.println("Title..1" + test.getTitle());
                        System.out.println("Charge..1" + test.getCharge());

                    }
                }

                doce = (double) records.get(3);
                total = labTest + doce;
                model.addRow(new Object[]{i - 3, "Doctors Fee", " " + doce});
                model.addRow(new Object[]{"", "", ""});
                model.addRow(new Object[]{"Total", "", "Ksh. " + total});

            }
        }
        System.out.println("Butto  Working");
    }

    private void searchAllRecordB() {
        payBillButton.setEnabled(false);
        patientNameL.setText("");
        paidStateL.setText("");

        DefaultTableModel model = (DefaultTableModel) recordsTable.getModel();
        //Clearing the table model before a new element is added to the system...
        if (model.getRowCount() > 0) {
            for (int j = model.getRowCount() - 1; j > -1; j--) {
                model.removeRow(j);
            }
        }

        String id = patientIdTF.getText().trim();

        int patient_id;
        if (id == "" || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Patient ID First ", "Empty Patient ID", JOptionPane.ERROR_MESSAGE);
        } else {
            patient_id = Integer.parseInt(id);
            records = db.getAllUnpaidPatientRecords(patient_id);
            if (records.isEmpty()) {
              JOptionPane.showMessageDialog(this, "No Patient Debt Records Found,Please Check The Id and Try Again", "No Debt Records", JOptionPane.INFORMATION_MESSAGE);

            } else {
                total = 0;
                doce = 0;
                labTest = 0;
                int i = 0;

                patient = (Patient) records.get(records.size() - 2);
                patientNameL.setText(patient.getName());
                paidStateL.setText("");
                ArrayList<Test> tests;
                ArrayList<Double> docFee= (ArrayList<Double>)records.get(records.size() - 1);

                ArrayList<Integer> recordIds = (ArrayList<Integer>) records.get(records.size() - 3);
                  if (records.size() > 3) {
                for (; i < records.size() - 3; i++) {
                    model.addRow(new Object[]{"", "Record ID  " + recordIds.get(i), ""});
                  
                        int j = 0;
                        tests = (ArrayList<Test>) records.get(i);
                        for (; j < tests.size(); j++) {

                            model.addRow(new Object[]{j + 1, tests.get(j).getTitle(), tests.get(j).getCharge()});
                            labTest += tests.get(j).getCharge();
                            System.out.println("Title..1" + tests.get(j).getTitle());
                            System.out.println("Charge..1" + tests.get(j).getCharge());
                        }
                        doce = docFee.get(0);
                        total = labTest + doce;
                        model.addRow(new Object[]{j + 1, "Doctors Fee", " " + doce});
                        model.addRow(new Object[]{"", "", ""});
                        model.addRow(new Object[]{"Total", "", "Ksh. " + total});

                    } 

                    }
                  else{
                      for(int k=0;k<docFee.size();k++){
                       model.addRow(new Object[]{"", "Record ID  " + recordIds.get(k), ""});
                        doce = docFee.get(k);
                        total += doce;
                        model.addRow(new Object[]{i + 1, "Doctors Fee", "  " + doce});
                        model.addRow(new Object[]{"", "", ""});
                                             
                  }
                       model.addRow(new Object[]{"Total", "", "Ksh. " + total});
                  } 

            }
        }
        System.out.println("Butto  Working");
    }
    private void patientIdTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_patientIdTFKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER) || (c == KeyEvent.VK_SPACE))) {
            System.out.println("here  " + c);
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Patient ID cannot contain " + c, "Patient ID Error", JOptionPane.ERROR_MESSAGE);
            evt.consume();
        }
        if (c == KeyEvent.VK_SPACE) {
            evt.consume();
            getToolkit().beep();
        }
        if (c == KeyEvent.VK_ENTER) {
            /* if(record_idTF.getText().trim().isEmpty())
             JOptionPane.showMessageDialog(null,"Record ID field Cannot be empty ","Record ID Error",JOptionPane.ERROR_MESSAGE); 
             else
             searchSingleRecordB();*/
            evt.consume();
        }
        if (c == KeyEvent.VK_BACKSPACE) {

            DefaultTableModel model = (DefaultTableModel) recordsTable.getModel();
            //Clearing the table model before a new element is added to the system...
            if (model.getRowCount() > 0) {
                for (int j = model.getRowCount() - 1; j > -1; j--) {
                    model.removeRow(j);
                }
               
            }
                patientNameL.setText("");
                paidStateL.setText("");
                payBillButton.setEnabled(false);

        }

        if (c == KeyEvent.VK_DELETE) {

            DefaultTableModel model = (DefaultTableModel) recordsTable.getModel();
            //Clearing the table model before a new element is added to the system...
            if (model.getRowCount() > 0) {
                for (int j = model.getRowCount() - 1; j > -1; j--) {
                    model.removeRow(j);
                }
               
            }
             patientNameL.setText("");
              paidStateL.setText("");
             payBillButton.setEnabled(false);
        }


    }//GEN-LAST:event_patientIdTFKeyTyped


    private void payBillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payBillButtonActionPerformed
        // TODO add your handling code here:
        
        
        if(db.paidStatus(patient.getId(), record_id)){
        JOptionPane.showMessageDialog(this, "The Bill is Already Cleared" , "Bill Payed", JOptionPane.ERROR_MESSAGE);
        
        }
        else{
        PayCash p = new PayCash();
        p.getTotalTF().setText(String.valueOf(total));
        frame = new JDialog(new Receipt(cashierId), "Pay Bill", true);
        frame.getContentPane().add(p);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(this);
        frame.pack();
        frame.setVisible(true);
        }

    }//GEN-LAST:event_payBillButtonActionPerformed

    private void record_idTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_record_idTFKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER) || (c == KeyEvent.VK_SPACE))) {
            System.out.println("here  " + c);
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Record ID cannot contain " + c, "Record ID Error", JOptionPane.ERROR_MESSAGE);
            evt.consume();
        }
        if (c == KeyEvent.VK_SPACE) {
            evt.consume();
            getToolkit().beep();
        }
        if (c == KeyEvent.VK_ENTER) {
            /*if(patientIdTF.getText().trim().isEmpty())
             JOptionPane.showMessageDialog(null,"Patient ID field Cannot be empty","Patient ID Error",JOptionPane.ERROR_MESSAGE); 
             else
             searchSingleRecordB();*/
            evt.consume();
        }
        if (c == KeyEvent.VK_BACKSPACE) {

            DefaultTableModel model = (DefaultTableModel) recordsTable.getModel();
            //Clearing the table model before a new element is added to the system...
            if (model.getRowCount() > 0) {
                for (int j = model.getRowCount() - 1; j > -1; j--) {
                    model.removeRow(j);
                }
               
            }
                patientNameL.setText("");
                paidStateL.setText("");
                payBillButton.setEnabled(false);
        }

        if (c == KeyEvent.VK_DELETE) {

            DefaultTableModel model = (DefaultTableModel) recordsTable.getModel();
            //Clearing the table model before a new element is added to the system...
            if (model.getRowCount() > 0) {
                for (int j = model.getRowCount() - 1; j > -1; j--) {
                    model.removeRow(j);
                }
              
            }
              patientNameL.setText("");
                paidStateL.setText("");
                payBillButton.setEnabled(false);
        }

    }//GEN-LAST:event_record_idTFKeyTyped

    private void searchAllBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAllBActionPerformed
        // TODO add your handling code here:
        searchAllRecordB();
    }//GEN-LAST:event_searchAllBActionPerformed

    private void logoutCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutCashierActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_logoutCashierActionPerformed
    public void getpayBillButton() {
       paidStateL.setText("Bill Status : Already Paid");
        paidStateL.setForeground(Color.RED);
        payBillButton.setEnabled(false);
       
    }

    public static void generateReciept() {
        date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dat = ft.format(date);
        Document receiptPdf = null;
        PdfWriter writer = null;
        String d = String.valueOf(date);

        // NumberFormat fmt = NumberFormat.getPercentInstance();
        // DecimalFormat fmt1 = new DecimalFormat("0.0");
        // Font font1 = new Font(Font.FontFamily.HELVETICA, 3, Font.NORMAL);
        Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 7);
        Font font3 = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);
        Font font4 = new Font(Font.FontFamily.COURIER, 9, Font.BOLD);
         Font font5 = new Font(Font.FontFamily.COURIER, 8, Font.BOLD);

        try {
            receiptPdf = new Document();
            file = new File("/root/Desktop/receipts/" + dateFormat.format(date) + " ID NO " + patient.getId() + ".pdf");
            System.out.println("File crearted");

            writer = PdfWriter.getInstance(receiptPdf, new FileOutputStream(file));
            receiptPdf.setMargins(10, 10, 30, 30);
            receiptPdf.setPageSize(PageSize.A5);
            receiptPdf.open();

        } catch (FileNotFoundException | DocumentException ex) {

        }

        try {
            Image image1 = null;
            try {
                image1 = Image.getInstance("hosi2.png");
                image1.scaleAbsolute(150, 50);
            } catch (BadElementException | IOException ex) {
                System.err.println("No Image " + ex.getMessage());
            }

            PdfPTable header = new PdfPTable(2);  // 2 column
            header.setWidthPercentage(80);

            List list = new List();
            list.setListSymbol("");
            list.add(new ListItem("", font5));
            list.add(new ListItem("AFYA  HOSPITAL,", font5));
            list.add(new ListItem("BOX 210 TEll AVIV.", font5));
            list.add(new ListItem("TELEPHONE: 0701010107", font5));
            list.add(new ListItem("EMAIL: kadunye@myemail.com", font5));

            PdfPCell logo = new PdfPCell(image1, true);
            logo.setBorder(Rectangle.NO_BORDER);
            logo.setFixedHeight(20f);
            PdfPCell adress = new PdfPCell();
            adress.setPaddingTop(0f);
            adress.setPaddingLeft(20f);
            adress.setPaddingBottom(0f);
            adress.addElement(list);
            adress.setBorder(Rectangle.NO_BORDER);

            header.setWidths(new int[]{200, 300});
            header.addCell(logo);
            header.addCell(adress);

            receiptPdf.add(header);

            receiptPdf.add(new Paragraph("                                                         Date:   " + dat, font3));
            receiptPdf.add(new Paragraph("         NAME : " + patient.getName(), font3));
            // receiptPdf.add(new Paragraph("FORM POS         OUT OF           CLASS POS           OUT OF         ", font3));

            PdfPTable table = new PdfPTable(3);  // 3 column
            table.setWidthPercentage(80);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            PdfPCell cell0 = new PdfPCell(new Paragraph("Sr No", font2));
            PdfPCell cell1 = new PdfPCell(new Paragraph("Particulars", font2));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Amount", font2));

            table.addCell(cell0);
            table.addCell(cell1);
            table.addCell(cell2);

            int j = 4;
            if (records.size() >= 5) {
                for (; j < records.size(); j++) {
                    test = (Test) records.get(j);

                    cell0 = new PdfPCell(new Paragraph(String.valueOf(j - 3), font2));
                    cell1 = new PdfPCell(new Paragraph(test.getTitle(), font2));
                    cell2 = new PdfPCell(new Paragraph(String.valueOf(test.getCharge()), font2));
                    table.addCell(cell0);
                    table.addCell(cell1);
                    table.addCell(cell2);
                }

            }

            double doce = (double) records.get(3);
            PdfPCell cell = new PdfPCell(new Paragraph(String.valueOf(j - 3), font2));
            PdfPCell docCell = new PdfPCell(new Paragraph("Doctor's Fee", font2));
            PdfPCell feeCell = new PdfPCell(new Paragraph(String.valueOf(doce), font2));
            table.addCell(cell);
            table.addCell(docCell);
            table.addCell(feeCell);

            cell0 = new PdfPCell(new Paragraph(String.valueOf(j - 2), font2));
            cell1 = new PdfPCell(new Paragraph("Total", font4));
            cell1.setPadding(5f);
            cell2 = new PdfPCell(new Paragraph("Ksh. " + String.valueOf(total), font4));
            table.addCell(cell0);
            table.addCell(cell1);
            table.addCell(cell2);

            receiptPdf.add(table);

            receiptPdf.add(new Paragraph("                             RECIEVED PAYMENT    ", font4));
            receiptPdf.add(new Paragraph("                             BY : " + db.getCashierName(cashierId) + " SIGN :", font4));

            receiptPdf.close();
        } catch (DocumentException ex) {
            System.err.println("NOT YET.." + ex.getMessage());
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutCashier;
    private javax.swing.JLabel paidStateL;
    private javax.swing.JLabel patientIdL;
    private javax.swing.JTextField patientIdTF;
    private javax.swing.JLabel patientName;
    private javax.swing.JLabel patientNameL;
    private javax.swing.JButton payBillButton;
    private javax.swing.JLabel record_idL;
    private javax.swing.JTextField record_idTF;
    private javax.swing.JTable recordsTable;
    private javax.swing.JButton searchAllB;
    private javax.swing.JButton searchPatientB;
    // End of variables declaration//GEN-END:variables
}
