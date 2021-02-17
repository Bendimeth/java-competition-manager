package com.competition.JSONDataBase.PlayerRanking.PlayerData;

import java.util.Objects;

public class Name {

    private String firstName;
    private String middleName;
    private String surname;

    public Name(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(firstName, name.firstName) && Objects.equals(middleName, name.middleName) && Objects.equals(surname, name.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, surname);
    }


    private Name(Builder builder){
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.surname = builder.surname;
    }

    public static class Builder{
        private String firstName;
        private String middleName;
        private String surname;

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
    //endregion
}
