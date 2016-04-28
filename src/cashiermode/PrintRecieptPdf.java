/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cashiermode;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Daniel
 */
public class PrintRecieptPdf {
    
    public static void printPdf() {
        Document transPdf = null;
        PdfWriter writer = null;
       
        // NumberFormat fmt = NumberFormat.getPercentInstance();
        // DecimalFormat fmt1 = new DecimalFormat("0.0");
        Font font1 = new Font(Font.FontFamily.HELVETICA, 3, Font.NORMAL);
        Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 7);
        Font font3 = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);
        Font font4 = new Font(Font.FontFamily.COURIER, 9, Font.BOLD);

        try {
            transPdf = new Document();
            File file = new File("Report.pdf");
            System.out.println("File crearted");

            writer = PdfWriter.getInstance(transPdf, new FileOutputStream(file));
            transPdf.open();
            transPdf.setMargins(0,5,0,2);
        } catch (FileNotFoundException | DocumentException ex) {

        }

       
    try{
            transPdf.add(new Paragraph("                                             TERM TWO ACCADEMIC REPORT FORM", font3));
            transPdf.add(new Paragraph("NAME :                                    ADM NO:             FORM             ", font3));
            transPdf.add(new Paragraph("FORM POS         OUT OF           CLASS POS           OUT OF         ", font3));

            PdfPTable table = new PdfPTable(10);  // 4 column
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            PdfPCell cell0 = new PdfPCell(new Paragraph("SUBJECT", font2));
            PdfPCell cell1 = new PdfPCell(new Paragraph("CAT1", font2));
            PdfPCell cell2 = new PdfPCell(new Paragraph("CAT2", font2));
            PdfPCell cell3 = new PdfPCell(new Paragraph("EXAM", font2));
            PdfPCell cell4 = new PdfPCell(new Paragraph("TOTAL", font2));
            PdfPCell cell5 = new PdfPCell(new Paragraph("GRADE", font2));
            PdfPCell cell6 = new PdfPCell(new Paragraph("PNTS", font2));
            PdfPCell cell7 = new PdfPCell(new Paragraph("POS", font2));
            PdfPCell cell8 = new PdfPCell(new Paragraph("RMK", font2));
            PdfPCell cell9 = new PdfPCell(new Paragraph("SIGN", font2));

            // cell2.setColspan(2);
            table.addCell(cell0);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            table.addCell(cell8);
            table.addCell(cell9);

            for (int i = 0; i < 11; i++) {

//                cell0 = new PdfPCell(new Paragraph(mysubs[i], font2));
                cell1 = new PdfPCell(new Paragraph("", font2));
                cell2 = new PdfPCell(new Paragraph("", font2));
                cell3 = new PdfPCell(new Paragraph("", font2));
                cell4 = new PdfPCell(new Paragraph("", font2));
                cell5 = new PdfPCell(new Paragraph("", font2));
                cell6 = new PdfPCell(new Paragraph("", font2));
                cell7 = new PdfPCell(new Paragraph("", font2));
                cell8 = new PdfPCell(new Paragraph("", font2));
                cell9 = new PdfPCell(new Paragraph("", font2));

                table.addCell(cell0);
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                table.addCell(cell7);
                table.addCell(cell8);
                table.addCell(cell9);

            }

           
            
            transPdf.add(table);
    
       
           

            Paragraph p = new Paragraph("Closing Date                                  Next Term Begins On                          ", font3);
            p.setSpacingBefore(185f);
            transPdf.add(p);
            transPdf.add(new Paragraph("Outstanding Fee                    Next Term Fee                     Total                 ", font3));
            transPdf.add(new Paragraph("Feedback                                                Sign             Date              ", font3));
            transPdf.add(new Paragraph("                       Parent/Guardian", font3));
            Paragraph lastp = new Paragraph("Closing date is on   27/14/2005", font3);
            lastp.setSpacingAfter(0f);
            transPdf.add(lastp);
                    
            //transPdf.newPage();

        } catch (DocumentException ex) {
            System.err.println("NOT YET.." + ex.getMessage());
        }

        transPdf.close();

    }

}
