/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saiton.ccs.uihandle;

import com.saiton.ccs.database.Starter;
import java.awt.Color;
import java.awt.Container;
import java.text.DateFormat.Field;
import java.util.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.view.JRViewer;
import org.apache.log4j.Logger;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.OracleCodec;

public class ReportGenerator extends JFrame {

    JasperPrint print;
    public static Starter star;//db connection
    Codec ORACLE_CODEC = new OracleCodec();
    private Logger log = Logger.getLogger(this.getClass());

    public ReportGenerator(String fileName) {
        this(fileName, null);
    }

    public ReportGenerator(String fileName, HashMap parameter) {

        super("Report");

        try {
            if (star.con == null) {
                JOptionPane.showMessageDialog(null, "Databse connection failiure. \n Please contact the administrator.");

            } else {

                print = JasperFillManager.fillReport(fileName, parameter, star.con);
                JRViewer jRViewer = new JRViewer(print);
 //               ((JPanel)jRViewer.getComponent(0)).remove(0); //save               
//                ((JPanel)jRViewer.getComponent(0)).remove(0); //print
//                ((JPanel)jRViewer.getComponent(0)).remove(0); //refresh
//                ((JPanel)jRViewer.getComponent(0)).remove(0); //space                

                Container c = getContentPane();
                c.add(jRViewer);

                

            }

        } catch (Exception e) {
            log.error("Exception tag --> " + "Jasper error" + e.getMessage());

        }
        setBounds(180, 30, 1000, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void jasperToPDF(String fileName, HashMap parameter, String destFileNamePdf) {

        try {
            print = JasperFillManager.fillReport(fileName, parameter, star.con);
            if (!(print.equals(null) && star.con.equals(null))) {
                JasperExportManager.exportReportToPdfFile(print, destFileNamePdf);
            } else {
                JOptionPane.showMessageDialog(null, "Jasper report should be generated first");
            }

        } catch (Exception e) {
            log.error("Exception tag --> " + "Jasper error" + e.getMessage());
        }
    }

//    private static void initFX(JFXPanel fxPanel) {
//        // This method is invoked on JavaFX thread
//        Scene scene = createScene();
//        fxPanel.setScene(scene);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                initAndShowGUI();
//            }
//        });
//    }
}
