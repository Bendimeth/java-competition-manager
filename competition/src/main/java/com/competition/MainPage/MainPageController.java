package com.competition.MainPage;

import com.competition.JSONDataBase.PlayerRanking.PlayerRanking;
import com.competition.JSONDataBase.PlayerRanking.Team.Team;
import com.competition.Player.PlayerController;
import com.competition.menu.Menu;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    private final Stage thisStage;
    private Scene thisScene;
    @FXML private AnchorPane paneShow;
    @FXML private Label LabelTest;
    @FXML private Label firstPlayerLabel;
    @FXML private Label firstTeamLabel;
    @FXML private Button btnranking;
    @FXML private Button btneditplayerteam;
    @FXML private Button btncreatetournament;
    @FXML private TableView RankingList;
    @FXML private TableView RankingListTeam;
    public static AnchorPane PublicPane;

    public MainPageController()throws IOException {
        thisStage = new Stage();
        URL url = new File("competition/src/main/java/com/competition/MainPage/MainPage.fxml").toURI().toURL();
        try {
            FXMLLoader fxmlLoader= new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setController(this);
            Parent root=fxmlLoader.load();
            thisScene  = new Scene(root);
            thisScene.getStylesheets().add("competition/src/main/java/com/competition/MainPage/main_styles.css");
            thisStage.setTitle("Tournament App");
            thisStage.setMaximized(true);
            thisStage.setScene(thisScene);
            btnclick_ranking();
        }catch (Exception ex){
            //log error
            System.out.println(ex.getMessage());
        }
    }

    public void setPane(AnchorPane panel){
        paneShow.getChildren().clear();
        paneShow.getChildren().add(panel);
    }

    public void start_main_page(Stage stage) throws IOException {
        thisStage.showAndWait();

    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnranking.setOnAction(event -> btnclick_ranking());
        btneditplayerteam.setOnAction(event -> btnclick_editplayerteam());
        btncreatetournament.setOnAction(event -> btnclick_createtournament());
    }


    public void btnclick_ranking(){
        try {
            URL url = new File("competition/src/main/java/com/competition/MainPage/Ranking.fxml").toURI().toURL();
            FXMLLoader fxmlLoader= new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setController(this);
            AnchorPane root=fxmlLoader.load();
            PlayerRanking ranking = new PlayerRanking();
            ranking.loadRankingFromFile();
            List<RankingModel> list_of_player = new ArrayList<>();
            List<RankingModel> list_of_teams = new ArrayList<>();
            ArrayList<PlayerRanking.PlayerDataWithTeam> listOfPlayers = new ArrayList<>();
            listOfPlayers = ranking.generatePlayerDataWithTeamInfo();
            listOfPlayers.sort((p1,p2)->p2.playerData.getRecord().getWin()-p1.playerData.getRecord().getWin());
            for(int i=0;listOfPlayers.size()>i;i++){
                RankingModel record = new RankingModel.Builder()
                        .loses(listOfPlayers.get(i).playerData.getRecord().getLose())
                        .wins(listOfPlayers.get(i).playerData.getRecord().getWin())
                        .nameOfPlayer(listOfPlayers.get(i).playerData.getName().getFirstName()+" "+listOfPlayers.get(i).playerData.getName().getMiddleName()+" "+listOfPlayers.get(i).playerData.getName().getSurname()+" - "+(listOfPlayers.get(i).team==null?"Brak zespołu":listOfPlayers.get(i).team.getName().getTeamName()))
                        .place(i+1).build();
                list_of_player.add(i,record);
            }
            //var listteam = listOfPlayers.stream().filter(x->x.playerData.getName().getTeamName()=="a");
            var lidtt = ranking.getListOfPlayersTeams();
            for(var el:lidtt){
                if(el instanceof Team) {
                    int wins_res=0,lose_res=0;
                    var TeamPlayers = ((Team) el).getTeamPlayerList();
                    if(TeamPlayers.size()>0) {
                        for (var PlayerEl : TeamPlayers) {
                            wins_res += PlayerEl.getRecord().getWin();
                            lose_res += PlayerEl.getRecord().getLose();
                        }
                        RankingModel record = new RankingModel.Builder()
                                .loses(lose_res)
                                .wins(wins_res)
                                .nameOfPlayer((el.getName().getTeamName().trim().isEmpty() ? "Bez zespolu" : el.getName().getTeamName()) + " - " + TeamPlayers.size() + " gracz(y)")
                                .place(0).build();
                        list_of_teams.add(record);
                    }
                }
            }
            list_of_teams.sort((x1,x2)->x2.getWins()-x1.getWins());
            for (int i=0;i<list_of_teams.size();i++){
                list_of_teams.get(i).setPlace(i+1);
            }
            ObservableList<RankingModel> PlayerTableData = FXCollections.observableArrayList(list_of_player);
            ObservableList<RankingModel> TeamTableData = FXCollections.observableArrayList(list_of_teams);

            TableColumn colPlyName = new TableColumn<>("Name");
            TableColumn colPlyPlace = new TableColumn<>("Place");
            TableColumn colPlyWins = new TableColumn<>("Wins");
            TableColumn colPlyLoses = new TableColumn<>("Loses");
            TableColumn colTeamName = new TableColumn<>("Name");
            TableColumn colTeamPlace = new TableColumn<>("Place");
            TableColumn colTeamWins = new TableColumn<>("Wins");
            TableColumn colTeamLoses = new TableColumn<>("Loses");
            colPlyName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingModel, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<RankingModel, String> c) {
                    return new SimpleStringProperty(c.getValue().getNameOfPlayer());
                }
            });
            colPlyPlace.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingModel, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<RankingModel, Integer> c) {
                    return new SimpleIntegerProperty(c.getValue().getPlace()).asObject();
                }
            });
            colPlyWins.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingModel, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<RankingModel, Integer> c) {
                    return new SimpleIntegerProperty(c.getValue().getWins()).asObject();
                }
            });
            colPlyLoses.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingModel, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<RankingModel, Integer> c) {
                    return new SimpleIntegerProperty(c.getValue().getLoses()).asObject();
                }
            });
            //RankingList.
            colPlyName.setPrefWidth(400);
            colPlyPlace.setPrefWidth(80);
            colPlyWins.setPrefWidth(100);
            colPlyLoses.setPrefWidth(100);
            colTeamName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingModel, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<RankingModel, String> c) {
                    return new SimpleStringProperty(c.getValue().getNameOfPlayer());
                }
            });
            colTeamPlace.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingModel, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<RankingModel, Integer> c) {
                    return new SimpleIntegerProperty(c.getValue().getPlace()).asObject();
                }
            });
            colTeamWins.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingModel, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<RankingModel, Integer> c) {
                    return new SimpleIntegerProperty(c.getValue().getWins()).asObject();
                }
            });
            colTeamLoses.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingModel, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<RankingModel, Integer> c) {
                    return new SimpleIntegerProperty(c.getValue().getLoses()).asObject();
                }
            });
            //RankingList.
            colTeamName.setPrefWidth(400);
            colTeamPlace.setPrefWidth(80);
            colTeamWins.setPrefWidth(100);
            colTeamLoses.setPrefWidth(100);
            firstPlayerLabel.setText("I miejsce - "+PlayerTableData.get(0).getNameOfPlayer());
            firstTeamLabel.setText("I miejsce Zespół - "+TeamTableData.get(0).getNameOfPlayer());
            RankingList.getColumns().clear();
            RankingListTeam.getColumns().clear();
            RankingList.getColumns().addAll(colPlyPlace,colPlyName,colPlyWins,colPlyLoses);
            RankingListTeam.getColumns().addAll(colTeamPlace,colTeamName,colTeamWins,colTeamLoses);
            RankingList.setItems(PlayerTableData);
            RankingListTeam.setItems(TeamTableData);
            this.setPane(root);
        }catch (Exception ex){
            //log error
            System.out.println(ex.getMessage());
        }
    }


    public void btnclick_createtournament(){
        try {
            Menu amenu = new Menu();
            amenu.start(thisStage);
        }catch (Exception ex){

        }
    }

    public void btnclick_editplayerteam(){
        try {
            PlayerController playerController = new PlayerController(this);
        }catch (Exception ex){
            //log error
            System.out.println(ex.getMessage());
        }
    }




}
