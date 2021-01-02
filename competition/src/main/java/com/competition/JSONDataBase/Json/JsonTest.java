package com.competition.JSONDataBase.Json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class JsonTest {

    String jsonSource  = "{ \"title\" : \"Coder From Scratch\" , \"author\": \"Rui\"}";


    @Test
    public void parse() throws IOException {
        JsonNode node = Json.parse(jsonSource);
        assertEquals(node.get("title").asText(),"Coder From Scratch");
    }
}