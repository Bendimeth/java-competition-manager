package com.competition.Main;

import com.competition.JSONDataBase.PlayerRanking.PlayerRanking;
import com.competition.JSONDataBase.PlayerRanking.PlayerRankingTest;
import com.competition.MainPage.MainPageController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * JavaFX App
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        String path = PlayerRanking.getPath();
        File file =new File(path);
        if(!file.exists()){
            PlayerRanking playerRanking = PlayerRankingTest.generateExampleRanking();
            playerRanking.saveRankingToFile();
        }
        MainPageController controller =new MainPageController();

        controller.start_main_page(stage);

    }

    public static void main(String[] args) {
        launch(args);
     }
}