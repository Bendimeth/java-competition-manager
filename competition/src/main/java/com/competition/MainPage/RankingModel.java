package com.competition.MainPage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class RankingModel implements Comparable<RankingModel>,RankingIterator  {
    private List<String> namesOfPlayers = new ArrayList<>();
    private List<RankingModel> players = new ArrayList<>();
    private int place;
    private String nameOfPlayer;
    private int wins;
    private int loses;
    private int currentPosition;


    public RankingModel(){

    }

    public void load(){
        for(int i=0;i<namesOfPlayers.size();i++){
            var plyer = getNext();
            namesOfPlayers.add(plyer.nameOfPlayer);
        }
    }

    @Override
    public boolean hasNext() {
        return currentPosition < namesOfPlayers.size();
    }

    @Override
    public RankingModel getNext() {
        if (!hasNext()) {
            return null;
        }
        RankingModel player = players.get(currentPosition);
        currentPosition++;
        return player;
    }

    @Override
    public void reset() {
        currentPosition = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RankingModel name = (RankingModel) o;
        return Objects.equals(place, name.place) && Objects.equals(nameOfPlayer, name.nameOfPlayer) && Objects.equals(wins, name.wins)&& Objects.equals(loses, name.loses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, nameOfPlayer, wins,loses);
    }


    private RankingModel(Builder builder){
        this.place = builder.place;
        this.nameOfPlayer = builder.nameOfPlayer;
        this.wins = builder.wins;
        this.loses = builder.loses;
        this.namesOfPlayers=builder.namesOfPlayers;
    }

    public static class Builder{
        private int place;
        private String nameOfPlayer;
        private int wins;
        private int loses;
        private List<String> namesOfPlayers;

        public Builder place(final int place){
            this.place = place;
            return this;
        }

        public Builder nameOfPlayer(final String nameOfPlayer){
            this.nameOfPlayer = nameOfPlayer;
            return this;
        }

        public Builder wins(final int wins){
            this.wins = wins;
            return this;
        }

        public Builder loses(final int loses){
            this.loses = loses;
            return this;
        }

        public Builder namesOfPlayers(final String player){
            this.namesOfPlayers.add(player);
            return  this;
        }

        public RankingModel build(){
            return new RankingModel(this);
        }
    }

    //region Getter
    public int getPlace() {
        return place;
    }

    public String getNameOfPlayer() {
        return nameOfPlayer;
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {return loses;}

    public String getPlayerName() {return namesOfPlayers.get(currentPosition);}
    //endregion

    //region Setter
    public void setPlace(int place) {
        this.place = place;
    }

    public void setNameOfPlayer(String nameOfPlayer) {
        this.nameOfPlayer = nameOfPlayer;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLoses(int loses) {this.loses = loses;}
    //endregion
    @Override
    public int compareTo(RankingModel o) {
        return Comparators.NAME.compare(this, o);
    }
    public static class Comparators {

        public static Comparator<RankingModel> NAME = new Comparator<RankingModel>() {
            @Override
            public int compare(RankingModel o1, RankingModel o2) {
                return o1.nameOfPlayer.compareTo(o2.nameOfPlayer);
            }
        };
        public static Comparator<RankingModel> WINS = new Comparator<RankingModel>() {
            @Override
            public int compare(RankingModel o1, RankingModel o2) {
                return o1.wins - o2.wins;
            }
        };
        public static Comparator<RankingModel> LOSES = new Comparator<RankingModel>() {
            @Override
            public int compare(RankingModel o1, RankingModel o2) {
                return o1.loses - o2.loses;
            }
        };
    }

}
