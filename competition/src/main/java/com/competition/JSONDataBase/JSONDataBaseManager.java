package com.competition.JSONDataBase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONDataBaseManager {

    private  static  ObjectMapper objectMapper = getDefaultObjectMapper();

    private  static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();

        return  defaultObjectMapper;
    }

    public  static JsonNode parse(String src) throws IOException {
       return objectMapper.readTree(src);
    }

    public  static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node,clazz);
    }
}
