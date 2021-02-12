package com.competition.JSONDataBase.PlayerRanking;

import com.competition.JSONDataBase.Json.JsonFileReaderWriter;
import com.competition.JSONDataBase.PlayerRanking.PlayerComponent.PlayerComponent;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.Name;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerData;
import com.competition.JSONDataBase.PlayerRanking.Team.Team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerRanking {
    private ArrayList<PlayerComponent> listOfPlayersTeams;
    private int lastGeneratedID = -1;

    private static String path;

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        PlayerRanking.path = path;
    }

    public PlayerRanking(){
        listOfPlayersTeams = new ArrayList<>();
    }

    public void printPlayerRankingInfo(){
        for(PlayerComponent playerComponent:listOfPlayersTeams){
            System.out.println(playerComponent.giveInfo());
        }
    }

    public void addPlayerToRanking(PlayerData player,Team team){

        PlayerData handler = findPlayerInDataBase(player);

        if(handler != null){
            //Find and update player data
            updatePlayerByObject(handler,player,team);
        }else{
            //Create new one

            if(team == null){
                listOfPlayersTeams.add(player);
            }else {
                // if team is in database
                Team teamHandler = findTeamInDataBase(team);

                if(teamHandler != null){
                    teamHandler.add(player);
                }else{
                    listOfPlayersTeams.add(team);
                    team.add(player);
                }
            }
        }


        /*
        if(listOfPlayersTeams.stream().anyMatch(x -> x.getName().equals(player.getName()))){
            //System.out.println("I got this one on list, update function activated");
            PlayerComponent playerDataHandler = listOfPlayersTeams.stream().filter(p -> p.getName().equals(p.getName())).findAny().orElse(null);
            updatePlayerByObject(playerDataHandler,player,team);
        }
        else{
            player.setId(lastGeneratedID + 1);
            lastGeneratedID++;

            //System.out.println(lastGeneratedID);

            listOfPlayersTeams.add(playerComponent);
        }
        */
    }

    public void addTeamToRanking(Team team){

        Team teamHandler = findTeamInDataBase(team);

        if(teamHandler == null){
            listOfPlayersTeams.add(team);
        }else{
            //update
            teamHandler.copy(team);
        }
    }

    private PlayerData findPlayerInDataBase(PlayerData playerData){

        for(int i = 0; i < listOfPlayersTeams.size(); i++){
            PlayerComponent pcHanler = listOfPlayersTeams.get(i);

            if(pcHanler instanceof Team){
                Team team = (Team) pcHanler;

                for(PlayerData player : team.getTeamPlayerList()){
                    if(player.getName().equals(playerData.getName())){
                        return player;
                    }
                }
            }
            else {
                if(pcHanler.getName().equals(playerData.getName())){
                    return (PlayerData) pcHanler;
                }
            }
        }

        return  null;
    }

    private Team findTeamInDataBase(Team team){

        for(PlayerComponent pc: listOfPlayersTeams){
            if(pc instanceof Team){
                if(pc.getName().equals(team.getName())){
                    return (Team) pc;
                }
            }
        }

        return null;
    }

    public int getPlayerIDByName(Name name){
        for(int i = 0; i < listOfPlayersTeams.size(); i++)
        {
            PlayerComponent playerHandler = listOfPlayersTeams.get(i);
            Name nameHandler = playerHandler.getName();

            if(nameHandler.equals(name)) return playerHandler.getId();
        }

        System.out.println("There is no player with such a name in base: " + name.getFirstName() + " " + name.getMiddleName() + " " + name.getSurname());
        return -1;
    }

    //region update existing player

    /*
    public void updatePlayerByID(int id,Payer playerData){
        PlayerComponent playerDataHandler = listOfPlayersTeams.stream().filter(player->player.getId() == id).findAny().orElse(null);
        updatePlayerByObject(playerDataHandler,playerData);
    }

     */
    public void updatePlayerByObject(PlayerComponent currentPlayerData, PlayerComponent newPlayerData,PlayerComponent team){
        currentPlayerData.copy(newPlayerData);
    }

    //endregion

    //region remove player

    public  void removePlayerFromRankingByName(Name name){
        listOfPlayersTeams.removeIf(x -> x.getName().equals(name));
    }

    public  void removePlayerFromByID(int id){
        listOfPlayersTeams.removeIf(x->x.getId() == id);
    }

    //endregion

    //region getters
    public List<PlayerComponent> getListOfPlayersTeams() {
        return listOfPlayersTeams;
    }

    public int getLastGeneratedID() {
        return lastGeneratedID;
    }
    //endregion

    //region setters

    public void setListOfPlayersTeams(ArrayList<PlayerComponent> listOfPlayersTeams) {
        this.listOfPlayersTeams = listOfPlayersTeams;
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
        this.listOfPlayersTeams = playerRanking.listOfPlayersTeams;
    }


    private static void generatePath(){
        String pathToAppData = System.getenv("APPDATA");
        String nameOfFile = "RankingData.json";
        path = pathToAppData + "/"+ nameOfFile;
        System.out.println("Path: " + path);
    }
    //endregion
}
