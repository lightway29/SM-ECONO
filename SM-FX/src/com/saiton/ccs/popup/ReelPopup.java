/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saiton.ccs.popup;

import com.saiton.ccs.base.CustomerRegistrationController;
import com.saiton.ccs.basedao.CustomerRegistrationDAO;
import com.saiton.ccs.uihandle.StagePassable;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ReelPopup {

    public SimpleStringProperty colReelNumber = new SimpleStringProperty(
            "tcReelNumber");
    public SimpleStringProperty colItemName = new SimpleStringProperty(
            "tcItemName");
    public SimpleStringProperty colReelCode = new SimpleStringProperty(
            "tcReelCode");
    public SimpleStringProperty colSerialNumber = new SimpleStringProperty(
            "tcSerialNumber");

    public String getColReelNumber() {
        return colReelNumber.get();
    }

    public String getColItemName() {
        return colItemName.get();
    }

    public String getColReelCode() {
        return colReelCode.get();
    }

    public String getColSerialNumber() {
        return colSerialNumber.get();
    }

    public TableView tableViewLoader(ObservableList observableList) {
        TableView tableView = new TableView();

        TableColumn tcReelNumber = new TableColumn("Reel No");
        tcReelNumber.setMinWidth(100);
        tcReelNumber.setCellValueFactory(
                new PropertyValueFactory<>("colReelNumber"));

        TableColumn tcItemName = new TableColumn("Name");
        tcItemName.setMinWidth(100);
        tcItemName.setCellValueFactory(
                new PropertyValueFactory<>("colItemName"));

        TableColumn tcReelCode = new TableColumn("Reel Code");
        tcReelCode.setMinWidth(100);
        tcReelCode.setCellValueFactory(
                new PropertyValueFactory<>("colReelCode"));

        TableColumn tcSerialNumber = new TableColumn("Serial No");
        tcSerialNumber.setMinWidth(150);
        tcSerialNumber.setCellValueFactory(
                new PropertyValueFactory<>("colSerialNumber"));

        tableView.setItems(observableList);
        tableView.getColumns().addAll(tcReelCode, tcSerialNumber, tcItemName,
                tcReelNumber);

        return tableView;
    }

}
