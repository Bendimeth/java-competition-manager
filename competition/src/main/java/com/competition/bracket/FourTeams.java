package com.competition.bracket;

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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class FourTeams extends Scene {

    /**
     * Constructor that generates a GUI for the four teams in the given teams ArrayList.
     * 
     * @param root - BorderPane that is the root of the GUI
     * @param width - width of GUI
     * @param height - height of GUI
     * @param fill - Color to fill as background
     * @param teams - List of all teams participating in tournament. Length should be four.
     */
    public FourTeams(Parent root, double width, double height, Paint fill, ArrayList<Team> teams) {
        super(root, width, height, fill);

     // References to losers of semi-finals games in order to report 3rd place team at end
        Team gameOneLoser = new Team();
        Team gameTwoLoser = new Team();

        // Create GridPane
        GridPane gPane = new GridPane();
        gPane.setGridLinesVisible(false);

        /*
         * The actual content of the scene will be stored in borderPane, which is a BorderPane
         * sitting in the root ScrollPane
         */
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

        /// Large Title at top of scene
        Text title = new Text("Tournament Bracket");
        title.setId("fancytext");
        title.setEffect(shad);
        borderPane.setTop(title);
        borderPane.setAlignment(title, Pos.CENTER);

        // Column headers for rounds
        // See helper method below
        Text round1 = createRoundHeader("Round 1");
        Text round2 = createRoundHeader("Round 2");


        // Labels for Round 1 Competitors
        // See helper method below
        Label label1 = createTeamLabel(teams, 0);
        Label label2 = createTeamLabel(teams, 3);
        Label label3 = createTeamLabel(teams, 1);
        Label label4 = createTeamLabel(teams, 2);



        // Labels for Round 2 Competitors
        // See helper method below
        Label winner1 = createWinnerLabel("1");
        Label winner2 = createWinnerLabel("2");

     // Champion Label to display Champion of tournament
        Label champ = new Label();
        champ.setMinHeight(25);
        champ.setText("Zwycięzca:");

     // runnerUp Label to display runnerUp of tournament
        Label runnerUp = new Label();
        runnerUp.setMinHeight(25);
        runnerUp.setText("Odpada w finale");

     // Third place Label to display runnerUp of tournament
        Label thirdPlace = new Label();
        thirdPlace.setMinHeight(25);
        thirdPlace.setText("Trzecie miejsce ");

        for(int i = 0; i < 3; i++) {
            if (i < 2) {
                new Utils().GenerateBorders(gPane, 2, 2 + i * 4);
            } else {
                new Utils().GenerateBorders(gPane, 5, 3);
            }
        }

        // Round 1 Score Input fields creation.
        // See helper method below
        TextField round1Score1 = createScoreInput("1");
        TextField round1Score2 = createScoreInput("2");
        TextField round1Score3 = createScoreInput("3");
        TextField round1Score4 = createScoreInput("4");
        
     // Enabling the score inputs for round 1 since, by default, the input labels are disabled
        round1Score1.setDisable(false);
        round1Score2.setDisable(false);
        round1Score3.setDisable(false);
        round1Score4.setDisable(false);

        // Round 2 Score Input fields creation.
        // See helper method below
        TextField round2Score1 = createScoreInput("1");
        TextField round2Score2 = createScoreInput("2");

        // Creating Round 1 Submit Buttons
        // See helper method below
        Button submit1 = createSubmitButton(teams, gameOneLoser, round2Score1, winner1, "1",
                        round1Score1, round1Score2, 0, 3, champ, runnerUp);
        Button submit2 = createSubmitButton(teams, gameTwoLoser, round2Score2, winner2, "2",
                        round1Score3, round1Score4, 1, 2, champ, runnerUp);

        // Create Championship Game Submit Button
        Button submit3 = new Button();
        submit3.setText("Zatwierdź wynik");
        submit3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    champ.setText("Zwycięzca: ");
                    runnerUp.setText("Odpada w finale ");

                    int team1score = Integer.parseInt(round2Score1.getText().trim());
                    int team2score = Integer.parseInt(round2Score2.getText().trim());

                    if (team1score == team2score) {
                        createInvalidInputAlert("Wynik nie może być remisem!");
                        return;
                    }

                    if (team1score < 0 || team2score < 0) { // Ensuring score input is non-negative
                        createInvalidInputAlert("Wynik musi być dodatni!");
                    } else if (team1score > team2score) {
                        champ.setText("Zwycięzca: " + winner1.getText());
                        runnerUp.setText("Odpada w finale " + winner2.getText());
                    } else if (team1score < team2score) {
                        champ.setText("Zwycięzca: " + winner2.getText());
                        runnerUp.setText("Odpada w finale " + winner1.getText());
                    } else {
                        createInvalidInputAlert("Niewłaściwa wartość pola!");
                    }

                    if (gameOneLoser.getTeamScore() > gameTwoLoser.getTeamScore())
                        thirdPlace.setText("Trzecie miejsce " + gameOneLoser.getTeamName());
                    else
                        thirdPlace.setText("Trzecie miejsce " + gameTwoLoser.getTeamName());

                } catch (NumberFormatException e) {
                    createInvalidInputAlert("Niewłaściwa wartość pola!");
                }

            }
        });
        
        
      //Adding column headers for each round to grid pane
        gPane.add(round1, 0, 0);
        gPane.add(round2, 3, 0);

        gPane.add(new Label(" "), 0, 1, 7, 1);

        //Adding round one team labels to grid pane
        gPane.add(label1, 0, 2);
        gPane.add(label2, 0, 4);
        gPane.add(label3, 0, 6);
        gPane.add(label4, 0, 8);

     // Adding round 1 submit buttons to grid pane
        gPane.add(submit1, 1, 3, 1, 1);
        gPane.add(submit2, 1, 7, 1, 1);
        
     // Adding championship submit button to grid pane
        gPane.add(submit3, 4, 5, 1, 1);
        gPane.add(new Label(" "), 0, 5,1,1);

        // Adding round one input fields to grid pane
        gPane.add(round1Score1, 1, 2);
        gPane.add(round1Score2, 1, 4);
        gPane.add(round1Score3, 1, 6);
        gPane.add(round1Score4, 1, 8);

     // Adding round 2 (championship) team labels to grid pane
        gPane.add(winner1, 3, 3);
        gPane.add(winner2, 3, 7);

     // Adding round 2 (championship) input fields to grid pane
        gPane.add(round2Score1, 4, 3);
        gPane.add(round2Score2, 4, 7);

        // Adding labels to display champion, runner up, and third place teams at end
        gPane.add(champ, 7, 5);
        gPane.add(runnerUp, 7, 10);
        gPane.add(thirdPlace, 7, 11);
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
     * Used to created the Underlined round headers above each round
     * 
     * @param roundName Name of round
     * 
     * @returns round The created Text
     */
    private Text createRoundHeader(String roundName) {
        Text round = new Text(roundName);
        round.setId("rounds");
        round.maxHeight(15);
        return round;
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
     * Used to create labels for game that don't yet have a contestant assigned to them.
     * 
     * @param winnerNumber Numbering of winner labels to be displayed in UI
     * 
     * @returns winner The created Label
     */
    private Label createWinnerLabel(String winnerNumber) {
        Label winner = new Label();
        winner.setPrefHeight(15);
        winner.setText("Zwycięzca " + winnerNumber + ": ");
        return winner;
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
        input.setPrefHeight(15);
        input.setMaxWidth(200);
        input.setPromptText("Punkty " + scoreNumber);
        input.setFocusTraversable(false);
        input.setDisable(true);
        return input;
    }

    /**
     * Used to create the submit buttons for each of the games in Round 1. Overrides handle method
     * of Button in order to correctly handle the user provided scores. Compares the values entered
     * in score fields and updates the correct team label for next round.
     * 
     * Begins by resetting the labels of any future games in the current teams path. Added so that
     * it resets future game labels if user changes outcome of previous games after already moving
     * further in bracket.
     * 
     * @param teams ArrayList of all teams
     * @param nextTextField Text field associated with the winner of this game, used to enable field
     *        when scores are submitted.
     * @param winner The winner of this game
     * @param winnerNum The number of the winner label. To reset winner label
     * @param nextWinner The winner of next rounds game. Used to reset label
     * @param nextwinnerNum Number of the winner of next rounds game. Used to reset label
     * @param futureWinner The winner of the game 2 rounds from now. Used to reset label
     * @param futureWinnerNum Number of the winner of game 2 rounds from now. Used to reset label
     * @param score1 Score of first team in this game
     * @param score2 Score of second team in this game
     * @param team1Index Index of first team in teams list
     * @param team2Index Index of second team in teams list
     * @param champion The label for champion of the tournament
     * @param runnerUp The label runner up in the tournament
     * @return submit The created Button
     */
    private Button createSubmitButton(ArrayList<Team> teams, Team loser, TextField nextTextField,
                    Label winner, String winnerNum, TextField score1, TextField score2,
                    int team1Index, int team2Index, Label champion, Label runnerUp) {
        Button submit = new Button();
        submit.setText("Zatwierdź wynik");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Resetting labels of future games in these teams' subsequent path
                    champion.setText("Zwycięzca: ");
                    runnerUp.setText("Odpada w finale ");
                    winner.setText("Zwycięzca " + winnerNum + ": ");


                    // Getting score results from text fields
                    int team1Score = Integer.parseInt(score1.getText().trim());
                    int team2Score = Integer.parseInt(score2.getText().trim());

                    if (team1Score == team2Score) {
                        createInvalidInputAlert("Wynik nie może być remisem!");
                        return;
                    }

                    if (team1Score < 0 || team2Score < 0) { // Ensuring score input is non-negative
                        createInvalidInputAlert("Wynik musi być dodatni!");
                    } else if (team1Score < 0 || team2Score < 0) { // Ensuring score input is non-negative
                        System.out.println("Invalid Score: Scores must be positive");
                    } else if (team1Score > team2Score) {
                        winner.setText(teams.get(team1Index).getTeamName()); // Updating winner
                        nextTextField.setDisable(false);
                        loser.setTeamName(teams.get(team2Index).getTeamName()); // Updating loser
                                                                                // reference
                        loser.setTeamScore(team2Score);
                    } else if (team1Score < team2Score) {
                        winner.setText(teams.get(team2Index).getTeamName());
                        nextTextField.setDisable(false);
                        loser.setTeamName(teams.get(team1Index).getTeamName()); // Updating loser
                                                                                // reference
                        loser.setTeamScore(team1Score);
                    } else {
                        createInvalidInputAlert("Niewłaściwa wartość pola!");
                    }

                } catch (NumberFormatException e) {
                    createInvalidInputAlert("Niewłaściwa wartość pola!");
                }

            }
        });
        return submit;
    }
}
