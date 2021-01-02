package com.competition.JSONDataBase.PlayerData;

import com.competition.JSONDataBase.Json.JsonFileReaderWriter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class PlayerDataSave {

    public static void savePlayerInJson(PlayerData playerData) throws IOException {
        String firstName = playerData.getName().getFirstName();
        String middleName = playerData.getName().getMiddleName();
        String surname = playerData.getName().getSurname();
        String team = playerData.getTeam();
        int id = playerData.getId();
        
        JsonFileReaderWriter.saveFileInLocation(playerData);
    }
}
