package com.competition.JSONDataBase.PlayerData;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerDataTest {

    @Test
    public void testCreatePlayerData(){

        String firstNameTest = "Jakub";
        String middleNameTest = "Antoni";
        String surnameTest = "Szwajcok";
        String teamTest = "Nigma";
        int idTest = 13;

        PlayerData playerData = generateExamplePlayer();

        assertEquals(playerData.getName().getFirstName(),firstNameTest);
        assertEquals(playerData.getName().getMiddleName(),middleNameTest);
        assertEquals(playerData.getName().getSurname(),surnameTest);
        assertEquals(playerData.getTeam(),teamTest);
        assertEquals(playerData.getId(),idTest);
    }

    @Test
    public  void testSavingPlayerToFile() throws IOException {
        PlayerData playerData = generateExamplePlayer();
        PlayerDataSave.savePlayerInJson(playerData);
    }

    private PlayerData generateExamplePlayer(){
        String firstNameTest = "Jakub";
        String middleNameTest = "Antoni";
        String surnameTest = "Szwajcok";
        String teamTest = "Nigma";
        int idTest = 13;

        Name name = new Name.Builder().firstName(firstNameTest)
                .middleName(middleNameTest)
                .surname(surnameTest)
                .build();

        PlayerData playerData = new PlayerData.Builder().name(name)
                .team(teamTest)
                .id(idTest)
                .build();

        return playerData;
    }
}