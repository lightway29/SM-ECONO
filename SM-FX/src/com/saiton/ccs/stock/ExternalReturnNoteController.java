/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saiton.ccs.stock;

import com.saiton.ccs.base.UserPermission;
import com.saiton.ccs.base.UserSession;
import com.saiton.ccs.msgbox.MessageBox;
import com.saiton.ccs.msgbox.SimpleMessageBoxFactory;
import com.saiton.ccs.popup.ItemStockPopup;
import com.saiton.ccs.popup.ReturnNotePopup;
import com.saiton.ccs.popup.SupplierPopup;
import com.saiton.ccs.stockdao.ExternalReturnNoteDAO;
import com.saiton.ccs.stockdao.RequestNoteDAO;
import com.saiton.ccs.stockdao.SupplierDAO;
import com.saiton.ccs.uihandle.ComponentControl;
import com.saiton.ccs.uihandle.StagePassable;
import com.saiton.ccs.uihandle.UiMode;
import com.saiton.ccs.validations.CustomTableViewValidationImpl;
import com.saiton.ccs.validations.CustomTextFieldValidationImpl;
import com.saiton.ccs.validations.ErrorMessages;
import com.saiton.ccs.validations.FormatAndValidate;
import com.saiton.ccs.validations.MessageBoxTitle;
import com.saiton.ccs.validations.Validatable;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;

/**
 * FXML Controller class
 *
 * @author MewanZ
 */
public class ExternalReturnNoteController extends AnchorPane implements
        Initializable, Validatable, StagePassable {

    //<editor-fold defaultstate="collapsed" desc="localize">
    @FXML
    private Label retNoteExLbl;
    @FXML
    private Label supplierLbl;
    @FXML
    private Label dateLbl;
    @FXML
    private Label descLbl;
    @FXML
    private Label usernameLbl;
    @FXML
    private Label retNoteExItemLbl;

    //btn texts
    private String btnDeleteTxt;
    private String btnSaveTxt;
    private String btnCloseTxt;

    //prompt texts
    private String txtReturnNoteIdPt;
    private String txtSupllierPt;
    private String txtDatePt;
    private String txtAreaDescriptionPt;
    private String txtUsernamePt;
    private String cmbCategoryPt;
    private String txtItemIdPt;
    private String txtItemPt;
    private String txtBatchNoPt;
    private String txtAreaReturnDescriptionPt;
    private String txtUnitPt;
    private String txtQtyPt;

    //table columns txts
    private String tcItemIdTc;
    private String tcBatchNoTc;
    private String tcItemNameTc;
    private String tcReturnDescriptionTc;
    private String tcQtyTc;
    private String tcUnitTc;

    private final ValidationSupport validationSupport = new ValidationSupport();
    private final ValidationSupport validationSupportTableData
            = new ValidationSupport();
    private final ValidationSupport validationSupportTable
            = new ValidationSupport();
    private final FormatAndValidate fav = new FormatAndValidate();

    private Stage stage;
    private String userId;
    private String userName;
    private String userCategory;
    private boolean insert = false;
    private boolean update = false;
    private boolean delete = false;
    private boolean view = false;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private MessageBox mb;

    Item item = new Item();
    private ObservableList TableItemData = FXCollections.observableArrayList();
    boolean isTableContentSaved = false;
    Date date = new Date();
    ExternalReturnNoteDAO retrunNoteDAO = new ExternalReturnNoteDAO();
    ComponentControl component = new ComponentControl();
    RequestNoteDAO requestNoteDAO = new RequestNoteDAO();

    Double availableQty = 0.0;
//    ObservableList<String> Type = FXCollections.observableArrayList(
//            "Banquet", "Ala Carte", "Reservation"
//    );
    //item info popup---------------------------------------
    private TableView itemTable = new TableView();
    private PopOver itemPop;
    private ObservableList<ItemStockPopup> itemData = FXCollections.
            observableArrayList();
    private ItemStockPopup itemPopup = new ItemStockPopup();

    //RequestId popup---------------------------------------
    private TableView returnNoteTable = new TableView();
    private PopOver returnNotePop;
    private ObservableList<ReturnNotePopup> returnNoteData = FXCollections.
            observableArrayList();
    private ReturnNotePopup returnNotePopup = new ReturnNotePopup();

    //Supplier Pop Up
    private TableView supplierTable = new TableView();
    private PopOver supplierPop;
    private ObservableList<SupplierPopup> supplierData = FXCollections.
            observableArrayList();
    private SupplierPopup supplierPopUp = new SupplierPopup();
    SupplierDAO supplierDao = new SupplierDAO();

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="initcomponents">
    private Label lblCategory;

    @FXML
    private Label lblQty;

//    private ComboBox<String> cmbType;
    @FXML
    private TableColumn<Item, String> tcItemName;

    @FXML
    private Button btnItemSearch;

    @FXML
    private TableView<Item> tblReturnNoteList;

    private ComboBox<String> cmbCategory;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<Item, String> tcQty;

    @FXML
    private TextField txtItemId;

    @FXML
    private Label lblItem;

    @FXML
    private TableColumn<Item, String> tcReturnDescription;

    @FXML
    private TextField txtReturnNoteId;

    @FXML
    private Button btnReturnNoteIdSearch;

    @FXML
    private Button btnClose;

    @FXML
    private TextArea txtAreaReturnDescription;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtQty;

    @FXML
    private TableColumn<Item, String> tcItemId;

    @FXML
    private TableColumn<Item, String> tcBatchNo;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private Label lblItemId;

    @FXML
    private Label lblItemDescription;

    @FXML
    private Button btnSave;

    private TextField txtUnit;

    @FXML
    private Label lblItemDetail;

    @FXML
    private Insets x1;

    @FXML
    private Insets x2;

    @FXML
    private Label lblRequestId;

    @FXML
    private TextField txtItem;

    @FXML
    private Label lblBatchNo;

    @FXML
    private TextField txtBatchNo;
    @FXML
    private TextField txtSupllier;
    @FXML
    private Button btnSupplierSearch;

//</editor-fold> 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcItemId.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colItemId"));
        tcBatchNo.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colBatchNo"));
        tcItemName.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colItemName"));
        tcReturnDescription.setCellValueFactory(
                new PropertyValueFactory<Item, String>("colReturnDescription"));

        tcQty.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colQty"));
        tblReturnNoteList.setItems(TableItemData);

        txtReturnNoteId.setText(retrunNoteDAO.generateID());

        mb = SimpleMessageBoxFactory.createMessageBox();
//        userId = "EM0001";
        txtDate.setText(dateFormat.format(date));
//        txtUsername.setText(userId);
        btnDelete.setVisible(false);
//        cmbType.setItems(Type);
//        cmbType.getSelectionModel().selectFirst();
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void clearInput() {
        txtReturnNoteId.setText(retrunNoteDAO.generateID());
        txtDate.setText(dateFormat.format(date));
        txtUsername.setText(userId);
        btnDelete.setVisible(false);
//        btnSave.setVisible(true);
        TableItemData.clear();
        txtAreaDescription.clear();
        txtAreaReturnDescription.clear();
        txtItem.clear();
        txtBatchNo.clear();
        txtItemId.clear();
        txtQty.clear();
        txtSupllier.clear();

        validatorInitialization();
    }

    @Override
    public void clearValidations() {

    }

    @Override
    public void setStage(Stage stage, Object[] obj) {
        this.stage = stage;
//        localize();
        setUserAccessLevel();

        if (obj != null) {
            DataFromApprove(obj[0].toString());
        }

        //RequestId popup------------------------
        returnNoteTable = returnNotePopup.tableViewLoader(returnNoteData);

        
        returnNoteTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    ReturnNotePopup p = null;
                    p = (ReturnNotePopup) returnNoteTable.getSelectionModel().
                            getSelectedItem();
                    clearInput();

                    if (p.getColReturnNoteId() != null) {
                        txtReturnNoteId.setText(p.getColReturnNoteId());
                        btnDelete.setVisible(true);
                        btnSave.setVisible(false);
                        existingAdditionalItemToTable();
                    }

                } catch (NullPointerException n) {

                }

                returnNotePop.hide();
                validatorInitialization();

            }

        });

        returnNoteTable.setOnMousePressed(e -> {

            if (e.getButton() == MouseButton.SECONDARY) {

                returnNotePop.hide();
                validatorInitialization();

            }

        });
        //item popup------------------------
        itemTable = itemPopup.tableViewLoader(itemData);

        itemTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    ItemStockPopup p = null;
                    p = (ItemStockPopup) itemTable.getSelectionModel().
                            getSelectedItem();
                    if (p.getColItemID() != null) {
                        txtItemId.setText(p.getColItemID());
                        txtBatchNo.setText(p.getColBatchNo());
                        txtItem.setText(p.getColItemName());
                        availableQty = Double.parseDouble(p.getColQty());

                    }

                } catch (NullPointerException n) {

                }

                itemPop.hide();
                validatorInitialization();

            }

        });

        itemTable.setOnMousePressed(e -> {

            if (e.getButton() == MouseButton.SECONDARY) {

                itemPop.hide();
                validatorInitialization();

            }

        });

        //Supplier PopUp
        supplierTable = supplierPopUp.tableViewLoader(supplierData);

        supplierTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    SupplierPopup p = null;
                    p = (SupplierPopup) supplierTable.getSelectionModel().
                            getSelectedItem();
                    if (p.getColSupId() != null) {
                        txtSupllier.setText(p.getColSupId());
//                        loadRelavantItemsOfSupplier(p.getColSupId(),
//                                cmbCategory.getValue().toString());

                    }

                } catch (NullPointerException n) {

                }

                supplierPop.hide();
                validatorInitialization();

            }

        });

        returnNotePop = new PopOver(returnNoteTable);
        itemPop = new PopOver(itemTable);
        supplierPop = new PopOver(supplierTable);

        stage.setOnCloseRequest(e -> {

            if (returnNotePop.isShowing() || itemPop.isShowing() || supplierPop.
                    isShowing()) {
                e.consume();
                returnNotePop.hide();
                itemPop.hide();
                supplierPop.hide();

            }
        });

        validatorInitialization();
    }

    private void SupplierTableDataLoader(String keyword) {

        supplierData.clear();
        ArrayList<ArrayList<String>> supplierInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = supplierDao.searchSuppliers(keyword);
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                supplierInfo.add(list.get(i));
            }

            if (supplierInfo != null && supplierInfo.size() > 0) {
                for (int i = 0; i < supplierInfo.size(); i++) {

                    supplierPopUp = new SupplierPopup();
                    supplierPopUp.colSupId.setValue(
                            supplierInfo.get(i).get(0));
                    supplierPopUp.colSupName.
                            setValue(supplierInfo.get(i).get(1));
                    supplierPopUp.colNic.setValue(supplierInfo.get(i).get(2));
                    supplierPopUp.colAddress.
                            setValue(supplierInfo.get(i).get(3));
                    supplierData.add(supplierPopUp);
                }
            }

        }

    }

//    private void loadRelavantItemsOfSupplier(String supplierId, String category) {
//
//        itemData.clear();
//        ArrayList<ArrayList<String>> itemInfo
//                = new ArrayList<ArrayList<String>>();
//        ArrayList<ArrayList<String>> list = retrunNoteDAO.
//                loadRelavantItemsOfSupplier(
//                        supplierId, category);
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
//                    itemPopup = new ItemStockPopup();
//                    itemPopup.colItemID.setValue(itemInfo.get(i).get(0));
//                    itemPopup.colBatchNo.setValue(itemInfo.get(i).get(1));
//                    itemPopup.colItemName.setValue(itemInfo.get(i).get(2));
//                    itemPopup.colQty.setValue(itemInfo.get(i).get(3));
//                    itemData.add(itemPopup);
//                }
//            }
//
//        }
//
//    }
    private void DataFromApprove(String returnId) {
        System.out.println("Entered");
        btnSave.setVisible(false);
        btnDelete.setVisible(false);
        txtAreaDescription.setDisable(true);

        txtItemId.setDisable(true);
        txtItem.setDisable(true);
        txtAreaReturnDescription.setDisable(true);
        txtBatchNo.setDisable(true);
        txtQty.setDisable(true);
        component.deactivateSearch(lblRequestId, txtReturnNoteId,
                btnReturnNoteIdSearch, 220.0, 0.0);
        component.deactivateSearch(supplierLbl, txtSupllier,
                btnSupplierSearch, 220.0, 0.0);
        component.deactivateSearch(lblItem, txtItem,
                btnItemSearch, 220.0, 0.0);
        tblReturnNoteList.setEditable(false);
        txtReturnNoteId.setText(returnId);

        existingAdditionalItemToTable();
        validatorInitialization();

    }

    private void existingAdditionalItemToTable() {

        returnNoteData.clear();
        ArrayList<String> DataList = null;
        DataList = retrunNoteDAO.
                loadingReturnNoteInfo(txtReturnNoteId.getText());
        ArrayList<ArrayList<String>> additionalItemInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = retrunNoteDAO.searchDetails(
                txtReturnNoteId.getText());


        txtAreaDescription.setText(DataList.get(1));
        txtDate.setText(DataList.get(2));
        txtSupllier.setText(DataList.get(3));

        if (DataList != null && list != null) {

            for (int i = 0; i < list.size(); i++) {

                additionalItemInfo.add(list.get(i));
            }

            if (additionalItemInfo != null && additionalItemInfo.size() > 0) {
                for (int i = 0; i < additionalItemInfo.size(); i++) {

                    item = new Item();

                    item.colItemId.setValue(additionalItemInfo.get(i).get(0));
                    item.colBatchNo.setValue(additionalItemInfo.get(i).get(1));
                    item.colItemName.setValue(additionalItemInfo.get(i).get(2));
                    item.colReturnDescription.setValue(
                            additionalItemInfo.get(i).get(3));
                    item.colQty.setValue(additionalItemInfo.get(i).get(4));
                    TableItemData.add(item);
                }
            }
        } else {
            mb.ShowMessage(stage, ErrorMessages.InvalidEvent, MessageBoxTitle.ERROR.toString(),
                    MessageBox.MessageIcon.MSG_ICON_FAIL,
                    MessageBox.MessageType.MSG_OK);

        }

    }

    @FXML
    void btnItemSearchOnAction(ActionEvent event) {
        if (!txtSupllier.getText().isEmpty()) {
            itemTableDataLoader(txtItem.getText(), txtSupllier.getText());

            itemTable.setItems(itemData);
            if (!itemData.isEmpty()) {
                itemPop.show(btnItemSearch);
            }
        } else {
            mb.ShowMessage(stage, ErrorMessages.selectSuplier, MessageBoxTitle.ERROR.toString(),
                    MessageBox.MessageIcon.MSG_ICON_FAIL,
                    MessageBox.MessageType.MSG_OK);
        }
        validatorInitialization();
    }

    private void itemTableDataLoader(String keyword,
            String supplier) {

        itemData.clear();
        ArrayList<ArrayList<String>> itemInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = retrunNoteDAO.searchItemDetails(
                keyword, supplier);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {

                itemInfo.add(list.get(i));
            }

            if (itemInfo != null && itemInfo.size() > 0) {
                for (int i = 0; i < itemInfo.size(); i++) {

                    itemPopup = new ItemStockPopup();
                    itemPopup.colItemID.setValue(itemInfo.get(i).get(0));
                    itemPopup.colBatchNo.setValue(itemInfo.get(i).get(1));
                    itemPopup.colItemName.setValue(itemInfo.get(i).get(2));
                    itemPopup.colQty.setValue(itemInfo.get(i).get(3));
                    itemData.add(itemPopup);
                }
            }

        }

    }

    @FXML
    void btnReturnNoteIdSearchOnAction(ActionEvent event) {
        ReturnNoteTableDataLoader(txtReturnNoteId.getText());
        returnNoteTable.setItems(returnNoteData);
        if (!returnNoteData.isEmpty()) {
            returnNotePop.show(btnReturnNoteIdSearch);
        }
        validatorInitialization();
    }

    @FXML
    private void txtSupplierOnKeyReleased(KeyEvent event) {
        if (txtSupllier.getText().length() >= 3) {

            SupplierTableDataLoader(txtSupllier.getText());
            supplierTable.setItems(supplierData);
            if (!supplierData.isEmpty()) {
                supplierPop.show(btnSupplierSearch);
            } else {
                supplierPop.hide();
            }
            validatorInitialization();
        }
    }

    private void ReturnNoteTableDataLoader(String keyword) {

        returnNoteData.clear();
        ArrayList<ArrayList<String>> itemInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = retrunNoteDAO.
                searchReturnNoteDetails(
                        keyword);

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                itemInfo.add(list.get(i));
            }

            if (itemInfo != null && itemInfo.size() > 0) {
                for (int i = 0; i < itemInfo.size(); i++) {

                    returnNotePopup = new ReturnNotePopup();
                    returnNotePopup.colReturnNoteId.setValue(
                            itemInfo.get(i).get(0));
                    returnNotePopup.colDate.setValue(itemInfo.get(i).get(1));
//                    returnNotePopup.colType.setValue(itemInfo.get(i).get(2));
                    returnNoteData.add(returnNotePopup);
                }
            }

        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isRequestNoteInserted = false;
        validatorInitialization();
        boolean validationSupportResult = false;
        boolean validationSupportTableResult = false;

        ValidationResult v = validationSupport.getValidationResult();
        ValidationResult v1 = validationSupportTable.getValidationResult();

        if (v != null && v1 != null) {
            validationSupportResult = validationSupport.isInvalid();
            validationSupportTableResult = validationSupportTable.isInvalid();

            if (validationSupportResult == true || validationSupportTableResult
                    == true) {

                mb.ShowMessage(stage, ErrorMessages.MandatoryError, MessageBoxTitle.ERROR.toString(),
                        MessageBox.MessageIcon.MSG_ICON_FAIL,
                        MessageBox.MessageType.MSG_OK);

            } else if (validationSupportResult == false
                    && validationSupportTableResult == false) {
                String id = retrunNoteDAO.generateID();
                if (insert == true) {
                    isRequestNoteInserted = retrunNoteDAO.insertReturnNote(
                            id,
                            txtDate.getText(),
                            txtAreaDescription.getText(),
                            userId,
                            txtSupllier.getText());
                    saveTableContent(id);
                } else {
                    mb.ShowMessage(stage, ErrorMessages.AccessDenied,
                            MessageBoxTitle.ERROR.toString(), MessageBox.MessageIcon.MSG_ICON_FAIL,
                            MessageBox.MessageType.MSG_OK);
                }
                if (isRequestNoteInserted == true && isTableContentSaved == true) {

                    mb.ShowMessage(stage, ErrorMessages.SuccesfullyCreated,
                            MessageBoxTitle.INFORMATION.toString(),
                            MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                            MessageBox.MessageType.MSG_OK);

                    clearInput();

                } else {
                    mb.ShowMessage(stage, ErrorMessages.NotSuccesfullyCreated,
                            MessageBoxTitle.ERROR.toString(), MessageBox.MessageIcon.MSG_ICON_FAIL,
                            MessageBox.MessageType.MSG_OK);
                }
                //Save Action Event
            }
        }
    }

    private void saveTableContent(String Id) {

        Item itemTable;

//// Loading to db
////=============================================================================================================== 
        if (tblReturnNoteList.getItems().size() != 0) {
            for (int i = 0; i < tblReturnNoteList.getItems().size(); i++) {
                itemTable = (Item) tblReturnNoteList.getItems().get(i);

                isTableContentSaved = retrunNoteDAO.insertReturnNoteItems(
                        Id,
                        itemTable.getColItemId(),
                        itemTable.getColBatchNo(),
                        itemTable.getColReturnDescription(),
                        Double.parseDouble(itemTable.getColQty()));

            }
        }

    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        boolean isDeleted = false;
        validatorInitialization();
        boolean validationSupportResult = false;
        boolean validationSupportTableResult = false;

        ValidationResult v = validationSupport.getValidationResult();
        ValidationResult v1 = validationSupportTable.getValidationResult();

        if (v != null && v1 != null) {
            validationSupportResult = validationSupport.isInvalid();
            validationSupportTableResult = validationSupportTable.isInvalid();

            if (validationSupportResult == true || validationSupportTableResult
                    == true) {
                mb.ShowMessage(stage, ErrorMessages.MandatoryError, MessageBoxTitle.ERROR.toString(),
                        MessageBox.MessageIcon.MSG_ICON_FAIL,
                        MessageBox.MessageType.MSG_OK);

            } else if (validationSupportResult == false
                    && validationSupportTableResult == false) {
                if (retrunNoteDAO.checkingReturnNoteAvailability(
                        txtReturnNoteId.getText())) {
                    MessageBox.MessageOutput option = mb.ShowMessage(stage,
                            ErrorMessages.Delete, MessageBoxTitle.INFORMATION.toString(),
                            MessageBox.MessageIcon.MSG_ICON_NONE,
                            MessageBox.MessageType.MSG_YESNO);
                    if (option.equals(MessageBox.MessageOutput.MSG_YES)) {
                        if (delete == true) {
                            isDeleted = retrunNoteDAO.deleteReturnNote(
                                    txtReturnNoteId.getText());
                        } else {
                            mb.ShowMessage(stage, ErrorMessages.AccessDenied,
                                    MessageBoxTitle.ERROR.toString(),
                                    MessageBox.MessageIcon.MSG_ICON_FAIL,
                                    MessageBox.MessageType.MSG_OK);
                        }

                        if (isDeleted == true) {

                            //Add Log information of delete record in database
                            mb.ShowMessage(stage,
                                    ErrorMessages.SuccesfullyDeleted,
                                    MessageBoxTitle.INFORMATION.toString(),
                                    MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                                    MessageBox.MessageType.MSG_OK);
                            clearInput();

                        } else {
                            mb.ShowMessage(stage, ErrorMessages.Error, MessageBoxTitle.ERROR.toString(),
                                    MessageBox.MessageIcon.MSG_ICON_FAIL,
                                    MessageBox.MessageType.MSG_OK);
                        }
                    }
                } else {
                    mb.ShowMessage(stage, ErrorMessages.InvalidId, MessageBoxTitle.ERROR.toString(),
                            MessageBox.MessageIcon.MSG_ICON_FAIL,
                            MessageBox.MessageType.MSG_OK);
                }
            }
        }
    }

    void cmbTypeOnAction(ActionEvent event) {

    }

    void cmbCategoryOnAction(ActionEvent event) {

    }

    @FXML
    void txtAreaDescriptionOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtAreaItemDescriptionOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtUsernameOnkeyReleased(KeyEvent event) {

    }

    @FXML
    void txtReturnNoteIdOnKeyReleased(KeyEvent event) {
        if (txtReturnNoteId.getText().length() >= 3) {
            ReturnNoteTableDataLoader(txtReturnNoteId.getText());
            returnNoteTable.setItems(returnNoteData);
            if (!returnNoteData.isEmpty()) {
                returnNotePop.show(btnReturnNoteIdSearch);
            } else {
                returnNotePop.hide();
            }
            validatorInitialization();
        }
    }

    @FXML
    void txtDateOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtItemOnKeyReleased(KeyEvent event) {
        if (txtItem.getText().length() >= 3) {
            if (!txtSupllier.getText().isEmpty()) {
                itemTableDataLoader(txtItem.getText(), txtSupllier.getText());
                itemTable.setItems(itemData);
                if (!itemData.isEmpty()) {
                    itemPop.show(btnItemSearch);
                } else {
                    itemPop.hide();
                }
            } else {
                mb.ShowMessage(stage, ErrorMessages.selectSuplier, MessageBoxTitle.ERROR.toString(),
                        MessageBox.MessageIcon.MSG_ICON_FAIL,
                        MessageBox.MessageType.MSG_OK);
            }
        }
        validatorInitialization();
    }

    @FXML
    void txtItemIdOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtQtyOnKeyReleased(KeyEvent event) {
        boolean validationSupportResult = false;
        validatorInitialization();
        if (event.getCode() == KeyCode.ENTER) {

            ValidationResult v = validationSupportTableData.
                    getValidationResult();
            if (v != null) {

                validationSupportResult = validationSupportTableData.isInvalid();
                if (validationSupportResult == true) {
                    mb.ShowMessage(stage, ErrorMessages.MandatoryError, MessageBoxTitle.ERROR.toString(),
                            MessageBox.MessageIcon.MSG_ICON_FAIL,
                            MessageBox.MessageType.MSG_OK);

                } else if (validationSupportResult == false) {

                    double qty = Double.parseDouble(txtQty.getText());
                    if (tblReturnNoteList.getItems().size() != 0) {
                        int n = tblReturnNoteList.getItems().size();
                        for (int s = 0; s < n; s++) {
                            item = (Item) tblReturnNoteList.getItems().get(s);
                            if (txtItemId.getText().equals(item.getColItemId())
                                    && tblReturnNoteList.getItems().size() > 0) {
                                TableItemData.remove(s);
                                n--;
                            }
                        }
                    }
                    if (qty <= availableQty && qty > 0) {

                        item = new Item();
                        item.colItemId.setValue(txtItemId.getText());
                        item.colBatchNo.setValue(txtBatchNo.getText());
                        item.colItemName.setValue(txtItem.getText());
                        item.colReturnDescription.setValue(
                                txtAreaReturnDescription.getText());
                        item.colQty.setValue(txtQty.getText());
                        TableItemData.add(item);

                    } else {
                        mb.ShowMessage(stage, txtItemId.getText() + " " + ErrorMessages.ItemOutOfStock + " or Invalid Quantity",
                                MessageBoxTitle.ERROR.toString(),
                                MessageBox.MessageIcon.MSG_ICON_FAIL,
                                MessageBox.MessageType.MSG_OK);
                    }

                    txtItemId.clear();
                    txtItem.clear();
                    txtAreaReturnDescription.clear();
                    txtBatchNo.clear();
                    txtQty.clear();
                }
            }
        }
        validatorInitialization();
    }

    @FXML
    void tblReturnNoteListOnMouseClicked(
            javafx.scene.input.MouseEvent mouseEvent) {
        try {

            if (mouseEvent.getClickCount() == 2) {

                boolean model = tblReturnNoteList.getSelectionModel().isEmpty();

                if (model == false) {
                    txtItemId.setText(tblReturnNoteList.getSelectionModel().
                            getSelectedItem().getColItemId());
                    txtItem.setText(tblReturnNoteList.getSelectionModel().
                            getSelectedItem().getColItemName());
                    txtBatchNo.setText(tblReturnNoteList.getSelectionModel().
                            getSelectedItem().getColBatchNo());
                    txtAreaReturnDescription.setText(tblReturnNoteList.
                            getSelectionModel().getSelectedItem().
                            getColReturnDescription());

                    txtQty.setText(tblReturnNoteList.getSelectionModel().
                            getSelectedItem().getColQty());

                    TableItemData.remove(
                            tblReturnNoteList.getSelectionModel().
                            getSelectedIndex());

                }
                validatorInitialization();

            }
        } catch (Exception e) {

        }
    }

    private void validatorInitialization() {
        validationSupport.registerValidator(txtReturnNoteId,
                new CustomTextFieldValidationImpl(txtReturnNoteId,
                        !fav.validName(txtReturnNoteId.getText()),
                        ErrorMessages.InvalidId));

        validationSupportTableData.registerValidator(txtItemId,
                new CustomTextFieldValidationImpl(txtItemId,
                        !fav.validName(txtItemId.getText()),
                        ErrorMessages.InvalidId));

        validationSupportTableData.registerValidator(txtBatchNo,
                new CustomTextFieldValidationImpl(txtBatchNo,
                        !fav.validName(txtBatchNo.getText()),
                        ErrorMessages.InvalidId));

        validationSupportTableData.registerValidator(txtItem,
                new CustomTextFieldValidationImpl(txtItem,
                        !fav.validName(txtItem.getText()), ErrorMessages.Error));

        validationSupportTableData.registerValidator(txtQty,
                new CustomTextFieldValidationImpl(txtQty,
                        !fav.isDouble(txtQty.getText()),
                        ErrorMessages.InvalidQty));

        validationSupportTable.registerValidator(tblReturnNoteList,
                new CustomTableViewValidationImpl(tblReturnNoteList,
                        !fav.validTableView(tblReturnNoteList),
                        ErrorMessages.EmptyListView));
    }

    private void setUiMode(UiMode uiMode) {

        switch (uiMode) {

            case SAVE:
                disableUi(false);
                component.deactivateSearch(lblRequestId, txtReturnNoteId,
                        btnReturnNoteIdSearch,
                        220.0, USE_PREF_SIZE);
                break;

            case DELETE:
                disableUi(false);
                btnSave.setDisable(true);
                btnSave.setVisible(false);

                break;

            case READ_ONLY:
                disableUi(false);
                btnClose.setDisable(false);
                btnClose.setVisible(true);

                btnSave.setDisable(true);
                btnSave.setVisible(false);

                btnDelete.setDisable(true);
                btnDelete.setVisible(false);

                break;

            case ALL_BUT_DELETE:
                disableUi(false);

                break;

            case FULL_ACCESS:
                disableUi(false);
//                btnDelete.setDisable(true);
                btnDelete.setVisible(false);

                break;

            case NO_ACCESS:
                disableUi(true);

                break;

        }

    }

    private void setUserAccessLevel() {

        userId = UserSession.getInstance().getUserInfo().getEid();
        userName = UserSession.getInstance().getUserInfo().getName();
        userCategory = UserSession.getInstance().getUserInfo().getCategory();
        txtUsername.setText(userName);

        String title = stage.getTitle();

        if (!title.isEmpty()) {

            UserPermission userPermission = UserSession.getInstance().
                    findPermission(title);

            if (userPermission.canInsert() == true) {
                insert = true;

            }

            if (userPermission.canDelete() == true) {
                delete = true;

            }

            if (userPermission.canUpdate() == true) {
                update = true;

            }

            if (userPermission.canView() == true) {
                view = true;

            }

            if (insert == true && delete == true && update == true && view
                    == true) {

                setUiMode(UiMode.FULL_ACCESS);

            } else if (insert == false && delete == true && update == true
                    && view
                    == true) {

                setUiMode(UiMode.FULL_ACCESS);

            } else if (insert == true && delete == false && update == true
                    && view
                    == true) {

                setUiMode(UiMode.ALL_BUT_DELETE);

            } else if (insert == true && delete == true && update == false
                    && view
                    == true) {

                setUiMode(UiMode.FULL_ACCESS);

            } else if (insert == true && delete == true && update == true
                    && view
                    == false) {

                setUiMode(UiMode.SAVE);

            } else if (insert == false && delete == false && update == true
                    && view
                    == true) {

                setUiMode(UiMode.FULL_ACCESS);

            } else if (insert == false && delete == true && update == false
                    && view
                    == true) {

                setUiMode(UiMode.DELETE);

            } else if (insert == false && delete == true && update == true
                    && view
                    == false) {

                setUiMode(UiMode.NO_ACCESS);

            } else if (insert == true && delete == false && update == false
                    && view
                    == true) {

                setUiMode(UiMode.ALL_BUT_DELETE);

            } else if (insert == true && delete == false && update == true
                    && view
                    == false) {

                setUiMode(UiMode.SAVE);

            } else if (insert == true && delete == true && update == false
                    && view
                    == false) {

                setUiMode(UiMode.SAVE);

            } else if (insert == false && delete == false && update == false
                    && view
                    == true) {
                setUiMode(UiMode.READ_ONLY);

            } else if (insert == false && delete == false && update == true
                    && view
                    == false) {

                setUiMode(UiMode.NO_ACCESS);

            } else if (insert == false && delete == true && update == false
                    && view
                    == false) {

                setUiMode(UiMode.NO_ACCESS);

            } else if (insert == true && delete == false && update == false
                    && view
                    == false) {

                setUiMode(UiMode.SAVE);

            } else if (insert == false && delete == false && update == false
                    && view
                    == false) {

                setUiMode(UiMode.NO_ACCESS);

            }
        } else {

            setUiMode(UiMode.NO_ACCESS);

        }

    }

    private void disableUi(boolean state) {

        btnClose.setDisable(state);
        btnClose.setVisible(!state);
        btnSave.setDisable(state);
        btnSave.setVisible(!state);
        btnDelete.setDisable(state);
        btnDelete.setVisible(!state);
        btnReturnNoteIdSearch.setDisable(state);
        btnReturnNoteIdSearch.setVisible(!state);
        btnItemSearch.setDisable(state);
        btnItemSearch.setVisible(!state);

        txtUsername.setDisable(state);
        txtUsername.setVisible(!state);

        txtReturnNoteId.setDisable(state);
        txtReturnNoteId.setVisible(!state);
//        cmbType.setVisible(!state);
//        cmbType.setDisable(state);
        txtDate.setDisable(state);
        txtDate.setVisible(!state);
        txtAreaDescription.setVisible(!state);
        txtAreaDescription.setDisable(state);
        txtUsername.setDisable(state);
        txtUsername.setVisible(!state);

        txtItemId.setDisable(state);
        txtItemId.setVisible(!state);
        txtItem.setVisible(!state);
        txtItem.setDisable(state);
        txtBatchNo.setVisible(!state);
        txtBatchNo.setDisable(state);
        txtAreaReturnDescription.setVisible(!state);
        txtAreaReturnDescription.setDisable(state);

        txtQty.setVisible(!state);
        txtQty.setDisable(state);

        tblReturnNoteList.setVisible(!state);
        tblReturnNoteList.setDisable(state);

    }

    @FXML
    private void btnSupplierSearchOnAction(ActionEvent event) {

        SupplierTableDataLoader(txtSupllier.getText());
        supplierTable.setItems(supplierData);
        if (!supplierData.isEmpty()) {
            supplierPop.show(btnSupplierSearch);
        }
        validatorInitialization();
    }

    public class Item {

        public SimpleStringProperty colItemId = new SimpleStringProperty(
                "tcItemId");
        public SimpleStringProperty colBatchNo = new SimpleStringProperty(
                "tcBatchNo");
        public SimpleStringProperty colItemName = new SimpleStringProperty(
                "tcItemName");
        public SimpleStringProperty colReturnDescription
                = new SimpleStringProperty(
                        "tcReturnDescription");

        public SimpleStringProperty colQty = new SimpleStringProperty("tcQty");

        public String getColItemId() {
            return colItemId.get();
        }

        public String getColBatchNo() {
            return colBatchNo.get();
        }

        public String getColItemName() {
            return colItemName.get();
        }

        public String getColReturnDescription() {
            return colReturnDescription.get();
        }

        public String getColQty() {
            return colQty.get();
        }

    }

}
