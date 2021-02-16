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

    public ArrayList<PlayerDataWithTeam> generatePlayerDataWithTeamInfo(){
        ArrayList<PlayerDataWithTeam> result = new ArrayList<>();

        for(PlayerComponent playerComponent : listOfPlayersTeams){
            if(playerComponent instanceof PlayerData){
                PlayerDataWithTeam playerDataWithTeam = new PlayerDataWithTeam((PlayerData)playerComponent,null);
                result.add(playerDataWithTeam);
            } else if(playerComponent instanceof Team){
                Team team = (Team) playerComponent;

                for(PlayerData playerData : team.getTeamPlayerList()){
                    PlayerDataWithTeam playerDataWithTeam = new PlayerDataWithTeam(playerData,team);
                    result.add(playerDataWithTeam);
                }
            }
        }

        return result;
    }

    public void printPlayerRankingInfo(){
        for(PlayerComponent playerComponent:listOfPlayersTeams){
            System.out.println(playerComponent.giveInfo());
        }
    }

    public void addPlayerToRanking(PlayerData player,Team team,int id){
        PlayerData handler = findPlayerInDataBase(player.getName());

        if(handler != null){
            System.out.println("This player already exists in ranking");
        }else{
            //Create new one
            player.setId(id);
            if(team == null){
                listOfPlayersTeams.add(player);
            }else {
                // if team is in database
                Team teamHandler = findTeamInDataBase(team);

                if(teamHandler != null){
                    teamHandler.add(player);
                }else{
                    lastGeneratedID++;
                    team.setId(lastGeneratedID);
                    listOfPlayersTeams.add(team);
                    team.add(player);
                }
            }
        }
    }

    public void addPlayerToRanking(PlayerData player,Team team){

        PlayerData handler = findPlayerInDataBase(player.getName());

        if(handler != null){
            System.out.println("This player already exists in ranking");
        }else{
            //Create new one
            lastGeneratedID++;
            player.setId(lastGeneratedID);
            if(team == null){
                listOfPlayersTeams.add(player);
            }else {
                // if team is in database
                Team teamHandler = findTeamInDataBase(team);

                if(teamHandler != null){
                    teamHandler.add(player);
                }else{
                    lastGeneratedID++;
                    team.setId(lastGeneratedID);
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



    private PlayerData findPlayerInDataBase(Name playerName){

        for(int i = 0; i < listOfPlayersTeams.size(); i++){
            PlayerComponent pcHanler = listOfPlayersTeams.get(i);

            if(pcHanler instanceof Team){
                Team team = (Team) pcHanler;

                for(PlayerData player : team.getTeamPlayerList()){
                    if(player.getName().equals(playerName)){
                        return player;
                    }
                }
            }
            else {
                if(pcHanler.getName().equals(playerName)){
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

    private Team findTeamInDataBaseByName(String name){

        for(PlayerComponent pc: listOfPlayersTeams){
            if(pc instanceof Team){
                if(pc.getName().getTeamName() == name){
                    return (Team) pc;
                }
            }
        }

        System.out.println("Find team in data base: there is no team with name: " + name);
        return null;
    }

    public int getTeamPlayerIDByName(Name name){
        for(int i = 0; i < listOfPlayersTeams.size(); i++)
        {
            if(listOfPlayersTeams.get(i) instanceof PlayerData)
            {
                PlayerData playerHandler = (PlayerData) listOfPlayersTeams.get(i);
                Name nameHandler = playerHandler.getName();
                if(nameHandler.equals(name)) return playerHandler.getId();
            } else if(listOfPlayersTeams.get(i) instanceof Team){
                Team team = (Team)listOfPlayersTeams.get(i);

                if(team.getName().equals(name)) return team.getId();

                for(PlayerData pd: team.getTeamPlayerList()){
                    if(pd.getName().equals(name))return pd.getId();
                }
            }


        }

        System.out.println("There is no player with such a name in base: " + name.getFirstName() + " " + name.getMiddleName() + " " + name.getSurname());
        return -1;
    }

    //region update existing player

    public void updatePlayer(PlayerData newPlayerData,Team team){
        PlayerData playerDataHandler = findPlayerInDataBase(newPlayerData.getName());

        if(playerDataHandler == null){
            System.out.println("Player update: no player with this name");
        }else{
            updatePlayerByObject(playerDataHandler,newPlayerData,team);
        }
    }

    public void updatePlayerByID(int id,PlayerData newPlayerData,Team team){
        for(PlayerComponent playerComponent : listOfPlayersTeams){
            if(playerComponent.getId() == id){
                if(playerComponent instanceof Team){
                    System.out.println("Cant update bcs it's team");
                    return;
                }
                else {
                    PlayerData playerDataHandler = (PlayerData) playerComponent;
                    updatePlayerByObject(playerDataHandler,newPlayerData,team);
                    return;
                }
            }
        }

        System.out.println("I cant find player data with this ID: " + id);
    }

    public void updatePlayerByObject(PlayerData currentPlayerData, PlayerData newPlayerData,Team team){
        PlayerData pcHandler = removePlayerFromRankingByName(currentPlayerData.getName());
        System.out.println("Current player data: " + currentPlayerData.getName().getSurname());
        if(pcHandler != null){
            int id = pcHandler.getId();
            addPlayerToRanking(newPlayerData,team,id);
        }else{
            System.out.println("Player update: no player to update with this name: " + newPlayerData.getName().getFirstName() + " " +newPlayerData.getName().getSurname());
        }

    }

    //endregion

    //region remove player

    public PlayerData removePlayerFromRankingByName(Name name){
            for(PlayerComponent playerComponent : listOfPlayersTeams){
                if(playerComponent instanceof Team){
                    Team team = (Team) playerComponent;

                    for(PlayerData pc: team.getTeamPlayerList()){
                        if(pc.getName().equals(name)){
                            PlayerData playerDataHandler = pc;
                            team.getTeamPlayerList().remove(pc);
                            return playerDataHandler;
                        }
                    }
                    //team.getTeamPlayerList().removeIf(x -> x.getName().equals(name));
                }else if(playerComponent instanceof PlayerData){
                    if(playerComponent.getName().equals(name)){
                        PlayerData playerDataHandler = (PlayerData) playerComponent;
                        listOfPlayersTeams.remove(playerComponent);
                        return playerDataHandler;
                    }
                }
            }

        return null;
    }

    public  void removePlayerTeamFromByID(int id){
        if(!listOfPlayersTeams.removeIf(x -> x.getId() == id)){
            for(PlayerComponent playerComponent : listOfPlayersTeams){
                if(playerComponent instanceof Team){
                    Team team = (Team) playerComponent;
                    team.getTeamPlayerList().removeIf(x -> x.getId() == id);
                }
            }
        }
    }

    public Team removeTeamFromRankingByName(Name name){

        for(PlayerComponent pc : listOfPlayersTeams){
            if(pc.getName().equals(name) && pc instanceof Team){

                Team team = (Team) pc;

                for(int i = team.getTeamPlayerList().size() - 1;i >= 0;i--){
                    PlayerData handler = team.getTeamPlayerList().get(i);
                    updatePlayerByObject(handler,handler,null);
                }

                listOfPlayersTeams.remove(pc);
                return team;
            }
        }

        return null;
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
        this.lastGeneratedID = playerRanking.getLastGeneratedID();
    }


    private static void generatePath(){
        String pathToAppData = System.getenv("APPDATA");
        String nameOfFile = "RankingData.json";
        path = pathToAppData + "/"+ nameOfFile;
        System.out.println("Path: " + path);
    }
    //endregion


    public class PlayerDataWithTeam{
        public PlayerDataWithTeam(PlayerData playerData,Team team){
            this.playerData = playerData;
            this.team = team;
        }

        public PlayerData playerData;
        public Team team;
    }
}
