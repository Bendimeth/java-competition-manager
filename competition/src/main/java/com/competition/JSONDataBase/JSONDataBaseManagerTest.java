package com.competition.JSONDataBase;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class JSONDataBaseManagerTest {

    String jsonSource  = "{ \"title\" : \"Coder From Scratch\" }";


    @Test
    public void parse() throws IOException {
        JsonNode node = JSONDataBaseManager.parse(jsonSource);
        assertEquals(node.get("title").asText(),"Coder From Scratch");
    }


    @Test
    public void fromJson() throws IOException {
        JsonNode node = JSONDataBaseManager.parse(jsonSource);
        SimpleTestCaseJson pojo = JSONDataBaseManager.fromJson(node,SimpleTestCaseJson.class);

        System.out.println("Pojo title: " + pojo.title);
    }




    /*
    public  static void main(String[] args)
    {

    }

     */
}