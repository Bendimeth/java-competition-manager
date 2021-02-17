package com.competition.JSONDataBase.PlayerRanking.PlayerData;

import java.util.Objects;

public class Name {

    private String firstName;
    private String middleName;
    private String surname;
    private String teamName;

    public Name(){

    }

    private Name(Builder builder){
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.surname = builder.surname;
        this.teamName = builder.teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(firstName, name.firstName) &&
                Objects.equals(middleName, name.middleName) &&
                Objects.equals(surname, name.surname) &&
                Objects.equals(teamName,name.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, surname,teamName);
    }

    public static class Builder{
        private String firstName;
        private String middleName;
        private String surname;
        private String teamName;

        public Builder firstName(final String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder middleName(final String middleName){
            this.middleName = middleName;
            return this;
        }

        public Builder surname(final String surname){
            this.surname = surname;
            return this;
        }

        public Builder teamName(final String teamName){
            this.teamName = teamName;
            return this;
        }

        public Name build(){
            return new Name(this);
        }
    }

    //region Getter
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSurname() {
        return surname;
    }

    public String getTeamName() { return teamName; }
    //endregion

    //region Setter
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTeamName(String teamName) { this.teamName = teamName; }
    //endregion
}
