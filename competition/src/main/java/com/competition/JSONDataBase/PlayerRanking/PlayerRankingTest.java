package com.competition.JSONDataBase.PlayerRanking;

import com.competition.JSONDataBase.PlayerRanking.PlayerData.Name;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerData;
import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerDataTest;
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
        int exampleID = 13;
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

    private PlayerRanking generateExampleRanking(){
        PlayerRanking playerRanking = new PlayerRanking();

        for(int i = 0; i < 3;i++){
            PlayerData playerData = PlayerDataTest.generateExamplePlayer();
            playerRanking.addPlayerToRanking(playerData);
        }

        PlayerData playerData = PlayerDataTest.generateExamplePlayer();
        playerData.setTeam("Virtus");
        playerRanking.addPlayerToRanking(playerData);

        return  playerRanking;
    }
}