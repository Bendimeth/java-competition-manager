package com.competition.Main;

import com.competition.MainPage.MainPageController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MainPageController.start_main_page(stage);

    }


    public static void main(String[] args) {

        launch(args);
     }
}