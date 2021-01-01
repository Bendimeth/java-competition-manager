package com.competition.JSONDataBase.Json;

import com.competition.JSONDataBase.SimpleTestCaseJson;
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


    @Test
    public void fromJson() throws IOException {
        JsonNode node = Json.parse(jsonSource);
        SimpleTestCaseJson pojo = Json.fromJson(node,SimpleTestCaseJson.class);

        assertEquals(pojo.getTitle(),"Coder From Scratch");
    }

    @Test
    public void toJson(){
        SimpleTestCaseJson pojo = new SimpleTestCaseJson();
        String testString = "Testing 123";

        pojo.setTitle(testString);

        JsonNode node = Json.toJason(pojo);

        assertEquals(node.get("title").asText(),testString);
    }

    @Test
    public void jsonToString() throws JsonProcessingException {
        SimpleTestCaseJson pojo = new SimpleTestCaseJson();
        pojo.setTitle("Testing 123");
        JsonNode node = Json.toJason(pojo);

        System.out.println(Json.jsonToString(node));
    }
}