package com.competition.Player;

import com.competition.MainPage.MainPageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerController implements Initializable {

    private final Stage thisStage;
    private final MainPageController controller1;

    public PlayerController(MainPageController controller1)throws IOException {
        this.controller1 = controller1;
        thisStage = new Stage();
        URL url = new File("competition/src/main/java/com/competition/Player/PlayerIndex.fxml").toURI().toURL();
        try {
            FXMLLoader fxmlLoader= new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setController(this);
            Parent root=fxmlLoader.load();
            //MainPageController controller=fxmlLoader.getController();
            Scene scene = new Scene(root);
            //thisStage.setTitle("FXML");
            thisStage.setMaximized(true);
            thisStage.setScene(scene);
        }catch (Exception ex){
            //log error
            var newex=ex.getMessage();
        }
    }
    public void showStage() {
        thisStage.showAndWait();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
