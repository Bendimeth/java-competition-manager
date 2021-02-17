package com.competition.JSONDataBase.Json;

import com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerData;
import com.competition.JSONDataBase.PlayerRanking.PlayerRanking;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.lang.reflect.Type;

public class JsonFileReaderWriter {

    //private static String path;

    public static <A> void saveJsonObjectInFile(A obj, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path),obj);
    }

    public  static <A> A readPlayerRankingFromFile(A obj,String path) throws IOException {
        FileReader reader = new FileReader(path);
        ObjectMapper objectMapper = new ObjectMapper();
        A objectFile = (A) objectMapper.readValue(new File(path),obj.getClass());

        return objectFile;
    }
}
