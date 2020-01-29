package com.saiton.ccs.scale;

import com.saiton.ccs.msgbox.MessageBox;
import com.saiton.ccs.msgbox.SimpleMessageBoxFactory;
import com.saiton.ccs.scaledao.ScaleRegisterationDAO;
import com.saiton.ccs.uihandle.StagePassable;
import com.saiton.ccs.uihandle.UiMode;
import com.saiton.ccs.validations.ErrorMessages;
import com.saiton.ccs.validations.MessageBoxTitle;
import com.saiton.ccs.validations.Validatable;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ScaleRegisterController implements Initializable, Validatable,
        StagePassable {

    //<editor-fold defaultstate="collapsed" desc="Initcomponent">
    @FXML
    private Button btnClose;

    @FXML
    private TableColumn<Scale, String> tcScaleId;

    @FXML
    private TableColumn<Scale, String> tcBoardRate;

    @FXML
    private TableColumn<Scale, String> tcScaleName;

    @FXML
    private TableColumn<Scale, String> tcComPort;

    @FXML
    private Label lblItemId;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtScaleId;

    @FXML
    private TextField txtNComPort;

    @FXML
    private TextField txtBoardRate;

    @FXML
    private TextField txtScaleName;
    
//    @FXML
//    private ComboBox<String> cmbReport;


    private ObservableList tableScaleData = FXCollections.
            observableArrayList();

//</editor-fold>
    private Stage stage;
    ScaleRegisterationDAO scaleRegistrationDAO = new ScaleRegisterationDAO();
    @FXML
    private TableView<Scale> tblItemList;
    Scale scaleItem = new Scale();
    private MessageBox mb;
    boolean isTableContentSaved = false;

    //<editor-fold defaultstate="collapsed" desc="Key Events">
    void txtSizeOnKeyReleased(ActionEvent event) {

    }

    void txtNoOnKeyReleased(ActionEvent event) {

    }

    void txtTNoShiftOnKeyReleased(ActionEvent event) {

    }

    void txtWeightScaleIdOnKeyReleased(ActionEvent event) {

    }

    @FXML
    private void txtSizeOnKeyReleased(KeyEvent event) {
    }


//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Action Events">
    void tblRequestNoteListOnMouseClicked(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        
        //saveTableContent();
        
        isTableContentSaved = scaleRegistrationDAO.insertScale(
                        txtScaleId.getText(),
                        txtScaleName.getText(),
                        txtNComPort.getText(),
                        txtBoardRate.getText());
        
        if (isTableContentSaved) {
            loadTableData();
            clearInput();
            mb.ShowMessage(stage, ErrorMessages.SuccesfullyCreated,
                MessageBoxTitle.INFORMATION.toString(),
                MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                MessageBox.MessageType.MSG_OK);
            
        }
        
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        stage.close();
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Click Events">
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tcScaleId.setCellValueFactory(new PropertyValueFactory<Scale, String>(
                "colScaleId"));
        tcScaleName.setCellValueFactory(
                new PropertyValueFactory<Scale, String>(
                        "colScaleName"));
        tcBoardRate.setCellValueFactory(
                new PropertyValueFactory<Scale, String>(
                        "colBoardRate"));

        tcComPort.setCellValueFactory(
                new PropertyValueFactory<Scale, String>(
                        "colComPort"));

        tblItemList.setItems(tableScaleData);
//
        mb = SimpleMessageBoxFactory.createMessageBox();
//      txtServiceId.setText(serviceDAO.generateID());
        btnDelete.setVisible(false);
        
        loadTableData();

    }

    @Override
    public boolean isValid() {

        return true;

    }

    @Override
    public void clearInput() {

        
        txtScaleId.clear();
        txtScaleName.clear();
        txtBoardRate.clear();
        txtNComPort.clear();
        txtScaleId.setText(scaleRegistrationDAO.generateID());

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

                btnSave.setDisable(true);
                btnSave.setVisible(false);

                deactivateCombo();

                break;

            case READ_ONLY:
                disableUi(false);
                deactivateCombo();
                btnDelete.setVisible(false);

                btnSave.setDisable(true);
                btnSave.setVisible(false);

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

        tblItemList.setDisable(state);
        tblItemList.setVisible(!state);

        btnDelete.setDisable(state);
        btnDelete.setVisible(!state);

        btnSave.setDisable(state);
        btnSave.setVisible(!state);

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
        txtScaleId.setText(scaleRegistrationDAO.generateID());

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


    @FXML
    private void tblRequestNoteListOnMouseClicked(MouseEvent event) {
    }

    private void saveTableContent() {

        Scale scaleItem;

//// Loading to db
////=============================================================================================================== 
        if (tblItemList.getItems().size() != 0) {
            for (int i = 0; i < tblItemList.getItems().size(); i++) {
                scaleItem = (Scale) tblItemList.getItems().get(i);

                isTableContentSaved = scaleRegistrationDAO.insertScale(
                        scaleItem.getColScaleId(),
                        scaleItem.getColScaleName(),
                        scaleItem.getColComPort(),
                        scaleItem.getColBoardRate());

            }
        }
    }
    
    private void loadTableData() {

        tableScaleData.clear();


        ArrayList<ArrayList<String>> custInfo
                = new ArrayList<>();
        ArrayList<ArrayList<String>> list = scaleRegistrationDAO.loadScaleInfo();

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                custInfo.add(list.get(i));
            }

            if (custInfo != null && custInfo.size() > 0) {
                for (int i = 0; i < custInfo.size(); i++) {

                    scaleItem = new Scale();

                    scaleItem.colScaleId.setValue(custInfo.get(i).get(0));
                    scaleItem.colScaleName.setValue(custInfo.get(i).get(1));
                    scaleItem.colComPort.setValue(custInfo.get(i).get(2));
                    scaleItem.colBoardRate.setValue(custInfo.get(i).get(3));
                    

                    tableScaleData.add(scaleItem);
                }
            }

        }

     
    }

    @FXML
    private void txtWeightScaleIdOnKeyReleased(KeyEvent event) {
    }

    @FXML
    private void txtNoOnKeyReleased(KeyEvent event) {
    }

    @FXML
    private void txtBoardRateKeyReleased(KeyEvent event) {
    }


    public class Scale {

        public SimpleStringProperty colScaleId = new SimpleStringProperty(
                "tcServiceId");
        public SimpleStringProperty colScaleName = new SimpleStringProperty(
                "tcServiceName");
        public SimpleStringProperty colBoardRate
                = new SimpleStringProperty(
                        "tcServicePrice");
        public SimpleStringProperty colComPort
                = new SimpleStringProperty(
                        "tcServiceDescription");

        public String getColScaleId() {
            return colScaleId.get();
        }

        public String getColScaleName() {
            return colScaleName.get();
        }

        public String getColBoardRate() {
            return colBoardRate.get();
        }

        public String getColComPort() {
            return colComPort.get();
        }

//        public void setColServiceName(String serviceName) {
//            colServiceName.setValue(serviceName);
//        }
    }

//</editor-fold>
}
