package com.saiton.ccs.scale;

import arduino.Arduino;
import com.saiton.ccs.uihandle.StagePassable;
import com.saiton.ccs.validations.Validatable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.saiton.ccs.msgbox.MessageBox;
import com.saiton.ccs.msgbox.SimpleMessageBoxFactory;
import com.saiton.ccs.popup.ReelPopup;
import com.saiton.ccs.printerservice.ReportPath;
import com.saiton.ccs.scaledao.ReelRequisitionDAO;
import com.saiton.ccs.scaledao.ScaleDAO;
import com.saiton.ccs.scaledao.ScaleRegisterationDAO;
import com.saiton.ccs.uihandle.ReportGenerator;
import com.saiton.ccs.uihandle.StagePassable;
import com.saiton.ccs.uihandle.UiMode;
import com.saiton.ccs.validations.ErrorMessages;
import com.saiton.ccs.validations.MessageBoxTitle;
import com.saiton.ccs.validations.Validatable;
import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

public class ReelRequisitionController implements Initializable, Validatable,
        StagePassable {

    //<editor-fold defaultstate="collapsed" desc="Initcomponent">
    @FXML
    private Button btnRefreshItemCode;
    
    @FXML
    private TextField txtItemCode;
    
    @FXML
    private Button btnClose;
    
    @FXML
    private TextField txtDescription;
    
    @FXML
    private TextField txtReelNo;
    
    @FXML
    private Button btnRefreshReturnedWeight;
    
    @FXML
    private Button btnRePrint;
    
    @FXML
    private TextField txtReelFb;
    
    @FXML
    private TextField txtReturnedWeight;
    
    @FXML
    private TableView<ReelLog> tblItemList;
    
    @FXML
    private TextField txtLogDate;
    
    @FXML
    private Label lblItemId;
    
    @FXML
    private TextField txtGSM;
    
    @FXML
    private TextField txtSize;
    
    @FXML
    private TextField txtIssuedWeight;
    
    @FXML
    private Button btnLog;
    
    @FXML
    private TextField txtItemName;
    
    @FXML
    private Button btnSearchItemCode;
    
    @FXML
    private TextField txtReelLiner;
    
    private Stage stage;
    
    private ObservableList tableReelLogData = FXCollections.
            observableArrayList();
    
    ReelRequisitionDAO reelDAO = new ReelRequisitionDAO();

    //Reel Popup
    private TableView reelIdTable = new TableView();
    private ReelPopup reelpopup = new ReelPopup();
    private ObservableList<ReelPopup> reelData = FXCollections.
            observableArrayList();
    private PopOver reelPop;
    
    private MessageBox mb;
    
    String currentWeight = null;
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    
    ReelLog reelLog = new ReelLog();
    
    String one = "Issued";
    String zero = "Returned";
    boolean isReelLoaded = false;
    @FXML
    private Button btnAgingreport;
    @FXML
    private RadioButton rdbBrowse;
    @FXML
    private RadioButton rdbLog;
    
    ScaleDAO scaleDAO = new ScaleDAO();
    int baurdRate = 0;
    String comPort = "";
    String scaleName = "";
    String scaleId = "";
    
    public static String reading = " ";
    @FXML
    private ComboBox<String> cmbScale;
    
    @FXML
    private TableColumn<ReelLog, String> tcTimeStamp;
    
    @FXML
    private TableColumn<ReelLog, String> tcWeight;
    
    @FXML
    private TableColumn<ReelLog, String> tcReturnTimeStamp;
    @FXML
    private TableColumn<ReelLog, String> tcReturnWeight;

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Action Events">
    @FXML
    private void btnAgingreportOnAction(ActionEvent event) {
        if (rdbBrowse.isSelected() && !txtItemCode.getText().isEmpty()) {
            HashMap param = new HashMap();
            param.put("reel_code", txtItemCode.getText());
            //param.put("from_reel_code", txtItemCode.getText());

            File fileOne
                    = new File(
                            ReportPath.PATH_AGING_REPORT.
                            toString());
            String img = fileOne.getAbsolutePath();
            ReportGenerator r = new ReportGenerator(img, param);
            
            r.setVisible(true);
            
        }
    }
    
    @FXML
    private void rdbBrowseOnAction(ActionEvent event) {
        rdbLog.setSelected(false);
        
        modeSelection();
        
    }
    
    @FXML
    private void rdbLogOnAction(ActionEvent event) {
        rdbBrowse.setSelected(false);
        modeSelection();
    }
    
    @FXML
    private void cmbScaleOnAction(ActionEvent event) {
    }
    
    @FXML
    private void btnRefreshItemCodeOnAction(ActionEvent event) {
        
        clearInput();
        
    }
    
    @FXML
    private void btnSearchItemCodeOnAction(ActionEvent event) {
        
        reelTableDataLoader(txtItemCode.getText());
        reelIdTable.setItems(reelData);
        if (!reelData.isEmpty()) {
            reelPop.show(btnSearchItemCode);
        }
        //validatorInitialization();
    }
    
    @FXML
    private void btnRePrintOnAction(ActionEvent event) {
        
        boolean isDataInserted = true;

        //<editor-fold defaultstate="collapsed" desc="Current Print Code">
        if (isDataInserted) {
            
            HashMap param = new HashMap();
            param.put("to_reel_code", txtItemCode.getText());
            param.put("from_reel_code", txtItemCode.getText());
            
            File fileOne
                    = new File(
                            ReportPath.PATH_BARCODE_REPORT.
                            toString());
            String img = fileOne.getAbsolutePath();
            ReportGenerator r = new ReportGenerator(img, param);
            
            r.setVisible(true);
            
            mb.ShowMessage(stage, ErrorMessages.SuccesfullyCreated,
                    MessageBoxTitle.INFORMATION.toString(),
                    MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                    MessageBox.MessageType.MSG_OK);
            //clearInput();
        }

//</editor-fold>
    }
    
    @FXML
    private void btnLogOnAction(ActionEvent event) {
        System.out.println("Issued");
//        if (isReelLoaded == true) {
        txtReturnedWeight.setText("200.00");
        
        System.out.println("Flag - " + reelDAO.getDbFlag(txtItemCode.getText()));
        
        if (reelDAO.getDbFlag(txtItemCode.getText()).equals("1")) {
            if (txtReturnedWeight.getText().isEmpty()) {
                System.out.println(
                        "State is issued.Cannot log without returned weight");
                
            } else {
                
                boolean isUpdated = reelDAO.updateReturnLog(txtItemCode.
                        getText(),
                        LocalDate.now().toString(),
                        txtReturnedWeight.getText());
                
                boolean isDataUpdated = reelDAO.updateFlagAndWeight(txtItemCode.getText(),
                         0,txtReturnedWeight.getText());
                
              
                    if (isUpdated && isDataUpdated) {
                    System.out.println("Return weight logged.");
                    tableReelLogData.clear();
                loadReelInfo();
                }
                
            }
            
        } else if (reelDAO.getDbFlag(txtItemCode.getText()).equals("0")) {
            System.out.println("State is Returned. Only to issue");
            
            boolean isDateSaved = reelDAO.insertIssueLog(
                    txtItemCode.getText(),
                    LocalDate.now().toString(),
                    txtIssuedWeight.getText());
            
            boolean isDataUpdated = reelDAO.updateFlag(txtItemCode.getText(), 1);
            System.out.println("Save - " + isDateSaved);
            System.out.println("Updated - " + isDataUpdated);
            
            if (isDateSaved && isDataUpdated) {
                System.out.println("Data Inserted.");
                tableReelLogData.clear();
                loadReelInfo();
                
            }
            
        }
//        }

    }
    
    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        stage.close();
        
    }
    
    @FXML
    private void btnRefreshReturnedWeightOnAction(ActionEvent event) {
        
        scaleCofigLoader(cmbScale.getValue());
        txtReturnedWeight.setText(getScaleReading());
        
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Key Events">
    @FXML
    private void txtItemCodeOnKeyReleased(KeyEvent event) {
        
        if (event.getCode().equals(KeyCode.ENTER)) {
            if (!txtItemCode.getText().isEmpty()) {
                loadReelInfo();
                txtItemCode.selectAll();
                reelPop.hide();
            }
            
        }
        
        if (txtItemCode.getText().length() >= 3) {
            
            reelTableDataLoader(txtItemCode.getText());
            reelIdTable.setItems(reelData);
            if (!reelData.isEmpty()) {
                reelPop.show(btnRefreshItemCode);
            } else {
                reelPop.hide();
            }
            //validatorInitialization();
        }
    }
    
    @FXML
    private void txtDescriptionOnKeyReleased(KeyEvent event) {
    }
    
    @FXML
    private void txtItemNameOnKeyReleased(KeyEvent event) {
    }
    
    @FXML
    private void txtGSMOnKeyReleased(KeyEvent event) {
    }
    
    @FXML
    private void txtLogDateOnKeyReleased(KeyEvent event) {
    }
    
    @FXML
    private void txtIssuedWeightOnKeyReleased(KeyEvent event) {
    }
    
    @FXML
    private void txtSizeOnKeyReleased(KeyEvent event) {
    }
    
    @FXML
    private void txtReelFbOnKeyReleased(KeyEvent event) {
    }
    
    @FXML
    private void txtReelNoOnKeyReleased(KeyEvent event) {
    }
    
    @FXML
    private void txtReturnedWeightOnReleased(KeyEvent event) {
    }
    
    @FXML
    private void txtReelLinerDateOnKeyReleased(KeyEvent event) {
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Click Events">

    @FXML
    private void tblRequestNoteListOnMouseClicked(MouseEvent event) {
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        tcTimeStamp.setCellValueFactory(
                new PropertyValueFactory<ReelLog, String>(
                        "colTimeStamp"));
        
        tcWeight.setCellValueFactory(new PropertyValueFactory<ReelLog, String>(
                "colWeight"));
        
        tcReturnTimeStamp.setCellValueFactory(
                new PropertyValueFactory<ReelLog, String>(
                        "coltcReturnTimeStamp"));
        
        tcReturnWeight.setCellValueFactory(
                new PropertyValueFactory<ReelLog, String>(
                        "coltcReturnWeight"));
        
        tblItemList.setItems(tableReelLogData);

        //txtItemCode.setText(reelDAO.generateID());
        txtItemCode.requestFocus();
        mb = SimpleMessageBoxFactory.createMessageBox();
        rdbBrowse.setSelected(true);
        rdbLog.setSelected(false);
        modeSelection();
        loadScaleNames();
        
    }
    
    @Override
    public void setStage(Stage stage, Object[] obj) {
        
        this.stage = stage;

        //Reel popup------------------------  
        reelIdTable = reelpopup.tableViewLoader(reelData);
        reelIdTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    ReelPopup p = null;
                    p = (ReelPopup) reelIdTable.getSelectionModel().
                            getSelectedItem();
                    clearInput();
                    
                    if (p.getColReelCode() != null) {
                        txtItemCode.setText(p.getColReelCode());
//                        customerCode = p.getColCustomerCode();
                        //btnDelete.setVisible(true);
                        loadReelInfo();
                        isReelLoaded = true;
                        
                    }
                    
                } catch (NullPointerException n) {
                    
                }
                
                reelPop.hide();
                //validatorInitialization();

            }
            
        });
        
        reelIdTable.setOnMousePressed(e -> {
            
            if (e.getButton() == MouseButton.SECONDARY) {
                
                reelPop.hide();
                //validatorInitialization();

            }
            
        });
        
        reelPop = new PopOver(reelIdTable);
        
        stage.setOnCloseRequest(e -> {
            
            if (reelPop.isShowing()) {
                e.consume();
                reelPop.hide();
                
            }
        });
        
    }
    
    @Override
    public boolean isValid() {
        return true;
    }
    
    @Override
    public void clearInput() {
        txtItemName.clear();
        txtIssuedWeight.clear();
        txtDescription.clear();
        txtReelLiner.clear();
        txtGSM.clear();
        txtSize.clear();
        txtReelFb.clear();
        txtReelNo.clear();
        txtLogDate.clear();
        txtItemCode.clear();
        tableReelLogData.clear();
        isReelLoaded = false;
        txtItemCode.requestFocus();
        reelData.clear();
        
    }
    
    @Override
    public void clearValidations() {
        
    }
    
    private boolean reelTableDataLoader(String keyword) {
        
        reelData.clear();
        ArrayList<ArrayList<String>> itemInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = reelDAO.
                searchReelDetailsDetails(keyword);
        
        if (list != null) {
            
            for (int i = 0; i < list.size(); i++) {
                
                itemInfo.add(list.get(i));
            }
            
            if (itemInfo != null && itemInfo.size() > 0) {
                for (int i = 0; i < itemInfo.size(); i++) {
                    
                    reelpopup = new ReelPopup();
                    reelpopup.colReelCode.setValue(itemInfo.get(i).
                            get(0));
                    reelpopup.colSerialNumber.setValue(itemInfo.get(i).
                            get(1));
                    reelpopup.colItemName.setValue(itemInfo.get(i).
                            get(2));
                    reelpopup.colReelNumber.setValue(itemInfo.get(i).
                            get(3));
                    
                    reelData.add(reelpopup);
                }
            }
            
        }
        return true;
        
    }
    
    private boolean loadReelInfo() {
        
        boolean isDataAvailable = false;
        
        try {

            //reelData.clear();
            ArrayList<String> dataList = null;
            dataList = reelDAO.
                    loadingReelInfo(txtItemCode.getText());
            
            ArrayList<ArrayList<String>> reelLogInfo
                    = new ArrayList<ArrayList<String>>();
            
            ArrayList<ArrayList<String>> list = reelDAO.
                    reelLogDetails(txtItemCode.getText());
            
            txtItemName.setText(dataList.get(0));
            txtIssuedWeight.setText(dataList.get(1));
            txtDescription.setText(dataList.get(2));
            
            txtReelLiner.setText(dataList.get(3));
            txtGSM.setText(dataList.get(4));
            
            txtSize.setText(dataList.get(5));
            txtReelFb.setText(dataList.get(6));
            txtReelNo.setText(dataList.get(7));
            
            txtLogDate.setText(dateFormat.format(date));
            
            isDataAvailable = true;
            
            if (dataList != null && list != null) {
                
                for (int i = 0; i < list.size(); i++) {
                    
                    reelLogInfo.add(list.get(i));
                }
                
                if (reelLogInfo != null && reelLogInfo.size() > 0) {
                    for (int i = 0; i < reelLogInfo.size(); i++) {
                        
                        reelLog = new ReelLog();
                        
                        reelLog.colTimeStamp.setValue(reelLogInfo.get(i).get(0));
                        
                        reelLog.colWeight.setValue(reelLogInfo.get(i).get(1));
                        reelLog.coltcReturnTimeStamp.setValue(
                                reelLogInfo.get(i).get(2));
                        reelLog.coltcReturnWeight.setValue(reelLogInfo.get(i).
                                get(3));
                        
                        tableReelLogData.add(reelLog);
                    }
                }
            } else {
                mb.ShowMessage(stage, ErrorMessages.InvalidEvent,
                        MessageBoxTitle.ERROR.toString(),
                        MessageBox.MessageIcon.MSG_ICON_FAIL,
                        MessageBox.MessageType.MSG_OK);
                clearInput();
                
            }
            
        } catch (Exception e) {
            clearInput();
            return false;
        }
        
        return isDataAvailable;
    }
    
    String getFlag(int flagValue) {
        
        if (flagValue == 1) {
            return one;
        } else if (flagValue == 0) {
            return zero;
        }
        
        return null;
    }
    
    private void scaleCofigLoader(String scaleName) {
        
        ArrayList<String> dataList = null;
        
        dataList = scaleDAO.loadingScaleConfigs(scaleName);
        
        if (dataList != null) {
            
            scaleId = dataList.get(0);
            scaleName = dataList.get(1);
            comPort = dataList.get(2);
            baurdRate = Integer.parseInt(dataList.get(3));
            System.out.println("Data Read : scaleId - " + scaleId
                    + " scaleName - " + scaleName
                    + " comPort - " + comPort
                    + " baurdRate - " + baurdRate);
            
        }
        
    }
    
    private void loadScaleNames() {
        
        cmbScale.getItems().clear();
        ArrayList<String> list = null;
        list = scaleDAO.loadScaleItem();
        if (list != null) {
            try {
                ObservableList<String> List = FXCollections.observableArrayList(
                        list);
                cmbScale.setItems(List);
                cmbScale.setValue(List.get(0));
            } catch (Exception e) {
                
            }
            
        }
    }
    
    private String getScaleReading() {
        
        try {
            
            Arduino arduino = new Arduino(comPort, baurdRate);
            System.out.
                    println("Connection Status - " + arduino.openConnection());
            
            String s = arduino.serialRead(1);
            
            System.out.println("---CurrentReading :-" + s);
            
            arduino.closeConnection();
            
            System.out.println("Sample Substring :" + s);
            
            s = s.substring(s.indexOf("+") + 1, s.indexOf("kg"));
            System.out.println("--- Frist Split :  " + s);
            
            reading = s;
            
        } catch (Exception e) {
//            System.err.println("-----------------Splting Error-----------");
//             e.printStackTrace();
        }
        
        return reading;
        
    }
    
    private void modeSelection() {
        
        if (rdbLog.isSelected() && !rdbBrowse.isSelected()) {
            btnAgingreport.setVisible(false);
            btnRePrint.setVisible(true);
            btnRefreshReturnedWeight.setVisible(true);
            btnLog.setVisible(true);
        } else if (rdbBrowse.isSelected() && !rdbLog.isSelected()) {
            btnAgingreport.setVisible(true);
            btnRePrint.setVisible(true);
            btnRefreshReturnedWeight.setVisible(false);
            btnLog.setVisible(false);
        }
        
    }

//</editor-fold>
    public class ReelLog {

//  tcReturnTimeStamp;
//tcReturnWeight;
        public SimpleStringProperty colTimeStamp = new SimpleStringProperty(
                "tcTimeStamp");
        
        public SimpleStringProperty colWeight = new SimpleStringProperty(
                "tcWeight");
        
        public SimpleStringProperty coltcReturnTimeStamp
                = new SimpleStringProperty(
                        "tcReturnTimeStamp");
        
        public SimpleStringProperty coltcReturnWeight
                = new SimpleStringProperty(
                        "tcReturnWeight");
        
        public String getColTimeStamp() {
            return colTimeStamp.get();
        }
        
        public String getColWeight() {
            return colWeight.get();
        }
        
        public String getColtcReturnTimeStamp() {
            return coltcReturnTimeStamp.get();
        }
        
        public String getColtcReturnWeight() {
            return coltcReturnWeight.get();
        }
        
    }
}
