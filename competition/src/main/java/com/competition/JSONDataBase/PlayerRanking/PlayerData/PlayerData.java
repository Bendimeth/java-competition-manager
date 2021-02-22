package com.competition.JSONDataBase.PlayerRanking.PlayerData;

import com.competition.JSONDataBase.PlayerRanking.PlayerComponent.PlayerComponent;

public class PlayerData extends PlayerComponent {

    private Record record;

    public  PlayerData(){ }

    private PlayerData(Builder builder){
        this.name = builder.name;
        this.record = builder.record;
    }

    @Override
    public void copy(PlayerComponent playerComponent){
        PlayerData playerData = (PlayerData) playerComponent;

        this.name = playerData.getName();
        this.id = playerData.getId();
        this.record = playerData.getRecord();
    }

    @Override
    public String giveInfo(){
        String result = "First Name: " +this.getName().getFirstName() + " " +
                "Middle Name: " + this.getName().getMiddleName() + " " +
                "Surname: " + this.getName().getSurname() + " "+
                "win: " + getRecord().getWin() + " " +
                "lose: " + getRecord().getLose() + " " +
                "ID: " + getId();

        return  result;
    }

    //Bcs of JSON
    @Override
    public Name getName(){
        return this.name;
    }
    @Override
    public void setName(Name name) {
        this.name = name;
    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }



    public static class Builder{

        private Name name;
        private Record record;

        public Builder name(final Name name){
            this.name = name;
            return this;
        }

        public Builder record(final Record record){
            this.record = record;
            return this;
        }

        public PlayerData build(){
            return new PlayerData(this);
        }
    }

    public static PlayerData generatePlayer(Name name,Record record){
        PlayerData playerData = new PlayerData.Builder().name(name).record(record).build();
        return  playerData;
    }

    //region Getters
    public Record getRecord(){return record;}
    //endregion

    //region Setters
    public void setRecord(Record record){this.record = record;}
    //endregion
}
