/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saiton.ccs.popup;

import com.saiton.ccs.base.CustomerRegistrationController;
import com.saiton.ccs.basedao.CustomerRegistrationDAO;
import com.saiton.ccs.ui.StagePassable;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ReportPopup {

    public SimpleStringProperty colReportID = new SimpleStringProperty("tcReportID");
    public SimpleStringProperty colReportName = new SimpleStringProperty("tcReportName");
    

    public String getColReportID() {
        return colReportID.get();
    }

    public String getColReportName() {
        return colReportName.get();
    }
    
    
  

    public TableView tableViewLoader(ObservableList observableList) {
        TableView tableView = new TableView();

        TableColumn tcReportID = new TableColumn("Report ID");
        tcReportID .setMinWidth(100);
        tcReportID .setCellValueFactory(
                new PropertyValueFactory<>("colReportID"));
        
        TableColumn tcReportName = new TableColumn("Report Name");
        tcReportName.setMinWidth(400);
        tcReportName.setCellValueFactory(
                new PropertyValueFactory<>("colReportName"));
        

        tableView.setItems(observableList);
        tableView.getColumns().addAll(tcReportID , tcReportName);

        return tableView;
    }

}
