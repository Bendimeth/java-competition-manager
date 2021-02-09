package com.competition.JSONDataBase.PlayerRanking.PlayerData;

import com.competition.JSONDataBase.Json.JsonFileReaderWriter;
import com.competition.JSONDataBase.PlayerRanking.PlayerRanking;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

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

    public static PlayerData generateExamplePlayer(){
        String firstNameTest = "Jakub";
        String middleNameTest = "Antoni";
        String surnameTest = "Szwajcok";
        String teamTest = "Nigma";

        Name name = new Name.Builder().firstName(firstNameTest)
                .middleName(middleNameTest)
                .surname(surnameTest)
                .build();

        Record record = new Record.Builder().win(3)
                                            .lose(3)
                                            .build();

        PlayerData playerData = new PlayerData.Builder().name(name)
                .team(teamTest)
                .record(record)
                .build();

        return playerData;
    }
}