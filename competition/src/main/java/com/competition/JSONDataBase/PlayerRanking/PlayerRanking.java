package com.competition.JSONDataBase.PlayerRanking;

import com.competition.JSONDataBase.Json.JsonFileReaderWriter;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.Name;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerRanking {
    private List<PlayerData> listOfPlayers;

    private static String path;

    public PlayerRanking(){
        listOfPlayers = new ArrayList<>();
    }

    public void LoadRankingFromFile(){

    }

    public void addPlayerToRanking(PlayerData playerData){
        //TODO Check if there is player with this name
        //TODO generate ID for player
        listOfPlayers.add(playerData);
    }

    public  void removePlayerFromRankingByName(Name name){
        listOfPlayers.removeIf(x -> x.getName().equals(name));
    }

    public  void removePlayerFromByID(int id){
        listOfPlayers.removeIf(x->x.getId() == id);
    }

    public List<PlayerData> getListOfPlayers() {
        return listOfPlayers;
    }

    public void setListOfPlayers(List<PlayerData> listOfPlayers) {
        this.listOfPlayers = listOfPlayers;
    }

    private static void generatePath(){
        String pathToAppData = System.getenv("APPDATA");
        String nameOfFile = "example.json";
        path = pathToAppData + "/"+ nameOfFile;
        System.out.println("Path: " + path);
    }

    public void saveRankingToFile() throws IOException {
        generatePath();
        JsonFileReaderWriter.saveJsonObjectInFile(this,path);
    }

    public void loadRankingFromFile() throws IOException {
        generatePath();
        PlayerRanking playerRanking = JsonFileReaderWriter.readPlayerRankingFromFile(this,path);
        this.listOfPlayers = playerRanking.listOfPlayers;
    }
}
