package com.competition.bracket;

import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerData;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.Record;
import com.competition.JSONDataBase.PlayerRanking.PlayerRanking;
import com.competition.menu.Menu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class TwoTeams extends Scene {
    private  Menu controller_ =null;
    /**
     * Constructor that generates a GUI for the two teams in the given teams ArrayList.
     * 
     * @param root - BorderPane that is the root of the GUI
     * @param width - width of GUI
     * @param height - height of GUI
     * @param fill - Color to fill as background
     * @param teams - List of all teams participating in tournament. Length should be two.
     */
    public TwoTeams(Parent root, double width, double height, Paint fill, ArrayList<Team> teams,Menu controller) {
        super(root, width, height, fill);
        controller_=controller;
        // Create GridPane
        GridPane gPane = new GridPane();
        gPane.setGridLinesVisible(false);
//        try{
//
//        }catch (Exception exception){
//
//        }
        /*
         * The actual content of the scene will be stored in borderPane, which is a BorderPane
         * sitting in the root ScrollPane
         */
        Button btn_close = new Button();
        btn_close.setText("Zakoncz");
        btn_close.setOnAction(event -> back_t_menu());

        ScrollPane scrollPane = ((ScrollPane) root);
        BorderPane borderPane = new BorderPane();
        scrollPane.setContent(borderPane);
        borderPane.setCenter(gPane);
        gPane.setAlignment(Pos.CENTER);
        gPane.getStyleClass().add("pane");

        // Drop Shadow effect for the "Tournament Bracket" Title
        DropShadow shad = new DropShadow();
        shad.setOffsetY(3.0f);
        shad.setColor(Color.color(0.4f, 0.4f, 0.4f));

        // Large Title at top of scene
        Text title = new Text("Tournament Bracket");
        title.setId("fancytext");
        title.setEffect(shad);
        borderPane.setTop(title);
        borderPane.setAlignment(title, Pos.CENTER);

        // Round 1 label
        Text round1 = new Text("Runda 1");
        round1.setId("rounds");
        round1.minHeight(25);


        /*
         * 
         * Create the labels for each winner for each game
         * 
         */
        // Zwycięzca Label
        Label champ = new Label();
        champ.setMinHeight(25);
        champ.setText("Zwycięzca:");

        // Runner Up label
        Label runnerUp = new Label();
        runnerUp.setMinHeight(25);
        runnerUp.setText("Odpada w finale:");


        // Labels for Round 1 Competitors
        // See helper method below
        Label label1 = createTeamLabel(teams, 0);
        Label label2 = createTeamLabel(teams, 1);

        // Score Input fields creation.
        // See helper method below
        TextField score1 = createScoreInput("1");
        TextField score2 = createScoreInput("2");

        // Creating Empty Labels for spacing purposes
        Label emptyRow = new Label(" ");

        new Utils().GenerateBorders(gPane, 2, 2);

        /*
         * 
         * Create the action listeners for submit buttons. This handle allows Zwycięzca and second
         * place to be displayed after scores are entered.
         * 
         */
        Button submit1 = new Button();
        submit1.setText("Zatwierdź wynik");
        submit1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Retrieve scores entered by user. If scores are not numbers,
                    // NumberFormatException will be thrown

                    int team1score = Integer.parseInt(score1.getText().trim());
                    int team2score = Integer.parseInt(score2.getText().trim());


                    if (team1score == team2score) {
                        createInvalidInputAlert("Wynik nie może być remisem!");
                        return;
                    }
                    // Do different tasks depending on who won
                    if (team1score < 0 || team2score < 0) { // Ensuring score input is non-negative
                        createInvalidInputAlert("Wynik musi być dodatni!");
                    } else if (team1score > team2score) {
                        add_win(teams.get(0).getTeamName());
                        add_lost(teams.get(1).getTeamName());
                        champ.setText("Zwycięzca: " + teams.get(0).getTeamName());
                        runnerUp.setText("Odpada w finale: " + teams.get(1).getTeamName());
                    } else if (team1score < team2score) {
                        add_lost(teams.get(0).getTeamName());
                        add_win(teams.get(1).getTeamName());
                        champ.setText(("Zwycięzca: " + teams.get(1).getTeamName()));
                        runnerUp.setText("Odpada w finale: " + teams.get(0).getTeamName());
                    } else {
                        createInvalidInputAlert("Niewłaściwa wartość pola!");
                    }
                }
                // Score entered was not a number so the game cannot be scored
                catch (NumberFormatException e) {
                    createInvalidInputAlert("Niewłaściwa wartość pola!");
                }

            }
        });

        /*
         * Add all elements to their respective positions in the grid
         */
        gPane.add(btn_close,0,7);
        gPane.add(round1, 0, 0);
        gPane.add(emptyRow, 0, 1, 4, 1);
        gPane.add(label1, 0, 2);
        gPane.add(score1, 1, 2);

        gPane.add(submit1, 1, 3);

        gPane.add(label2, 0, 4);
        gPane.add(score2, 1, 4);


        gPane.add(champ, 3, 3);
        gPane.add(runnerUp, 3, 6);



    }

    /*
     * Creates and shows an alert to inform user that their input for scores was invalid
     *
     * @param message Text to be displayed under the header
     */
    private void createInvalidInputAlert(String message) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Niewłaściwa wartość pola");
        a.setContentText(message);
        a.show();
    }

    /*
     * Used to create labels to assign teams to locations in Bracket
     * 
     * @param teams ArrayList of teams
     * 
     * @param teamIndex The index of the team in teams list
     * 
     * @returns winner The created Label
     */
    private Label createTeamLabel(ArrayList<Team> teams, int teamIndex) {
        Label label = new Label();
        label.setPrefHeight(15);
        label.setText(teams.get(teamIndex).getTeamName() + ": ");
        return label;
    }


    /*
     * Used to create Text fields for score input. Sets size, Prompt text, etc.
     * 
     * @param scoreNumber Numbering of text fields to be displayed in UI
     * 
     * @returns input The created TextField
     */
    private TextField createScoreInput(String scoreNumber) {
        TextField input = new TextField();
        input.setMaxHeight(20);
        input.setMaxWidth(200);
        input.setPromptText("Punkty " + scoreNumber);
        input.setFocusTraversable(false);
        return input;
    }

    private void add_win(String name){
        PlayerRanking ranking = new PlayerRanking();
        try {
            PlayerData to_update = new PlayerData();
            ranking.loadRankingFromFile();
            List<PlayerRanking.PlayerDataWithTeam> PlayerTableData = ranking.generatePlayerDataWithTeamInfo();
            for (var player : PlayerTableData) {
                if (player.team!=null) {
                    if (player.team.getName().getTeamName().equals(name)) {
                        to_update = player.playerData;
                        Record new_record = new Record.Builder().win(to_update.getRecord().getWin() + 1).lose(to_update.getRecord().getLose()).build();
                        to_update.setRecord(new_record);
                        ranking.updatePlayer(to_update, player.team);
                    }
                }
            }
        }catch (Exception ex){}
        try{
            ranking.saveRankingToFile();
        }catch (Exception exception){}
    }
    private void add_lost(String name) {
        PlayerRanking ranking = new PlayerRanking();
        try {
            PlayerData to_update = new PlayerData();
            ranking.loadRankingFromFile();
            List<PlayerRanking.PlayerDataWithTeam> PlayerTableData = ranking.generatePlayerDataWithTeamInfo();
            for (var player : PlayerTableData) {
                if (player.team!=null) {
                    String idk = player.team.getName().getTeamName();
                    if (idk.equals(name)) {
                        to_update = player.playerData;
                        Record new_record = new Record.Builder().win(to_update.getRecord().getWin()).lose(to_update.getRecord().getLose() + 1).build();
                        to_update.setRecord(new_record);
                        ranking.updatePlayer(to_update, player.team);
                    }
                }
            }

        }catch (Exception ex){}
        try{
            ranking.saveRankingToFile();
        }catch (Exception exception){}
    }
    public void back_t_menu(){
        controller_.back_to_menu();

    }

}
