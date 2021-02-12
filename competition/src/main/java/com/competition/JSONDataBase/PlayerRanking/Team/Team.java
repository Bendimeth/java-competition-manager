package com.competition.JSONDataBase.PlayerRanking.Team;

import com.competition.JSONDataBase.PlayerRanking.PlayerComponent.PlayerComponent;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.Name;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerData;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.ArrayList;
import java.util.List;

public class Team extends PlayerComponent {

    private ArrayList<PlayerData> teamPlayerList = new ArrayList<>();

    public Team(){}
    public Team(Name name){
        this.name = name;
    }

    @Override
    public void add(PlayerData playerComponent){
        teamPlayerList.add(playerComponent);
    }

    @Override
    public void remove(PlayerData playerComponent){
        teamPlayerList.remove(playerComponent);
    }

    @Override
    public void copy(PlayerComponent playerComponent){
        if(playerComponent instanceof Team){
            this.teamPlayerList = ((Team) playerComponent).getTeamPlayerList();
        }else {
            System.out.println("Team,copy: you are trying copy Player Data class to Team class.");
        }
    }

    @Override
    public String giveInfo(){
        int size = teamPlayerList.size();
        String result = "Team name: " + getName().getTeamName() + " Team ID: "+ getId() + " Size: " + size + " ";

        if(size != 0){
            result += "content: ";
            for(PlayerData playerData : teamPlayerList){
                result += playerData.giveInfo() + " ";
            }
        }

        return result;
    }

    //BCS of JSON MAPPER
    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public Name getName(){
        return this.name;
    }
    @Override
    public void setName(Name name) {
        this.name = name;
    }

    public ArrayList<PlayerData> getTeamPlayerList(){
        return teamPlayerList;
    }

    public void setTeamPlayerList(ArrayList<PlayerData> teamPlayerList) {
        this.teamPlayerList = teamPlayerList;
    }
}

