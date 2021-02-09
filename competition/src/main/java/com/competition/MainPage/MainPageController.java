package com.competition.MainPage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    //@FXML
//    ImageView imageView;
    @FXML
    private static AnchorPane PaneShow;

    public MainPageController()throws IOException {
    }

    public static void start_main_page(Stage stage) throws IOException {
        URL url = new File("competition/src/main/java/com/competition/MainPage/MainPage.fxml").toURI().toURL();
        try {
            FXMLLoader fxmlLoader= new FXMLLoader();
            AnchorPane root=fxmlLoader.load(url);
            Scene scene = new Scene(root);
            stage.setTitle("FXML Welcome");
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            //log error
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void btnclick_ranking(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(String.valueOf("ex"));
        alert.showAndWait();

    }

    @FXML
    public void btnclick_createtournament(){

    }
    @FXML
    public void btnclick_editplayerteam() throws IOException {
        URL url = new File("competition/src/main/java/com/competition/Player/PlayerIndex.fxml").toURI().toURL();
        try {
            Pane playerPane =  FXMLLoader.load(url);

            PaneShow.getChildren().add(playerPane);
        }catch (Exception ex){
            //log error
        }
    }
    @FXML
    public void btnclick_history(){

    }
    @FXML
    public void btnclick_help(){

    }



}
