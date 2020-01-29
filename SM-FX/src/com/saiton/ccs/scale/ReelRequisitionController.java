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
    private TableColumn<?, ?> tcInventoryDate;

    @FXML
    private Button btnRefreshReturnedWeight;

    @FXML
    private Button btnRePrint;

    @FXML
    private TextField txtReelFb;

    @FXML
    private TextField txtReturnedWeight;

    @FXML
    private TableView<?> tblItemList;

    @FXML
    private TableColumn<?, ?> tcItemCode;

    @FXML
    private TableColumn<?, ?> tcItemDate;

    @FXML
    private TableColumn<?, ?> tcReleased;

    @FXML
    private TextField txtLogDate;

    @FXML
    private Label lblItemId;

    @FXML
    private TableColumn<?, ?> tcReturned;

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
    private TableColumn<?, ?> tcDescription;
    @FXML
    private TextField txtReelLiner;

    private Stage stage;

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Action Events">
    @FXML
    private void btnRefreshItemCodeOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSearchItemCodeOnAction(ActionEvent event) {
    }

    @FXML
    private void btnRePrintOnAction(ActionEvent event) {
    }

    @FXML
    private void btnLogOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
    }

    @FXML
    private void btnRefreshReturnedWeightOnAction(ActionEvent event) {
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Key Events">
    @FXML
    private void txtItemCodeOnKeyReleased(KeyEvent event) {
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

    }

    @Override
    public void setStage(Stage stage, Object[] obj) {

        this.stage = stage;

    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearInput() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearValidations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//</editor-fold>
}
