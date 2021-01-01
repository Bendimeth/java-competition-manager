package com.competition.JSONDataBase.Json;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFileWriter {

    public static void saveFileInLocation(String inString) throws IOException {
        FileWriter file = new FileWriter("example.json");

        file.write(inString);
        file.flush();
    }
}
