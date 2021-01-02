package com.competition.JSONDataBase.Json;

import com.competition.JSONDataBase.PlayerData.PlayerData;
import com.competition.JSONDataBase.PlayerData.PlayerDataSave;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.*;

public class JsonFileReaderWriter {

    private static String path;

    public static void saveFileInLocation(PlayerData content) throws IOException {
        setPath();
        //FileWriter file = new FileWriter(path);
        ///file.write(content);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path),content);
        //file.flush();
    }

    public  static String readFile() throws IOException {
        setPath();
        FileReader reader = new FileReader(path);
        ObjectMapper objectMapper = new ObjectMapper();

        PlayerData content = objectMapper.readValue(new File(path),PlayerData.class);
        //Object obj = objectMapper
        //JsonNode node = Json.parse(reader.toString());
        System.out.println("text: " + content.getTeam());

        return null;
    }

    private static void setPath(){
        String pathToAppData = System.getenv("APPDATA");
        String nameOfFile = "example.json";
        path = pathToAppData + "/"+ nameOfFile;
        System.out.println("Path: " + path);
    }
}
