package com.competition.JSONDataBase.PlayerData;

public class PlayerData {

    private  Name name;
    private  String team;
    private int id;

    private  PlayerData(Builder builder){
        this.name = builder.name;
        this.team = builder.team;
        this.id = builder.id;
    }

    public  static class Builder{

        private Name name;
        private  String team;
        private  int id;

        public Builder id(final int id){
            this.id = id;
            return this;
        }

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

    public void setTeam(String team) {
        this.team = team;
    }

    public void setId(int id) {
        this.id = id;
    }
    //endregion
}
