package com.competition.JSONDataBase.Json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;

public class Json {

    private  static  ObjectMapper objectMapper = getDefaultObjectMapper();

    private  static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return  defaultObjectMapper;
    }

    public  static JsonNode parse(String src) throws IOException {
       return objectMapper.readTree(src);
    }

    public  static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node,clazz);
    }

    public  static  JsonNode toJason(Object a){
        return objectMapper.valueToTree(a);
    }

    public  static  String jsonToString(JsonNode node) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();

        return objectWriter.writeValueAsString(node);
    }
}
