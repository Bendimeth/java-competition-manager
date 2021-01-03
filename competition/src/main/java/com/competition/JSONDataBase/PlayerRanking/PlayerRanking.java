package com.competition.JSONDataBase.PlayerRanking;

import com.competition.JSONDataBase.Json.JsonFileReaderWriter;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.Name;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerData;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.Record;

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

        if(listOfPlayers.stream().anyMatch(x -> x.getName().equals(playerData.getName()))){
            //System.out.println("I got this one on list, update function activated");
            PlayerData playerDataHandler = listOfPlayers.stream().filter(player -> player.getName().equals(playerData.getName())).findAny().orElse(null);
            updatePlayerByObject(playerDataHandler,playerData);
        }
        else{
            playerData.setId(lastGeneratedID + 1);
            lastGeneratedID++;

            //System.out.println(lastGeneratedID);

            listOfPlayers.add(playerData);
        }
    }

    //region update existing player

    public void updatePlayerByID(int id,PlayerData playerData){
        PlayerData playerDataHandler = listOfPlayers.stream().filter(player->player.getId() == id).findAny().orElse(null);
        updatePlayerByObject(playerDataHandler,playerData);
    }

    public void updatePlayerByObject(PlayerData currentPlayerData, PlayerData newPlayerData){
        currentPlayerData.copy(newPlayerData);
    }

    //endregion

    //region remove player

    public  void removePlayerFromRankingByName(Name name){
        listOfPlayers.removeIf(x -> x.getName().equals(name));
    }

    public  void removePlayerFromByID(int id){
        listOfPlayers.removeIf(x->x.getId() == id);
    }

    //endregion

    //region getters
    public List<PlayerData> getListOfPlayers() {
        return listOfPlayers;
    }

    public int getLastGeneratedID() {
        return lastGeneratedID;
    }
    //endregion

    public int getPlayerIDByName(Name name){
        for(int i = 0; i < listOfPlayers.size();i++)
        {
            PlayerData playerHandler = listOfPlayers.get(i);
            Name nameHandler = playerHandler.getName();

            if(nameHandler.equals(name)) return playerHandler.getId();
        }

        System.out.println("There is no player with such a name in base: " + name.getFirstName() + " " + name.getMiddleName() + " " + name.getSurname());
        return -1;
    }

    //region setters

    public void setListOfPlayers(List<PlayerData> listOfPlayers) {
        this.listOfPlayers = listOfPlayers;
    }

    public void setLastGeneratedID(int lastGeneratedID) {
        this.lastGeneratedID = lastGeneratedID;
    }
    //endregion

    //region save/load

    public void saveRankingToFile() throws IOException {
        generatePath();
        JsonFileReaderWriter.saveJsonObjectInFile(this,path);
    }

    public void loadRankingFromFile() throws IOException {
        generatePath();
        PlayerRanking playerRanking = JsonFileReaderWriter.readPlayerRankingFromFile(this,path);
        this.listOfPlayers = playerRanking.listOfPlayers;
    }

    //endregion

    private static void generatePath(){
        String pathToAppData = System.getenv("APPDATA");
        String nameOfFile = "example.json";
        path = pathToAppData + "/"+ nameOfFile;
        System.out.println("Path: " + path);
    }
}
