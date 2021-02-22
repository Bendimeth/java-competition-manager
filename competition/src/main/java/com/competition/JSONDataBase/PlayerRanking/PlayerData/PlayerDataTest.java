package com.competition.JSONDataBase.PlayerRanking.PlayerData;

import com.competition.JSONDataBase.Json.JsonFileReaderWriter;
import com.competition.JSONDataBase.PlayerRanking.PlayerRanking;
import com.competition.JSONDataBase.PlayerRanking.Team.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PlayerDataTest {

    @Test
    public void testCreatePlayerData(){

        String firstNameTest = "Jakub";
        String middleNameTest = "Antoni";
        String surnameTest = "Szwajcok";
        int idTest = 13;

        PlayerData playerData = generateExamplePlayer();

        assertEquals(playerData.getName().getFirstName(),firstNameTest);
        assertEquals(playerData.getName().getMiddleName(),middleNameTest);
        assertEquals(playerData.getName().getSurname(),surnameTest);
        assertEquals(playerData.getId(),idTest);
    }

    public static PlayerData generateExamplePlayer(){
        String firstNameTest = "Jakub";
        String middleNameTest = "Antoni";
        String surnameTest = "Szwajcok";

        Name name = new Name.Builder().firstName(firstNameTest)
                .middleName(middleNameTest)
                .surname(surnameTest)
                .build();

        Record record = new Record.Builder().win(3)
                                            .lose(3)
                                            .build();

        PlayerData playerData = new PlayerData.Builder().name(name)
                .record(record)
                .build();

        playerData.setId(13);

        return playerData;
    }
}