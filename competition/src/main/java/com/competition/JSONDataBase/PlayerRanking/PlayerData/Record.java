package com.competition.JSONDataBase.PlayerRanking.PlayerData;

public class Record {

    private int win;
    private int lose;

    public Record(){}

    private Record(Builder builder){
        this.win = builder.win;
        this.lose = builder.lose;
    }

    public static class Builder{
        private int win;
        private int lose;

        public Builder win(final int win){
            this.win = win;
            return this;
        }

        public  Builder lose(final int lose){
            this.lose = lose;
            return this;
        }

        public Record build(){
            return new Record(this);
        }
    }

    public void addWin(){
        win++;
    }

    public void addLose(){
        lose++;
    }

    //region Getters
    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }
    //endregion
    //region Setters

    public void setWin(int win) {
        this.win = win;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    //endregion
}
