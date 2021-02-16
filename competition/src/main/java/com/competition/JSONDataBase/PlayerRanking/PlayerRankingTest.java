package com.competition.JSONDataBase.PlayerRanking;

import com.competition.JSONDataBase.PlayerRanking.PlayerData.Name;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerData;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerDataTest;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.Record;
import com.competition.JSONDataBase.PlayerRanking.Team.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PlayerRankingTest {

    //good
    @Test
    public void testSavingRankingToFile() throws IOException {
        PlayerRanking playerRanking = generateExampleRanking();
        playerRanking.saveRankingToFile();
    }

    //good
    @Test
    public void testLoadRankingFromFile() throws IOException {
        PlayerRanking playerRanking = new PlayerRanking();
        playerRanking.loadRankingFromFile();
        playerRanking.printPlayerRankingInfo();
        System.out.println("player base length : " + playerRanking.getListOfPlayersTeams().size());
        System.out.println("Last generated ID: " + playerRanking.getLastGeneratedID());
    }

    //good
    @Test
    public void testSaveAndLoad() throws IOException {
        testSavingRankingToFile();
        testLoadRankingFromFile();
    }

    //good
    @Test
    public void testRemovePlayerFromListByName() throws IOException {
        PlayerRanking playerRanking = generateExampleRanking();
        Name name = new Name.Builder().firstName("Jakub").middleName("Antoni").surname("Poniatowski").build();
        playerRanking.printPlayerRankingInfo();
        System.out.println("Player ranking size before: " + playerRanking.getListOfPlayersTeams().size());
        playerRanking.removePlayerFromRankingByName(name);
        System.out.println("Player ranking size after: " + playerRanking.getListOfPlayersTeams().size());
        playerRanking.printPlayerRankingInfo();
    }

    //good
    @Test
    public void testUpdatePlayerByObject() throws IOException {
        PlayerRanking playerRanking = generateExampleRanking();
        PlayerData pd = PlayerDataTest.generateExamplePlayer();
        Team team = new Team(new Name.Builder().teamName("czolgi").build());
        playerRanking.addPlayerToRanking(pd,team);
        playerRanking.printPlayerRankingInfo();
        pd.setRecord(new Record.Builder().win(20).lose(20).build());
        System.out.println(" ");
        playerRanking.updatePlayer(pd,null);
        playerRanking.printPlayerRankingInfo();
    }

    //good
    @Test
    public void testRemovePlayerByIndex() throws IOException {
        PlayerRanking playerRanking = generateExampleRanking();
        int exampleID = 5;
        playerRanking.printPlayerRankingInfo();
        System.out.println("Player ranking size before: " + playerRanking.getListOfPlayersTeams().size());
        playerRanking.removePlayerTeamFromByID(exampleID);
        System.out.println("Player ranking size after: " + playerRanking.getListOfPlayersTeams().size());
        playerRanking.printPlayerRankingInfo();
    }

    //good
    @Test
    public void testRemoveTeamFromRanking() throws IOException{
        PlayerRanking playerRanking = generateExampleRanking();
        playerRanking.printPlayerRankingInfo();
        System.out.println(" ");
        playerRanking.removeTeamFromRankingByName(new Name.Builder().teamName("Cichociemni").build());
        playerRanking.printPlayerRankingInfo();
    }

    //good
    @Test
    public  void testUpdatePlayerDataById() throws IOException {
        PlayerRanking playerRanking = new PlayerRanking();
        PlayerData playerData = PlayerDataTest.generateExamplePlayer();
        playerRanking.addPlayerToRanking(playerData,null);
        playerRanking.printPlayerRankingInfo();
        PlayerData playerData1 = PlayerDataTest.generateExamplePlayer();
        playerData1.setRecord(new Record.Builder().win(20).lose(20).build());
        int id = playerRanking.getTeamPlayerIDByName(playerData.getName());
        playerRanking.updatePlayerByID(id,playerData1,null);
        playerRanking.saveRankingToFile();
        playerRanking.printPlayerRankingInfo();
    }

    //good
    @Test
    public void testGetIdPlayerByName() throws IOException {
        PlayerData playerData = PlayerDataTest.generateExamplePlayer();
        PlayerRanking playerRanking = generateExampleRanking();
        playerRanking.addPlayerToRanking(playerData,null);
        Name nameOfPlayer = playerData.getName();

        int id = playerRanking.getTeamPlayerIDByName(nameOfPlayer);
        System.out.println("ID: " + id);
    }

    //good
    @Test
    public static PlayerRanking generateExampleRanking() throws IOException {
        PlayerRanking playerRanking = new PlayerRanking();

        {
            Name name = new Name.Builder().firstName("Piotr")
                    .middleName("Karol")
                    .surname("Kowalski")
                    .build();

            Record record = new Record.Builder().win(3)
                    .lose(3)
                    .build();

            Name teamName = new Name.Builder().teamName("Rodzynki").build();
            Team team = new Team(teamName);

            PlayerData playerData = PlayerData.generatePlayer(name, record);
            playerRanking.addPlayerToRanking(playerData,team);
        }


        {
            Name name = new Name.Builder().firstName("Tomek")
                    .middleName("Miłosz")
                    .surname("Nowak")
                    .build();

            Record record = new Record.Builder().win(5)
                    .lose(1)
                    .build();

            Name teamName = new Name.Builder().teamName("Cichociemni").build();
            Team team = new Team(teamName);

            PlayerData playerData = PlayerData.generatePlayer(name, record);
            playerRanking.addPlayerToRanking(playerData,team);
        }

        {
            Name name = new Name.Builder().firstName("Jakub")
                    .middleName("Antoni")
                    .surname("Poniatowski")
                    .build();

            Record record = new Record.Builder().win(0)
                    .lose(4)
                    .build();

            Name teamName = new Name.Builder().teamName("Cichociemni").build();
            Team team = new Team(teamName);

            PlayerData playerData = PlayerData.generatePlayer(name, record);
            playerRanking.addPlayerToRanking(playerData,team);
        }

        {
            Name name = new Name.Builder().firstName("Bartek")
                    .middleName("Zbigniew")
                    .surname("Krzywda")
                    .build();

            Record record = new Record.Builder().win(2)
                    .lose(6)
                    .build();

            Name teamName = new Name.Builder().teamName("Magnus").build();
            Team team = new Team(teamName);

            PlayerData playerData = PlayerData.generatePlayer(name, record);
            playerRanking.addPlayerToRanking(playerData,team);
        }

        {
            Name name = new Name.Builder().firstName("Zuzanna")
                    .middleName("Ewa")
                    .surname("Kowalczyk")
                    .build();

            Record record = new Record.Builder().win(10)
                    .lose(2)
                    .build();

            Name teamName = new Name.Builder().teamName("Europa").build();
            Team team = new Team(teamName);

            PlayerData playerData = PlayerData.generatePlayer(name, record);
            playerRanking.addPlayerToRanking(playerData,team);
        }

        {
            Name name = new Name.Builder().firstName("Magdalena")
                    .middleName("Zuzanna")
                    .surname("Wieczorek")
                    .build();

            Record record = new Record.Builder().win(8)
                    .lose(4)
                    .build();

            Name teamName = new Name.Builder().teamName("Europa").build();
            Team team = new Team(teamName);

            PlayerData playerData = PlayerData.generatePlayer(name, record);
            playerRanking.addPlayerToRanking(playerData,team);
        }

        {
            Name name = new Name.Builder().firstName("Julia")
                    .middleName("Zofia")
                    .surname("Paderewska")
                    .build();

            Record record = new Record.Builder().win(16)
                    .lose(4)
                    .build();

            Name teamName = new Name.Builder().teamName("Europa").build();
            Team team = new Team(teamName);

            PlayerData playerData = PlayerData.generatePlayer(name, record);
            playerRanking.addPlayerToRanking(playerData,team);
        }

        {
            Name name = new Name.Builder().firstName("Jan")
                    .middleName("Aleksander")
                    .surname("Kowalczyk")
                    .build();

            Record record = new Record.Builder().win(20)
                    .lose(2)
                    .build();


            PlayerData playerData = PlayerData.generatePlayer(name, record);
            playerRanking.addPlayerToRanking(playerData,null);
        }

        return playerRanking;
    }
}