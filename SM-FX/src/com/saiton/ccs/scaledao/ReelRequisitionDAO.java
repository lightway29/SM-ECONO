/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saiton.ccs.scaledao;

import com.saiton.ccs.database.Starter;
import com.saiton.ccs.scale.ReelRequisitionController;
import static com.saiton.ccs.scaledao.ScaleDAO.star;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.OracleCodec;

/**
 *
 * @author lightway
 */
public class ReelRequisitionDAO {

    public static Starter star;//db connection
    Codec ORACLE_CODEC = new OracleCodec();
    private final Logger log = Logger.getLogger(this.getClass());

    public String generateID() {

        Integer id = null;
        String cid = null;
        String final_id = null;
        if (star.con == null) {
            log.error("Database connection failiure.");
            return null;
        } else {
            try {

                Statement st = star.con.createStatement();
                Statement ste = star.con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT MAX(id) as ID FROM reel");

                while (rs.next()) {
                    id = rs.getInt("id");
                }
                ResultSet rss = ste.executeQuery(
                        "SELECT reel_code FROM reel WHERE id= " + id
                        + "");

                while (rss.next()) {
                    cid = rss.getString("reel_code");
                }

                if (id != 0) {
                    String original = cid.split("L")[1];
                    int i = Integer.parseInt(original) + 1;

                    if (i < 10) {
                        final_id = "REL000" + i;
                    } else if (i >= 10 && i < 100) {
                        final_id = "REL00" + i;
                    } else if (i >= 100 && i < 1000) {
                        final_id = "REL0" + i;
                    } else if (i >= 1000 && i < 10000) {
                        final_id = "REL" + i;
                    }
                    return final_id;

                } else {
                    return "REL0001";
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException |
                    SQLException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {
                    log.error("Exception tag --> " + "Split character error"
                            + " " + e);
                } else if (e instanceof NumberFormatException) {
                    log.error("Exception tag --> "
                            + "Invalid number found in current id");
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
    }

    public ArrayList<ArrayList<String>> searchReelDetailsDetails(
            String searchTerm) {

        String encodedSearchTerm = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                searchTerm);

        String reelCode = null;
        String reelNumber = null;
        String serialNumber = null;
        String itemName = null;

        ArrayList<ArrayList<String>> mainList
                = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Database connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT * "
                        + "From reel "
                        + "Where reel_code LIKE ? or reel_number LIKE ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedSearchTerm + "%");
                pstmt.setString(2, encodedSearchTerm + "%");
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    reelCode = r.getString("reel_code");
                    reelNumber = r.getString("reel_number");
                    serialNumber = r.getString("serial_number");
                    itemName = r.getString("item_name");

                    list.add(reelCode);
                    list.add(serialNumber);
                    list.add(itemName);
                    list.add(reelNumber);

                    mainList.add(list);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error(
                            "Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement"
                            + " " + e);

                } else if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry for list");

                }
                return null;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return null;
            }
        }
        return mainList;
    }

    public ArrayList<String> loadingReelInfo(String reelId) {
        String encodedReelId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                reelId);

        String issuedWeight = null;
        String itemName = null;
        String description = null;
        //String logDate = null;
        String reelLiner = null;
        String gsm = null;
        String size = null;
        String reelFb = null;
        String reelNo = null;

        ArrayList<String> list = new ArrayList<>();

        if (star.con == null) {
            log.error("Databse connection failiure.");

        } else {

            try {
                String query
                        = "select * "
                        + "from reel "
                        + "where reel_code =? ";
                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedReelId);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    itemName = r.getString("item_name");
                    issuedWeight = r.getString("current_weight");
                    description = r.getString("item_des");
                    //logDate = r.getString("r.supplier_id");
                    reelLiner = r.getString("reel_liner");
                    gsm = r.getString("gsm");
                    size = r.getString("size");
                    reelFb = r.getString("reel_fb");
                    reelNo = r.getString("reel_number");

                    list.add(itemName);
                    list.add(issuedWeight);
                    list.add(description);
                    list.add(reelLiner);
                    list.add(gsm);
                    list.add(size);
                    list.add(reelFb);
                    list.add(reelNo);

                }
            } catch (NullPointerException | SQLException e) {

                if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry passed");

                } else if (e instanceof SQLException) {

                    log.error(
                            "Exception tag --> " + "Invalid sql statement " + e.
                            getMessage());

                }
                return null;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return null;
            }
        }
        return list;
    }

    public ArrayList<ArrayList<String>> reelLogDetails(String reelCode) {

        String encodedreelCode = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                reelCode);

        String timeStamp = null;
        String issueWeight = null;
        String flag = null;
        String returnWeight = null;
        String returnTimeStamp = null;

        ArrayList<ArrayList<String>> mainList
                = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Database connection failiure. ");
            return null;

        } else {
            try {

                String query
                        = "SELECT * "
                        + "FROM reel_log "
                        + "WHERE reel_code = ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedreelCode);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    timeStamp = r.getString("time_stamp");
                    
                    issueWeight = r.getString("issue_weight");
                    returnTimeStamp = r.getString("return_time_stamp");
                    returnWeight = r.getString("return_weight");
                    

                    list.add(timeStamp);
                    
                    list.add(issueWeight);
                    list.add(returnTimeStamp);
                    list.add(returnWeight);

                    mainList.add(list);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error(
                            "Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement");
                    e.printStackTrace();

                } else if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry for list");

                }
                return null;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return null;
            }
        }
        return mainList;
    }

    public String getDbFlag(String reelCode) {
        String encodedReelCode = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                reelCode);

        String flag = null;

        if (star.con == null) {
            log.error("Database connection failiure.");
            return null;
        } else {

            try {
                String query = "SELECT * FROM  reel where "
                        + "reel_code = ? ";
                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedReelCode);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    flag = r.getString("flag");

                }

            } catch (NullPointerException | SQLException e) {

                if (e instanceof NullPointerException) {
                    log.error("Exception tag --> " + "Empty entry passed");
                } else if (e instanceof SQLException) {
                    log.error(
                            "Exception tag --> " + "Invalid sql statement " + e.
                            getMessage());
                }
                return null;
            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return null;
            }
        }
        return flag;
    }

    public boolean saveDataToDB(String filePath) {

        int batchSize = 20;

        try {
            FileInputStream inputStream = new FileInputStream(filePath);

            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();

            if (star.con == null) {
                log.error("Databse connection failiure.");
                return false;
            } else {
                try {
                    int count = 0;
                    rowIterator.next(); // skip the header row

//                    PreparedStatement ps = star.con.prepareStatement(
//                            "INSERT INTO `reel` ("
//                            + " `reel_code`, "
//                            + "`item_no`, "
//                            + "`item_category`, "
//                            + "`lot_no`, "
//                            + "`serial_number`, "
//                            + "`item_name`, "
//                            + "`item_des`,"
//                            + " `location`, "
//                            + "`gsm`, "
//                            + "`reel_width`, "
//                            + "`reel_diameter`, "
//                            + "`reel_number`, "
//                            + "`initial_weight`, "
//                            + "`qty`, "
//                            + "`remaining_qty`, "
//                            + "`size`, "
//                            + "`current_weight`"
//                            + ", `flag`, "
//                            + "`reel_fb`)  VALUES(?,?,?,?,?,?,?,?,?,?,?,"
//                            + "?,?,?,?,?,?,?,?)");
                    PreparedStatement ps = star.con.prepareStatement(
                            "INSERT INTO `reel` ("
                            + " `reel_code`, "
                            + "`item_no`, "
                            + "`item_category`, "
                            + "`lot_no`, "
                            + "`serial_number`, "
                            + "`item_name`"
                            + ")  VALUES(?,?,?,?,?,?)");
                    while (rowIterator.hasNext()) {
                        Row nextRow = rowIterator.next();
                        DataFormatter formatter = new DataFormatter();
                        Iterator<Cell> cellIterator = nextRow.cellIterator();

                        while (cellIterator.hasNext()) {
                            Cell nextCell = cellIterator.next();
                            //Cell cell = sheet.getRow(i).getCell(0);
                            String j_username = formatter.formatCellValue(
                                    nextCell);
                            int columnIndex = nextCell.getColumnIndex();

                            switch (columnIndex) {
                                case 0:
                                    
//                                    ps.setString(1, formatter.formatCellValue(
//                                            nextCell));
                                    ps.setString(1, generateID());
//                                   
                                    break;
                                case 1:
                                    ps.setString(2, formatter.formatCellValue(
                                            nextCell));

                                case 2:
                                    ps.setString(3, formatter.formatCellValue(
                                            nextCell));

                                case 3:
                                    ps.setString(4, formatter.formatCellValue(
                                            nextCell));

                                case 4:
                                    ps.setString(5, formatter.formatCellValue(
                                            nextCell));

                                case 5:
                                    ps.setString(6, formatter.formatCellValue(
                                            nextCell));


                            }

                        }

                        ps.addBatch();

                        if (count % batchSize == 0) {
                            int[] val = ps.executeBatch();
                            for (int w : val) {
                                if (w == 1) {
                                } else {
                                    return false;
                                }
                            }
                        }

                    }

                    // execute the remaining queries
                    int[] val = ps.executeBatch();
                    for (int w : val) {
                        if (w == 1) {
                            System.out.println("Entry state " + w);
                        } else {
                            return false;
                        }
                    }
                    return true;

                } catch (SQLException e) {

                    if (e instanceof SQLException) {
                        log.error("Exception tag --> "
                                + "Invalid sql statement "
                                + e.getMessage());
                    }
                    return false;

                } catch (Exception e) {
                    log.error("Exception tag --> " + "Error ");
                    e.printStackTrace();
                    return false;
                }
            }

        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReelRequisitionController.class.
                    getName()).
                    log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ReelRequisitionController.class.
                    getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;

    }
    
    public Boolean insertIssueLog(
            String reelCode,
            String issue_timestamp,
            String issue_weight
) {
        String encodedReelCode = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                reelCode);
        String encodedIssueTimestamp = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, issue_timestamp);
        String encodedIssueWeight = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, issue_weight);


        if (star.con == null) {
            log.error("Databse connection failiure.");
            return false;
        } else {
            try {

                PreparedStatement ps = star.con.prepareStatement(
                        "INSERT INTO `reel_log` ("
                                + "`reel_code`,"
                                + " `time_stamp`,"
                                + " `issue_weight`"
                                + " ) "
                        + "VALUES(?,?,?,?)");
                
                 


                ps.setString(1, encodedReelCode);
                ps.setString(2, encodedIssueTimestamp);
                ps.setString(3, encodedIssueWeight);


                int val = ps.executeUpdate();

                if (val == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (SQLException e) {

                if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                }
                return false;

            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }
        }
    }

}
