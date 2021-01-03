package com.competition.JSONDataBase.PlayerRanking.PlayerData;

public class PlayerData {

    private  Name name;
    private  String team;
    private int id;

    public  PlayerData(){

    }

    private PlayerData(Builder builder){
        this.name = builder.name;
        this.team = builder.team;
    }

    public void copy(PlayerData playerData){
        this.name = playerData.getName();
        this.team = playerData.getTeam();
        this.id = playerData.getId();
    }

    public static class Builder{

        private Name name;
        private  String team;

        public Builder name(final Name name){
            this.name = name;
            return this;
        }

        public Builder team(final String team){
            this.team = team;
            return this;
        }

        public PlayerData build(){
            return new PlayerData(this);
        }
    }

    //region Getters

    public Name getName() { return name; }

    public String getTeam() {
        return team;
    }

    public int getId() {
        return id;
    }
    //endregion

    //region Setters
    public void setName(Name name) {
        this.name = name;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setId(int id) {
        this.id = id;
    }
    //endregion
}
