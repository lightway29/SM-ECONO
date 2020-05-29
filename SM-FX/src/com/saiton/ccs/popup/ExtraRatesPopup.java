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


public class ExtraRatesPopup {

 
    public SimpleStringProperty colExtraRateCode = new SimpleStringProperty("tcExtraRateCode");
    public SimpleStringProperty colSeason = new SimpleStringProperty("tcSeason");

    public String getColExtraRateCode() {
        return colExtraRateCode.get();
    }
    
    public String getColSeason() {
        return colSeason.get();
    }
    
   public TableView tableViewLoader(ObservableList observableList){
       TableView tableView = new TableView();
       
       
       TableColumn tcExtraRateCode = new TableColumn("Extra Rate Code");
        tcExtraRateCode.setMinWidth(100);
        tcExtraRateCode.setCellValueFactory(
                new PropertyValueFactory<>("colExtraRateCode"));
        
        TableColumn tcSeason = new TableColumn("Season");
        tcSeason.setMinWidth(100);
        tcSeason.setCellValueFactory(
                new PropertyValueFactory<>("colSeason"));

        tableView.setItems(observableList);
        tableView.getColumns().addAll(tcExtraRateCode,tcSeason);
   
   return tableView;
   }

}
