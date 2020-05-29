package com.saiton.ccs.scaledao;

import com.saiton.ccs.database.Starter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.OracleCodec;

public class ScaleDAO {

    public static Starter star;//db connection
    Codec ORACLE_CODEC = new OracleCodec();
    private final Logger log = Logger.getLogger(this.getClass());

    public String generateID() {

        Integer id = null;
        String cid = null;
        String final_id = null;
        if (star.con == null) {
            log.error("Databse connection failiure.");
            return null;
        } else {
            try {

                Statement st = star.con.createStatement();
                Statement ste = star.con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT MAX(id) as ID FROM scale");

                while (rs.next()) {
                    id = rs.getInt("id");
                }
                ResultSet rss = ste.executeQuery(
                        "SELECT weight_scale_id FROM scale WHERE id= " + id
                        + "");

                while (rss.next()) {
                    cid = rss.getString("weight_scale_id");
                }

                if (id != 0) {
                    String original = cid.split("D")[1];
                    int i = Integer.parseInt(original) + 1;

                    if (i < 10) {
                        final_id = "WID000" + i;
                    } else if (i >= 10 && i < 100) {
                        final_id = "WID00" + i;
                    } else if (i >= 100 && i < 1000) {
                        final_id = "WID0" + i;
                    } else if (i >= 1000 && i < 10000) {
                        final_id = "WID" + i;
                    }
                    return final_id;

                } else {
                    return "WID0001";
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

    public Boolean insertWeight(
            String weightScaleId,
            String scaleId,
            String customerCode,
            String desc,
            String reelNo,
            String jobNo,
            String size,
            String length,
            String width,
            String epfNo,
            String batchNo,
            String machine,
            String guage,
            double qty,
            double grossWeight,
            double netweight,
            double coreWeight,
            String date,
            String film
    ) {
        String encodedWeightScaleId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                weightScaleId);
        String encodedScaleId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                scaleId);
        String encodedCustomerCode = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                customerCode);
        String encodedDesc = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, desc);
        String encodedReelNo = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, reelNo);
        String encodedJobNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, jobNo);
        String encodedSize = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, size);
        String encodedLength = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, length);
        String encodedwidth = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, width);
        String encodedEpfNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, epfNo);
        String encodedBatchNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                batchNo);
        String encodedMachine = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                machine);
        String encodedGuage = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, guage);
        String encodedDate = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, date);

        if (star.con == null) {
            log.error("Databse connection failiure.");
            return false;
        } else {
            try {

                PreparedStatement ps = star.con.prepareStatement(
                        "INSERT INTO scale("
                        + " `weight_scale_id`,"
                        + " `scale_id`,"
                        + " `customer_code`,"
                        + " `description`,"
                        + " `reel_no`,"
                        + " `job_no`,"
                        + " `size`,"
                        + " `length`,"
                        + " `width`,"
                        + " `epf_no`,"
                        + " `batch_no`,"
                        + " `machine`,"
                        + " `gauge`,"
                        + " `qty`,"
                        + " `gross_weight`,"
                        + " `net_weight`,"
                        + " `core_weight`,"
                        + " `date`,"
                        + " `film`"
                        + " ) "
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                ps.setString(1, encodedWeightScaleId);
                ps.setString(2, encodedScaleId);
                ps.setString(3, encodedCustomerCode);
                ps.setString(4, encodedDesc);
                ps.setString(5, encodedReelNo);
                ps.setString(6, encodedJobNo);
                ps.setString(7, encodedSize);
                ps.setString(8, encodedLength);
                ps.setString(9, encodedwidth);
                ps.setString(10, encodedEpfNo);
                ps.setString(11, encodedBatchNo);
                ps.setString(12, encodedMachine);
                ps.setString(13, encodedGuage);
                ps.setDouble(14, qty);
                ps.setDouble(15, grossWeight);
                ps.setDouble(16, netweight);
                ps.setDouble(17, coreWeight);
                ps.setString(18, encodedDate);
                ps.setString(19, film);

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

    public ArrayList<ArrayList<String>> searchCustomerDetailsDetails(
            String searchTerm) {

        String encodedSearchTerm = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                searchTerm);

        String customerCode = null;
        String customerName = null;

        ArrayList<ArrayList<String>> mainList
                = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT * "
                        + "From customer "
                        + "Where customer_code LIKE ? or customer_name LIKE ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedSearchTerm + "%");
                pstmt.setString(2, encodedSearchTerm + "%");
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    customerCode = r.getString("customer_code");
                    customerName = r.getString("customer_name");

                    list.add(customerCode);
                    list.add(customerName);

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

    public ArrayList<ArrayList<String>> searchSizeDetailsDetails(
            String searchTerm) {

        String encodedSearchTerm = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                searchTerm);

        String size = null;

        ArrayList<ArrayList<String>> mainList
                = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT * "
                        + "From size "
                        + "Where size LIKE ?  ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedSearchTerm + "%");

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    size = r.getString("size");

                    list.add(size);

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

    public ArrayList<ArrayList<String>> searchMachineDetailsDetails(
            String searchTerm) {

        String encodedSearchTerm = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                searchTerm);

        String size = null;

        ArrayList<ArrayList<String>> mainList
                = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Database connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT * "
                        + "From machine "
                        + "Where machine_name LIKE ?  ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedSearchTerm + "%");

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    size = r.getString("machine_name");

                    list.add(size);

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

    public ArrayList<String> loadScaleItem() {

        String scaleNames = null;
        ArrayList list = new ArrayList();

        if (star.con == null) {
            log.error(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT * FROM scale_register ";
                PreparedStatement pstmt = star.con.prepareStatement(query);
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    scaleNames = r.getString("scale_name");
                    list.add(scaleNames);

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

    public String getScaleId(String scaleName) {

        String category = null;
        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return category;
        } else {
            try {

                String query = "SELECT * FROM "
                        + " scale_register "
                        + "where scale_name = ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, scaleName);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    category = r.getString("scale_id");
                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error(
                            "Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement");

                } else if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry for list");

                }
                return category;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return category;
            }
        }
        return category;
    }

    public String getCustomerCode(String customerName) {

        String category = null;
        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return category;
        } else {
            try {

                String query = "SELECT * FROM "
                        + " customer "
                        + "where customer_name = ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, customerName);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    category = r.getString("customer_code");
                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error(
                            "Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement");

                } else if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry for list");

                }
                return category;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return category;
            }
        }
        return category;
    }

    public ArrayList<String> loadingScaleConfigs(String grnId) {
        String encodedScaleName = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                grnId);

        String scaleName = null;
        String scaleId = null;
        String comPort = null;
        String boardRate = null;

        ArrayList<String> list = new ArrayList<>();

        if (star.con == null) {
            log.error("Databse connection failiure.");

        } else {

            try {
                String query = "select * "
                        + "from scale_register "
                        + "where scale_name=? ";
                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedScaleName);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    scaleId = r.getString("scale_id");
                    scaleName = r.getString("scale_name");
                    comPort = r.getString("com_port");
                    boardRate = r.getString("board_rate");

                    list.add(scaleId);
                    list.add(scaleName);
                    list.add(comPort);
                    list.add(boardRate);

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
        return list;
    }

}
