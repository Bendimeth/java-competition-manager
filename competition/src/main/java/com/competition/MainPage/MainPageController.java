package com.competition.MainPage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    private final Stage thisStage;
    @FXML private AnchorPane paneShow;
    @FXML private Label LabelTest;
    @FXML private Button btnhelp;
    @FXML private Button btnranking;
    @FXML private Button btneditplayerteam;
    @FXML private Button btncreatetournament;
    @FXML private Button btnhistory;
    public static AnchorPane PublicPane;

    public MainPageController()throws IOException {
        thisStage = new Stage();
        URL url = new File("competition/src/main/java/com/competition/MainPage/MainPage.fxml").toURI().toURL();
        try {
            FXMLLoader fxmlLoader= new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setController(this);
            Parent root=fxmlLoader.load();
            MainPageController controller=fxmlLoader.getController();
            Scene scene = new Scene(root);
            thisStage.setTitle("Tournament App");
            thisStage.setMaximized(true);
            thisStage.setScene(scene);
        }catch (Exception ex){
            //log error
            var newex=ex.getMessage();
        }
    }

    public void start_main_page(Stage stage) throws IOException {
        thisStage.showAndWait();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnhelp.setOnAction(event -> btnclick_help());
        btnranking.setOnAction(event -> btnclick_ranking());
        btneditplayerteam.setOnAction(event -> btnclick_editplayerteam());
        btncreatetournament.setOnAction(event -> btnclick_createtournament());
        btnhistory.setOnAction(event -> btnclick_history());

    }


    public void btnclick_ranking(){

    }


    public void btnclick_createtournament(){

    }

    public void btnclick_editplayerteam(){

    }

    public void btnclick_history(){

    }

    public void btnclick_help(){
        try {
            URL url = new File("competition/src/main/java/com/competition/Player/PlayerIndex.fxml").toURI().toURL();
            FXMLLoader fxmlLoader= new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setController(this);
            Pane playerPane=fxmlLoader.load();
            //this.PaneShow = new AnchorPane(playerPane);
            this.paneShow.getChildren().add(playerPane);
//            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/application/fxml2.fxml"));
//            secPane.getChildren().add(newLoadedPane);
        }catch (Exception ex){
            //log error
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }



}
