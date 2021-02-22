package com.competition.bracket;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class SetNames {
    private ArrayList<Team> theTeams = new ArrayList<>();

    public void main(){
        Stage stage = new Stage();
        Label team1 = new Label("Team1");
        Label team2 = new Label("Team2");
        Label team3 = new Label("Team3");
        Label team4 = new Label("Team4");
        Label team5 = new Label("Team5");
        Label team6 = new Label("Team6");
        Label team7 = new Label("Team7");
        Label team8 = new Label("Team8");
        Label team9 = new Label("Team9");
        Label team10 = new Label("Team10");
        Label team11 = new Label("Team11");
        Label team12 = new Label("Team12");
        Label team13 = new Label("Team13");
        Label team14 = new Label("Team14");
        Label team15 = new Label("Team15");
        Label team16 = new Label("Team16");
        TextField team1_txt = new TextField();
        TextField team2_txt = new TextField();
        TextField team3_txt = new TextField();
        TextField team4_txt = new TextField();
        TextField team5_txt = new TextField();
        TextField team6_txt = new TextField();
        TextField team7_txt = new TextField();
        TextField team8_txt = new TextField();
        TextField team9_txt = new TextField();
        TextField team10_txt = new TextField();
        TextField team11_txt = new TextField();
        TextField team12_txt = new TextField();
        TextField team13_txt = new TextField();
        TextField team14_txt = new TextField();
        TextField team15_txt = new TextField();
        TextField team16_txt = new TextField();
        GridPane root = new GridPane();
        root.add(team1,0,1);
        root.add(team2,0,2);
        root.add(team3,0,3);
        root.add(team4,0,4);
        root.add(team5,0,5);
        root.add(team6,0,6);
        root.add(team7,0,7);
        root.add(team8,0,8);
        root.add(team9,0,9);
        root.add(team10,0,10);
        root.add(team11,0,11);
        root.add(team12,0,12);
        root.add(team13,0,13);
        root.add(team14,0,14);
        root.add(team15,0,15);
        root.add(team16,0,16);
        root.add(team1_txt,1,1);
        root.add(team2_txt,1,2);
        root.add(team3_txt,1,3);
        root.add(team4_txt,1,4);
        root.add(team5_txt,1,5);
        root.add(team6_txt,1,6);
        root.add(team7_txt,1,7);
        root.add(team8_txt,1,8);
        root.add(team9_txt,1,9);
        root.add(team10_txt,1,10);
        root.add(team11_txt,1,11);
        root.add(team12_txt,1,12);
        root.add(team13_txt,1,13);
        root.add(team14_txt,1,14);
        root.add(team15_txt,1,15);
        root.add(team16_txt,1,16);
        Button btn  = new Button();
        btn.setText("Submit");
        btn.setOnAction(event -> {
            theTeams = new ArrayList<Team>(
                    Arrays.asList(
                            new Team(team1_txt.getText(), 1),
                            new Team(team2_txt.getText(), 1),
                            new Team(team3_txt.getText(), 1),
                            new Team(team4_txt.getText(), 1),
                            new Team(team5_txt.getText(), 1),
                            new Team(team6_txt.getText(), 1),
                            new Team(team7_txt.getText(), 1),
                            new Team(team8_txt.getText(), 1),
                            new Team(team9_txt.getText(), 1),
                            new Team(team10_txt.getText(), 1),
                            new Team(team11_txt.getText(), 1),
                            new Team(team12_txt.getText(), 1),
                            new Team(team13_txt.getText(), 1),
                            new Team(team14_txt.getText(), 1),
                            new Team(team15_txt.getText(), 1),
                            new Team(team16_txt.getText(), 1)
                    )
            );
            stage.close();
        });
        root.add(btn,0,17);
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }
    //private void
    public ArrayList<Team> getTeams(){
        return  theTeams;
    }

}
