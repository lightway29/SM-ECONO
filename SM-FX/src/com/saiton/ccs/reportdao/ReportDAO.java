/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saiton.ccs.reportdao;

import com.saiton.ccs.database.Starter;

import org.apache.log4j.Logger;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.OracleCodec;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.owasp.esapi.ESAPI;

public class ReportDAO {

    public static Starter star;//db connection
    Codec ORACLE_CODEC = new OracleCodec();
    private final Logger log = Logger.getLogger(this.getClass());

    public ArrayList<ArrayList<String>> loadeReports(String reportName,String privilegeQuery) {

        String encodedReportName = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                reportName);

        String reportId = null;
        String reportNames = null;
        String reportURL = null;

        ArrayList<ArrayList<String>> Mainlist
                = new ArrayList<ArrayList<String>>();
        if (star.con == null) {
            log.error("Databse connection failiure.");
            return null;
        } else {
            try {
                                    
                String query
                        = String.format("select * "
                        + "from "
                        + "report "
                        + "where name LIKE ? ");

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedReportName + "%");

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    
                    ArrayList<String> list = new ArrayList<String>();

                    reportId = r.getString("rid");
                    reportNames = r.getString("name");
//                    reportURL = r.getString("url");

                    list.add(reportId);
                    list.add(reportNames);
//                    list.add(reportURL);
                    

                    Mainlist.add(list);

                }
                //   }

            } catch (NullPointerException | NumberFormatException | SQLException e) {

                if (e instanceof NullPointerException) {
                    log.error("Exception tag --> " + "Empty entry passed");
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
        return Mainlist;
    }

     public ArrayList<String> loadReports() {

        String reportName = null;
        ArrayList list = new ArrayList();

        if (star.con == null) {
            log.error(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT * FROM report ";
                PreparedStatement pstmt = star.con.prepareStatement(query);
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    reportName = r.getString("name");
                    list.add(reportName);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {
                    log.error("Exception tag --> "
                            + "Invalid entry location for list");
                } else if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement");
                } else if (e instanceof NullPointerException) {
                    log.error("Exception tag --> " + "Empty entry for list");
                }
                return null;
            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return null;
            }
        }
        return list;
    }
     
     
          public ArrayList<String> loadGsm() {

        String gsm = null;
        ArrayList list = new ArrayList();

        if (star.con == null) {
            log.error(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT DISTINCT gsm FROM reel ";
                PreparedStatement pstmt = star.con.prepareStatement(query);
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    gsm = r.getString("gsm");
                    list.add(gsm);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {
                    log.error("Exception tag --> "
                            + "Invalid entry location for list");
                } else if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement");
                } else if (e instanceof NullPointerException) {
                    log.error("Exception tag --> " + "Empty entry for list");
                }
                return null;
            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return null;
            }
        }
        return list;
    }
          
               
          public ArrayList<String> loadCategory() {

        String category = null;
        ArrayList list = new ArrayList();

        if (star.con == null) {
            log.error(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT DISTINCT item_category FROM reel ";
                PreparedStatement pstmt = star.con.prepareStatement(query);
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    category = r.getString("item_category");
                    list.add(category);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {
                    log.error("Exception tag --> "
                            + "Invalid entry location for list");
                } else if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement");
                } else if (e instanceof NullPointerException) {
                    log.error("Exception tag --> " + "Empty entry for list");
                }
                return null;
            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return null;
            }
        }
        return list;
    }
     
    public ArrayList<ArrayList<String>> searchItemDetails(String search) {

        String encodedSearch = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, search);

        String reportId = null;
        String reportName = null;

        ArrayList<ArrayList<String>> Mainlist
                = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query
                        = "select rid, name "
                        + "from report"
                        + "where name= ? ;";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedSearch + "%");

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    reportId = r.getString("rid");
                    reportName = r.getString("name");

                    list.add(reportId);
                    list.add(reportName);

                    Mainlist.add(list);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error("Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement");

                } else if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry for list");

                }
                return null;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return null;
            }
        }
        return Mainlist;
    }

    public ArrayList<String> LoadReportInfo(String reportId) {

        String encodedItemId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                reportId);

        String ItemId = null;
        String Name = null;

        ArrayList<String> list = new ArrayList<>();

        if (star.con == null) {
            log.info("Exception tag --> " + "Databse connection failiure. ");
        } else {
            try {

                String query
                        = "select `rid`, `name`"
                        + "from report  ";
                //    + "where `item_id`= ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedItemId);
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    ItemId = r.getString("rid");
                    Name = r.getString("name");

                    list.add(ItemId);
                    list.add(Name);

                }

            } catch (SQLException e) {
                if (e instanceof SQLException) {
                    log.info("Exception tag --> " + "Invalid sql statement" + e.
                            getMessage());
                }

            } catch (Exception e) {
                log.info("Exception tag --> " + "Error");
            }
        }
        return list;
    }

    public boolean checkingReportAvailability(String reportId) {
        String encodedReportId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                reportId);

        boolean available = false;

        if (star.con == null) {
            log.error("Databse connection failiure.");
            return false;
        } else {

            try {
                String query
                        = "SELECT * FROM  report where rid = ? ";
                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedReportId);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    available = true;
                }

            } catch (NullPointerException | SQLException e) {

                if (e instanceof NullPointerException) {
                    log.error("Exception tag --> " + "Empty entry passed");
                } else if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                }
                return false;
            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }
        }
        return available;
    }

    public boolean deleteReport(String reportId) {

        String encodedReportId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                reportId);

        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return false;
        } else {
            try {
                int val = 0;

                String sql
                        = "DELETE FROM report WHERE rid=? ";
                PreparedStatement stmt = star.con.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, encodedReportId);
                val = stmt.executeUpdate();

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

    public String getReportURL(String reportID) {

        String encodedItemId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, reportID);
        String url = null;

        if (star.con == null) {

            log.info("Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT url FROM report WHERE rid = ?";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedItemId);
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    url = r.getString("url");
                    return url;
                }

            } catch (ArrayIndexOutOfBoundsException | NumberFormatException |
                    SQLException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.info("Exception tag --> " + "Split character error");

                } else if (e instanceof NumberFormatException) {

                    log.info("Exception tag --> "
                            + "Invalid number found in current id");

                } else if (e instanceof SQLException) {

                    log.info("Exception tag --> " + "Invalid sql statement");

                }

                return null;
            } catch (Exception e) {

                log.info("Exception tag --> " + "Error");

                return null;
            }
        }
        return null;
    }
}
