package com.competition.JSONDataBase.PlayerRanking;

import com.competition.JSONDataBase.Json.JsonFileReaderWriter;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.Name;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerRanking {
    private List<PlayerData> listOfPlayers;
    private int lastGeneratedID = -1;

    private static String path;

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        PlayerRanking.path = path;
    }

    public PlayerRanking(){
        listOfPlayers = new ArrayList<>();
    }

    public void LoadRankingFromFile(){

    }

    public void addPlayerToRanking(PlayerData playerData){
        //TODO Check if there is player with this name

        if(listOfPlayers.stream().anyMatch(x -> x.getName().equals(playerData.getName()))){
            System.out.println("I got this one on list, update function activated");

            PlayerData playerDataHandler = listOfPlayers.stream().filter(player -> player.getName().equals(playerData.getName())).findAny().orElse(null);

            playerDataHandler.copy(playerData);

        }
        else{
            playerData.setId(lastGeneratedID + 1);
            lastGeneratedID++;

            //System.out.println(lastGeneratedID);

            listOfPlayers.add(playerData);
        }
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

    public int getLastGeneratedID() {
        return lastGeneratedID;
    }

    public void setLastGeneratedID(int lastGeneratedID) {
        this.lastGeneratedID = lastGeneratedID;
    }
}
