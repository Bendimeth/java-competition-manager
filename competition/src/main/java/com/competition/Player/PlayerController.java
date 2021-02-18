package com.competition.Player;


import com.competition.JSONDataBase.PlayerRanking.PlayerData.Name;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerData;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.Record;
import com.competition.JSONDataBase.PlayerRanking.PlayerRanking;
import com.competition.JSONDataBase.PlayerRanking.Team.Team;
import com.competition.MainPage.MainPageController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlayerController implements Initializable  {

    private final Stage thisStage;
    private final MainPageController controller1;
    private Stage addPlayerStg;
    private Stage editPlayerStg;
    private ArrayList<PlayerRanking.PlayerDataWithTeam> listOfPlayers = new ArrayList<>();
    private PlayerRanking ranking = new PlayerRanking();
    private ObservableList<PlayerRanking.PlayerDataWithTeam> PlayerTableData =null;
    private PlayerRanking.PlayerDataWithTeam lastSelected=null;
    @FXML private TableView TablePlayers;
    @FXML private TableView TableTeams;
    @FXML private Label LabelTeams;
    @FXML private Label LabelPlayers;
    @FXML private Button editPlayerBtn;
    @FXML private Button addPlayerBtn;
    @FXML private Button RefreshBtn;
    @FXML private Button deletePlayerBtn;
    @FXML private Button EditBtn;
    @FXML private Button AddBtn;
    @FXML private TextField addName;
    @FXML private TextField addMidName;
    @FXML private TextField addSurname;
    @FXML private TextField addTeam;
    @FXML private TextField editName;
    @FXML private TextField editMidName;
    @FXML private TextField editSurname;
    @FXML private TextField editTeam;
    @FXML private ListView listView;



    public PlayerController(MainPageController controller1)throws IOException {
        this.controller1 = controller1;
        thisStage = new Stage();
        URL url = new File("competition/src/main/java/com/competition/Player/PlayerIndex.fxml").toURI().toURL();
        FXMLLoader fxmlLoader= new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setController(this);
        AnchorPane root=fxmlLoader.load();
        controller1.setPane(root);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ObservableList<List<PlayerData>> data = FXCollections.observableArrayList();
        if(editPlayerBtn!=null)editPlayerBtn.setOnAction(event -> btnclick_editPlayer());
        if(addPlayerBtn!=null)addPlayerBtn.setOnAction(event -> btnclick_addPlayer());
        if(deletePlayerBtn!=null)deletePlayerBtn.setOnAction(event -> btnclick_deletePlayer());
        if(EditBtn!=null)EditBtn.setOnAction(event -> save_edit());
        if(AddBtn!=null)AddBtn.setOnAction(event -> save_add());
        if(RefreshBtn!=null)RefreshBtn.setOnAction(event -> refresh_data());
        try {
            TableColumn colName = new TableColumn<>("Name");
            TableColumn colMiddleName = new TableColumn<>("Middle Name");
            TableColumn colSurname = new TableColumn<>("Surname");
            TableColumn colWin = new TableColumn<>("Number of wins");
            TableColumn colLose = new TableColumn<>("Number of losses");
            TableColumn colTeam = new TableColumn<>("Team");
            colName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PlayerRanking.PlayerDataWithTeam, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<PlayerRanking.PlayerDataWithTeam, String> c) {
                    return new SimpleStringProperty(c.getValue().playerData.getName().getFirstName());
                }
            });
            colMiddleName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PlayerRanking.PlayerDataWithTeam, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<PlayerRanking.PlayerDataWithTeam, String> c) {
                    return new SimpleStringProperty(c.getValue().playerData.getName().getMiddleName());
                }
            });
            colSurname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PlayerRanking.PlayerDataWithTeam, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<PlayerRanking.PlayerDataWithTeam, String> c) {
                    return new SimpleStringProperty(c.getValue().playerData.getName().getSurname());
                }
            });
            colWin.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PlayerRanking.PlayerDataWithTeam, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<PlayerRanking.PlayerDataWithTeam, Integer> c) {
                    return new SimpleIntegerProperty(c.getValue().playerData.getRecord().getWin()).asObject();
                }
            });
            colLose.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PlayerRanking.PlayerDataWithTeam, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<PlayerRanking.PlayerDataWithTeam, Integer> c) {
                    return new SimpleIntegerProperty(c.getValue().playerData.getRecord().getLose()).asObject();
                }
            });
            colTeam.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PlayerRanking.PlayerDataWithTeam, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<PlayerRanking.PlayerDataWithTeam, String> c) {
                    return new SimpleStringProperty(c.getValue().team==null?"":c.getValue().team.getName().getTeamName());
                }
            });
            TablePlayers.getSelectionModel().setCellSelectionEnabled(true);
            TablePlayers.getColumns().clear();
            TablePlayers.getColumns().addAll(colName,colMiddleName,colSurname,colWin,colLose,colTeam);
            refresh_data();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    private void btnclick_editPlayer(){
        editPlayerStg = new Stage();
        lastSelected = (PlayerRanking.PlayerDataWithTeam) TablePlayers.getSelectionModel().getSelectedItem();
        if(lastSelected!=null){
            try{
                URL url = new File("competition/src/main/java/com/competition/Player/PlayerEdit.fxml").toURI().toURL();
                FXMLLoader fxmlLoader= new FXMLLoader();
                fxmlLoader.setLocation(url);
                fxmlLoader.setController(this);
                AnchorPane root=fxmlLoader.load();
                Scene addScene = new Scene(root);
                editName.setText(lastSelected.playerData.getName().getFirstName());
                editMidName.setText(lastSelected.playerData.getName().getMiddleName());
                editSurname.setText(lastSelected.playerData.getName().getSurname());
                editTeam.setText(lastSelected.playerData.getName().getTeamName());
                editPlayerStg.setScene(addScene);
                editPlayerStg.setResizable(false);
                editPlayerStg.show();
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    private void btnclick_addPlayer(){
        addPlayerStg = new Stage();
        try{
            URL url = new File("competition/src/main/java/com/competition/Player/PlayerCreate.fxml").toURI().toURL();
            FXMLLoader fxmlLoader= new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setController(this);
            AnchorPane root=fxmlLoader.load();
            Scene addScene = new Scene(root);
            addPlayerStg.setScene(addScene);
            addPlayerStg.setResizable(false);
            addPlayerStg.show();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    private void btnclick_deletePlayer(){
        //yes/no dialog
        lastSelected = (PlayerRanking.PlayerDataWithTeam) TablePlayers.getSelectionModel().getSelectedItem();
        ButtonType yes = new ButtonType("Tak", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,"Czy na pewno chcesz usunac gracza "+lastSelected.playerData.getName().getFirstName()+" "+lastSelected.playerData.getName().getSurname()+"?",yes,no);
        alert.setTitle("Usuwanie gracza");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {
            try {
                ranking.removePlayerTeamFromByID(lastSelected.playerData.getId());
                ranking.saveRankingToFile();
            }catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        lastSelected=null;
        refresh_data();
    }

    private void save_edit(){
        try {
            PlayerRanking playerRanking = new PlayerRanking();
            String name = editName.getText();
            String midName = editMidName.getText();
            String surname = editSurname.getText();
            String team = editTeam.getText();
            Name name_list = new Name.Builder().firstName(name)
                    .middleName(midName)
                    .surname(surname)
                    .teamName(team)
                    .build();

            Record record = new Record.Builder().win(0)
                    .lose(0)
                    .build();

            Name team_name = new Name.Builder().teamName(team).build();
            Team team1 = new Team(team_name);

            PlayerData playerData = PlayerData.generatePlayer(name_list, record);
            ranking.updatePlayerByID(lastSelected.playerData.getId(),playerData,team1);
            ranking.saveRankingToFile();
            lastSelected=null;
            editPlayerStg.close();
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        refresh_data();

    }
    private void save_add(){
        try {
            //PlayerRanking playerRanking = new PlayerRanking();
            int team_id=0;
            String name = addName.getText();
            String midName = addMidName.getText();
            String surname = addSurname.getText();
            String team = addTeam.getText();
            Name name_list = new Name.Builder().firstName(name)
                    .middleName(midName)
                    .surname(surname)
                    .teamName(team)
                    .build();

            Record record = new Record.Builder().win(0)
                    .lose(0)
                    .build();

            Name team_name = new Name.Builder().teamName(team).build();
            Team team1 = new Team(team_name);

            PlayerData playerData = PlayerData.generatePlayer(name_list, record);
            ranking.addPlayerToRanking(playerData,team1);
            ranking.saveRankingToFile();
            addPlayerStg.close();
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        refresh_data();
    }

    private void refresh_data(){
        try {
            ranking.loadRankingFromFile();
            listOfPlayers = ranking.generatePlayerDataWithTeamInfo();
            PlayerTableData = FXCollections.observableArrayList(listOfPlayers);
            TablePlayers.setItems(PlayerTableData);
            TablePlayers.refresh();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }


}
