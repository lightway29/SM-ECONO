package com.saiton.ccs.scale;

import com.saiton.ccs.base.UserPermission;
import com.saiton.ccs.base.UserSession;
import com.saiton.ccs.common.TimeConvert;
import com.saiton.ccs.msgbox.MessageBox;
import com.saiton.ccs.msgbox.SimpleMessageBoxFactory;
import com.saiton.ccs.popup.ItemInfoPopup;
import com.saiton.ccs.popup.ServiceInfoPopup;
import com.saiton.ccs.printerservice.ReportPath;
import com.saiton.ccs.salesdao.ServiceDAO;
import com.saiton.ccs.scaledao.ReelRequisitionDAO;
import com.saiton.ccs.uihandle.ReportGenerator;
import com.saiton.ccs.uihandle.StagePassable;
import com.saiton.ccs.uihandle.UiMode;
import com.saiton.ccs.validations.CustomTableViewValidationImpl;
import com.saiton.ccs.validations.CustomTextAreaValidationImpl;
import com.saiton.ccs.validations.CustomTextFieldValidationImpl;
import com.saiton.ccs.validations.ErrorMessages;
import com.saiton.ccs.validations.FormatAndValidate;
import com.saiton.ccs.validations.MessageBoxTitle;
import com.saiton.ccs.validations.Validatable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.PopOver;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;

public class SearchWeightScaleBulkPrintController implements Initializable,
        Validatable,
        StagePassable {

    //<editor-fold defaultstate="collapsed" desc="Initcomponent">
    @FXML
    private Button btnClose;

    @FXML
    private TableColumn<Item, String> tcCoreWeight;

    @FXML
    private TableColumn<Item, String> tcWeightScaleID;

    @FXML
    private TableColumn<Item, String> tcGrossWeight;

    @FXML
    private TableColumn<Item, String> tcBatchNo;

    @FXML
    private TableColumn<Item, String> tcJobNo;

    @FXML
    private TableColumn<Item, String> tcNetWeight;

    @FXML
    private TableColumn<Item, String> tcLenght;

    @FXML
    private TableColumn<Item, String> tcWidth;

    @FXML
    private TableColumn<Item, String> tcGauge;

    @FXML
    private TableColumn<Item, String> tcCustomer;

    @FXML
    private TableColumn<Item, String> tcReelNo;

    @FXML
    private TableColumn<Item, String> tcQty;

    @FXML
    private TableColumn<Item, String> tcMachine;

    @FXML
    private TableColumn<Item, String> tcDate;

    @FXML
    private TableColumn<Item, String> tcScale;

    @FXML
    private TableColumn<Item, String> tcEPFNo;

    @FXML
    private TableColumn<Item, String> tcDescription;

    @FXML
    private TableColumn<Item, String> tcSize;

    @FXML
    private TableView<Item> tblWorkData;

    private ObservableList tableData = FXCollections.
            observableArrayList();

    @FXML
    private DatePicker dtpToDate;

    @FXML
    private DatePicker dtpFromDate;

//</editor-fold>
    private Stage stage;
    @FXML
    private TextField txtFromItemCode;
    @FXML
    private Button btnRefreshItemCode;
    @FXML
    private TextField txtToItemCode;
    @FXML
    private CheckBox chbPendingPrints;
    @FXML
    private Button btnPrint;
    @FXML
    private CheckBox chbWorkInProgress;
    @FXML
    private DatePicker dtFromDate;
    @FXML
    private DatePicker dtToDate;
    @FXML
    private Button btnConsumption;

    ReelRequisitionDAO reelDAO = new ReelRequisitionDAO();

    private MessageBox mb;
    @FXML
    private Button btnUploadReelData;

    Item item = new Item();

    //<editor-fold defaultstate="collapsed" desc="Key Events">
    @FXML
    private void txtToItemCodeOnKeyReleased(KeyEvent event) {
        if (txtFromItemCode.getText().length() >= 3) {
            clearInput();
            loadReelInfo(txtFromItemCode.getText(), txtToItemCode.getText());
        }

    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Action Events">
    @FXML
    private void btnRefreshItemCodeOnAction(ActionEvent event) {
        clearInput();
        loadReelInfo("", "");

        txtFromItemCode.clear();
        txtToItemCode.clear();

    }

    @FXML
    private void chbPendingPrintsOnAction(ActionEvent event) {
        chbWorkInProgress.setSelected(false);
        clearInput();
        loadPendingPrints();
        if (!chbPendingPrints.isSelected()) {
            clearInput();
        }
    }

    @FXML
    private void btnPrintOnAction(ActionEvent event) {
        if (!txtFromItemCode.getText().isEmpty()) {
            HashMap param = new HashMap();
            param.put("from_reel_code", txtFromItemCode.getText() + '%');
            param.put("to_reel_code", txtToItemCode.getText());

            File fileOne
                    = new File(
                            ReportPath.PATH_BARCODE_REPORT.
                                    toString());
            String img = fileOne.getAbsolutePath();
            ReportGenerator r = new ReportGenerator(img, param);

            r.setVisible(true);

            reelDAO.updatePrintCount(txtFromItemCode.getText(), txtToItemCode.getText(), 1);

        }
    }

    @FXML
    void btnConsumptionOnAction(ActionEvent event) {
            HashMap param = new HashMap();
            param.put("from_time_stamp", dtFromDate.getValue().toString());
            param.put("to_time_stamp", dtToDate.getValue().toString());

            File fileOne
                    = new File(
                            ReportPath.PATH_PAPER_CONSUMPTION_REPORT.
                                    toString());
            String img = fileOne.getAbsolutePath();
            ReportGenerator r = new ReportGenerator(img, param);

            r.setVisible(true);
    }

    @FXML
    private void chbWorkInProgressOnAction(ActionEvent event) {
        chbPendingPrints.setSelected(false);
        if (chbWorkInProgress.isSelected()) {
            clearInput();
            loadWorkInProgress();
        } else if (!chbWorkInProgress.isSelected()) {
            clearInput();
            loadReelInfo(txtFromItemCode.getText(), txtToItemCode.getText());
        }
    }

    @FXML
    private void btnUploadReelDataOnAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);

        boolean isSaved = reelDAO.saveDataToDB(selectedFile.getAbsolutePath());

        if (isSaved) {
            mb.ShowMessage(stage, ErrorMessages.SuccesfullyCreated,
                    MessageBoxTitle.INFORMATION.toString(),
                    MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                    MessageBox.MessageType.MSG_OK);
        } else {
            mb.ShowMessage(stage, ErrorMessages.InvalidText,
                    MessageBoxTitle.ERROR.toString(),
                    MessageBox.MessageIcon.MSG_ICON_FAIL,
                    MessageBox.MessageType.MSG_OK);
        }

    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        stage.close();

    }

    @FXML
    void dtpFromDateOnAction(ActionEvent event) {

    }

    @FXML
    void dtpToDateOnAction(ActionEvent event) {

    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Click Events">
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    TimeConvert timeConvert = new TimeConvert();
    Date currentDate = new Date();
    DateFormat time = new SimpleDateFormat("HH:mm");
    Calendar cal = Calendar.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tcCoreWeight.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcCoreWeight"));

        tcWeightScaleID.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcWeightScaleID"));

        tcGrossWeight.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcGrossWeight"));

        tcBatchNo.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcBatchNo"));

        tcJobNo.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcJobNo"));

        tcNetWeight.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcNetWeight"));

        tcLenght.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcLenght"));

        tcWidth.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcWidth"));

        tcGauge.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcGauge"));

        tcCustomer.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcCustomer"));

        tcReelNo.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcReelNo"));

        tcQty.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcQty"));

        tcMachine.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcMachine"));

        tcDate.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcDate"));

        tcScale.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcScale"));

        tcEPFNo.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcEPFNo"));

        tcDescription.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcDescription"));

        tcSize.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "coltcSize"));

        tblWorkData.setItems(tableData);

//
        mb = SimpleMessageBoxFactory.createMessageBox();
//        txtServiceId.setText(serviceDAO.generateID());
//        btnDelete.setVisible(false);

        chbWorkInProgress.setSelected(true);

        loadWorkInProgress();

        dateFormatterFromDate("yyyy-MM-dd");
        dtFromDate.setValue(LocalDate.now());
        dateFormatterToDate("yyyy-MM-dd");
        dtToDate.setValue(LocalDate.now());

    }

    private void dateFormatterToDate(String pattern) {

        dtToDate.setConverter(new StringConverter<LocalDate>() {
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
    
    private void dateFormatterFromDate(String pattern) {

        dtFromDate.setConverter(new StringConverter<LocalDate>() {
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

    @Override
    public boolean isValid() {

        return true;

    }

    @Override
    public void clearInput() {

        tableData.clear();
//        txtFromItemCode.clear();
//        txtToItemCode.clear();
//            txtDescription.clear();
//            txtPrice.clear();
//            txtService.clear();
//            txtServiceId.clear();
//            TableItemData.clear();
//            txtServiceId.setText(serviceDAO.generateID());
//            no = 1;
//             isupdate = false;

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

                break;

            case DELETE:
                disableUi(false);

                deactivateCombo();

                break;

            case READ_ONLY:
                disableUi(false);
                deactivateCombo();

                break;

            case ALL_BUT_DELETE:
                disableUi(false);

                break;

            case FULL_ACCESS:
                disableUi(false);
                break;

            case NO_ACCESS:
                disableUi(true);

                break;

        }

    }

    private void disableUi(boolean state) {

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

    private boolean loadReelInfo(String fromId, String toId) {

        boolean isDataAvailable = false;

        try {

            //reelData.clear();
            ArrayList<ArrayList<String>> reelLogInfo
                    = new ArrayList<ArrayList<String>>();

            ArrayList<ArrayList<String>> list = reelDAO.
                    reelLogDetailsBulk(fromId, toId);

            isDataAvailable = true;

            if (list != null) {

                for (int i = 0; i < list.size(); i++) {

                    reelLogInfo.add(list.get(i));
                }

                if (reelLogInfo != null && reelLogInfo.size() > 0) {
                    for (int i = 0; i < reelLogInfo.size(); i++) {

                        item = new Item();

//                        item.coltcCoreWeight.setValue(reelLogInfo.get(i).get(0));
//                        item.coltcWeightScaleID.setValue(reelLogInfo.get(i).get(1));
//                        item.coltcGrossWeight.setValue(reelLogInfo.get(i).get(2));
//                        item.coltcBatchNo.setValue(reelLogInfo.get(i).get(3));
//                        item.coltcJobNo.setValue(reelLogInfo.get(i).get(4));
//                        item.coltcNetWeight.setValue(reelLogInfo.get(i).get(5));
//                        item.coltcLenght.setValue(reelLogInfo.get(i).get(6));
//                        item.coltcWidth.setValue(reelLogInfo.get(i).get(7));
//                        item.coltcGauge.setValue(reelLogInfo.get(i).get(8));
//                        item.coltcCustomer.setValue(reelLogInfo.get(i).get(9));
//                        item.coltcReelNo.setValue(reelLogInfo.get(i).get(10));
//                        item.coltcQty.setValue(reelLogInfo.get(i).get(11));
//                        item.coltcMachine.setValue(reelLogInfo.get(i).get(12));
//                        item.coltcDate.setValue(reelLogInfo.get(i).get(13));
//                        item.coltcScale.setValue(reelLogInfo.get(i).get(14));
//                        item.coltcEPFNo.setValue(reelLogInfo.get(i).get(15));
//                        item.coltcDescription.setValue(reelLogInfo.get(i).get(16));
//                        item.coltcSize.setValue(reelLogInfo.get(i).get(17));
                        item.coltcWeightScaleID.setValue(reelLogInfo.get(i).get(0));
                        item.coltcScale.setValue(reelLogInfo.get(i).get(1));
                        item.coltcCustomer.setValue(reelLogInfo.get(i).get(2));
                        item.coltcBatchNo.setValue(reelLogInfo.get(i).get(3));
                        item.coltcDescription.setValue(reelLogInfo.get(i).get(4));
                        item.coltcMachine.setValue(reelLogInfo.get(i).get(5));
                        item.coltcJobNo.setValue(reelLogInfo.get(i).get(6));
                        item.coltcGauge.setValue(reelLogInfo.get(i).get(7));
                        item.coltcReelNo.setValue(reelLogInfo.get(i).get(8));
                        item.coltcQty.setValue(reelLogInfo.get(i).get(9));
                        item.coltcSize.setValue(reelLogInfo.get(i).get(10));
                        item.coltcGrossWeight.setValue(reelLogInfo.get(i).get(11));
                        item.coltcNetWeight.setValue(reelLogInfo.get(i).get(12));
                        item.coltcCoreWeight.setValue(reelLogInfo.get(i).get(13));
                        item.coltcLenght.setValue(reelLogInfo.get(i).get(14));
                        item.coltcWidth.setValue(reelLogInfo.get(i).get(15));
                        item.coltcEPFNo.setValue(reelLogInfo.get(i).get(16));
                        item.coltcDate.setValue(reelLogInfo.get(i).get(17));

                        tableData.add(item);
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

    private boolean loadWorkInProgress() {

        boolean isDataAvailable = false;

        try {

            //reelData.clear();
            ArrayList<ArrayList<String>> reelLogInfo
                    = new ArrayList<ArrayList<String>>();

            ArrayList<ArrayList<String>> list = reelDAO.
                    reelLogDetailsBulkIssued(txtFromItemCode.getText());

            isDataAvailable = true;

            if (list != null) {

                for (int i = 0; i < list.size(); i++) {

                    reelLogInfo.add(list.get(i));
                }

                if (reelLogInfo != null && reelLogInfo.size() > 0) {
                    for (int i = 0; i < reelLogInfo.size(); i++) {

                        item = new Item();

                        item.coltcWeightScaleID.setValue(reelLogInfo.get(i).get(0));
                        item.coltcScale.setValue(reelLogInfo.get(i).get(1));
                        item.coltcCustomer.setValue(reelLogInfo.get(i).get(2));
                        item.coltcBatchNo.setValue(reelLogInfo.get(i).get(3));
                        item.coltcDescription.setValue(reelLogInfo.get(i).get(4));
                        item.coltcMachine.setValue(reelLogInfo.get(i).get(5));
                        item.coltcJobNo.setValue(reelLogInfo.get(i).get(6));
                        item.coltcGauge.setValue(reelLogInfo.get(i).get(7));
                        item.coltcReelNo.setValue(reelLogInfo.get(i).get(8));
                        item.coltcQty.setValue(reelLogInfo.get(i).get(9));
                        item.coltcSize.setValue(reelLogInfo.get(i).get(10));
                        item.coltcGrossWeight.setValue(reelLogInfo.get(i).get(11));
                        item.coltcNetWeight.setValue(reelLogInfo.get(i).get(12));
                        item.coltcCoreWeight.setValue(reelLogInfo.get(i).get(13));
                        item.coltcLenght.setValue(reelLogInfo.get(i).get(14));
                        item.coltcWidth.setValue(reelLogInfo.get(i).get(15));
                        item.coltcEPFNo.setValue(reelLogInfo.get(i).get(16));
                        item.coltcDate.setValue(reelLogInfo.get(i).get(17));

//                        item.coltcCoreWeight.setValue(reelLogInfo.get(i).get(0));
//                        item.coltcWeightScaleID.setValue(reelLogInfo.get(i).get(1));
//                        item.coltcGrossWeight.setValue(reelLogInfo.get(i).get(2));
//                        item.coltcBatchNo.setValue(reelLogInfo.get(i).get(3));
//                        item.coltcJobNo.setValue(reelLogInfo.get(i).get(4));
//                        item.coltcNetWeight.setValue(reelLogInfo.get(i).get(5));
//                        item.coltcLenght.setValue(reelLogInfo.get(i).get(6));
//                        item.coltcWidth.setValue(reelLogInfo.get(i).get(7));
//                        item.coltcGauge.setValue(reelLogInfo.get(i).get(8));
//                        item.coltcCustomer.setValue(reelLogInfo.get(i).get(9));
//                        item.coltcReelNo.setValue(reelLogInfo.get(i).get(10));
//                        item.coltcQty.setValue(reelLogInfo.get(i).get(11));
//                        item.coltcMachine.setValue(reelLogInfo.get(i).get(12));
//                        item.coltcDate.setValue(reelLogInfo.get(i).get(13));
//                        item.coltcScale.setValue(reelLogInfo.get(i).get(14));
//                        item.coltcEPFNo.setValue(reelLogInfo.get(i).get(15));
//                        item.coltcDescription.setValue(reelLogInfo.get(i).get(16));
//                        item.coltcSize.setValue(reelLogInfo.get(i).get(17));
                        tableData.add(item);
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

    private boolean loadPendingPrints() {

        boolean isDataAvailable = false;

        try {

            //reelData.clear();
            ArrayList<ArrayList<String>> reelLogInfo
                    = new ArrayList<ArrayList<String>>();

            ArrayList<ArrayList<String>> list = reelDAO.
                    loadPendingPrints();

            isDataAvailable = true;

            if (list != null) {

                for (int i = 0; i < list.size(); i++) {

                    reelLogInfo.add(list.get(i));
                }

                if (reelLogInfo != null && reelLogInfo.size() > 0) {
                    for (int i = 0; i < reelLogInfo.size(); i++) {

                        item = new Item();

                        item.coltcWeightScaleID.setValue(reelLogInfo.get(i).get(0));
                        item.coltcScale.setValue(reelLogInfo.get(i).get(1));
                        item.coltcCustomer.setValue(reelLogInfo.get(i).get(2));
                        item.coltcBatchNo.setValue(reelLogInfo.get(i).get(3));
                        item.coltcDescription.setValue(reelLogInfo.get(i).get(4));
                        item.coltcMachine.setValue(reelLogInfo.get(i).get(5));
                        item.coltcJobNo.setValue(reelLogInfo.get(i).get(6));
                        item.coltcGauge.setValue(reelLogInfo.get(i).get(7));
                        item.coltcReelNo.setValue(reelLogInfo.get(i).get(8));
                        item.coltcQty.setValue(reelLogInfo.get(i).get(9));
                        item.coltcSize.setValue(reelLogInfo.get(i).get(10));
                        item.coltcGrossWeight.setValue(reelLogInfo.get(i).get(11));
                        item.coltcNetWeight.setValue(reelLogInfo.get(i).get(12));
                        item.coltcCoreWeight.setValue(reelLogInfo.get(i).get(13));
                        item.coltcLenght.setValue(reelLogInfo.get(i).get(14));
                        item.coltcWidth.setValue(reelLogInfo.get(i).get(15));
                        item.coltcEPFNo.setValue(reelLogInfo.get(i).get(16));
                        item.coltcDate.setValue(reelLogInfo.get(i).get(17));

//                        item.coltcCoreWeight.setValue(reelLogInfo.get(i).get(0));
//                        item.coltcWeightScaleID.setValue(reelLogInfo.get(i).get(1));
//                        item.coltcGrossWeight.setValue(reelLogInfo.get(i).get(2));
//                        item.coltcBatchNo.setValue(reelLogInfo.get(i).get(3));
//                        item.coltcJobNo.setValue(reelLogInfo.get(i).get(4));
//                        item.coltcNetWeight.setValue(reelLogInfo.get(i).get(5));
//                        item.coltcLenght.setValue(reelLogInfo.get(i).get(6));
//                        item.coltcWidth.setValue(reelLogInfo.get(i).get(7));
//                        item.coltcGauge.setValue(reelLogInfo.get(i).get(8));
//                        item.coltcCustomer.setValue(reelLogInfo.get(i).get(9));
//                        item.coltcReelNo.setValue(reelLogInfo.get(i).get(10));
//                        item.coltcQty.setValue(reelLogInfo.get(i).get(11));
//                        item.coltcMachine.setValue(reelLogInfo.get(i).get(12));
//                        item.coltcDate.setValue(reelLogInfo.get(i).get(13));
//                        item.coltcScale.setValue(reelLogInfo.get(i).get(14));
//                        item.coltcEPFNo.setValue(reelLogInfo.get(i).get(15));
//                        item.coltcDescription.setValue(reelLogInfo.get(i).get(16));
//                        item.coltcSize.setValue(reelLogInfo.get(i).get(17));
                        tableData.add(item);
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

    private void modeSelection() {

        if (chbPendingPrints.isSelected() && !chbWorkInProgress.isSelected()) {

        } else if (chbWorkInProgress.isSelected() && !chbPendingPrints.isSelected()) {

        }

    }

    public class Item {

        public SimpleStringProperty coltcCoreWeight = new SimpleStringProperty(
                "tcCoreWeight");

        public String getColtcCoreWeight() {
            return coltcCoreWeight.get();
        }

        public SimpleStringProperty coltcWeightScaleID
                = new SimpleStringProperty(
                        "tcWeightScaleID");

        public String getColtcWeightScaleID() {
            return coltcWeightScaleID.get();
        }

        public SimpleStringProperty coltcGrossWeight = new SimpleStringProperty(
                "tcGrossWeight");

        public String getColtcGrossWeight() {
            return coltcGrossWeight.get();
        }

        public SimpleStringProperty coltcBatchNo = new SimpleStringProperty(
                "tcBatchNo");

        public String getColtcBatchNo() {
            return coltcBatchNo.get();
        }

        public SimpleStringProperty coltcJobNo = new SimpleStringProperty(
                "tcJobNo");

        public String getColtcJobNo() {
            return coltcJobNo.get();
        }

        public SimpleStringProperty coltcNetWeight = new SimpleStringProperty(
                "tcNetWeight");

        public String getColtcNetWeight() {
            return coltcNetWeight.get();
        }

        public SimpleStringProperty coltcLenght = new SimpleStringProperty(
                "tcLenght");

        public String getColtcLenght() {
            return coltcLenght.get();
        }

        public SimpleStringProperty coltcWidth = new SimpleStringProperty(
                "tcWidth");

        public String getColtcWidth() {
            return coltcWidth.get();
        }

        public SimpleStringProperty coltcGauge = new SimpleStringProperty(
                "tcGauge");

        public String getColtcGauge() {
            return coltcGauge.get();
        }

        public SimpleStringProperty coltcCustomer = new SimpleStringProperty(
                "tcCustomer");

        public String getColtcCustomer() {
            return coltcCustomer.get();
        }

        public SimpleStringProperty coltcReelNo = new SimpleStringProperty(
                "tcReelNo");

        public String getColtcReelNo() {
            return coltcReelNo.get();
        }

        public SimpleStringProperty coltcQty = new SimpleStringProperty(
                "tcQty");

        public String getColtcQty() {
            return coltcQty.get();
        }

        public SimpleStringProperty coltcMachine = new SimpleStringProperty(
                "tcMachine");

        public String getColtcMachine() {
            return coltcMachine.get();
        }

        public SimpleStringProperty coltcDate = new SimpleStringProperty(
                "tcDate");

        public String getColtcDate() {
            return coltcDate.get();
        }

        public SimpleStringProperty coltcScale = new SimpleStringProperty(
                "tcScale");

        public String getColtcScale() {
            return coltcScale.get();
        }

        public SimpleStringProperty coltcEPFNo = new SimpleStringProperty(
                "tcEPFNo");

        public String getColtcEPFNo() {
            return coltcEPFNo.get();
        }

        public SimpleStringProperty coltcDescription = new SimpleStringProperty(
                "tcDescription");

        public String getColtcDescription() {
            return coltcDescription.get();
        }

        public SimpleStringProperty coltcSize = new SimpleStringProperty(
                "tcSize");

        public String getColtcSize() {
            return coltcSize.get();
        }

        public SimpleStringProperty colServiceId = new SimpleStringProperty(
                "tcServiceId");

        public String getColServiceId() {
            return colServiceId.get();
        }

    }

//</editor-fold>
}
