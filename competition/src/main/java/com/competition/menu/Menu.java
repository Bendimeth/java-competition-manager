package com.competition.menu;

import com.competition.MainPage.MainPageController;
import com.competition.bracket.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu extends Application{
    private Stage stage;
    private MainPageController controller1;
    private ArrayList<Team> mockTeams = new ArrayList<Team>(
            Arrays.asList(
                    new Team("Team1", 1),
                    new Team("Team2", 1),
                    new Team("Team3", 1),
                    new Team("Team4", 1),
                    new Team("Team5", 1),
                    new Team("Team6", 1),
                    new Team("Team7", 1),
                    new Team("Team8", 1),
                    new Team("Team9", 1),
                    new Team("Team10", 1),
                    new Team("Team11", 1),
                    new Team("Team12", 1),
                    new Team("Team13", 1),
                    new Team("Team14", 1),
                    new Team("Team15", 1),
                    new Team("Team16", 1)
            )
    );
    @Override
    public void start(Stage primaryStage) throws Exception {
        //controller1=controller;
        stage = primaryStage;
        Scene scene = Menu();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public  void start_menu(Stage stage,MainPageController controller){
        try {
            start(stage);
            controller1 = controller;
        }catch (Exception exception){}
    }
    public Menu getStage(){
        return this;
    }

    public void  back_to_menu() {
        try {
            //stage.close();
            //var main=new MainPageController();
            controller1.redo();
            //main.start_main_page(null);
        }catch (Exception ex){}

    }

    public Scene Menu(){
        GridPane root = new GridPane();
        root.setGridLinesVisible(false);
        root.setAlignment(Pos.CENTER);
        root.setPrefHeight(900);
        root.setPrefWidth(1600);
        stage.setMaximized(true);
        Button createTwoBracket = new Button("2 zawodników");
        createTwoBracket.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                stage.setScene(CreateTwoBracket());
            }
        });
        Button createFourBracket = new Button("4 zawodników");
        createFourBracket.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                stage.setScene(CreateFourBracket());
            }
        });
        Button createEightBracket = new Button("8 zawodników");
        createEightBracket.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                stage.setScene(CreateEightBracket());
            }
        });
        Button createSixteentBracket = new Button("16 zawodników");
        createSixteentBracket.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                stage.setScene(CreateSixteenBracket());
            }
        });
        // filler
        for(int i = 0; i < 5; i++) {
            root.add(new Label(" "), 0, i * 2);
        }
        root.add(createTwoBracket, 0, 1);
        root.add(createFourBracket,0,3);
        root.add(createEightBracket, 0, 5);
        root.add(createSixteentBracket,0,7);
        Button start_btn = new Button();
        start_btn.setText("Cancel");
        start_btn.setOnAction(event -> back_to_menu());
        root.add(start_btn,0,9);
        return new Scene(root);
    }
    protected Scene CreateTwoBracket() {
        setNames();
        ScrollPane scrollPane = new ScrollPane();
        SetBracketStyles(scrollPane);
        Scene newScene= new TwoTeams(scrollPane, 1600, 900, Color.DARKGRAY, new ArrayList(mockTeams.subList(0, 2)),this);
        return newScene;
    }
    protected Scene CreateFourBracket() {
        setNames();
        ScrollPane scrollPane = new ScrollPane();
        SetBracketStyles(scrollPane);
        Scene newScene = new FourTeams(scrollPane, 1600, 900, Color.DARKGRAY, new ArrayList(mockTeams.subList(0, 4)),this);
        return newScene;
    }
    protected Scene CreateEightBracket() {
        setNames();
        ScrollPane scrollPane = new ScrollPane();
        SetBracketStyles(scrollPane);
        Scene newScene = new EightTeams(scrollPane, 1600, 900, Color.DARKGRAY, new ArrayList(mockTeams.subList(0, 8)),this);
        return newScene;
    }
    protected Scene CreateSixteenBracket() {
        setNames();
        ScrollPane scrollPane = new ScrollPane();
        SetBracketStyles(scrollPane);
        Scene newScene = new SixteenTeams(scrollPane, 1600, 900, Color.DARKGRAY, mockTeams,this);
        return newScene;
    }
    public void SetBracketStyles(ScrollPane root) {
        File f = new File("competition\\src\\main\\java\\com\\competition\\bracket\\style.css");
        root.getStylesheets().clear();
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void setNames(){
        SetNames setNames = new SetNames();
        setNames.main();
        mockTeams = setNames.getTeams();
    }

}
