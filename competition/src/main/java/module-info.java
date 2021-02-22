module com.competition {
    //JavaFX
    requires javafx.controls;
    //

    //Junit
    requires junit;
    requires org.testng;
    requires org.junit.jupiter;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;
    requires org.junit.platform.commons;
    //

    //Jackson
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    //

    requires javafx.fxml;

    exports com.competition.JSONDataBase.PlayerRanking.PlayerData;
    exports com.competition.JSONDataBase.PlayerRanking.Team;
    exports com.competition.JSONDataBase.PlayerRanking;
    exports com.competition.JSONDataBase.Json;
    exports com.competition.Main;
    exports com.competition.bracket;
    exports com.competition.menu;
    exports com.competition.MainPage;
    exports com.competition.Player;

    opens com.competition.MainPage to javafx.fxml;
    opens com.competition.Player to javafx.fxml;
}
