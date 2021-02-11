package com.competition.JSONDataBase.PlayerRanking;

import com.competition.JSONDataBase.PlayerRanking.PlayerData.Name;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerData;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerDataTest;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.Record;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PlayerRankingTest {

    @Test
    public  void testSavingRankingToFile() throws IOException {
        PlayerRanking playerRanking = generateExampleRanking();
        playerRanking.saveRankingToFile();
    }

    @Test
    public  void testLoadRankingFromFile() throws IOException {
        PlayerRanking playerRanking = new PlayerRanking();
        playerRanking.loadRankingFromFile();
        System.out.println("player base length : " + playerRanking.getListOfPlayers().size());
    }

    @Test
    public void testRemovePlayerFromListByName(){
        PlayerRanking playerRanking = generateExampleRanking();
        Name name = new Name.Builder().firstName("Jakub").middleName("Antoni").surname("Szwajcok").build();
        System.out.println("Player ranking size before: " + playerRanking.getListOfPlayers().size());
        playerRanking.removePlayerFromRankingByName(name);
        System.out.println("Player ranking size after: " + playerRanking.getListOfPlayers().size());
    }

    @Test
    public  void testRemovePlayerByIndex(){
        PlayerRanking playerRanking = generateExampleRanking();
        int exampleID = 7;
        System.out.println("Player ranking size before: " + playerRanking.getListOfPlayers().size());
        playerRanking.removePlayerFromByID(exampleID);
        System.out.println("Player ranking size after: " + playerRanking.getListOfPlayers().size());
    }

    @Test
    public  void testUpdatePlayerDataById() throws IOException {
        PlayerRanking playerRanking = new PlayerRanking();

        PlayerData playerData = PlayerDataTest.generateExamplePlayer();
        playerRanking.addPlayerToRanking(playerData);

        PlayerData playerData1 = PlayerDataTest.generateExamplePlayer();
        playerData1.setTeam("Virtus");

        playerRanking.updatePlayerByID(0,playerData1);
        playerRanking.saveRankingToFile();
    }

    @Test
    public void testGetIdPlayerByName(){
        PlayerData playerData = PlayerDataTest.generateExamplePlayer();
        PlayerRanking playerRanking = generateExampleRanking();
        Name nameOfPlayer = playerData.getName();

        int id = playerRanking.getPlayerIDByName(nameOfPlayer);
        System.out.println("ID: " + id);
    }

    @Test
    public static PlayerRanking generateExampleRanking(){
        PlayerRanking playerRanking = new PlayerRanking();

        {
            Name name = new Name.Builder().firstName("Piotr")
                    .middleName("Karol")
                    .surname("Kowalski")
                    .build();

            Record record = new Record.Builder().win(3)
                    .lose(3)
                    .build();

            PlayerData playerData = PlayerData.generatePlayer(name, record, "Rodzynki");
            playerRanking.addPlayerToRanking(playerData);
        }

        {
            Name name = new Name.Builder().firstName("Tomek")
                    .middleName("Miłosz")
                    .surname("Nowak")
                    .build();

            Record record = new Record.Builder().win(5)
                    .lose(1)
                    .build();

            PlayerData playerData = PlayerData.generatePlayer(name, record, "Cichociemni");
            playerRanking.addPlayerToRanking(playerData);
        }

        {
            Name name = new Name.Builder().firstName("Jakub")
                    .middleName("Antoni")
                    .surname("Poniatowski")
                    .build();

            Record record = new Record.Builder().win(0)
                    .lose(4)
                    .build();

            PlayerData playerData = PlayerData.generatePlayer(name, record, "Cichociemni");
            playerRanking.addPlayerToRanking(playerData);
        }

        {
            Name name = new Name.Builder().firstName("Bartek")
                    .middleName("Zbigniew")
                    .surname("Krzywda")
                    .build();

            Record record = new Record.Builder().win(2)
                    .lose(6)
                    .build();

            PlayerData playerData = PlayerData.generatePlayer(name, record, "Magnus");
            playerRanking.addPlayerToRanking(playerData);
        }

        {
            Name name = new Name.Builder().firstName("Zuzanna")
                    .middleName("Ewa")
                    .surname("Kowalczyk")
                    .build();

            Record record = new Record.Builder().win(10)
                    .lose(2)
                    .build();

            PlayerData playerData = PlayerData.generatePlayer(name, record, "Europa");
            playerRanking.addPlayerToRanking(playerData);
        }

        {
            Name name = new Name.Builder().firstName("Magdalena")
                    .middleName("Zuzanna")
                    .surname("Wieczorek")
                    .build();

            Record record = new Record.Builder().win(8)
                    .lose(4)
                    .build();

            PlayerData playerData = PlayerData.generatePlayer(name, record, "Europa");
            playerRanking.addPlayerToRanking(playerData);
        }

        {
            Name name = new Name.Builder().firstName("Julia")
                    .middleName("Zofia")
                    .surname("Paderewska")
                    .build();

            Record record = new Record.Builder().win(16)
                    .lose(4)
                    .build();

            PlayerData playerData = PlayerData.generatePlayer(name, record, "Europa");
            playerRanking.addPlayerToRanking(playerData);
        }

        {
            Name name = new Name.Builder().firstName("Miłosz")
                    .middleName("Aleksander")
                    .surname("Kowalczyk")
                    .build();

            Record record = new Record.Builder().win(20)
                    .lose(2)
                    .build();

            PlayerData playerData = PlayerData.generatePlayer(name, record, "Rodzynki");
            playerRanking.addPlayerToRanking(playerData);
        }

        return playerRanking;
    }
}