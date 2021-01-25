package com.competition.bracket;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class EightTeams extends Scene {

    /**
     * Constructor that generates a GUI for the eight teams in the given teams ArrayList.
     * 
     * @param root - BorderPane that is the root of the GUI
     * @param width - width of GUI
     * @param height - height of GUI
     * @param fill - Color to fill as background
     * @param teams - List of all teams participating in tournament. Length should be eight.
     */
    public EightTeams(Parent root, double width, double height, Paint fill, ArrayList<Team> teams) {
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
        borderPane.getStyleClass().add("border-pane");
        gPane.setAlignment(Pos.CENTER);
        gPane.getStyleClass().add("pane");

        // Add shadows
        DropShadow shad = new DropShadow();
        shad.setOffsetY(3.0f);
        shad.setColor(Color.color(0.4f, 0.4f, 0.4f));

        // Add title and instructions to borderPane
        Text title = new Text("Tournament Bracket");
        title.setId("fancytext");
        title.setEffect(shad);
        borderPane.setTop(title);
        borderPane.setAlignment(title, Pos.CENTER);

        // Instructions, text to show user how to use the bracket
//        Label info = new Label();
//        info.setText("INSTRUCTIONS:\n-For each game: Enter each team's score then \n   click"
//                        + " submit button between the two teams.\n-Scores must be positive"
//                        + " number and games \n   can not result in a tie\n-After " + ""
//                        + "completing all games for a " + ""
//                        + "round, move\n   on to next round and repeat process to " + ""
//                        + "enter teams' scores.\n After submitting the " + ""
//                        + "scores for the championship " + "" + "game the \n   "
//                        + "top three contenders will be displayed!");
//        info.setFont(Font.font("Ariel", 15));
//        borderPane.setRight((info));
//        borderPane.setAlignment(info, Pos.CENTER);

        // Column headers for rounds
        // See helper method below
        Text round1 = createRoundHeader("Runda 1");
        Text round2 = createRoundHeader("Runda 2");
        Text round3 = createRoundHeader("Runda 3");

        // Labels for Round 1 Competitors
        // See helper method below
        Label label1 = createTeamLabel(teams, 0);
        Label label2 = createTeamLabel(teams, 7);
        Label label3 = createTeamLabel(teams, 3);
        Label label4 = createTeamLabel(teams, 4);
        Label label5 = createTeamLabel(teams, 1);
        Label label6 = createTeamLabel(teams, 6);
        Label label7 = createTeamLabel(teams, 2);
        Label label8 = createTeamLabel(teams, 5);



        // Labels for Round 2 Competitors
        // See helper method below
        Label winner1 = createWinnerLabel("1");
        Label winner2 = createWinnerLabel("2");
        Label winner3 = createWinnerLabel("3");
        Label winner4 = createWinnerLabel("4");

        // Labels for Round 3 Competitors
        // See helper method below
        Label winner5 = createWinnerLabel("5");
        Label winner6 = createWinnerLabel("6");



        Label champ = new Label();
        champ.setMinHeight(25);
        champ.setText("Zwycięzca:");

        Label runnerUp = new Label();
        runnerUp.setMinHeight(25);
        runnerUp.setText("Odpada w finale:");

        Label thirdPlace = new Label();
        thirdPlace.setMinHeight(25);
        thirdPlace.setText("Trzecie miejsce: ");

        // Round 1 Score Input fields creation.
        // See helper method below
        TextField round1Score1 = createScoreInput("1");
        TextField round1Score2 = createScoreInput("2");
        TextField round1Score3 = createScoreInput("3");
        TextField round1Score4 = createScoreInput("4");
        TextField round1Score5 = createScoreInput("5");
        TextField round1Score6 = createScoreInput("6");
        TextField round1Score7 = createScoreInput("7");
        TextField round1Score8 = createScoreInput("8");

        // Enabling the score inputs for round 1 since, by default, the input labels are disabled
        round1Score1.setDisable(false);
        round1Score2.setDisable(false);
        round1Score3.setDisable(false);
        round1Score4.setDisable(false);
        round1Score5.setDisable(false);
        round1Score6.setDisable(false);
        round1Score7.setDisable(false);
        round1Score8.setDisable(false);

        // Round 2 Score Input fields creation.
        // See helper method below
        TextField round2Score1 = createScoreInput("1");
        TextField round2Score2 = createScoreInput("2");
        TextField round2Score3 = createScoreInput("3");
        TextField round2Score4 = createScoreInput("4");


        // Round 3 Score Input fields creation.
        // See helper method below
        TextField round3Score1 = createScoreInput("1");
        TextField round3Score2 = createScoreInput("2");

        // Creating Round 1 Submit Buttons
        // See helper method below
        Button submit1 = createSubmitButton(teams, round2Score1, winner1, "1", winner5, "5",
                        round1Score1, round1Score2, 0, 7, champ, runnerUp);
        Button submit2 = createSubmitButton(teams, round2Score2, winner2, "2", winner5, "5",
                        round1Score3, round1Score4, 3, 4, champ, runnerUp);
        Button submit3 = createSubmitButton(teams, round2Score3, winner3, "3", winner6, "6",
                        round1Score5, round1Score6, 1, 6, champ, runnerUp);
        Button submit4 = createSubmitButton(teams, round2Score4, winner4, "4", winner6, "6",
                        round1Score7, round1Score8, 2, 5, champ, runnerUp);
        gPane.setHgap(10);

        // Creating Round 2 Submit Buttons
        // See helper method below
        Button submit5 = createRnd2SubmitButton(gameOneLoser, winner1, winner2, winner5,
                        round3Score1, "5", round2Score1, round2Score2, champ, runnerUp);
        Button submit6 = createRnd2SubmitButton(gameTwoLoser, winner3, winner4, winner6,
                        round3Score2, "6", round2Score3, round2Score4, champ, runnerUp);

        // Create Championship Game Submit Button
        Button submit7 = new Button();
        submit7.setText("Zatwierdź wynik");
        submit7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    champ.setText("Zwycięzca: ");
                    runnerUp.setText("Odpada w finale: ");

                    int team1Score = Integer.parseInt(round3Score1.getText().trim());
                    int team2Score = Integer.parseInt(round3Score2.getText().trim());

                    if (team1Score == team2Score) {
                        createInvalidInputAlert("Wynik nie może być remisem!");
                        return;
                    }
                    if (team1Score < 0 || team2Score < 0) { // Ensuring score input is non-negative
                        createInvalidInputAlert("Wartości muszą być dodatnie!");
                    } else if (team1Score > team2Score) {
                        champ.setText("Zwycięzca: " + winner5.getText());
                        runnerUp.setText("Odpada w finale: " + winner6.getText());
                    } else if (team1Score < team2Score) {
                        champ.setText("Zwycięzca: " + winner6.getText());
                        runnerUp.setText("Odpada w finale: " + winner5.getText());
                    } else {
                        createInvalidInputAlert("Niewłaściwa wartość pola!");
                    }

                    // Determining 3rd place of tournament based of scores of the losers of semi
                    // finals
                    if (gameOneLoser.getTeamScore() > gameTwoLoser.getTeamScore()) {
                        System.out.println(gameOneLoser.getTeamName());
                        thirdPlace.setText("Trzeci: " + gameOneLoser.getTeamName());
                    }

                    else {
                        System.out.println(gameTwoLoser.getTeamName());
                        thirdPlace.setText("Trzeci: " + gameTwoLoser.getTeamName());
                    }

                } catch (NumberFormatException e) {
                    createInvalidInputAlert("Niewłaściwa wartość pola!");
                }

            }
        });

        // Empty labels for spacing purposes
        Label emptyRow1 = new Label(" ");
        Label emptyCol1 = new Label(" ");
        Label emptyCol2 = new Label(" ");
        Label emptyCol3 = new Label(" ");
        emptyCol1.setMinWidth(100);
        emptyCol2.setMinWidth(100);
        emptyCol3.setMinWidth(100);


        // Adding column headers for each round to grid pane
        gPane.add(round1, 0, 0);
        gPane.add(round2, 3, 0);
        gPane.add(round3, 6, 0);

        gPane.add(emptyRow1, 0, 1, 9, 1);

        // Adding round one team labels to grid pane
        gPane.add(label1, 0, 2);
        gPane.add(label2, 0, 4);
        gPane.add(label3, 0, 6);
        gPane.add(label4, 0, 8);
        gPane.add(label5, 0, 10);
        gPane.add(label6, 0, 12);
        gPane.add(label7, 0, 14);
        gPane.add(label8, 0, 16);

        // Adding round one input fields to grid pane
        gPane.add(round1Score1, 1, 2);
        gPane.add(round1Score2, 1, 4);
        gPane.add(round1Score3, 1, 6);
        gPane.add(round1Score4, 1, 8);
        gPane.add(round1Score5, 1, 10);
        gPane.add(round1Score6, 1, 12);
        gPane.add(round1Score7, 1, 14);
        gPane.add(round1Score8, 1, 16);


        // Adding round one submit buttons to grid pane
        gPane.add(submit1, 1, 3, 1, 1);
        gPane.add(submit2, 1, 7, 1, 1);
        gPane.add(submit3, 1, 11, 1, 1);
        gPane.add(submit4, 1, 15, 1, 1);

        for(int i = 0; i < 7; i++) {
            if(i < 4) {
                new Utils().GenerateBorders(gPane, 2, 2 + i * 4);
            }
            else if (i < 6) {
                new Utils().GenerateBorders(gPane, 5, i == 4 ? 3 : 11);
            } else {
                new Utils().GenerateBorders(gPane, 8, 5);
            }
        }

        gPane.add(emptyCol1, 2, 0, 1, 16);


        // Adding round 2 team labels to grid pane
        gPane.add(winner1, 3, 3);
        gPane.add(winner2, 3, 7);
        gPane.add(winner3, 3, 11);
        gPane.add(winner4, 3, 15);


        // Adding round 2 input fields to grid pane
        gPane.add(round2Score1, 4, 3);
        gPane.add(round2Score2, 4, 7);
        gPane.add(round2Score3, 4, 11);
        gPane.add(round2Score4, 4, 15);


        // Adding round 2 submit buttons to grid pane
        gPane.add(submit5, 4, 5, 1, 1);
        gPane.add(submit6, 4, 13, 1, 1);

        gPane.add(emptyCol2, 5, 0, 1, 16);

        // Adding round 3 team labels to grid pane
        gPane.add(winner5, 6, 5);
        gPane.add(winner6, 6, 13);

        // Adding round 3 input fields to grid pane
        gPane.add(round3Score1, 7, 5);
        gPane.add(round3Score2, 7, 13);

        // Adding round 3 submit button to grid pane
        gPane.add(submit7, 7, 9, 1, 1);

        gPane.add(emptyCol3, 8, 0, 1, 16);

        // Adding labels to display champion, runner up, and third place teams at end
        gPane.add(champ, 9, 9);
        gPane.add(runnerUp, 9, 15);
        gPane.add(thirdPlace, 9, 16);
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
    private Button createSubmitButton(ArrayList<Team> teams, TextField nextTextField, Label winner,
                    String winnerNum, Label nextWinner, String nextWinnerNum, TextField score1,
                    TextField score2, int team1Index, int team2Index, Label champion,
                    Label runnerUp) {
        Button submit = new Button();
        submit.setText("Zatwierdź wynik");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Resetting labels of future games in these teams' subsequent path
                    champion.setText("Zwycięzca: ");
                    runnerUp.setText("Odpada w finale: ");
                    winner.setText("Zwycięzca " + winnerNum + ": ");
                    nextWinner.setText("Zwycięzca " + nextWinnerNum + ": ");

                    // Getting score results from text fields
                    int team1Score = Integer.parseInt(score1.getText().trim());
                    int team2Score = Integer.parseInt(score2.getText().trim());

                    if (team1Score == team2Score) {
                        createInvalidInputAlert("Wynik nie może być remisem!");
                        return;
                    }
                    if (team1Score < 0 || team2Score < 0) { // Ensuring score input is non-negative
                        createInvalidInputAlert("Wartości muszą być dodatnie!");
                    } else if (team1Score > team2Score) {
                        winner.setText(teams.get(team1Index).getTeamName()); // Updating winner
                        nextTextField.setDisable(false);
                    } else if (team1Score < team2Score) {
                        winner.setText(teams.get(team2Index).getTeamName());
                        nextTextField.setDisable(false);
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

    /**
     * This method is a helper method to create the submit buttons for each of the games in round 3.
     * It overrides the handle method of Button in order to correctly handle the user provided
     * scores. It compares the values entered in the respective score fields and updates the correct
     * team labels accordingly.
     * 
     * It begins by resetting the labels of any future games in the current teams path. This is to
     * reset labels if user goes back and changes a games outcome after already moving farther
     * forward in the bracket.
     * 
     * @param loser To store loser of game for runner up calculations later
     * @param contestant1 The first competitor
     * @param contestant2 The second competitor
     * @param winner The winner of this game
     * @param winnerNum The number of the winner label. To update winner label
     * @param score1 Score of contestant1 in this game
     * @param score2 Score of contestant2 in this game
     * @param champ The label for champion of the tournament
     * @param runnerUp The label runner up in the tournament
     * @return submit The created Button
     */
    private Button createRnd2SubmitButton(Team loser, Label contestant1, Label contestant2,
                    Label winner, TextField nextTextField, String winnerNum, TextField score1,
                    TextField score2, Label champ, Label runnerUp) {
        Button submit = new Button();
        submit.setText("Zatwierdź wynik");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Resetting labels of future games in these teams' subsequent path
                    champ.setText("Zwycięzca: ");
                    runnerUp.setText("Odpada w finale: ");
                    winner.setText("Zwycięzca " + winnerNum + ": ");

                    // Getting score results from text fields
                    int team1Score = Integer.parseInt(score1.getText().trim());
                    int team2Score = Integer.parseInt(score2.getText().trim());

                    if (team1Score == team2Score) {
                        createInvalidInputAlert("Wynik nie może być remisem!");
                        return;
                    }

                    if (team1Score < 0 || team2Score < 0) { // Ensuring score input is non-negative
                        createInvalidInputAlert("Wartości muszą być dodatnie!");
                    } else if (team1Score > team2Score) {
                        winner.setText(contestant1.getText()); // Updating winner label to winning
                                                               // team
                        nextTextField.setDisable(false);
                        loser.setTeamName(contestant2.getText());
                        loser.setTeamScore(team2Score);

                    } else if (team1Score < team2Score) {
                        winner.setText(contestant2.getText());
                        nextTextField.setDisable(false);
                        loser.setTeamName(contestant1.getText());
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

    /*
     * Creates and shows an alert to inform user that their input for scores was invalid
     * 
     * @param message Text to be displayed under the header
     */
    private void createInvalidInputAlert(String message) {
        Alert a = new Alert(AlertType.ERROR);
        a.setHeaderText("Niewłaściwa wartość pola");
        a.setContentText(message);
        a.show();
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
     * Used to created the Underlined round headers above each round
     * 
     * @param roundName Name of round
     * 
     * @returns round The created Text
     */
    private Text createRoundHeader(String roundName) {
        Text round = new Text(roundName);
        round.setId("runda");
        round.maxHeight(15);
        return round;
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
        input.setPromptText("Wynik " + scoreNumber);
        input.setFocusTraversable(false);
        input.setDisable(true);
        return input;
    }
}
