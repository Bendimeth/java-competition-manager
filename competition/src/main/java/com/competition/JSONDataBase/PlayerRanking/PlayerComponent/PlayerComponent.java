package com.competition.JSONDataBase.PlayerRanking.PlayerComponent;

import com.competition.JSONDataBase.PlayerRanking.PlayerData.Name;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerData;

import com.competition.JSONDataBase.PlayerRanking.Team.Team;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

//@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlayerData.class, name = "PlayerData"),

        @JsonSubTypes.Type(value = Team.class, name = "Team") }
)
public abstract class PlayerComponent {

    protected Name name;
    protected int id;

    //For JSON ###
     /*
    @JsonDeserialize(as = PlayerData.class)
    public abstract class AbstractPlayerData{}
    @JsonDeserialize(as = Team.class)
    public abstract class AbstractTeam{}
    */
    //###

    public abstract void copy(PlayerComponent playerComponent);

    public abstract String giveInfo();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Name getName(){
        return this.name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void add(PlayerData playerComponent){
        throw  new UnsupportedOperationException();
    }

    public void remove(PlayerData playerComponent){
        throw  new UnsupportedOperationException();
    }
}
