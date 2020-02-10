package com.saiton.ccs.base;

import com.saiton.ccs.msgbox.MessageBox;
import com.saiton.ccs.msgbox.SimpleMessageBoxFactory;
import com.saiton.ccs.uihandle.FxmlUiLauncher;
import com.saiton.ccs.uihandle.ReportGenerator;
import com.saiton.ccs.ui.fxhome.ConcreteUiPageManager;
import com.saiton.ccs.ui.fxhome.ConcreteUiTitle;
import com.saiton.ccs.ui.fxhome.FxHome;
import com.saiton.ccs.ui.fxhome.TileColors;
import com.saiton.ccs.ui.fxhome.UiLink;
import com.saiton.ccs.ui.fxhome.UiLinkFactory;
import com.saiton.ccs.ui.fxhome.UiPageManager;
import com.saiton.ccs.database.Starter;
import com.saiton.ccs.printerservice.PrinterServiceServer;
import com.saiton.ccs.ui.fxhome.TileDescription;
import com.saiton.ccs.util.XMLFileReader;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.log4j.Logger;
import org.w3c.dom.NodeList;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import org.w3c.dom.Document;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

public class CCSHome extends Application implements HomeCallback {

    private final Logger log = Logger.getLogger(this.getClass());

    private FxHome home;

    private Node login;

    private Stage stage;

    private LoginAccess loginAccess = null;

    private final MessageBox mb = SimpleMessageBoxFactory.createMessageBox();
    private XMLFileReader xmlFileReader = new XMLFileReader();

    @Override
    public void start(Stage stage) {

    //<editor-fold defaultstate="collapsed" desc="Printer Service">
        //Server configuration
//        File file = new File("serverConfig.xml");
//
//        if (file.exists()) {
//
//            if (xmlFileReader.isIsServer() == true) {
//
//                PrinterServiceServer printerServiceServer
//                        = new PrinterServiceServer();
//                printerServiceServer.startPrintJobScanner();
//
//            }
//
//        }
//</editor-fold>
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        
        this.stage = stage;

        if (ApplicationProperties.getInstance().isInvalid()) {
            FxmlUiLauncher.launchOnNewStageWait(
                    "/com/saiton/ccs/base/DatabaseCon.fxml",
                    "Database Connection", null);
        }

        if (ApplicationProperties.getInstance().isInvalid() || !Starter.start()) {
            mb.ShowMessage(stage,
                    "Database connection failed.\n"
                    + "Connection details will now reset.\n"
                    + "Application will now close.",
                    "Start",
                    MessageBox.MessageIcon.MSG_ICON_FAIL,
                    MessageBox.MessageType.MSG_OK);
            ApplicationProperties.getInstance().reset();
            Platform.exit();
            System.exit(0);
        }

//        log.info("CCS Initiated. " + (ApplicationProperties.getInstance().
//                isFingerAvailable() ? "Finger Print Present" : "No Finger Print"));

        try {

            createHomeUi();
            createLoginUi();
            NotificationFacade.start(); //nothing happens unless user is logged in
            startLoginUi(); //disable this to hide login
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createHomeUi() {
        home = new FxHome(new ConcreteUiTitle());

        //<editor-fold defaultstate="collapsed" desc="Home">
        home.createTileButton("User \nRegistration",
                TileColors.TILE_COLOR_BLUEVIOLET,
                "/com/saiton/ccs/res/img-user.png", FxHome.HOME_PAGE,
                e -> FxmlUiLauncher.launchOnNewStage(
                        "/com/saiton/ccs/base/UserRegistration.fxml",
                        "User Registration"));
        
        home.createTileButton("Report \nGenerator" ,
                TileColors.TILE_COLOR_DARKCYAN,
                "/com/saiton/ccs/res/img-external-return-note.png",
                FxHome.HOME_PAGE,
                e -> FxmlUiLauncher.launchOnNewStage(
                        "/com/saiton/ccs/report/ReportGenerator.fxml",
                        "Report Generator"));
        
        home.createTileButton("Stock Report",
                TileColors.TILE_COLOR_AQUAMARINE,
                "/com/saiton/ccs/res/img-external-grn.png",
                FxHome.HOME_PAGE,
                e -> {
            HashMap param = new HashMap();
            File fil
                    = new File(".//Reports//Stock.jasper");
            String img = fil.getAbsolutePath();
            ReportGenerator r = new ReportGenerator(img, param);
            r.setVisible(true);
        });
        
        home.createTileButton("Weight Scale",
         TileColors.TILE_COLOR_CORAL,
         "/com/saiton/ccs/res/img-suppliers.png", FxHome.HOME_PAGE,
         e -> FxmlUiLauncher.launchOnNewStage(
         "/com/saiton/ccs/scale/Scale.fxml",
                 "Weight Scale"));
        
        home.createTileButton("Search Weight \nScale",
         TileColors.TILE_COLOR_SPRINGGREEN,
         "/com/saiton/ccs/res/img-report-settings.png", FxHome.HOME_PAGE,
         e -> FxmlUiLauncher.launchOnNewStage(
         "/com/saiton/ccs/scale/SearchWeightScale.fxml",
                 "Search Weight Scale"));
        
        home.createTileButton("Customer \nRegistration",
         TileColors.TILE_COLOR_MAGENTA,
         "/com/saiton/ccs/res/img-customer.png", FxHome.HOME_PAGE,
         e -> FxmlUiLauncher.launchOnNewStage(
         "/com/saiton/ccs/base/CustomerRegistration.fxml",
                 "Customer Registration"));
        
        home.createTileButton("Reel \nRequisition",
                TileColors.TILE_COLOR_CORAL,
                "/com/saiton/ccs/res/img-user.png", FxHome.HOME_PAGE,
                e -> FxmlUiLauncher.launchOnNewStage(
                        "/com/saiton/ccs/scale/ReelRequisition.fxml",
                        "Reel Requisition"));
        
        home.createTileButton("Work \nIn-progress",
                TileColors.TILE_COLOR_AQUAMARINE,
                "/com/saiton/ccs/res/img-user.png", FxHome.HOME_PAGE,
                e -> FxmlUiLauncher.launchOnNewStage(
                        "/com/saiton/ccs/scale/SearchWeightScaleBulkPrint.fxml",
                        "Work In-progress"));

      
         
        //</editor-fold>  
        //<editor-fold defaultstate="collapsed" desc="Settings">
         home.createTileButton("Printer \nRegistration",
                TileColors.TILE_COLOR_CORAL,
                "/com/saiton/ccs/res/img-printer-registration.png",
                FxHome.SETTINGS_PAGE,
                e -> FxmlUiLauncher.launchOnNewStage(
                        "/com/saiton/ccs/report/PrinterRegistration.fxml",
                        "Printer Registration"));

        home.createTileButton("Report \nRegistration",
                TileColors.TILE_COLOR_CYAN,
                "/com/saiton/ccs/res/img-report-registration.png",
                FxHome.SETTINGS_PAGE,
                e -> FxmlUiLauncher.launchOnNewStage(
                        "/com/saiton/ccs/report/ReportRegistration.fxml",
                        "Report Registration"));

        home.createTileButton("Report \nSettings",
                TileColors.TILE_COLOR_DARKCYAN,
                "/com/saiton/ccs/res/img-report-settings.png", 
                FxHome.SETTINGS_PAGE,
                e -> FxmlUiLauncher.launchOnNewStage(
                        "/com/saiton/ccs/report/ReportSettings.fxml",
                        "Report Settings"));
        
        home.createTileButton("Scale \nRegistration",
                TileColors.TILE_COLOR_PLUM,
                "/com/saiton/ccs/res/img-report-settings.png", 
                FxHome.SETTINGS_PAGE,
                e -> FxmlUiLauncher.launchOnNewStage(
                        "/com/saiton/ccs/scale/ScaleRegister.fxml",
                        "Scale Registration"));
    
          



//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Left Bar">
        UiPageManager manager = home.getPageManager();
        manager.registerUiPage(ConcreteUiPageManager.buildUiPage(FxmlUiLauncher.
                FxmlToNode("/com/saiton/ccs/base/Notify.fxml", stage), "notify"));

        ArrayList<UiLink> links = new ArrayList<>();
        links.add(UiLinkFactory.createUiLink("Home",
                "/com/saiton/ccs/res/img-leftnav-home.png", fxHome -> {
            fxHome.getPageManager().navigateTo(FxHome.HOME_PAGE);
        }));

        links.add(UiLinkFactory.createUiLink("Settings",
                "/com/saiton/ccs/res/img-alacarte.png", fxHome -> {
            fxHome.getPageManager().navigateTo(FxHome.SETTINGS_PAGE);
        }));

        links.add(UiLinkFactory.createUiLink("Logout",
                "/com/saiton/ccs/res/img-leftnav-logout.png", fxHome
                -> {
            UserSession.getInstance().logout();
            startLoginUi();
        }));
        home.createUiLinks(links);
//</editor-fold>

        home.start();
    }

    private void createLoginUi() {
        if (ApplicationProperties.getInstance().isFingerAvailable()) {
            login = FxmlUiLauncher.
                    FxmlToNode("/com/saiton/ccs/base/Login.fxml",
                            stage, new Object[]{this});
        } else {
            login = FxmlUiLauncher.FxmlToNode(
                    "/com/saiton/ccs/base/LoginNoFinger.fxml",
                    stage, new Object[]{this});
        }

    }

    @Override
    public void startHomeUi() {
        FxmlUiLauncher.launchContentReplacement(home,
                "CCS: Dashboard", stage, null,
                scene -> {
            scene.lookup("#StageMenu").setVisible(false); // no icon on left
            scene.lookup("#TitleLabel").setVisible(false); // no title                
        });

        stage.centerOnScreen();
    }

    @Override
    public void onLoginSuccess() {
        home.hideAllTiles();
        HashMap<TileDescription, Button> map = home.getTilesMap();

        // during development 
        //comment the "permission based version" and keep "show all"
        //show tiles based on permission
        //--------------------------------------------
        map.keySet().forEach((TileDescription key) -> {
            UserPermission permission = UserSession.getInstance().
                    findPermission(key.getTitle());
            if (permission != null && (permission.canView() || permission.
                    canInsert())) {
                Button btn = map.get(key);
                if (btn != null) {
                    home.addButton(key.getPage(), btn);
                    btn.setVisible(true);
                    btn.setDisable(false);
                }
            }
        });
//        //---------------------------------------

        //-----------------------------------
        //show all 
//        map.keySet().forEach((TileDescription key) -> {
//            Button btn = map.get(key);
//            home.addButton(key.getPage(), btn);
//            btn.setVisible(true);
//            btn.setDisable(false);
//        });
        //-------------------
        NotificationFacade.showNotifications();
    }

    @Override
    public void startLoginUi() {
        FxmlUiLauncher.launchContentReplacement(login, "Login", stage);
        if (loginAccess != null) {
            loginAccess.init();
        }

        stage.centerOnScreen();
    }

    @Override
    public void setLoginAccess(LoginAccess access) {
        loginAccess = access;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
