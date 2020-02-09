package com.saiton.ccs.scale;

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
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

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

    @FXML
    private TableColumn<ReelLog, String> tcTimeStamp;
    @FXML
    private TableColumn<ReelLog, String> tcFlag;
    @FXML
    private TableColumn<ReelLog, String> tcWeight;

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

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

    ReelLog reelLog = new ReelLog();
    
    
    String one = "Issued";
    String zero = "Returned";
    boolean isReelLoaded = false;
    

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Action Events">
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
            param.put("weight_scale_id", txtItemCode.getText());


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
        if (isReelLoaded == true) {
            
            if (reelDAO.getDbFlag(txtItemCode.getText())=="1") {
                System.out.println("Issued");
            }else if (reelDAO.getDbFlag(txtItemCode.getText())=="0") {
                System.out.println("Returned");
                if (txtReturnedWeight.getText().isEmpty()) {
                    System.out.println("Returned weight is empty");
                }
                
                
            }
        }
        
        
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        stage.close();
    }

    @FXML
    private void btnRefreshReturnedWeightOnAction(ActionEvent event) {

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
        
                tcTimeStamp.setCellValueFactory(new PropertyValueFactory<ReelLog, String>(
                "colTimeStamp"));
        tcFlag.setCellValueFactory(new PropertyValueFactory<ReelLog, String>(
                "colFlag"));
        tcWeight.setCellValueFactory(new PropertyValueFactory<ReelLog, String>(
                "colWeight"));

        tblItemList.setItems(tableReelLogData);

        //txtItemCode.setText(reelDAO.generateID());
        txtItemCode.requestFocus();
        mb = SimpleMessageBoxFactory.createMessageBox();

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
        //tableReelLogData.clear();
        isReelLoaded = false;
        txtItemCode.requestFocus();
        //reelData.clear();
        

    }

    @Override
    public void clearValidations() {

    }

    private boolean  reelTableDataLoader(String keyword) {

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
                    reelLog.colFlag.setValue(getFlag(Integer.parseInt(reelLogInfo.get(i).get(1))));
                    reelLog.colWeight.setValue(reelLogInfo.get(i).get(2));

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
    
    String getFlag(int flagValue){
        
        if (flagValue == 1) {
            return one;
        }else if (flagValue == 0) {
            return zero;
        }
        
        
        
        return null;
}

//</editor-fold>
    public class ReelLog {

        public SimpleStringProperty colTimeStamp = new SimpleStringProperty(
                "tcTimeStamp");
        public SimpleStringProperty colFlag = new SimpleStringProperty(
                "tcFlag");
        public SimpleStringProperty colWeight = new SimpleStringProperty(
                "tcWeight");

        public String getColTimeStamp() {
            return colTimeStamp.get();
        }

        public String getColFlag() {
            return colFlag.get();
        }

        public String getColWeight() {
            return colWeight.get();
        }

    }
}
