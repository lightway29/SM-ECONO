/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saiton.ccs.stockdao;

import com.saiton.ccs.database.Starter;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.OracleCodec;

public class ExportStockDataDAO {

    public static Starter star;//db connection
    Codec ORACLE_CODEC = new OracleCodec();
    private Logger log = Logger.getLogger(this.getClass());
    String csvFilePath=".//stockdata.csv";

    public String exportStockDataCSV(String fromDate,String toDate) {
        fromDate = fromDate+" 00:00:00";
        toDate = toDate+" 00:00:00";
        
        System.out.println("From Date - "+fromDate);
        System.out.println("To Date - "+toDate);

        String itemNo = null;

        if (star.con == null) {
            log.error("Databse connection failiure.");

        } else {

            try {
          
                   String query = "SELECT rl.id,rl.return_time_stamp,"
                           + "r.item_no,r.reel_number,r.item_name,"
                           + "r.gsm,r.reel_width,rl.issue_weight "
                           + "FROM reel AS r "
                           + "JOIN reel_log AS rl "
                           + "ON r.reel_code = rl.reel_code "
                           + "WHERE rl.return_time_stamp between ? and ? ";
                   
                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, fromDate);
                pstmt.setString(2, toDate);

                ResultSet r = pstmt.executeQuery();
                
                
             BufferedWriter writer = null;
                 
                 //File writer 
            writer = new BufferedWriter(new FileWriter(csvFilePath));
            
            //Write to the file
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader(r));
            printer.printRecords(r); 
            printer.close(true);

                while (r.next()) {

                    itemNo = r.getString("r.item_no");
                    System.out.println("Item No - "+itemNo);

                }
            } catch (NullPointerException | SQLException e) {

                if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry passed");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());

                }
                return null;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return null;
            }
        }
        return itemNo;
    }

}
