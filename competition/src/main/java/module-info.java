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
    //

    exports com.competition.JSONDataBase;
    exports com.competition.Main;
}