package com.competition.MainPage;

import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerData;
import com.competition.JSONDataBase.PlayerRanking.PlayerRanking;
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
    @FXML private Button btnhelp;
    @FXML private Button btnranking;
    @FXML private Button btneditplayerteam;
    @FXML private Button btncreatetournament;
    @FXML private Button btnhistory;
    @FXML private TableView RankingList;
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
        btnhelp.setOnAction(event -> btnclick_help());
        btnranking.setOnAction(event -> btnclick_ranking());
        btneditplayerteam.setOnAction(event -> btnclick_editplayerteam());
        btncreatetournament.setOnAction(event -> btnclick_createtournament());
        btnhistory.setOnAction(event -> btnclick_history());

    }


    public void btnclick_ranking(){
        try {
            URL url = new File("competition/src/main/java/com/competition/MainPage/Ranking.fxml").toURI().toURL();
            FXMLLoader fxmlLoader= new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setController(this);
            AnchorPane root=fxmlLoader.load();
            List<PlayerData> listOfPlayers;
            PlayerRanking ranking = new PlayerRanking();
            ranking.loadRankingFromFile();
            listOfPlayers = ranking.getListOfPlayers();
            List<RankingModel> list_of_player = new ArrayList<>();
            listOfPlayers.stream().sorted();
            listOfPlayers.sort((p1,p2) -> p2.getRecord().getWin()-p1.getRecord().getWin());
            for(int i=0;listOfPlayers.size()>i;i++){
                RankingModel record = new RankingModel.Builder().loses(listOfPlayers.get(i).getRecord().getLose()).wins(listOfPlayers.get(i).getRecord().getWin()).nameOfPlayer(listOfPlayers.get(i).getName().getFirstName()+" "+listOfPlayers.get(i).getName().getMiddleName()+" "+listOfPlayers.get(i).getName().getSurname()+" - "+listOfPlayers.get(i).getTeam()).place(i+1).build();
                list_of_player.add(i,record);
            }
            ObservableList<RankingModel> PlayerTableData = FXCollections.observableArrayList(list_of_player);
            //RankingList.setItems(PlayerTableData);
            TableColumn colName = new TableColumn<>("Name");
            TableColumn colPlace = new TableColumn<>("Place");
            TableColumn colWins = new TableColumn<>("Wins");
            TableColumn colLoses = new TableColumn<>("Loses");
            colName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingModel, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<RankingModel, String> c) {
                    return new SimpleStringProperty(c.getValue().getNameOfPlayer());
                }
            });
            colPlace.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingModel, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<RankingModel, Integer> c) {
                    return new SimpleIntegerProperty(c.getValue().getPlace()).asObject();
                }
            });
            colWins.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingModel, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<RankingModel, Integer> c) {
                    return new SimpleIntegerProperty(c.getValue().getWins()).asObject();
                }
            });
            colLoses.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingModel, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<RankingModel, Integer> c) {
                    return new SimpleIntegerProperty(c.getValue().getLoses()).asObject();
                }
            });
            //RankingList.
            colName.setPrefWidth(500);
            colPlace.setPrefWidth(200);
            colWins.setPrefWidth(200);
            colLoses.setPrefWidth(200);
            firstPlayerLabel.setText("I miejsce - "+PlayerTableData.get(0).getNameOfPlayer());
            RankingList.getColumns().clear();
            RankingList.getColumns().addAll(colPlace,colName,colWins,colLoses);
            RankingList.setItems(PlayerTableData);
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

    public void btnclick_history(){

    }

    public void btnclick_help(){

    }



}
