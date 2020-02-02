package com.saiton.ccs.scale;

import com.saiton.ccs.base.UserPermission;
import com.saiton.ccs.base.UserSession;
import com.saiton.ccs.msgbox.MessageBox;
import com.saiton.ccs.msgbox.SimpleMessageBoxFactory;
import com.saiton.ccs.popup.CustomerIdPopup;
import com.saiton.ccs.popup.ItemInfoPopup;
import com.saiton.ccs.popup.MachinePopup;
import com.saiton.ccs.popup.ServiceInfoPopup;
import com.saiton.ccs.popup.SizePopup;
import com.saiton.ccs.printerservice.ReportPath;
import com.saiton.ccs.salesdao.ServiceDAO;
import com.saiton.ccs.scaledao.ScaleDAO;
import com.saiton.ccs.uihandle.ReportGenerator;
import com.saiton.ccs.uihandle.StagePassable;
import com.saiton.ccs.uihandle.UiMode;
import com.saiton.ccs.util.SerialController;
import com.saiton.ccs.util.SimpleRead;
import com.saiton.ccs.validations.CustomTableViewValidationImpl;
import com.saiton.ccs.validations.CustomTextAreaValidationImpl;
import com.saiton.ccs.validations.CustomTextFieldValidationImpl;
import com.saiton.ccs.validations.ErrorMessages;
import com.saiton.ccs.validations.FormatAndValidate;
import com.saiton.ccs.validations.MessageBoxTitle;
import com.saiton.ccs.validations.Validatable;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.StringConverter;
import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.ButtonBar.ButtonType;
import org.controlsfx.control.PopOver;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import arduino.*;
public class ScaleController implements Initializable, Validatable,
        StagePassable {

    //<editor-fold defaultstate="collapsed" desc="Initcomponent">
    @FXML
    private TextField txtWeightScaleId;

    @FXML
    private DatePicker dtpDate;

    @FXML
    private TextField txtReelNo;

    @FXML
    private TextField txtGrossWeight;

    @FXML
    private TextField txtNetWeight1;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtGauge;

    @FXML
    private TextField txtNetWeight;

    @FXML
    private TextField txtWidth;

    @FXML
    private TextField txtBatchNo;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnRefreshNetWeight;

    @FXML
    private TextField txtJobNo;

    @FXML
    private TextField txtLength;

    @FXML
    private ComboBox<String> cmbScale;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQty;

    @FXML
    private Button btnPrint;

    @FXML
    private Button btnRefreshCoreWeight;

    @FXML
    private Label lblItemId;

    @FXML
    private TextField txtEPFNo;

    @FXML
    private Button btnRefreshGrossWeight;

    @FXML
    private ComboBox<String> cmbLabel;
    
    private Stage stage;
    @FXML
    private TextField txtCustomer;
    @FXML
    private Button btnSearchCustomer;
    @FXML
    private Button btnRefreshCustomer;
    @FXML
    private Button btnCustomerAdd;
    @FXML
    private TextField txtSize;
    @FXML
    private Button btnSearchSize;
    @FXML
    private Button btnRefreshSize;
    @FXML
    private Button btnSizeAdd;
    @FXML
    private TextField txtMachine;
    @FXML
    private Button btnSearchMachine;
    @FXML
    private Button btnRefreshMachine;
    @FXML
    private Button btnMachineAdd;
    @FXML
    private TextField txtFilm;
    @FXML
    private CheckBox chbPreviewReport;
    
    public static String currentReading = " ";
    public static String reading = " ";
    

    public static int count = 0;
    ScaleDAO scaleDAO = new ScaleDAO();
    ;
    String customerCode = "";
    //Customer Popup
    private TableView customerIdTable = new TableView();
    private CustomerIdPopup customerIdPopup = new CustomerIdPopup();
    private ObservableList<CustomerIdPopup> customerData = FXCollections.
            observableArrayList();
    private PopOver customerIdPop;

    //Size Popup
    private TableView sizeIdTable = new TableView();
    private SizePopup sizeIdPopup = new SizePopup();
    private ObservableList<SizePopup> sizeData = FXCollections.
            observableArrayList();
    private PopOver sizeIdPop;

    //Machine Popup
    private TableView machineIdTable = new TableView();
    private MachinePopup machineIdPopup = new MachinePopup();
    private ObservableList<MachinePopup> machineData = FXCollections.
            observableArrayList();
    private PopOver machineIdPop;
    private MessageBox mb;

    //--------------
    final TextField username = new TextField();
    final TextField password = new TextField();
    //--------------

    ObservableList<String> locationList = FXCollections.observableArrayList(
            "Factory", "Studio"
    );

    int baurdRate = 0;
    String comPort = "";
    String scaleName = "";
    String scaleId = "";
    SerialController main = null;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Key Events">
    @FXML
    private void txtWeightScaleIdOnKeyReleased(KeyEvent event) {

    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Action Events">
    @FXML
    void cmbLabelOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshGrossWeightOnAction(ActionEvent event) {
// count = 0;
//        count++;
//
//        main = new SerialController();
        scaleCofigLoader(cmbScale.getValue());
        txtGrossWeight.setText(getScaleReading());
        //currentReading = null;
        calculate();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnPrintOnAction(ActionEvent event) {

        boolean isDataInserted = true;
        isDataInserted = scaleDAO.insertWeight(
                txtWeightScaleId.getText(),
                scaleDAO.getScaleId(cmbScale.getValue()),
                scaleDAO.getCustomerCode(txtCustomer.getText()),
                txtDescription.getText(),
                txtReelNo.getText(),
                txtJobNo.getText(),
                txtSize.getText(),
                txtLength.getText(),
                txtWidth.getText(),
                txtEPFNo.getText(),
                txtBatchNo.getText(),
                txtMachine.getText(),
                txtGauge.getText(),
                                Double.parseDouble(txtQty.getText()),
                                Double.parseDouble(txtGrossWeight.getText()),
                                Double.parseDouble(txtNetWeight.getText()),
                                Double.parseDouble(txtNetWeight1.getText()),
//                Double.parseDouble("100.00"),
//                Double.parseDouble("100.00"),
//                Double.parseDouble("100.00"),
//                Double.parseDouble("100.00"),
                dtpDate.getValue().toString(),
                txtFilm.getText());

        //<editor-fold defaultstate="collapsed" desc="Current Print Code">
        if (isDataInserted) {

            HashMap param = new HashMap();
            param.put("weight_scale_id", txtWeightScaleId.getText());

            if (cmbLabel.getValue().toString() == "Studio") {
                File fileOne
                        = new File(
                                ReportPath.PATH_WEIGHT_ONE_REPORT.
                                toString());
                String img = fileOne.getAbsolutePath();
                ReportGenerator r = new ReportGenerator(img, param);
                if (chbPreviewReport.isSelected()) {
                    r.setVisible(true);
                }

            }else{
                 File filTwo
                    = new File(
                            ReportPath.PATH_WEIGHT_TWO_REPORT.
                            toString());
            String report = filTwo.getAbsolutePath();
            ReportGenerator rep = new ReportGenerator(report, param);
            if (chbPreviewReport.isSelected()) {
                rep.setVisible(true);
            }
            }

           

            mb.ShowMessage(stage, ErrorMessages.SuccesfullyCreated,
                    MessageBoxTitle.INFORMATION.toString(),
                    MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                    MessageBox.MessageType.MSG_OK);

            try {
                int val = Integer.parseInt(txtReelNo.getText()) + 1;
                txtReelNo.setText(val + "");

                txtWeightScaleId.setText(scaleDAO.generateID());
            } catch (Exception e) {
            }

            //clearInput();
        }

//</editor-fold>
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {

        stage.close();
    }

    @FXML
    void btnRefreshNetWeightOnAction(ActionEvent event) {

        calculate();

//        net = gross - core
        //main.close();
    }

    @FXML
    void dtpDateOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshCoreWeightOnAction(ActionEvent event) {

        scaleCofigLoader(cmbScale.getValue());
        txtNetWeight1.setText(getScaleReading());
        calculate();

    }

    @FXML
    private void btnSearchSizeOnAction(ActionEvent event) {

        sizeTableDataLoader(txtSize.getText());
        sizeIdTable.setItems(sizeData);
        if (!sizeData.isEmpty()) {
            sizeIdPop.show(btnSearchSize);
        }
        validatorInitialization();

    }

    @FXML
    private void btnRefreshSizeOnAction(ActionEvent event) {
        txtSize.clear();
    }

    @FXML
    private void btnSizeAddOnAction(ActionEvent event) {

    }

    @FXML
    private void btnSearchMachineOnAction(ActionEvent event) {

        machineTableDataLoader(txtMachine.getText());
        machineIdTable.setItems(machineData);
        if (!machineData.isEmpty()) {
            machineIdPop.show(btnSearchMachine);
        }
        validatorInitialization();

    }

    @FXML
    private void btnRefreshMachineOnAction(ActionEvent event) {
        txtMachine.clear();
    }

    @FXML
    private void btnMachineAddOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSearchCustomerOnAction(ActionEvent event) {

        customerTableDataLoader(txtCustomer.getText());
        customerIdTable.setItems(customerData);
        if (!customerData.isEmpty()) {
            customerIdPop.show(btnSearchCustomer);
        }
        validatorInitialization();

    }

    @FXML
    private void btnRefreshCustomerOnAction(ActionEvent event) {
        txtCustomer.clear();
        clearInput();
    }

    @FXML
    private void btnCustomerAddOnAction(ActionEvent event) {

    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Click Events">
//</editor-fold>    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtGrossWeight.setText("0.00");
        txtNetWeight.setText("0.00");
        txtNetWeight1.setText("0.00");
        cmbLabel.setItems(locationList);
        cmbLabel.getSelectionModel().selectFirst();

        dateFormatter("yyyy-MM-dd");
        dtpDate.setValue(LocalDate.now());
        loadScaleNames();
        mb = SimpleMessageBoxFactory.createMessageBox();

//        tcServiceId.setCellValueFactory(new PropertyValueFactory<Item, String>(
//                "colServiceId"));
//        tcServiceName.setCellValueFactory(
//                new PropertyValueFactory<Item, String>(
//                        "colServiceName"));
//        tcServicePrice.setCellValueFactory(
//                new PropertyValueFactory<Item, String>(
//                        "colServicePrice"));
//        tcServiceDescription.setCellValueFactory(
//                new PropertyValueFactory<Item, String>(
//                        "colServiceDescription"));
//
//        tblItemList.setItems(TableItemData);
//
//        mb = SimpleMessageBoxFactory.createMessageBox();
        txtWeightScaleId.setText(scaleDAO.generateID());
        btnDelete.setVisible(false);
        txtReelNo.setText("0");

    }

    private String getScaleReading() {

/*       SerialController main = new SerialController();
//        main = new SerialController();
//        main.initialize(comPort, baurdRate);
//        Thread t = new Thread() {
//            public void run() {
//                //the following line will keep this app alive for 1000    seconds,
//                //waiting for events to occur and responding to them    (printing incoming messages to console).
//                try {
//                    Thread.sleep(100);
////                    Thread.sleep(1000000);
//
//                } catch (InterruptedException ie) {
//                }
//            }
//        };
//        t.start();
        
        */
        
        
        try {
            
            Arduino arduino = new Arduino(comPort,baurdRate);
         System.out.println("Connection Status - "+ arduino.openConnection());
       
//        String s = ScaleController.currentReading;
//        System.out.println("---CurrentReading :-" + ScaleController.currentReading);
         
             String s = arduino.serialRead(1);
            
             System.out.println("---CurrentReading :-" + s);
        
              
         arduino.closeConnection();

//            s = s.substring(s.indexOf("=") + 1);
//            s = s.substring(0, s.indexOf("="));
//
            System.out.println("Sample Substring :" + s);
//            reading = s;
            
             
             s = s.substring(s.indexOf("+")+1, s.indexOf("kg"));
             System.out.println("--- Frist Split :  " + s);
//           
//            s = s.split("+",1)[0];
//
//            System.out.println("Sample Substring :" + s);
            reading = s;

        } catch (Exception e) {
//            System.err.println("-----------------Splting Error-----------");
//             e.printStackTrace();
        }

        try {
            //Reverse
//            String input = s;
//            byte[] strAsByteArray = input.getBytes();
//            byte[] result = new byte[strAsByteArray.length];
//
//            for (int i = 0; i < strAsByteArray.length; i++) {
//                result[i] = strAsByteArray[strAsByteArray.length - i - 1];
//            }
//
//            String finalValue = new String(result);
            //System.out.println("Reverse Print : " + finalValue);
            //reading = finalValue;
            
            
            
            //double valueInDecimal = Double.parseDouble(finalValue);

            //reading = valueInDecimal + "";
        } catch (Exception e) {
            // e.printStackTrace();
        }

        return reading;

    }

    @Override
    public boolean isValid() {

        return true;

    }

    void calculate() {

        try {
            Double gross = Double.parseDouble(txtGrossWeight.getText());
            Double core = Double.parseDouble(txtNetWeight1.getText());
            Double net = Double.parseDouble(txtNetWeight.getText());
            if (!txtNetWeight1.getText().isEmpty()) {
                net = gross - core;
                txtNetWeight.setText(net + "");

            }
        } catch (Exception e) {

        }

    }

    @Override
    public void clearInput() {

        txtWeightScaleId.clear();

        txtCustomer.clear();
        txtDescription.clear();
        txtReelNo.clear();
        txtJobNo.clear();
        txtSize.clear();
        txtLength.clear();
        txtWidth.clear();
        txtEPFNo.clear();
        txtBatchNo.clear();
        txtMachine.clear();
        txtGauge.clear();
        txtQty.clear();
        txtGrossWeight.clear();
        txtNetWeight.clear();
        txtNetWeight1.clear();
        customerCode = "";
        txtWeightScaleId.setText(scaleDAO.generateID());
        System.out.println("ID : " + scaleDAO.generateID());
        txtReelNo.setText("0");

        txtGrossWeight.setText("0.00");
        txtNetWeight.setText("0.00");
        txtNetWeight1.setText("0.00");

    }

    private void clearComponentsForNewEntry() {

//        txtDescription.clear();
//        txtPrice.clear();
//        txtService.clear();
    }

    @Override
    public void clearValidations() {

//        no = 1;
//        
////        txtPrice.clear();
//        isupdate = true;
//
//        int count = TableItemData.size();
//        if (count == 0) {
//
//            btnDelete.setVisible(true);
//
//        }
    }

    private void setUserAccessLevel() {

//        userId = UserSession.getInstance().getUserInfo().getEid();
//
//        userName = UserSession.getInstance().getUserInfo().getName();
//        userCategory = UserSession.getInstance().getUserInfo().getCategory();
//        txtUserName.setText(userName);
//
//        String title = stage.getTitle();
//
//        if (!title.isEmpty()) {
//
//            UserPermission userPermission = UserSession.getInstance().
//                    findPermission(title);
//
//            if (userPermission.canInsert() == true) {
//                insert = true;
//            }
//
//            if (userPermission.canDelete() == true) {
//                delete = true;
//            }
//
//            if (userPermission.canUpdate() == true) {
//                update = true;
//            }
//
//            if (userPermission.canView() == true) {
//                view = true;
//            }
//
//            if (insert == true && delete == true && update == true && view
//                    == true) {
//                setUiMode(UiMode.FULL_ACCESS);
//
//            } else if (insert == false && delete == true && update == true
//                    && view
//                    == true) {
//                setUiMode(UiMode.FULL_ACCESS);
//
//            } else if (insert == true && delete == false && update == true
//                    && view
//                    == true) {
//                setUiMode(UiMode.ALL_BUT_DELETE);
//
//            } else if (insert == true && delete == true && update == false
//                    && view
//                    == true) {
//
//                setUiMode(UiMode.FULL_ACCESS);
//
//            } else if (insert == true && delete == true && update == true
//                    && view
//                    == false) {
//                setUiMode(UiMode.SAVE);
//
//            } else if (insert == false && delete == false && update == true
//                    && view
//                    == true) {
//
//                setUiMode(UiMode.FULL_ACCESS);
//
//            } else if (insert == false && delete == true && update == false
//                    && view
//                    == true) {
//                setUiMode(UiMode.DELETE);
//
//            } else if (insert == false && delete == true && update == true
//                    && view
//                    == false) {
//                setUiMode(UiMode.NO_ACCESS);
//
//            } else if (insert == true && delete == false && update == false
//                    && view
//                    == true) {
//
//                setUiMode(UiMode.ALL_BUT_DELETE);
//
//            } else if (insert == true && delete == false && update == true
//                    && view
//                    == false) {
//                setUiMode(UiMode.SAVE);
//
//            } else if (insert == true && delete == true && update == false
//                    && view
//                    == false) {
//                setUiMode(UiMode.SAVE);
//
//            } else if (insert == false && delete == false && update == false
//                    && view
//                    == true) {
//
//                setUiMode(UiMode.READ_ONLY);
//
//            } else if (insert == false && delete == false && update == true
//                    && view
//                    == false) {
//                setUiMode(UiMode.NO_ACCESS);
//
//            } else if (insert == false && delete == true && update == false
//                    && view
//                    == false) {
//                setUiMode(UiMode.NO_ACCESS);
//
//            } else if (insert == true && delete == false && update == false
//                    && view
//                    == false) {
//                setUiMode(UiMode.SAVE);
//
//            } else if (insert == false && delete == false && update == false
//                    && view
//                    == false) {
//                setUiMode(UiMode.NO_ACCESS);
//
//            }
//        } else {
//
//            setUiMode(UiMode.NO_ACCESS);
//
//        }
    }

    private void setUiMode(UiMode uiMode) {

        switch (uiMode) {

            case SAVE:
                disableUi(false);
                deactivateSearch();

                btnDelete.setVisible(false);

                break;

            case DELETE:
                disableUi(false);

                btnPrint.setDisable(true);
                btnPrint.setVisible(false);

                deactivateCombo();

                break;

            case READ_ONLY:
                disableUi(false);
                deactivateCombo();
                btnDelete.setVisible(false);

                btnPrint.setDisable(true);
                btnPrint.setVisible(false);

                break;

            case ALL_BUT_DELETE:
                disableUi(false);

                btnDelete.setVisible(false);

                break;

            case FULL_ACCESS:
                disableUi(false);
                btnDelete.setVisible(false);
                break;

            case NO_ACCESS:
                disableUi(true);

                break;

        }

    }

    private void disableUi(boolean state) {

        btnDelete.setDisable(state);
        btnDelete.setVisible(!state);

        btnPrint.setDisable(state);
        btnPrint.setVisible(!state);

        btnClose.setDisable(state);
        btnClose.setVisible(!state);
    }

    private void deactivateSearch() {

//        componentControl.deactivateSearch(lblItemName, txtItemName,
//                btnItemNameSearch,
//                220.00, 0.00);
    }

    private void deactivateCombo() {
//        componentControl.controlCComboBox(lblItemId1, cmbBatchNo, btnBatchNo,
//                220.00, 0.0, true);
    }

    private void itemTableDataLoader(String keyword) {

//        itemData.clear();
//        ArrayList<ArrayList<String>> itemInfo
//                = new ArrayList<ArrayList<String>>();
//        ArrayList<ArrayList<String>> list = serviceDAO.searchItemDetails(keyword);
//
//        if (list != null) {
//
//            for (int i = 0; i < list.size(); i++) {
//
//                itemInfo.add(list.get(i));
//            }
//
//            if (itemInfo != null && itemInfo.size() > 0) {
//                for (int i = 0; i < itemInfo.size(); i++) {
//
//                    itemPopup = new ServiceInfoPopup();
//                    itemPopup.colItemID.setValue(itemInfo.get(i).get(0));
//                    itemPopup.colItemName.setValue(itemInfo.get(i).get(1));
//                    itemPopup.colItemDesc.setValue(itemInfo.get(i).get(2));
//                    itemPopup.colItemPrice.setValue(itemInfo.get(i).get(3));
//                    
//                    
//                    itemData.add(itemPopup);
//                }
//            }
//
//        }
    }

    @Override
    public void setStage(Stage stage, Object[] obj) {

        this.stage = stage;
//        setUserAccessLevel();

        //CustomerId popup------------------------
        customerIdTable = customerIdPopup.tableViewLoader(customerData);

        customerIdTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    CustomerIdPopup p = null;
                    p = (CustomerIdPopup) customerIdTable.getSelectionModel().
                            getSelectedItem();
                    clearInput();

                    if (p.getColCustomerName() != null) {
                        txtCustomer.setText(p.getColCustomerName());
                        customerCode = p.getColCustomerCode();
                        btnDelete.setVisible(true);

                    }

                } catch (NullPointerException n) {

                }

                customerIdPop.hide();
                validatorInitialization();

            }

        });

        customerIdTable.setOnMousePressed(e -> {

            if (e.getButton() == MouseButton.SECONDARY) {

                customerIdPop.hide();
                validatorInitialization();

            }

        });

        //Machine popup------------------------
        machineIdTable = machineIdPopup.tableViewLoader(machineData);

        machineIdTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    MachinePopup p = null;
                    p = (MachinePopup) machineIdTable.getSelectionModel().
                            getSelectedItem();

                    if (p.getColMachine() != null) {
                        txtMachine.setText(p.getColMachine());

                    }

                } catch (NullPointerException n) {

                }

                machineIdPop.hide();
                validatorInitialization();

            }

        });

        machineIdTable.setOnMousePressed(e -> {

            if (e.getButton() == MouseButton.SECONDARY) {

                machineIdPop.hide();
                validatorInitialization();

            }

        });

        //Size popup------------------------
        sizeIdTable = sizeIdPopup.tableViewLoader(sizeData);

        sizeIdTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    SizePopup p = null;
                    p = (SizePopup) sizeIdTable.getSelectionModel().
                            getSelectedItem();

                    if (p.getColSize() != null) {
                        txtSize.setText(p.getColSize());

                    }

                } catch (NullPointerException n) {

                }

                sizeIdPop.hide();
                validatorInitialization();

            }

        });

        sizeIdTable.setOnMousePressed(e -> {

            if (e.getButton() == MouseButton.SECONDARY) {

                sizeIdPop.hide();
                validatorInitialization();

            }

        });

        customerIdPop = new PopOver(customerIdTable);
        sizeIdPop = new PopOver(sizeIdTable);
        machineIdPop = new PopOver(machineIdTable);

        stage.setOnCloseRequest(e -> {

            if (customerIdPop.isShowing() || sizeIdPop.isShowing()
                    || machineIdPop.isShowing()) {
                e.consume();
                customerIdPop.hide();
                sizeIdPop.hide();
                machineIdPop.hide();

            }
        });

//        
//        //item popup------------------------
//        itemTable = itemPopup.tableViewLoader(itemData);
//
//        itemTable.setOnMouseClicked(e -> {
//            if (e.getClickCount() == 2) {
//                try {
//                    btnDelete.setVisible(true);
//                    ServiceInfoPopup p = null;
//                    p = (ServiceInfoPopup) itemTable.getSelectionModel().
//                            getSelectedItem();
//                    if (p.getColItemID() != null) {
//                        clearValidations();
//
//                        txtServiceId.setText(p.getColItemID());
//                        txtService.setText(p.getColItemName());
//                        txtDescription.setText(p.getColItemDesc());
//                        txtPrice.setText(p.getColItemPrice());
//                        txtUserName.setText(serviceDAO.getUserName(
//                                txtServiceId.getText()));
//                        
//                        
//                    }
//
//                } catch (NullPointerException n) {
//
//                }
//
//                itemPop.hide();
//                validatorInitialization();
//
//            }
//
//        });
//
//        itemTable.setOnMousePressed(e -> {
//
//            if (e.getButton() == MouseButton.SECONDARY) {
//
//                itemPop.hide();
//                validatorInitialization();
//
//            }
//
//        });
//
//        itemPop = new PopOver(itemTable);
//
//        stage.setOnCloseRequest(e -> {
//
//            if (itemPop.isShowing()) {
//                e.consume();
//                itemPop.hide();
//
//            }
//        });
//
//        
//        
//        
//        validatorInitialization();
    }

    private void validatorInitialization() {

//        validationSupportTableData.registerValidator(txtService,
//                new CustomTextAreaValidationImpl(txtService,
//                        !fav.validName(txtService.getText()),
//                        ErrorMessages.Error));
//
//        validationSupportTableData.registerValidator(txtDescription,
//                new CustomTextAreaValidationImpl(txtDescription,
//                        !fav.validName(txtDescription.getText()),
//                        ErrorMessages.Error));
//
//        validationSupportTableData.registerValidator(txtDescription,
//                new CustomTextAreaValidationImpl(txtDescription,
//                        !fav.validName(txtDescription.getText()),
//                        ErrorMessages.Error));
//
//        validationSupportTableData.registerValidator(txtPrice,
//                new CustomTextFieldValidationImpl(txtPrice,
//                        !fav.chkPrice(txtPrice.getText()),
//                        ErrorMessages.InvalidPrice));
//        
//        validationSupportTable.registerValidator(tblItemList,
//                new CustomTableViewValidationImpl(tblItemList,
//                        !fav.validTableView(tblItemList),
//                        ErrorMessages.EmptyListView));
    }

    private void customerTableDataLoader(String keyword) {

        customerData.clear();
        ArrayList<ArrayList<String>> itemInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = scaleDAO.
                searchCustomerDetailsDetails(keyword);

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                itemInfo.add(list.get(i));
            }

            if (itemInfo != null && itemInfo.size() > 0) {
                for (int i = 0; i < itemInfo.size(); i++) {

                    customerIdPopup = new CustomerIdPopup();
                    customerIdPopup.colCustomerCode.setValue(itemInfo.get(i).
                            get(0));
                    customerIdPopup.colCustomerName.setValue(itemInfo.get(i).
                            get(1));

                    customerData.add(customerIdPopup);
                }
            }

        }

    }

    private void sizeTableDataLoader(String keyword) {

        sizeData.clear();
        ArrayList<ArrayList<String>> itemInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = scaleDAO.searchSizeDetailsDetails(
                keyword);

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                itemInfo.add(list.get(i));
            }

            if (itemInfo != null && itemInfo.size() > 0) {
                for (int i = 0; i < itemInfo.size(); i++) {

                    sizeIdPopup = new SizePopup();
                    sizeIdPopup.colSize.setValue(itemInfo.get(i).get(0));

                    sizeData.add(sizeIdPopup);
                }
            }

        }

    }

    private void machineTableDataLoader(String keyword) {

        machineData.clear();
        ArrayList<ArrayList<String>> itemInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = scaleDAO.
                searchMachineDetailsDetails(keyword);

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                itemInfo.add(list.get(i));
            }

            if (itemInfo != null && itemInfo.size() > 0) {
                for (int i = 0; i < itemInfo.size(); i++) {

                    machineIdPopup = new MachinePopup();
                    machineIdPopup.colMachine.setValue(itemInfo.get(i).get(0));

                    machineData.add(machineIdPopup);
                }
            }

        }

    }

    private void dateFormatter(String pattern) {

        dtpDate.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(
                    pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

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

    @FXML
    private void cmbScaleOnAction(ActionEvent event) {

        scaleCofigLoader(cmbScale.getValue());

    }

//</editor-fold>
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Class">
    public class Item {

        public SimpleStringProperty colServiceId = new SimpleStringProperty(
                "tcServiceId");
        public SimpleStringProperty colServiceName = new SimpleStringProperty(
                "tcServiceName");
        public SimpleStringProperty colServicePrice
                = new SimpleStringProperty(
                        "tcServicePrice");
        public SimpleStringProperty colServiceDescription
                = new SimpleStringProperty(
                        "tcServiceDescription");

        public String getColServiceId() {
            return colServiceId.get();
        }

        public String getColServiceName() {
            return colServiceName.get();
        }

        public String getColServicePrice() {
            return colServicePrice.get();
        }

        public String getColServiceDescription() {
            return colServiceDescription.get();
        }

        public void setColServiceName(String serviceName) {
            colServiceName.setValue(serviceName);
        }

    }

}
