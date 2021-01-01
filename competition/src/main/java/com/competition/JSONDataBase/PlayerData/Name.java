package com.competition.JSONDataBase.PlayerData;

import java.security.PublicKey;

public class Name {

    private String firstName;
    private String middleName;
    private String surname;

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
    public void setName(String firstName) {
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
